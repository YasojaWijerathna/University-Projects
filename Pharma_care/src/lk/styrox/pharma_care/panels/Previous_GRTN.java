/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package lk.styrox.pharma_care.panels;

import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import lk.styrox.pharma_care.connection.Mysql;
import lk.styrox.pharma_care.logger.Logger;
import lk.styrox.pharma_care.table_model.TableCustom;
import raven.toast.Notifications;

/**
 *
 * @author thara
 */
public class Previous_GRTN extends javax.swing.JPanel {

    /**
     * Creates new form Previous_GRTN
     */
    public Previous_GRTN() {
        initComponents();
        loadTable();
    }

    public void loadTable() {
        TableCustom.apply(tableScrollPane, TableCustom.TableType.DEFAULT);
        JTableHeader header = jTable1.getTableHeader();
        header.setFont(new Font("San-Serif", Font.BOLD, 13));

        try {
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            ResultSet rs = Mysql.search("SELECT *\n"
                    + "FROM `goods_return`\n"
                    + "INNER JOIN `goods_return_item` ON `goods_return`.`id` = `goods_return_item`.`grtn_id`\n"
                    + "INNER JOIN `stock` ON `goods_return_item`.`barcode` = `stock`.`barcode`\n"
                    + "INNER JOIN `product` ON `stock`.`product_id` = `product`.`id`\n"
                    + "INNER JOIN `brand` ON `product`.`brand_id` = `brand`.`id`");

            while (rs.next()) {
                Vector<String> v = new Vector<>();
                v.add(rs.getString("goods_return.id"));
                v.add(rs.getString("stock.barcode"));
                v.add(rs.getString("product.name"));
                v.add(rs.getString("brand.name"));
                v.add(rs.getString("goods_return_item.qty"));
                v.add(rs.getString("stock.buying_price"));
                v.add(rs.getString("goods_return.date"));

                model.addRow(v);
                jTable1.setModel(model);
            }

        } catch (SQLException e) {
            Logger.AdminlLog(String.valueOf(e));
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

        background1 = new lk.styrox.pharma_care.main_components.Background();
        background2 = new lk.styrox.pharma_care.main_components.Background();
        roundButton1 = new lk.styrox.pharma_care.main_components.roundButton();
        jLabel1 = new javax.swing.JLabel();
        roundTextField1 = new lk.styrox.pharma_care.main_components.roundTextField();
        roundTextField2 = new lk.styrox.pharma_care.main_components.roundTextField();
        jLabel3 = new javax.swing.JLabel();
        tableScrollPane = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        background1.setBackground(new java.awt.Color(255, 255, 255));

        background2.setBackground(new java.awt.Color(231, 231, 233));

        roundButton1.setBackground(new java.awt.Color(185, 235, 193));
        roundButton1.setForeground(new java.awt.Color(52, 198, 75));
        roundButton1.setText("Search");
        roundButton1.setFont(new java.awt.Font("Microsoft JhengHei", 1, 16)); // NOI18N
        roundButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel1.setText("GRTN ID");

        roundTextField1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        roundTextField2.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel3.setText("Product Name");

        javax.swing.GroupLayout background2Layout = new javax.swing.GroupLayout(background2);
        background2.setLayout(background2Layout);
        background2Layout.setHorizontalGroup(
            background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background2Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(roundTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(roundTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 409, Short.MAX_VALUE)
                .addComponent(roundButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );
        background2Layout.setVerticalGroup(
            background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(roundButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
            .addGroup(background2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(background2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(background2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "GRN ID", "Stock ID ", "Product Name", "Brand", "Quantity", "Buying Price", "Date Of Purchase"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableScrollPane.setViewportView(jTable1);

        javax.swing.GroupLayout background1Layout = new javax.swing.GroupLayout(background1);
        background1.setLayout(background1Layout);
        background1Layout.setHorizontalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tableScrollPane)
                    .addComponent(background2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        background1Layout.setVerticalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(background2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(tableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(background1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void roundButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundButton1ActionPerformed
        String grtn_id = roundTextField1.getText();
        String product_name = roundTextField2.getText();

        String query = "SELECT *\n"
                + "FROM `goods_return`\n"
                + "INNER JOIN `goods_return_item` ON `goods_return`.`id` = `goods_return_item`.`grtn_id`\n"
                + "INNER JOIN `stock` ON `goods_return_item`.`barcode` = `stock`.`barcode`\n"
                + "INNER JOIN `product` ON `stock`.`product_id` = `product`.`id`\n"
                + "INNER JOIN `brand` ON `product`.`brand_id` = `brand`.`id` WHERE `grtn_id` LIKE '%"+grtn_id+"%' AND `product`.`name` LIKE '%"+product_name+"%'";

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        try {
            ResultSet rs = Mysql.search(query);

            while (rs.next()) {

                Vector<String> v = new Vector<>();
                v.add(rs.getString("goods_return.id"));
                v.add(rs.getString("stock.barcode"));
                v.add(rs.getString("product.name"));
                v.add(rs.getString("brand.name"));
                v.add(rs.getString("goods_return_item.qty"));
                v.add(rs.getString("stock.buying_price"));
                v.add(rs.getString("goods_return.date"));

                model.addRow(v);
                jTable1.setModel(model);
            }

        } catch (SQLException e) {
            Logger.AdminlLog(String.valueOf(e));
            e.printStackTrace();
        }
    }//GEN-LAST:event_roundButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private lk.styrox.pharma_care.main_components.Background background1;
    private lk.styrox.pharma_care.main_components.Background background2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTable jTable1;
    private lk.styrox.pharma_care.main_components.roundButton roundButton1;
    private lk.styrox.pharma_care.main_components.roundTextField roundTextField1;
    private lk.styrox.pharma_care.main_components.roundTextField roundTextField2;
    private javax.swing.JScrollPane tableScrollPane;
    // End of variables declaration//GEN-END:variables
}
