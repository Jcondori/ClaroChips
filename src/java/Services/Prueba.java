package Services;

public class Prueba {

    public static void main(String[] args) {
        String Inicio, Fin, Valor;
        int start, end;
        Inicio = "895110163917654100";
        Fin = "895110163917654299";
        Valor = Inicio.substring(0, 9);
        start = Integer.valueOf(Inicio.substring(Inicio.length() - 9));
        end = Integer.valueOf(Fin.substring(Fin.length() - 9));
        System.out.println(Inicio);
        while (start <= end) {
            System.out.print(Valor);
            System.out.println(start);
            start++;
        }
        System.out.println(Fin);
    }

}
