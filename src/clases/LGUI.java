/*
Archivo: LGUI.java
Materia: LENGUAJES Y AUTÓMATAS II
Programa: 3.2 Analizador Lexico Básico
Descripción: Se crea la Interfaz gráfica de usuario y se le asignan los métodos a los botones correspondientes
Fecha: 30-Nov-2021
*/
package clases;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.StringReader;

public class LGUI extends JFrame {
    JTextArea t1;
    static JTextArea t2;
    JButton b1;
        ImageIcon btnCerrar = new ImageIcon("src/clases/Images/cerrar.png");
    JButton b2;
        ImageIcon btnAnalizar = new ImageIcon("src/clases/Images/analizar.png");
    JButton b3;
        ImageIcon btnLimpiar = new ImageIcon("src/clases/Images/limpiar.png");
    JPanel jp1;
    JPanel jpc1;
    JPanel jph1;
    JPanel jph2;
    JLabel lb1;
    JLabel lb2;
    BufferedReader input = null;
    Lexico lexico;

    public LGUI() {
        JFrame jf = new JFrame();
        jf.setTitle("Analizador Léxico Básico - Equipo 1");
        Container c = jf.getContentPane();
        c.setBackground(Color.black);
        t1 = new JTextArea(10, 35);
        t2 = new JTextArea(10, 35);
        lb1 = new JLabel("Ingresa el texto a analizar");
        lb1.setForeground(Color.WHITE);
        lb2 = new JLabel("Resultado del análisis léxico", SwingConstants.CENTER);
        lb2.setForeground(Color.WHITE);
        JScrollPane js1=new JScrollPane(t1);
        JScrollPane js2=new JScrollPane(t2);
        js1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        js2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        t1.setLineWrap(true);
        t1.setSize(400, 300);
        t2.setSize(400, 300);
        b1 = new JButton(" CERRAR");
            b1.setIcon(btnCerrar);
            b1.setIconTextGap(2);
            b1.setHorizontalAlignment(SwingConstants.CENTER);
            b1.setVerticalAlignment(SwingConstants.CENTER);
        b2 = new JButton(" ANALIZAR");
            b2.setIcon(btnAnalizar);
            b2.setIconTextGap(2);
            b2.setHorizontalAlignment(SwingConstants.CENTER);
            b2.setVerticalAlignment(SwingConstants.CENTER);
        b3=new JButton(" LIMPIAR");
            b3.setIcon(btnLimpiar);
            b3.setIconTextGap(2);
            b3.setHorizontalAlignment(SwingConstants.CENTER);
            b3.setVerticalAlignment(SwingConstants.CENTER);
        b2.addActionListener(this::actionPerformed);
        b1.addActionListener(this::actionPerformed);
        b3.addActionListener(this::actionPerformed);
        jpc1 = new JPanel();
        this.jpc1.setBackground(Color.black);
        jph1 = new JPanel();
            jph1.add(lb1);
            this.jph1.setBorder(new EmptyBorder(0, 0, 0, 200));
            this.jph1.setBackground(Color.black);
        jph2 = new JPanel();
            jph2.add(lb2);
            this.jph2.setBorder(new EmptyBorder(0, 100, 0, 0));
            this.jph2.setBackground(Color.black);
        jpc1.add(jph1);
        jpc1.add(jph2);
        jp1 = new JPanel();
        this.jp1.setBackground(Color.black);
        jp1.add(b2);
        jp1.add(b3);
        jp1.add(b1);
        c.add(js1, BorderLayout.WEST);
        c.add(js2, BorderLayout.EAST);
        c.add(jp1, BorderLayout.SOUTH);
        c.add(jpc1, BorderLayout.NORTH);
        jf.setSize(850, 400);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
        jf.setLocation(250, 200);
    }

    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == b1) {
            System.exit(0);
        }
        if(obj==b3){ //LIMPIAR
           // t1.setText("");
            t2.setText("");
        }
        if (obj == b2) { //El proceso de análisis se describe en análisis
            lexico=new Lexico();
            char a[];            
            try {
                input = new BufferedReader(new StringReader(t1.getText()));
                String line;
                while((line=input.readLine())!=null){
                    line.replace("\\s+",""); //Varios espacios combinados en uno solo
                    a = line.toCharArray(); //Una fila se convierte en una matriz
                    for (char c : a) 
                    {
                    //    System.out.println(c);
                        lexico.list1.offer(c);//Inserción de elementos en la cola                        
                    }
                    lexico.list1.offer('°');
                  //  System.out.println(lexico.list1.toString());
                    lexico.Analizar();
                }
            } catch (Exception ee) {
                ee.printStackTrace();
            }       
            lexico.imprimeTablaTokens();
        }
    }
}
