/*
Archivo: Id.java
Materia: LENGUAJES Y AUTÓMATAS II
Programa: 3.2 Analizador Lexico Básico
Descripción: Se declaran las palabras reservadas usadas en el lenguaje
Fecha: 30-Nov-2021
*/
package clases;
public class Id {
    private static final String keyword[]={"int","short","long","double","float","String","private","final",
            "protect","public","class","static","boolean","if","else","switch","case","break","continue",
            "byte","extends","implements","interface","void","return","super","this","try","catch","throw",
"throws","while","new","abimelec"};
    public static boolean isKeyword(String s){
        for (String string:keyword) {
            if (s.equals(string))
                return true;
        }
        return false;
    }
}
