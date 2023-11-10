/*'''Las 2 matrices de prueba seran las siguientes:
La siguiente mastriz tiene una secuencia diagonal CCCCC en la diagonal de
la segunda fila que empieza a la izquierda, y una secuecia CCCC horizontal
en la quinta fila
Matriz mutante: A T G C G A
                C T G T T C
                T C A T G T
                A G C A G G
                C C C C T A
                T C A C C G

La siguiente matriz solo tiene una secuencia vertical GGGG que no alcanza
para ser mutante esta se encuentra en la primera fila, quinta columna
Matriz no mutante: A T G C G A
                   C C G T G C
                   T T A T G T
                   A G A A G G
                   C T C C T A
                   T C A C T G
'''*/
import java.util.ArrayList;
import java.util.Scanner;

public class parcial2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        /*Ciclo while principal del programa que se cortara cuando el usuario desee*/
        while (true) {
            ArrayList<String> mutant_matrix = new ArrayList<String>();
            String string_mutant = "ATCG";

            System.out.println("Ahora vas a ingresar 6 strings de 6 caracteres");
            System.out.println("Estos representarán la secuencia de ADN del mutante o no mutante!");

            // Función para crear la matriz
            creating_matrix(mutant_matrix, string_mutant, sc);

            // Función para saber si la matriz es de un mutante
            boolean mutant = is_mutant(mutant_matrix);

            if (mutant) {
                System.out.println("El humano es un mutante, servirá para luchar contra los X-MEN!");
            } else {
                System.out.println("El humano lamentablemente no es un mutante");
            }

            System.out.print("Ingresa 1 si deseas salir o cualquier otra tecla para reiniciar el programa: ");
            String out = sc.nextLine();

            if (out.equals("1")) {
                System.out.println("Programa finalizando, adiós!...");
                break;
            }
        }
    }

    public static void creating_matrix(ArrayList<String> matrix, String string_mutant, Scanner sc) {
        /*En la funcion para crear la matriz se hacen las respectivas validaciones*/
        for (int i = 0; i < 6; i++) {
            boolean string_mutant_coincidence = false;
            System.out.print("Ingresa el string " + (i + 1) + " conformado por bases nitrogenadas: ");
            String string = sc.nextLine();
            string = string.toUpperCase();

            while (string.length() != 6) {
                System.out.print("La secuencia debe contener 6 caracteres seguidos, ingresa de vuelta: ");
                string = sc.nextLine();
                string = string.toUpperCase();
            }

            while (true) {
                for (char c : string.toCharArray()) {
                    if (string_mutant.indexOf(c) != -1) {
                        string_mutant_coincidence = true;
                    } else {
                        string_mutant_coincidence = false;
                        break;
                    }
                }
                if (string_mutant_coincidence) {
                    break;
                } else {
                    System.out.print("Uno de las bases nitrogenadas no es correcta, ingrese de vuelta: ");
                    string = sc.nextLine();
                    string = string.toUpperCase();

                    while (string.length() != 6) {
                        System.out.print("La secuencia debe contener 6 caracteres seguidos, ingresa bien: ");
                        string = sc.nextLine();
                        string = string.toUpperCase();
                    }
                }
            }

            matrix.add(string);
        }
    }

    public static boolean is_mutant(ArrayList<String> dna) {

        int mutant_secuence = 0;
        /*Aca se chequean las secuencias horizontales*/

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                int coincidences = 0;
                int auxiliar_variablej = j;

                for (int k = 0; k < 3; k++) {
                    if (dna.get(i).charAt(auxiliar_variablej + 1) == dna.get(i).charAt(auxiliar_variablej)) {
                        coincidences++;
                    } else {

                    }
                    auxiliar_variablej++;
                }

                if (coincidences == 3) {
                    mutant_secuence++;
                }
            }
        }

        /*Aca se chequean las secuencias verticales*/

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                int coincidences = 0;
                int auxiliar_variablej = j;

                for (int k = 0; k < 3; k++) {
                    if (dna.get(auxiliar_variablej + 1).charAt(i) == dna.get(auxiliar_variablej).charAt(i)) {
                        coincidences++;
                    } else {

                    }
                    auxiliar_variablej++;
                }

                if (coincidences == 3) {
                    mutant_secuence++;
                }
            }
        }

        /*Aca se chequean las secuencias diagonales*/

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                int coincidences = 0;
                int auxiliar_variablei = i;
                int auxiliar_variablej = j;

                // Diagonal principal (izquierda a derecha)
                for (int k = 0; k < 3; k++) {
                    if (auxiliar_variablei < 6 && auxiliar_variablej < 6 && dna.get(auxiliar_variablei).charAt(auxiliar_variablej) == dna.get(i).charAt(j)) {
                        coincidences++;
                        auxiliar_variablei++;
                        auxiliar_variablej++;
                    } else {
                        break;
                    }
                }

                if (coincidences == 3) {
                    mutant_secuence++;
                }

                // Diagonal secundaria (derecha a izquierda)
                coincidences = 0;
                auxiliar_variablei = i;
                auxiliar_variablej = j;

                for (int k = 0; k < 3; k++) {
                    if (auxiliar_variablei < 6 && auxiliar_variablej >= 0 && dna.get(auxiliar_variablei).charAt(auxiliar_variablej) == dna.get(i).charAt(j)) {
                        coincidences++;
                        auxiliar_variablei++;
                        auxiliar_variablej--;
                    } else {
                        break;
                    }
                }

                if (coincidences == 3) {
                    mutant_secuence++;
                }

                // Diagonal superior izquierda a inferior derecha
                coincidences = 0;
                auxiliar_variablei = i;
                auxiliar_variablej = j;

                for (int k = 0; k < 3; k++) {
                    if (auxiliar_variablei < 6 && auxiliar_variablej < 6 && dna.get(auxiliar_variablei).charAt(auxiliar_variablej) == dna.get(i).charAt(j)) {
                        coincidences++;
                        auxiliar_variablei++;
                        auxiliar_variablej++;
                    } else {
                        break;
                    }
                }

                if (coincidences == 3) {
                    mutant_secuence++;
                }

                // Diagonal superior derecha a inferior izquierda
                coincidences = 0;
                auxiliar_variablei = i;
                auxiliar_variablej = j;

                for (int k = 0; k < 3; k++) {
                    if (auxiliar_variablei < 6 && auxiliar_variablej >= 0 && dna.get(auxiliar_variablei).charAt(auxiliar_variablej) == dna.get(i).charAt(j)) {
                        coincidences++;
                        auxiliar_variablei++;
                        auxiliar_variablej--;
                    } else {
                        break;
                    }
                }

                if (coincidences == 3) {
                    mutant_secuence++;
                }
            }
        }

        if (mutant_secuence >= 2) {
            return true;
        } else {
            return false;
        }
    }
}