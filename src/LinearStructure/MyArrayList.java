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

    // toString方法，方便打印顺序表查看表内情况
    @Override
    public String toString() {
        return "顺序表：" + Arrays.toString(Arrays.copyOf(arr,size)) +
                "\n顺序表容量：" + capacity +
                "\n有效元素数量：" + size;
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
            expandCapacity();
            //扩容成功后继续添加元素
            arr[size] = element;
            // 元素添加完成后索引指针后移
            size++;
            // 输出提示
            System.out.println("添加元素成功！");
        }
    }

    /**
     * 功能：删除顺序表内指定位置元素
     *      如果要删除元素的索引在有效范围内，
     *      从index开始，循环把元素依次往前移一位arr[index] = arr[index+1]，
     *      有效元素量-1（size--），
     *      顺序表有效元素量较少时可以考虑缩容
     * 参数：
     *      要删除元素的索引index
     * 返回值：void
     */
    public void deleteElement(int index){
        // 先判断顺序表是否为空
        if (isEmpty()) {
            System.out.println("顺序表已空");
        }else {
            // 先判断输入的索引是否在有效范围内
            if (index>=0 && index <size) {
                // 如果在，直接将后面的元素全部前移一位
                // leftPointer < size - 1，这里为啥是size-1？还有点不太明白，先记着
                for (int leftPointer = index; leftPointer < size - 1; leftPointer++) {
                    arr[leftPointer] = arr[leftPointer+1];
                }
                // 有效元素量减一
                size--;
                System.out.println("删除索引" + index + "的元素成功");
            }else {
                // 如果不在有效范围内，输出错误日志
                System.out.println("索引超出有效范围，有效索引为0~" + (size - 1));
            }
        }

    }

    /**
     * 功能：查询顺序表指定位置的元素
     * 参数：
     *  需要查询的元素索引 index
     * 返回值：
     *  查询到的元素
     */
    public int getElement(int index){
        if (index>=0 && index<size){
            return arr[index];
        }else {
            System.out.println("查询索引超出有效范围");
            return -1;
        }
    }

    /**
     * 功能：修改顺序表指定位置的元素
     * 参数：
     *  index:元素索引
     *  element:修改后的元素
     * 返回值：void
     */
    public void setElement(int index, int element){
        if (index>=0 && index<size){
            arr[index] = element;
        }else {
            System.out.println("修改索引超出有效范围，请重新设置");
        }
    }

    /**
     * 功能：清空顺序表
     * 参数：void
     * 返回值：void
     */
    public void clear(){
        // 数组的数值统一修改成0
        Arrays.fill(arr,0,size,0);
        this.size = 0;
    }

    /**
     * 功能：判断顺序表是否为空
     * 参数：void
     * 返回值：void
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * 功能：在顺序表有效元素范围内指定位置插入元素，
     * 若所插入元素位置大于有效元素长度，则直接将元素添加到顺序表末尾，
     * 如果所插入位置索引小于0，则输出索引越界日志
     * 参数：int index, int element
     *  分别为要插入的位置和要插入的元素
     * 返回值：void
     */
    public void insertElement(int index, int element){
        // 先判断所插入元素是否在有效范围内
        if (index < 0 || index > size) {
            // 如果所插入位置索引不在有效范围内，输出报错日志
            System.out.println("索引越界，有效范围是0<=index<=size");
        }else {
            // 如果所插入位置在有效范围内
            // 先判断顺序表是否会满或者已满
            if (size>=capacity){
                // 如果会满或者已满，则先自动扩容
                expandCapacity();
            }
            // 从后往前依次将元素后移一位
            // 定义一个整型变量作为指针指向最后一个有效元素的索引
            // 循环判断右指针是否已经指向要插入元素的位置
            for (int rightPointer = size - 1; rightPointer >= index; rightPointer--) {
                // 如果右指针在插入元素位置的右边，则将现在指向的元素后移一位，直至右指针在所插入元素位置的左边
                arr[rightPointer +1] = arr[rightPointer];
            }
            // 挪完后面所有元素后，在当前指定位置插入元素
            arr[index] = element;
            // 有效元素量+1
            size++;
            // 输出日志
            System.out.println("元素插入成功！\n");
        }
    }

    /**
     * 功能：顺序表扩容
     *      根据顺序表原容量计算新容量
     *      创建新数组newArr，复制元素至新数组
     *      更新顺序表内存储元素的数组arr以及顺序表的容量capacity
     * 参数：void
     * 返回值：void
     */
    public void expandCapacity(){
        int newCapacity = capacity + capacity / 2;
        // 新建数组
        int[] newArr = new int[newCapacity];
        // 复制原数组至新数组
        System.arraycopy(arr,0,newArr,0,size);
        // 使用新数组替代原数组
        this.arr = newArr;
        // 将数组长度修改为扩容后的长度
        this.capacity = newCapacity;
        //输出日志
        System.out.println("顺序表已满，已扩容至："+newCapacity+"\n");
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
        data.insertElement(0,191);
        data.insertElement(10,191);
        System.out.println(data);

        // 删除开头/中间/末尾元素
        data.deleteElement(0);
        data.deleteElement(10);
        data.deleteElement(5);
        System.out.println(data);

        // 连续插入多次查看是否扩容
        data.insertElement(2,123);
        data.insertElement(2,12);
        data.insertElement(2,33);
        data.insertElement(2,99);
        data.insertElement(2,58);
        System.out.println(data);

        // 清空表
        data.clear();
        System.out.println(data);

        // 对空表删除
        data.deleteElement(0);
        System.out.println(data);

        // 对空表插入
        data.insertElement(0,1234);
        System.out.println(data);

        MyArrayList data2 = new MyArrayList(15);
        System.out.println(data2);
    }
}


