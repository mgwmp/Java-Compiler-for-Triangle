public class nodo3 {
    String tipo="";
    String dato ="";
    String variable="";
    
    nodo3 sig = null;

    nodo3(String tipo, String variable, String dato){
        this.tipo = tipo; //se usa el this para referenciar dentro de la clase a la variable.
        this.dato = dato;
        this.variable = variable;
    }//nodo 
}