package com.wxf.mvpframework;

/**
 * Created by Administrator on 2017/10/23.
 */

public class TestRunnable implements Runnable {
    private int ticket =5;
    private String name;
    @Override
    public void run() {
        for (int i=0; i<10; i++) {
            if (this.ticket>0)
                System.out.println("线程开始："+this.name+",卖票：i="+this.ticket--);
        }
    }

    public static void main(String[] args)
    {
        Thread t1 = new TestThread("线程a");
        Thread t2 = new TestThread("线程b");
        t1.run();
        t2.run();
    }
}
