import java.io.IOException;
import java.io.*;

public class compita{
         public static void main(String[] args)throws IOException{
               lexico a = new lexico();
               a.lexic();
               lexico.p=lexico.cabeza;
               while (lexico.p!=null) {
                 System.out.println(lexico.p.lexema+" "+lexico.p.token+" "+lexico.p.renglon);
             }
               
         }
}//class