/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vistas;

import Controlador.ControladorPrincipal;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author monco
 */
public class Principal extends javax.swing.JFrame {

    DefaultTableModel modelo;
    private Principal prin;

    public Principal() {
        initComponents();
       // graficaLinealHistorico();
        //graficaLinealAritmetco();
        //graficaLinealGeometrico();
        //graficaLinealExponencial();
        
        
        this.setTitle("Simulacion de crecimiento poblacional");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximiza la ventana
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Permite cerrar solo esta ventana
        
        ControladorPrincipal.llenarComboBoxProductos(txtEstado);
        
    }
    
    
    

    public void setPrin(Principal prin) {
        this.prin = prin;
    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelPrincipal = new javax.swing.JPanel();
        PanelPrincipal2 = new javax.swing.JPanel();
        PanelSup = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtEstado = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        nombreProd = new javax.swing.JLabel();
        txtDatos = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        iniciar = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        PanelInferiorm = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        PanelCentro = new javax.swing.JPanel();
        PanelBtn = new javax.swing.JPanel();
        graficas = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        PanelLogin = new javax.swing.JPanel();
        panelHistorico = new javax.swing.JPanel();
        panelAritmetico = new javax.swing.JPanel();
        panelGeometrico = new javax.swing.JPanel();
        panelExponencial = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        PanelPrincipal.setLayout(new java.awt.BorderLayout());

        PanelPrincipal2.setLayout(new java.awt.BorderLayout());

        PanelSup.setBackground(new java.awt.Color(0, 102, 102));
        PanelSup.setMinimumSize(new java.awt.Dimension(300, 200));
        PanelSup.setPreferredSize(new java.awt.Dimension(200, 150));
        PanelSup.setLayout(new java.awt.GridLayout(4, 6, 10, 0));
        PanelSup.add(jLabel19);

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("   C      R      E      C        I");
        PanelSup.add(jLabel17);

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("M     I    E    N    T   O ");
        jLabel18.setToolTipText("");
        PanelSup.add(jLabel18);

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("      P      O      B     L      A  ");
        PanelSup.add(jLabel15);

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("C     I     O    N    A    L");
        PanelSup.add(jLabel14);
        PanelSup.add(jLabel13);
        PanelSup.add(jLabel20);
        PanelSup.add(jLabel21);

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Estado:");
        PanelSup.add(jLabel8);

        txtEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activo", "Inactivo" }));
        txtEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEstadoActionPerformed(evt);
            }
        });
        PanelSup.add(txtEstado);
        PanelSup.add(jLabel22);
        PanelSup.add(jLabel23);
        PanelSup.add(jLabel24);
        PanelSup.add(jLabel25);

        nombreProd.setForeground(new java.awt.Color(255, 255, 255));
        nombreProd.setText("Cantidad de años a proyectar:");
        PanelSup.add(nombreProd);

        txtDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDatosActionPerformed(evt);
            }
        });
        PanelSup.add(txtDatos);
        PanelSup.add(jLabel26);
        PanelSup.add(jLabel27);
        PanelSup.add(jLabel28);
        PanelSup.add(jLabel31);
        PanelSup.add(jLabel29);

        iniciar.setText("Iniciar");
        iniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iniciarActionPerformed(evt);
            }
        });
        PanelSup.add(iniciar);
        PanelSup.add(jLabel32);
        PanelSup.add(jLabel33);

        PanelPrincipal2.add(PanelSup, java.awt.BorderLayout.NORTH);

        PanelInferiorm.setBackground(new java.awt.Color(0, 102, 102));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Derechos reservados por Super Wash");
        PanelInferiorm.add(jLabel1);

        PanelPrincipal2.add(PanelInferiorm, java.awt.BorderLayout.SOUTH);

        PanelCentro.setBackground(new java.awt.Color(158, 117, 50));
        PanelCentro.setLayout(new java.awt.BorderLayout());

        PanelBtn.setBackground(new java.awt.Color(0, 102, 102));
        PanelCentro.add(PanelBtn, java.awt.BorderLayout.SOUTH);

        graficas.setLayout(new java.awt.BorderLayout());

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        graficas.add(jPanel3, java.awt.BorderLayout.PAGE_START);

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        graficas.add(jPanel4, java.awt.BorderLayout.CENTER);

        PanelCentro.add(graficas, java.awt.BorderLayout.EAST);

        jPanel2.setOpaque(false);
        jPanel2.add(jLabel2);

        PanelCentro.add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jPanel1.setOpaque(false);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/mundo3.png"))); // NOI18N
        jPanel1.add(jLabel4);

        PanelLogin.setOpaque(false);
        PanelLogin.setOpaque(false);
        PanelLogin.setPreferredSize(new java.awt.Dimension(760, 500));
        PanelLogin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelHistorico.setLayout(new java.awt.BorderLayout());
        PanelLogin.add(panelHistorico, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 380, 210));

        panelAritmetico.setLayout(new java.awt.BorderLayout());
        PanelLogin.add(panelAritmetico, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 0, 360, 210));

        panelGeometrico.setLayout(new java.awt.BorderLayout());
        PanelLogin.add(panelGeometrico, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 380, 210));

        panelExponencial.setLayout(new java.awt.BorderLayout());
        PanelLogin.add(panelExponencial, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 240, 360, 210));

        jButton2.setText("Datos");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        PanelLogin.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 210, -1, -1));

        jButton3.setText("Descripcion ");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        PanelLogin.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 210, -1, -1));

        jButton4.setText("Descripcion ");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        PanelLogin.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 210, -1, -1));

        jButton5.setText("Datos");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        PanelLogin.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 450, -1, -1));

        jButton6.setText("Descripcion ");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        PanelLogin.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 450, -1, -1));

        jButton8.setText("Datos");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        PanelLogin.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 210, -1, -1));

        jButton9.setText("Datos");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        PanelLogin.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 450, -1, -1));

        jButton10.setText("Descripcion ");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        PanelLogin.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 450, -1, -1));

        jPanel1.add(PanelLogin);

        PanelCentro.add(jPanel1, java.awt.BorderLayout.CENTER);

        PanelPrincipal2.add(PanelCentro, java.awt.BorderLayout.CENTER);

        PanelPrincipal.add(PanelPrincipal2, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 1190, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void iniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iniciarActionPerformed
        // TODO add your handling code here:
        //aca se realizara todo
        ControladorPrincipal.operar();

    }//GEN-LAST:event_iniciarActionPerformed

    private void txtDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDatosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDatosActionPerformed

    private void txtEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEstadoActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        ControladorPrincipal.mostrarDescripcionHistorica();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        ControladorPrincipal.mostrarDescripcionAritmetica();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        ControladorPrincipal.mostrarDescripcionGeometrica();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        ControladorPrincipal.mostrarDescripcionExponencial();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        ControladorPrincipal.mostrarHistoricos();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        ControladorPrincipal.mostrarAritmeticos();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        ControladorPrincipal.mostrarGeometricos();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        ControladorPrincipal.mostrarExponencial();
    }//GEN-LAST:event_jButton9ActionPerformed


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
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelBtn;
    private javax.swing.JPanel PanelCentro;
    private javax.swing.JPanel PanelInferiorm;
    private javax.swing.JPanel PanelLogin;
    public javax.swing.JPanel PanelPrincipal;
    public javax.swing.JPanel PanelPrincipal2;
    private javax.swing.JPanel PanelSup;
    private javax.swing.JPanel graficas;
    private javax.swing.JButton iniciar;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel nombreProd;
    public javax.swing.JPanel panelAritmetico;
    public javax.swing.JPanel panelExponencial;
    public javax.swing.JPanel panelGeometrico;
    public javax.swing.JPanel panelHistorico;
    public javax.swing.JTextField txtDatos;
    public javax.swing.JComboBox<String> txtEstado;
    // End of variables declaration//GEN-END:variables
}
