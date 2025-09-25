package Search;

/*
  分块查找
  核心思想：
      块内无序，块间有序
  实现步骤：
      1.创建数组blockArr存放每一个块对象的信息
      2.先查找blockArr确定要查找的数据属于哪一块
      3.再单独遍历这一块数据即可
*/
public class BlockSearch {
    public static void main(String[] args) {
        // 测试用数组
        // 按理论分块后数据相对不是很均匀，故将第一块和第二块合并
        int[] arr = {
                16, 5, 9, 12, 21, 18,
                32, 23, 37, 26, 45, 34,
                50, 48, 61, 52, 73, 66
        };

        // 1. 把数据进行分块
        // 所分块数为数据数开根号，比如上方18个数据，开根号大约是4.24块
        // 18 / 4 = 4.25块

        // 创建三个块的对象
        Block b1 = new Block(21,0,5);
        Block b2 = new Block(45,6,11);
        Block b3 = new Block(73,12,17);

        // 定义数组用来管理三个块的对象（这数组也叫索引表）
        Block[] blocks = {b1,b2,b3};

        // 定义一个变量用来记录要查找的元素
        int number = 37;

        // 调用方法，传递索引表、数组、要查找的元素
        int index = getIndex(blocks,arr,number);

        // 打印获取的索引
        System.out.println(index);
    }

    /*
    * 功能：通过分块查找的原理查找number的索引
    *  参数：
    *   1. Block[] blocks：管理三个块的数组，即索引表
    *   2. int[] arr：被查找的数组
    *   3. int number：要查找的元素
    * 返回值：
    *   int：所查找数值在数值内的索引
    * */
    private static int getIndex(Block[] blocks, int[] arr, int number) {
        // 1. 确定所查找的数值number所在块的位置
        int indexBlock = findIndexBlock(blocks,number);

        // 根据上方方法所获得的块索引位置，排除数值过大，不在数组内的情况
        if (indexBlock == -1) {
            // 表示number不在数组当中
            return -1;
        }
        // 2. 此时数据要么在数组内，要么在范围内没有对应数据，要么过小不在数组内
        // 获取所在块的起始索引与结束索引
        int startIndex = blocks[indexBlock].getStartIndex();
        int endIndex = blocks[indexBlock].getEndIndex();

        // 3. 在对应块的索引内遍历查找
        for (int i = startIndex; i <= endIndex; i++) {
            if (arr[i] == number) {
                return i;
            }
        }
        // number不存在，或者过小
        return -1;
    }

    /*
    * 功能：定义一个方法用于查找number所在块的位置
    * 参数：
    * 1. Block[] blocks：管理三个块的数字，即索引表
    * 2. int number：要查找的元素
    * 返回值：
    *    int：所查找元素所在块的位置（索引）
    * */
    private static int findIndexBlock(Block[] blocks, int number) {
        // Block b1 = new Block(21,0,5);   ----  0
        // Block b2 = new Block(45,6,11);  ----  1
        // Block b3 = new Block(73,12,17); ----  2

        // 从0开始遍历blocks，如果number<=对应块的最大值，那就表示number在这这一块当中
        for (int i = 0; i < blocks.length; i++) {
            if (number <= blocks[i].getMax()) {
                return i;
            }
        }
        // 如果都不在，那么就表示当前数值过大，不在所查找数组中，返回-1
        return -1;
    }

}

/*
*
* 块类
* max:块中的最大值
* startIndex:块的起始索引
* endIndex:块的结束索引
*
* */
class Block {
    int max;
    int startIndex;
    int endIndex;

    public Block() {
    }

    public Block(int max, int startIndex, int endIndex) {
        this.max = max;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    /**
     * 获取
     * @return max
     */
    public int getMax() {
        return max;
    }

    /**
     * 设置
     * @param max
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * 获取
     * @return startIndex
     */
    public int getStartIndex() {
        return startIndex;
    }

    /**
     * 设置
     * @param startIndex
     */
    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    /**
     * 获取
     * @return endIndex
     */
    public int getEndIndex() {
        return endIndex;
    }

    /**
     * 设置
     * @param endIndex
     */
    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

    public String toString() {
        return "Block{max = " + max + ", startIndex = " + startIndex + ", endIndex = " + endIndex + "}";
    }
}

