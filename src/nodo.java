class nodo {
    String lexema="";
    int renglon;
    int token;
    nodo sig = null;

    nodo(String lexem, int tok, int reng){
    lexema = lexem; //se usa el this para referenciar dentro de la clase a la variable.
    token = tok;
    renglon = reng;
    }//nodo 
     
}//class nodo
