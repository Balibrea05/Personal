package tienda;

import java.util.Scanner;

public class Tienda {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        String nombre_cliente;
        String logo= "--------------------------------\n"+
        "----Bienvenido a Flores.com-----\n"+
        "--------------------------------\n"+
        " \n"+
        "              .-~~-.--.\n"+
        "             :         )\n"+
        "      .~ ~ -.\\       /.- ~~ .\n"+
        "     >       `.   .'       <\n"+
        "      (         .- -.         )\n"+
        "       `- -.-~  `- -'  ~-.- -'\n"+
        "         (        :        )           _ _ .-:\n"+
        "          ~--.    :    .--~        .-~  .-~  }\n"+
        "              ~-.-^-.-~ \\_      .~  .-~   .~\n"+
        "                         `.`.    //\n"+
        "                . - ~ ~-.__`.`-.//\n"+
        "          .-~   . - ~  }~ ~ ~-.~-.\n"+
        "        .' .-~      .-~       :/~-.~-./:\n"+
        "       /_~_ _ . - ~                 ~-.~-._\n"+
        "                                          ~-.<\n";
        
        double total_sin_IVA,coste_IVA,total_con_IVA,recargo_plazos,recargo_pago,coste_plazo,tasa_IVA=21;
        int plazos,forma_pago,espera;
        
        String[] nombres= {"Amapola","Rosa","Clavel","Margarita",
                "Tulipan"};
        int[] stocks={40,35,40,20,30};
        int[] cantidad = new int[stocks.length];
        double[] precios={2.0,4.0,3.5,2.0,1.5};
    
        
       System.out.println(logo);
        System.out.println("=========================================");
        System.out.println("Tabla de precios");
        for(int i=0;i<nombres.length;i++){
            System.out.println(nombres[i]+"______"+precios[i]);
        }
        System.out.println("=========================================");
            
        
        boolean hay_cola=true;
        int j=0;
        boolean hay_stock=false;
        
        while(j<stocks.length && !hay_stock){
            if(stocks[j]>0){
                hay_stock=true;
            }
            j++;
        }
        while(hay_stock && hay_cola){
            
            for (int i=0;i<stocks.length;i++){
                if(stocks[i]>0){
                do{
                    System.out.println("¿Cuantos "+nombres[i]+" quieres? Disponemos de "+stocks[i]);
                    cantidad[i]=teclado.nextInt();
                }while(cantidad[i]<0 || cantidad[i]>stocks[i]);
            }else{
                cantidad[i]=0;
               }
            }
            j=0;
            boolean no_hay_compra=true;
            while(j<cantidad.length && no_hay_compra){
                if(cantidad[j]>0){
                    no_hay_compra=false;
                }
            }
            if(no_hay_compra){
                System.out.println("No ha elegido ningun producto, sentimos no"
                        + " ofrecerle nada de su agrado hasta la proxima");
            }else{
                teclado.nextLine();
                do{
                    System.out.println("Escriba su nombre completo:");
                    nombre_cliente=teclado.nextLine();
                }while(nombre_cliente.equals(""));
                
                total_sin_IVA=0;
                for(int i=0;i<precios.length;i++){
                    total_sin_IVA+=precios[i]*cantidad[i];
                }
                coste_IVA=total_sin_IVA*tasa_IVA/100;
                total_con_IVA=total_sin_IVA+coste_IVA;

                for(int i=0;i<stocks.length;i++){
                    stocks[i]-=cantidad[i];
                }

               System.out.println("=========================================");
               System.out.println("Resumen compra");
               System.out.println(nombre_cliente);
               for(int i=0;i<nombres.length;i++){
                   System.out.println(nombres[i]+"______"+cantidad[i]+" unidades");
               }
               System.out.println("Total sin IVA:"+total_sin_IVA+"€");
               System.out.println("Coste IVA:"+coste_IVA+"€");
               System.out.println("Tota a pagar:"+total_con_IVA+"€");
               System.out.println("=========================================");

               

               
               do{
                   System.out.println("¿Como va a pagar?\n1)En efectivo\n2)Tarjeta");
                   forma_pago=teclado.nextInt();
               }while(forma_pago!=1 && forma_pago!=2);

               if(forma_pago==2){
                   recargo_pago=total_con_IVA*0.02;
                   System.out.println("Hay recargo por pago con tarjeta de "+recargo_pago+"€");
                   total_con_IVA+=recargo_pago;
                   System.out.println("Total a pagar es de "+total_con_IVA+"€");
               }
               
               do{
                   System.out.println("¿En cuantos plazos desea pagar?(1-24 plazos maximo)");
                   plazos=teclado.nextInt();
               }while(plazos<=0 || plazos>24); 
               
               if(plazos>1){
                   recargo_plazos=total_con_IVA*(plazos-1)*0.01;
                   System.out.println("Hay recargo por pago a plazos de "+recargo_plazos+"€");
                   total_con_IVA+=recargo_plazos;
                   System.out.println("Total a pagar es de "+total_con_IVA+"€");
                   coste_plazo=total_con_IVA/plazos;
                   System.out.println("Vas a pagar en "+plazos+" y cada plazo es de "+coste_plazo+"€");
               }

               

               System.out.println("Gracias por su compra¡¡¡");
            }
            
            j=0;
            hay_stock=false;
        
        while(j<stocks.length && !hay_stock){
            if(stocks[j]>0){
                hay_stock=true;
            }
            j++;
        }
           if(hay_stock){
                do{
                    System.out.println("¿Hay alguien más esperando?\n1)Sí\n2)No");
                    espera=teclado.nextInt();
                }while(espera!=1 && espera!=2);
                if(espera==2){
                    hay_cola=false;
                }
            }
        }
        
        
        if(hay_cola){
            System.out.println("Se no han acabado la existencias");
            System.out.println("Lo sentimos mucho intentaremos estar vuelta lo antes posible");
        }else{
            System.out.println("Hemos atentido a todos nuestros clientes");
        }
    }
    
}



