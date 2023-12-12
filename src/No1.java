import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class No1 {
    public static void main(String[] args) {
        int N,X,Y;
        List<Double> n_sudut = new ArrayList();
        String input1 = new Scanner(System.in).nextLine();
        String[] input_arr = input1.split(" ");
        if(input_arr.length < 3) {
            System.out.println("Invalid input!");
            return;
        }
        try{
            N = Integer.parseInt(input_arr[0]);
            if(N > 100) {
                System.out.println("Invalid N input!");
                return;
            }
            X = Integer.parseInt(input_arr[1]);
            Y = Integer.parseInt(input_arr[2]);
        } catch(NumberFormatException e) {
            System.out.println("E:"+e.getMessage());
            return;
        }

        String input2;
        int count =0;
        for(int i=0; i < N; i++){
            input2 = new Scanner(System.in).nextLine();
            String[] input_arr2 = input2.split(" ");
            if(input_arr2.length == 2) {
                int pos_asteroid_x = Integer.parseInt(input_arr2[0]);
                int pos_asteroid_y = Integer.parseInt(input_arr2[1]);
                double bagi;
                try{
                    double atas = (X - pos_asteroid_x);
                    double bawah = (Y - pos_asteroid_y);
                    bagi = atas/bawah;
                } catch(Exception e) {
                    bagi = 0;
                }
                if(!n_sudut.contains(bagi)){
                    n_sudut.add(bagi);
                    count++;
                }
            }
        }
        System.out.println("Jumlah asteroid yang terlihat : " + count);
    }
}
