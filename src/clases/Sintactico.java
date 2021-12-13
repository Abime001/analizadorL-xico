/*
Archivo: Lexico.java
Materia: LENGUAJES Y AUTÓMATAS II
Programa: 3.2 Analizador Lexico Básico
Descripción: Se realiza el análisis léxico
Fecha: 30-Nov-2021
*/
package clases;
import java.util.LinkedList;
public class Sintactico extends  Lexico {
    int line=0;
    
    public Sintactico(){
    }
    
    public void imprimeTablaTokens(){
        System.out.println("\n\r Sintactico: Tokens guardados...\n\r");
        Object temp=token.getToken();                
        while(temp!=null){                 
            System.out.println("-"+temp);
            temp=token.getToken();
        }
        token.reiniciaGet();
    }
    
    public void imprimeTablaTokensReverse(){
        System.out.println("\n\r Sintactico Resverse: Tokens guardados...\n\r");
        Object temp=token.getLastToken();                
        while(temp!=null){                 
            System.out.println("+"+temp);
            temp=token.getLastToken();
        }
        token.reiniciaGetLast();
    }
        
    public void Analizar() {
        line++;
        int state = 0;
        Object temp=token.getToken();
        Object temp2=token.getLastToken();
        while (temp!=null) {
            switch (state) {
                case 0:
                        if(String.valueOf(temp)=="inicia" && String.valueOf(temp2)=="termina"){
                            System.out.println("okis");
                            state=1;
                        } 
                        temp=token.getToken();
                        break;
                        
                case 1:                    
                        
                        System.out.println("hola");                      
                        temp=token.getToken();
                        break;
            }
        }
    }
        
    
}
