package Search;

public class BinarySearch {
    public static void main(String[] args) {
        // 二分查找/折半查找

        // 核心：所查找的元素必须是有序的（大到小-小到大均可）

/*      如果数据是无序的，可以先进行排序，单排序后会改变原有数据顺序，
        查找出来的元素位置与原来的元素可能不一样，故排序后再查找只能判断当前数据是否在容器中，
        返回的索引无实际意义

        基本思想：
            用给定值先与中间节点比较
            比较完后有三种情况：
            - 相等：
                说明找到所查询元素
            - 要查找的数据比中间节点小：
                说明要查找的数据在中间节点左边
            - 要查找的数据比中间节点大：
                说明要查找的数据在中间节点右边
        */

        //需求：定义一个方法利用二分查找，查询某个元素在数组中的索引
        //数据如下：{7, 23, 79, 81, 103, 127, 131, 147}

        // 查找150--> min=0,mid=3,max=7 ====> min=4,max=7,mid=5 ====>min=6,max=7,mid=6 ====>min=7,max=7,mid=7

        int[] arr = {7, 23, 79, 81, 103, 127, 131, 147};

        System.out.println(binarySearch(arr,1));
    }

    private static int binarySearch(int[] arr, int number) {
        // 1. 定义两个变量(左指针与右指针)记录要查找的范围
        // 分别是要查找数组的最小索引与最大索引（左指针指向数组的最小值，右指针指向数组的最大值）
        // 每次查找完后，根据查找元素的情况移动指针（改变变量的值）
        int min = 0;
        int max = arr.length -1;
        int mid;

        // 2. 利用循环查找所需数据
        while (true) {
            // 如果最小索引大于最大最大索引（左指针跑到右指针右边，或者反过来），则返回负一
            if (min > max){
                return -1;
            }
            // 3. 定义一个mid指针，指向查找范围的中间位置，每次循环查找重新赋值

            // 普通写法
            // mid = (min+max) / 2;
            // 安全写法 可以避免数值过大时整数溢出
            mid = min + (max - min) / 2;

            //4. 拿着要查找的元素与中间指针指向的元素进行对比
            if (number > arr[mid]) {
                // 4.1 mid指针在number左边
                // max指针不变，让min指针指向mid指针右边的第一个值
                min = mid+1;
            } else if (number < arr[mid]) {
                // 4.2 mid指针在number右边
                // min指针不变，让max指针指向mid指针左边的第一个值
                max = mid-1;
            }else {
                // 4.3 number和mid指向的指针相同
                // 找到了，返回指针
                return mid;
            }
        }
    }
}
