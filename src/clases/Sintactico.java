/*
Archivo: Lexico.java
Materia: LENGUAJES Y AUTÓMATAS II
Programa: 3.2 Analizador Lexico Básico
Descripción: Se realiza el análisis léxico
Fecha: 30-Nov-2021
*/
package clases;
import java.util.LinkedList;

import javax.sound.sampled.LineUnavailableException;
public class Sintactico extends  Lexico {
    int line=0;
    
    public Sintactico(){
    }
    
    public void imprimeTablaTokensSintac(){
        System.out.println("\n\r Sintactico: Tokens guardados...\n\r");
        Object temp=token.getToken();                
        while(temp!=null){                 
            System.out.println("-"+temp);
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
        
    public void Analizar() {
        line++;
        String state = "index";
        Nodo cabeza = null;
        Object temp=token.getToken();
        Object line = token.getLine();
        Object temp2=token.getLastToken();
        
        boolean flag=false;
        while (temp!=null) {
            switch (state) {
                case "index": //Producción para tipo de dato Entero                
                    if(String.valueOf(temp) == "inicio_bloque"){
                        state="inicia";
                    }else if(String.valueOf(temp) == "termina_bloque"){
                        temp=token.getToken();
                    }else if(String.valueOf(temp) == "continua"){
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
                    }else{                        
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
                            System.out.println("error al declarar bloque");
                            temp=null;                   
                        }                        
                        break;                                           
                
                case "entero":    
                            temp = token.getToken();
                            if(String.valueOf(temp)=="Identificador"){
                                temp = token.getToken();
                                if(String.valueOf(temp)=="Punto y coma"){
                                    temp = token.getToken();
                                    state = "index";
                                    System.out.println("Entero cool sin asignación");
                                }else if(String.valueOf(temp)=="Asignación"){
                                    //System.out.println("Se esperaba ;" + line);
                                    temp = token.getToken();
                                    if(String.valueOf(temp) == "Valores numéricos"){
                                        temp = token.getToken();
                                        if(String.valueOf(temp) =="Punto y coma"){
                                            temp = token.getToken();
                                            state = "index";
                                            flag=true;
                                            System.out.println("Entero cool con asignación");     
                                        }

                                    }else{
                                        line = token.getLine(); 
                                        System.out.println("Se esperaba un número entero. Linea: " + line);
                                        temp=null;
                                    }
                                }
                                else{ 
                                System.out.println("Error al declarar Sentencia. Linea: " + line);
                                temp=null;
                                }
                            } else{
                                line = token.getLine();
                                System.out.println("falta identificador en variable. Linea: " + line);
                                temp=null;
                            }                        
                            break;
                    
                case "real"://Producción para tipo de dato Real
                            temp=token.getToken();
                            if(String.valueOf(temp)=="Identificador"){
                                temp=token.getToken();
                                if(String.valueOf(temp)=="Punto y coma"){
                                    temp = token.getToken();
                                    state ="index";
                                }else if(String.valueOf(temp)=="Asignación"){
                                    temp=token.getToken();
                                    if(String.valueOf(temp) == "Valores numéricos"){
                                        temp=token.getToken();
                                        if(String.valueOf(temp) =="Punto y coma"){
                                            temp = token.getToken();
                                            state ="index";
                                        }
                                    }else{
                                        line = token.getLine(); 
                                        System.out.println("Se esperaba un número de tipo real. Linea: " + line);
                                        temp=null;                                        
                                    }
                                }

                            } else{
                                line = token.getLine(); 
                                System.out.println("falta identificador en variable. Linea: " + line);
                                temp=null;
                            }                             
                           break;
    
                case "bool"://Producción para tipo de dato Booleano
                            temp=token.getToken();
                            if(String.valueOf(temp)=="Identificador"){
                                temp=token.getToken();
                                if(String.valueOf(temp)=="Punto y coma"){
                                    temp = token.getToken();
                                    state = "index";
                                }else if(String.valueOf(temp)=="Asignación"){
                                    //System.out.println("Se esperaba ;" + line);
                                    temp=token.getToken();
                                    if(String.valueOf(temp) == "TRUE" || String.valueOf(temp) == "FALSE"){
                                        temp=token.getToken();
                                        if(String.valueOf(temp) =="Punto y coma"){
                                            temp = token.getToken();
                                            state = "index";
                                        }
                                    }else{
                                        line = token.getLine(); 
                                        System.out.println("Se esperaba true o false. Linea: " + line);
                                        temp=null;
                                    }
                                }
                            } else{
                                line = token.getLine(); 
                                System.out.println("falta identificador en variable. Linea: " + line);
                                temp=null;
                            }
                            break;
    
                case "cad": //Producción para tipo de dato Cadena
                            temp=token.getToken();
                            if(String.valueOf(temp)=="Identificador"){
                                temp=token.getToken();
                                if(String.valueOf(temp)=="Punto y coma"){
                                    temp = token.getToken();
                                    state = "index";
                                }else if(String.valueOf(temp)=="Asignación"){
                                    //System.out.println("Se esperaba ;" + line);
                                    temp=token.getToken();
                                    if(String.valueOf(temp) == "COMILLAS DOBLES"){
                                        temp=token.getToken();
                                        if(String.valueOf(temp) =="identificador"){
                                            temp=token.getToken();
                                            if(String.valueOf(temp) == "COMILLAS DOBLES"){
                                                temp=token.getToken();
                                                if(String.valueOf(temp)=="Punto y coma"){
                                                    temp = token.getToken();
                                                    state ="index";
                                                }
                                            }else{
                                                line = token.getLine(); 
                                                System.out.println("Se esperaban comillas. Linea: " + line);
                                                temp=null;
                                            }
                                        }
                                    }else{
                                        line = token.getLine(); 
                                        System.out.println("Se esperaban comillas. Linea: " + line);
                                        temp=null;
                                    }
                                }
                            } else{
                                line = token.getLine(); 
                                System.out.println("falta identificador en variable. Linea: " + line);
                                temp=null;
                            }               
                            break;
                        
                case "si"://Producción para sentencia IF
                          if(String.valueOf(temp)=="Paren abre"){
                                temp=token.getToken();
                                if(String.valueOf(temp)=="Identificador" || String.valueOf(temp)=="Valor númerico"){
                                    temp = token.getToken();
                                    if(String.valueOf(temp) == "Igualdad" || String.valueOf(temp) == "Mayor que" || String.valueOf(temp) == "Menor que" || String.valueOf(temp) == "Menor igual que" || String.valueOf(temp) == "Mayor igual que" || String.valueOf(temp) == "OR" || String.valueOf(temp) == "AND" || String.valueOf(temp) == "Negación" || String.valueOf(temp) == "Desigual"){
                                        temp = token.getToken();
                                        if(String.valueOf(temp) == "Identificador" || String.valueOf(temp)=="Valor númerico"){
                                            temp = token.getToken();
                                            if(String.valueOf(temp) == "Paren cierra"){
                                                temp = token.getToken();
                                                state = "index";
                                            }
                                        }else{
                                            line = token.getLine();
                                            System.out.println("Error en condición, símbolo erroneo. Linea: " + line);
                                            temp=null;
                                        }
                                    }else{
                                        line = token.getLine();
                                        System.out.println("Error en condición. Linea: " + line);
                                        temp=null;
                                    }
                                }else{
                                    line = token.getLine();
                                    System.out.println("Error en condición. Linea: " + line);
                                    temp=null;
                                }
                            } else{
                                line = token.getLine(); 
                                System.out.println("Se esperaba '('. Linea: " + line);
                                temp=null;
                            }
                        break;
                        
                case "entonces": //Producción para sentenica Entonces
                            if(String.valueOf(temp)=="Paren abre"){
                                temp=token.getToken();
                                if(String.valueOf(temp) == "#entero"){
                                    temp=token.getToken();
                                    state = "index";//Manda a la sentencia de tipo de dato
                                }else if(String.valueOf(temp) == "#real"){
                                    temp=token.getToken();
                                    state = "index"; //Manda a la sentencia de tipo de dato
                                }else if(String.valueOf(temp) == "#cad"){
                                    temp=token.getToken();
                                    state = "index";//Manda a la sentencia de tipo de dato
                                }else if(String.valueOf(temp) == "#bool"){
                                    temp=token.getToken();
                                    state = "index";//Manda a la sentencia de tipo de dato
                                }else if(String.valueOf(temp) == "Imprime"){
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
                                    line = token.getLine(); 
                                    System.out.println("Se esperaba ')'. Linea: " + line);
                                    temp=null;
                                }
                            } else{
                                line = token.getLine(); 
                                System.out.println("Se esperaba '('. Linea: " + line);
                                temp=null;
                            }                                             
                        break;
                        
                case "imprime": //Producción para sentencia Imprime
                            temp = token.getToken();
                            if(String.valueOf(temp) == "COMILLAS DOBLES"){
                                temp = token.getToken();
                                if(String.valueOf(temp) == "Palabra clave" || String.valueOf(temp) == "Valor númerico" || String.valueOf(temp) == "Identificador"){
                                    temp = token.getToken();
                                    if(String.valueOf(temp) == "Punto y coma"){
                                        temp = token.getToken();
                                        state = "index";
                                    }else{
                                        line = token.getLine(); 
                                        System.out.println("Se esperaba ' ; '. Linea: " + line);
                                        temp=null;
                                    }
                                }else{
                                    line = token.getLine(); 
                                    System.out.println("Error el imprimir. Linea: " + line);
                                    temp=null;
                                }
                            }else if (String.valueOf(temp) == "Palabra clave" || String.valueOf(temp) == "Valor númerico" || String.valueOf(temp) == "Identificador"){
                                temp = token.getToken();
                                if(String.valueOf(temp) == "Punto y coma"){
                                    temp = token.getToken();
                                    state = "index";
                                }else{
                                    line = token.getLine(); 
                                    System.out.println("Se esperaba ' ; '. Linea: " + line);
                                    temp=null;
                                }
                            }else{
                                line = token.getLine(); 
                                System.out.println("Se esperaba ' un valor '. Linea: " + line);
                                temp=null;
                            }
                        break;
                        
                case "sino"://Producción para sentenica ELSE
                            if(String.valueOf(temp) == "Paren abre"){
                                temp=token.getToken();
                                if(String.valueOf(temp) == "#entero"){
                                    temp=token.getToken();
                                    state = "index";//Manda a la sentencia de tipo de dato
                                }else if(String.valueOf(temp) == "#real"){
                                    temp=token.getToken();
                                    state = "index"; //Manda a la sentencia de tipo de dato
                                }else if(String.valueOf(temp) == "#cad"){
                                    temp=token.getToken();
                                    state = "index";//Manda a la sentencia de tipo de dato
                                }else if(String.valueOf(temp) == "#bool"){
                                    temp=token.getToken();
                                    state = "index";//Manda a la sentencia de tipo de dato
                                }else if(String.valueOf(temp) == "Imprime"){
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
                                    line = token.getLine(); 
                                    System.out.println("Se esperaba ')'. Linea: " + line);
                                    temp=null;
                                }

                            }else{
                                line = token.getLine();
                                System.out.println("Se esperaba '('. Linea: " + line);
                                temp=null;
                            }
                        break;
                        
                case "while"://Producción para sentencia While
                            if(String.valueOf(temp) == "Paren abre"){
                                temp = token.getToken();
                                if(String.valueOf(temp)=="Identificador" || String.valueOf(temp)=="Valor númerico"){
                                    temp = token.getToken();
                                    if(String.valueOf(temp) == "Igualdad" || String.valueOf(temp) == "Mayor que" || String.valueOf(temp) == "Menor que" || String.valueOf(temp) == "Menor igual que" || String.valueOf(temp) == "Mayor igual que" || String.valueOf(temp) == "OR" || String.valueOf(temp) == "AND" || String.valueOf(temp) == "Negación" || String.valueOf(temp) == "Desigual"){
                                        temp = token.getToken();
                                        if(String.valueOf(temp) == "Identificador" || String.valueOf(temp)=="Valor númerico"){
                                            temp = token.getToken();
                                            if(String.valueOf(temp) == "Paren cierra"){
                                                temp = token.getToken();
                                                state = "index";
                                            }
                                        }else{
                                            line = token.getLine();
                                            System.out.println("Error en condición, símbolo erroneo. Linea: " + line);
                                            temp=null;
                                        }
                                    }else{
                                        line = token.getLine();
                                        System.out.println("Error en condición. Linea: " + line);
                                        temp=null;
                                    }
                                }else{
                                    line = token.getLine();
                                    System.out.println("Error en condición. Linea: " + line);
                                    temp=null;
                                }
                                
                            }else{
                                line = token.getToken();
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
                                    line = token.getLine();
                                    System.out.println("Se esperaba ')'. Linea: " + line);
                                    temp=null;
                                }

                            }else{
                                line = token.getToken();
                                System.out.println("Se esperaba '('. Linea: " + line);
                                temp=null;
                            }
                        break;
                
                case "identificador": //Producción para asignación
                            temp = token.getToken();
                            if(String.valueOf(temp) == "Asignación"){
                                temp = token.getToken();
                                if(String.valueOf(temp) == "Identificador" || String.valueOf(temp) == "Valor númerico"){
                                    temp = token.getToken();
                                    if(String.valueOf(temp) == "Punto y coma"){
                                        temp = token.getToken();
                                        state = "index";
                                        //falta el error si no hay Punto y coma ';'
                                    }else if(String.valueOf(temp) == "Operador suma" || String.valueOf(temp) == "Operador resta" || String.valueOf(temp) == "Operador multiplicación" || String.valueOf(temp) == "Operador división"){
                                        temp = token.getToken();
                                        if(String.valueOf(temp) == "Identificador" || String.valueOf(temp) == "Valor númerico"){
                                            temp = token.getToken();
                                            if(String.valueOf(temp) == "Punto y coma"){
                                                temp = token.getToken();
                                                state = "index";
                                            }else{
                                                line = token.getToken();
                                                System.out.println("Se esperaba ';'. Linea: " + line);
                                                temp=null;
                                            }
                                        }else{
                                            line = token.getToken();
                                            System.out.println("Se esperaba un valor. Linea: " + line);
                                            temp=null;
                                        }
                                    }else{
                                        line = token.getToken();
                                        System.out.println("Error en la exp arit. Linea: " + line);
                                        temp=null;
                                    }
                                }else{
                                    line = token.getToken();
                                    System.out.println("Se esperaba un valor. Linea: " + line);
                                    temp=null;
                                }
                            }else{
                                //line = token.getToken();
                                System.out.println("Se esperaba '='. Linea: " + line);
                                temp=null;
                            }                        
                        break;
            }
                       
        }
        if(flag)
            System.out.println("existo"); 
    }
        
    
}
