/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package lk.starfield.yasoja.dialogs;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.Color;
import java.awt.Font;
import java.sql.*;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import lk.starfield.yasoja.connection.Mysql;
import raven.toast.Notifications;

/**
 *
 * @author dnana
 */
public class Student_Table extends javax.swing.JDialog {

    /**
     * Creates new form Dashboard_details
     */
    public Student_Table(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        loadBatchs();
        loadTable();

        Notifications.getInstance().setJFrame((JFrame) parent);
    }

    private void loadBatchs() {
        try {
            ResultSet resultSet = Mysql.search("SELECT * FROM `batch`");

            Vector v = new Vector();
            v.add("Select Batch");

            while (resultSet.next()) {
                v.add(resultSet.getString("name"));

            }
            DefaultComboBoxModel m = new DefaultComboBoxModel(v);
            jComboBox1.setModel(m);

        } catch (Exception e) {
            System.out.println(e);

        }
    }

    private void loadTable() {

        JTableHeader tableHeader = jTable1.getTableHeader();
        Font headerFont = new Font("Microsoft JhengHei ", Font.BOLD, 16);
        Color THcolorBg = new Color(6, 52, 119);
        Color THcolotFont = new Color(255, 255, 255);
        tableHeader.setBackground(THcolorBg);
        tableHeader.setForeground(THcolotFont );
        tableHeader.setFont(headerFont);
        jTable1.setTableHeader(tableHeader);

        try {

            ResultSet student_rs = Mysql.search("SELECT * FROM `student`"
                    + "INNER JOIN `batch` ON `student`.`batch_id`= `batch`.`id` "
                    + " ORDER BY `student`.`id` ASC ");

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            DefaultTableCellRenderer columnRenderer = new DefaultTableCellRenderer();
            columnRenderer.setHorizontalAlignment(SwingConstants.CENTER);

            for (int columnIndex = 0; columnIndex < model.getColumnCount(); columnIndex++) {
                jTable1.getColumnModel().getColumn(columnIndex).setCellRenderer(columnRenderer);
            }

            while (student_rs.next()) {

                Vector v = new Vector();
                v.add(student_rs.getString("student.id"));
                v.add(student_rs.getString("fname") + " " + student_rs.getString("lname"));
                v.add(student_rs.getString("nic"));
                v.add(student_rs.getString("mobile1"));
                v.add(student_rs.getString("email"));
                v.add(student_rs.getString("batch.name"));

                model.addRow(v);
                jTable1.setModel(model);

            }

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

        background2 = new lk.starfield.yasoja.main_components.Background();
        logo2 = new lk.starfield.yasoja.sub_custom_components.Logo();
        jLabel2 = new javax.swing.JLabel();
        background1 = new lk.starfield.yasoja.main_components.Background();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        roundTextField1 = new lk.starfield.yasoja.main_components.roundTextField();
        jLabel5 = new javax.swing.JLabel();
        roundTextField2 = new lk.starfield.yasoja.main_components.roundTextField();
        jLabel6 = new javax.swing.JLabel();
        roundTextField3 = new lk.starfield.yasoja.main_components.roundTextField();
        roundButton1 = new lk.starfield.yasoja.main_components.roundButton();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);

        background2.setBackground(new java.awt.Color(255, 255, 255));

        logo2.setBackground(new java.awt.Color(255, 255, 255));
        logo2.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout logo2Layout = new javax.swing.GroupLayout(logo2);
        logo2.setLayout(logo2Layout);
        logo2Layout.setHorizontalGroup(
            logo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 143, Short.MAX_VALUE)
        );
        logo2Layout.setVerticalGroup(
            logo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 62, Short.MAX_VALUE)
        );

        jLabel2.setFont(new java.awt.Font("Leelawadee", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Search Student");

        background1.setBackground(new java.awt.Color(231, 231, 233));

        jLabel3.setFont(new java.awt.Font("Microsoft JhengHei", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Select Batch");

        jComboBox1.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox1.setForeground(new java.awt.Color(51, 51, 51));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setFont(new java.awt.Font("Microsoft JhengHei", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Student ID");

        roundTextField1.setBackground(new java.awt.Color(255, 255, 255));
        roundTextField1.setForeground(new java.awt.Color(51, 51, 51));
        roundTextField1.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Microsoft JhengHei", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Student Name");

        roundTextField2.setBackground(new java.awt.Color(255, 255, 255));
        roundTextField2.setForeground(new java.awt.Color(51, 51, 51));
        roundTextField2.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Microsoft JhengHei", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("NIC");

        roundTextField3.setBackground(new java.awt.Color(255, 255, 255));
        roundTextField3.setForeground(new java.awt.Color(51, 51, 51));
        roundTextField3.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 14)); // NOI18N

        roundButton1.setBackground(new java.awt.Color(232, 194, 255));
        roundButton1.setForeground(new java.awt.Color(104, 2, 166));
        roundButton1.setText("Search Student");
        roundButton1.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        roundButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout background1Layout = new javax.swing.GroupLayout(background1);
        background1.setLayout(background1Layout);
        background1Layout.setHorizontalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background1Layout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(background1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(37, 37, 37)
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(roundTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(background1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addGap(143, 143, 143))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(background1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(195, 195, 195))
                    .addGroup(background1Layout.createSequentialGroup()
                        .addComponent(roundTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                        .addComponent(roundButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))))
        );
        background1Layout.setVerticalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(background1Layout.createSequentialGroup()
                        .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1))
                    .addGroup(background1Layout.createSequentialGroup()
                        .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(background1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(background1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(roundTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(roundTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(background1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(2, 2, 2)
                                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(roundTextField1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(roundButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        jTable1.setBackground(new java.awt.Color(255, 255, 255));
        jTable1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Full Name ", "NIC", "Mobile", "Email", "Batch"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
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
        jTable1.setFocusCycleRoot(true);
        jTable1.setShowHorizontalLines(true);
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setMinWidth(2);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(70);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(80);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(20);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(140);
            jTable1.getColumnModel().getColumn(3).setMaxWidth(150);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(110);
            jTable1.getColumnModel().getColumn(5).setMaxWidth(150);
        }

        javax.swing.GroupLayout background2Layout = new javax.swing.GroupLayout(background2);
        background2.setLayout(background2Layout);
        background2Layout.setHorizontalGroup(
            background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background2Layout.createSequentialGroup()
                .addContainerGap(8, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 967, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9))
            .addGroup(background2Layout.createSequentialGroup()
                .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(background2Layout.createSequentialGroup()
                        .addComponent(logo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(275, 275, 275)
                        .addComponent(jLabel2))
                    .addGroup(background2Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(background1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        background2Layout.setVerticalGroup(
            background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(logo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(background1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(background2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void roundButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundButton1ActionPerformed
        int batchID = jComboBox1.getSelectedIndex();
        String STNo = roundTextField1.getText();
        String name = roundTextField2.getText();
        String nic = roundTextField3.getText();

        String query = "SELECT * FROM `student` INNER JOIN `batch` ON `student`.`batch_id`= `batch`.`id` ";

        if (batchID == 0) {
            if (STNo.isBlank() && name.isBlank() && nic.isEmpty()) {
                Notifications.getInstance().show(Notifications.Type.WARNING,
                        Notifications.Location.TOP_CENTER, "Search details not included");
                loadTable();
                return;
            } else if (!STNo.isBlank()) {
                query = query + "  WHERE `student`.`id` LIKE '%" + STNo + "%'";
            } else if (!name.isBlank()) {
                query = query + " WHERE `fname` LIKE '" + name + "%' OR `lname` LIKE '"+name+"%' ";
            } else if (!nic.isBlank()) {
                query = query + " WHERE `nic` LIKE '%" + nic + "%'";
            }else{
                loadTable();
            }
        } else {
            if (STNo.isBlank() && name.isBlank() && nic.isEmpty()) {
                query = query + " WHERE `batch_id`='" + batchID + "'";
            } else if (!STNo.isBlank()) {
                query = query + "  WHERE `batch_id`='" + batchID + "' AND `student`.`id` LIKE '%" + STNo + "%'";
            } else if (!name.isBlank()) {
                query = query + " WHERE `batch_id`='" + batchID + "' AND `fname` LIKE '" + name + "%' OR `lname` LIKE '"+name+"%' ";
            } else if (!nic.isBlank()) {
                query = query + " WHERE `batch_id`='" + batchID + "' AND `nic` LIKE '%" + nic + "%'";
            }else{
                loadTable();
            }
        }
        try {
            ResultSet rs = Mysql.search(query);
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("student.id"));
                v.add(rs.getString("fname") + " " + rs.getString("lname"));
                v.add(rs.getString("nic"));
                v.add(rs.getString("mobile1"));
                v.add(rs.getString("email"));
                v.add(rs.getString("batch.name"));

                model.addRow(v);
                jTable1.setModel(model);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }//GEN-LAST:event_roundButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        FlatMacLightLaf.setup();

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Student_Table dialog = new Student_Table(new javax.swing.JFrame(), true);
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
    private lk.starfield.yasoja.main_components.Background background1;
    private lk.starfield.yasoja.main_components.Background background2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private lk.starfield.yasoja.sub_custom_components.Logo logo2;
    private lk.starfield.yasoja.main_components.roundButton roundButton1;
    private lk.starfield.yasoja.main_components.roundTextField roundTextField1;
    private lk.starfield.yasoja.main_components.roundTextField roundTextField2;
    private lk.starfield.yasoja.main_components.roundTextField roundTextField3;
    // End of variables declaration//GEN-END:variables
}
