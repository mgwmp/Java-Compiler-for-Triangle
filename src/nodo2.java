public class nodo2 {
    String type="";
    String lexema="";
    String dato = "";
    nodo2 nextRight;
    

    nodo2(String typ, String lex, String dat){
    type = typ; //se usa el this para referenciar dentro de la clase a la variable.
    lexema = lex;
    dato = dat;
    
    }//nodo
        public void setVariableName(String nombre){
        lexema = nombre;
    }
    public String getVariableName(){
        return lexema;
    }
    
    public void setVariableDato(String dato){
         this.dato = dato;
    }
     public String getVariableDato(){
         return  dato;
    }
    
    public void setVariableTipo(String tipo){
        this.type = tipo;
    }
    
    public String getVariableTipo(){
        return type;
    }
    
    public void setNuevoNodo(nodo2 nodoaux){
        this.nextRight = nodoaux;
    }
    

}
