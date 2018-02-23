package com.wxf.mvpframework;

import android.util.Log;

/**
 * Created by Administrator on 2017/10/23.
 */

public class TestThread extends Thread {
    private  String name;
    private int ticket = 5;
    public TestThread(String name) {
        this.name = name;
    }

    public void run() {
        for (int i=0; i<10; i++) {
            if (this.ticket>0)
                System.out.println("线程开始："+this.name+",卖票：i="+this.ticket--);
        }
    }

    private void get(){}
}
