package P2_RMC170_Carlstrom;

public class P2_Main {
    
    public static void main(String[] args) {

        CustomSQueue<Integer> queue = new CustomSQueue<Integer>();
       
       // CustomQStack<Integer> customStack = new CustomQStack<Integer>();

        for(int i =1; i<10; i++){
            queue.add(i);
        }

        System.out.println(queue.toString());
        queue.poll();
        System.out.println(queue.toString());
        queue.poll();
        System.out.println(queue.toString());
        queue.add(2);
        System.out.println(queue.toString());


    }
}
