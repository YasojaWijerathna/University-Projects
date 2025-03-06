/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package lk.styrox.pharma_care.panels;

import java.awt.Font;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import lk.styrox.pharma_care.dialogs.Select_Stock;
import lk.styrox.pharma_care.main_components.roundTextField;
import raven.toast.Notifications;
import java.sql.*;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import lk.styrox.pharma_care.connection.Mysql;
import lk.styrox.pharma_care.gui.CashierDashboard;
import lk.styrox.pharma_care.gui.Login;
import lk.styrox.pharma_care.logger.Logger;
import lk.styrox.pharma_care.table_model.TableCustom;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author dnana
 */
public class Invoice extends javax.swing.JPanel {

    JFrame parentFrame;
    public int maxQty;
    HashMap<String, String> paymentmap = new HashMap<>();
    String user_id = "";
    String user_name = "";

    /**
     * Creates new form Invoice
     */
    public Invoice(JFrame parent) {
        initComponents();
        parentFrame = parent;
        PIDfield.grabFocus();
        loadTable();
        loadPaymentType();
        init();
    }

    public roundTextField getPIDfield() {
        return PIDfield;
    }

    public roundTextField getPNameField() {
        return PNameField;
    }

    public roundTextField getBrandField() {
        return brandField;
    }

    public roundTextField getCategoryField() {
        return categoryField;
    }

    public roundTextField getQtyField() {
        return qtyField;
    }

    public roundTextField getPriceField() {
        return PriceField;
    }

    private void loadTable() {
        TableCustom.apply(tableScrollPane, TableCustom.TableType.DEFAULT);
        JTableHeader header = jTable1.getTableHeader();
        header.setFont(new Font("San-Serif", Font.BOLD, 13));

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        model.addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {

                if (e.getType() == TableModelEvent.INSERT || e.getType() == TableModelEvent.DELETE || e.getType() == TableModelEvent.UPDATE) {
                    int rowcount = jTable1.getRowCount();
                    int totalQTy = 0;
                    double totalAmount = 0;
                    for (int x = 0; x < rowcount; x++) {
                        String qtyStr = String.valueOf(jTable1.getValueAt(x, 3));
                        String amountStr = String.valueOf(jTable1.getValueAt(x, 4));

                        int qty = Integer.parseInt(qtyStr);
                        double amount = Double.parseDouble(amountStr);
                        totalQTy = totalQTy + qty;
                        totalAmount = totalAmount + (qty * amount);
                    }
                    TotalAmountField.setText(String.valueOf(totalAmount));
                    TotalQtyField.setText(String.valueOf(totalQTy));
                }
            }
        });
    }

    public void init() {
        user_id = Login.USER_ID;
        try {
            ResultSet user = Mysql.search("SELECT * FROM `user` WHERE `id`='" + user_id + "'");
            if (user.next()) {

                user_name = user.getString("fname") + " " + user.getString("lname");
                roundTextField10.setText(user_name);
                roundTextField9.setText(user.getString("id"));
            } else {
                System.out.println("no user");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.AdminlLog(String.valueOf(e));
        }

    }

    private void loadPaymentType() {
        try {
            ResultSet payment = Mysql.search("SELECT * FROM `payment_method` WHERE `id`!='2'");

            Vector v = new Vector();
            v.add("Select Payment Method");
            while (payment.next()) {
                v.add(payment.getString("method"));
                paymentmap.put(payment.getString("method"), payment.getString("id"));
            }
            DefaultComboBoxModel m = new DefaultComboBoxModel(v);
            jComboBox1.setModel(m);
        } catch (SQLException e) {
            Logger.CashierLog(String.valueOf(e));
            e.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        background4 = new lk.styrox.pharma_care.main_components.Background();
        jLabel2 = new javax.swing.JLabel();
        PIDfield = new lk.styrox.pharma_care.main_components.roundTextField();
        jLabel5 = new javax.swing.JLabel();
        PNameField = new lk.styrox.pharma_care.main_components.roundTextField();
        roundButton3 = new lk.styrox.pharma_care.main_components.roundButton();
        categoryField = new lk.styrox.pharma_care.main_components.roundTextField();
        jLabel6 = new javax.swing.JLabel();
        brandField = new lk.styrox.pharma_care.main_components.roundTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        PriceField = new lk.styrox.pharma_care.main_components.roundTextField();
        background5 = new lk.styrox.pharma_care.main_components.Background();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        roundTextField9 = new lk.styrox.pharma_care.main_components.roundTextField();
        roundTextField10 = new lk.styrox.pharma_care.main_components.roundTextField();
        jLabel10 = new javax.swing.JLabel();
        backgound6 = new lk.styrox.pharma_care.main_components.Background();
        jLabel16 = new javax.swing.JLabel();
        qtyField = new lk.styrox.pharma_care.main_components.roundTextField();
        roundButton4 = new lk.styrox.pharma_care.main_components.roundButton();
        background7 = new lk.styrox.pharma_care.main_components.Background();
        jLabel17 = new javax.swing.JLabel();
        TotalAmountField = new lk.styrox.pharma_care.main_components.roundTextField();
        roundButton5 = new lk.styrox.pharma_care.main_components.roundButton();
        TotalQtyField = new lk.styrox.pharma_care.main_components.roundTextField();
        jLabel18 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        tableScrollPane = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setOpaque(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1220, 700));

        background4.setBackground(new java.awt.Color(231, 231, 233));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Barcode");

        PIDfield.setBackground(new java.awt.Color(255, 255, 255));
        PIDfield.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Product Name");

        PNameField.setEditable(false);
        PNameField.setBackground(new java.awt.Color(255, 255, 255));
        PNameField.setFocusable(false);
        PNameField.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        roundButton3.setBackground(new java.awt.Color(255, 250, 223));
        roundButton3.setForeground(new java.awt.Color(255, 219, 0));
        roundButton3.setText("Select  Stock");
        roundButton3.setFont(new java.awt.Font("Microsoft JhengHei", 1, 16)); // NOI18N
        roundButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundButton3ActionPerformed(evt);
            }
        });

        categoryField.setEditable(false);
        categoryField.setBackground(new java.awt.Color(255, 255, 255));
        categoryField.setFocusable(false);
        categoryField.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Category");

        brandField.setEditable(false);
        brandField.setBackground(new java.awt.Color(255, 255, 255));
        brandField.setFocusable(false);
        brandField.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Brand");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Price");

        PriceField.setEditable(false);
        PriceField.setBackground(new java.awt.Color(255, 255, 255));
        PriceField.setFocusable(false);
        PriceField.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        javax.swing.GroupLayout background4Layout = new javax.swing.GroupLayout(background4);
        background4.setLayout(background4Layout);
        background4Layout.setHorizontalGroup(
            background4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(background4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(PriceField, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(background4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel7)
                        .addComponent(jLabel6)
                        .addComponent(jLabel5)
                        .addComponent(jLabel2)
                        .addComponent(roundButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(PIDfield, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(PNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(categoryField, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(brandField, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15))
        );
        background4Layout.setVerticalGroup(
            background4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(roundButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PIDfield, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(categoryField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(brandField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PriceField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        background5.setBackground(new java.awt.Color(231, 231, 233));

        jLabel8.setFont(new java.awt.Font("Leelawadee", 1, 17)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Employee Deatils");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Employee ID");

        roundTextField9.setEditable(false);
        roundTextField9.setBackground(new java.awt.Color(255, 255, 255));
        roundTextField9.setFocusable(false);
        roundTextField9.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        roundTextField10.setEditable(false);
        roundTextField10.setBackground(new java.awt.Color(255, 255, 255));
        roundTextField10.setFocusable(false);
        roundTextField10.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Employee Name");

        javax.swing.GroupLayout background5Layout = new javax.swing.GroupLayout(background5);
        background5.setLayout(background5Layout);
        background5Layout.setHorizontalGroup(
            background5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(background5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8)
                    .addComponent(roundTextField9, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                    .addComponent(roundTextField10, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );
        background5Layout.setVerticalGroup(
            background5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roundTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roundTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        backgound6.setBackground(new java.awt.Color(231, 231, 233));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Quantity");

        qtyField.setBackground(new java.awt.Color(255, 255, 255));
        qtyField.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        roundButton4.setBackground(new java.awt.Color(199, 218, 255));
        roundButton4.setForeground(new java.awt.Color(52, 75, 198));
        roundButton4.setText("Add to Table");
        roundButton4.setFont(new java.awt.Font("Microsoft JhengHei", 1, 16)); // NOI18N
        roundButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout backgound6Layout = new javax.swing.GroupLayout(backgound6);
        backgound6.setLayout(backgound6Layout);
        backgound6Layout.setHorizontalGroup(
            backgound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgound6Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(backgound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backgound6Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(backgound6Layout.createSequentialGroup()
                        .addComponent(qtyField, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(roundButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))))
        );
        backgound6Layout.setVerticalGroup(
            backgound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgound6Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(backgound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(qtyField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        background7.setBackground(new java.awt.Color(231, 231, 233));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Total Amount");

        TotalAmountField.setEditable(false);
        TotalAmountField.setBackground(new java.awt.Color(255, 255, 255));
        TotalAmountField.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        roundButton5.setBackground(new java.awt.Color(197, 250, 193));
        roundButton5.setForeground(new java.awt.Color(8, 109, 24));
        roundButton5.setText("Print invoice");
        roundButton5.setFont(new java.awt.Font("Microsoft JhengHei", 1, 16)); // NOI18N
        roundButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundButton5ActionPerformed(evt);
            }
        });

        TotalQtyField.setEditable(false);
        TotalQtyField.setBackground(new java.awt.Color(255, 255, 255));
        TotalQtyField.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Total Quantity");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Select Payment Method");

        javax.swing.GroupLayout background7Layout = new javax.swing.GroupLayout(background7);
        background7.setLayout(background7Layout);
        background7Layout.setHorizontalGroup(
            background7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background7Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(background7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(TotalQtyField, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(background7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TotalAmountField, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addGap(18, 18, 18)
                .addGroup(background7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(background7Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(background7Layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 132, Short.MAX_VALUE)
                        .addComponent(roundButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))))
        );
        background7Layout.setVerticalGroup(
            background7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background7Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(background7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(background7Layout.createSequentialGroup()
                        .addGroup(background7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(background7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TotalAmountField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(roundButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(background7Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TotalQtyField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Barcode", "Product Name", "Brand", "Quantity", "Price", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        tableScrollPane.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(110);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(300);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(180);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(80);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(110);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(110);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(background5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(background4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(tableScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 935, Short.MAX_VALUE)
                        .addComponent(backgound6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(background7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(backgound6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(tableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(background7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(background5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(background4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15))
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1241, Short.MAX_VALUE)
                .addGap(6, 6, 6))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 731, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void roundButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundButton3ActionPerformed
        new Select_Stock(parentFrame, true, this).setVisible(true);
    }//GEN-LAST:event_roundButton3ActionPerformed

    private void roundButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundButton4ActionPerformed

        String qty = qtyField.getText();
        String barcode = PIDfield.getText();

        if (barcode.isBlank()) {
            Notifications.getInstance().show(Notifications.Type.WARNING,
                    Notifications.Location.TOP_CENTER, "Please Select a Product");
        } else if (qty.isBlank()) {
            Notifications.getInstance().show(Notifications.Type.WARNING,
                    Notifications.Location.TOP_CENTER, "Please Enter Product Quantity");
        } else if (qty.matches("0")) {
            Notifications.getInstance().show(Notifications.Type.WARNING,
                    Notifications.Location.TOP_CENTER, "Invalid Quantity");
        } else if (!qty.matches("[0-9]+")) {
            Notifications.getInstance().show(Notifications.Type.WARNING,
                    Notifications.Location.TOP_CENTER, "Invalid Quantity");
        } else if (maxQty < Integer.parseInt(qty)) {
            Notifications.getInstance().show(Notifications.Type.WARNING,
                    Notifications.Location.TOP_CENTER, "Invalid Quantity. Quantity larger than current stock");
        } else {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            String brand = brandField.getText();
            String price = PriceField.getText();
            String product_name = PNameField.getText();

            try {

                ResultSet stock_rs = Mysql.search("SELECT * FROM `stock` "
                        + " WHERE `barcode`='" + barcode + "' AND `status_id`='1'  ");

                if (stock_rs.next()) {

                    int count = jTable1.getRowCount();

                    if (count < 1) {

                        Vector<String> v = new Vector<>();
                        v.add(barcode);
                        v.add(product_name);
                        v.add(brand);
                        v.add(qty);
                        v.add(price);

                        double total = Integer.parseInt(qty) * Double.parseDouble(price);
                        v.add(String.valueOf(total));

                        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                        model.addRow(v);
                        jTable1.setModel(model);
                        qtyField.setText("");
                    } else {

                        boolean barcodePresent = false;

                        for (int y = 0; y < count; y++) {
                            String tableBarcode = String.valueOf(jTable1.getValueAt(y, 0));

                            if (barcode.equals(tableBarcode)) {
                                String tableQty = String.valueOf(jTable1.getValueAt(y, 3));

                                int newQty = Integer.parseInt(qty) + Integer.parseInt(tableQty);
                                double newTotal = newQty * Double.parseDouble(price);

                                jTable1.setValueAt(String.valueOf(newQty), y, 3);
                                jTable1.setValueAt(newTotal, y, 5);
                                qtyField.setText("");

                                barcodePresent = true;
                                break;
                            }
                        }

                        if (!barcodePresent) {
                            Vector<String> v = new Vector<>();
                            v.add(barcode);
                            v.add(product_name);
                            v.add(brand);
                            v.add(qty);
                            v.add(price);

                            double total = Integer.parseInt(qty) * Double.parseDouble(price);
                            v.add(String.valueOf(total));

                            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                            model.addRow(v);
                            jTable1.setModel(model);
                            qtyField.setText("");
                        }
                    }
                } else {
                    Notifications.getInstance().show(Notifications.Type.WARNING,
                            Notifications.Location.TOP_CENTER, "Invalid Barcode. Barcode doesn't match database");
                }
            } catch (SQLException e) {
                Logger.CashierLog(String.valueOf(e));
                e.printStackTrace();
            }
            ;
        }
    }//GEN-LAST:event_roundButton4ActionPerformed

    private void roundButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundButton5ActionPerformed
        try {
            int rowcount = jTable1.getRowCount();
            String method = String.valueOf(jComboBox1.getSelectedItem());
            String totalAmount = TotalAmountField.getText();

            if (rowcount < 1) {
                Notifications.getInstance().show(Notifications.Type.WARNING,
                        Notifications.Location.TOP_CENTER, "Please add data to the table");

            } else if (method.equals("Select Payment Method")) {
                Notifications.getInstance().show(Notifications.Type.WARNING,
                        Notifications.Location.TOP_CENTER, "Please select a payment method");
            } else {
                String method_id = paymentmap.get(method);
                String invoiceNo = "";
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                ResultSet invoice_rs = Mysql.search("SELECT * FROM `invoice` ORDER BY  `id` DESC LIMIT 1 ");

                if (invoice_rs.next()) {

                    String previousInvoice = invoice_rs.getString("id");

                    String[] split = previousInvoice.split("_");
                    int oldNo = Integer.parseInt(split[1]);
                    String newNo = String.format("%05d", oldNo + 1);

                    invoiceNo = "IN_" + newNo;
                } else {
                    invoiceNo = "IN_00001";
                }

                //invoive insert
                Mysql.iud("INSERT INTO `invoice` (`id`,`issue_date`,`total`,`payment_method_id`,`user_id`)"
                        + "VALUES ('" + invoiceNo + "','" + sdf.format(new Date()) + "','" + totalAmount + "','" + method_id + "','" + user_id + "')");

                for (int x = 0; x < rowcount; x++) {
                    String barcode = String.valueOf(jTable1.getValueAt(x, 0));
                    String qty = String.valueOf(jTable1.getValueAt(x, 3));

                    // invoice item insert
                    Mysql.iud("INSERT INTO `invoice_item` (`barcode`,`qty`,`invoice_id`)"
                            + "VALUES  ('" + barcode + "','" + qty + "','" + invoiceNo + "')");

                    if (Integer.parseInt(qty) == maxQty) {

                        
                    Mysql.iud("UPDATE `stock` SET `qty`=`qty`-'" + qty + "' ,`status_id`='2' WHERE `barcode`='" + barcode + "'");
                    } else {

                        Mysql.iud("UPDATE `stock` SET `qty`=`qty`-'" + qty + "' WHERE `barcode`='" + barcode + "'");
                    }

                }

                JRTableModelDataSource datasource = new JRTableModelDataSource(jTable1.getModel());

                InputStream logo = getClass().getResourceAsStream("/lk/styrox/pharma_care/report_img/logo.png");
                InputStream bg = getClass().getResourceAsStream("/lk/styrox/pharma_care/report_img/Report_bg.png");

                HashMap<String, Object> parameters = new HashMap<>();
                parameters.put("invoice_id", invoiceNo);
                parameters.put("cashier", user_name);
                parameters.put("method", method);
                parameters.put("amount", totalAmount);
                parameters.put("date", sdf.format(new Date()));
                parameters.put("bg", bg);
                parameters.put("logo", logo);

                InputStream reportInput = getClass().getResourceAsStream("/lk/styrox/pharma_care/reports/invoice.jasper");

                JasperPrint report = JasperFillManager.fillReport(reportInput, parameters, datasource);
                JasperViewer.viewReport(report, false);
                
                CashierDashboard dashboard= new CashierDashboard();
                dashboard.setVisible(true);
                dashboard.loadPanel(new Invoice(parentFrame));
                parentFrame.dispose();
            }

        } catch (SQLException | JRException e) {
            Logger.CashierLog(String.valueOf(e));
            e.printStackTrace();
        }
    }//GEN-LAST:event_roundButton5ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (evt.getClickCount() == 2) {

            int selectedRow = jTable1.getSelectedRow();

            int option = JOptionPane.showConfirmDialog(parentFrame, "Are you sure you want to remove this item from table?",
                    "Remove Item ", JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.YES_OPTION) {
                DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                model.removeRow(selectedRow);
                jTable1.setModel(model);
            }
        }
    }//GEN-LAST:event_jTable1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private lk.styrox.pharma_care.main_components.roundTextField PIDfield;
    private lk.styrox.pharma_care.main_components.roundTextField PNameField;
    private lk.styrox.pharma_care.main_components.roundTextField PriceField;
    private lk.styrox.pharma_care.main_components.roundTextField TotalAmountField;
    private lk.styrox.pharma_care.main_components.roundTextField TotalQtyField;
    private lk.styrox.pharma_care.main_components.Background backgound6;
    private lk.styrox.pharma_care.main_components.Background background4;
    private lk.styrox.pharma_care.main_components.Background background5;
    private lk.styrox.pharma_care.main_components.Background background7;
    private lk.styrox.pharma_care.main_components.roundTextField brandField;
    private lk.styrox.pharma_care.main_components.roundTextField categoryField;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private lk.styrox.pharma_care.main_components.roundTextField qtyField;
    private lk.styrox.pharma_care.main_components.roundButton roundButton3;
    private lk.styrox.pharma_care.main_components.roundButton roundButton4;
    private lk.styrox.pharma_care.main_components.roundButton roundButton5;
    private lk.styrox.pharma_care.main_components.roundTextField roundTextField10;
    private lk.styrox.pharma_care.main_components.roundTextField roundTextField9;
    private javax.swing.JScrollPane tableScrollPane;
    // End of variables declaration//GEN-END:variables
}
