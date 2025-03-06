/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package lk.starfield.yasoja.panels;

import java.awt.Color;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import lk.starfield.yasoja.connection.Mysql;
import lk.starfield.yasoja.gui.Dashboard;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;
import raven.toast.Notifications;

/**
 *
 * @author dnana
 */
public class Payment_Due extends javax.swing.JPanel {

    HashMap<String, String> subjectMap = new HashMap<>();
    JFrame parentFrame;
    
    public Payment_Due(JFrame parent) {
        initComponents();
        loadTable();
        loadSubjects();
        parentFrame = parent;
        Notifications.getInstance().setJFrame(parentFrame);
    }

    private void loadSubjects() {
        try {
            ResultSet resultSet = Mysql.search("SELECT * FROM `subject`");

            Vector v = new Vector();
            v.add("Select Subject");
            while (resultSet.next()) {
                v.add(resultSet.getString("name"));
                subjectMap.put(resultSet.getString("name"), resultSet.getString("id"));
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
        tableHeader.setForeground(THcolotFont);
        tableHeader.setFont(headerFont);
        jTable1.setTableHeader(tableHeader);

        try {

            ResultSet student_rs = Mysql.search("SELECT *FROM `student`"
                    + "INNER JOIN `class_enroll` ON `student`.`id`=`class_enroll`.`student_id`"
                    + "INNER JOIN `class` ON `class_enroll`.`class_id`=`class`.`id`"
                    + "INNER JOIN `teacher` ON `class`.`teacher_id`=`teacher`.`id`"
                    + "INNER JOIN `subject` ON `teacher`.`subject_id`=`subject`.`id`"
                    + "WHERE `payment_status`='6' ");

            ResultSet duelist_detail = Mysql.search("SELECT COUNT(`student`.`id`) AS `count` "
                    + ",SUM(`subject`.`fee`)AS `total` FROM `class_enroll`"
                    + "INNER JOIN `student` ON `student`.`id`=`class_enroll`.`student_id`"
                    + "INNER JOIN `class` ON `class_enroll`.`class_id`=`class`.`id`"
                    + "INNER JOIN `teacher` ON `class`.`teacher_id`=`teacher`.`id`"
                    + "INNER JOIN `subject` ON `teacher`.`subject_id`=`subject`.`id`"
                    + "WHERE `payment_status`='6' ");

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            DefaultTableCellRenderer columnRenderer = new DefaultTableCellRenderer();
            columnRenderer.setHorizontalAlignment(SwingConstants.CENTER);

            for (int columnIndex = 0; columnIndex < model.getColumnCount(); columnIndex++) {
                jTable1.getColumnModel().getColumn(columnIndex).setCellRenderer(columnRenderer);
            }
            if (duelist_detail.next()) {
                while (student_rs.next()) {

                    Vector v = new Vector();
                    v.add(student_rs.getString("student.id"));
                    v.add(student_rs.getString("student.fname") + " " + student_rs.getString("student.lname"));
                    v.add(student_rs.getString("subject.name"));
                    v.add(student_rs.getString("class.id"));
                    v.add(student_rs.getString("subject.fee"));

                    model.addRow(v);
                    jTable1.setModel(model);
                }
                countLabel.setText(duelist_detail.getString("count"));
                amountLabel.setText(duelist_detail.getString("total"));
            }

        } catch (SQLException e) {
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

        background1 = new lk.starfield.yasoja.main_components.Background();
        background2 = new lk.starfield.yasoja.main_components.Background();
        roundButton4 = new lk.starfield.yasoja.main_components.roundButton();
        jLabel7 = new javax.swing.JLabel();
        STname = new lk.starfield.yasoja.main_components.roundTextField();
        jLabel6 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        background4 = new lk.starfield.yasoja.main_components.Background();
        roundButton5 = new lk.starfield.yasoja.main_components.roundButton();
        jLabel11 = new javax.swing.JLabel();
        countLabel = new javax.swing.JLabel();
        amountLabel = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        background1.setBackground(new java.awt.Color(255, 255, 255));

        background2.setBackground(new java.awt.Color(231, 231, 233));

        roundButton4.setBackground(new java.awt.Color(165, 199, 249));
        roundButton4.setForeground(new java.awt.Color(6, 52, 119));
        roundButton4.setText("Search Class");
        roundButton4.setFocusable(false);
        roundButton4.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        roundButton4.setPreferredSize(new java.awt.Dimension(164, 30));
        roundButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundButton4ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Microsoft JhengHei", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Student Name");

        STname.setBackground(new java.awt.Color(255, 255, 255));
        STname.setForeground(new java.awt.Color(51, 51, 51));
        STname.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Microsoft JhengHei", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Batch Name");

        jComboBox1.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout background2Layout = new javax.swing.GroupLayout(background2);
        background2.setLayout(background2Layout);
        background2Layout.setHorizontalGroup(
            background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background2Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background2Layout.createSequentialGroup()
                        .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7)
                            .addComponent(STname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6)
                            .addComponent(jComboBox1, 0, 205, Short.MAX_VALUE))
                        .addGap(15, 15, 15))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background2Layout.createSequentialGroup()
                        .addComponent(roundButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))))
        );
        background2Layout.setVerticalGroup(
            background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(STname, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(roundButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        background4.setBackground(new java.awt.Color(231, 231, 233));

        roundButton5.setBackground(new java.awt.Color(196, 248, 241));
        roundButton5.setForeground(new java.awt.Color(31, 125, 112));
        roundButton5.setText("Print Due List");
        roundButton5.setFocusable(false);
        roundButton5.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        roundButton5.setPreferredSize(new java.awt.Dimension(164, 30));
        roundButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundButton5ActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Microsoft JhengHei", 1, 15)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("No of Due Payments :");

        countLabel.setFont(new java.awt.Font("Microsoft JhengHei", 1, 15)); // NOI18N
        countLabel.setForeground(new java.awt.Color(51, 51, 51));
        countLabel.setText("Count");

        amountLabel.setFont(new java.awt.Font("Microsoft JhengHei", 1, 15)); // NOI18N
        amountLabel.setForeground(new java.awt.Color(51, 51, 51));
        amountLabel.setText("Amount");

        jLabel12.setFont(new java.awt.Font("Microsoft JhengHei", 1, 15)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Total Due Amount :");

        javax.swing.GroupLayout background4Layout = new javax.swing.GroupLayout(background4);
        background4.setLayout(background4Layout);
        background4Layout.setHorizontalGroup(
            background4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background4Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(countLabel)
                .addGap(41, 41, 41)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(amountLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(roundButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        background4Layout.setVerticalGroup(
            background4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(background4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(roundButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(countLabel)
                    .addGroup(background4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(amountLabel)))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jTable1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student ID", "Student Name", "Subject", "Class ID", "Amount"
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
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(110);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(140);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(210);
            jTable1.getColumnModel().getColumn(1).setMaxWidth(230);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(3).setMaxWidth(120);
        }

        javax.swing.GroupLayout background1Layout = new javax.swing.GroupLayout(background1);
        background1.setLayout(background1Layout);
        background1Layout.setHorizontalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(background4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(background1Layout.createSequentialGroup()
                        .addComponent(background2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        background1Layout.setVerticalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(background4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(background2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(81, 81, 81))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(background1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void roundButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundButton4ActionPerformed
        String subject = String.valueOf(jComboBox1.getSelectedItem());
        String name = STname.getText();
        String subject_id = subjectMap.get(subject);

        String query = "SELECT *FROM `student`"
                + "INNER JOIN `class_enroll` ON `student`.`id`=`class_enroll`.`student_id`"
                + "INNER JOIN `class` ON `class_enroll`.`class_id`=`class`.`id`"
                + "INNER JOIN `teacher` ON `class`.`teacher_id`=`teacher`.`id`"
                + "INNER JOIN `subject` ON `teacher`.`subject_id`=`subject`.`id`"
                + "WHERE `payment_status`='6' ";

        if (subject.equals("Select Subject") && name.isBlank()) {
            Notifications.getInstance().show(Notifications.Type.WARNING,
                    Notifications.Location.TOP_CENTER, "Please Enter details to search");
        } else if (subject.equals("Select Subject") && !name.isBlank()) {
            query = query + " AND (`student`.`fname` LIKE '" + name + "%' OR `student`.`lname` LIKE '" + name + "%')  ";
        } else if (!subject.equals("Select Subject") && name.isBlank()) {

            query = query + " AND `subject_id`='" + subject_id + "'  ";
        } else if (!subject.equals("Select Subject") && !name.isBlank()) {
            query = query + "AND `subject_id`='" + subject_id + "' AND (`student`.`fname` LIKE '" + name + "%' OR `student`.`lname` LIKE '" + name + "%')  ";
        } else {
            loadTable();
        }

        try {
            ResultSet student_rs = Mysql.search(query);

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
                v.add(student_rs.getString("student.fname") + " " + student_rs.getString("student.lname"));
                v.add(student_rs.getString("subject.name"));
                v.add(student_rs.getString("class.id"));
                v.add(student_rs.getString("subject.fee"));

                model.addRow(v);
                jTable1.setModel(model);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_roundButton4ActionPerformed

    private void roundButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundButton5ActionPerformed
        String count = countLabel.getText();
        String amount = amountLabel.getText();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String date = sdf.format(new Date());

        try {
            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put("count", count);
            parameters.put("date", date);
            parameters.put("total", amount);

            JasperReport compiledReport = JasperCompileManager.compileReport("src/lk/starfield/yasoja/reports/Starfield_duelist.jrxml");
            JRDataSource datasource = new JRTableModelDataSource(jTable1.getModel());
            JasperPrint report = JasperFillManager.fillReport(compiledReport, parameters, datasource);
            JasperViewer.viewReport(report, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_roundButton5ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (evt.getClickCount() == 2) {
            String classID = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 3));
            String STno = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 0));

            Payment panel = new Payment(parentFrame);
            panel.getClassID().setText(classID);
            panel.getID().setText(STno);

            Dashboard dashboard = new Dashboard();
            dashboard.loadPanel(panel);
            dashboard.setVisible(true);
            parentFrame.setVisible(false);
        }
    }//GEN-LAST:event_jTable1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private lk.starfield.yasoja.main_components.roundTextField STname;
    private javax.swing.JLabel amountLabel;
    private lk.starfield.yasoja.main_components.Background background1;
    private lk.starfield.yasoja.main_components.Background background2;
    private lk.starfield.yasoja.main_components.Background background4;
    private javax.swing.JLabel countLabel;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private lk.starfield.yasoja.main_components.roundButton roundButton4;
    private lk.starfield.yasoja.main_components.roundButton roundButton5;
    // End of variables declaration//GEN-END:variables
}
