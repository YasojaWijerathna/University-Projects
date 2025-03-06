/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package lk.styrox.pharma_care.panels;

import java.util.Calendar;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import lk.styrox.pharma_care.chart_model.ModelPolarAreaChart;
import lk.styrox.pharma_care.chart_model.PolarAreaChart;
import java.sql.Connection;
import java.util.logging.FileHandler;
import lk.styrox.pharma_care.connection.Mysql;
import lk.styrox.pharma_care.logger.Logger;
import lk.styrox.pharma_care.table_model.TableCustom;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import raven.toast.Notifications;

/**
 *
 * @author thara
 */
public class Dashboard extends javax.swing.JPanel {

    private static FileHandler handler;
    private static final String MYSQLDUMP_PATH = "C:/Program Files/MySQL/MySQL Server 8.0/bin";

    private static final String USERNAME = "root", PASSWORD = "colombo123", DATABASE = "pharma_care";

    public Dashboard(JFrame parent) {
        initComponents();
        parent.getContentPane().setBackground(new Color(255, 255, 255));
        loadChart();
        loadDetails();
        loadEmployee();

        gradiantPanel2.changeRoundPanel(Color.decode("#44A0E6"), Color.decode("#B2D6F0"));
        gradiantPanel3.changeRoundPanel(Color.decode("#FF989E"), Color.decode("#FFD2D3"));
        gradiantPanel1.changeRoundPanel(Color.decode("#45D1BF"), Color.decode("#AEECE3"));

        roundedBtn3.repaintBtn(Color.decode("#B2D6F0"), Color.decode("#44A0E6"));
        roundedBtn4.repaintBtn(Color.decode("#FFD2D3"), Color.decode("#FF989E"));
        roundedBtn5.repaintBtn(Color.decode("#AEECE3"), Color.decode("#45D1BF"));
        TableCustom.apply(jScrollPane1, TableCustom.TableType.MULTI_LINE);
    }

    private void loadChart() {
        double Returns = 0;
        double GRN = 0;
        double Sellings = 0;

        try {
            ResultSet returns_rs = Mysql.search("SELECT SUM(`qty`) AS sum FROM `goods_return_item`");
            if (returns_rs.next()) {
                Returns = returns_rs.getDouble("sum");
            }
            ResultSet grn_rs = Mysql.search("SELECT SUM(`qty`) AS sum FROM `goods_receive_item`");
            if (grn_rs.next()) {
                GRN = grn_rs.getDouble("sum");
            }
            ResultSet selling_rs = Mysql.search("SELECT SUM(`qty`) AS sum FROM `invoice_item`");
            if (selling_rs.next()) {
                Sellings = selling_rs.getDouble("sum");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        polarAreaChart2.addItem(new ModelPolarAreaChart(new Color(52, 148, 203), "Returns", Returns));
        polarAreaChart2.addItem(new ModelPolarAreaChart(new Color(175, 67, 237), "GRN", GRN));
        polarAreaChart2.addItem(new ModelPolarAreaChart(new Color(87, 218, 137), "Sellings", Sellings));
        polarAreaChart2.start();
    }

    private void loadEmployee() {

        try {
            ResultSet rs = Mysql.search("SELECT * FROM `user` JOIN `status` ON `status`.`id` = `user`.`status_id` ");

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);
            while (rs.next()) {
                Vector<String> data = new Vector<>();
                data.add(rs.getString("id"));
                data.add(rs.getString("fname") + " " + rs.getString("lname"));
                data.add(rs.getString("email"));
                data.add(rs.getString("join_date"));
                data.add(rs.getString("name"));
                model.addRow(data);
            }
            jTable1.setModel(model);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void backUP() {
        try {

            CodeSource codeSource = Dashboard.class.getProtectionDomain().getCodeSource();
            File jarFile = new File(codeSource.getLocation().toURI().getPath());
            String jarDir = jarFile.getParentFile().getPath();

            String BACKUP_DIRECTORY = jarDir;

            String timeStamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
            String fileName = String.format("%s_%s.sql", DATABASE, timeStamp);

            String Command = String.format("%s/mysqldump -u%s -p%s --add-drop-database %s -r%s/%s",
                    MYSQLDUMP_PATH, USERNAME, PASSWORD, DATABASE, BACKUP_DIRECTORY, fileName);

          
            
            Process process = Runtime.getRuntime().exec(Command);
            int waitFor = process.waitFor();

            if (waitFor == 0) {
                Notifications.getInstance().show(Notifications.Type.SUCCESS,
                        Notifications.Location.TOP_CENTER, "Database BackUp Successfully");
            } else {
                Notifications.getInstance().show(Notifications.Type.WARNING,
                        Notifications.Location.TOP_CENTER, "Database BackUp Failed");
            }

        } catch (IOException | InterruptedException | URISyntaxException ex) {
            Logger.AdminlLog(String.valueOf(ex));
            ex.printStackTrace();
        }
    }

    private void loadDetails() {

        double Earnings = 0;
        int unitsSOld = 0;
        double Sellings = 0;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {

            String query = "SELECT * FROM `invoice_item`"
                    + "INNER JOIN `stock` ON `invoice_item`.`barcode`=`stock`.`barcode`"
                    + "INNER JOIN `invoice` ON `invoice`.`id`=`invoice_item`.`invoice_id` ";

            Calendar calendar = Calendar.getInstance();
            Date today = calendar.getTime();

            calendar.add(Calendar.DAY_OF_MONTH, 30);
            Date datePlus30Days = calendar.getTime();

            query += " WHERE `invoice`.`issue_date` < '" + sdf.format(datePlus30Days) + "'";

            ResultSet returns_rs = Mysql.search(query);
            while (returns_rs.next()) {
                int qty = returns_rs.getInt("invoice_item.qty");
                double selling_price = returns_rs.getDouble("selling_price");
                double buying_price = returns_rs.getDouble("buying_price");
                double profit = (selling_price - buying_price) * qty;
                Earnings = Earnings + profit;
            }

            ResultSet invoice_rs = Mysql.search("SELECT SUM(`qty`) AS sum FROM `invoice_item`");
            if (invoice_rs.next()) {
                unitsSOld = invoice_rs.getInt("sum");
            }

            ResultSet selling_rs = Mysql.search("SELECT SUM(`total`) AS sum FROM `invoice`");
            if (selling_rs.next()) {
                Sellings = selling_rs.getDouble("sum");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        jLabel3.setText("Rs." + String.valueOf(Earnings));
        jLabel6.setText(String.valueOf(unitsSOld));
        jLabel8.setText("Rs." + String.valueOf(Sellings));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background1 = new lk.styrox.pharma_care.main_components.Background();
        background2 = new lk.styrox.pharma_care.main_components.Background();
        jLabel2 = new javax.swing.JLabel();
        roundedBtn3 = new lk.styrox.pharma_care.main_components.RoundedBtn();
        roundedBtn4 = new lk.styrox.pharma_care.main_components.RoundedBtn();
        roundedBtn5 = new lk.styrox.pharma_care.main_components.RoundedBtn();
        gradiantPanel2 = new lk.styrox.pharma_care.main_components.GradiantPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        gradiantPanel3 = new lk.styrox.pharma_care.main_components.GradiantPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        gradiantPanel1 = new lk.styrox.pharma_care.main_components.GradiantPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        background4 = new lk.styrox.pharma_care.main_components.Background();
        jLabel4 = new javax.swing.JLabel();
        polarAreaChart2 = new lk.styrox.pharma_care.chart_model.PolarAreaChart();
        jLabel9 = new javax.swing.JLabel();

        background1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Leelawadee", 1, 14)); // NOI18N
        jLabel2.setText("Processes");

        roundedBtn3.setForeground(new java.awt.Color(255, 255, 255));
        roundedBtn3.setText("Product Report");
        roundedBtn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundedBtn3ActionPerformed(evt);
            }
        });

        roundedBtn4.setForeground(new java.awt.Color(255, 255, 255));
        roundedBtn4.setText("Monthly Sale Report");
        roundedBtn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundedBtn4ActionPerformed(evt);
            }
        });

        roundedBtn5.setForeground(new java.awt.Color(255, 255, 255));
        roundedBtn5.setText("BackUp Database");
        roundedBtn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundedBtn5ActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("Monthly Earnings");

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel3.setText("Rs. 12000.00");

        javax.swing.GroupLayout gradiantPanel2Layout = new javax.swing.GroupLayout(gradiantPanel2);
        gradiantPanel2.setLayout(gradiantPanel2Layout);
        gradiantPanel2Layout.setHorizontalGroup(
            gradiantPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gradiantPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        gradiantPanel2Layout.setVerticalGroup(
            gradiantPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gradiantPanel2Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(gradiantPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addGap(26, 26, 26))
        );

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 204, 204));
        jLabel5.setText("Total Products Sold");

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel6.setText("Rs. 5600.00");

        javax.swing.GroupLayout gradiantPanel3Layout = new javax.swing.GroupLayout(gradiantPanel3);
        gradiantPanel3.setLayout(gradiantPanel3Layout);
        gradiantPanel3Layout.setHorizontalGroup(
            gradiantPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gradiantPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        gradiantPanel3Layout.setVerticalGroup(
            gradiantPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gradiantPanel3Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(gradiantPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(204, 204, 204));
        jLabel7.setText("Total Sale");

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel8.setText("Rs. 17500.00");

        javax.swing.GroupLayout gradiantPanel1Layout = new javax.swing.GroupLayout(gradiantPanel1);
        gradiantPanel1.setLayout(gradiantPanel1Layout);
        gradiantPanel1Layout.setHorizontalGroup(
            gradiantPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gradiantPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );
        gradiantPanel1Layout.setVerticalGroup(
            gradiantPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gradiantPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(gradiantPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout background2Layout = new javax.swing.GroupLayout(background2);
        background2.setLayout(background2Layout);
        background2Layout.setHorizontalGroup(
            background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(background2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(background2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(roundedBtn4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                            .addComponent(roundedBtn5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(roundedBtn3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(44, 44, 44)
                        .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(gradiantPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(gradiantPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(gradiantPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(35, 35, 35))))
        );
        background2Layout.setVerticalGroup(
            background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(background2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(gradiantPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(gradiantPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(gradiantPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(background2Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(roundedBtn3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(roundedBtn4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(roundedBtn5, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jTable1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Full Name", "Email", "Joined Date", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel4.setFont(new java.awt.Font("Leelawadee", 1, 14)); // NOI18N
        jLabel4.setText("Total Growth");

        javax.swing.GroupLayout background4Layout = new javax.swing.GroupLayout(background4);
        background4.setLayout(background4Layout);
        background4Layout.setHorizontalGroup(
            background4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background4Layout.createSequentialGroup()
                .addGroup(background4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(background4Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel4))
                    .addGroup(background4Layout.createSequentialGroup()
                        .addContainerGap(88, Short.MAX_VALUE)
                        .addComponent(polarAreaChart2, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(90, Short.MAX_VALUE))
        );
        background4Layout.setVerticalGroup(
            background4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(polarAreaChart2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel9.setFont(new java.awt.Font("Leelawadee", 1, 12)); // NOI18N
        jLabel9.setText("Users");

        javax.swing.GroupLayout background1Layout = new javax.swing.GroupLayout(background1);
        background1.setLayout(background1Layout);
        background1Layout.setHorizontalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(background1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(background1Layout.createSequentialGroup()
                        .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(background1Layout.createSequentialGroup()
                                .addComponent(background2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(background4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1))
                        .addGap(22, 22, 22))))
        );
        background1Layout.setVerticalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(background2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(background4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(background1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(28, 28, 28))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(background1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void roundedBtn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundedBtn3ActionPerformed

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            Connection databaseConnection = Mysql.GetConnection();

            InputStream logo = getClass().getResourceAsStream("/lk/styrox/pharma_care/report_img/logo.png");
            InputStream bg = getClass().getResourceAsStream("/lk/styrox/pharma_care/report_img/Report_bg.png");

            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put("date", sdf.format(new Date()));
            parameters.put("bg", bg);
            parameters.put("logo", logo);

            InputStream reportInput = getClass().getResourceAsStream("/lk/styrox/pharma_care/reports/product.jasper");

            JasperPrint report = JasperFillManager.fillReport(reportInput, parameters, databaseConnection);
            JasperViewer.viewReport(report, false);
        } catch (JRException e) {
            Logger.AdminlLog(String.valueOf(e));
            e.printStackTrace();
        }
    }//GEN-LAST:event_roundedBtn3ActionPerformed

    private void roundedBtn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundedBtn4ActionPerformed
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            Connection databaseConnection = Mysql.GetConnection();

            InputStream logo = getClass().getResourceAsStream("/lk/styrox/pharma_care/report_img/logo.png");
            InputStream bg = getClass().getResourceAsStream("/lk/styrox/pharma_care/report_img/Report_bg.png");

            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put("date", sdf.format(new Date()));
            parameters.put("bg", bg);
            parameters.put("logo", logo);

            InputStream reportInput = getClass().getResourceAsStream("/lk/styrox/pharma_care/reports/sales.jasper");

            JasperPrint report = JasperFillManager.fillReport(reportInput, parameters, databaseConnection);
            JasperViewer.viewReport(report, false);
        } catch (JRException e) {
            Logger.AdminlLog(String.valueOf(e));
            e.printStackTrace();
        }
    }//GEN-LAST:event_roundedBtn4ActionPerformed

    private void roundedBtn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundedBtn5ActionPerformed
        backUP();

    }//GEN-LAST:event_roundedBtn5ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private lk.styrox.pharma_care.main_components.Background background1;
    private lk.styrox.pharma_care.main_components.Background background2;
    private lk.styrox.pharma_care.main_components.Background background4;
    private lk.styrox.pharma_care.main_components.GradiantPanel gradiantPanel1;
    private lk.styrox.pharma_care.main_components.GradiantPanel gradiantPanel2;
    private lk.styrox.pharma_care.main_components.GradiantPanel gradiantPanel3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private lk.styrox.pharma_care.chart_model.PolarAreaChart polarAreaChart2;
    private lk.styrox.pharma_care.main_components.RoundedBtn roundedBtn3;
    private lk.styrox.pharma_care.main_components.RoundedBtn roundedBtn4;
    private lk.styrox.pharma_care.main_components.RoundedBtn roundedBtn5;
    // End of variables declaration//GEN-END:variables
}
