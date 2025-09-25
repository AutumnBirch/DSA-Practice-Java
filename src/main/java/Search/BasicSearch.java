package Search;

public class BasicSearch {
    public static void main(String[] args) {
    // 基本查找/顺序查找

        //需求：定义一个方法利用基本查找，查询某个元素是否存在
        //数据如下：{131, 127, 147, 81, 103, 23, 7, 79}

        int[] arr = {131, 127, 147, 81, 103, 23, 7, 79};
        int number = 82;
        System.out.println(basicSearch(arr,number));

    }

    /*
    * 功能：顺序查找，查找一个数是否存在所查询的数组中
    * 参数1：int[] - 所需查询的数组
    * 参数2：int number - 所需查询的数字
    * 返回值：boolen（元素是否存在）
    * */
    private static boolean basicSearch(int[] arr, int number) {

        // 利用循环将所需查询的数字挨个与数组中的元素进行对比
        for (int i = 0; i < arr.length; i++) {
            // 如果存在，返回true
            if (arr[i] == number) {
                return true;
            }
        }
        // 默认返回不存在
        return false;
    }

}
