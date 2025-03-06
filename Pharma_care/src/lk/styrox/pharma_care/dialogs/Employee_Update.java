/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package lk.styrox.pharma_care.dialogs;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import lk.styrox.pharma_care.connection.Mysql;
import lk.styrox.pharma_care.logger.Logger;
import lk.styrox.pharma_care.panels.Employee_Management;
import lk.styrox.pharma_care.validation.Validation;
import raven.toast.Notifications;

/**
 *
 * @author thara
 */
public class Employee_Update extends javax.swing.JDialog {

    /**
     * Creates new form Employee_Update
     */
    private HashMap<String, Integer> statusMap;
    private String[] mobileID;
    
    public Employee_Update(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        init();
    }

    private void init() {
        statusMap = new HashMap<>();
        mobileID = new String[2];
        loadStatus();

        roundedBtnProcesses1.repaintBtn(Color.BLACK, 2);
        background1.grabFocus();
        roundedBtn1.repaintBtn(Color.decode("#ACFDAA"), Color.decode("#ACFDAA"));

        loadSelectedUse();
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

    private void loadSelectedUse() {

        try {

            ResultSet rs = Mysql.search("SELECT `fname`,`lname`,`email`,`status_id`,`username`,`password` FROM `user` \n"
                    + "JOIN `login` ON `user`.`id` = `login`.`user_id` WHERE `id` = '" + Employee_Management.eID.getID() + "'");
            System.out.println(Employee_Management.eID.getID());

            ResultSet m_rs = Mysql.search("SELECT * FROM `mobile` WHERE `user_id` = '" + Employee_Management.eID.getID() + "'");

            if (rs.next()) {

                roundTextField1.setText(rs.getString("fname"));
                roundTextField2.setText(rs.getString("lname"));
                roundTextField3.setText(rs.getString("email"));
                roundTextField4.setText(rs.getString("username"));
                roundPasswordField1.setText(rs.getString("password"));

                jComboBox1.setSelectedIndex(Integer.parseInt(rs.getString("status_id")));

            } else {
                Notifications.getInstance().show(Notifications.Type.WARNING,
                        Notifications.Location.TOP_CENTER, "Employee Not Found");
            }

            if (m_rs.next()) {
                mobileID[0] = m_rs.getString("id");
                roundTextField5.setText(m_rs.getString("mobile"));
                if (m_rs.next()) {
                    mobileID[1] = m_rs.getString("id");
                    roundTextField6.setText(m_rs.getString("mobile"));
                }
            } else {
                Notifications.getInstance().show(Notifications.Type.WARNING,
                        Notifications.Location.TOP_CENTER, "Mobile Not Found");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private boolean usernameValidate(String value) {
        if (value.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING,
                    Notifications.Location.TOP_CENTER, "Username is not filled");
            return true;
        } else if (value.length() < 3) {
            Notifications.getInstance().show(Notifications.Type.WARNING,
                    Notifications.Location.TOP_CENTER, "Username cannot be shorter than 3 characters");
            return true;
        }
        return false;
    }

    private boolean passwordValidate(String password) {
        if (password.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING,
                    Notifications.Location.TOP_CENTER, "Password is not filled");

            return true;
        } else if (!password.matches(Validation.PASSWORD_VALIDATION.validate())) {
            Notifications.getInstance().show(Notifications.Type.WARNING,
                    Notifications.Location.TOP_CENTER, "Password is not Valid."
                    + "Your password should include atleast one special character,"
                    + "capital letter,simple letter,numver");
            return true;
        }
        return false;
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
        logo1 = new lk.styrox.pharma_care.sub_components.Logo();
        jLabel1 = new javax.swing.JLabel();
        roundedBtnProcesses1 = new lk.styrox.pharma_care.main_components.roundedBtnProcesses();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        roundTextField1 = new lk.styrox.pharma_care.main_components.roundTextField();
        roundTextField2 = new lk.styrox.pharma_care.main_components.roundTextField();
        jLabel3 = new javax.swing.JLabel();
        roundTextField3 = new lk.styrox.pharma_care.main_components.roundTextField();
        jLabel4 = new javax.swing.JLabel();
        roundTextField4 = new lk.styrox.pharma_care.main_components.roundTextField();
        jLabel5 = new javax.swing.JLabel();
        roundPasswordField1 = new lk.styrox.pharma_care.main_components.roundPasswordField();
        jLabel6 = new javax.swing.JLabel();
        roundTextField5 = new lk.styrox.pharma_care.main_components.roundTextField();
        jLabel7 = new javax.swing.JLabel();
        roundTextField6 = new lk.styrox.pharma_care.main_components.roundTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        roundedBtn1 = new lk.styrox.pharma_care.main_components.RoundedBtn();
        jComboBox1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        background1.setBackground(new java.awt.Color(255, 255, 255));

        logo1.setPreferredSize(new java.awt.Dimension(148, 134));

        javax.swing.GroupLayout logo1Layout = new javax.swing.GroupLayout(logo1);
        logo1.setLayout(logo1Layout);
        logo1Layout.setHorizontalGroup(
            logo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 61, Short.MAX_VALUE)
        );
        logo1Layout.setVerticalGroup(
            logo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 61, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel1.setText("Update Employee");

        roundedBtnProcesses1.setText("X");
        roundedBtnProcesses1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundedBtnProcesses1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel2.setText("First Name");

        roundTextField1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        roundTextField2.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel3.setText("Last Name");

        roundTextField3.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel4.setText("Email");

        roundTextField4.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel5.setText("Username");

        roundPasswordField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                roundPasswordField1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                roundPasswordField1FocusLost(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel6.setText("Password");

        roundTextField5.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel7.setText("Mobile");

        roundTextField6.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        roundTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundTextField6ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel8.setText("Mobile 2 (Optional)");

        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel9.setText("User Type");

        jLabel10.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel10.setText("Employee");

        jLabel11.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel11.setText("Status");

        roundedBtn1.setForeground(new java.awt.Color(3, 148, 0));
        roundedBtn1.setText("Update");
        roundedBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundedBtn1ActionPerformed(evt);
            }
        });

        jComboBox1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout background1Layout = new javax.swing.GroupLayout(background1);
        background1.setLayout(background1Layout);
        background1Layout.setHorizontalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(background1Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(background1Layout.createSequentialGroup()
                        .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(background1Layout.createSequentialGroup()
                                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(roundTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 23, Short.MAX_VALUE)
                                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(roundTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 5, Short.MAX_VALUE))
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background1Layout.createSequentialGroup()
                                .addComponent(logo1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(roundedBtnProcesses1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(roundTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 566, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background1Layout.createSequentialGroup()
                                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background1Layout.createSequentialGroup()
                                        .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(roundTextField5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background1Layout.createSequentialGroup()
                                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(135, 135, 135))
                                            .addComponent(roundTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(27, 27, 27))
                                    .addGroup(background1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(roundPasswordField1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(roundTextField6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox1, 0, 289, Short.MAX_VALUE))))
                        .addGap(29, 29, 29))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(roundedBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        background1Layout.setVerticalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background1Layout.createSequentialGroup()
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(background1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(logo1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(background1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(roundedBtnProcesses1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(background1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(background1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24)
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(background1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(roundTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(roundPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(background1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24)
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE))
                .addGap(24, 24, 24)
                .addComponent(roundedBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(background1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(background1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void roundedBtnProcesses1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundedBtnProcesses1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_roundedBtnProcesses1ActionPerformed

    private void roundPasswordField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_roundPasswordField1FocusGained
        roundPasswordField1.setEchoChar('\u2020');
    }//GEN-LAST:event_roundPasswordField1FocusGained

    private void roundPasswordField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_roundPasswordField1FocusLost
        roundPasswordField1.setEchoChar('\u0000');
    }//GEN-LAST:event_roundPasswordField1FocusLost

    private void roundTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_roundTextField6ActionPerformed

    private void roundedBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundedBtn1ActionPerformed

        String fname = roundTextField1.getText();
        String lname = roundTextField2.getText();
        String email = roundTextField3.getText();
        String username = roundTextField4.getText();
        String password = String.valueOf(roundPasswordField1.getPassword());
        String mobile = roundTextField5.getText();
        String mobile2 = roundTextField6.getText();
        String status = String.valueOf(jComboBox1.getSelectedItem());

        if (fname.isBlank()) {
            Notifications.getInstance().show(Notifications.Type.WARNING,
                    Notifications.Location.TOP_CENTER, "Please enter First Name");
        } else if (lname.isBlank()) {
            Notifications.getInstance().show(Notifications.Type.WARNING,
                    Notifications.Location.TOP_CENTER, "Please enter Last Name");
        } else if (email.isBlank()) {
            Notifications.getInstance().show(Notifications.Type.WARNING,
                    Notifications.Location.TOP_CENTER, "Please enter Email");
        } else if (!email.matches(Validation.EMAIL_VALIDATION.validate())) {
            Notifications.getInstance().show(Notifications.Type.WARNING,
                    Notifications.Location.TOP_CENTER, "Invalid Email");
        } else if (mobile.isBlank()) {
            Notifications.getInstance().show(Notifications.Type.WARNING,
                    Notifications.Location.TOP_CENTER, "Please enter Mobile");
        } else if (!mobile.matches(Validation.MOBILE_VALIDATION.validate())) {
            Notifications.getInstance().show(Notifications.Type.WARNING,
                    Notifications.Location.TOP_CENTER, "Invalid Mobile");
        } else if (usernameValidate(username)) {
            //            username validation
        } else if (passwordValidate(password)) {
            //            password validation
        } else if (status.equals("Select status")) {
            Notifications.getInstance().show(Notifications.Type.WARNING,
                    Notifications.Location.TOP_CENTER, "Select a Status");
        } else {

            Mysql.iud("UPDATE `user` SET `fname` = '" + fname + "', `lname`='" + lname + "', `email`='" + email + "', "
                    + "`status_id` = '" + statusMap.get(status) + "' WHERE `id`='" + Employee_Management.eID.getID() + "'");

            Mysql.iud("UPDATE `mobile` SET `mobile`= '" + mobile + "' WHERE `id`='" + mobileID[0] + "'");

            if (!mobile2.isBlank() && mobile2.matches(Validation.MOBILE_VALIDATION.validate())) {
                if (mobileID[1] != null && !mobileID[1].isEmpty()) {
                    Mysql.iud("UPDATE `mobile` SET `mobile`= '" + mobile2 + "' WHERE `id`='" + mobileID[1] + "'");
                } else {
                    Mysql.iud("INSERT INTO `mobile` (`mobile`,`user_id`) VALUES ('" + mobile2 + "','" + Employee_Management.eID.getID() + "') ");
                }
            } else if (!mobile2.isBlank() && !mobile2.matches(Validation.MOBILE_VALIDATION.validate())) {
                Notifications.getInstance().show(Notifications.Type.WARNING,
                        Notifications.Location.TOP_CENTER, "Invalid Mobile 02 ");
            }

            Mysql.iud("UPDATE `login` SET `username`='" + username + "', `password`='" + password + "' WHERE `user_id`='" + Employee_Management.eID.getID() + "'");

            Notifications.getInstance().show(Notifications.Type.SUCCESS,
                    Notifications.Location.TOP_CENTER, "Employee Updated Successfully");

            this.dispose();

        }
    }//GEN-LAST:event_roundedBtn1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Employee_Update.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Employee_Update.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Employee_Update.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Employee_Update.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Employee_Update dialog = new Employee_Update(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private lk.styrox.pharma_care.main_components.Background background1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private lk.styrox.pharma_care.sub_components.Logo logo1;
    private lk.styrox.pharma_care.main_components.roundPasswordField roundPasswordField1;
    private lk.styrox.pharma_care.main_components.roundTextField roundTextField1;
    private lk.styrox.pharma_care.main_components.roundTextField roundTextField2;
    private lk.styrox.pharma_care.main_components.roundTextField roundTextField3;
    private lk.styrox.pharma_care.main_components.roundTextField roundTextField4;
    private lk.styrox.pharma_care.main_components.roundTextField roundTextField5;
    private lk.styrox.pharma_care.main_components.roundTextField roundTextField6;
    private lk.styrox.pharma_care.main_components.RoundedBtn roundedBtn1;
    private lk.styrox.pharma_care.main_components.roundedBtnProcesses roundedBtnProcesses1;
    // End of variables declaration//GEN-END:variables
}
