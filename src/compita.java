import java.io.IOException;
import java.io.*;

public class compita{
         public static void main(String[] args)throws IOException{
               lexico lexicomain = new lexico();//creación del objeto lista
               lexicomain.lexic();
               
               lexicomain.p = lexicomain.cabeza;//se apunta 
               while (lexicomain.p != null) {
                 System.out.println(lexicomain.p.lexema+" "+lexicomain.p.token+" "+lexicomain.p.renglon);//impresión de la lista;
                 lexicomain.p = lexicomain.p.sig;//aquí se avanza al nodo siguiente
                }//while
        }//main
}//class