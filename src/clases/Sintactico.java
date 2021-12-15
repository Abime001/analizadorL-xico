/*
Archivo: Lexico.java
Materia: LENGUAJES Y AUTÓMATAS II
Programa: 3.2 Analizador Lexico Básico
Descripción: Se realiza el análisis léxico
Fecha: 30-Nov-2021
*/
package clases;
import java.security.Key;
//import java.util.LinkedList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Map.Entry;
//import javax.sound.sampled.LineUnavailableException;
public class Sintactico extends  Lexico {
    int line=0;
    Map <String, String> symbolTable = new HashMap<String, String>();

    public Sintactico(){

    }
    
    public void imprimeTablaTokensSintac(){
        System.out.println("\n\r Sintactico: Tokens guardados...\n\r");
        Object temp=token.getToken();               
        while(temp!=null){                 
            System.out.println("Lexema: "+token.getName()+" | "+token.getLine());
            temp=token.getToken();
        }
        token.reiniciaGet();
    }
    
    public void imprimeTablaTokensReverseSintac(){
        System.out.println("\n\r Sintactico Resverse: Tokens guardados...\n\r");
        Object temp=token.getLastToken();                
        while(temp!=null){                 
            System.out.println("+"+temp);
            temp=token.getLastToken();
        }
        token.reiniciaGetLast();
    }
        
    public void S() {
        
        line++;
        String state = "index";
        //Nodo cabeza = null;
        Object temp=token.getToken();
        Object line = token.getLine();
        Object temp2=token.getLastToken();
        
        boolean flag=false;
        while (temp!=null) {
            switch (state) {
                case "index": //Producción para tipo de dato Entero                
                    if(String.valueOf(temp) == "inicio_bloque"){
                        state="inicia";
                    }else if(String.valueOf(temp) == "fin_bloque"){
                        temp=token.getToken();
                    }else if(String.valueOf(temp) == "siguiente_bloque"){
                        temp=token.getToken();
                    }else if(String.valueOf(temp) == "#ENTERO"){//Verifica que el valor del token sea el esperado y avanza en la regla gramtical
                        state="entero";
                    }else if(String.valueOf(temp) == "#REAL"){
                        state="real";
                    }else if(String.valueOf(temp) == "#BOOL"){
                        state="bool";
                    }else if(String.valueOf(temp) == "#CAD"){
                        state="cad";
                    }else if(String.valueOf(temp) == "SI"){
                        state="si";
                    }else if(String.valueOf(temp) == "ENTONCES"){
                        state="entonces";
                    }else if(String.valueOf(temp) == "Imprimir"){
                        state="imprime";
                    }else if(String.valueOf(temp) == "SINO"){
                        state="sino";
                    }else if(String.valueOf(temp) == "MIENTRAS"){
                        state="mientras";
                    }else if(String.valueOf(temp) == "HACER"){
                        state="hacer";
                    }else if(String.valueOf(temp) == "Identificador"){// s = 5 * (a+b);
                        state="identificador";                        
                    }else if(String.valueOf(temp) == "Paren cierra"){// s = 5 * (a+b);
                        temp=token.getToken();
                    }else{      
                        LGUI.t4.append("Error con: "+temp+" en Linea "+token.getLine());
                        LGUI.t4.append("\r\n");                 
                        System.out.println("Error con: "+temp+" en Linea "+token.getLine());
                        temp=null;
                    }
                    
                    break;                    
                                            
                case "inicia":                            
                    if(String.valueOf(temp2)=="fin_bloque"){                            
                            temp=token.getToken();
                            state ="index";
                            flag=true;
                        }else{
                            LGUI.t4.append("Error al declarar bloque");
                            LGUI.t4.append("\r\n");  
                            System.out.println("error al declarar bloque");
                            temp=null;                   
                        }                        
                        break;                                           
                
                case "entero":    
                            temp = token.getToken();
                            if(String.valueOf(temp)=="Identificador"){
                                if(symbolTable.containsKey(token.getName())){
                                    line = token.getLine();
                                    //Error semantico
                                    System.out.println("Identificador duplicado " + line);
                                    temp = null;
                                    flag=false;
                                    break;
                                    
                                }else{
                                    symbolTable.put(String.valueOf(token.getName()), "0");
                                }
                                temp = token.getToken();
                                if(String.valueOf(temp)=="Punto y coma"){
                                    temp = token.getToken();
                                    state = "index";
                                    flag=true;
                                }else{ 
                                    LGUI.t4.append("Error al declarar Sentencia. Linea: " + line);
                                    LGUI.t4.append("\r\n");  
                                    System.out.println("Error al declarar Sentencia. Linea: " + line);
                                    temp=null;
                                }
                            }else{
                                LGUI.t4.append("falta identificador en variable. Linea: " + line);
                                LGUI.t4.append("\r\n");  
                                line = token.getLine();
                                System.out.println("falta identificador en variable. Linea: " + line);
                                temp=null;
                            }                        
                            break;
                    
                case "real"://Producción para tipo de dato Real
                            temp=token.getToken();
                            if(String.valueOf(temp)=="Identificador"){
                                if(symbolTable.containsKey(token.getName())){
                                    line = token.getLine();
                                    //Error semantico en Real
                                    System.out.print("Identificador duplicado" + line);
                                    temp = null;
                                    flag = false;
                                    break;
                                }else{
                                    symbolTable.put(String.valueOf(token.getName()), "0");
                                }
                                temp=token.getToken();
                                if(String.valueOf(temp)=="Punto y coma"){
                                    temp = token.getToken();
                                    state ="index";
                                }else{
                                    LGUI.t4.append("Se esperaba ' ; '. Linea: " + line);
                                    LGUI.t4.append("\r\n");
                                    line = token.getLine(); 
                                    System.out.println("Se esperaba ' ; '. Linea: " + line);
                                    temp=null;
                                    flag=false;
                                    break;
                                }
                                /*if(String.valueOf(temp)=="Asignación"){
                                    temp=token.getToken();
                                    if(String.valueOf(temp) == "Valores numéricos"){
                                        temp=token.getToken();
                                        if(String.valueOf(temp) =="Punto y coma"){
                                            temp = token.getToken();
                                            state ="index";
                                        }
                                    }else{
                                        LGUI.t4.append("Se esperaba un número de tipo real. Linea: " + line);
                                        LGUI.t4.append("\r\n"); 
                                        line = token.getLine(); 
                                        System.out.println("Se esperaba un número de tipo real. Linea: " + line);
                                        temp=null;                                        
                                    }
                                }*/

                            } else{
                                LGUI.t4.append("falta identificador en variable. Linea: " + line);
                                LGUI.t4.append("\r\n"); 
                                line = token.getLine(); 
                                System.out.println("falta identificador en variable. Linea: " + line);
                                temp=null;
                            }                             
                           break;
    
                case "bool"://Producción para tipo de dato Booleano
                            temp=token.getToken();
                            if(String.valueOf(temp)=="Identificador"){
                                if(symbolTable.containsKey(token.getName())){
                                    line = token.getLine();
                                    //Error semantico en Bool
                                    System.out.print("Identificador duplicado" + line);
                                    temp=null;
                                    flag = false;
                                    break;
                                }else{
                                    symbolTable.put(String.valueOf(token.getName()), "TRUE");
                                }
                                temp=token.getToken();
                                if(String.valueOf(temp)=="Punto y coma"){
                                    temp = token.getToken();
                                    state = "index";
                                }/*else if(String.valueOf(temp)=="Asignación"){
                                    //System.out.println("Se esperaba ;" + line);
                                    temp=token.getToken();
                                    if(String.valueOf(temp) == "TRUE" || String.valueOf(temp) == "FALSE"){
                                        temp=token.getToken();
                                        if(String.valueOf(temp) =="Punto y coma"){
                                            temp = token.getToken();
                                            state = "index";
                                        }
                                    }else{
                                        LGUI.t4.append("Se esperaba true o false. Linea: " + line);
                                        LGUI.t4.append("\r\n"); 
                                        line = token.getLine(); 
                                        line = token.getLine(); 
                                        System.out.println("Se esperaba true o false. Linea: " + line);
                                        temp=null;
                                    }
                                }*/
                            } else{
                                LGUI.t4.append("falta identificador en variable. Linea: " + line);
                                LGUI.t4.append("\r\n"); 
                                line = token.getLine(); 
                                line = token.getLine(); 
                                System.out.println("falta identificador en variable. Linea: " + line);
                                temp=null;
                            }
                            break;
    
                case "cad": //Producción para tipo de dato Cadena
                            temp=token.getToken();
                            if(String.valueOf(temp)=="Identificador"){
                                if(symbolTable.containsKey(token.getName())){
                                    line = token.getLine();
                                    //Error semantico en Bool
                                    System.out.print("Identificador duplicado" + line);
                                    temp=null;
                                    flag = false;
                                    break;
                                }else{
                                    symbolTable.put(String.valueOf(token.getName()), "0");
                                }
                                temp=token.getToken();
                                if(String.valueOf(temp)=="Punto y coma"){
                                    temp = token.getToken();
                                    state = "index";
                                }else{ 
                                    LGUI.t4.append("Se esperaban comillas. Linea: " + line);
                                    LGUI.t4.append("\r\n");
                                    line = token.getLine(); 
                                    System.out.println("Se esperaban comillas. Linea: " + line);
                                    temp=null;   
                                }/*else if(String.valueOf(temp)=="Asignación"){
                                    //System.out.println("Se esperaba ;" + line);
                                    temp=token.getToken();


                                    if(String.valueOf(temp) == "COMILLAS DOBLES"){
                                        temp=token.getToken();
                                        if(String.valueOf(temp) =="Identificador"){
                                            temp=token.getToken();
                                            if(String.valueOf(temp) == "COMILLAS DOBLES"){
                                                temp=token.getToken();
                                                if(String.valueOf(temp)=="Punto y coma"){
                                                    temp = token.getToken();
                                                    state ="index";
                                                }
                                            }else{
                                                LGUI.t4.append("Se esperaban comillas. Linea: " + line);
                                                LGUI.t4.append("\r\n");
                                                line = token.getLine(); 
                                                System.out.println("Se esperaban comillas. Linea: " + line);
                                                temp=null;
                                            }
                                        }
                                    }else{
                                        LGUI.t4.append("Se esperaban comillas. Linea: " + line);
                                        LGUI.t4.append("\r\n");
                                        line = token.getLine(); 
                                        System.out.println("Se esperaban comillas. Linea: " + line);
                                        temp=null;
                                    }
                                }*/
                            } else{
                                LGUI.t4.append("falta identificador en variable. Linea: " + line);
                                LGUI.t4.append("\r\n");
                                line = token.getLine(); 
                                System.out.println("falta identificador en variable. Linea: " + line);
                                temp=null;
                            }               
                            break;
                        
                case "si"://Producción para sentencia IF
                            temp=token.getToken ();
                          if(String.valueOf(temp)=="Paren abre"){
                                temp=token.getToken();
                                if(String.valueOf(temp)=="Identificador" || String.valueOf(temp)=="Valores numéricos"){
                                    temp = token.getToken();
                                    if(String.valueOf(temp) == "Igualdad" || String.valueOf(temp) == "Mayor que" || String.valueOf(temp) == "Menor que" || String.valueOf(temp) == "Menor igual que" || String.valueOf(temp) == "Mayor igual que" || String.valueOf(temp) == "OR" || String.valueOf(temp) == "AND" || String.valueOf(temp) == "Negación" || String.valueOf(temp) == "Desigual"){
                                        temp = token.getToken();
                                        if(String.valueOf(temp) == "Identificador" || String.valueOf(temp)=="Valores numéricos"){
                                            temp = token.getToken();
                                            if(String.valueOf(temp) == "Paren cierra"){
                                                temp = token.getToken();
                                                state = "index";
                                            }
                                        }else{
                                            LGUI.t4.append("Error en condición, símbolo erroneo. Linea: " + line);
                                            LGUI.t4.append("\r\n");
                                            line = token.getLine();
                                            System.out.println("Error en condición, símbolo erroneo. Linea: " + line);
                                            temp=null;
                                        }
                                    }else{
                                        LGUI.t4.append("Error en condición. Linea: " + line);
                                        LGUI.t4.append("\r\n");
                                        line = token.getLine();
                                        System.out.println("Error en condición. Linea: " + line);
                                        temp=null;
                                    }
                                }else{
                                    LGUI.t4.append("Error en condición. Linea: " + line);
                                    LGUI.t4.append("\r\n");
                                    line = token.getLine();
                                    System.out.println("Error en condición. Linea: " + line);
                                    temp=null;
                                }
                            } else{
                                LGUI.t4.append("Se esperaba '('. Linea: " + line);
                                LGUI.t4.append("\r\n");
                                line = token.getLine(); 
                                System.out.println("Se esperaba '('. Linea: " + line);
                                temp=null;
                            }
                        break;
                        
                case "entonces": //Producción para sentenica Entonces
                            temp=token.getToken();
                            if(String.valueOf(temp)=="Paren abre"){
                                temp=token.getToken();
                                if(String.valueOf(temp) == "#Entero"){
                                    temp=token.getToken();
                                    state = "index";//Manda a la sentencia de tipo de dato
                                }else if(String.valueOf(temp) == "#Real"){
                                    temp=token.getToken();
                                    state = "index"; //Manda a la sentencia de tipo de dato
                                }else if(String.valueOf(temp) == "#Cad"){
                                    temp=token.getToken();
                                    state = "index";//Manda a la sentencia de tipo de dato
                                }else if(String.valueOf(temp) == "#Bool"){
                                    temp=token.getToken();
                                    state = "index";//Manda a la sentencia de tipo de dato
                                }else if(String.valueOf(temp) == "Imprimir"){
                                    temp=token.getToken();
                                    state = "index";//Manda a la sentencia Imprimir
                                }else if(String.valueOf(temp) == "Identificador"){
                                    temp=token.getToken();
                                    state = "index"; //Manda a la sentencia de asignación
                                }
                                //Falta para otro bloquue Inicia continua
                                else if(String.valueOf(temp) == "Paren cierra"){
                                    temp = token.getToken();
                                    state = "index";
                                }else{
                                    LGUI.t4.append("Se esperaba ')' Linea: " + line);
                                    LGUI.t4.append("\r\n");
                                    line = token.getLine(); 
                                    System.out.println("Se esperaba ')'. Linea: " + line);
                                    temp=null;
                                }
                            } else{
                                LGUI.t4.append("Se esperaba '(' Linea: " + line);
                                LGUI.t4.append("\r\n");
                                line = token.getLine(); 
                                System.out.println("Se esperaba '('. Linea: " + line);
                                temp=null;
                            }                                             
                        break;
                        
                case "imprime": //Producción para sentencia Imprime
                            temp = token.getToken();
                            if(String.valueOf(temp) == "COMILLAS DOBLES"){
                                temp = token.getToken();
                                if(String.valueOf(temp) == "Palabra clave" || String.valueOf(temp) == "Valores numéricos" || String.valueOf(temp) == "Identificador"){
                                    temp = token.getToken();
                                    if(String.valueOf(temp) == "Punto y coma"){
                                        temp = token.getToken();
                                        state = "index";
                                    }else{
                                        LGUI.t4.append("Se esperaba ';' Linea: " + line);
                                        LGUI.t4.append("\r\n");
                                        line = token.getLine(); 
                                        System.out.println("Se esperaba ' ; '. Linea: " + line);
                                        temp=null;
                                    }
                                }else{
                                    LGUI.t4.append("Error el imprimir. Linea: " + line);
                                    LGUI.t4.append("\r\n");
                                    line = token.getLine(); 
                                    System.out.println("Error el imprimir. Linea: " + line);
                                    temp=null;
                                }
                            }else if (String.valueOf(temp) == "Identificador"){
                                if(symbolTable.containsKey(token.getName())){
                                    String getValueFromKey = getSingleValueFromKey(symbolTable, token.getName());
                                    System.out.println("Variable  = " + getValueFromKey);
                                    
                                }else{
                                    line = token.getLine();
                                    //Error semantico en Bool
                                    System.out.print("Variable no declarada" + line);
                                    temp=null;
                                    flag = false;
                                    break;
                                }
                                temp = token.getToken();
                                if(String.valueOf(temp) == "Punto y coma"){
                                    temp = token.getToken();
                                    state = "index";
                                }else{
                                    LGUI.t4.append("Se esperaba ' ; '. Linea: " + line);
                                    LGUI.t4.append("\r\n");
                                    line = token.getLine(); 
                                    System.out.println("Se esperaba ' ; '. Linea: " + line);
                                    temp=null;
                                }
                            }else if(String.valueOf(temp) == "Valores numéricos"){
                                System.out.println("Imprime = " + token.getName());
                                temp = token.getToken();
                                state = "index";
                                flag=true;

                            }else{
                                LGUI.t4.append("Se esperaba ' un valor '. Linea: " + line);
                                LGUI.t4.append("\r\n");
                                line = token.getLine(); 
                                System.out.println("Se esperaba ' un valor '. Linea: " + line);
                                temp=null;
                            }
                        break;
                        
                case "sino"://Producción para sentenica ELSE
                            temp=token.getToken();
                            if(String.valueOf(temp) == "Paren abre"){
                                temp=token.getToken();
                                if(String.valueOf(temp) == "#Entero"){
                                    temp=token.getToken();
                                    state = "index";//Manda a la sentencia de tipo de dato
                                }else if(String.valueOf(temp) == "#Real"){
                                    temp=token.getToken();
                                    state = "index"; //Manda a la sentencia de tipo de dato
                                }else if(String.valueOf(temp) == "#Cad"){
                                    temp=token.getToken();
                                    state = "index";//Manda a la sentencia de tipo de dato
                                }else if(String.valueOf(temp) == "#Bool"){
                                    temp=token.getToken();
                                    state = "index";//Manda a la sentencia de tipo de dato
                                }else if(String.valueOf(temp) == "Imprimir"){
                                    temp=token.getToken();
                                    state = "index";//Manda a la sentencia Imprimir
                                }else if(String.valueOf(temp) == "Identificador"){
                                    temp=token.getToken();
                                    state = "index"; //Manda a la sentencia de asignación
                                }
                                //Falta para otro bloquue Inicia continua
                                else if(String.valueOf(temp) == "Paren cierra"){
                                    temp = token.getToken();
                                    state = "index";
                                }else{
                                    LGUI.t4.append("Se esperaba ' ) '. Linea: " + line);
                                    LGUI.t4.append("\r\n");
                                    line = token.getLine(); 
                                    System.out.println("Se esperaba ')'. Linea: " + line);
                                    temp=null;
                                }

                            }else{
                                LGUI.t4.append("Se esperaba '('. Linea: " + line);
                                LGUI.t4.append("\r\n");
                                line = token.getLine();
                                System.out.println("Se esperaba '('. Linea: " + line);
                                temp=null;
                            }
                        break;
                        
                case "while"://Producción para sentencia While
                            if(String.valueOf(temp) == "Paren abre"){
                                temp = token.getToken();
                                if(String.valueOf(temp)=="Identificador" || String.valueOf(temp)=="Valores numéricos"){
                                    temp = token.getToken();
                                    if(String.valueOf(temp) == "Igualdad" || String.valueOf(temp) == "Mayor que" || String.valueOf(temp) == "Menor que" || String.valueOf(temp) == "Menor igual que" || String.valueOf(temp) == "Mayor igual que" || String.valueOf(temp) == "OR" || String.valueOf(temp) == "AND" || String.valueOf(temp) == "Negación" || String.valueOf(temp) == "Desigual"){
                                        temp = token.getToken();
                                        if(String.valueOf(temp) == "Identificador" || String.valueOf(temp)=="Valores numéricos"){
                                            temp = token.getToken();
                                            if(String.valueOf(temp) == "Paren cierra"){
                                                temp = token.getToken();
                                                state = "index";
                                            }
                                        }else{
                                            LGUI.t4.append("Error en condición, símbolo erroneo. Linea: " + line);
                                            LGUI.t4.append("\r\n");
                                            line = token.getLine();
                                            System.out.println("Error en condición, símbolo erroneo. Linea: " + line);
                                            temp=null;
                                        }
                                    }else{
                                        LGUI.t4.append("Error en condición. Linea: " + line);
                                        LGUI.t4.append("\r\n");
                                        line = token.getLine();
                                        System.out.println("Error en condición. Linea: " + line);
                                        temp=null;
                                    }
                                }else{
                                    LGUI.t4.append("Error en condición. Linea: " + line);
                                    LGUI.t4.append("\r\n");
                                    line = token.getLine();
                                    System.out.println("Error en condición. Linea: " + line);
                                    temp=null;
                                }
                                
                            }else{
                                LGUI.t4.append("Se esperaba '('. Linea: " + line);
                                LGUI.t4.append("\r\n");
                                line = token.getLine();
                                System.out.println("Se esperaba '('. Linea: " + line);
                                temp=null;
                            }
                        break;
                        
                case "hacer": //Producción para Hacer
                         if(String.valueOf(temp) == "Paren abre"){
                                temp=token.getToken();
                                if(String.valueOf(temp) == "#entero"){
                                    temp=token.getToken();
                                    state = "index";//Manda a la sentencia de tipo de dato
                                }else if(String.valueOf(temp) == "#REAL"){
                                    temp=token.getToken();
                                    state = "index"; //Manda a la sentencia de tipo de dato
                                }else if(String.valueOf(temp) == "#CAD"){
                                    temp=token.getToken();
                                    state = "index";//Manda a la sentencia de tipo de dato
                                }else if(String.valueOf(temp) == "#BOOL"){
                                    temp=token.getToken();
                                    state = "index";//Manda a la sentencia de tipo de dato
                                }else if(String.valueOf(temp) == "IMPRIME"){
                                    temp=token.getToken();
                                    state = "index";//Manda a la sentencia Imprimir
                                }else if(String.valueOf(temp) == "Identificador"){
                                    temp=token.getToken();
                                    state = "index"; //Manda a la sentencia de asignación
                                }
                                //Falta para otro bloquue Inicia continua
                                else if(String.valueOf(temp) == "Paren cierra"){
                                    temp = token.getToken();
                                    state = "index";
                                }else{
                                    LGUI.t4.append("Se esperaba ')'. Linea: " + line);
                                    LGUI.t4.append("\r\n");
                                    line = token.getLine();
                                    System.out.println("Se esperaba ')'. Linea: " + line);
                                    temp=null;
                                }

                            }else{
                                LGUI.t4.append("Se esperaba '('. Linea: " + line);
                                LGUI.t4.append("\r\n");
                                line = token.getLine();
                                System.out.println("Se esperaba '('. Linea: " + line);
                                temp=null;
                            }
                        break;
                
                case "identificador": //Producción para asignación
                            String auxName = "";
                            int auxValue=0;
                            int aux3=0;
                            int aux4=0;
                            int res = 0;
                            if(symbolTable.containsKey(token.getName())){
                                String getValueFromKey = getSingleValueFromKey(symbolTable, token.getName());
                                auxName = token.getName();
                                auxValue = Integer.parseInt(getValueFromKey);
                                temp = token.getToken();
                            }else{
                                line = token.getLine();
                                //Error semantico en Bool
                                System.out.print("Variable no declarada" + line);
                                temp=null;
                                flag = false;
                                break;
                            }
                            
                            if(String.valueOf(temp) == "Asignación"){
                                temp = token.getToken();
                                if(String.valueOf(temp) == "Identificador"){
                                    if(symbolTable.containsKey(token.getName())){
                                        String getValueFromKey = getSingleValueFromKey(symbolTable, token.getName());
                                        aux3 = Integer.parseInt(getValueFromKey);
                                        symbolTable.put(auxName, getValueFromKey);
                                        temp = token.getToken();
                                    }else{
                                        line = token.getLine();
                                        //Error semantico en Bool
                                        System.out.print("Variable no declarada" + line);
                                        temp=null;
                                        flag = false;
                                        break;
                                    }
                                    if(String.valueOf(temp) == "Punto y coma"){
                                        temp = token.getToken();
                                        state = "index";
                                        flag=true;
                                    }else if(String.valueOf(temp) == "Operador suma" || String.valueOf(temp) == "Operador resta" ||  String.valueOf(temp) == "Operador multiplicación" || String.valueOf(temp) == "Operador división"){
                                        String auxOpe =String.valueOf(temp);
                                        temp = token.getToken();
                                        if(String.valueOf(temp) == "Identificador"){
                                            if(symbolTable.containsKey(token.getName())){
                                                String getValueFromKey = getSingleValueFromKey(symbolTable, token.getName());
                                                aux4 = Integer.parseInt(getValueFromKey);
                                                if(auxOpe == "+"){
                                                    res = auxValue + aux4;
                                                }else if(auxOpe == "-"){
                                                    res = auxValue - aux4;
                                                } else if(auxOpe == "*"){
                                                    res = auxValue * aux4;
                                                }else if(auxOpe == "/"){
                                                    res = auxValue / aux4;
                                                }
                                                symbolTable.put(auxName, String.valueOf(res));
                                                temp = token.getToken();
                                                if(String.valueOf(temp) == "Punto y coma"){
                                                    temp = token.getToken();
                                                    state = "index";
                                                    flag=true;
                                                }else{
                                                    LGUI.t4.append("Se esperaba ';'. Linea: " + line);
                                                    LGUI.t4.append("\r\n");
                                                    line = token.getLine();
                                                    System.out.println("Se esperaba ';'. Linea: " + line);
                                                    temp=null;
                                                    break;
                                                }
                                            }else{
                                                line = token.getLine();
                                                //Error semantico en Bool
                                                System.out.print("Variable no declarada" + line);
                                                temp=null;
                                                flag = false;
                                                break;
                                            }
                                   
                                        }else if(String.valueOf(temp) == "Valores númerico"){
                                            aux4 = Integer.parseInt(token.getName());
                                            if(auxOpe == "+"){
                                                res = auxValue + aux4;
                                            }else if(auxOpe == "-"){
                                                res = auxValue - aux4;
                                            } else if(auxOpe == "*"){
                                                res = auxValue * aux4;
                                            }else if(auxOpe == "/"){
                                                res = auxValue / aux4;
                                            }
                                            symbolTable.put(auxName, String.valueOf(res));
                                            temp = token.getToken();
                                            if(String.valueOf(temp) == "Punto y coma"){
                                                temp = token.getToken();
                                                state = "index";
                                                flag=true;
                                            }else{
                                                LGUI.t4.append("Se esperaba ';'. Linea: " + line);
                                                LGUI.t4.append("\r\n");
                                                line = token.getLine();
                                                System.out.println("Se esperaba ';'. Linea: " + line);
                                                temp=null;
                                                break;
                                            }
                                        }else{
                                            LGUI.t4.append("Se esperaba un valor. Linea: " + line);
                                            LGUI.t4.append("\r\n");
                                            line = token.getLine();
                                            System.out.println("Se esperaba un valor. Linea: " + line);
                                            temp=null;
                                            flag=false;
                                        }
                                    }else{
                                        LGUI.t4.append("Se esperaba un ' operador '. Linea: " + line);
                                        LGUI.t4.append("\r\n");
                                        line = token.getLine();
                                        System.out.println("Se esperaba ' operador '. Linea: " + line);
                                        temp=null;
                                        flag=false;
                                        break;
                                    }
                                }else if(String.valueOf(temp)== "Valores númericos"){
                                    //aux3 = Integer.parseInt(token.getName());
                                    symbolTable.put(auxName, token.getName());
                                    temp = token.getToken();
                                    if(String.valueOf(temp) == "Punto y coma"){
                                        temp = token.getToken();
                                        state = "index";
                                        flag=true;
                                    }else if(String.valueOf(temp) == "Operador suma" || String.valueOf(temp) == "Operador resta" ||  String.valueOf(temp) == "Operador multiplicación" || String.valueOf(temp) == "Operador división"){
                                        String auxOpe =String.valueOf(temp);
                                        temp = token.getToken();
                                        if(String.valueOf(temp) == "Identificador"){
                                            if(symbolTable.containsKey(token.getName())){
                                                String getValueFromKey = getSingleValueFromKey(symbolTable, token.getName());
                                                aux4 = Integer.parseInt(getValueFromKey);
                                                if(auxOpe == "+"){
                                                    res = auxValue + aux4;
                                                }else if(auxOpe == "-"){
                                                    res = auxValue - aux4;
                                                } else if(auxOpe == "*"){
                                                    res = auxValue * aux4;
                                                }else if(auxOpe == "/"){
                                                    res = auxValue / aux4;
                                                }
                                                symbolTable.put(auxName, String.valueOf(res));
                                                temp = token.getToken();
                                                if(String.valueOf(temp) == "Punto y coma"){
                                                    temp = token.getToken();
                                                    state = "index";
                                                    flag=true;
                                                }else{
                                                    LGUI.t4.append("Se esperaba ';'. Linea: " + line);
                                                    LGUI.t4.append("\r\n");
                                                    line = token.getLine();
                                                    System.out.println("Se esperaba ';'. Linea: " + line);
                                                    temp=null;
                                                    break;
                                                }
                                            }else{
                                                line = token.getLine();
                                                //Error semantico en Bool
                                                System.out.print("Variable no declarada" + line);
                                                temp=null;
                                                flag = false;
                                                break;
                                            }
                                   
                                        }else if(String.valueOf(temp) == "Valores númerico"){
                                            aux4 = Integer.parseInt(token.getName());
                                            if(auxOpe == "+"){
                                                res = auxValue + aux4;
                                            }else if(auxOpe == "-"){
                                                res = auxValue - aux4;
                                            } else if(auxOpe == "*"){
                                                res = auxValue * aux4;
                                            }else if(auxOpe == "/"){
                                                res = auxValue / aux4;
                                            }
                                            symbolTable.put(auxName, String.valueOf(res));
                                            temp = token.getToken();
                                            if(String.valueOf(temp) == "Punto y coma"){
                                                temp = token.getToken();
                                                state = "index";
                                                flag=true;
                                            }else{
                                                LGUI.t4.append("Se esperaba ';'. Linea: " + line);
                                                LGUI.t4.append("\r\n");
                                                line = token.getLine();
                                                System.out.println("Se esperaba ';'. Linea: " + line);
                                                temp=null;
                                                break;
                                            }
                                        }else{
                                            LGUI.t4.append("Se esperaba un valor. Linea: " + line);
                                            LGUI.t4.append("\r\n");
                                            line = token.getLine();
                                            System.out.println("Se esperaba un valor. Linea: " + line);
                                            temp=null;
                                            flag=false;
                                        }
                                    }else{
                                        LGUI.t4.append("Se esperaba ';'. Linea: " + line);
                                        LGUI.t4.append("\r\n");
                                        line = token.getLine();
                                        System.out.println("Se esperaba ';'. Linea: " + line);
                                        temp=null;
                                        flag=false;
                                        break;
                                    }

                                }
                            }else{
                                LGUI.t4.append("Se esperaba '='. Linea: " + line);
                                LGUI.t4.append("\r\n");                           
                                line = token.getLine();
                                System.out.println("Se esperaba '='. Linea: " + line);
                                temp=null;
                                flag = false;
                            }                
                        break;
            }
                       
        }
        if(flag==true){
            System.out.println("\033[32m BUILD SUCCESS ");
            LGUI.t4.append("\033[32m  BUILD SUCCESS!");
            LGUI.t4.append("\r\n");
        }else{
            System.out.println("ERROR AL COMPILAR");
            LGUI.t4.append("ERROR AL COMPILAR");
            LGUI.t4.append("\r\n");
        }
        for (Map.Entry<String, String> entry : symbolTable.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
    }

    public static <K, V> V getSingleValueFromKey(Map<K, V> map, K key) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (Objects.equals(key, entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }   
}
