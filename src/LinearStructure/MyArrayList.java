package LinearStructure;

import java.util.Arrays;

/**
 * 顺序表的核心：用数组存储数据，并用变量记录实际元素数量
 * 实现思路：
 * 顺序表是一个可以动态管理元素的数组容器，
 * 它需要有地方来存储很多数据，需要知道里面有多少有效元素，需要知道它最多能存储多少数据，需要知道它已经存储了多少数据，故：
 *  定义一个数组来存储顺序表中的数据
 *  定义一个容量来表示该顺序表最多能存储的元素量,容量即底层数组的长度，与arr.length保持一致
 *  定义一个长度来表示该顺序表目前存储的有效元素数量
 * 因为该顺序表是用数组实现的，所以类名就叫MyArrayList
 */

public class MyArrayList {

    // 默认顺序表初始容量
    private static final int DEFAULT_CAPACITY = 10;
    // 存储顺序表数据的数组，默认容量为10，用私有修饰符防止数据被外部类修改
    private int[] arr;
    // 数组长度/顺序表容量
    private int capacity;
    // 顺序表内的有效元素数量，默认初始值为0
    private int size;

    // 空参构造，用于应对用户不传入初始参数的情况
    public MyArrayList(){
        this.arr = new int[DEFAULT_CAPACITY];
        this.size = 0;
        this.capacity = DEFAULT_CAPACITY;
    }

    // 带参构造方法，用于初始化顺序表
    public MyArrayList(int capacity){
        // 边界校验，判断用户传入的容量值是否大于等于0
        if (capacity < 0){
            // 如果小于0，那么初始化数组容量为默认值
            this.arr = new int[DEFAULT_CAPACITY];
            // 初始化顺序表容量
            this.capacity = DEFAULT_CAPACITY;
        }else {
            // 如果用户正常输入
            // 初始化数组容量为capacity
            this.arr = new int[capacity];
            // 初始化目前数组内的有效元素数量为0
            this.size = 0;
            // 初始化顺序表容量
            this.capacity = capacity;
        }
    }

    /**
     * 功能：添加元素到顺序表的末尾
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
            System.out.println("元素添加成功！");
        }else {
            // 如果顺序表已满，自动扩容至原1.5倍大小
            int newCapacity = capacity + capacity / 2;
            // 新建数组
            int[] newArr = new int[newCapacity];
            // 复制原数组至新数组
            System.arraycopy(arr,0,newArr,0,capacity);
            // 使用新数组替代原数组
            this.arr = newArr;
            // 将数组长度修改为扩容后的长度
            this.capacity = newCapacity;
            //扩容成功后继续添加元素
            arr[size] = element;
            // 元素添加完成后索引指针后移
            size++;
            // 输出提示
            System.out.println("顺序表已满，已扩容至："+newCapacity+". 添加元素成功！");
        }
    }

    /**
     * 功能：在顺序表指定位置插入元素
     * 参数：int index, int element
     *  分别为要插入的位置和要插入的元素
     * 返回值：void
     */
    public void insertElement(int index, int element){
        // 先判断所插入位置是否已经存在元素
        if (arr[index] == 0) {
            // 如果不存在，则直接将要元素插入
            arr[index] = element;
            // 有效元素量+1
            size++;
        }else {
            // 如果存在，则将该位置及之后元素后移，再插入元素
            // 把当前位置的下一个元素给临时变量1
            int temp1 = arr[index+1];
            // 把当前位置的元素放到下一个元素刚刚空出来的位置
            arr[index+1] = arr[index];
            int temp2 = temp1;
            for (int i = index; i < capacity; i++) {
                // 把当前索引的下下个给第一个临时变量
                temp1 = arr[i+2];
                //
                arr[i+2] = temp2;
                temp2 = temp1;
                if (temp1 == 0) {
                    break;
                }
            }
            // 挪完后面所有元素后，在当前指定位置插入元素
            arr[index] = element;
            // 有效元素量+1
            size++;
            System.out.println("元素插入成功！");
        }
    }


    // toString方法，方便打印顺序表查看表内情况
    @Override
    public String toString() {
        return "顺序表：" + Arrays.toString(arr) +
                "，顺序表容量：" + capacity +
                "，有效元素数量：" + size;
    }

    // 主程序入口，用于测试顺序表功能
    public static void main(String[] args) {
        // 添加元素至顺序表末尾
        MyArrayList data = new MyArrayList();
        data.addElement(114);
        data.addElement(115);
        data.addElement(116);
        data.addElement(117);
        data.addElement(118);
        data.addElement(118);
        data.addElement(118);
        data.addElement(118);
        data.addElement(118);
        System.out.println(data);

        // 在顺序表指定位置插入元素
        data.insertElement(7,191);
        System.out.println(data);
    }
}


