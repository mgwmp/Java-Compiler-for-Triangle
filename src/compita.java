import java.io.IOException;
import java.io.*;

public class compita{
         public static void main(String[] args)throws IOException{
               lexico lexicomain = new lexico();
               lexicomain.lexic();
               lexicomain.p = lexicomain.cabeza;
               while (lexicomain.p != null) {
                 System.out.println(lexicomain.p.lexema+" "+lexicomain.p.token+" "+lexicomain.p.renglon);
                 lexicomain.p = lexicomain.p.sig;
             }
               
         }
}//class