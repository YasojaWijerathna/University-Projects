/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package lk.starfield.yasoja.panels;

import java.awt.Color;
import java.awt.Font;
import java.sql.*;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import lk.starfield.yasoja.connection.Mysql;
import lk.starfield.yasoja.dialogs.Register_Student;
import lk.starfield.yasoja.dialogs.Student_Table;
import lk.starfield.yasoja.dialogs.Update_Students;
import lk.starfield.yasoja.main_components.roundTextField;
import lk.starfield.yasoja.validation.Valid_Status;
import raven.toast.Notifications;

/**
 *
 * @author dnana
 */
public class Student_Management extends javax.swing.JPanel {
    
    JFrame parentFrame;

    /**
     * Creates new form Student_Management
     */
    public Student_Management(JFrame parent) {
        initComponents();
        parentFrame = parent;
        loadTable();
        ID.grabFocus();
        Notifications.getInstance().setJFrame(parentFrame);
    }
    
    private void loadTable() {
        
        JTableHeader tableHeader = jTable1.getTableHeader();
        Font headerFont = new Font("Microsoft JhengHei ", Font.BOLD, 16);
        Color THcolorBg = new Color(6, 52, 119);
        Color THcolotFont = new Color(255, 255, 255);
        tableHeader.setBackground(THcolorBg);
        tableHeader.setForeground(THcolotFont);
        tableHeader.setFont(headerFont);
        jTable1.setTableHeader(tableHeader);
        
        DefaultTableCellRenderer columnRenderer = new DefaultTableCellRenderer();
        columnRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        for (int columnIndex = 0; columnIndex < model.getColumnCount(); columnIndex++) {
            jTable1.getColumnModel().getColumn(columnIndex).setCellRenderer(columnRenderer);
        }
    }
    
    public roundTextField getID() {
        return ID;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background1 = new lk.starfield.yasoja.main_components.Background();
        background2 = new lk.starfield.yasoja.main_components.Background();
        roundButton1 = new lk.starfield.yasoja.main_components.roundButton();
        jLabel2 = new javax.swing.JLabel();
        roundButton2 = new lk.starfield.yasoja.main_components.roundButton();
        roundButton3 = new lk.starfield.yasoja.main_components.roundButton();
        background3 = new lk.starfield.yasoja.main_components.Background();
        roundButton4 = new lk.starfield.yasoja.main_components.roundButton();
        jLabel4 = new javax.swing.JLabel();
        ID = new lk.starfield.yasoja.main_components.roundTextField();
        jLabel5 = new javax.swing.JLabel();
        Name = new lk.starfield.yasoja.main_components.roundTextField();
        jLabel7 = new javax.swing.JLabel();
        NIC = new lk.starfield.yasoja.main_components.roundTextField();
        jLabel8 = new javax.swing.JLabel();
        Batch = new lk.starfield.yasoja.main_components.roundTextField();
        jLabel6 = new javax.swing.JLabel();
        Email = new lk.starfield.yasoja.main_components.roundTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        background1.setBackground(new java.awt.Color(255, 255, 255));

        background2.setBackground(new java.awt.Color(231, 231, 233));

        roundButton1.setBackground(new java.awt.Color(232, 194, 255));
        roundButton1.setForeground(new java.awt.Color(104, 2, 166));
        roundButton1.setText("Update Student Details");
        roundButton1.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        roundButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundButton1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Leelawadee", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Processes");

        roundButton2.setBackground(new java.awt.Color(192, 218, 255));
        roundButton2.setForeground(new java.awt.Color(30, 0, 215));
        roundButton2.setText("Register New Student");
        roundButton2.setFocusable(false);
        roundButton2.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        roundButton2.setPreferredSize(new java.awt.Dimension(164, 30));
        roundButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundButton2ActionPerformed(evt);
            }
        });

        roundButton3.setBackground(new java.awt.Color(187, 255, 254));
        roundButton3.setForeground(new java.awt.Color(24, 190, 188));
        roundButton3.setText("View Student Table");
        roundButton3.setFocusable(false);
        roundButton3.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        roundButton3.setPreferredSize(new java.awt.Dimension(164, 30));
        roundButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout background2Layout = new javax.swing.GroupLayout(background2);
        background2.setLayout(background2Layout);
        background2Layout.setHorizontalGroup(
            background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 381, Short.MAX_VALUE)
                .addComponent(roundButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(roundButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(roundButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        background2Layout.setVerticalGroup(
            background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        background3.setBackground(new java.awt.Color(231, 231, 233));

        roundButton4.setBackground(new java.awt.Color(165, 199, 249));
        roundButton4.setForeground(new java.awt.Color(6, 52, 119));
        roundButton4.setText("Search Student");
        roundButton4.setFocusable(false);
        roundButton4.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        roundButton4.setPreferredSize(new java.awt.Dimension(164, 30));
        roundButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundButton4ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Microsoft JhengHei", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Student ID");

        ID.setBackground(new java.awt.Color(255, 255, 255));
        ID.setForeground(new java.awt.Color(51, 51, 51));
        ID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Microsoft JhengHei", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Student Name");

        Name.setEditable(false);
        Name.setBackground(new java.awt.Color(255, 255, 255));
        Name.setForeground(new java.awt.Color(51, 51, 51));

        jLabel7.setFont(new java.awt.Font("Microsoft JhengHei", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("NIC");

        NIC.setEditable(false);
        NIC.setBackground(new java.awt.Color(255, 255, 255));
        NIC.setForeground(new java.awt.Color(51, 51, 51));

        jLabel8.setFont(new java.awt.Font("Microsoft JhengHei", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Batch");

        Batch.setEditable(false);
        Batch.setBackground(new java.awt.Color(255, 255, 255));
        Batch.setForeground(new java.awt.Color(51, 51, 51));

        jLabel6.setFont(new java.awt.Font("Microsoft JhengHei", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Email");

        Email.setEditable(false);
        Email.setBackground(new java.awt.Color(255, 255, 255));
        Email.setForeground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout background3Layout = new javax.swing.GroupLayout(background3);
        background3.setLayout(background3Layout);
        background3Layout.setHorizontalGroup(
            background3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background3Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(roundButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                .addGap(38, 38, 38))
            .addGroup(background3Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(background3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6)
                    .addGroup(background3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(background3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(Batch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(NIC, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE))
                        .addGroup(background3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(ID, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                            .addComponent(Name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(Email, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27))
        );
        background3Layout.setVerticalGroup(
            background3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(roundButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(background3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(background3Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(ID, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Name, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(background3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(background3Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(Batch, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NIC, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addGap(5, 5, 5)
                .addComponent(Email, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Subject", "Teacher's Name ", "Class Day/s", "Time Peroid", "Class Status", "Payment Status"
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
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(130);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(180);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(230);
            jTable1.getColumnModel().getColumn(1).setMaxWidth(320);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(110);
            jTable1.getColumnModel().getColumn(2).setMaxWidth(160);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(180);
            jTable1.getColumnModel().getColumn(3).setMaxWidth(250);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(110);
            jTable1.getColumnModel().getColumn(4).setMaxWidth(160);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(120);
            jTable1.getColumnModel().getColumn(5).setMaxWidth(160);
        }

        javax.swing.GroupLayout background1Layout = new javax.swing.GroupLayout(background1);
        background1.setLayout(background1Layout);
        background1Layout.setHorizontalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(background2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(background1Layout.createSequentialGroup()
                        .addComponent(background3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(jScrollPane1)))
                .addGap(10, 10, 10))
        );
        background1Layout.setVerticalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(background2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(background3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(background1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void roundButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundButton1ActionPerformed
        String STno = ID.getText();
        try {
            ResultSet student = Mysql.search("SELECT * FROM `student`"
                    + "INNER JOIN `batch` ON `student`.`batch_id`=`batch`.`id` WHERE `student`.`id`='" + STno + "'");
            if (STno.isBlank()) {
                
                Notifications.getInstance().show(Notifications.Type.WARNING,
                        Notifications.Location.TOP_CENTER, "Please type Student No to search for student");
            } else if (!student.next()) {
                Notifications.getInstance().show(Notifications.Type.WARNING,
                        Notifications.Location.TOP_CENTER, "Student ID does not match any reistered Data");
            } else {
                new Update_Students(parentFrame, STno, true).setVisible(true);
                roundButton4ActionPerformed(evt);
                
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }//GEN-LAST:event_roundButton1ActionPerformed

    private void roundButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundButton2ActionPerformed
        new Register_Student(parentFrame, true).setVisible(true);
    }//GEN-LAST:event_roundButton2ActionPerformed

    private void roundButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundButton3ActionPerformed
        new Student_Table(parentFrame, true).setVisible(true);
    }//GEN-LAST:event_roundButton3ActionPerformed

    private void roundButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundButton4ActionPerformed
        String STno = ID.getText();
        
        if (STno.isBlank()) {
            Notifications.getInstance().show(Notifications.Type.WARNING,
                    Notifications.Location.TOP_CENTER, "Please type Student No to search for student");
        } else {
            
            try {
                
                ResultSet student_details = Mysql.search("SELECT * FROM `student`"
                        + "INNER JOIN `batch` ON `student`.`batch_id`=`batch`.`id` WHERE `student`.`id`='" + STno + "' ");
                
                if (student_details.next()) {
                    
                    ResultSet student_class = Mysql.search("SELECT * FROM `student`"
                            + "INNER JOIN `class_enroll` ON `student`.`id`=`class_enroll`.`student_id`"
                            + "INNER JOIN `batch` ON `student`.`batch_id`=`batch`.`id`"
                            + "INNER JOIN `class` ON `class_enroll`.`class_id`=`class`.`id`"
                            + "INNER JOIN `class_day` ON `class`.`class_day_id`=`class_day`.`id`"
                            + "INNER JOIN `teacher` ON `class`.`teacher_id`=`teacher`.`id`"
                            + "INNER JOIN `subject` ON `teacher`.`subject_id`=`subject`.`id`"
                            + "WHERE `student`.`id` = '" + STno + "' ");
                    
                    ID.setText(student_details.getString("student.id"));
                    Name.setText(student_details.getString("fname") + " " + student_details.getString("lname"));
                    Batch.setText(student_details.getString("batch.name"));
                    NIC.setText(student_details.getString("nic"));
                    Email.setText(student_details.getString("email"));
                    
                    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                    model.setRowCount(0);
                    
                    while (student_class.next()) {
                        
                        Vector v = new Vector();
                        v.add(student_class.getString("subject.name"));
                        v.add(student_class.getString("teacher.fname") + " " + student_class.getString("teacher.lname"));
                        v.add(student_class.getString("day"));
                        v.add(student_class.getString("start_time") + " " + student_class.getString("end_time"));
                        
                        String classStatus = "";
                        String paymentStatus = "";
                        
                        if (student_class.getInt("class.status_id") == 3) {
                            classStatus = Valid_Status.ONGOING.name();
                        } else if (student_class.getInt("class.status_id") == 4) {
                            classStatus = Valid_Status.COMPLETED.name();
                        }
                        if (student_class.getInt("payment_status") == 5) {
                            paymentStatus = Valid_Status.PAID.name();
                        } else if (student_class.getInt("payment_status") == 6) {
                            paymentStatus = Valid_Status.UNPAID.name();
                        }
                        v.add(classStatus);
                        v.add(paymentStatus);
                        
                        model.addRow(v);
                        jTable1.setModel(model);
                    }
                } else {
                    Notifications.getInstance().show(Notifications.Type.WARNING,
                            Notifications.Location.TOP_CENTER, "Student ID does not match any reistered Data");
                }
                
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }//GEN-LAST:event_roundButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private lk.starfield.yasoja.main_components.roundTextField Batch;
    private lk.starfield.yasoja.main_components.roundTextField Email;
    private lk.starfield.yasoja.main_components.roundTextField ID;
    private lk.starfield.yasoja.main_components.roundTextField NIC;
    private lk.starfield.yasoja.main_components.roundTextField Name;
    private lk.starfield.yasoja.main_components.Background background1;
    private lk.starfield.yasoja.main_components.Background background2;
    private lk.starfield.yasoja.main_components.Background background3;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private lk.starfield.yasoja.main_components.roundButton roundButton1;
    private lk.starfield.yasoja.main_components.roundButton roundButton2;
    private lk.starfield.yasoja.main_components.roundButton roundButton3;
    private lk.starfield.yasoja.main_components.roundButton roundButton4;
    // End of variables declaration//GEN-END:variables
}
