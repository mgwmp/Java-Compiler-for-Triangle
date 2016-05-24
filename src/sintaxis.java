public class sintaxis {

    public lexico lexicomain = new lexico();//creación del objeto lista
    nodo p;
    String numeroerror;
  //  boolean error = false;

    sintaxis(nodo cabeza) {
      
        while(p!=null){
            if (p.token==200) {
                p=p.sig;
                block();
            }else{
                numeroerror="503";
                errorsintax();
                System.out.println("en el renglon"+p.renglon);
                System.exit(0);
            }
        }
    
    }
    private void block() {
       variabledeclare();
        if (p.token==116) {
            p=p.sig;
            block1();
        }else{
            numeroerror="505";
            errorsintax();
            System.out.println("En el renglon" + p.renglon);
            System.exit(0);
        }       
    }
    private void variabledeclare() {
        if (p.token==209) {
            p=p.sig;
            if (p.token==100) {
                p=p.sig;
                if (p.token==117) {
                    p=p.sig;
                    methodtype();
                    if (p.token == 116) {
                        p=p.sig;
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
    }//delcaracion variables
    private void methodtype() {
        if (p.token==214||p.token==215||p.token==216) {
            p=p.sig;
        }else{
            numeroerror ="508";
            errorsintax();
            System.out.println("En el renglon"+p.renglon);
            System.exit(0);
        }
    }
    private void block1() {
        if(p.token==207){
            p=p.sig;
        }else{
            numeroerror="509";
            errorsintax();
            System.out.println("En el renglon" + p.renglon);
            System.exit(0);
            command();
        }
    }
    private void errorsintax() {
        String[][] errores ={
            {"503","Se esperaba let "},
            {"504","Se esperaba var "},
            {"505","Se esperaba ; "},
            {"506","Se esperaba un id "},
            {"507","Se esperaba : "},
            {"508","Declaracion de variable invalida "},
            {"509","Se esperaba un in "},
            {"510","Invalid Command"},
            {"511","Se esperaba un then"},
            {"512","Se esperaba do"}
                
        };
        for (int i = 0; i < errores.length; i++) {
            if (numeroerror.equals(errores[i][0])) {
                System.out.println("Error "+errores[i][1]);
            }
        }
    }//error sintaxis
    private void command() {
        if (p.token == 100) {
           p = p.sig;
           arithmetic();
        }else{
            if (p.token == 206) {
                p = p.sig;
                expression();
                if (p.token==208) {
                    p = p.sig;
                    command();
                }else{
                    numeroerror = "511";
                    errorsintax();
                    System.out.println("En el renglon " + p.renglon);
                    System.exit(0);
                }
            }else{
                if (p.token == 202) {
                    p = p.sig;
                    expression();
                    if (p.token==203) {
                        p = p.sig;
                        command();
                    }else{
                        numeroerror = "512";
                        errorsintax();
                        System.out.println("En el Renglon "+p.renglon);
                        System.exit(0);
                    }
                }else{
                    if (p.token == 213){
                        p=p.sig;
                    }else{
                        if (p.token == 212) {
                            p=p.sig;
                        }else{
                            numeroerror = "510";
                            errorsintax();
                            System.out.println("En el Renglon "+p.renglon);
                            System.exit(0);
                            
                        }
                    }
                }
            }
        }
    }
    private void arithmetic() {

    }


    private void expression() {
        secundaryespression();
    }

    private void primaryexpression() {
        
    }

    private void secundaryespression() {
        primaryexpression();
        secundaryexpression2();
    }
    
    private void secundaryexpression2() {
            
    }


    
}//class
/*ahí la llevas chavo*/