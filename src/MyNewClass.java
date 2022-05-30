import java.util.*;
public class MyNewClass {

    public static void main(String[] args) {

        Scanner locale= new Scanner( System.in);

        int par1 =locale.nextInt();
        int par2 =locale.nextInt();

        System.out.println( sumMyInt (par1, par2));
    }

    public static int sumMyInt(int par1, int par2) {
         return (par1+par2) ;


    }
}
