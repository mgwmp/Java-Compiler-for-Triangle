import java.io.RandomAccessFile;
import static java.lang.Character.isDigit;
import static java.lang.Character.isLetter;
import java.io.*;

class Lexicon{
     
     static int caracter;
     static int col, estado = 0, renglon = 0, valormatriz;
     static String lexema="";
     //static nodo P;

    public static void main(String[] args){
        
       int matriztrans[][]={
             //0	1	2	3	4	5	6	7	8	9	10	11	12	13	14	15	16	17	18	19	20	21	22
             //L	D	+	-	*	/	\	>	<	!	=	,	;	:	(	)	"	'	eb	tab 	nl	rc	OC
        /*0*/{1,	2,	102,	103,	104,	3,	4,	5,	6,	7,	108,	115,	116,	8,	118,	119,	9,	10,	0,	0,	0,	0,	501},
        /*1*/{1,	1,	100,	100,	100,	100,	100,	100,	100,	100,	100,	100,	100,	100,	100,	100,	100,	100,	100,	100,	100,	100,	100},
        /*2*/{101,	2,	101,	101,	101,	101,	101,	101,	101,	101,	101,	101,	101,	101,	101,	101,	101,	101,	101,	101,	101,	101,	101},   
        /*3*/{105,	105,	105,	105,	105,	105,	112,	105,	105,	105,	105,	105,	105,	105,	105,	105,	105,	105,	105,	105,	105,	105,	105},
        /*4*/{114,	114,	114,	114,	114,	113,	114,	114,	114,	114,	111,	114,	114,	114,	114,	114,	114,	114,	114,	114,	114,	114,	114},
        /*5*/{106,	106,	106,	106,	106,	106,	106,	106,	106,	106,	110,	106,	106,	106,	106,	106,	106,	106,	106,	106,	106,	106,	106},
        /*6*/{107,	107,	107,	107,	107,	107,	107,	107,	107,	107,	109,	107,	107,	107,	107,	107,	107,	107,	107,	107,	107,	107,	107},
        /*7*/{7,         7,	7,	7,	7,	7,	7,	7,	7,	7,	7,	7,	7,	7,	7,	7,	7,	7,	7,	7,	0,	0,	  7}, 
        /*8*/{117,	117,	117,	117,	117,	117,	117,	117,	117,	117,	120,	117,	117,	117,	117,	117,	117,	117,	117,	117,	117,	117,	117}, 
        /*9*/{9,	9,	9,	9,	9,	9,	9,	9,	9,	9,	9,	9,	9,	9,	9,	9,	121,	9,	9,	9,	500,	500,	  9},
       /*10*/{11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11},
       /*11*/{502,	502,	502,	502,	502,	502,	502,	502,	502,	502,	502,	502,	502,	502,	502,	502,	502,	122,	502,	502,	502,	502,	502}
        };//Fin de la Matriz
        
        String errores [][]={
            /*eol unexpected*/{"500","eol unexpected"},
            /*invalid symbol*/{"501","invalid symbol"},
            /*expected*/{"502","expected"},
        };//errores
                
        RandomAccessFile lecturaarchivo = null;
        
        String nombreArchivo = "E:\\Documents\\NetBeansProjects\\Compilador\\src\\prueba.tri";
         
        try{
            lecturaarchivo = new RandomAccessFile(nombreArchivo,"r");
         while(caracter != -1){
             caracter = lecturaarchivo.read();
             
          	  if(isLetter(caracter)){
                  	col = 0;
        	  }else if(isDigit(caracter)){
                      col = 1;  
                  }else{
                      switch(caracter){
                                case '+': col = 2;
                                break;
                                case '-': col = 3;
                                break;
                                case'*': col = 4;
                                break;
                                case'/': col = 5;
                                break;
                                case 92: col = 6;
                                break;
                                case'>': col = 7;
                                break;
                                case'<': col = 8;
                                break;
                                case'!': col = 9;
                                break;
                                case'=': col = 10;
                                break;
                                case',': col = 11;
                                break;
                                case';': col = 12;
                                break;
                                case':': col = 13;
                                break;
                                case'(': col = 14;
                                break;
                                case')': col = 15;
                                break;
                                case'"': col = 16;
                                break;
                                case 39 : col = 17;
                                break;
                                case ' ': col = 18;
                                break;
                                case 9: col = 19;
                                break;
                                case 10: col = 20;
                               // Renglon = Renglon+1;
                                break;
                                case 13: col = 21;
                                break;
                                default: col = 22;
                                break;
                            }//switch
			}//else si es digito
                   valormatriz = matriztrans[estado][col];
                  if (valormatriz <100) {
                       estado = valormatriz;
                       if (estado == 0) {
                          lexema = "";
                      }else{
                          lexema = lexema + (char) caracter;
                       }
                    }else if (valormatriz >=100 && valormatriz <500 ){
                            if (valormatriz == 100) {
                              reservoirwords();
                            }
                            if(valormatriz==100||valormatriz==101||valormatriz==105||valormatriz==114||valormatriz==106||valormatriz==107||valormatriz==117||valormatriz>=200){
                                lecturaarchivo.seek(lecturaarchivo.getFilePointer()-1);
                            }else{
                                lexema = lexema + (char)caracter;
                            }
                            System.out.println(lexema+" "+valormatriz+" "+ renglon);
                            estado = 0;
                            lexema = "";
                        }else{
                            System.out.println("Error");
                        }  
                }//while
                    }catch (FileNotFoundException ex) {
                       System.out.println("No se puede abrir archivo ' " + nombreArchivo + " ' ");
                    }catch (IOException ex) {
                      System.out.println("error leyendo el archivo ' " + nombreArchivo + " ' ");
                    }finally{
                      try {
                        if (lecturaarchivo != null) {
                          lecturaarchivo.close();
                            }
                          } catch (IOException e) {
                      }
                    }
                 }//main
public static void reservoirwords(){
        String matrizreservoir[][]=
        {     
            {"200","let"},
            {"201","begin"},
            {"202","while"},
            {"203","do"},
            {"204","else"},
            {"205","end"},
            {"206","if"},
            {"207","in"},
            {"208","then"},
            {"209","var"},
            {"210","true"},
            {"211","false"},
            {"212","put"},
            {"213","get"},
            {"214","integer"},
            {"215","char"},
            {"216","string"}
        };
               for (int i = 0; i < matrizreservoir.length; i++) {
                   if(lexema.equals(matrizreservoir[i][1])){
                       int MPR = Integer.parseInt(matrizreservoir[i][0]);
                       valormatriz=MPR;
                   }//if
               }//for
}//method reserved words

}//class