import java.util.Scanner;

public class gasoducto {
    static int[] ruta;
    static long[][] memoria;
    static int consultas;
    static int longitud;
    static int costo_gasoducto;
    static int costo_pilar;
    static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args){
        int consultas = sc.nextInt();

        while (consultas != 0){
            System.out.println(inicializar());
            consultas--;
        }
        sc.close();
    }
    public static long inicializar(){
        //tomo la longitud de la ruta
        longitud = sc.nextInt();

        //tomo el costo de una unidad de gasoducto
        costo_gasoducto = sc.nextInt();

        //tomo el costo de una unidad de pilar
        costo_pilar = sc.nextInt();

        String s = sc.next();
        ruta = new int[longitud];
        for (int i = 0; i < longitud; i++){
            ruta[i] =  s.charAt(i) - '0';
        }

        //inicializo memoria para guardar el costo optimo de la ruta por cada indice
        memoria = new long[longitud][2];
        for (int i = 0; i < longitud; i++){
            for (int j = 0; j < 2; j++){
                memoria[i][j] = -1;
            }
        }

        //sumo los costos de los pilares del inicio 
        return gasoducto(longitud, 1) + (costo_pilar);
    }

    private static long gasoducto(int i, int j) {
        long res = 0;
        if (i == 0){
            return 0;
        }

        if (memoria [i-1][j-1] != -1){
            return memoria [i-1][j-1];
        }

        //un cruce despues del inicio tiene que estar en 1
        if (i == 1){
            if(j==1){
                //quedo en nivel 1
                res = costo_gasoducto + costo_pilar + gasoducto(i-1,1);
            }else if(j==2){
                //bajo
                res = 2 * costo_gasoducto + 2 * costo_pilar + gasoducto(i-1,1);
            }
        //si en la anterior no hay cruce y estoy en un cruce
        }else if(ruta[i-2] == 0 & ruta[i-1] == 1){
            //quedo en nivel 2
            res = costo_gasoducto + 2 * costo_pilar + gasoducto(i-1,2);
        //si en la anterior no hay cruce y no estoy en un cruce
        }else if(ruta[i-2] == 0 & ruta[i-1] == 0){
            if(j==1){
                //quedo en nivel 1 o subo
                res = Math.min(costo_gasoducto + costo_pilar + gasoducto(i-1,1), 2 * costo_gasoducto + costo_pilar + gasoducto(i-1,2));
            }else if(j==2){
                //quedo en nivel 2 o bajo
                res = Math.min(costo_gasoducto + 2 * costo_pilar + gasoducto(i-1,2), 2 * costo_gasoducto + 2 * costo_pilar + gasoducto(i-1,1));
            }
        //si en la anterior hay cruce
        } else if (ruta[i-2] == 1){
            if(j==1){
                //subo a nivel 2
                res = 2 * costo_gasoducto + costo_pilar + gasoducto(i-1,2);
            }else if(j==2){
                //quedo en nivel 2
                res = costo_gasoducto + 2 * costo_pilar + gasoducto(i-1,2);
            }
        }

        memoria[i-1][j-1] = res;
        return res;
    }
}
