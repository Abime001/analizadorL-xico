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
        int state = 0;
        Nodo cabeza = null;
        Object temp=token.getToken();
        Object line = token.getLine();
        Object temp2=token.getLastToken();
        while (temp!=null) {
            switch (state) {
               /*case 0:
                        if(String.valueOf(temp)=="inicia" && String.valueOf(temp2)=="termina"){
                            System.out.println("okis");
                            state=1;
                        }else{
                            // System.out.println("error en" + line);
                            state=1;
                        }
                        temp=token.getToken();
                        break;
                        */
                case 0: //Producción para tipo de dato Entero
                        if(String.valueOf(temp)=="#entero"){ //Verifica que el valor del token sea el esperado y avanza en la regla gramtical
                            temp = token.getToken();
                            if(String.valueOf(temp)=="Identificador"){
                                temp = token.getToken();
                                if(String.valueOf(temp)=="Punto y coma"){
                                    temp = token.getToken();
                                    state = 2;
                                    System.out.println("Entero cool sin asignación");

                                }else if(String.valueOf(temp)=="Asignación"){
                                    //System.out.println("Se esperaba ;" + line);
                                    temp = token.getToken();
                                    if(String.valueOf(temp) == "Valores numéricos"){
                                        temp = token.getToken();
                                        if(String.valueOf(temp) =="Punto y coma"){
                                            temp = token.getToken();
                                            state = 2;
                                            System.out.println("Entero cool con asignación");     
                                        }

                                    }else{
                                        line = token.getLine(); 
                                        System.out.println("Se esperaba un número entero. Linea: " + line);
                                    }
                                }

                            } else{
                                line = token.getLine();
                                System.out.println("falta identificador en variable. Linea: " + line);
                            }
                        }else{
                            state=2;
                        }
                        break;
                case 2://Producción para tipo de dato Real
                        if(String.valueOf(temp)=="#real"){
                            temp=token.getToken();
                            if(String.valueOf(temp)=="Identificador"){
                                temp=token.getToken();
                                if(String.valueOf(temp)=="Punto y coma"){
                                    temp = token.getToken();
                                    state = 3;
                                }else if(String.valueOf(temp)=="Asignación"){
                                    temp=token.getToken();
                                    if(String.valueOf(temp) == "Valores numéricos"){
                                        temp=token.getToken();
                                        if(String.valueOf(temp) =="Punto y coma"){
                                            temp = token.getToken();
                                            state = 3;
                                        }
                                    }else{
                                        line = token.getLine(); 
                                        System.out.println("Se esperaba un número de tipo real. Linea: " + line);
                                    }
                                }

                            } else{
                                line = token.getLine(); 
                                System.out.println("falta identificador en variable. Linea: " + line);
                            }
                        }else{
                            state=3;     
                        }                     
                        
                        break;
                case 3://Producción para tipo de dato Booleano
                        if(String.valueOf(temp)=="#bool"){
                            temp=token.getToken();
                            if(String.valueOf(temp)=="Identificador"){
                                temp=token.getToken();
                                if(String.valueOf(temp)=="Punto y coma"){
                                    temp = token.getToken();
                                    state = 4;
                                }else if(String.valueOf(temp)=="Asignación"){
                                    //System.out.println("Se esperaba ;" + line);
                                    temp=token.getToken();
                                    if(String.valueOf(temp) == "true" || String.valueOf(temp) == "false"){
                                        temp=token.getToken();
                                        if(String.valueOf(temp) =="Punto y coma"){
                                            temp = token.getToken();
                                            state = 4;
                                        }
                                    }else{
                                        line = token.getLine(); 
                                        System.out.println("Se esperaba true o false. Linea: " + line);
                                    }
                                }
                            } else{
                                line = token.getLine(); 
                                System.out.println("falta identificador en variable. Linea: " + line);
                            }
                        }else{
                            state=4;  
                        }                     
                        
                        break;
                case 4: //Producción para tipo de dato Cadena
                        if(String.valueOf(temp)=="#cad"){
                            temp=token.getToken();
                            if(String.valueOf(temp)=="Identificador"){
                                temp=token.getToken();
                                if(String.valueOf(temp)=="Punto y coma"){
                                    temp = token.getToken();
                                    state = 5;
                                }else if(String.valueOf(temp)=="Asignación"){
                                    //System.out.println("Se esperaba ;" + line);
                                    temp=token.getToken();
                                    if(String.valueOf(temp) == "comillas dobles"){
                                        temp=token.getToken();
                                        if(String.valueOf(temp) =="identificador"){
                                            temp=token.getToken();
                                            if(String.valueOf(temp) == "comillas dobles"){
                                                temp=token.getToken();
                                                if(String.valueOf(temp)=="Punto y coma"){
                                                    temp = token.getToken();
                                                    state =5;
                                                }
                                            }else{
                                                line = token.getLine(); 
                                                System.out.println("Se esperaban comillas. Linea: " + line);
                                            }
                                        }
                                    }else{
                                        line = token.getLine(); 
                                        System.out.println("Se esperaban comillas. Linea: " + line);
                                    }
                                }
                            } else{
                                line = token.getLine(); 
                                System.out.println("falta identificador en variable. Linea: " + line);
                            }
                        }else{
                            temp = token.getToken(); 
                            state=5;  
                        }                     
                        break;
                case 5://Producción para sentencia IF
                        if(String.valueOf(temp)=="SI"){ //Puede ser palabra clave
                            temp=token.getToken();
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
                                                state = 6;
                                            }
                                        }else{
                                            line = token.getLine();
                                            System.out.println("Error en condición, símbolo erroneo. Linea: " + line);
                                        }
                                    }else{
                                        line = token.getLine();
                                        System.out.println("Error en condición. Linea: " + line);
                                    }
                                }else{
                                    line = token.getLine();
                                    System.out.println("Error en condición. Linea: " + line);
                                }
                            } else{
                                line = token.getLine(); 
                                System.out.println("Se esperaba '('. Linea: " + line);
                            }
                        }else{
                            temp = token.getToken();
                            state=6;  
                        }                     
                        break;
                case 6: //Producción para sentenica Entonces
                        if(String.valueOf(temp)=="ENTONCES"){ //Puede ser palabra clave 
                            temp=token.getToken();
                            if(String.valueOf(temp)=="Paren abre"){
                                temp=token.getToken();
                                if(String.valueOf(temp) == "#entero"){
                                    temp=token.getToken();
                                    state = 0;//Manda a la sentencia de tipo de dato
                                }else if(String.valueOf(temp) == "#real"){
                                    temp=token.getToken();
                                    state = 2; //Manda a la sentencia de tipo de dato
                                }else if(String.valueOf(temp) == "#cad"){
                                    temp=token.getToken();
                                    state = 4;//Manda a la sentencia de tipo de dato
                                }else if(String.valueOf(temp) == "#bool"){
                                    temp=token.getToken();
                                    state = 3;//Manda a la sentencia de tipo de dato
                                }else if(String.valueOf(temp) == "Imprime"){
                                    temp=token.getToken();
                                    state = 7;//Manda a la sentencia Imprimir
                                }else if(String.valueOf(temp) == "Identificador"){
                                    temp=token.getToken();
                                    state = 11; //Manda a la sentencia de asignación
                                }
                                //Falta para otro bloquue Inicia continua
                                else if(String.valueOf(temp) == "Paren cierra"){
                                    temp = token.getToken();
                                    state = 7;
                                }else{
                                    line = token.getLine(); 
                                    System.out.println("Se esperaba ')'. Linea: " + line);
                                }
                            } else{
                                line = token.getLine(); 
                                System.out.println("Se esperaba '('. Linea: " + line);
                            }
                        }else{
                            temp = token.getToken();
                            state=7;  
                        }                     
                        break;
                case 7: //Producción para sentencia Imprime
                        if(String.valueOf(temp) == "Imprime"){
                            temp = token.getToken();
                            if(String.valueOf(temp) == "Comillas"){
                                temp = token.getToken();
                                if(String.valueOf(temp) == "Palabra clave" || String.valueOf(temp) == "Valor númerico" || String.valueOf(temp) == "Identificador"){
                                    temp = token.getToken();
                                    if(String.valueOf(temp) == "Punto y coma"){
                                        temp = token.getToken();
                                        state = 8;
                                    }else{
                                        line = token.getLine(); 
                                        System.out.println("Se esperaba ' ; '. Linea: " + line);
                                    }
                                }else{
                                    line = token.getLine(); 
                                    System.out.println("Error el imprimir. Linea: " + line);
                                }
                            }else if (String.valueOf(temp) == "Palabra clave" || String.valueOf(temp) == "Valor númerico" || String.valueOf(temp) == "Identificador"){
                                temp = token.getToken();
                                if(String.valueOf(temp) == "Punto y coma"){
                                    temp = token.getToken();
                                    state = 8;
                                }else{
                                    line = token.getLine(); 
                                    System.out.println("Se esperaba ' ; '. Linea: " + line);
                                }
                            }
                        }else{
                            temp = token.getToken();
                            state = 8;
                        }
                        break;
                case 8://Producción para sentenica ELSE
                        if(String.valueOf(temp) == "SINO"){
                            temp = token.getToken();
                            if(String.valueOf(temp) == "Paren abre"){
                                temp=token.getToken();
                                if(String.valueOf(temp) == "#entero"){
                                    temp=token.getToken();
                                    state = 0;//Manda a la sentencia de tipo de dato
                                }else if(String.valueOf(temp) == "#real"){
                                    temp=token.getToken();
                                    state = 2; //Manda a la sentencia de tipo de dato
                                }else if(String.valueOf(temp) == "#cad"){
                                    temp=token.getToken();
                                    state = 4;//Manda a la sentencia de tipo de dato
                                }else if(String.valueOf(temp) == "#bool"){
                                    temp=token.getToken();
                                    state = 3;//Manda a la sentencia de tipo de dato
                                }else if(String.valueOf(temp) == "Imprime"){
                                    temp=token.getToken();
                                    state = 7;//Manda a la sentencia Imprimir
                                }else if(String.valueOf(temp) == "Identificador"){
                                    temp=token.getToken();
                                    state = 11; //Manda a la sentencia de asignación
                                }
                                //Falta para otro bloquue Inicia continua
                                else if(String.valueOf(temp) == "Paren cierra"){
                                    temp = token.getToken();
                                    state = 9;
                                }else{
                                    line = token.getLine(); 
                                    System.out.println("Se esperaba ')'. Linea: " + line);
                                }

                            }else{
                                line = token.getToken();
                                System.out.println("Se esperaba '('. Linea: " + line);   
                            }
                        }else{
                                temp = token.getToken();
                                state = 9;
                            }
                        break;
                case 9://Producción para sentencia While
                        if(String.valueOf(temp) == "MIENTRAS"){ //mientras
                            temp = token.getToken();
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
                                                state = 10;
                                            }
                                        }else{
                                            line = token.getLine();
                                            System.out.println("Error en condición, símbolo erroneo. Linea: " + line);
                                        }
                                    }else{
                                        line = token.getLine();
                                        System.out.println("Error en condición. Linea: " + line);
                                    }
                                }else{
                                    line = token.getLine();
                                    System.out.println("Error en condición. Linea: " + line);
                                }
                                
                            }else{
                                line = token.getToken();
                                System.out.println("Se esperaba '('. Linea: " + line);   
                            }
                        }else{
                                temp = token.getToken();
                                state = 10;
                            }
                        break;
                case 10: //Producción para Hacer
                        if(String.valueOf(temp) == "HACER"){ //hacer //mientras(adsasdfasdfasd) hacer(asdadsdfasd)
                            temp = token.getToken();
                            if(String.valueOf(temp) == "Paren abre"){
                                temp=token.getToken();
                                if(String.valueOf(temp) == "#entero"){
                                    temp=token.getToken();
                                    state = 0;//Manda a la sentencia de tipo de dato
                                }else if(String.valueOf(temp) == "#real"){
                                    temp=token.getToken();
                                    state = 2; //Manda a la sentencia de tipo de dato
                                }else if(String.valueOf(temp) == "#cad"){
                                    temp=token.getToken();
                                    state = 4;//Manda a la sentencia de tipo de dato
                                }else if(String.valueOf(temp) == "#bool"){
                                    temp=token.getToken();
                                    state = 3;//Manda a la sentencia de tipo de dato
                                }else if(String.valueOf(temp) == "Imprime"){
                                    temp=token.getToken();
                                    state = 7;//Manda a la sentencia Imprimir
                                }else if(String.valueOf(temp) == "Identificador"){
                                    temp=token.getToken();
                                    state = 11; //Manda a la sentencia de asignación
                                }
                                //Falta para otro bloquue Inicia continua
                                else if(String.valueOf(temp) == "Paren cierra"){
                                    temp = token.getToken();
                                    state = 9;
                                }else{
                                    line = token.getLine();
                                    System.out.println("Se esperaba ')'. Linea: " + line);
                                }

                            }else{
                                line = token.getToken();
                                System.out.println("Se esperaba '('. Linea: " + line);   
                            }
                        }else{
                                temp = token.getToken();
                                state = 9;
                            }
                        break;
                
                case 11: //Producción para asignación
                        if(String.valueOf(temp) == "Identificador"){// s = 5 * (a+b);
                            temp = token.getToken();
                            if(String.valueOf(temp) == "Asignación"){
                                temp = token.getToken();
                                if(String.valueOf(temp) == "Identificador" || String.valueOf(temp) == "Valor númerico"){
                                    temp = token.getToken();
                                    if(String.valueOf(temp) == "Punto y coma"){
                                        temp = token.getToken();
                                        state = 12;
                                        //falta el error si no hay Punto y coma ';'
                                    }else if(String.valueOf(temp) == "Operador suma" || String.valueOf(temp) == "Operador resta" || String.valueOf(temp) == "Operador multiplicación" || String.valueOf(temp) == "Operador división"){
                                        temp = token.getToken();
                                        if(String.valueOf(temp) == "Identificador" || String.valueOf(temp) == "Valor númerico"){
                                            temp = token.getToken();
                                            if(String.valueOf(temp) == "Punto y coma"){
                                                temp = token.getToken();
                                                state = 12;
                                            }else{
                                                line = token.getToken();
                                                System.out.println("Se esperaba ';'. Linea: " + line);
                                            }
                                        }else{
                                            line = token.getToken();
                                            System.out.println("Se esperaba un valor. Linea: " + line);
                                        }
                                    }else{
                                        line = token.getToken();
                                        System.out.println("Error en la exp arit. Linea: " + line);
                                    }
                                }else{
                                    line = token.getToken();
                                    System.out.println("Se esperaba un valor. Linea: " + line); 
                                }
                            }else{
                                line = token.getToken();
                                System.out.println("Se esperaba '='. Linea: " + line); 
                            }
                        }else{
                                temp = token.getToken();
                                state = 12;
                            }
                        break;
            }
        }
    }
        
    
}
