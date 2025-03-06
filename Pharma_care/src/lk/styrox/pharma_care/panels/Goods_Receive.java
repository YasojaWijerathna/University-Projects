/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package lk.styrox.pharma_care.panels;

import java.awt.Font;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import lk.styrox.pharma_care.connection.Mysql;
import lk.styrox.pharma_care.dialogs.Select_Product;
import lk.styrox.pharma_care.dialogs.Select_Supplier;
import lk.styrox.pharma_care.gui.AdminDashboard;
import lk.styrox.pharma_care.gui.CashierDashboard;
import lk.styrox.pharma_care.gui.Login;
import static lk.styrox.pharma_care.gui.Login.USER_TYPE;
import lk.styrox.pharma_care.logger.Logger;
import lk.styrox.pharma_care.main_components.roundTextField;
import lk.styrox.pharma_care.table_model.TableCustom;
import lk.styrox.pharma_care.validation.Valid_User;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;
import raven.toast.Notifications;

/**
 *
 * @author dnana
 */
public class Goods_Receive extends javax.swing.JPanel {

    JFrame parentFrame;
    String user_id = "";
    HashMap<String, Integer> brandMap = new HashMap<>();
    HashMap<String, String> paymentmap = new HashMap<>();

    /**
     * Creates new form Goods_Receive
     */
    public Goods_Receive(JFrame parent) {
        initComponents();
        parentFrame = parent;
        Notifications.getInstance().setJFrame(parentFrame);
        loadTable();
        init();
        loadPaymentType();
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
                    int totalAmount = 0;
                    for (int x = 0; x < rowcount; x++) {
                        String qtyStr = String.valueOf(jTable1.getValueAt(x, 3));
                        String amountStr = String.valueOf(jTable1.getValueAt(x, 4));
                        int qty = Integer.parseInt(qtyStr);
                        int amount = Integer.parseInt(amountStr);
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
                roundTextField10.setText(user.getString("fname") + " " + user.getString("lname"));
                roundTextField9.setText(user.getString("id"));
            }
            ResultSet brand = Mysql.search("SELECT * FROM `brand`");
            while (brand.next()) {
                brandMap.put(brand.getString("name"), brand.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            if (USER_TYPE.equals(Valid_User.ADMIN.name())) {
                Logger.AdminlLog(String.valueOf(e));
            } else if (USER_TYPE.equals(Valid_User.CASHIER.name())) {
                Logger.CashierLog(String.valueOf(e));
            }
        }

    }

    public roundTextField getPIDfield() {
        return PIDfield;
    }

    public roundTextField getPNameField() {
        return PNameField;
    }

    public roundTextField getSPIDfield() {
        return SPIDfield;
    }

    public roundTextField getBrandField() {
        return brandField;
    }

    public roundTextField getCategoryField() {
        return categoryField;
    }

    public roundTextField getCompanyField() {
        return companyField;
    }

    public roundTextField getSupplierField() {
        return supplierField;
    }

    private void loadPaymentType() {
        try {
            ResultSet payment = Mysql.search("SELECT * FROM `payment_method` WHERE `id`!='3'");

            Vector v = new Vector();
            v.add("Select Payment Method");
            while (payment.next()) {
                v.add(payment.getString("method"));
                paymentmap.put(payment.getString("method"), payment.getString("id"));
            }
            DefaultComboBoxModel m = new DefaultComboBoxModel(v);
            jComboBox1.setModel(m);

        } catch (SQLException e) {
            if (USER_TYPE.equals(Valid_User.ADMIN.name())) {
                Logger.AdminlLog(String.valueOf(e));
            } else if (USER_TYPE.equals(Valid_User.CASHIER.name())) {
                Logger.CashierLog(String.valueOf(e));
            }
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
        background3 = new lk.styrox.pharma_care.main_components.Background();
        jLabel1 = new javax.swing.JLabel();
        SPIDfield = new lk.styrox.pharma_care.main_components.roundTextField();
        jLabel3 = new javax.swing.JLabel();
        supplierField = new lk.styrox.pharma_care.main_components.roundTextField();
        roundButton2 = new lk.styrox.pharma_care.main_components.roundButton();
        companyField = new lk.styrox.pharma_care.main_components.roundTextField();
        jLabel4 = new javax.swing.JLabel();
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
        background5 = new lk.styrox.pharma_care.main_components.Background();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        roundTextField9 = new lk.styrox.pharma_care.main_components.roundTextField();
        roundTextField10 = new lk.styrox.pharma_care.main_components.roundTextField();
        jLabel10 = new javax.swing.JLabel();
        backgound6 = new lk.styrox.pharma_care.main_components.Background();
        jLabel12 = new javax.swing.JLabel();
        sellingPriceField = new lk.styrox.pharma_care.main_components.roundTextField();
        buyingPriceField = new lk.styrox.pharma_care.main_components.roundTextField();
        jLabel13 = new javax.swing.JLabel();
        mfdField = new lk.styrox.pharma_care.main_components.roundDateChooser();
        jLabel14 = new javax.swing.JLabel();
        expField = new lk.styrox.pharma_care.main_components.roundDateChooser();
        jLabel15 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel16 = new javax.swing.JLabel();
        qtyField = new lk.styrox.pharma_care.main_components.roundTextField();
        roundButton4 = new lk.styrox.pharma_care.main_components.roundButton();
        tableScrollPane = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        background7 = new lk.styrox.pharma_care.main_components.Background();
        jLabel17 = new javax.swing.JLabel();
        TotalAmountField = new lk.styrox.pharma_care.main_components.roundTextField();
        roundButton5 = new lk.styrox.pharma_care.main_components.roundButton();
        TotalQtyField = new lk.styrox.pharma_care.main_components.roundTextField();
        jLabel18 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setOpaque(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1220, 1040));

        background3.setBackground(new java.awt.Color(231, 231, 233));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("ID");

        SPIDfield.setEditable(false);
        SPIDfield.setBackground(new java.awt.Color(255, 255, 255));
        SPIDfield.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Supplier Name");

        supplierField.setEditable(false);
        supplierField.setBackground(new java.awt.Color(255, 255, 255));
        supplierField.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        roundButton2.setBackground(new java.awt.Color(223, 251, 255));
        roundButton2.setForeground(new java.awt.Color(0, 224, 255));
        roundButton2.setText("Select Supplier");
        roundButton2.setFont(new java.awt.Font("Microsoft JhengHei", 1, 16)); // NOI18N
        roundButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundButton2ActionPerformed(evt);
            }
        });

        companyField.setEditable(false);
        companyField.setBackground(new java.awt.Color(255, 255, 255));
        companyField.setAutoscrolls(false);
        companyField.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        companyField.setMaximumSize(new java.awt.Dimension(210, 20000));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Company Name");

        javax.swing.GroupLayout background3Layout = new javax.swing.GroupLayout(background3);
        background3.setLayout(background3Layout);
        background3Layout.setHorizontalGroup(
            background3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(background3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1)
                    .addComponent(supplierField, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(SPIDfield, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(companyField, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );
        background3Layout.setVerticalGroup(
            background3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(roundButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SPIDfield, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(supplierField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(companyField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        background4.setBackground(new java.awt.Color(231, 231, 233));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("ID");

        PIDfield.setEditable(false);
        PIDfield.setBackground(new java.awt.Color(255, 255, 255));
        PIDfield.setFocusable(false);
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
        roundButton3.setText(" Select Product");
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

        javax.swing.GroupLayout background4Layout = new javax.swing.GroupLayout(background4);
        background4.setLayout(background4Layout);
        background4Layout.setHorizontalGroup(
            background4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(background4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel2)
                    .addComponent(roundButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PIDfield, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(categoryField, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(brandField, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );
        background4Layout.setVerticalGroup(
            background4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(roundButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
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
                .addGap(19, 19, 19))
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

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Selling Price");

        sellingPriceField.setBackground(new java.awt.Color(255, 255, 255));
        sellingPriceField.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        buyingPriceField.setBackground(new java.awt.Color(255, 255, 255));
        buyingPriceField.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Buying Price");

        mfdField.setDateFormatString("yyyy-MM-dd");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Manufacture Date");

        expField.setDateFormatString("yyyy-MM-dd");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Expiration Date");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

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
                .addGap(15, 15, 15)
                .addGroup(backgound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backgound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(buyingPriceField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(mfdField, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE))
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addGap(29, 29, 29)
                .addGroup(backgound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel12)
                    .addComponent(sellingPriceField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel15)
                    .addComponent(expField, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE))
                .addGroup(backgound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backgound6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(backgound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(qtyField, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(284, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgound6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 269, Short.MAX_VALUE)
                        .addComponent(roundButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))))
        );
        backgound6Layout.setVerticalGroup(
            backgound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgound6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(backgound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgound6Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mfdField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))
                    .addGroup(backgound6Layout.createSequentialGroup()
                        .addGroup(backgound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(backgound6Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(expField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(backgound6Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(qtyField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(backgound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgound6Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(backgound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sellingPriceField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(roundButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgound6Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buyingPriceField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Barcode", "Product Name", "Brand", "Quantity", "Buying Price", "Selling Price", "MFD", "EXP"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
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
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(220);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(160);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(75);
            jTable1.getColumnModel().getColumn(3).setMaxWidth(75);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(90);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(90);
        }

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 159, Short.MAX_VALUE)
                        .addComponent(roundButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))))
        );
        background7Layout.setVerticalGroup(
            background7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background7Layout.createSequentialGroup()
                .addGap(20, 20, 20)
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(background5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(background4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(background3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(backgound6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(background7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(tableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 959, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(background5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backgound6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(tableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(background7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(background4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(background3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30))
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void roundButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundButton2ActionPerformed
        new Select_Supplier(parentFrame, true, this).setVisible(true);

    }//GEN-LAST:event_roundButton2ActionPerformed

    private void roundButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundButton3ActionPerformed
        new Select_Product(parentFrame, true, this).setVisible(true);
    }//GEN-LAST:event_roundButton3ActionPerformed

    private void roundButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundButton4ActionPerformed
        Date mfd = mfdField.getDate();
        Date exp = expField.getDate();
        String qty = qtyField.getText();
        String buyingPrice = buyingPriceField.getText();
        String sellingPrice = sellingPriceField.getText();
        String product_id = PIDfield.getText();
        String supplier_id = SPIDfield.getText();

        if (product_id.isBlank()) {
            Notifications.getInstance().show(Notifications.Type.WARNING,
                    Notifications.Location.TOP_CENTER, "Please Select Product");
        } else if (supplier_id.isBlank()) {
            Notifications.getInstance().show(Notifications.Type.WARNING,
                    Notifications.Location.TOP_CENTER, "Please Select Supplier");
        } else if (mfd == null) {
            Notifications.getInstance().show(Notifications.Type.WARNING,
                    Notifications.Location.TOP_CENTER, "Please Select Manufacture Date");
        } else if (mfd.after(new Date())) {
            Notifications.getInstance().show(Notifications.Type.WARNING,
                    Notifications.Location.TOP_CENTER, "Invalid Manufacture Date");
        } else if (exp == null) {
            Notifications.getInstance().show(Notifications.Type.WARNING,
                    Notifications.Location.TOP_CENTER, "Please Select Expiration Date");
        } else if (exp.before(new Date())) {
            Notifications.getInstance().show(Notifications.Type.WARNING,
                    Notifications.Location.TOP_CENTER, "Invalid Expiration Date");
        } else if (qty.isBlank()) {
            Notifications.getInstance().show(Notifications.Type.WARNING,
                    Notifications.Location.TOP_CENTER, "Please Enter Product Quantity");
        } else if (qty.matches("0")) {
            Notifications.getInstance().show(Notifications.Type.WARNING,
                    Notifications.Location.TOP_CENTER, "Invalid Quantity");
        } else if (!qty.matches("[0-9]+")) {
            Notifications.getInstance().show(Notifications.Type.WARNING,
                    Notifications.Location.TOP_CENTER, "Invalid Quantity");
        } else if (buyingPrice.isBlank()) {
            Notifications.getInstance().show(Notifications.Type.WARNING,
                    Notifications.Location.TOP_CENTER, "Please Enter Product Buying Price");
        } else if (buyingPrice.matches("0")) {
            Notifications.getInstance().show(Notifications.Type.WARNING,
                    Notifications.Location.TOP_CENTER, "Invalid Buying Price");
        } else if (!buyingPrice.matches("[0-9]+")) {
            Notifications.getInstance().show(Notifications.Type.WARNING,
                    Notifications.Location.TOP_CENTER, "Invalid Buying Price");
        } else if (sellingPrice.isBlank()) {
            Notifications.getInstance().show(Notifications.Type.WARNING,
                    Notifications.Location.TOP_CENTER, "Please Enter Product Selling Price");
        } else if (sellingPrice.matches("0")) {
            Notifications.getInstance().show(Notifications.Type.WARNING,
                    Notifications.Location.TOP_CENTER, "Invalid Selling Price");
        } else if (!sellingPrice.matches("[0-9]+")) {
            Notifications.getInstance().show(Notifications.Type.WARNING,
                    Notifications.Location.TOP_CENTER, "Invalid Selling Price");
        } else {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            String brand = brandField.getText();
            String product_name = PNameField.getText();
            String stock_no = "";
            try {

                ResultSet stock_rs = Mysql.search("SELECT * FROM `stock` "
                        + " WHERE `product_id`='" + product_id + "' AND `mfd`='" + sdf.format(mfd) + "'"
                        + " AND `exp`='" + sdf.format(exp) + "' AND `buying_price`='" + buyingPrice + "' "
                        + "AND `selling_price`='" + sellingPrice + "' AND `status_id`='1'  ");

                if (stock_rs.next()) {
                    stock_no = stock_rs.getString("barcode");
                } else {

                    int brand_id = brandMap.get(brand);

                    ResultSet product_rs = Mysql.search("SELECT * FROM `stock` "
                            + "INNER JOIN `product` ON `stock`.`product_id`=`product`.`id`"
                            + "WHERE `product_id`='" + product_id + "' AND `brand_id`='" + brand_id + "'"
                            + "ORDER BY `barcode` DESC LIMIT 1 ");

                    if (product_rs.next()) {

                        String barcode = product_rs.getString("barcode");

                        String[] split = barcode.split("-");
                        int oldNo = Integer.parseInt(split[2]);
                        String newNo = String.format("%03d", oldNo + 1);
                        stock_no = split[0] + "-" + split[1] + "-" + newNo;

                    } else {

                        String brandNo = String.format("%03d", brand_id);
                        stock_no = product_id + "-" + brandNo + "-001";
                    }
                }
            } catch (NumberFormatException | SQLException e) {
                if (USER_TYPE.equals(Valid_User.ADMIN.name())) {
                    Logger.AdminlLog(String.valueOf(e));
                } else if (USER_TYPE.equals(Valid_User.CASHIER.name())) {
                    Logger.CashierLog(String.valueOf(e));
                }
                e.printStackTrace();
            }

            int count = jTable1.getRowCount();

            if (count < 1) {

                Vector<String> v = new Vector<>();
                v.add(stock_no);
                v.add(product_name);
                v.add(brand);
                v.add(qty);
                v.add(buyingPrice);
                v.add(sellingPrice);
                v.add(sdf.format(mfd));
                v.add(sdf.format(exp));

                DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                model.addRow(v);
                jTable1.setModel(model);

                mfdField.setDate(null);
                expField.setDate(null);
                qtyField.setText("");
                buyingPriceField.setText("");
                sellingPriceField.setText("");

            } else {

                boolean barcodePresent = false;

                for (int y = 0; y < count; y++) {
                    String tableBarcode = String.valueOf(jTable1.getValueAt(y, 0));

                    if (stock_no.equals(tableBarcode)) {

                        String tableQty = String.valueOf(jTable1.getValueAt(y, 3));

                        int newQty = Integer.parseInt(qty) + Integer.parseInt(tableQty);
                        jTable1.setValueAt(newQty, y, 3);

                        Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                Notifications.Location.TOP_CENTER, "New Quantity Updated");

                        mfdField.setDate(null);
                        expField.setDate(null);
                        qtyField.setText("");
                        buyingPriceField.setText("");
                        sellingPriceField.setText("");

                        barcodePresent = true;
                        break;
                    }
                }

                if (!barcodePresent) {
                    Vector<String> v = new Vector<>();
                    v.add(stock_no);
                    v.add(product_name);
                    v.add(brand);
                    v.add(qty);
                    v.add(buyingPrice);
                    v.add(sellingPrice);
                    v.add(sdf.format(mfd));
                    v.add(sdf.format(exp));

                    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                    model.addRow(v);
                    jTable1.setModel(model);

                    mfdField.setDate(null);
                    expField.setDate(null);
                    qtyField.setText("");
                    buyingPriceField.setText("");
                    sellingPriceField.setText("");
                }
            }
     sellingPriceField.setText("");
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
                String supplier_id = SPIDfield.getText();
                String supplier_name = supplierField.getText();
                String company = companyField.getText();

                String GRN_id = "";
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                ResultSet grn_rs = Mysql.search("SELECT * FROM `goods_receive` ORDER BY  `id` DESC LIMIT 1 ");

                if (grn_rs.next()) {

                    String previousInvoice = grn_rs.getString("id");

                    String[] split = previousInvoice.split("_");
                    int oldNo = Integer.parseInt(split[1]);
                    String newNo = String.format("%04d", oldNo + 1);

                    GRN_id = "GRN_" + newNo;
                } else {
                    GRN_id = "GRN_00001";
                }

                //GRN insert
                Mysql.iud("INSERT INTO `goods_receive` (`id`,`date`,`total`,`payment_method_id`,`user_id`,`supplier_id`)"
                        + "VALUES ('" + GRN_id + "','" + sdf.format(new Date()) + "','" + totalAmount + "'"
                        + ",'" + method_id + "','" + user_id + "','" + supplier_id + "')");

                for (int x = 0; x < rowcount; x++) {
                    String barcode = String.valueOf(jTable1.getValueAt(x, 0));
                    String qty = String.valueOf(jTable1.getValueAt(x, 3));
                    String buying_price = String.valueOf(jTable1.getValueAt(x, 4));
                    String selling_price = String.valueOf(jTable1.getValueAt(x, 5));
                    String mfd = String.valueOf(jTable1.getValueAt(x, 6));
                    String exp = String.valueOf(jTable1.getValueAt(x, 7));

                    String[] split = barcode.split("-");
                    String product_id = split[0];

                    // Stock Insert
                    Mysql.iud("INSERT INTO `stock` (`barcode`,`qty`,`buying_price`,`selling_price`"
                            + ",`mfd`,`exp`,`status_id`,`product_id`)"
                            + "VALUES ('" + barcode + "','" + qty + "','" + buying_price + "','" + selling_price + "',"
                            + "'" + mfd + "','" + exp + "','1','" + product_id + "')");

                    // GRN item insert
                    Mysql.iud("INSERT INTO `goods_receive_item` (`barcode`,`qty`,`grn_id`)"
                            + "VALUES  ('" + barcode + "','" + qty + "','" + GRN_id + "')");
                }

                JRTableModelDataSource datasource = new JRTableModelDataSource(jTable1.getModel());

                InputStream logo = getClass().getResourceAsStream("/lk/styrox/pharma_care/report_img/logo.png");
                InputStream bg = getClass().getResourceAsStream("/lk/styrox/pharma_care/report_img/Report_bg.png");

                HashMap<String, Object> parameters = new HashMap<>();
                parameters.put("grn_id", GRN_id);
                parameters.put("sup_name", supplier_name);
                parameters.put("com_name", company);
                parameters.put("method", method);
                parameters.put("amount", totalAmount);
                parameters.put("date", sdf.format(new Date()));
                parameters.put("bg", bg);
                parameters.put("logo", logo);

                InputStream reportInput = getClass().getResourceAsStream("/lk/styrox/pharma_care/reports/GRN.jasper");

                JasperPrint report = JasperFillManager.fillReport(reportInput, parameters, datasource);
                JasperViewer.viewReport(report, false);

                if (USER_TYPE.equals(Valid_User.ADMIN.name())) {

                    AdminDashboard dashboard = new AdminDashboard();
                    dashboard.setVisible(true);
                    dashboard.loadPanel(new Goods_Receive(parentFrame));
                    parentFrame.dispose();
                } else if (USER_TYPE.equals(Valid_User.CASHIER.name())) {
                    CashierDashboard dashboard = new CashierDashboard();
                    dashboard.setVisible(true);
                    dashboard.loadPanel(new Goods_Receive(parentFrame));
                    parentFrame.dispose();
                }

            }

        } catch (SQLException | JRException e) {

            if (USER_TYPE.equals(Valid_User.ADMIN.name())) {
                Logger.AdminlLog(String.valueOf(e));
            } else if (USER_TYPE.equals(Valid_User.CASHIER.name())) {
                Logger.CashierLog(String.valueOf(e));
            }
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
    private lk.styrox.pharma_care.main_components.roundTextField SPIDfield;
    private lk.styrox.pharma_care.main_components.roundTextField TotalAmountField;
    private lk.styrox.pharma_care.main_components.roundTextField TotalQtyField;
    private lk.styrox.pharma_care.main_components.Background backgound6;
    private lk.styrox.pharma_care.main_components.Background background3;
    private lk.styrox.pharma_care.main_components.Background background4;
    private lk.styrox.pharma_care.main_components.Background background5;
    private lk.styrox.pharma_care.main_components.Background background7;
    private lk.styrox.pharma_care.main_components.roundTextField brandField;
    private lk.styrox.pharma_care.main_components.roundTextField buyingPriceField;
    private lk.styrox.pharma_care.main_components.roundTextField categoryField;
    private lk.styrox.pharma_care.main_components.roundTextField companyField;
    private lk.styrox.pharma_care.main_components.roundDateChooser expField;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private lk.styrox.pharma_care.main_components.roundDateChooser mfdField;
    private lk.styrox.pharma_care.main_components.roundTextField qtyField;
    private lk.styrox.pharma_care.main_components.roundButton roundButton2;
    private lk.styrox.pharma_care.main_components.roundButton roundButton3;
    private lk.styrox.pharma_care.main_components.roundButton roundButton4;
    private lk.styrox.pharma_care.main_components.roundButton roundButton5;
    private lk.styrox.pharma_care.main_components.roundTextField roundTextField10;
    private lk.styrox.pharma_care.main_components.roundTextField roundTextField9;
    private lk.styrox.pharma_care.main_components.roundTextField sellingPriceField;
    private lk.styrox.pharma_care.main_components.roundTextField supplierField;
    private javax.swing.JScrollPane tableScrollPane;
    // End of variables declaration//GEN-END:variables
}
