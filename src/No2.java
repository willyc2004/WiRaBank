import java.util.Arrays;
import java.util.Scanner;

public class No2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        System.out.print("");
        String input = scanner.nextLine();

        System.out.println(pyramidPattern(input));
    }

    public static String pyramidPattern(String input) {
        int N;
        int[] X;

        try {
            String[] inputArr = input.split(" ");
            N = Integer.parseInt(inputArr[0]);

            if (N >= 2 && N <= 100) {
                X = new int[N];

                for (int i = 0; i < N; i++) {
                    try {
                        X[i] = Integer.parseInt(inputArr[i + 1]);
                    } catch (NumberFormatException e) {
                        X[i] = 0;
                    }
                }

                selectionSort(X);
                StringBuilder output = new StringBuilder();

                int rumus = findDasar(X.length);
                int tmp_rumus = rumus;
                int counter = 0;

                for (int i = (X.length - 1); i >= 0; i--) {
                    counter++;
                    output.insert(0, " " + X[i] + "  ");

                    if (i == 0) {
                        int spasi_kiri = rumus - tmp_rumus;
                        for (int j = 0; j < spasi_kiri; j++) {
                            output.insert(0, "  ");
                        }
                    }

                    if (counter == tmp_rumus) {
                        if (i != 0) {
                            int spasi_kiri = rumus - tmp_rumus;
                            for (int j = 0; j < spasi_kiri; j++) {
                                output.insert(0, "  ");
                            }
                        }
                        output.insert(0, "\n");
                        counter = 0;
                        tmp_rumus--;
                    }
                }

                return output.toString();
            } else {
                return "Invalid input for N!";
            }
        } catch (Exception e) {
            return "E:" + e.getMessage();
        }
    }

    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int index = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[index]) {
                    index = j;
                }
            }
            int smallerNumber = arr[index];
            arr[index] = arr[i];
            arr[i] = smallerNumber;
        }
    }

    public static int findDasar(int size) {
        int a = 1;
        int b = 3;
        int c = -1 * ((size * 2) - 2);
        double x1 = -1;
        int D = (b * b) - (4 * a * c);
        if (D > 0) {
            x1 = (-b + Math.sqrt(D)) / (2 * a);
        } else if (D == 0) {
            x1 = (-b + Math.sqrt(D)) / (2 * a);
        } else {
            System.out.println(" Solusi not found!");
        }

        return (int) (Math.ceil(x1) + 1);
    }
}
