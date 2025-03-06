/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package lk.styrox.pharma_care.panels;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import lk.styrox.pharma_care.connection.Mysql;
import lk.styrox.pharma_care.data.EmployeeID;
import lk.styrox.pharma_care.dialogs.Employee_Registration;
import lk.styrox.pharma_care.dialogs.Employee_Update;
import lk.styrox.pharma_care.gui.AdminDashboard_1;
import lk.styrox.pharma_care.table_model.HoverIndex;
import lk.styrox.pharma_care.table_model.TableCustom;
import lk.styrox.pharma_care.table_model.TableCustomCellRender;
import lk.styrox.pharma_care.table_model.TextAreaCellRenderer;
import raven.toast.Notifications;

/**
 *
 * @author thara
 */
public class Employee_Management extends javax.swing.JPanel {

    /**
     * Creates new form Employee_Management
     */
    private HashMap<String, Integer> statusMap;
    public static EmployeeID eID;

    private AdminDashboard_1 dashboard;

    public Employee_Management(JFrame parent) {
        initComponents();
        Notifications.getInstance().setJFrame(parent);
        dashboard = (AdminDashboard_1) parent;
        init();
    }

    private void init() {

        statusMap = new HashMap<>();

        roundedBtn1.repaintBtn(Color.decode("#ACFDAA"), Color.decode("#ACFDAA"));
        roundedBtn2.repaintBtn(Color.decode("#D2D2D2"), Color.decode("#D2D2D2"));

        roundedBtnProcesses3.repaintBtn(Color.decode("#34C64B"), 2);
        roundedBtnProcesses2.repaintBtn(Color.decode("#000000"), 2);
        roundedBtnProcesses1.repaintBtn(Color.decode("#CB2525"), 2);
        loadEmployee();
        loadStatus();
        TableCustom.apply(jScrollPane1, TableCustom.TableType.DEFAULT);
    }

    private void loadEmployee() {

        try {
            ResultSet rs = Mysql.search("SELECT * FROM `user` JOIN `status` ON `status`.`id` = `user`.`status_id` WHERE `user`.`user_type_id` = '2'");

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

    private void loadStatus() {
        try {

            ResultSet rs = Mysql.search("SELECT * FROM `status` ORDER BY `id` ASC");

            Vector<String> data = new Vector<>();
            data.add("Select status");
            while (rs.next()) {

                data.add(rs.getString("name"));
                statusMap.put(rs.getString("name"), rs.getInt("id"));

            }
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(data);

            jComboBox1.setModel(model);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void reset() {

        roundTextField1.setText("");
        roundTextField2.setText("");
        roundTextField4.setText("");
        jComboBox1.setSelectedIndex(0);

    }

    private void searchEmployees() {

        String id =roundTextField1.getText();
        String user_name = roundTextField2.getText();
        String email = roundTextField4.getText();

        String status = String.valueOf(jComboBox1.getSelectedIndex());

        String query = "SELECT `user`.`id` AS `user_id`,`fname`,`lname`,`email`,`join_date`,`name` "
                + "FROM `user` JOIN `status` ON `user`.`status_id` = `status`.`id` "
                + "WHERE `user`.`user_type_id` = '2' "
                + "AND `user`.`id` LIKE '%" + id + "%' ";

        if(!email.isBlank()){
            query=query+"AND `email` LIKE '%" + email + "%'";
        }
        if (!user_name.isBlank()&& user_name.contains(" ")) {
            String[] name = user_name.split(" ");
            query += " AND `fname` LIKE '%" + name[0] + "%' AND `lname` LIKE '%" + name[1] + "%'";
        } else if (!user_name.isBlank()&& !user_name.equals("")) {
            query += " AND `fname` LIKE '%" + user_name + "%'";
        }
        if (!status.equals("0")) {
            query += " AND `status_id` = '" + status + "'";
        }

        try {
            ResultSet rs = Mysql.search(query);

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            while (rs.next()) {
                Vector<String> data = new Vector<>();
                data.add(rs.getString("user_id"));
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
        roundTextField4 = new lk.styrox.pharma_care.main_components.roundTextField();
        jLabel5 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 204, 204));
        setOpaque(false);

        background1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Leelawadee", 1, 18)); // NOI18N
        jLabel2.setText("Processes");

        roundedBtnProcesses1.setText("Change Status");
        roundedBtnProcesses1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundedBtnProcesses1ActionPerformed(evt);
            }
        });

        roundedBtnProcesses2.setText("Update Employees");
        roundedBtnProcesses2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundedBtnProcesses2ActionPerformed(evt);
            }
        });

        roundedBtnProcesses3.setText("Add Employees");
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
        jLabel1.setText("User ID");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel4.setText("User Name");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel6.setText("Status");

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

        roundTextField4.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel5.setText("Email");

        jComboBox1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout background3Layout = new javax.swing.GroupLayout(background3);
        background3.setLayout(background3Layout);
        background3Layout.setHorizontalGroup(
            background3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(background3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(background3Layout.createSequentialGroup()
                        .addComponent(roundTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(background3Layout.createSequentialGroup()
                        .addGroup(background3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(roundTextField2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(roundedBtn2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(roundedBtn1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(roundTextField4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, background3Layout.createSequentialGroup()
                                .addGroup(background3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 166, Short.MAX_VALUE)))
                        .addGap(31, 31, 31))))
        );
        background3Layout.setVerticalGroup(
            background3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(roundTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roundTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roundTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(roundedBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(roundedBtn2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jTable1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Email", "Joined Date", "Status"
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

        javax.swing.GroupLayout background1Layout = new javax.swing.GroupLayout(background1);
        background1.setLayout(background1Layout);
        background1Layout.setHorizontalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(background2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(background1Layout.createSequentialGroup()
                        .addComponent(background3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)))
                .addGap(22, 22, 22))
        );
        background1Layout.setVerticalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(background2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(background3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1))
                .addGap(15, 15, 15))
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

    private void roundedBtnProcesses3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundedBtnProcesses3ActionPerformed
        Employee_Registration employee = new Employee_Registration(dashboard, true);
        employee.setVisible(true);
        loadEmployee();
    }//GEN-LAST:event_roundedBtnProcesses3ActionPerformed

    private void roundedBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundedBtn1ActionPerformed
        searchEmployees();
    }//GEN-LAST:event_roundedBtn1ActionPerformed

    private void roundedBtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundedBtn2ActionPerformed
        reset();
        loadEmployee();
    }//GEN-LAST:event_roundedBtn2ActionPerformed

    private void roundedBtnProcesses2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundedBtnProcesses2ActionPerformed

        if (jTable1.getSelectedRow() > -1) {

            eID = new EmployeeID();
            eID.setID(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 0)));

            Employee_Update employee = new Employee_Update(dashboard, true);
            employee.setVisible(true);
            loadEmployee();

        } else {
            Notifications.getInstance().show(Notifications.Type.WARNING,
                    Notifications.Location.TOP_CENTER, "Select a Employee");
        }

    }//GEN-LAST:event_roundedBtnProcesses2ActionPerformed

    private void roundedBtnProcesses1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundedBtnProcesses1ActionPerformed

        if (jTable1.getSelectedRow() > -1) {

            String employeeId = (String) jTable1.getValueAt(jTable1.getSelectedRow(), 0);
            String status = (String) jTable1.getValueAt(jTable1.getSelectedRow(), 4);
            if (status.equals("ACTIVE")) {
                Mysql.iud("UPDATE `user` SET `status_id` = '2' WHERE `id`='" + employeeId + "'");
            } else if (status.equals("INACTIVE")) {
                Mysql.iud("UPDATE `user` SET `status_id` = '1' WHERE `id`='" + employeeId + "'");
            }
            loadEmployee();
            Notifications.getInstance().show(Notifications.Type.SUCCESS,
                    Notifications.Location.TOP_CENTER, "Employee status changed");
        } else {
            Notifications.getInstance().show(Notifications.Type.WARNING,
                    Notifications.Location.TOP_CENTER, "Select a Employee");
        }

    }//GEN-LAST:event_roundedBtnProcesses1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private lk.styrox.pharma_care.main_components.Background background1;
    private lk.styrox.pharma_care.main_components.Background background2;
    private lk.styrox.pharma_care.main_components.Background background3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private lk.styrox.pharma_care.main_components.roundTextField roundTextField1;
    private lk.styrox.pharma_care.main_components.roundTextField roundTextField2;
    private lk.styrox.pharma_care.main_components.roundTextField roundTextField4;
    private lk.styrox.pharma_care.main_components.RoundedBtn roundedBtn1;
    private lk.styrox.pharma_care.main_components.RoundedBtn roundedBtn2;
    private lk.styrox.pharma_care.main_components.roundedBtnProcesses roundedBtnProcesses1;
    private lk.styrox.pharma_care.main_components.roundedBtnProcesses roundedBtnProcesses2;
    private lk.styrox.pharma_care.main_components.roundedBtnProcesses roundedBtnProcesses3;
    // End of variables declaration//GEN-END:variables
}
