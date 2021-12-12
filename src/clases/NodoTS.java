/*
Archivo: Nodo.java
Materia: LENGUAJES Y AUTÓMATAS II
Programa: 3.2 Analizador Lexico Básico
Descripción: Clase paa manejar los nodos necesarios para el análisis léxico
Fecha: 30-Nov-2021
*/
package clases;
public class NodoTS {
    Object name;
    Integer[] lineas = new Integer[80];
    NodoTS siguienteNodo;
    int i=0;
    
    public NodoTS(Object nombreTok, Object lineaTok){
        name=nombreTok;
        siguienteNodo=null;       
    }
   
//    public Object Nodo
    
    public Object obtenerNombre(){
        return name;
    }
    
    public Object obtenerLine(){
        return lineas;
    }
    
    public void enlazarAlNodo(NodoTS valorSiguienteNodo){
        siguienteNodo=valorSiguienteNodo;
    }
    
    public NodoTS obtenerSiguienteNodo(){
        return siguienteNodo;
    }
}
