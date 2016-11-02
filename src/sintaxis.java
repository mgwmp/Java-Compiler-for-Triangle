import java.util.ArrayList;
public class sintaxis {
        public lexico lexicomain = new lexico();//creación del objeto lista
    nodo p;
    nodo f;
    nodo q;
    nodo2 nodoVariables;
    String numeroerror;
    String lex;
    String typ;
    String tok;
    
    
    String tipo;
    String variable;
    String variabledos;
    
    
    ArrayList<String> arreglovariables = new ArrayList<>();

    ArrayList<nodo3> variableini = new ArrayList<>();
    
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
           f = p.sig;
            if (f.token == 120) {
                variable = p.lexema;
            }
           gop();
            
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
                                       gop();
                                       if(p.token==119){
                                           gop();
                                           if(p.token==116){
                                               gop();
                                               //block();
                                               if(p.token==118){
                                                    numeroerror="521";
                                                    errorsintax();
                                                    System.out.println(" prueba En el renglon "+p.renglon);
                                                    System.exit(0);
                                               }
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
                               }
                           }else{
                                numeroerror="516";
                                errorsintax();
                                System.out.println(" En el renglon "+p.renglon);
                                System.exit(0);
                           }
                       }else{
                            numeroerror="521";
                            errorsintax();
                            System.out.println(" prueba En el renglon "+p.renglon);
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
            p=p.sig;
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
                        asignarValor(valorVariable, nombreVariable);
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
          nodo2 aux = nodoVariables;
          while(aux !=null){
              if (aux.lexema.equals(nombreVariable)) {
                  aux.setVariableDato(dato);
                  break;
              }
              aux = aux.nextRight;
          }
    }
    private void compruebaVacio(){
        nodo aux = p;
        while(aux != null){
            if (aux.token == 120) {
                 aux = aux.sig;
            }
            if (aux.token == 116) {
                System.out.println("Error espacio en blanco " + aux.renglon);
                System.exit(0);
            }
            aux = aux.sig;
        }
        

    }
    

    
}//class
