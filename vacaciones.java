import java.util.Scanner;

public class vacaciones{
    static int[] a;
    static int[][] memoria;

    public static int inicializar(){
        Scanner sc = new Scanner(System.in);

        //i es el numero de dias
        int i = sc.nextInt();

        //tomo la secuencia de actividades
        a = new int[i];
        for (int j = 0; j < i; j++){
            a[j] =  sc.nextInt();
        }

        //inicializo memoria
        //uso 0:descanso, 1:competencia, 2:gimnasio
        memoria = new int[i][3];
        for (int j = 0; j < i; j++){
            for (int k = 0; k < 3; k++){
                memoria[j][k] = -1;
            }
        }
        
        return vac(i-1,0);
    }
    private static int vac(int i, int actividad) {
        int res;
        if (i == -1){
            return 0;
        }

        if (memoria [i][actividad] != -1){
            return memoria [i][actividad];
        }

        res = 1 + vac(i-1,0);

        if (a[i]==1){
            if (actividad !=1){
                res = Math.min(vac(i-1,1), 1 + vac(i-1,0));
            }else{
                res = 1 + vac(i-1,0);
            }
        }

        if (a[i]==2){
            if (actividad !=2){
                res = Math.min(vac(i-1, 2), 1 + vac(i-1,0));
            }else{
                res = 1 + vac(i-1,0);
            }
        }
        
        if (a[i]==3){
            if (actividad==0){
                res = Math.min(vac(i-1,1), Math.min(vac(i-1,2),1 + vac(i-1,0)));
            }else if (actividad==2){
                res = Math.min(vac(i-1,1), 1 + vac(i-1,0));
            }else if (actividad==1){
                res = Math.min(vac(i-1,2), 1 + vac(i-1,0));
            }
        }
        memoria[i][actividad] = res;
        return res;
    }

    public static void main(String[] args){
        System.out.println(inicializar());
    }
}