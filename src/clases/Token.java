/*
Archivo: Token.java
Materia: LENGUAJES Y AUTÓMATAS II
Programa: 3.2 Analizador Lexico Básico
Descripción: Se asingan los métodos para trabajar con los tokens, las listas y la salida a la Interfaz Gráfica
Fecha: 30-Nov-2021
*/
package clases;
public class Token {
    private String name;
    private String type;
    private int line;
    private int valor;
    Nodo cabeza;
    int size;
    int nGet;
    
    public Token(){
        cabeza=null;                            
    }
    
    public Object getToken(){
        Object v=null;
        if(nGet>=0){
            v=ObtenerIndexListaTokens(nGet);
            nGet--;
        }
        else 
            return null;
        return v;                               
    }
    
     public Object ObtenerIndexListaTokens(int index){
        int cont=0;
        Nodo temp=cabeza;        
        while (cont<index){
            if(size>cont+1){                
                temp=temp.obtenerSiguienteNodo();
                cont++;
            }
            else
                return null;
        }
        return temp.obtenerType();
    }
       
    public void eliminarFinalListaTokens(){
        int cont=0;
        Nodo temp=cabeza;
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
     
    public void agregaPrimerolListaTokens(Object c,Object t, Object v,Object l){
        //System.out.println("token: "+c+' '+t+' '+v+' '+l+'\n');
        if (estaVacialListaTokens()){
            cabeza= new Nodo(c,t,v,l);
          //System.out.println("b1 "+cabeza+"-->"+'\t'+" entran: "+c+' '+t+' '+v+' '+l+'\n');
        }
        else{                        
            Nodo temp=cabeza;            
            Nodo nuevo=new Nodo(c,t,v,l);
            nuevo.enlazarAlNodo(temp);
            cabeza=nuevo;
          //System.out.println("b2 "+cabeza+"-->"+'\t'+" entran: "+c+' '+t+' '+v+' '+l+'\n');            
        }
        size++;
        nGet=size-1;
    }
    
    public boolean estaVacialListaTokens(){
        return (cabeza==null)?true:false;
    }
    
    public int sizelListaTokens(){
        return size;
    }
    
    public void eliminarPrimerolListaTokens(){
        cabeza=cabeza.obtenerSiguienteNodo();
        size--;
    }
        
    @Override
    public String toString() { 
        agregaPrimerolListaTokens(name, type, valor, line);
        return "Token{" +
                "nombre='" + name + '\'' +
                ", tipo='" + type + '\'' +
                ", valor='" + valor + '\'' +
                ", linea='" + line + '\'' +
                '}';
    }
        

    public String getName() {        
        return name;
    }
    
    public String getType() {
        return type;
    }  
    
    public int getValor() {
        return valor;
    }
    
    public int getLine() {
        return line;
    }     
    
    public void setName(String name) {
        this.name = name;        
    }
    
    public void setType(String type) {
        this.type = type;       
    }
    
    public void setValor(int valor) {
        this.valor = valor;        
    }

    public void setLine(int line) {
        this.line = line;
    }
}
