/*
Archivo: Lexico.java
Materia: LENGUAJES Y AUTÓMATAS II
Programa: 3.2 Analizador Lexico Básico
Descripción: Se realiza el análisis léxico
Fecha: 30-Nov-2021
*/
package clases;
import java.util.LinkedList;
import java.util.ArrayList;

public class Lexico {
    LinkedList<Character> list1=new LinkedList<>();
    LinkedList<Character> list2=new LinkedList<>();
    int line=0;
    public static Token token = new Token();
      
   
    public Lexico(){
    }
        
    public char Readin(){
        char c=list1.pollFirst();
        list2.offerLast(c);//Toma el primer carácter de la lista1 y lo pone al final de la lista2
        return c;
    }

    public void Readout(){
        char c1=list2.pollLast();
        list1.offerFirst(c1);//Tomar el último carácter de la lista2 y ponerlo al principio de la lista1            
    }

    private String GetList2() { //Obtener el contenido de la lista2
        String s=new String();
        for (int i = 0; i <list2.size() ; i++) {
            char c=list2.get(i);
            s+=c;
        }
        return s;
    }        
    
    private boolean esSimboloFinalLinea(char c2){
        boolean v=false;
        if(Character.isSpaceChar(c2)|| c2=='\t' || c2=='\n')
            v=true;
        return v;
    }

    public void Analizar() {
        line++;
        int state = 0;
        while (!list1.isEmpty()) {            
            char c2 = Readin(); //Si la lista1 no está vacía, recupera el primer carácter de la lista1
            switch (state) {
                case 0:
                    if(Character.isLetter(c2))
                        state = 1;
                    else if(Character.isDigit(c2))
                        state = 3;
                    else if(c2 == '+')
                        state = 10;
                    else if(c2 == '-')
                        state = 14;
                    else if(c2 == '*')
                        state = 18;
                    else if(c2 == '/')
                        state = 21;
                    else if(c2 == '%')
                        state = 24;
                    else if(c2 == '&')
                        state = 27;
                    else if(c2 == '|')
                        state = 31;
                    else if(c2 == '^')
                        state = 38;
                    else if(c2 == '<')
                        state = 41;
                    else if(c2 == '>')
                        state = 47;
                    else if(c2 == '=')
                        state = 57;
                    else if(c2 == '?'){
                        state = 60;
                        Readout();
                    }else if(c2 == ':'){
                        state = 61;
                        Readout();
                    }else if(c2 == '['){
                        state = 62;
                        Readout();
                    }else if(c2 == ']'){
                        state = 63;
                        Readout();
                    }else if (c2 == '('){
                        state = 64;
                        Readout();
                    }else if(c2 == ')'){
                        state = 65;
                        Readout();
                    }else if(c2 == '.'){
                        state = 66;
                        Readout();
                    }else if(c2 == ','){
                        state = 67;
                        Readout();
                    }else if(c2 == '{'){
                        state = 68;
                        Readout();
                    }else if(c2 == '}'){
                        state = 69;
                        Readout();
                    }else if(c2 == ';'){
                        state = 70;
                        Readout();
                    }else if(c2 == '#'){
                        state = 71;                        
                    }else if(String.valueOf(c2).equals("\"")){
                        state = 96;
                        Readout();
                    }else if(String.valueOf(c2).equals("'")){
                        state = 97;
                        Readout();
                    }else if(esSimboloFinalLinea(c2)){
                        list2.removeLast();
                    } else {
                        if(c2 != ' '){
                            //System.out.println("Irreconocible");
                            //LGUI.t2.append("Irreconocible");
                            //LGUI.t2.append("\r\n");
                            list2.clear();
                            continue;
                        }
                    }
                    break;
                
                case 1:
                    if(Character.isLetter(c2) || Character.isDigit(c2)){
                        state = 1;
                        String s = GetList2();
                        if(s.equals("inicia")){
                            state=80; 
                            Readout();
                        }else if(s.equals("termina")){
                            state=81;
                            Readout();
                        }else if (s.equals("continua")){
                            state=82;
                            Readout();
                        }else if (s.equals("true")){
                            state=90;
                            Readout();
                        }
                        else if (s.equals("false")){
                            state=91;
                            Readout();
                        }
                        else if (s.equals("si")){
                            state=92;
                            Readout();
                        }
                        else if (s.equals("entonces")){
                            state=93;
                            Readout();
                        }
                        else if (s.equals("mientras")){
                            state=94;
                            Readout();
                        }
                        else if (s.equals("hacer")){
                            state=96;
                            Readout();
                        }
                        else if (s.equals("sino")){
                            state=97;
                            Readout();
                        }
                    }
                    else{
                            state = 2;
                            Readout();
                        }
                    continue;
                    
                case 2:
                    Readout();
                    String s = GetList2();
                    if(Id.isKeyword(s.trim())){
                        token.setName(s.trim());
                        token.setType("Palabras clave"); //Palabras clave de la sentencia
                        token.setValor(200);
                        token.setLine(line);
                    }else{
                        token.setName(s.trim());
                        token.setType("Identificador");
                        token.setValor(666);
                        token.setLine(line);
                    }
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                        list2.clear();
                        state = 0;
                        continue;
                case 3:
                    if(Character.isDigit(c2))
                        state = 3;
                    else if(c2 == '.')
                        state = 4;
                    else if(c2 == 'e' || c2 == 'E')
                        state = 6;
                    else {
                        state = 9;
                        Readout();
                    }
                    break;

                case 4:
                    if(Character.isDigit(c2))
                        state = 5;
                    else {
                        if(c2 != ' '){
                            /*System.out.println("Irreconocible");
                            LGUI.t2.append("Irreconocible");
                            LGUI.t2.append("\r\n");*/
                            list2.clear();
                            continue;
                        }
                    }
                    break;
                
                case 5:
                    if(c2 == 'e' || c2 == 'E')
                        state = 6;
                    else{
                        state = 9;
                        Readout();
                    }
                    break;

                case 6:
                    if(c2 == '+' || c2 == '-')
                        state = 7;
                    else if(Character.isDigit(c2))
                        state = 3;
                    else {
                        if(c2 != ' ')
                        /*System.out.println("Irreconocible");
                        LGUI.t2.append("Irreconocible");
                        LGUI.t2.append("\r\n");
                        list2.clear();*/
                        continue;
                    }
                    break;
                
                case 7:

                    if(Character.isDigit(c2))
                        state = 8;
                    else {
                        System.out.println("Irreconocible");
                        /*LGUI.t2.append("Irreconocible");
                        LGUI.t2.append("\r\n");
                        list2.clear();*/
                        continue;
                    }
                    break;
                
                case 8:
                    if(Character.isDigit(c2))
                        state = 8;
                    else {
                        state = 9;
                        Readout();
                    }
                    break;

                case 9: //Para valores númericos 
                    Readout();
                    String s1 = GetList2();
                    token.setName(s1.trim());
                    token.setType("Valores numéricos");
                    token.setValor(666);
                    token.setLine(line);
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;

                case 10:
                    if (Character.isDigit(c2))
                        state = 3;
                    else if (c2 == '+') {
                        state = 11;
                        Readout();
                    } else if (c2 == '=') {
                        state = 12;
                        Readout();
                    } else {
                        state = 13;
                        Readout();
                    }
                    break;
                case 11: //++
                    token.setName(GetList2().trim());
                    token.setType("Incremento");
                    token.setValor(666);
                    token.setLine(line);
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                
                case 12://+-
                    token.setName(GetList2().trim());
                    token.setType("Más menos");
                    token.setValor(666);
                    token.setLine(line);
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                
                case 13://+
                    Readout();
                    token.setName(GetList2().trim());
                    token.setType("Operador suma");
                    token.setValor(666);
                    token.setLine(line);
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                
                case 14: 
                    if(Character.isDigit(c2))
                        state = 3;
                    else if(c2 == '-'){
                        state = 15;
                        Readout();
                    }else if(c2 == '='){
                        state = 16;
                        Readout();
                    }else{
                        state = 17;
                        Readout();
                    }
                    break;

                case 15: //--
                    token.setName(GetList2().trim());
                    token.setType("Decremento");
                    token.setValor(666);
                    token.setLine(line);
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;

                case 16://-=
                    token.setName(GetList2().trim());
                    token.setType("Asignación -=");
                    token.setValor(666);
                    token.setLine(line);
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;

                case 17://-
                    token.setName(GetList2().trim());
                    token.setType("Operadores resta");
                    token.setValor(666);
                    token.setLine(line);
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;

                case 18:
                    if(c2 == '='){
                        state = 19;
                        Readout();
                    }else{
                        state = 20;
                        Readout();
                    }
                    break;

                case 19: //*=
                    token.setName(GetList2());
                    token.setType("Asiganción *=");
                    token.setValor(666);
                    token.setLine(line);
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;

                case 20:// *
                    Readout();
                    token.setName(GetList2());
                    token.setType("Operador multiplicación");
                    token.setValor(666);
                    token.setLine(line);
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;

                case 21:
                    if(c2 == '='){
                        state = 22;
                        Readout();
                    }else{
                        state = 23;
                        Readout();
                    }
                    break;

                case 22: // /=
                    token.setName(GetList2());
                    token.setType("Simbolo /= ");
                    token.setValor(666);
                    token.setLine(line);
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;

                case 23: // /
                    token.setName(GetList2());
                    token.setType("Operador divición");
                    token.setValor(666);
                    token.setLine(line);
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state  = 0;
                    break;

                case 24:
                    if(c2 == '='){
                        state = 25;
                        Readout();
                    }else{
                        state = 26;
                        Readout();
                    }
                    break;

                case 25: //%=
                    token.setName(GetList2());
                    token.setType("Simbolo %=");
                    token.setValor(666);
                    token.setLine(line);
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                
                case 26: // %
                    Readout();
                    token.setName(GetList2());
                    token.setType("Operador resto");
                    token.setValor(666);
                    token.setLine(line);
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;

                case 27:
                    if(c2 == '&'){
                        state = 28;
                        Readout();
                    } else if (c2 == '='){
                        state = 29;
                        Readout();
                    }else{
                        state = 30;
                        Readout();
                    }
                    break;

                case 28: // &&
                    token.setName(GetList2());
                    token.setType("AND");
                    token.setValor(666);
                    token.setLine(line);
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 29: // &=
                    token.setName(GetList2());
                    token.setType("Simbolo &=");
                    token.setValor(666);
                    token.setLine(line);
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 30: //
                    Readout();
                    token.setName(GetList2());
                    token.setType("Ampersand");
                    token.setValor(666);
                    token.setLine(line);
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 31:
                    if(c2 == '|'){
                        state = 32;
                        Readout();
                    } else if(c2 == '='){
                        state = 33;
                        Readout();
                    }else {
                        state = 34;
                        Readout();
                    }
                    break;
                case 32: // ||
                    token.setName(GetList2());
                    token.setType("OR");
                    token.setValor(666);
                    token.setLine(line);
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 33:// |=
                    token.setName(GetList2());
                    token.setType("Simbolo |=");
                    token.setValor(666);
                    token.setLine(line);
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 34:// |
                    Readout();
                    token.setName(GetList2());
                    token.setType("Simbolo |");
                    token.setValor(666);
                    token.setLine(line);
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;

                case 35: 
                    if(c2 == '='){
                        state  = 36;
                        Readout();
                    } else {
                        state = 37;
                        Readout();
                    }
                    break;
                case 36: // !=
                    token.setName(GetList2());
                    token.setType("Desigual");
                    token.setValor(666);
                    token.setLine(line);
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 37: // 
                    Readout();
                    token.setName(GetList2());
                    token.setType("Negatición");
                    token.setValor(666);
                    token.setLine(line);
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 38:
                    if(c2 == '='){
                        state = 39;
                        Readout();
                    }else{
                        state = 40;
                        Readout();
                    }
                    break;
                case 39: // ^=
    
                    token.setName(GetList2());
                    token.setType("Simbolo ^=");
                    token.setValor(666);
                    token.setLine(line);
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 40: // ^
                    Readout();
                    token.setName(GetList2());
                    token.setType("Exponente");
                    token.setValor(666);
                    token.setLine(line);
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 41:
                    if(c2 == '='){
                        state = 42;
                        Readout();
                    } else if (c2 == '<'){
                        state = 43;
                    }else{
                        state = 46;
                        Readout();
                    }
                    break;
                case 42: // <=
                    token.setName(GetList2());
                    token.setType("Menor igual que");
                    token.setValor(666);
                    token.setLine(line);
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 43:
                    if(c2 == '='){
                        state = 44;
                        Readout();
                    }else{
                        state = 45;
                        Readout();
                    }
                    break;
                case 44: // <<
                    token.setName(GetList2());
                    token.setType("Asinación compuesto");
                    token.setValor(666);
                    token.setLine(line);
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 45: //<<
                    Readout();
                    token.setName(GetList2());
                    token.setType("Imprimir");
                    token.setValor(666);
                    token.setLine(line);
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;

                case 46: //
                    Readout();
                    token.setName(GetList2());
                    token.setType("Menor que");
                    token.setValor(666);
                    token.setLine(line);
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 47:
                    if(c2 == '='){
                        state = 48;
                        Readout();
                    }else if (c2 == '>')
                        state = 49;
                    else{
                        state = 55;
                        Readout();
                    }
                    break;
                case 48: //>=
                    token.setName(GetList2());
                    token.setType("Mayor que");
                    token.setValor(666);
                    token.setLine(line);
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 49:
                    if(c2 == '>')
                        state = 50;
                    else if(c2 == '='){
                        state = 53;
                        Readout();
                    }else{
                        state = 54;
                        Readout();
                    }
                    break;
                case 50:
                    if(c2 == '='){
                        state = 51;
                        Readout();
                    }else{
                        state = 52;
                        Readout();
                    }
                    break;
                case 51: // >>>=
                    token.setName(GetList2());
                    token.setType("Operadores");
                    token.setValor(666);
                    token.setLine(line);
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 52:
                    Readout();
                    token.setName(GetList2());
                    token.setType("Operadores");
                    token.setValor(666);
                    token.setLine(line);
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 53: //>>>
                    token.setName(GetList2());
                    token.setType("Operadores");
                    token.setValor(666);
                    token.setLine(line);
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 54: // >>
                    Readout();
                    token.setName(GetList2());
                    token.setType("Operadores");
                    token.setValor(666);
                    token.setLine(line);
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 55: // >
                    Readout();
                    token.setName(GetList2());
                    token.setType("Mayor que");
                    token.setValor(666);
                    token.setLine(line);
                      LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 56:
                    break;
                case 57:
                    if(c2 == '='){
                        state = 58;
                        Readout();
                    }else{
                        state = 59;
                        Readout();
                    }
                    break;
                case 58: // == 
                    token.setName(GetList2());
                    token.setType("Igualdad");
                    token.setValor(666);
                    token.setLine(line);
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 59:// = 
                    Readout();
                    token.setName(GetList2());
                    token.setType("Asignación");
                    token.setValor(666);
                    token.setLine(line);
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 60: // ? 
                    token.setName(GetList2());
                    token.setType("Pregunta cierre");
                    token.setValor(770);
                    token.setLine(line);
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 61: //
                    token.setName(GetList2());
                    token.setType("Dos puntos");
                    token.setValor(770);
                    token.setLine(line);
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 62: // [
                    token.setName(GetList2());
                    token.setType("Corchete abre");
                    token.setValor(770);
                    token.setLine(line);
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 63: //
                    token.setName(GetList2());
                    token.setType("Corchete cierra");
                    token.setValor(770);
                    token.setLine(line);
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                
                case 64: // (
                    token.setName(GetList2());
                    token.setType("Paren abre");
                    token.setValor(770);
                    token.setLine(line);
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                
                case 65: //)
                    token.setName(GetList2());
                    token.setType("Paren cierra");
                    token.setValor(770);
                    token.setLine(line);
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                
                case 66:// .
                    token.setName(GetList2());
                    token.setType("punto");
                    token.setValor(770);
                    token.setLine(line);
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                
                case 67:// , 
                    token.setName(GetList2());
                    token.setType("Coma");
                    token.setValor(770);
                    token.setLine(line);
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                
                case 68://{
                    token.setName(GetList2());
                    token.setType("Llave abre");
                    token.setValor(770);
                    token.setLine(line);
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                
                case 69://}
                    token.setName(GetList2());
                    token.setType("Llave cierra");
                    token.setValor(770);
                    token.setLine(line);
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                
                case 70: //; 
                    token.setName(GetList2());
                    token.setType("Punto y coma");
                    token.setValor(770);
                    token.setLine(line);
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                
                case 71:
                    if(Character.isLetter(c2)){
                        state=71;
                        String g = GetList2();
                        if(g.equals("#Entero")){
                            state=73;
                            Readout();
                        }
                        else if (g.equals("#Real")){
                            state=75;
                            Readout();
                        }else if (g.equals("#Bool")){
                            state=77;
                            Readout();
                        }else if (g.equals("#Cad")){
                            state=79;
                            Readout();
                        }                                                 
                    }else{
                        state=666;
                        Readout();
                    }
                    continue;
                    
                    
                case 73:
                    token.setName(GetList2());
                    token.setType("#ENTERO");
                    token.setValor(70);
                    token.setLine(line);
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;

                case 75:
                    token.setName(GetList2());
                    token.setType("#REAL");
                    token.setValor(19);
                    token.setLine(line);
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;

                case 77:
                    token.setName(GetList2());
                    token.setType("#BOOL");
                    token.setValor(110);
                    token.setLine(line);
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                    
                case 79:
                    token.setName(GetList2());
                    token.setType("#CAD");
                    token.setValor(13);
                    token.setLine(line);
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                
                case 80:          
                        String f = GetList2();
                        token.setName(f.trim());
                        token.setType("inicio_bloque"); //Palabras clave de la sentencia
                        token.setValor(202);
                        token.setLine(line);
                        LGUI.t2.append(token.toString());
                        LGUI.t2.append("\r\n");
                        list2.clear();
                        state = 0;
                        break;
                
                case 81:
                        String g = GetList2();
                        token.setName(g.trim());
                        token.setType("fin_bloque"); //Palabras clave de la sentencia
                        token.setValor(204);
                        token.setLine(line);
                        LGUI.t2.append(token.toString());
                        LGUI.t2.append("\r\n");
                        list2.clear();
                        state = 0;
                        break;
                
                case 82:
                        String h = GetList2();
                        token.setName(h.trim());
                        token.setType("siguiente_bloque"); //Palabras clave de la sentencia
                        token.setValor(206);
                        token.setLine(line);
                        LGUI.t2.append(token.toString());
                        LGUI.t2.append("\r\n");
                        list2.clear();
                        state = 0;
                        break;                                                           
                
                case 90:
                        String n = GetList2();
                        token.setName(n.trim());
                        token.setType("TRUE"); //Palabras clave de la sentencia
                        token.setValor(90);
                        token.setLine(line);
                        LGUI.t2.append(token.toString());
                        LGUI.t2.append("\r\n");
                        list2.clear();
                        state = 0;
                        break;                                                           

                case 91:
                        String m = GetList2();
                        token.setName(m.trim());
                        token.setType("FALSE"); //Palabras clave de la sentencia
                        token.setValor(91);
                        token.setLine(line);
                        LGUI.t2.append(token.toString());
                        LGUI.t2.append("\r\n");
                        list2.clear();
                        state = 0;
                        break;                                                           
                    
                case 92:
                        String c1 = GetList2();
                        token.setName(c1.trim());
                        token.setType("SI"); //Palabras clave de la sentencia
                        token.setValor(92);
                        token.setLine(line);
                        LGUI.t2.append(token.toString());
                        LGUI.t2.append("\r\n");
                        list2.clear();
                        state = 0;
                        break;                                                           
                case 93:
                        String c3 = GetList2();
                        token.setName(c3.trim());
                        token.setType("MIENTRAS"); //Palabras clave de la sentencia
                        token.setValor(90);
                        token.setLine(line);
                        LGUI.t2.append(token.toString());
                        LGUI.t2.append("\r\n");
                        list2.clear();
                        state = 0;
                        break;
                        
                case 95:
                        String c5 = GetList2();
                        token.setName(c5.trim());
                        token.setType("HACER"); //Palabras clave de la sentencia
                        token.setValor(90);
                        token.setLine(line);
                        LGUI.t2.append(token.toString());
                        LGUI.t2.append("\r\n");
                        list2.clear();
                        state = 0;
                        break;                                                           
                        
                case 96:
                        String f1 = GetList2();
                        token.setName(f1.trim());
                        token.setType("COMILLAS DOBLES"); //Palabras clave de la sentencia
                        token.setValor(96);
                        token.setLine(line);
                        LGUI.t2.append(token.toString());
                        LGUI.t2.append("\r\n");
                        list2.clear();
                        state = 0;
                        break;                                                           
                        
                case 97:
                        String f2 = GetList2();
                        token.setName(f2.trim());
                        token.setType("COMILLA SIMPLE"); //Palabras clave de la sentencia
                        token.setValor(97);
                        token.setLine(line);
                        LGUI.t2.append(token.toString());
                        LGUI.t2.append("\r\n");
                        list2.clear();
                        state = 0;
                        break;                                                           
                    
                case 666:
                    token.setName(GetList2());
                    token.setType("DESCONOCIDO");
                    token.setValor(7);
                    token.setLine(line);
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                   
            }            
        }

    }

    public void imprimeTablaTokensLex(){
        System.out.println("Tokens guardados...\n\r");
        Object temp=token.getToken();                
        while(temp!=null){                 
            System.out.println("-"+temp);
            temp=token.getToken();
        }
	token.reiniciaGet();                
    }
}
