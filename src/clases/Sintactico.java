/*
Archivo: Sintactico.java
Materia: LENGUAJES Y AUTÓMATAS II
Programa: 3.2 Analizador Sintáctico y Semántico Básico
Descripción: Se realiza el análisis Sintáctico y Semántico 
Fecha: 5-Dic-2021
*/
package clases;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Map.Entry;

public class Sintactico extends  Lexico {
    int Line=0;
    Map <String, String> symbolTableValor = new HashMap<String, String>();
    Map <String, String> symbolTableType = new HashMap<String, String>();

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
        
    public void S() {//análisis Sintáctico y Semántico        
        String state = "index";
        Object temp=token.getToken();
        Object temp2=token.getLastToken();        
        boolean flag=false;
        while (temp!=null) {
            switch (state) {                
                case "index"://Index de producciones
                    Line++;//Una linea por instruccion
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
                        LGUI.t4.append("Palabra no identificada '"+temp+"', Linea: "+Line);
                        LGUI.t4.append("\r\n");                 
                        temp=null;
                        flag=false;
                    }                    
                    break;                    
                                            
                case "inicia":                            
                    if(String.valueOf(temp2)=="fin_bloque"){                            
                            temp=token.getToken();
                            state ="index";
                            flag=true;
                        }else{
                            LGUI.t4.append("Error al declarar bloque, Linea: "+Line);
                            LGUI.t4.append("\r\n");  
                            flag=false;
                            temp=null;                   
                        }                        
                        break;                                           
                
                case "entero":    
                            temp = token.getToken();
                            if(String.valueOf(temp)=="Identificador"){
                                if(symbolTableValor.containsKey(token.getName())){
                                    LGUI.t4.append("Identificador duplicado " + Line);
                                    LGUI.t4.append("\r\n");                                    
                                    temp = null;
                                    flag=false;
                                    break;
                                    
                                }else{
                                    symbolTableValor.put(String.valueOf(token.getName()), "0");
                                    symbolTableType.put(String.valueOf(token.getName()), "#Entero");
                                }
                                temp = token.getToken();
                                if(String.valueOf(temp)=="Punto y coma"){
                                    temp = token.getToken();
                                    state = "index";
                                    flag=true;
                                }else{ 
                                    LGUI.t4.append("Error al declarar Sentencia. Linea: " + Line);
                                    LGUI.t4.append("\r\n");  
                                    temp=null;
                                    flag=false;

                                }
                            }else{
                                LGUI.t4.append("falta identificador en variable. Linea: " + Line);
                                LGUI.t4.append("\r\n");
                                temp=null;
                                flag=false;                                
                            }                        
                            break;
                    
                case "real"://Producción para tipo de dato Real
                            temp=token.getToken();
                            if(String.valueOf(temp)=="Identificador"){
                                if(symbolTableValor.containsKey(token.getName())){
                                    //Error semantico en Real
                                    LGUI.t4.append("Identificador duplicado " + Line);
                                    LGUI.t4.append("\r\n");                                    
                                    temp = null;
                                    flag = false;
                                    break;
                                }else{
                                    symbolTableValor.put(String.valueOf(token.getName()), "0");
                                    symbolTableType.put(String.valueOf(token.getName()), "#Real");
                                }
                                temp=token.getToken();
                                if(String.valueOf(temp)=="Punto y coma"){
                                    temp = token.getToken();
                                    state ="index";
                                }else{
                                    LGUI.t4.append("Se esperaba ' ; '. Linea: " + Line);
                                    LGUI.t4.append("\r\n");
                                    temp=null;
                                    flag=false;
                                    break;
                                }
                            } else{                  
                                LGUI.t4.append("falta identificador en variable. Linea: " + Line);
                                LGUI.t4.append("\r\n"); 
                                temp=null;
                                flag=false;                                
                            }                             
                            break;
    
                case "bool"://Producción para tipo de dato Booleano
                            temp=token.getToken();
                            if(String.valueOf(temp)=="Identificador"){
                                if(symbolTableValor.containsKey(token.getName())){
                                    LGUI.t4.append("Identificador duplicado " + Line);
                                    LGUI.t4.append("\r\n");                                    
                                    //Error semantico en Bool
                                    temp=null;
                                    flag = false;
                                    break;
                                }else{
                                    symbolTableValor.put(String.valueOf(token.getName()), "TRUE");                                    
                                    symbolTableType.put(String.valueOf(token.getName()), "#Bool");
                                }
                                temp=token.getToken();
                                if(String.valueOf(temp)=="Punto y coma"){
                                    temp = token.getToken();
                                    state = "index";
                                    flag=true;
                                }
                            } else{
                                LGUI.t4.append("falta identificador en variable. Linea: " + Line);
                                LGUI.t4.append("\r\n"); 
                                temp=null;
                                flag=false;
                            }
                            break;
    
                case "cad": //Producción para tipo de dato Cadena
                            temp=token.getToken();
                            if(String.valueOf(temp)=="Identificador"){
                                if(symbolTableValor.containsKey(token.getName())){
                                    //Error semantico en Bool
                                    LGUI.t4.append("Identificador duplicado " + Line);
                                    LGUI.t4.append("\r\n");                                    
                                    temp=null;
                                    flag = false;
                                    break;
                                }else{
                                    symbolTableValor.put(String.valueOf(token.getName()), "0");
                                    symbolTableType.put(String.valueOf(token.getName()), "#Cad");
                                }
                                temp=token.getToken();
                                if(String.valueOf(temp)=="Punto y coma"){
                                    temp = token.getToken();
                                    state = "index";
                                    flag=true;
                                }else{ 
                                    LGUI.t4.append("Se esperaban cosquillas. Linea: " + Line);
                                    LGUI.t4.append("\r\n");                                     
                                    temp=null;   
                                    flag=false;
                                }
                            } else{
                                LGUI.t4.append("falta identificador en variable. Linea: " + Line);
                                LGUI.t4.append("\r\n");                                
                                temp=null;
                                flag=false;
                            }               
                            break;
                        
                case "si"://Producción para sentencia IF
                          String getValueFromKeyType1=null;
                          String getValueFromKeyType2=null;
                          temp=token.getToken ();
                          if(String.valueOf(temp)=="Paren abre"){
                                temp=token.getToken();
                                if(String.valueOf(temp)=="Identificador"){
                                    if(symbolTableValor.containsKey(token.getName())){
                                        getValueFromKeyType1 = getSingleValueFromKey(symbolTableType, token.getName());                                        
                                        temp = token.getToken();                                        
                                    }else{
                                        LGUI.t4.append("Error, No esta declarada la variable. Linea: " + Line);
                                        LGUI.t4.append("\r\n");
                                        temp=null;                           
                                        flag=false;
                                        break;                                        
                                    }
                                    if(String.valueOf(temp) == "Igualdad" || String.valueOf(temp) == "Mayor que" || String.valueOf(temp) == "Menor que" || String.valueOf(temp) == "Menor igual que" || String.valueOf(temp) == "Mayor igual que" || String.valueOf(temp) == "OR" || String.valueOf(temp) == "AND" || String.valueOf(temp) == "Negación" || String.valueOf(temp) == "Desigual"){
                                        temp = token.getToken();
                                        if(String.valueOf(temp) == "Identificador"){
                                            if(symbolTableValor.containsKey(token.getName())){
                                                getValueFromKeyType2 = getSingleValueFromKey(symbolTableType, token.getName());
                                                if(getValueFromKeyType2==getValueFromKeyType1){
                                                    temp = token.getToken();
                                                    if(String.valueOf(temp) == "Paren cierra"){
                                                        temp = token.getToken();
                                                        state = "index";
                                                        flag=true;
                                                    }else{
                                                        LGUI.t4.append("Error se esperaba ')'. Linea: " + Line);
                                                        LGUI.t4.append("\r\n");
                                                        temp=null;                           
                                                        flag=false;
                                                        break;
                                                    }
                                                }else{                                                    
                                                    LGUI.t4.append("Error, Tipos de dato incompatibles. Linea: " + Line);
                                                    LGUI.t4.append("\r\n");
                                                    temp=null;                           
                                                    flag=false;
                                                    break;                                                    
                                                }                                                   
                                            }else{
                                                LGUI.t4.append("Error, Falta declarar variable. Linea: " + Line);
                                                LGUI.t4.append("\r\n");
                                                temp=null;                           
                                                flag=false;
                                                break;                                              
                                            }                                           
                                            
                                        }else if(String.valueOf(temp) == "Valores numéricos"){
                                            if(getValueFromKeyType1=="#Entero" || getValueFromKeyType1=="#Real"){
                                                temp=token.getToken();
                                                if(String.valueOf(temp) == "Paren cierra"){
                                                    temp = token.getToken();
                                                    state = "index";
                                                    flag=true;
                                                }else{
                                                    LGUI.t4.append("Error se esperaba ')'. Linea: " + Line);
                                                    LGUI.t4.append("\r\n");
                                                    temp=null;                           
                                                    flag=false;
                                                    break;
                                                }
                                            }else{
                                                //Falta producciones de a<1 1<a 1<1
                                            }
                                        }
                                    }else{
                                        LGUI.t4.append("Error simbolo de comparacion. Linea: " + Line);
                                        LGUI.t4.append("\r\n");                                        
                                        temp=null;
                                        flag=false;
                                        break;
                                    }
                                }else{
                                    LGUI.t4.append("Error en condición. Linea: " + Line);
                                    LGUI.t4.append("\r\n");
                                    temp=null;
                                }
                            } else{
                                LGUI.t4.append("Se esperaba '('. Linea: " + Line);
                                LGUI.t4.append("\r\n");
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
                                    LGUI.t4.append("Se esperaba ')' Linea: " + Line);
                                    LGUI.t4.append("\r\n");
                                    temp=null;
                                }
                            } else{
                                LGUI.t4.append("Se esperaba '(' Linea: " + Line);
                                LGUI.t4.append("\r\n");
                                temp=null;
                            }                                             
                        break;
                        
                case "imprime": //Producción para sentencia Imprime                         
                            temp = token.getToken();
                            String auxImprime=null;                            
                            if(String.valueOf(temp) == "COMILLAS DOBLES"){//<<"
                                temp = token.getToken();
                                if(String.valueOf(temp)=="COMILLAS DOBLES"){//Dar un salto de linea
                                    LGUI.t4.append("\r\n");
                                    temp=token.getToken();
                                    if(String.valueOf(temp) == "Punto y coma"){
                                        state = "index";
                                        temp = token.getToken();
                                        flag=true;
                                        break;
                                    }else{
                                        LGUI.t4.append("Se esperaba ';' Linea: " + Line);
                                        LGUI.t4.append("\r\n");
                                        temp=null;
                                        flag=false;
                                        break;
                                    }  
                                }else{
                                    while(String.valueOf(temp)!="COMILLAS DOBLES"){  
                                        auxImprime=token.getName();
                                        LGUI.t4.append(auxImprime+" ");                                    
                                        temp = token.getToken();                                    
                                    }

                                }                            
                                    if(String.valueOf(temp) == "COMILLAS DOBLES"){
                                      temp=token.getToken(); 
                                      if(String.valueOf(temp) == "Punto y coma"){
                                        state = "index";
                                        //LGUI.t4.append("\r\n");
                                        temp = token.getToken();
                                        flag=true;                                
                                    }else{
                                        LGUI.t4.append("Se esperaba ';' Linea: " + Line);
                                        LGUI.t4.append("\r\n");
                                        temp=null;
                                        flag=false;
                                        break;
                                    }
                                    }else{
                                        LGUI.t4.append("Se esperaban ' \" ' Linea: " + Line);
                                        LGUI.t4.append("\r\n");
                                        temp=null;
                                        flag=false;
                                        break;
                                    }
                                    
                                if (String.valueOf(temp)=="COMILLAS DOBLES"){
                                    temp = token.getToken();
                                    if (String.valueOf(temp)=="Punto y coma"){
                                        temp = token.getToken();
                                        state="index";
                                        flag=true;
                                        break;
                                    }else{
                                        LGUI.t4.append("Se esperaba ';' Linea: " + Line);
                                        LGUI.t4.append("\r\n");
                                        temp=null;
                                        flag=false;
                                        break;
                                    }
                                }
                            }else if (String.valueOf(temp) == "Identificador"){//   
                                if(symbolTableValor.containsKey(token.getName())){
                                    String getValueFromKey = getSingleValueFromKey(symbolTableValor, token.getName());
                                    temp=token.getToken();
                                    if(String.valueOf(temp) == "Punto y coma"){                                        
                                        state = "index";
                                        LGUI.t4.append(" "+getValueFromKey+" ");
                                        //LGUI.t4.append("\r\n");
                                        temp = token.getToken();
                                        flag=true;
                                    }else{
                                        LGUI.t4.append("Se esperaba ';' Linea: " + Line);
                                        LGUI.t4.append("\r\n");
                                        temp=null;
                                        flag = false;
                                        break;
                                    }                                    
                                }else{
                                    //Error semantico en Bool
                                    LGUI.t4.append("Error, la variable no esta declarada. Linea: " + Line);
                                    LGUI.t4.append("\r\n");                                    
                                    temp=null;
                                    flag = false;
                                    break;
                                }                                
                            }else if(String.valueOf(temp) == "Valores numéricos"){
                                auxImprime=token.getName();
                                temp = token.getToken();
                                if(String.valueOf(temp) == "Punto y coma"){
                                   LGUI.t4.append(auxImprime);
                                   LGUI.t4.append("\r\n");
                                   temp = token.getToken();
                                   flag=true;
                                   state = "index";
                                }else{
                                    LGUI.t4.append("Se esperaba ' ; '. Linea: " + Line);
                                    LGUI.t4.append("\r\n");
                                    temp=null;
                                    flag = false;
                                    break;                
                                }                                
                            }else{
                                LGUI.t4.append("Se esperaba ' un valor '. Linea: " + Line);
                                LGUI.t4.append("\r\n");
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
                                    LGUI.t4.append("Se esperaba ' ) '. Linea: " + Line);
                                    LGUI.t4.append("\r\n");
                                    temp=null;
                                }

                            }else{
                                LGUI.t4.append("Se esperaba '('. Linea: " + Line);
                                LGUI.t4.append("\r\n");
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
                                            LGUI.t4.append("Error en condición, símbolo erroneo. Linea: " + Line);
                                            LGUI.t4.append("\r\n");
                                            temp=null;
                                        }
                                    }else{
                                        LGUI.t4.append("Error en condición. Linea: " + Line);
                                        LGUI.t4.append("\r\n");
                                        temp=null;
                                    }
                                }else{
                                    LGUI.t4.append("Error en condición. Linea: " + Line);
                                    LGUI.t4.append("\r\n");
                                    temp=null;
                                }
                                
                            }else{
                                LGUI.t4.append("Se esperaba '('. Linea: " + Line);
                                LGUI.t4.append("\r\n");
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
                                    LGUI.t4.append("Se esperaba ')'. Linea: " + Line);
                                    LGUI.t4.append("\r\n");
                                    temp=null;
                                }

                            }else{
                                LGUI.t4.append("Se esperaba '('. Linea: " + Line);
                                LGUI.t4.append("\r\n");
                                temp=null;
                            }
                        break;
	
                
                case "identificador": //Producción para asignación
                            String auxName = "";
                            String auxTemp = "";
                            int auxValue=0;
                            int aux3=0;
                            int aux4=0;
                            int res=0;
                            double resF=0;
                            auxName=token.getName();
                            auxTemp=buscar(auxName);
                            if(auxTemp==auxName){                                
                                String getValueFromKey = getSingleValueFromKey(symbolTableValor, token.getName());                                
                                auxValue = Integer.parseInt(getValueFromKey);                              
                                temp = token.getToken();
                            }else{
                                //Error semantico en Bool
                                LGUI.t4.append("Variable no declarada, Linea: "+ Line);
                                LGUI.t4.append("\r\n");                                    
                                temp=null;
                                flag = false;
                                break;
                            }
                            
                            if(String.valueOf(temp) == "Asignación"){
                                temp = token.getToken();
                                if(String.valueOf(temp) == "Identificador"){
                                    if(symbolTableValor.containsKey(token.getName())){
                                        String getValueFromKey = getSingleValueFromKey(symbolTableValor, token.getName());
                                        aux3 = Integer.parseInt(getValueFromKey);
                                        symbolTableValor.replace(auxName, getValueFromKey);
                                        temp = token.getToken();
                                    }else{
                                        //Error semantico en Bool
                                        LGUI.t4.append("Variable no declarada" + Line);
                                        LGUI.t4.append("\r\n");                                    
                                        temp=null;
                                        flag = false;
                                        break;
                                    }
                                    if(String.valueOf(temp) == "Punto y coma"){
                                        temp = token.getToken();
                                        state = "index";
                                        flag=true;
                                    }else if(String.valueOf(temp) == "Operador suma" || String.valueOf(temp) == "Operadores resta" ||  String.valueOf(temp) == "Operador multiplicación" || String.valueOf(temp) == "Operador división"){
                                        String auxOpe=null;
                                        auxOpe =token.getName();
                                        temp = token.getToken();
                                        if(String.valueOf(temp) == "Identificador"){
                                            if(symbolTableValor.containsKey(token.getName())){
                                                String getValueFromKey = getSingleValueFromKey(symbolTableValor, token.getName());
                                                aux4 = Integer.parseInt(getValueFromKey);
                                                if(auxOpe.equals("+")){
                                                res = aux3 + aux4;
                                                symbolTableValor.replace(auxName, String.valueOf(res));
                                                }else if(auxOpe.equals("-")){
                                                    res = aux3 - aux4;
                                                    symbolTableValor.replace(auxName, String.valueOf(res));
                                                } else if(auxOpe.equals("*")){
                                                    res = aux3 * aux4;
                                                    symbolTableValor.replace(auxName, String.valueOf(res));
                                                }else if(auxOpe.contains("/")){                                                
                                                    resF =(double) aux3 / aux4;         
                                                    symbolTableValor.replace(auxName, String.valueOf(resF));
                                                    System.out.println("entre "+ resF);
                                                }
                                                temp = token.getToken();
                                                if(String.valueOf(temp) == "Punto y coma"){
                                                    temp = token.getToken();
                                                    state = "index";
                                                    flag=true;
                                                }else{
                                                    LGUI.t4.append("Se esperaba ';', Linea: " + Line);
                                                    LGUI.t4.append("\r\n");
                                                    temp=null;
                                                    break;
                                                }
                                            }else{
                                                //Error semantico en Bool
                                                LGUI.t4.append("Variable no declarada" + Line);
                                                LGUI.t4.append("\r\n");                                    
                                                temp=null;
                                                flag = false;
                                                break;
                                            }
                                   
                                        }else if(String.valueOf(temp) == "Valores numéricos"){
                                            aux4 = Integer.parseInt(token.getName());
                                            resF=0;
                                            if(auxOpe.equals("+")){
                                                res = aux3 + aux4;
                                                symbolTableValor.replace(auxName, String.valueOf(res));
                                            }else if(auxOpe.equals("-")){
                                                res = aux3 - aux4;
                                                symbolTableValor.replace(auxName, String.valueOf(res));
                                            } else if(auxOpe.equals("*")){
                                                res = aux3 * aux4;
                                                symbolTableValor.replace(auxName, String.valueOf(res));
                                            }else if(auxOpe.contains("/")){                                                
                                                resF =(double) aux3 / aux4;         
                                                symbolTableValor.replace(auxName, String.valueOf(resF));                                                
                                            }
                                            temp = token.getToken();
                                            if(String.valueOf(temp) == "Punto y coma"){
                                                temp = token.getToken();
                                                state = "index";
                                                flag=true;
                                            }else{
                                                LGUI.t4.append("Se esperaba ';'. Linea: " + Line);
                                                LGUI.t4.append("\r\n");
                                                temp=null;
                                                flag=false;
                                                break;
                                            }
                                        }else{
                                            LGUI.t4.append("Se esperaba un valor. Linea: " + Line);
                                            LGUI.t4.append("\r\n");
                                            temp=null;
                                            flag=false;
                                            break;
                                        }
                                    }else{
                                        LGUI.t4.append("Se esperaba un ' operador '. Linea: " + Line);
                                        LGUI.t4.append("\r\n");
                                        temp=null;
                                        flag=false;
                                        break;
                                    }
                                }else if(String.valueOf(temp)== "Valores numéricos"){ 
                                    String aux6=null;
                                    aux6=token.getName();
                                    temp = token.getToken();
                                    if(String.valueOf(temp) == "Punto y coma"){                                                                                
                                        symbolTableValor.replace(auxName, aux6);
                                        temp = token.getToken();
                                        state = "index";
                                        flag=true;
                                    }else if(String.valueOf(temp) == "Operador suma" || String.valueOf(temp) == "Operador resta" ||  String.valueOf(temp) == "Operador multiplicación" || String.valueOf(temp) == "Operador división"){
                                        String auxOpe =String.valueOf(token.getName());
                                        temp = token.getToken();
                                        if(String.valueOf(temp) == "Identificador"){
                                            if(symbolTableValor.containsKey(token.getName())){
                                                String getValueFromKey = getSingleValueFromKey(symbolTableValor, token.getName());
                                                aux4 = Integer.parseInt(getValueFromKey);
                                                if(auxOpe == "+"){
                                                    res = aux3 + aux4;
                                                }else if(auxOpe == "-"){
                                                    res = aux4 - aux4;
                                                } else if(auxOpe == "*"){
                                                    res = aux4 * aux4;
                                                }else if(auxOpe == "/"){
                                                    res = aux4 / aux4;
                                                }
                                                symbolTableValor.replace(auxName, String.valueOf(res));
                                                temp = token.getToken();
                                                if(String.valueOf(temp) == "Punto y coma"){
                                                    temp = token.getToken();
                                                    state = "index";
                                                    flag=true;
                                                }else{
                                                    LGUI.t4.append("Se esperaba ';'. Linea: " + Line);
                                                    LGUI.t4.append("\r\n");
                                                    temp=null;
                                                    flag=false;
                                                    break;
                                                }
                                            }else{
                                                //Error semantico en Bool
                                                LGUI.t4.append("Variable no declarada" + Line);
                                                LGUI.t4.append("\r\n");
                                                temp=null;
                                                flag = false;
                                                break;
                                            }
                                   
                                        }else if(String.valueOf(temp) == "Valores númerico"){
                                            aux4 = Integer.parseInt(token.getName());
                                            if(auxOpe == "+"){
                                                res = aux3 + aux4;
                                            }else if(auxOpe == "-"){
                                                res = aux3 - aux4;
                                            } else if(auxOpe == "*"){
                                                res = aux3 * aux4;
                                            }else if(auxOpe == "/"){
                                                res = aux3 / aux4;
                                            }
                                            symbolTableValor.replace(auxName, String.valueOf(res));
                                            temp = token.getToken();
                                            if(String.valueOf(temp) == "Punto y coma"){
                                                temp = token.getToken();
                                                state = "index";
                                                flag=true;
                                            }else{
                                                LGUI.t4.append("Se esperaba ';'. Linea: " + Line);
                                                LGUI.t4.append("\r\n");
                                                temp=null;
                                                flag=false;
                                                break;
                                            }
                                        }else{
                                            LGUI.t4.append("Se esperaba un valor. Linea: " + Line);
                                            LGUI.t4.append("\r\n");
                                            temp=null;
                                            flag=false;
                                            break;
                                        }
                                    }else{
                                        LGUI.t4.append("Se esperaba ';'. Linea: " + Line);
                                        LGUI.t4.append("\r\n");
                                        temp=null;
                                        flag=false;
                                        break;
                                    }

                                }else if(String.valueOf(temp)=="COMILLAS DOBLES"){
                                    temp=token.getToken();
                                    if(String.valueOf(temp)=="Identificador"||String.valueOf(temp)=="Valores numéricos"){
                                        symbolTableValor.replace(auxName, token.getName());
                                        temp=token.getToken();
                                        if(String.valueOf(temp)=="COMILLAS DOBLES"){
                                            temp=token.getToken();
                                            if(String.valueOf(temp)=="Punto y coma"){
                                                temp=token.getToken();
                                                state = "index";
                                                flag=true;
                                            }else{
                                                LGUI.t4.append("Se esperaba ';'. Linea: " + Line);
                                                LGUI.t4.append("\r\n");
                                                temp=null;
                                                flag=false;
                                                break;                                                
                                            }
                                            
                                        }else {
                                            LGUI.t4.append("Se esperaba ' \" '. Linea: " + Line);
                                            LGUI.t4.append("\r\n");
                                            temp=null;
                                            flag=false;
                                            break;

                                        }
                                    }
                                }else if(String.valueOf(temp)=="TRUE"||String.valueOf(temp)=="FALSE"){
                                    symbolTableValor.replace(auxName, token.getName());
                                    temp=token.getToken();
                                    if(String.valueOf(temp)=="Punto y coma"){
                                        temp=token.getToken();
                                        state = "index";
                                        flag=true;
                                    }else{
                                        LGUI.t4.append("Se esperaba ';'. Linea: " + Line);
                                        LGUI.t4.append("\r\n");
                                        temp=null;
                                        flag=false;
                                        break;                                                
                                    }

                                }else{
                                LGUI.t4.append("Se esperaba 'Identificador, Número o Comillas'. Linea: " + Line);
                                LGUI.t4.append("\r\n");                           
                                temp=null;
                                flag = false;                                    
                                }
                            }else{
                                LGUI.t4.append("Se esperaba '='. Linea: " + Line);
                                LGUI.t4.append("\r\n");                           
                                temp=null;
                                flag = false;
                            }                
                        break;
            }                       
        }
        
        if(flag==true){
            System.out.println("\033[32m BUILD SUCCESS ");
            LGUI.t4.append("\r\nBUILD SUCCESS!\r\n");

        }else{
            System.out.println("ERROR AL COMPILAR");
            LGUI.t4.append("\r\nERROR AL COMPILAR\r\n");

        }/*
        for (Map.Entry<String, String> entry : symbolTableValor.entrySet()) {
            LGUI.t5.append("< " + entry.getKey() + " | " + entry.getValue()+" >\r\n");
        }*/        
    }
    
    public String buscar(String key){
        if(symbolTableValor.containsKey(key)){
            return key;
        }else{
        return "null";
        }
    }
    
    public void imprimirTS(){
        Object temp;
        token.reiniciaGet();
        temp=token.getToken();
        LGUI.t5.append("CADENA \tVALOR \tLINEA\r\n\r\n");
        for (Map.Entry<String, String> entry : symbolTableValor.entrySet()) {
            LGUI.t5.append(entry.getKey() + "\t"+entry.getValue()+ "\t");        
            while(temp!=null){
                if(token.getName().equals(entry.getKey()))
                    LGUI.t5.append(">"+String.valueOf(token.getLine()));
                temp=token.getToken();
            }
            LGUI.t5.append("\r\n");
            token.reiniciaGet();
            temp=token.getToken();
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
