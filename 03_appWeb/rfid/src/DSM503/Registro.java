/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DSM503;

import com.sun.glass.events.KeyEvent;
import datos.ConexionMySQL;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.UIManager.getString;
import javax.swing.table.DefaultTableModel;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import com.fazecast.jSerialComm.SerialPort;
import java.io.IOException;
import java.io.InputStream;// entrada , es como rx
import java.io.OutputStream;//es como el tx (transmicon , recepcion)


/**
 *
 * @author PC
 */
public final class Registro extends javax.swing.JFrame {

    ConexionMySQL c;
    Statement t;
    ResultSet r;
    DefaultTableModel mitabla = new DefaultTableModel();
    Image cb;
    Image gqr;
    SerialPort puertoSerial;
    OutputStream salida;
    InputStream entrada;
    
    
    
    public Registro() throws Exception {
        initComponents();
        c = new ConexionMySQL();
        t = c.abrir().createStatement();
        r = t.executeQuery("select*from alumno");
        System.out.println("conexion exitosa");
        inicializartabla();
        this.jTextField1.setText(null);
        this.jTextField1.requestFocus();
        btnOff.setVisible(false);
       btnOn.setVisible(false);
    }
    
    public void conexion(){
        int baudios=9600;
        String puerto="COM3";
        puertoSerial=SerialPort.getCommPort(puerto);
        puertoSerial.setBaudRate(baudios);
        puertoSerial.setNumDataBits(8);
        puertoSerial.setNumStopBits(1);
        puertoSerial.setParity(1);       
        try {            
          puertoSerial.openPort();
          salida=puertoSerial.getOutputStream();
          entrada=puertoSerial.getInputStream();
          this.jLabel5.setText("conexion exitosa");
        }catch (Exception e){
            this.jLabel5.setText("conexion fallida");
            
        }
    }
    
    public void enviarArduino(String n) throws IOException{
        salida.write(n.getBytes());
        salida.flush();
        salida=puertoSerial.getOutputStream();
        entrada=puertoSerial.getInputStream();
    }
    
    public void on(){
        String dato=null;
        byte[]bytes=null;
        dato=""+1;
        bytes = dato.getBytes();
        puertoSerial.writeBytes(bytes,bytes.length);
        this.jLabel5.setText("se mando 1 a arduino");
        
    }
    
     public void off(){
        String dato=null;
        byte[]bytes=null;
        dato=""+0;
        bytes = dato.getBytes();
        puertoSerial.writeBytes(bytes,bytes.length);
        this.jLabel5.setText("se mando 0 a arduino");
        
    }
    
    
    public void buscarMatricula(String m) throws Exception{
         
      t=null;
      t=c.abrir().createStatement();
      r=t.executeQuery("select*from alumno where matricula='"+m+"'");
      r.first();
     
         
      
      
      if (r.absolute(1)){
         this.jTextArea1.setText(r.getString(1)+" "+ r.getString(2)+" "+r.getString(3)+" "+r.getString(3)+" "+r.getString(4)+" "+r.getString(5)+" "+r.getString(6));                   
         cb=Toolkit.getDefaultToolkit().getImage("src/DSM503/"+r.getString(1)+".png");
         this.jLabel3.setIcon(new ImageIcon(cb.getScaledInstance(250, 100, 0)));
       
         this.jTextField1.setText(null);
         this.jTextField1.requestFocus();
//         this.enviarArduino(r.getString(2));
         
           off();
      }
      
    }

    public void inicializartabla() throws Exception {
        mitabla.addColumn("Matricula");
        mitabla.addColumn("Nombre");
        mitabla.addColumn("Primer Apellido");
        mitabla.addColumn("Segundo Apellido");
        mitabla.addColumn("Edad");
        mitabla.addColumn("Telefono");
        this.jTable1.setModel(mitabla);
        llenartabla();
 
    }

   public void llenartabla() throws Exception{
      t=null;
      t=c.abrir().createStatement();
      r=t.executeQuery("select*from alumno");
      r.beforeFirst();
      
      while(r.next()){
          mitabla=(DefaultTableModel)this.jTable1.getModel();
          Object [] datos = {r.getString(1),r.getString(2),r.getString(3),r.getString(4),r.getString(5),r.getString(6)};
          mitabla.addRow(datos);
          this.jTable1.setModel(mitabla);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        btnOff = new javax.swing.JButton();
        btnOn = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnConexion = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 0), 10));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("matricula:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 40, -1, -1));

        jTextField1.setText("jTextField1");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 80, 110, -1));

        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 40, 230, 180));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 420, 670, 150));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 290, 670, -1));

        btnOff.setText("On");
        btnOff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnOffMouseClicked(evt);
            }
        });
        btnOff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOffActionPerformed(evt);
            }
        });
        jPanel3.add(btnOff);

        btnOn.setText("Off");
        btnOn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnOnMouseClicked(evt);
            }
        });
        jPanel3.add(btnOn);

        jLabel4.setText("Estatus:");
        jPanel3.add(jLabel4);

        jLabel5.setText("jLabel5");
        jPanel3.add(jLabel5);

        btnConexion.setText("conexion");
        btnConexion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnConexionMouseClicked(evt);
            }
        });
        btnConexion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConexionActionPerformed(evt);
            }
        });
        jPanel3.add(btnConexion);

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 150, 100));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
       if (evt.getKeyCode()==KeyEvent.VK_ENTER){
           try{
           buscarMatricula(this.jTextField1.getText());
       }catch(Exception ex){
           Logger.getLogger(Registro.class.getName()).log(Level.SEVERE,null,ex);
       
       }
       }
      
    }//GEN-LAST:event_jTextField1KeyPressed

    private void btnConexionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConexionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnConexionActionPerformed

    private void btnConexionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConexionMouseClicked
       this.conexion();
    }//GEN-LAST:event_btnConexionMouseClicked

    private void btnOnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOnMouseClicked
this.on();        // TODO add your handling code here:
    }//GEN-LAST:event_btnOnMouseClicked

    private void btnOffMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOffMouseClicked
        this.off();  
    }//GEN-LAST:event_btnOffMouseClicked

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void btnOffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOffActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnOffActionPerformed

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
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Registro().setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConexion;
    private javax.swing.JButton btnOff;
    private javax.swing.JButton btnOn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
