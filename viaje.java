import java.util.Scanner;

public class viaje{
    static int[][] grafo;
    static int calles;
    static int lugares;
    static int tiempo;
    static int[][] memoria;

    public static int inicializar(){
        Scanner sc = new Scanner(System.in);

        //tomo la cantidad de lugares turisticos
        lugares = sc.nextInt();

        //tomo la cantidad de calles entre los lugares
        calles = sc.nextInt();

        //tomo el tiempo
        tiempo = sc.nextInt();

        //armo el grafo dirigido de las calles (agrando para que los indices coincidan con los valores)
        grafo = new int[lugares+1][calles+1];
        for (int i = 1; i < lugares+1; i++){
            int partida = sc.nextInt();
            int destino = sc.nextInt();
            int t = sc.nextInt();
            grafo[partida][destino] = t;
        }

        //inicializo memoria
        memoria = new int[tiempo+1][lugares+1];
        for (int j = 0; j < tiempo+1; j++){
            for (int k = 0; k < lugares+1; k++){
                memoria[j][k] = -1;
            }
        }
        
        return viaje(tiempo,1);
    }
    private static int viaje(int t, int lugar) {
        int res = 0;
        String camino = "";
        if (t == 0){
            return 0;
        }

        if (memoria [t][lugar] != -1){
            return memoria [t][lugar];
        }

        //tengo que buscar el max de esto
        for (int destino = 0; destino < calles+1; destino++){
            int costo = grafo[lugar][destino];
            //veo si existe la calle y si tengo tiempo para tomarla
            if(costo != 0 && t-costo >= 0){
                res = Math.max(1 + viaje(t - grafo[lugar][destino],destino), res);
            }
        }

        memoria[t][lugar] = res;
        return res;
    }

    public static void main(String[] args){
        System.out.println(inicializar());
    }
}