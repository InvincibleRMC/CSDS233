public class Node{
    private String name;
    private int length;
    private Node edge;
    private boolean end;

    // Road
    public Node(String name, int length){
        this.name = name;
        this.length = length;
        this.end =true;
        this.edge=null;
    }
    // Building
    public Node(String name){
        this.name = name;
        this.length = 0;
        this.end=true;
        this.edge=null;
    }

    public String getName(){
        return name;
    }
    public int getLength(){
        return length;
    }
    public Node getEdge(){
        return edge;
    }

    public boolean isEnd(){
        return end;
    }

    public void setName(String name){
        this.name= name;
    }
    public void setLength(int length){
        this.length=length;
    }
    public void setEnd(boolean end){
        this.end= end;
    }
    public void setEdge(Node n){
        this.edge=n;
    }

    // Finds the next Node when a collision occurs
    public Node findNextBuilding(){

        Node temp= getEdge();

        while(!isEnd()){
            temp =temp.getEdge();
        }

        return temp;

    }

    public Node findEnd(){
        Node temp =getEdge();

        while(temp.getEdge() != null){
            temp = temp.getEdge();
        }
        return temp;
    }
    

}