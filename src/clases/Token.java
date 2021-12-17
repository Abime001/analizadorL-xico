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
    int nGetLast;
    
    public Token(){
        cabeza=null;                            
    }

    public void reiniciaGet(){
        nGet=size-1;  
    }

    public void reiniciaGetLast(){
        nGetLast=0;  
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

    public Object getLastToken(){
        Object v=null;
        if(nGetLast<size){
            v=ObtenerIndexListaTokens(nGetLast);
            nGetLast++;
        }
        else 
            return null;
        return v;                               
    }
    
    public Object ObtenerIndexListaTokens(int index){
        int cont=0;
        int i;
        Nodo temp=cabeza;        
        while (cont<index){
            if(size>cont+1){                
                temp=temp.obtenerSiguienteNodo();
                cont++;
            }
            else
                return null;
        }
        if(temp!=null){
            i=(int)temp.obtenerLine();
            setLine(i);
            setName(String.valueOf(temp.obtenerNombre()));
            setType(String.valueOf(temp.obtenerType()));
        }
        return temp.obtenerType(); //Valor para enviar a las listas Token
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
         
    public void vaciar(){
    cabeza=null;
    size=0;
    }
     
    public void agregaPrimerolListaTokens(Object c,Object t, Object v,Object l){
        if (estaVacialListaTokens()){
            cabeza= new Nodo(c,t,v,l);
        }
        else{                        
            Nodo temp=cabeza;            
            Nodo nuevo=new Nodo(c,t,v,l);
            nuevo.enlazarAlNodo(temp);
            cabeza=nuevo;
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
