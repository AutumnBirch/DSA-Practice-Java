package LinearStructure;

class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class MyArrayList {
    private int[] element;
    private int size;

    // 默认初始容量
    private static final int DEFAULT_CAPACITY = 10;

    public MyArrayList(){
        element = new int[DEFAULT_CAPACITY];
        size = 0;
    }
}


