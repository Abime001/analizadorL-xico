/*
Archivo: Nodo.java
Materia: LENGUAJES Y AUTÓMATAS II
Programa: 3.2 Analizador Lexico Básico
Descripción: Clase paa manejar los nodos necesarios para el análisis léxico
Fecha: 30-Nov-2021
*/
package clases;
public class Nodo {
    Object name;
    Object type;
    Object valor;
    Object line;
    Nodo siguienteNodo;
    
    public Nodo(Object vNombre, Object vType, Object vValor, Object vLine){
        name=vNombre;
        type=vType;
        valor=vValor;
        line=vLine;
        siguienteNodo=null;       
    }
    
    public Object obtenerNombre(){
        return name;
    }
    
    public Object obtenerType(){
        return type;
    }
    
    public Object obtenerValor(){
        return valor;
    }
    
    public Object obtenerLine(){
        return line;
    }
    
    public void enlazarAlNodo(Nodo valorSiguienteNodo){
        siguienteNodo=valorSiguienteNodo;
    }
    
    public Nodo obtenerSiguienteNodo(){
        return siguienteNodo;
    }
}
    