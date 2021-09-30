package P2_RMC170_Carlstrom;


public class P2_Main {
    
    public static void main(String[] args) {

        System.out.println("Part 2A");
        ReverseLinkList<Integer> lst = new ReverseLinkList<Integer>(9);
        lst.append(9);
        lst.append(3);
        lst.append(10);
        lst.append(1);
        System.out.println(lst);
        lst.reverse();
        System.out.println(lst);

        System.out.println("Part 2B a)");
        CustomQStack<Integer> stack = new CustomQStack<Integer>();

        System.out.println(stack.toString());
        
        for(int i =1; i<5; i++){
            stack.push(i);
        }

        System.out.println(stack.toString());
        stack.pop();
        System.out.println(stack.toString());
        stack.pop();
        System.out.println(stack.toString());
        stack.pop();
        System.out.println(stack.toString());
        System.out.println(stack.isEmpty());
        stack.pop();
        System.out.println(stack.toString());
        System.out.println(stack.isEmpty());
        stack.push(123);
        System.out.println(stack.toString());
        stack.push(246);
        System.out.println(stack.toString());
        stack.pop();
        System.out.println(stack.toString());

        System.out.println("Part 2B b)");
        CustomSQueue<Integer> queue = new CustomSQueue<Integer>();

        for(int i =1; i<5; i++){
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
