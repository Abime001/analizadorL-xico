/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;
//import clases.Lexico;
//import clases.Sintactico;
//import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Iltze
 */
public class LGUI extends javax.swing.JFrame {
  
    BufferedReader input = null;
    Lexico lexico;
    Sintactico sintactico;
    Token tok;
    NumeroLinea numeroLinea;
    
    public LGUI() {
        JFrame jf = new JFrame();
        jf.setTitle("Analizador sintáctico p/Lenguaje propio - Equipo 1");
        initComponents();        
        numeroLinea = new NumeroLinea(t1);
        jScrollPane1.setRowHeaderView(numeroLinea);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBox1 = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        t1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        t2 = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        t4 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        b3 = new javax.swing.JButton();
        b2 = new javax.swing.JButton();
        b1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        t5 = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();

        jCheckBox1.setText("jCheckBox1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 51, 153));
        setLocation(new java.awt.Point(10, 10));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 51, 153));

        t1.setColumns(20);
        t1.setFont(new java.awt.Font("Calibri", 0, 13)); // NOI18N
        t1.setRows(5);
        jScrollPane1.setViewportView(t1);

        t2.setColumns(20);
        t2.setRows(5);
        jScrollPane2.setViewportView(t2);

        t4.setColumns(20);
        t4.setRows(5);
        jScrollPane4.setViewportView(t4);

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("INGRESA EL CÓDIGO A ANALIZAR");

        jLabel2.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("ANÁLISIS LÉXICO");

        jLabel3.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("ANÁLISIS SINTÁCTICO");

        b3.setBackground(new java.awt.Color(0, 51, 153));
        b3.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        b3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/clases/Images/analizar.png"))); // NOI18N
        b3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        b3.setSelected(true);
        b3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b3ActionPerformed(evt);
            }
        });

        b2.setBackground(new java.awt.Color(0, 51, 153));
        b2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/clases/Images/limpiar.png"))); // NOI18N
        b2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b2ActionPerformed(evt);
            }
        });

        b1.setBackground(new java.awt.Color(0, 51, 153));
        b1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/clases/Images/cerrar.png"))); // NOI18N
        b1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b1ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Calibri", 0, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("ANALIZADOR SEMÁNTICO P/LENGUAJE PROPIO ");

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Equipo #1");

        jLabel8.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Instituto Tecnológico de Pachuca");

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/clases/Images/logoITP.png"))); // NOI18N

        jButton1.setBackground(new java.awt.Color(0, 51, 153));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/clases/Images/marca-de-verificacion.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 51, 153));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/clases/Images/marca-de-verificacion.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 51, 153));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/clases/Images/cancelar.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(0, 51, 153));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/clases/Images/seleccionar.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        t5.setColumns(20);
        t5.setRows(5);
        jScrollPane5.setViewportView(t5);

        jLabel4.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("TABLA DE SÍMBOLOS");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(152, 152, 152))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane5)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(30, 30, 30)
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton3)))
                        .addGap(11, 11, 11)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(b3)
                        .addGap(29, 29, 29)
                        .addComponent(b2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(b1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addGap(21, 21, 21))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(171, 171, 171)
                .addComponent(jLabel8)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(129, 129, 129)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(130, 130, 130))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jButton3)
                                            .addComponent(jButton2)
                                            .addComponent(jButton1))
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton4))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(43, 43, 43)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(b2)
                                            .addComponent(b3)
                                            .addComponent(b1))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel8)))
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void b3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b3ActionPerformed
            lexico=new Lexico();
            sintactico=new Sintactico();
            char a[];            
            try {
                input = new BufferedReader(new StringReader(t1.getText()));
                String line;
                while((line=input.readLine())!=null){
                    line.replace("/s+",""); //Varios espacios combinados en uno solo
                    a = line.toCharArray(); //Una fila se convierte en una matriz
                    for (char c : a) 
                    {
                    //    System.out.println(c);
                        lexico.list1.offer(c);//Inserción de elementos en la cola                        
                    }
                    lexico.Analizar();
                }
            } catch (Exception ee) {
                ee.printStackTrace();
            }       
            //lexico.imprimeTablaTokens();
            //sintactico.imprimeTablaTokensSintac();
            sintactico.S();
            
    }//GEN-LAST:event_b3ActionPerformed

    private void b2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b2ActionPerformed
            t2.setText("");
            t4.setText("");
            t5.setText("");
            System.out.flush();
            lexico.reinicia();
    }//GEN-LAST:event_b2ActionPerformed

    private void b1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_b1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //Se invoca el método para cargar archivo 1
        //cargarArchivoCorrecto1("C:/Users/abime/Desktop/LENG/analizadorL-xico/src/clases/archivos/correcto1.txt");
        cargarArchivoCorrecto1("C:/xampp/htdocs/Git/analizadorL-xico/src/clases/archivos/correcto1.txt");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        //Se invoca el método para cargar archivo 1
        //cargarArchivoCorrecto2("C:/Users/abime/Desktop/LENG/analizadorL-xico/src/clases/archivos/correcto2.txt");
        cargarArchivoCorrecto2("C:/xampp/htdocs/Git/analizadorL-xico/src/clases/archivos/correcto2.txt");
    }//GEN-LAST:event_jButton2ActionPerformed
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        //Se invoca el método para cargar archivo 1
        //cargarArchivoIncorrecto("C:/Users/abime/Desktop/LENG/analizadorL-xico/src/clases/archivos/incorrecto.txt");
        cargarArchivoIncorrecto("C:/xampp/htdocs/Git/analizadorL-xico/src/clases/archivos/incorrecto.txt");

    }//GEN-LAST:event_jButton3ActionPerformed
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        t1.setText("");
        String aux = "";
        String texto = "";
        String resultado = "";
        
        try {
            JFileChooser file = new JFileChooser(System.getProperty("user.dir"));
            file.showOpenDialog(this);
            File archivo = file.getSelectedFile();;
            if (archivo != null) {
                FileReader archivos = new FileReader(archivo);
                BufferedReader leer = new BufferedReader(archivos);
                while ((aux = leer.readLine()) != null) {
                     texto += aux + "\n";
                }
            leer.close();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error Importando - " + ex);
        }
        t1.setText(texto);
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(LGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LGUI().setVisible(true);
            }
        });
        
    }
    public void cargarArchivoCorrecto1(String ruta1){
        t1.setText("");
        String aux = "";
        String texto = "";
            try {
                File archivo = new File (ruta1);
                if (archivo != null) {
                    FileReader archivos = new FileReader(archivo);
                    BufferedReader leer = new BufferedReader(archivos);
                    while ((aux = leer.readLine()) != null) {
                         texto += aux + "\n";
                    }
                leer.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error Importando - " + ex);
            }
            t1.setText(texto);
    }

    public void cargarArchivoCorrecto2(String ruta2){
        t1.setText("");
        String aux = "";
        String texto = "";
            try {
                File archivo = new File (ruta2);
                if (archivo != null) {
                    FileReader archivos = new FileReader(archivo);
                    BufferedReader leer = new BufferedReader(archivos);
                    while ((aux = leer.readLine()) != null) {
                         texto += aux + "\n";
                    }
                leer.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error Importando - " + ex);
            }
        t1.setText(texto);
    }
    public void cargarArchivoIncorrecto(String incorrecto){
        t1.setText("");
        String aux = "";
        String texto = "";
            try {
                File archivo = new File (incorrecto);
                if (archivo != null) {
                    FileReader archivos = new FileReader(archivo);
                    BufferedReader leer = new BufferedReader(archivos);
                    while ((aux = leer.readLine()) != null) {
                         texto += aux + "\n";
                    }
                leer.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error Importando - " + ex);
            }
        t1.setText(texto);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b1;
    private javax.swing.JButton b2;
    private javax.swing.JButton b3;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    static javax.swing.JTextArea t1;
    static javax.swing.JTextArea t2;
    static javax.swing.JTextArea t4;
    static javax.swing.JTextArea t5;
    // End of variables declaration//GEN-END:variables
}
