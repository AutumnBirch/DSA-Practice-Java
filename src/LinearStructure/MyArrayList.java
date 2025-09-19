package LinearStructure;

/**
 * 顺序表的核心：用数组存储数据，并用变量记录实际元素数量
 * 实现思路：
 * 顺序表是一个可以动态管理元素的数组容器，
 * 它需要有地方来存储很多数据，需要知道里面有多少有效数据，需要知道它最多能存储多少数据，故：
 *  定义一个数组来存储顺序表中的数据
 *  定义一个容量来表示该顺序表最多能存储的数据量
 *  定义一个长度来表示该顺序表目前存储了多少数据
 * 因为该顺序表是用数组实现的，所以类名就叫MyArrayList
 */

public class MyArrayList {

    // 默认初始容量
    private static final int DEFAULT_CAPACITY = 10;
    // 存储数据的数组，默认容量为10，用私有修饰符防止数据被外部类修改
    private int[] arr;
    // 数组的最大容量
    private int capacity;
    // 目前数组内的有效元素数量，默认创建初始值为0
    private int size;

    // 空参构造，用于应对用户不传入初始参数的情况
    public MyArrayList(){
        this.arr = new int[DEFAULT_CAPACITY];
        this.size = 0;
        this.capacity = DEFAULT_CAPACITY;
    }

    // 带参构造方法，用于初始化顺序表
    public MyArrayList(int capacity){
        // 初始化数组容量为capacity
        this.arr = new int[capacity];
        // 初始化目前数组内的有效元素数量为0
        this.size = 0;
        // 初始化数组最大容量
        this.capacity = capacity;
    }

    /**
     * 功能：添加元素到顺序表的最后
     * 参数：int element 要添加的元素
     * 返回值：void
     */
    public void addElement(int element){
        // 首先判断顺序表是否已满
        if (size < capacity) {
            // 顺序表未满，添加元素到顺序表
            arr[size] = element;
            // 添加完成后有效元素数量+1
            size++;
        }else {
            System.out.println("顺序表已满，无法添加元素......");
        }
    }

    // 主程序入口，用于测试顺序表功能
    public static void main(String[] args) {
        MyArrayList data = new MyArrayList();



    }
}


