package Random;
public class fibonacci  {
    public static void main(String[] args) {

        System.out.println(fib(19));

    }

    static int fib(int n) {
        int fib1 =1;
        int fib2 = 1;
        int current =1;
        for(int i =3;i<=n; i ++){
            current = fib1+fib2;
            fib2 = fib1;
            fib1= current;
        }
        return current;
    }
}
