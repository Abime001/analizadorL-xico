/*
Archivo: Token.java
Materia: LENGUAJES Y AUTÓMATAS II
Programa: 3.2 Analizador Lexico Básico
Descripción: Se asingan los métodos para trabajar con los tokens, las listas y la salida a la Interfaz Gráfica
Fecha: 30-Nov-2021
*/
package clases;
public class TablaSimbolos {
    private String name;
    private Integer[] lineas=new Integer[80];
    NodoTS cabeza;
    int size;
    int nGet; //contador global para metodo getTablaSimbolos().
    
    public TablaSimbolos(){
        cabeza=null;                            
    }
    
    public Object imprimir(){
        Object v=null;
        if(nGet>=0){
            v=buscar(nGet);
            nGet--;
        }
        else
            return null;
        return v;                               
    }

    public Object insertar(){
        Object v=null;
        if(nGet>=0){
            v=buscar(nGet);
            nGet--;
        }
        else
            return null;
        return v;                               
    }

    
    public Object buscar(int index){
        int cont=0;
        NodoTS temp=cabeza;        
        while (cont<index){
            if(size>cont+1){                
                temp=temp.obtenerSiguienteNodo();
                cont++;
            }
            else
                return null;
        }
        return temp.obtenerNombre();
    }
        
    public void eliminarFinalListaTablaSimbolos(){
        int cont=0;
        NodoTS temp=cabeza;
        if (size==1)
            cabeza=null;
        else
        {
            while (cont<size-1){
                temp=temp.obtenerSiguienteNodo();
                cont++;
            }        
            temp.enlazarAlNodo(null);
        }
        size--;                      
    }
     
    public void addlListaTablaSimbolos(Object nombreTok,Object lineTok){
        System.out.println("TablaSimbolos: "+nombreTok+' '+lineTok+'\n');
        if (estaVacialListaTablaSimbolos()){
            cabeza= new NodoTS(nombreTok,lineTok);
        }
        else{                        
            NodoTS temp=cabeza;            
            NodoTS nuevo=new NodoTS(nombreTok,lineTok);
            nuevo.enlazarAlNodo(temp);
            cabeza=nuevo;
        }
        size++;
        nGet=size-1;
    }
    
    public boolean estaVacialListaTablaSimbolos(){
        return (cabeza==null)?true:false;
    }
    
    public int sizelListaTablaSimbolos(){
        return size;
    }
    
    public void eliminarPrimerolListaTablaSimbolos(){
        cabeza=cabeza.obtenerSiguienteNodo();
        size--;
    }
        
    @Override
    public String toString() {         
        return "Token{" +
                "nombre='" + name + '\'' +     
                '}';
    }
        

    public String getName() {        
        return name;
    }    
    
    public void setName(String name) {
        this.name = name;        
    }

}
