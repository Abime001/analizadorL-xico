/*
Archivo: Lexico.java
Materia: LENGUAJES Y AUTÓMATAS II
Programa: 3.2 Analizador Lexico Básico
Descripción: Se realiza el análisis léxico
Fecha: 30-Nov-2021
*/
package clases;
import java.util.LinkedList;
public class Lexico {
    LinkedList<Character> list1=new LinkedList<>();
    LinkedList<Character> list2=new LinkedList<>();
    int line=0;
    Token token = new Token();
      
   
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
                    } else {
                        if(c2 != ' '){
                            System.out.println("Irreconocible");
                            LGUI.t2.append("Irreconocible");
                            LGUI.t2.append("\r\n");
                            list2.clear();
                            continue;
                        }
                    }
                    break;
                
                case 1:
                    if(Character.isLetter(c2) || Character.isDigit(c2))
                        state = 1;
                    else {
                        state = 2;
                        Readout();
                    }
                    break;
                case 2:
                    Readout();
                    String s = GetList2();
                    if(Id.isKeyword(s.trim())){
                        token.setName(s.trim());
                        token.setType("Palabras clave");
                        token.setValor(200);
                        token.setLine(line);
                    }else{
                        token.setName(s.trim());
                        token.setType("Identificador");
                        token.setValor(666);
                        token.setLine(line);
                    }
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                        list2.clear();
                        state = 0;
                        break;
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
                            System.out.println("Irreconocible");
                            LGUI.t2.append("Irreconocible");
                            LGUI.t2.append("\r\n");
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
                        System.out.println("Irreconocible");
                        LGUI.t2.append("Irreconocible");
                        LGUI.t2.append("\r\n");
                        list2.clear();
                        continue;
                    }
                    break;
                
                case 7:

                    if(Character.isDigit(c2))
                        state = 8;
                    else {
                        System.out.println("Irreconocible");
                        LGUI.t2.append("Irreconocible");
                        LGUI.t2.append("\r\n");
                        list2.clear();
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

                case 9:
                    Readout();
                    String s1 = GetList2();
                    token.setName(s1.trim());
                    token.setType("Valores numéricos");
                    token.setValor(666);
                    token.setLine(line);
                    System.out.println(token.toString());
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
                    token.setType("operador ++");
                    token.setValor(666);
                    token.setLine(line);
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                
                case 12://+-
                    token.setName(GetList2().trim());
                    token.setType("Operadores");
                    token.setValor(666);
                    token.setLine(line);
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                
                case 13://+
                    Readout();
                    token.setName(GetList2().trim());
                    token.setType("Operadores");
                    token.setValor(666);
                    token.setLine(line);
                    System.out.println(token.toString());
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
                    token.setType("Operadores");
                    token.setValor(666);
                    token.setLine(line);
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;

                case 16://-=
                    token.setName(GetList2().trim());
                    token.setType("Operadores");
                    token.setValor(666);
                    token.setLine(line);
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;

                case 17://-
                    token.setName(GetList2().trim());
                    token.setType("Operadores");
                    token.setValor(666);
                    token.setLine(line);
                    System.out.println(token.toString());
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
                    token.setType("Operadores");
                    token.setValor(666);
                    token.setLine(line);
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;

                case 20:// *
                    Readout();
                    token.setName(GetList2());
                    token.setType("Operadores");
                    token.setValor(666);
                    token.setLine(line);
                    System.out.println(token.toString());
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
                    token.setType("Operadores");
                    token.setValor(666);
                    token.setLine(line);
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;

                case 23: // /
                    token.setName(GetList2());
                    token.setType("Operadores");
                    token.setValor(666);
                    token.setLine(line);
                    System.out.append(token.toString());
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

                case 25:
                    token.setName(GetList2());
                    token.setType("Operadores");
                    token.setValor(666);
                    token.setLine(line);
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                
                case 26:
                    Readout();
                    token.setName(GetList2());
                    token.setType("Operadores");
                    token.setValor(666);
                    token.setLine(line);
                    System.out.println(token.toString());
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

                case 28:
                    token.setName(GetList2());
                    token.setType("Operadores");
                    token.setValor(666);
                    token.setLine(line);
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 29:
                    token.setName(GetList2());
                    token.setType("Operadores");
                    token.setValor(666);
                    token.setLine(line);
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 30:
                    Readout();
                    token.setName(GetList2());
                    token.setType("Operadores");
                    token.setValor(666);
                    token.setLine(line);
                    System.out.println(token.toString());
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
                case 32:
                    token.setName(GetList2());
                    token.setType("Operadores");
                    token.setValor(666);
                    token.setLine(line);
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 33:
                    token.setName(GetList2());
                    token.setType("Operadores");
                    token.setValor(666);
                    token.setLine(line);
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 34:
                    Readout();
                    token.setName(GetList2());
                    token.setType("Operadores");
                    token.setValor(666);
                    token.setLine(line);
                    System.out.println(token.toString());
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
                case 36:
                    token.setName(GetList2());
                    token.setType("Operadores");
                    token.setValor(666);
                    token.setLine(line);
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 37:
                    Readout();
                    token.setName(GetList2());
                    token.setType("Operadores");
                    token.setValor(666);
                    token.setLine(line);
                    System.out.println(token.toString());
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
                case 39:
                    token.setName(GetList2());
                    token.setType("Operadores");
                    token.setValor(666);
                    token.setLine(line);
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 40:
                    Readout();
                    token.setName(GetList2());
                    token.setType("Operadores");
                    token.setValor(666);
                    token.setLine(line);
                    System.out.println(token.toString());
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
                case 42:
                    token.setName(GetList2());
                    token.setType("Operadores");
                    token.setValor(666);
                    token.setLine(line);
                    System.out.println(token.toString());
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
                case 44:
                    token.setName(GetList2());
                    token.setType("Operadores");
                    token.setValor(666);
                    token.setLine(line);
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 45:
                    Readout();
                    token.setName(GetList2());
                    token.setType("Operadores");
                    token.setValor(666);
                    token.setLine(line);
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;

                case 46:
                    Readout();
                    token.setName(GetList2());
                    token.setType("Operadores");
                    token.setValor(666);
                    token.setLine(line);
                    System.out.println(token.toString());
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
                case 48:
                    token.setName(GetList2());
                    token.setType("Operadores");
                    token.setValor(666);
                    token.setLine(line);
                    System.out.println(token.toString());
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
                case 51:
                    token.setName(GetList2());
                    token.setType("Operadores");
                    token.setValor(666);
                    token.setLine(line);
                    System.out.println(token.toString());
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
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 53:
                    token.setName(GetList2());
                    token.setType("Operadores");
                    token.setValor(666);
                    token.setLine(line);
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 54:
                    Readout();
                    token.setName(GetList2());
                    token.setType("Operadores");
                    token.setValor(666);
                    token.setLine(line);
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 55:
                    Readout();
                    token.setName(GetList2());
                    token.setType("Operadores");
                    token.setValor(666);
                    token.setLine(line);
                    System.out.println(token.toString());
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
                case 58:
                    token.setName(GetList2());
                    token.setType("Operadores");
                    token.setValor(666);
                    token.setLine(line);
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 59:
                    Readout();
                    token.setName(GetList2());
                    token.setType("Operadores");
                    token.setValor(666);
                    token.setLine(line);
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 60:
                    token.setName(GetList2());
                    token.setType("Símbolo del Limite");
                    token.setValor(770);
                    token.setLine(line);
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 61:
                    token.setName(GetList2());
                    token.setType("Símbolo del Límite");
                    token.setValor(770);
                    token.setLine(line);
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 62:
                    token.setName(GetList2());
                    token.setType("Símbolo del Limite");
                    token.setValor(770);
                    token.setLine(line);
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 63:
                    token.setName(GetList2());
                    token.setType("Símbolo del Limite");
                    token.setValor(770);
                    token.setLine(line);
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 64:
                    token.setName(GetList2());
                    token.setType("Símbolo del Limite");
                    token.setValor(770);
                    token.setLine(line);
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 65:
                    token.setName(GetList2());
                    token.setType("Símbolo del Limite");
                    token.setValor(770);
                    token.setLine(line);
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 66:
                    token.setName(GetList2());
                    token.setType("Símbolo del Limite");
                    token.setValor(770);
                    token.setLine(line);
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 67:
                    token.setName(GetList2());
                    token.setType("Símbolo del Limite");
                    token.setValor(770);
                    token.setLine(line);
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 68:
                    token.setName(GetList2());
                    token.setType("Símbolo del Limite");
                    token.setValor(770);
                    token.setLine(line);
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 69:
                    token.setName(GetList2());
                    token.setType("Símbolo del Limite");
                    token.setValor(770);
                    token.setLine(line);
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
                case 70:
                    token.setName(GetList2());
                    token.setType("Símbolo del Limite");
                    token.setValor(770);
                    token.setLine(line);
                    System.out.println(token.toString());
                    LGUI.t2.append(token.toString());
                    LGUI.t2.append("\r\n");
                    list2.clear();
                    state = 0;
                    break;
            }            
        }

    }
    public void imprimeTablaTokens(){
        System.out.println("Tokens guardados...\n\r");
        Object temp=token.getToken();                
        while(temp!=null){                 
            System.out.println("-"+temp);
            temp=token.getToken();
        }                
    }
}
