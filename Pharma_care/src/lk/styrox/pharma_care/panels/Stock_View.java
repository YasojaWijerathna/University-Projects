/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package lk.styrox.pharma_care.panels;

import java.awt.Color;
import java.awt.Frame;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import lk.styrox.pharma_care.connection.Mysql;
import lk.styrox.pharma_care.dialogs.ImpendingExpiration;
import lk.styrox.pharma_care.dialogs.StockExpired;
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
public class Stock_View extends javax.swing.JPanel {

    /**
     * Creates new form Stock_View
     */
    private JFrame dashboard;
    private HashMap<String, Integer> categoryMap;
    private HashMap<String, Integer> brandMap;

    public Stock_View(JFrame dashboard) {
        initComponents();
        this.dashboard = dashboard;
        init();
        Notifications.getInstance().setJFrame(dashboard);
    }

    private void init() {

        categoryMap = new HashMap<>();
        brandMap = new HashMap<>();

        roundedBtn1.repaintBtn(Color.decode("#ACFDAA"), Color.decode("#ACFDAA"));
        roundedBtn2.repaintBtn(Color.decode("#D2D2D2"), Color.decode("#D2D2D2"));

        roundedBtnProcesses3.repaintBtn(Color.decode("#000000"), 1);
        roundedBtnProcesses2.repaintBtn(Color.decode("#FCD923"), 1);
        roundedBtnProcesses1.repaintBtn(Color.decode("#CB2525"), 1);

        loadStock();
        loadCategory();
        loadBrand();
        TableCustom.apply(jScrollPane1, TableCustom.TableType.DEFAULT);

    }

    private void loadCategory() {

        try {

            ResultSet rs = Mysql.search("SELECT * FROM `category`");

            Vector<String> data = new Vector<>();
            data.add("Select Category");
            while (rs.next()) {
                data.add(rs.getString("name"));
                categoryMap.put(rs.getString("name"), rs.getInt("id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(data);
            jComboBox2.setModel(model);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void loadBrand() {

        try {

            ResultSet rs = Mysql.search("SELECT * FROM `brand`");

            Vector<String> data = new Vector<>();
            data.add("Select Brand");
            while (rs.next()) {
                data.add(rs.getString("name"));
                brandMap.put(rs.getString("name"), rs.getInt("id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(data);
            jComboBox1.setModel(model);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void loadStock() {

        try {

            ResultSet rs = Mysql.search("SELECT `barcode`,`qty`,`selling_price`,`buying_price`,`mfd`,`exp`,`status`.`name` AS `status_name`,\n"
                    + "`product`.`name` AS `product_name`, `brand`.`name` AS `brand_name`, `category`.`name` AS `category_name` FROM `stock`\n"
                    + "JOIN product ON stock.product_id = product.id\n"
                    + "JOIN `status` ON stock.status_id = status.id\n"
                    + "JOIN category ON product.category_id = category.id\n"
                    + "JOIN brand ON product.Brand_id = brand.id");

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            while (rs.next()) {

                Vector<String> data = new Vector<>();
                data.add(rs.getString("barcode"));
                data.add(rs.getString("product_name"));
                data.add(rs.getString("category_name"));
                data.add(rs.getString("brand_name"));
                data.add(rs.getString("qty"));
                data.add(rs.getString("buying_price"));
                data.add(rs.getString("selling_price"));
                data.add(rs.getString("mfd"));
                data.add(rs.getString("exp"));
                data.add(rs.getString("status_name"));
                model.addRow(data);

            }

            jTable1.setModel(model);

        } catch (SQLException e) {
            throw new RuntimeException();
        }

    }

    private void clear() {
        roundTextField1.setText("");
        roundTextField2.setText("");
        jComboBox2.setSelectedIndex(0);
        jComboBox1.setSelectedIndex(0);
        jDateChooser1.setDate(null);
        jDateChooser2.setDate(null);
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
        roundedBtnProcesses1 = new lk.styrox.pharma_care.main_components.roundedBtnProcesses();
        roundedBtnProcesses2 = new lk.styrox.pharma_care.main_components.roundedBtnProcesses();
        roundedBtnProcesses3 = new lk.styrox.pharma_care.main_components.roundedBtnProcesses();
        background3 = new lk.styrox.pharma_care.main_components.Background();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        roundTextField1 = new lk.styrox.pharma_care.main_components.roundTextField();
        roundTextField2 = new lk.styrox.pharma_care.main_components.roundTextField();
        roundedBtn2 = new lk.styrox.pharma_care.main_components.RoundedBtn();
        roundedBtn1 = new lk.styrox.pharma_care.main_components.RoundedBtn();
        jLabel5 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        background1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Leelawadee", 1, 18)); // NOI18N
        jLabel2.setText("Processes");

        roundedBtnProcesses1.setText("View Expired Stock");
        roundedBtnProcesses1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundedBtnProcesses1ActionPerformed(evt);
            }
        });

        roundedBtnProcesses2.setText("View Stock Impending Expiration");
        roundedBtnProcesses2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundedBtnProcesses2ActionPerformed(evt);
            }
        });

        roundedBtnProcesses3.setText("Print Stock Report");
        roundedBtnProcesses3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundedBtnProcesses3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout background2Layout = new javax.swing.GroupLayout(background2);
        background2.setLayout(background2Layout);
        background2Layout.setHorizontalGroup(
            background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(roundedBtnProcesses3, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(roundedBtnProcesses2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(roundedBtnProcesses1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11))
        );
        background2Layout.setVerticalGroup(
            background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background2Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundedBtnProcesses1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundedBtnProcesses2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundedBtnProcesses3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Leelawadee", 1, 14)); // NOI18N
        jLabel3.setText("Search");

        jSeparator1.setBackground(new java.awt.Color(153, 153, 153));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel1.setText("Stock ID");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel4.setText("Product Name");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel6.setText("Brand");

        roundTextField1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        roundTextField2.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        roundedBtn2.setForeground(new java.awt.Color(136, 136, 136));
        roundedBtn2.setText("Clear All");
        roundedBtn2.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        roundedBtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundedBtn2ActionPerformed(evt);
            }
        });

        roundedBtn1.setBackground(new java.awt.Color(255, 153, 153));
        roundedBtn1.setForeground(new java.awt.Color(3, 148, 0));
        roundedBtn1.setText("Search");
        roundedBtn1.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        roundedBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundedBtn1ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel5.setText("Manufactuer Date");

        jComboBox1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox2.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel7.setText("Category");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel8.setText("Expiry Date");

        javax.swing.GroupLayout background3Layout = new javax.swing.GroupLayout(background3);
        background3.setLayout(background3Layout);
        background3Layout.setHorizontalGroup(
            background3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(background3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
                    .addComponent(roundTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(background3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(background3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(background3Layout.createSequentialGroup()
                            .addComponent(roundedBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(roundedBtn2, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(background3Layout.createSequentialGroup()
                            .addGroup(background3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(roundTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(27, 27, 27)
                            .addGroup(background3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(21, 21, 21)
                            .addGroup(background3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        background3Layout.setVerticalGroup(
            background3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(background3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(background3Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(background3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(background3Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(roundTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(background3Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(roundTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(background3Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(background3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(background3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(background3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(background3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(roundedBtn2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundedBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        jTable1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Stock ID", "Product Name", "Category", "Brand", "Quantity", "Buying Price", "Selling Price ", "Manufacture Date", "Expiry Date", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(190);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(130);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(120);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(60);
            jTable1.getColumnModel().getColumn(9).setPreferredWidth(80);
        }

        javax.swing.GroupLayout background1Layout = new javax.swing.GroupLayout(background1);
        background1.setLayout(background1Layout);
        background1Layout.setHorizontalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1130, Short.MAX_VALUE)
                    .addComponent(background2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(background3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        background1Layout.setVerticalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(background2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(background3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(background1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(25, 25, 25))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(background1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void roundedBtnProcesses2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundedBtnProcesses2ActionPerformed
        ImpendingExpiration view = new ImpendingExpiration((Frame) dashboard, true);
        view.setVisible(true);
        TableCustom.color = "#A1EAB1";
    }//GEN-LAST:event_roundedBtnProcesses2ActionPerformed

    private void roundedBtnProcesses3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundedBtnProcesses3ActionPerformed
     try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            Connection databaseConnection = Mysql.GetConnection();

            InputStream logo = getClass().getResourceAsStream("/lk/styrox/pharma_care/report_img/logo.png");
            InputStream bg = getClass().getResourceAsStream("/lk/styrox/pharma_care/report_img/Report_bg.png");

            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put("date", sdf.format(new Date()));
            parameters.put("bg", bg);
            parameters.put("logo", logo);

            InputStream reportInput = getClass().getResourceAsStream("/lk/styrox/pharma_care/reports/stock.jasper");

            JasperPrint report = JasperFillManager.fillReport(reportInput, parameters, databaseConnection);
            JasperViewer.viewReport(report, false);
        } catch (JRException e) {
            Logger.AdminlLog(String.valueOf(e));
            e.printStackTrace();
        }
    
    }//GEN-LAST:event_roundedBtnProcesses3ActionPerformed

    private void roundedBtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundedBtn2ActionPerformed
        clear();
        loadStock();
    }//GEN-LAST:event_roundedBtn2ActionPerformed

    private void roundedBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundedBtn1ActionPerformed

        String stockID = roundTextField1.getText();
        String productName = roundTextField2.getText();
        String Category = (String) jComboBox2.getSelectedItem();
        String Brand = (String) jComboBox1.getSelectedItem();
        Date mfd = jDateChooser1.getDate();
        Date exd = jDateChooser2.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {

            String query = "SELECT `barcode`,`qty`,`selling_price`,`buying_price`,`mfd`,`exp`,`status`.`name` AS `status_name`,\n"
                    + "`product`.`name` AS `product_name`, `brand`.`name` AS `brand_name`, `category`.`name` AS `category_name` FROM `stock`\n"
                    + "JOIN product ON stock.product_id = product.id\n"
                    + "JOIN `status` ON stock.status_id = status.id\n"
                    + "JOIN category ON product.category_id = category.id\n"
                    + "JOIN brand ON product.Brand_id = brand.id WHERE `barcode` LIKE '%" + stockID + "%' AND `product`.`name` LIKE '%" + productName + "%'";

            if (!Category.equals("Select Category")) {
                query += " AND `category`.`name` = '" + Category + "'";
            }
            if (!Brand.equals("Select Brand")) {
                query += " AND `brand`.`name` = '" + Brand + "'";
            }
            if (mfd != null) {
                query += " AND `mfd` LIKE '%" + sdf.format(mfd) + "%'";
            }
            if (exd != null) {
                query += " AND `exp` LIKE '%" + sdf.format(exd) + "%'";
            }

            ResultSet rs = Mysql.search(query);

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            while (rs.next()) {

                Vector<String> data = new Vector<>();
                data.add(rs.getString("barcode"));
                data.add(rs.getString("product_name"));
                data.add(rs.getString("category_name"));
                data.add(rs.getString("brand_name"));
                data.add(rs.getString("qty"));
                data.add(rs.getString("buying_price"));
                data.add(rs.getString("selling_price"));
                data.add(rs.getString("mfd"));
                data.add(rs.getString("exp"));
                data.add(rs.getString("status_name"));
                model.addRow(data);

            }

            jTable1.setModel(model);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }//GEN-LAST:event_roundedBtn1ActionPerformed

    private void roundedBtnProcesses1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundedBtnProcesses1ActionPerformed
        StockExpired view = new StockExpired((Frame) dashboard, true);
        view.setVisible(true);
        TableCustom.color = "#A1EAB1";
    }//GEN-LAST:event_roundedBtnProcesses1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private lk.styrox.pharma_care.main_components.Background background1;
    private lk.styrox.pharma_care.main_components.Background background2;
    private lk.styrox.pharma_care.main_components.Background background3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private lk.styrox.pharma_care.main_components.roundTextField roundTextField1;
    private lk.styrox.pharma_care.main_components.roundTextField roundTextField2;
    private lk.styrox.pharma_care.main_components.RoundedBtn roundedBtn1;
    private lk.styrox.pharma_care.main_components.RoundedBtn roundedBtn2;
    private lk.styrox.pharma_care.main_components.roundedBtnProcesses roundedBtnProcesses1;
    private lk.styrox.pharma_care.main_components.roundedBtnProcesses roundedBtnProcesses2;
    private lk.styrox.pharma_care.main_components.roundedBtnProcesses roundedBtnProcesses3;
    // End of variables declaration//GEN-END:variables
}
