package Search;

import javax.crypto.interfaces.PBEKey;
import java.util.Arrays;

public class FeiBoSearch {
    // 给数组设定最大值
    public static int maxSize = 20;

    /*
    * 主方法
    * */
    public static void main(String[] args) {
        // 被查找的数组
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        System.out.println(search(arr, 1234));
    }


    /*
    * 功能：查找数据在数组内的位置并返回
    * 参数：
    *   int[] arr：被查找的数组
    *   要查找的数据
    * 返回值：
    *
    * */
    private static int search(int[] arr, int key) {
        int left = 0;
        int right = arr.length-1;

        // 表示斐波那契数分割数的下表值
        int index = 0;
        int mid = 0;

        int[] feiBo = getFeiBo();

        // 获取斐波那契分割数值的下标
        while (right > (feiBo[index] - 1)) {
            index++;
        }
        //因为f[k]值可能大于a的长度，因此需要使用Arrays工具类，构造一个新法数组，并指向temp[],不足的部分会使用0补齐
        int[] temp = Arrays.copyOf(arr, feiBo[index]);
        //实际需要使用arr数组的最后一个数来填充不足的部分
        for (int i = right+1; i < temp.length; i++) {
            temp[i] = arr[right];
        }
        while (left <= right) {
            mid = left + feiBo[index - 1] - 1;
            if (key < temp[mid]) { // 向数组的前面部分进行查找
                right = mid - 1;
                 /*
                  对k--进行理解
                  1.全部元素=前面的元素+后面的元素
                  2.f[k]=k[k-1]+f[k-2]
                  因为前面有k-1个元素没所以可以继续分为f[k-1]=f[k-2]+f[k-3]
                  即在f[k-1]的前面继续查找k--
                  即下次循环,mid=f[k-1-1]-1
*/
                index--;
            } else if (key > temp[mid]) {
                left = mid + 1;
                index -= 2;
            }else {
                if (mid <= right) {
                    return mid;
                }else {
                    return right;
                }
            }
        }
        return -1;
    }

    /*
    * 功能：获取包含斐波那契数列的数组
    * 参数：void
    * 返回值：
    *   int[]：得到的斐波那契数组
    * */
    public static int[] getFeiBo() {
        // 定义一个数组用来存斐波那契数列的数据
        int[] arr = new int[maxSize];
        // 斐波那契数列初始值
        arr[0] = 1;
        arr[1] = 1;
        // 将斐波那契数列的每个数添加到数组中
        for (int i = 2; i < maxSize; i++) {
            arr[i] = arr[i-1] + arr[i - 2];
        }
        return arr;
    }
}
