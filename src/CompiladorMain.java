import java.io.FileReader;
import static java.lang.Character.isDigit;
import static java.lang.Character.isLetter;
import java.io.*;

 class pruebota{

    public static void main(String[] args){
        
        int matrizTrans[][]={
                      //L	D	+	-	*	/	\	>	<	!	=	,	;	:	(	)	"	'	eb	tab 	nl	eol	eof	OC
            /*1er fila*/{1,	2,	102,	103,	104,	3,	4,	5,	6,	7,	108,	115,	116,	8,	118,	119,	9,	10,	0,	0,	0,	0,	0,	500},
            /*2da fila*/{1,	1,	100,	100,	100,	100,	100,	100,	100,	100,	100,	100,	100,	100,	100,	100,	100,	100,	100,	100,	100,	100,	100,	100},
            /*3er fila*/{101,	101,	101,	101,	101,	101,	101,	101,	101,	101,	101,	101,	101,	101,	101,	101,	101,	101,	101,	101,	101,	101,	101,	101},
            /*4ta fila*/{105,	105,	105,	105,	105,	105,	112,	105,	105,	105,	105,	105,	105,	105,	105,	105,	105,	105,	105,	105,	105,	105,	105,	105},
            /*5ta fila*/{114,	114,	114,	114,	114,	113,	114,	114,	114,	114,	111,	114,	114,	114,	114,	114,	114,	114,	114,	114,	114,	114,	114,	114},
            /*6ta fila*/{106,	106,	106,	106,	106,	106,	106,	106,	106,	106,	110,	106,	106,	106,	106,	106,	106,	106,	106,	106,	106,	106,	106,	106},
            /*7ma fila*/{107,	107,	107,	107,	107,	107,	107,	107,	107,	107,	109,	107,	107,	107,	107,	107,	107,	107,	107,	107,	107,	107,	107,	107},
            /*8va fila*/{7,	7,	7,	7,	7,	7,	7,	7,	7,	7,	7,	7,	7,	7,	7,	7,	7,	7,	7,	7,	7,	0,	7,	7},
            /*9na fila*/{117,	117,	117,	117,	117,	117,	117,	117,	117,	117,	120,	117,	117,	117,	117,	117,	117,	117,	117,	117,	117,	117,	117,	117},
            /*10ma fila*/{9,	9,	9,	9,	9,	9,	9,	9,	9,	9,	9,	9,	9,	9,	9,	9,	121,	9,	9,	9,	9,	500,	9,	9},
            /*11va fila*/{11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	122,	11,	11,	11,	11,	11,	11},
            /*12va fila*/{502,	502,	502,	502,	502,	502,	502,	502,	502,	502,	502,	502,	502,	502,	502,	502,	502,	122,	502,	502,	502,	502,	502,	502},
          };//fin matriztransicion
        
        String errores [][]={
            /*eol unexpected*/{"500","eol unexpected"},
            /*invalid symbol*/{"501","invalid symbol"},
            /*expected*/{"502","expected"},
        };//errores
                
        String nombreArchivo = "E:\\Documents\\NetBeansProjects\\Compilador\\src\\prueba.tri";
        
                
        int caracter;
        int col;
        String lexema ="";
        
        BufferedReader lecturaenbufer = null;
                
        try{
        	FileReader lecturaarchivo = new FileReader(nombreArchivo);
        	lecturaenbufer = new BufferedReader(lecturaarchivo);
         while ((caracter = lecturaenbufer.read()) != -1/*end of file*/) {
             lexema = lexema + (char)caracter;
           //  System.out.println(lexema);
             //System.out.println((char)caracter);
			  if(isLetter(caracter)){
			  	col = 0;
			  }else{
			  	if(isDigit(caracter)){
			  		col = 1;
			  	}else{

			  		switch(caracter){
			  			case '+': col = 2;
                                                break;
                                                case '-': col = 3;
                                                break;
                                                case '*': col = 4;
                                                break;
                                                case '/': col = 5;
                                                break;
                                             /*   case '\ ': col = 6;
                                                break;*/
                                                case '>': col = 7;
                                                break;
                                                case '<': col = 8;
                                                break;
                                                case '!': col = 9;
                                                break;
                                                case '=': col = 10;
                                                break;
                                                case ',': col = 11;
                                                break;
                                                case ';': col = 12;
                                                break;
                                                case ':': col = 13;
                                                break;
                                                case '(': col = 14;
                                                break;
                                                case ')': col = 15;
                                                break;
                                              /*  case '""': col = 16;
                                                break;*/
                                               /* case "''": col = 17;
                                                break;*/
                                              /*  case 'eb': col = 18;
                                                break; */
			  		}			  	}
			  }
         }//while
       }catch (FileNotFoundException ex) {
          System.out.println("No se puede abrir archivo ' " + nombreArchivo + " ' ");
       }catch (IOException ex) {
         System.out.println("error leyendo el archivo ' " + nombreArchivo + " ' ");
       }finally{
         try {
           if (lecturaenbufer != null) {
             lecturaenbufer.close();
           }
         } catch (IOException e) {
           
         }
       }
		
    }//main
public static void reservedwords(){
    String reservadas [][] = {
       /**/{"200","let"},
       /**/{"201","begin"},
       /**/{"202","while"},
       /**/{"203","do"},
       /**/{"204","else"},
       /**/{"205","end"},
       /**/{"206","if"},
       /**/{"207","in"},
       /**/{"208","then"},
       /**/{"209","var"},
       /**/{"210","true"},
       /**/{"211","false"},
       /**/{"212","put"},
       /**/{"213","get"},
       /**/{"214","integer"},
       /**/{"215","char"},
       /**/{"216","string"},       
    };
}//method reserved words
}//class

/*this is only a test a good one!*/
/*Otra prueba */