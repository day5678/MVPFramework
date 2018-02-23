package com.wxf.mvpframework;

/**
 * Created by Administrator on 2017/12/12.
 */

public class Test3
{
    public static void quickSort(int[] array)
    {
        if(null != array && array.length > 1)
        {
            sort(array,0,array.length - 1);
        }

        for(int i = 0 ;i < array.length; i++)
        {
            if(i != 0)
            {
                System.out.print(",");
            }
            System.out.print(array[i]);
        }
    }

    public static void sort(int[] array,int low,int high)
    {
        if (low < high)
        {
            int division = getDivisionPosition(array,low,high);
            sort(array,low,division-1);
            sort(array,division+1,high);
        }
    }

    public static int getDivisionPosition(int[] array,int low,int high)
    {
        int base = array[low];

        while(low < high)
        {
            while(low < high && array[high] >= base)
            {
                high--;
            }
            changePosition(array,low,high);

            while (low < high && array[low] <= base)
            {
                low++;
            }
            changePosition(array,low,high);
        }

        return low;
    }

    /**
     * 交换位置
     *
     * @param array
     * @param start
     * @param end
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


    public static void main(String[] args) {
//        int[] array = {5,4,8,1,7,9,6};
//        quickSort(array);
        int i=0;
        for(;;)
        {
            System.out.print(i+"");
            i++;
            if(i==5)
            {
                return;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
