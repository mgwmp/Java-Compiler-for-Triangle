import java.io.IOException;
import java.io.*;

public class compita{
         public static void main(String[] args)throws IOException{
               lexico a = new lexico();
               a.lexic();
               a.p = a.cabeza;
               while (a.p != null) {
                 System.out.println(a.p.lexema+" "+a.p.token+" "+a.p.renglon);
                 a.p = a.p.sig;
             }
               
         }
}//class