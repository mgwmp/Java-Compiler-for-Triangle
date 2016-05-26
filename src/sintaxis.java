public class sintaxis {

    public lexico lexicomain = new lexico();//creación del objeto lista
    nodo p;
    String numeroerror;
  //  boolean error = false;

    sintaxis(nodo cabeza) {
      
        while(p!=null){
            if (p.token==200) {
                gop();;
                block();
            }else{
                numeroerror="503";
                errorsintax();
                System.out.println("en el renglon"+p.renglon);
                System.exit(0);
            }
        }
    
    }//ok
    private void block() {
       variabledeclare();
       block1();
    }//ok
    private void variabledeclare() {
        if (p.token==209) {
            gop();
            if (p.token==100) {
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
        if (p.token == 214 || p.token == 215 || p.token == 216) {
            gop();
        }else{
            numeroerror="508";
            errorsintax();
            System.out.println(" En el renglon "+p.renglon);
            System.exit(0);
        }
    }//ok
    private void block1() {
        if (p.token == 207) {
            gop();
            if(p.token == 100 || p.token == 206 || p.token == 202 || p.token == 213 || p.token == 212) {
               command();
                if (p.token == 116) {
                    gop();
                    if (p.token == 100 || p.token == 206 || p.token == 202 || p.token == 213 || p.token == 212) {
                        command();
                    }
                    if (p.token == 205) {
                        gop();
                    }else{
                        numeroerror="520";
                        errorsintax();
                        System.out.println("En el renglon "+p.renglon);
                        System.exit(0);
                    }
                }else{
                    numeroerror="505";
                    errorsintax();
                    System.out.println(" En el renglon "+p.renglon);
                    System.exit(0);
                }
            }else{
                numeroerror="521";
                errorsintax();
                System.out.println(" En el renglon "+p.renglon);
                System.exit(0);                
            }
        }else{
                numeroerror="509";
                errorsintax();
                System.out.println(" En el renglon "+p.renglon);
                System.exit(0);  
        }
    }//ok
    private void command() {
        if (p.token == 100) {
            gop();
            expression();
        }else{
            if (p.token == 206) {
                gop();
                expression();
                if (p.token == 208) {
                    gop();
                    if (p.token== 201) {
                        gop();
                    }else{
                        numeroerror="519";
                        errorsintax();
                        System.out.println("En el renglon " + p.renglon);
                        System.exit(0);
                    }
                    command();
                    if (p.token == 204) {
                        command();
                        if (p.token == 205) {
                            gop();
                        }else{
                        numeroerror="520";
                        errorsintax();
                        System.out.println("En el renglon " + p.renglon);
                        System.exit(0);
                        }
                    }
                    if (p.token == 205) {
                        gop();
                    }else{
                        numeroerror="520";
                        errorsintax();
                        System.out.println("En el renglon " + p.renglon);
                        System.exit(0);
                    }
                }else{
                        numeroerror="511";
                        errorsintax();
                        System.out.println("En el renglon " + p.renglon);
                        System.exit(0);                
                }
            }else{
                if (p.token == 202) {
                    gop();
                    expression();
                    if (p.token == 203) {
                        gop();
                        if (p.token == 201) {
                            gop();
                        }else{
                        numeroerror="519";
                        errorsintax();
                        System.out.println("En el renglon " + p.renglon);
                        System.exit(0);                            
                        }
                        command();
                        if (p.token == 205) {
                            gop();
                        }else{
                            numeroerror="520";
                            errorsintax();
                            System.out.println("En el renglon " + p.renglon);
                            System.exit(0);                            
                        }
                    }else{
                        numeroerror="512";
                        errorsintax();
                        System.out.println("En el renglon " + p.renglon);
                        System.exit(0);                        
                    }
                }else{
                    if (p.token == 213) {
                        gop();
                        if (p.token== 118) {
                            gop();
                        }else{
                            numeroerror="516";
                            errorsintax();
                            System.out.println("En el renglon " + p.renglon);
                            System.exit(0);                            
                        }
                        if (p.token == 100) {
                            gop();
                        }else{
                            numeroerror="506";
                            errorsintax();
                            System.out.println("En el renglon " + p.renglon);
                            System.exit(0);                        
                        }
                        if (p.token == 119) {
                            gop();
                        }else{
                            numeroerror="515";
                            errorsintax();
                            System.out.println("En el renglon " + p.renglon);
                            System.exit(0);                            
                        }
                    }else{
                        if (p.token == 212) {
                            gop();
                            if (p.token == 118) {
                                gop();
                            }else{
                                numeroerror="516";
                                errorsintax();
                                System.out.println("En el renglon " + p.renglon);
                                System.exit(0);                                
                            }
                            if (p.token == 121) {
                                gop();
                                if (p.token ==115) {
                                    gop();
                                }else{
                                    numeroerror="517";
                                    errorsintax();
                                    System.out.println("En el renglon " + p.renglon);
                                    System.exit(0);                                    
                                }
                                if (p.token == 100) {
                                    gop();
                                }else{
                                    numeroerror="506";
                                    errorsintax();
                                    System.out.println("En el renglon " + p.renglon);
                                    System.exit(0);                                    
                                }
                                if (p.token == 119) {
                                    gop();
                                }else{
                                    numeroerror="515";
                                    errorsintax();
                                    System.out.println("En el renglon " + p.renglon);
                                    System.exit(0);                                    
                                }
                            }
                            if (p.token == 100) {
                                gop();
                            }else{
                                numeroerror="506";
                                errorsintax();
                                System.out.println("En el renglon " + p.renglon);
                                System.exit(0);                                
                            }
                            if (p.token == 119) {
                                gop();
                            }else{
                                numeroerror="515";
                                errorsintax();
                                System.out.println("En el renglon " + p.renglon);
                                System.exit(0);                                
                            }
                        }else{
                            numeroerror="510";
                            errorsintax();
                            System.out.println("En el renglon " + p.renglon);
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
        gop();
        if (p.token==102||p.token==103||p.token==104||p.token==105||p.token==106||p.token==107||p.token==108||p.token==109||p.token==110||p.token==111||
                p.token==112||p.token==113||p.token==114) {
            secundaryexpression2();
        }
    }//ok
    private void primaryexpression() {
            if(p.token==214||p.token==215||p.token==216||p.token==100||p.token==102||p.token==103||p.token==104||p.token==105||p.token==106||p.token==107||p.token==108||p.token==109||p.token==110||p.token==111||
                    p.token==112||p.token==113||p.token==114||p.token==118){
            
            //preguntar si son operadores
            if(p.token==102||p.token==103||p.token==104||p.token==105||p.token==106||p.token==107||p.token==108||p.token==109||p.token==110||p.token==111||
                p.token==112||p.token==113||p.token==114){
                primaryexpression();
            }else{
                
               if(p.token==118){
                   gop();
               }else{
                    numeroerror="516";
                    errorsintax();
                    System.out.println(" En el renglon "+p.renglon);
                    System.exit(0);
                            
               }
                //si no son operadores es ( y manda a llamar a expresion
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
            }
        
        }else{
            numeroerror="514";
            errorsintax();
            System.out.println(" En el renglon "+p.renglon);
            System.exit(0);
                            
        }
    }//ok
    private void secundaryexpression2() {
        if(p.token==102||p.token==103||p.token==104||p.token==105||p.token==106||p.token==107||p.token==108||p.token==109||p.token==110||p.token==111||
           p.token==112||p.token==113||p.token==114){
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
            {"521","Se esperaba un comando"}            
                
        };
        for (int i = 0; i < errores.length; i++) {
            if (numeroerror.equals(errores[i][0])) {
                System.out.println("Error "+errores[i][1]);
            }
        }
    }//ok
    private void gop(){
        if(p==null){
            numeroerror="518";
            errorsintax();
            System.out.println(" En el renglon "+p.renglon);
            System.exit(0);
        }else{
            p=p.sig;
        }
    }//ok

}//class
/*ahí la llevas chavo*/