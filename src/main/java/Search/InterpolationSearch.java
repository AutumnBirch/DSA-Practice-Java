package Search;

public class InterpolationSearch {
    public static void main(String[] args) {
        int[] arr = {7, 23, 79, 81, 103, 127, 131, 147};

        System.out.println(interpolationSearch(arr,127));

    }

    private static int interpolationSearch(int[] arr, int number) {
        int min = 0;
        int max = arr.length-1;
        int mid;

        while (true) {
            if (min > max){
                return -1;
            }
            // (number-arr[min]) / (arr[max]-arr[min])算出number在整个数组中大致的位置（大约处于几分之几的位置），乘以索引长度得到在索引中的位置，最后在加上min的值，用于处理偏移量？不是特别理解
            // 要求数组内的数据分布均匀
            mid = min + (number-arr[min]) / (arr[max]-arr[min]) * (max-min);

            //4. 拿着要查找的元素与中间指针指向的元素进行对比
            if (number > arr[mid]) {
                // 4.1 mid指针在number左边

                min = mid+1;
            } else if (number < arr[mid]) {
                // 4.2 mid指针在number右边

                max = mid-1;
            }else {
                // 4.3 number和mid指向的指针相同
                // 找到了，返回指针
                return mid;
            }
        }

    }
}
