public class nodoImpreso {
    String impreso1="";
    String lexema="";
    nodoImpreso nextRight;
    nodoImpreso(String impreso, String lex){
    impreso1 = impreso; //se usa el this para referenciar dentro de la clase a la variable.
    lexema = lex;
    }//nodoImpreso
    public void setVariableName(String nombre){
        lexema = nombre;
    }
    public String getVariableName(){
        return lexema;
    }
    public void setVariableImpreso(String impreso){
        this.impreso1 = impreso;
    }
    public String getVariableImpreso(){
        return impreso1;
    }
    public void setNuevoNodo(nodoImpreso nodoaux){
        this.nextRight = nodoaux;
    }
}
