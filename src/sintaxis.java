import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Stack;
import static java.lang.Character.isDigit;
public class sintaxis {
    public lexico lexicomain = new lexico();//creación del objeto lista
    nodo p;
    nodo f;
    nodo q;
    nodo2 nodoVariables;
    nodoImpreso nodoImpresion;
    nodoImpreso cabeza = null;
    nodoImpreso pp;
    String impreso;
    String numeroerror;
    String lex;
    String typ;
    String tok;
    String CompTipo;
    
    String tipo;
    String variable, variable2;
    String variabledos;
    
    String header;
    String body;
    String footer;
    String code ="";
    String variableconcatenadas = "";
    String codecomplete;
    Boolean conca1 = false;
    Boolean conca2 = false, concate = false;
    Boolean expresion2 = false;
    
    String CompTipo2;
    int contador = 0;
    
    ArrayList<String> arreglovariables = new ArrayList<>();

    ArrayList<nodo3> variableini = new ArrayList<>();
    
    ArrayList<nodoImpreso> arrayImpreso = new ArrayList<nodoImpreso>();
    
    //POLACA
    String codigoExpresion = "";
    Boolean expresion = false, exp = false;
    
    ArrayList<String> array_postfijo = new ArrayList<String> ();
    
    sintaxis(nodo cabeza) {
        p = cabeza;
        q = cabeza;
        while(p!=null){
            if (p.token == 200) {
                gop();
                variabledeclare();
                
                if (p.token == 207) {
                    gop();
                    if (p.token == 100 || p.token == 206 || p.token == 202 || p.token == 213 || p.token == 212|| p.token == 218) {
                        block();
                    }else{
                        numeroerror = "521";
                        errorsintax();
                        System.out.println("En el renglón " + p.renglon);
                        System.exit(0);
                    }
                }else{
                    numeroerror = "509";
                    errorsintax();
                    System.out.println("En el renglón " + p.renglon);
                    System.exit(0);
                }
            }else{
                numeroerror = "503";
                errorsintax();
                System.out.println("En el renglón " + p.renglon);
                System.exit(0);
            }
            if (p.token == 205) {
                System.out.println("Compiled Succesfull");
                imprimeNodo();
                ensamblador();
                aTexto();
                System.exit(0);
                
                
            }else{
                numeroerror = "520";
                errorsintax();
                System.out.println("En el renglón " + p.renglon);
                System.exit(0);
            }
        }

        
    }//ok
    private void variabledeclare() {
        if (p.token==209) {
            gop();
            if (p.token==100) {
              
                dobledeclarada();
                //variable = p.lexema; 
                gop();
                if (p.token==117) {
                    gop();
                    methodtype();
                    if (p.token == 116) {
                        
                        gop();
                        if (p.token == 209) {
                            variabledeclare();
                        }
                    }else{
                        numeroerror="505";
                        errorsintax();
                        System.out.println("En el renglon" + p.renglon);
                        System.exit(0);
                    }
                }else{
                    numeroerror ="507";
                    errorsintax();
                    System.out.println("En el renglon "+p.renglon);
                    System.exit(0);
                }
            }else{
                numeroerror ="506";
                errorsintax();
                System.out.println("En el renglon "+p.renglon);
                System.exit(0);
            }
        }else{
            numeroerror="504";
            errorsintax();
            System.out.println("En el renglon"+p.renglon);
            System.exit(0);
        }
    }//ok
    private void methodtype() {
        
        if (p.token == 214 || p.token == 215 || p.token == 216||p.token == 219) {
            typ = p.lexema;
            
            nodo2 nodo = new nodo2(typ, lex, tok);
            
            if(nodoVariables == null){
                nodoVariables = new nodo2(typ, lex, null);
            }else{
                nodo2 nodoaux = nodoVariables;
                while(nodoaux.nextRight != null){
                    nodoaux = nodoaux.nextRight;
                   
                }
                nodo2 nodoValor = new nodo2(typ, lex, null);
                nodoaux.nextRight = nodoValor;
                
            }
           
            
            gop();
        }else{
            numeroerror="508";
            errorsintax();
            System.out.println(" En el renglon "+p.renglon);
            System.exit(0);
        }
    }//ok
    private void block() {
         if(p.token==100||p.token==206||p.token==202||p.token==213||p.token==212 || p.token==218){
            command();
         }
         if(p.token==100||p.token==206||p.token==202||p.token==213||p.token==212|| p.token==218){
                block();
         }
    }//ok
    private void command() {
        if(p.token==100/*id*/){   
            variablecomp();
            //Aqui preguntamos si el token futuro es un ":=" para agregar a la 
            //variable "variable" el lexema y despues asignarlo en el ensamblador
            if (f.token == 120) {
                variable = p.lexema;
                buscarTipo(variable);
                
            }
            //Aqui preguntamos si lo que sigue es un incremento o decremento para 
            //Guardar la variable y despues en ensamblador hacer "SUMAR variable2 " 
            //Porque la variable "variable" no llega a ese punto ya que solo se 
            //asigna cuando el token futuro es un ":="
            if(f.token == 403 || f.token == 404){
                variable2 = p.lexema;
            }
            if(f.token == 403 || f.token == 404){
                CompTipo = "integer";
                compararTipo(p.lexema);
                CompTipo = "";
            }
            gop();
            if(p.token == 403 || p.token == 404){
                if(p.lexema.equals("+")){
                    code = code + ("SUMAR " + variable2 + ", 1, $1\nMOV AX, $1\nI_ASIGNAR " + variable2 + ",  AX\n\n");
                }else if(p.lexema.equals("-")){
                    code = code + "RESTAR " + variable2 + ", 1, $1\nMOV AX, $1\nI_ASIGNAR " + variable2 + ",  AX\n\n";
                }
                gop();
                if (p.token == 116/*;*/) {
                   
               }else{
                   
               }
            }else
           if(p.token==120 /*:=*/){
               
               gop();
             
               if (p.token == 116/*;*/) {
                   
               }else{
                   
               }
           }else{
                numeroerror="522";
                errorsintax();
                System.out.println(" En el renglon "+p.renglon);
                System.exit(0);
           }
           expression();
           
           if(p.token==116/*;*/){
               
               CompTipo = "";
               variable = "";
               expresion2 = false;
               concate = false;
               gop();
                
            }else{
                numeroerror="505";
                errorsintax();
                System.out.println(" En el renglon "+p.renglon);
                System.exit(0);
            } 
           //block();
       }else{
           if(p.token==206){
               
               gop();
               
                if (p.token==100) {
                    variablecomp();
                    buscarTipo(p.lexema);
                    if (CompTipo.equals("string")) {
                        System.out.println("Error perdida de precisión" );
                        System.exit(0);
                    }
                }
                if(p.token == 101){
                    CompTipo = "integer";
                }
                if(p.token == 121){
                    CompTipo = "string";
                }
                if(p.token == 122){
                    CompTipo = "char";
                }
               expression();
               if(p.token==208){
                   gop();
               }else{
                    numeroerror="511";
                    errorsintax();
                    System.out.println(" En el renglon "+p.renglon);
                    System.exit(0);
               }
               if(p.token==201){
                   gop();
                   command();
                   block();
                   if(p.token==205&&p.sig==null){
                        numeroerror="520";
                        errorsintax();
                        System.out.println("  En el renglon "+p.renglon);
                        System.exit(0);
                   }
               }else{
                    numeroerror="519";
                    errorsintax();
                    System.out.println(" En el renglon "+p.renglon);
                    System.exit(0);
               }
               if(p.token==205){
                   gop();
                   
               }else{
                    numeroerror="520";
                    errorsintax();
                    System.out.println("  En el renglon "+p.renglon);
                    System.exit(0);
               }
               if(p.token==204){
                   gop();
                   if(p.token==201){
                       gop();
                       command();
                       block();
                       if(p.token==205&&p.sig==null){
                            numeroerror="520";
                            errorsintax();
                            System.out.println("  En el renglon "+p.renglon);
                            System.exit(0);
                       }
                       if(p.token==205){
                           gop();
                       }else{
                           numeroerror="520";
                            errorsintax();
                            System.out.println(" En el renglon "+p.renglon);
                            System.exit(0);
                       }
                   }else{
                        numeroerror="519";
                        errorsintax();
                        System.out.println(" En el renglon "+p.renglon);
                        System.exit(0);
                   }
               }
           }else{
               if(p.token==202){
                    gop();
                    if (p.token==100) {
                        variablecomp();
                        buscarTipo(p.lexema);
                    }
                    if(p.token == 101){
                        CompTipo = "integer";
                    }
                    if(p.token == 121){
                        CompTipo = "string";
                    }
                    if(p.token == 122){
                        CompTipo = "char";
                    }
                    
                   expression();
                   if(p.token==203){
                       gop();
                   }else{
                        numeroerror="512";
                        errorsintax();
                        System.out.println(" En el renglon "+p.renglon);
                        System.exit(0);
                   }
                   if(p.token==201){
                       gop();
                       command();
                       block();
                       if(p.token==205 && p.sig==null){
                            numeroerror="520";
                            errorsintax();
                            System.out.println("  En el renglon "+p.renglon);
                            System.exit(0);
                        }
                       //block();
                       if(p.token==205){
                           gop();
                           if(p.token==205&&p.sig==null){
                                numeroerror="520";
                                errorsintax();
                                System.out.println("  En el renglon "+p.renglon);
                                System.exit(0);
                           }
                       }else{
                            numeroerror="520";
                            errorsintax();
                            System.out.println("  En el renglon "+p.renglon);
                            System.exit(0);
                       }
                   }else{
                        numeroerror="519";
                        errorsintax();
                        System.out.println(" En el renglon "+p.renglon);
                        System.exit(0);
                   }
               }else{
                   if(p.token==213){
                       gop();
                       if(p.token==118){
                           gop();
                           
                           if(p.token==100){
                               lex = p.lexema;
                               buscarTipo2(lex);
                               gop();
                           }else{
                                numeroerror="506";
                                errorsintax();
                                System.out.println(" En el renglon "+p.renglon);
                                System.exit(0);
                           }
                           if(p.token==119){
                               gop();
                               if(p.token==116){
                                    //agregaalista();
                                    if(CompTipo2.equals("integer") || CompTipo2.equals("double")){
                                        code += "I_CIN " + lex + "\n\n";
                                    }else if(CompTipo2.equals("string") || CompTipo2.equals("char")){
                                        code += "S_CIN " + lex + "\n\n";
                                    }
                                    gop();
                                }else{
                                    numeroerror="505";
                                    errorsintax();
                                    System.out.println(" En el renglon "+p.renglon);
                                    System.exit(0);
                                }
                               //block();
                           }else{
                                numeroerror="515";
                                errorsintax();
                                System.out.println(" En el renglon "+p.renglon);
                                System.exit(0);
                           }
                       }else{
                            numeroerror="516";
                            errorsintax();
                            System.out.println(" En el renglon "+p.renglon);
                            System.exit(0);
                       }
                       lex = "";
                       CompTipo2 = "";
                   }else{
                       if(p.token==212){
                           gop();
                           if(p.token==118){
                               gop();
                               if(p.token==121){
                                   gop();
                                   if(p.token==115){
                                       gop();
                                       if(p.token==100){
                                           gop();
                                           if(p.token==119){
                                               gop();
                                               if(p.token==116){
                                                    gop();
                                                    //block();
                                                }else{
                                                    numeroerror="505";
                                                    errorsintax();
                                                    System.out.println(" En el renglon "+p.renglon);
                                                    System.exit(0);
                                                }
                                           }else{
                                                numeroerror="515";
                                                errorsintax();
                                                System.out.println(" En el renglon "+p.renglon);
                                                System.exit(0);
                                           }
                                       }else{
                                            numeroerror="506";
                                            errorsintax();
                                            System.out.println(" En el renglon "+p.renglon);
                                            System.exit(0);
                                       }
                                   }else{
                                        numeroerror="517";
                                        errorsintax();
                                        System.out.println(" En el renglon "+p.renglon);
                                        System.exit(0);
                                   }
                               }else{
                                   if(p.token==100){
                                        lex = p.lexema;
                                        buscarTipo2(lex);
                                       gop();
                                       if(p.token==119){
                                           gop();
                                           if(p.token==116){
                                                if(CompTipo2.equals("integer") || CompTipo2.equals("double")){
                                                    code += "I_COUT " + lex + "\n\n";
                                                }else if(CompTipo2.equals("string") || CompTipo2.equals("char")){
                                                    code += "S_COUT " + lex + "\n\n";
                                                }
                                               gop();
                                               //block();
                                               if(p.token==118){
                                                    numeroerror="521";
                                                    errorsintax();
                                                    System.out.println(" prueba En el renglon "+p.renglon + numeroerror);
                                                    System.exit(0);
                                               }
                                           }else{
                                                numeroerror="505";
                                                errorsintax();
                                                System.out.println(" En el renglon "+p.renglon + numeroerror);
                                                System.exit(0);
                                           }
                                       }else{
                                            numeroerror="515";
                                            errorsintax();
                                            System.out.println(" En el renglon "+p.renglon + numeroerror);
                                            System.exit(0);
                                       }
                                   }else{
                                        numeroerror="506";
                                        errorsintax();
                                        System.out.println(" En el renglon "+p.renglon+ numeroerror);
                                        System.exit(0);
                                   }
                                   lex = "";
                                   CompTipo2 = "";
                               }
                           }else{
                                numeroerror="516";
                                errorsintax();
                                System.out.println(" En el renglon "+p.renglon+ numeroerror);
                                System.exit(0);
                           }
                       }else{
                            numeroerror="521";
                            errorsintax();
                            System.out.println(" prueba En el renglon "+p.renglon+ numeroerror);
                            System.exit(0);
                       }
                   }
               }
           }
       }
    }//complicated ok
    private void expression() {
        secundaryexpression();
        
    }//ok
    private void secundaryexpression() {
        primaryexpression();
        if(p.token==102||p.token==103||p.token==104||p.token==105||p.token==106||p.token==107||
                p.token==108||p.token==109||p.token==110||p.token==111||
           p.token==112||p.token==113||p.token==114){
            secundaryexpression2();
        }
    }//ok
    private void primaryexpression() {
        if(p.token==101||p.token==122||p.token==121||p.token==100||p.token==102||p.token==103||
                p.token==104||p.token==105||p.token==106||p.token==107||p.token==108||p.token==109||p.token==110||p.token==111||
           p.token==112||p.token==113||p.token==114||p.token==118||p.token == 402){
            variabledos = p.lexema;
            if (p.token==100) {
                variablecomp();
                compararTipo(p.lexema);
            }
            if(f.token==105 && CompTipo == "integer"){
                    System.out.println("Perdida de precisión en el renglon " + p.renglon);
                      System.exit(0);
                }
            
            //integer 101, string 121, char 122, double
            
            if(p.token == 121){
                compararTipo2("string");
                variableconcatenadas +=  "@impreso"+ contador + " DB  " + p.lexema + ", '$'\n";
               // nodoImpresion = new nodoImpreso( "@impreso" + contador, p.lexema);
                nodoImpreso newnodo = new nodoImpreso("@impreso"+ contador, p.lexema);
                arrayImpreso.add(newnodo);
                
                //  Comienza la concatenacion
                //  Guardamos el primer impreso de nuestra concatenacion
                if( f.lexema.equals("+ ") && conca1 == false){
                    code += "CONCATENAR @impreso" + contador + ", ";
                    conca1 = true;
                }else if(f.lexema.equals("+ ") && conca2 == false){
                    code += "@impreso" + contador + ", $1 \n";
                    conca2 = true;
                }else if(f.lexema.equals("+ ")){
                    code += "CONCATENAR $1,  @impreso" + contador + ",  $1 \n";
                }else if(f.lexema.equals(";") && conca1 == true){ 
                    //  Guardamos la ultima cadena de nuestra concatenacion
                    //  Y tambien asignamos toda la concatenacion
                    if(conca2 == false){
                        code += "@impreso" + contador + ", $1 \n";
                        code += "MOV AX, $1 \n S_ASIGNAR " + variable + ", AX \n\n";
                    }else{
                        code += "CONCATENAR $1, @impreso" + contador + ", $1 \n MOV AX, $1 \n S_ASIGNAR " + variable + ", AX \n\n";
                        concate = true;
                    }
                    
                    conca1 = false;
                    conca2 = false;
                }
                
                //Fin concatenacion
                contador++;
                
                if(f.token==103||f.token==104||f.token==105){
                    System.out.println("Error no puedes hacer - / * ");
                    System.exit(0);
                }
            }
            if(p.token == 122){
                compararTipo2("char");
            }
            
            
            if((CompTipo.equals("integer") || CompTipo.equals("double")) && (f.lexema.equals("+ ") || f.lexema.equals("- ") || f.lexema.equals("*") || f.lexema.equals("/"))){
                codigoExpresion += p.lexema + " ";
                codigoExpresion += f.lexema + " ";
                expresion = true;
                expresion2 = true;
            }else if(p.lexema.equals("(")){
                codigoExpresion += p.lexema + " ";
                gop();
                codigoExpresion += p.lexema + " ";
                if (!f.lexema.equals(";"))
                {
                    codigoExpresion += f.lexema + " ";
                }
            }else if(expresion){
                codigoExpresion += p.lexema + " ";
                if(!f.lexema.equals(";"))
                {
                    if(f.lexema.equals(")"))
                    {
                        codigoExpresion += f.lexema + " ";
                        gop();
                    }
                    codigoExpresion += f.lexema + " ";
                }
                if (CompTipo.equals("integer") && f.lexema.equals(";")){
                    expresion = false;
                    CALCULADORA_POSTFIJO();
                    String var1 = "";
                    String var2 = "";
                    int impresoPolaca = 0;
                    String varTemporal = "";

                    for (int i = 0; i < array_postfijo.size(); i++)
                    {
                        if (array_postfijo.get(i).equals("+") || array_postfijo.get(i).equals("-") || array_postfijo.get(i).equals("/") || array_postfijo.get(i).equals("*"))
                        {
                            var1 = array_postfijo.get(i - 2);
                            var2 = array_postfijo.get(i - 1);

                            if(array_postfijo.get(i).equals("+"))
                            {
                                //SUMAR x, y,$1
                                code += "SUMAR " + var1 + ", " + var2 + ", $" + impresoPolaca + "\n";

                            }
                            else if (array_postfijo.get(i).equals("-"))
                            {
                                //RESTA x, y,$1
                                code += "RESTA " + var1 + ", " + var2 + ", $" + impresoPolaca + "\n";

                            }
                            else if (array_postfijo.get(i).equals("/"))
                            {
                                //DIVIDE x, y,$1
                                code += "DIVIDE " + var1 + ", " + var2 + ", $" + impresoPolaca + "\n";

                            }
                            else if (array_postfijo.get(i).equals("*"))
                            {
                                //MULTI x, y,$1
                                code += "MULTI " + var1 + ", " + var2 + ", $" + impresoPolaca + "\n";
                            }

                            varTemporal = "$" + impresoPolaca;
                            impresoPolaca++;
                            array_postfijo.add(i + 1, varTemporal);
                            array_postfijo.remove(i - 1);
                            array_postfijo.remove(i - 1);
                            array_postfijo.remove(i - 2);

                            i = 0;

                            if (array_postfijo.size() == 1)
                            {
                                code += "MOV AX, " + array_postfijo.get(i) + "\n I_ASIGNAR " + variable + ", AX \n\n";
                                exp = true;
                                break;
                            }
                        }
                        //AL FINAL MOV AX, $1 I_ASIGNAR z, AX
                    }
                }
            }
            if(p.token == 101){
                compararTipo2("integer");
                if(expresion2 == false){
                    code += "MOV AX, " + p.lexema + "\nI_ASIGNAR " + variable + " AX\n\n";
                    
                }
            }
            if(p.token == 402){
                compararTipo2("double");
                if(expresion2 == false){
                    code += "MOV AX, " + p.lexema + "\nI_ASIGNAR " + variable + " AX\n\n";
                }
            }
            if(p.token == 121 && conca1 == false && concate == false){
                for(nodoImpreso aux : arrayImpreso){
                    if(aux.lexema.equals(p.lexema)){
                        impreso = aux.impreso1;
                    }
                }
                if (impreso != null) {
                    code += "S_ASIGNAR " + variable + ",  " + impreso + "\n\n" ;
                }
            }
            
            if (p.token==100) {
                buscarTipo2(p.lexema);
                if(CompTipo2.equals("integer")||CompTipo2.equals("double")){
                    code += "MOV AX, " + p.lexema + "\nI_ASIGNAR " + variable + " AX\n\n";
                }else if(CompTipo2.equals("string")||CompTipo2.equals("char")){
                    code += "MOV AX, " + p.lexema + "\nS_ASIGNAR " + variable + " AX\n\n";
                }
            }
            
            gop();
        }else{
            //preguntar si son operadores
            if(p.token==102||p.token==103||p.token==104||p.token==105||p.token==106||
                    p.token==107||p.token==108||p.token==109||p.token==110||p.token==111||
                p.token==112||p.token==113||p.token==114){
                gop();
                primaryexpression();
            }else{
                if(p.token==118){
                   gop();
                   expression();
                   gop();
                   if(p.token==119){
                       gop();
                   }else{
                        numeroerror="515";
                        errorsintax();
                        System.out.println(" En el renglon "+p.renglon);
                        System.exit(0);
                   }

               }else{
                    numeroerror="516";
                    errorsintax();
                    System.out.println(" En el renglon "+p.renglon);
                    System.exit(0);
                            
               }
            }
        }
    }//ok
    private void secundaryexpression2() {
        if(p.token==102||p.token==103||p.token==104||p.token==105||p.token==106||p.token==107||p.token==108||p.token==109||p.token==110||p.token==111||
           p.token==112||p.token==113||p.token==114){
          
            gop();
        
            primaryexpression();
            secundaryexpression2();
        }
        
    }//ok         
    private void errorsintax() {
        String[][] errores ={
            {"503","Se esperaba let "},
            {"504","Se esperaba var "},
            {"505","Se esperaba ; "},
            {"506","Se esperaba un id "},
            {"507","Se esperaba : "},
            {"508","Declaracion de variable invalida"},
            {"509","Se esperaba in "},
            {"510"," Comando invalido "},
            {"511","Se esperaba then "},
            {"512","Se esperaba do "},
            {"513","Se esperaba operador "},
            {"514","Se esperaba una expresion"},
            {"515","Se esperaba un ) "},
            {"516","Se esperaba un ( "},
            {"517","Se esperaba una  , "},
            {"518","Codigo incompleto"},
            {"519","Se esperaba begin"},
            {"520","Se esperaba end"},
            {"521","Se esperaba un comando"},
            {"522","Se esperaba simbolo de asignacion"},
 
                
        };
        for (int i = 0; i < errores.length; i++) {
            if (numeroerror.equals(errores[i][0])) {
                System.out.println("Error "+errores[i][1]);
            }
        }
    }//ok
    private void gop(){
        if(p.sig==null){
            numeroerror="518";
            errorsintax();
            System.out.println(" En el renglon "+p.renglon);
            System.exit(0);
        }else{
            p = p.sig;
            f = p.sig;
        }
    }//ok
    private void dobledeclarada(){
         if(arreglovariables.contains(p.lexema)){
                    System.out.println("Compilacion Incorrecta la variable " + p.lexema + " ya está declarada ");
                    System.exit(0);
                }else{
                    arreglovariables.add(p.lexema);
                    lex = p.lexema;
                }
    }
    
    private void variablecomp() {
         if(arreglovariables.contains(p.lexema)){
             
        }else{
             System.out.println("Compilacion Incorrecta la variable " + p.lexema + " No existe ");
             System.exit(0);
        }
    }
    private void imprimeNodo(){
          revisarValores();
        nodo2 nodoaux = nodoVariables;
      
        while(nodoaux.nextRight != null){
            System.out.print("Nombre " + nodoaux.lexema);
            System.out.print(" Tipo "+nodoaux.type);
            System.out.println(" Dato "+nodoaux.dato);
            nodoaux = nodoaux.nextRight;
        }
            System.out.print("Nombre " + nodoaux.lexema);
            System.out.print(" Tipo "+nodoaux.type);
            System.out.println(" Dato "+nodoaux.dato);
            
            
        
    }
    private void revisarValores(){ 
       String valorVariable;
       String nombreVariable;
        nodo aux = q;
        while(aux.token != 207){
            if (aux.sig == null) {
                break;
            }
            aux = aux.sig;
        }
        while(aux.sig != null){
            valorVariable = "";
            if (aux.token == 100/*id*/) {
                nombreVariable = aux.lexema;
                aux = aux.sig;
                
                if (aux.token == 120/*:=*/) {
                    aux = aux.sig;

                    while(aux.token != 116/*;*/){
                        if (aux.token != 100/*id*/ ) {
                            valorVariable += aux.lexema;
                        }else{
                            String valorActualVariable = revisarInicializacion(aux.lexema);
                           
                            if(valorActualVariable != null){
                                valorVariable += valorActualVariable;
                                
                            }else{
                                    System.out.println("Error Variable no inicializada " + aux.lexema + " en el renglon " + aux.renglon);
                            }
                        }
                        aux = aux.sig;
                    }
                        //asignarValor(valorVariable, nombreVariable);
                }else{
                    
                    if (revisarInicializacion(nombreVariable)== null) {
                        System.out.println("Error variable no inicializada " + nombreVariable + " en el renglon " + aux.renglon);
                    }
                }
                    
            }else{
                aux = aux.sig;
            }       
        }   
    }
    private String revisarInicializacion(String nombreVariable){
        nodo2  aux = nodoVariables;
        while(aux != null){
            if (aux.lexema.equals(nombreVariable)) {
                return aux.getVariableDato();
            }
            aux = aux.nextRight;
        }
        return null;
    }
    private void asignarValor(String dato,String nombreVariable){
        
        buscarTipo2(nombreVariable);
        if((CompTipo2.equals("integer")|| CompTipo2.equals("double")) && !exp ){
            code += "MOV  AX, " + dato + "\nI_ASIGNAR  " + nombreVariable + ", AX\n";
        } else if(CompTipo2.equals("string") || CompTipo2.equals("char")){
            
            for(nodoImpreso aux : arrayImpreso){
                if(aux.lexema.equals(dato)){
                    impreso = aux.impreso1;
                }
            }
            if (impreso != null) {
                code += "S_ASIGNAR " + nombreVariable + ",  " + impreso + "\n " ;
            }
        }
        exp = false;
        
        nodo2 aux = nodoVariables;
        while(aux !=null){
            if (aux.lexema.equals(nombreVariable)) {
                aux.setVariableDato(dato);
                break;
            }
            aux = aux.nextRight;
        }
    }
    private void buscarTipo(String nombreVariable){
        nodo2  aux = nodoVariables;
        while(aux != null){
            if (aux.lexema.equals(nombreVariable)) {
                CompTipo = aux.type;
            }
            aux = aux.nextRight;
        }
    }
    
    private void buscarTipo2(String nombreVariable){
        nodo2  aux = nodoVariables;
        while(aux != null){
            if (aux.lexema.equals(nombreVariable)) {
                CompTipo2 = aux.type;
            }
            aux = aux.nextRight;
        }
    }
    private void compararTipo2(String nombreVariable){
            if(CompTipo.equals("double") && nombreVariable.equals("integer")){
                
            }else if (!CompTipo.equals(nombreVariable)) {
                System.out.println("Error tipo de variable incompatible " + "en el renglon " + p.renglon);
                System.exit(0);
            }
           
        
    }
    private void compararTipo(String nombreVariable){
        nodo2  aux = nodoVariables;
        while(aux != null){
            /*if(aux.lexema.equals(nombreVariable) && aux.type.equals("integer") && f.token==105){
                    System.out.println("Error no puedes hacer  / ");
                    System.exit(0);
                }*/
            
            if(aux.lexema.equals(nombreVariable) && (f.lexema.equals("+") || f.lexema.equals("-"))&& !aux.type.equals("integer")){
                System.out.println("Error tipo de variable incompatible " + "en el renglon " + p.renglon);
                System.exit(0);
            }
            if (aux.lexema.equals(nombreVariable) && !CompTipo.equals(aux.type)) {
                System.out.println("Error tipo de variable incompatible " + "en el renglon " + p.renglon);
                System.exit(0);
            }
            aux = aux.nextRight;
        }
        
    }
    private void ensamblador(){
        header = "INCLUDE macros.mac \n DOSSEG \n .MODEL SMALL \n STACK 100h \n .DATA \n $BLANCOS\t DB '$' \n $MENOS \t\t DB '-$' \n $COUNT \t\t DW 0 \n $NEGATIVO\tDB\t0 \n $BUFFER\tDB\t8\tDUP('$') \n $BUFFERTEMP DB\t8\tDUP('$')\n $LISTAPAR \tLABEL BYTE \n $LONGMAX \tDB 255\n $TOTCAR \t\t DB ? \n $INTRODUCIDOS \tDB 255 DUP('$') \n $S_TEMP\tDB\t255\tDUP('$')\t;STRING TEMPORAL \n $I_TEMP\tDW\t0000H\t\t\t;INT TEMPORAL \n $CONCAT\tDB\t255\tDUP('$') \n $1\t\tDW\t0000H\t\t\t\n $2\t\tDW\t0000H\n $3\t\tDW\t0000H\n";
        body = ".CODE \n .386 \n BEGIN: \n \t\t MOV\tAX, @DATA \n\t\t MOV\tDS, AX \n\t\t CALL\tCOMPI \n\t\t MOV AX, 4C00H \n\t\t INT 21H \n COMPI  PROC \n \n";
        footer = "\t RET \n COMPI  ENDP \n INCLUDE subs.sub \n END BEGIN \n \n";
        
                  nodo2 aux = nodoVariables;
          while(aux !=null){
              if (aux.type.equals("integer")|| aux.type.equals("double")) {
                 variableconcatenadas += aux.lexema + "  DW  ? \n" ;
              
              }else if(aux.type.equals("string")||aux.type.equals("char")){
                  variableconcatenadas += aux.lexema + "  DB  255  DUP('$') \n" ;
              }              
              aux = aux.nextRight;
              
          }
          codecomplete = header + variableconcatenadas + body + code + footer;
          
    
    }
    private void aTexto(){
        try{
        File archivo = new File("Ensamblador.txt");
        FileWriter escribir = new FileWriter(archivo, true);
        escribir.write(codecomplete);
        escribir.close();
      }catch(Exception e){
            System.out.println("Errore al escribir ");
      }
    }
    
    
    private void CALCULADORA_POSTFIJO() {

        //Depurar la expresion algebraica
        String expr = depurar(codigoExpresion);
        String[] arrayInfix = expr.split(" ");

        //Declaración de las pilas
        Stack < String > E = new Stack <  > (); //Pila entrada
        Stack < String > P = new Stack <  > (); //Pila temporal para operadores
        Stack < String > S = new Stack <  > (); //Pila salida

        //Añadir el arreglo a la Pila de entrada (E)
        for (int i = arrayInfix.length - 1; i >= 0; i--) {
          E.push(arrayInfix[i]);
        }

        try {
          //Algoritmo Infijo a Postfijo
          while (!E.isEmpty()) {
            switch (pref(E.peek())){
              case 1:
                P.push(E.pop());
                break;
              case 2:
                while(!P.peek().equals("(")) {
                  S.push(P.pop());
                }
                P.pop();
                E.pop();
                break;             
              case 3:

              case 4:
                while(pref(P.peek()) >= pref(E.peek())) {
                  S.push(P.pop());
                }
                P.push(E.pop());
                break; 
              case 5:
                  P.push(E.pop());
                  break;
              default:
                S.push(E.pop()); 
            } 
          }

          //Eliminacion de `impurezas´ en la expresiones algebraicas
          String postfix = S.toString().replaceAll("[\\]\\[,]", "");
            for (int i = 0; i < S.size(); i++) {
                array_postfijo.add(S.get(i));
            }
          
          
        }catch(Exception ex){ 
          //try de los errores. 
          System.out.println("Error en la expresión algebraica");
          System.err.println(ex);
        }
      }//main

      //Depurar expresión algebraica
      private static String depurar(String s) {
        s = s.replaceAll("\\s+", ""); //Elimina espacios en blanco
        s = "(" + s + ")";
        String simbols = "+-*/()^";
        String str = "";

        //Deja espacios entre operadores
        for (int i = 0; i < s.length(); i++) {
          if (simbols.contains("" + s.charAt(i))) {
            str += " " + s.charAt(i) + " ";
          }else str += s.charAt(i);
        }
        return str.replaceAll("\\s+", " ").trim();
      }

      //Jerarquia de los operadores
      private static int pref(String op) {
        int prf = 99;
        if (op.equals("^")) prf = 5;
        if (op.equals("*") || op.equals("/")) prf = 4;
        if (op.equals("+") || op.equals("-")) prf = 3;
        if (op.equals(")")) prf = 2;
        if (op.equals("(")) prf = 1;
        return prf;
      }
    
   

}//class
