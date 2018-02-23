package com.wxf.mvpframework;

import org.w3c.dom.Node;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/10/12.
 */

public class Test {

    // 插入排序法
    // 插入排序的基本思想是在遍历数组的过程中，假设在序号 i 之前的元素即 [0..i-1] 都已经排好序，
    // 本趟需要找到 i 对应的元素 x 的正确位置 k ，并且在寻找这个位置 k 的过程中逐个将比较过的元素往后移一位，
    // 为元素 x “腾位置”，最后将 k 对应的元素值赋为 x ，一般情况下，插入排序的时间复杂度和空间复杂度分别为 O(n2 ) 和 O(1)。
    public static int[] sortInsert(int[] array)
    {
        for(int i = 1; i<array.length; i++)
        {
            int temp = array[i];
            int j;
            for(j = i-1; j >= 0 && temp < array[j]; j--)
            {
                array[j + 1] = array[j];
            }
            array[j + 1] = temp;
        }
        for(int i : array)
        {
            System.out.print(i);
        }

        return array;
    }

    // 选择排序法
    // 选择排序的基本思想是遍历数组的过程中，以 i 代表当前需要排序的序号，则需要在剩余的 [i…n-1] 中找出其中的最小值，
    // 然后将找到的最小值与 i 指向的值进行交换。因为每一趟确定元素的过程中都会有一个选择最大值的子流程，所以人们形象地称之为选择排序。
    // 选择排序的时间复杂度和空间复杂度分别为 O(n2 ) 和 O(1) 。
    public static int[] sortSelect(int[] array)
    {
        for(int i = 0; i < array.length; i++)
        {
            // 剩余最小值的位置
            int minIndex = i;

            // 遍历剩余数组
            for(int j = i + 1; j < array.length; j++)
            {
                if(array[minIndex] > array[j])
                {
                    minIndex = j;
                }
            }

            // 开始进行比较
            if(array[i] > array[minIndex])
            {
                // 如果当前值比最小值要大，交换位置
                int temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;
            }
        }

        for(int i : array)
        {
            System.out.print(i);
        }

        return array;
    }

    // 冒泡排序法
    // 冒泡排序是將比較大的數字沉在最下面，较小的浮在上面
    public static int[] sortBubble(int[] array)
    {
        for(int i = 0; i < array.length - 1; i++)
        {
            for(int j = array.length - 1; j > i; j--)
            {
                if(array[j] < array[j - 1])
                {
                    int temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                }
            }
        }

        for(int i : array)
        {
            System.out.print(i);
        }

        return array;
    }


/***************************************************************************** 快速排序start **********************************************************************************************/
   // 通过一趟排序将待排记录分割成独立的两部分,其中一部分记录的关键字均比另一部分的关键字小,则可以分别对这两部分记录继续进行排序,
    // 已达到整个序列有序的目的，本质就是,找一个基位(枢轴,分水岭,作用是左边的都比它小,右边的都比它大.可随机,取名base，
    // 首先从序列最右边开始找比base小的,如果小,换位置,从而base移到刚才右边(比较时比base小)的位置(记为临时的high位),
    // 这样base右边的都比base大。然后,从序列的最左边开始找比base大的，如果大,换位置,从而base移动到刚才左边(比较时比base大)的位置(记为临时的low位),
    // 这样base左边的都比base小，循环以上两步,直到 low == heigh, 这使才真正的找到了枢轴,分水岭. 返回这个位置,分水岭左边和右边的序列,分别再来递归。
private static void quickSort(int[] array)
{
    if(array.length > 1)
    {
        sort(array,0,array.length - 1);
    }

    for (int i = 0; i < array.length; i++)
    {
        if(i != 0)
        {
            System.out.print(",");
        }
        System.out.print(array[i]);
    }
}

    /**
     * 排序
     *
     * @param array
     *          需要排序的数组
     * @param low
     *          低位
     * @param high
     *          高位
     */
    private static void sort(int[] array,int low,int high)
    {
        // 判断低位是否小于高位
        if(low < high)
        {
            // 获取分割值位置
            int divisionPosition = getDivisionPosition(array,low,high);
            // 排序前半段
            sort(array,low,divisionPosition - 1);
            // 排序后半段
            sort(array,divisionPosition + 1,high);
        }
    }

    /**
     * 排序，并返回排序后的分割值所在的位置
     *
     * @param array
     *          需要排序的数组
     * @param low
     *          最低位置
     * @param high
     *          最高位置
     *
     * @return 分割值位置
     */
    private static int getDivisionPosition(int[] array,int low,int high)
    {
        // 把数组的最低位当作分割值
        int division = array[low];

        // 当低位小于高位时，循环，保证分割值左侧都比分割值小，右侧都比分割值大
        while (low < high)
        {
            // 从高向低，当低位小于高位，并且高位大于等于分割值时，继续循环
            while(low < high && array[high] >= division)
            {
                high --;
            }

            // 交换位置,并确保high为division位置
            changePosition(array,low,high);

            // 从低向高，当低位小于高位，并且低位小于等于分割值时，继续循环
            while(low < high && array[low] <= division)
            {
                low ++;
            }

            // 交换位置,并确保low为division位置
            changePosition(array,low,high);
        }

        // 返回low，即为division位置
        return low;
    }

    /**
     * 交换数组位置
     *
     * @param array
     *          需要交换的数组
     * @param start
     *          开始位置
     * @param end
     *          结束位置
     */
    private static void changePosition(int[] array,int start,int end)
    {
        if(start == end)
        {
            return;
        }
        int base = array[start];
        array[start] = array[end];
        array[end] = base;
    }

    /***************************************************************************** 快速排序end **********************************************************************************************/

    public static void main(String[] args)
    {
        int[] array = {5,4,8,1,7,9,6};
        sortInsert(array);
    }
}
