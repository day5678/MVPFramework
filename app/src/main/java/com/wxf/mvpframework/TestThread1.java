package com.wxf.mvpframework;

import android.os.Handler;
import android.util.SparseArray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

/**
 * Created by Administrator on 2017/12/6.
 */

public class TestThread1 {
    private String flag[] = { "true" };

    class NotifyThread extends Thread {
        public NotifyThread(String name) {
            super(name);
        }

        public void run() {
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (flag) {
                flag[0] = "false";
                flag.notifyAll();
                System.out.println(getName() + " 111111111111");
            }
            System.out.println(getName() + " 22222222222");
        }
    };

    class WaitThread extends Thread {
        public WaitThread(String name) {
            super(name);
        }

        public void run() {
            synchronized (flag) {
                while (flag[0] != "false") {
                    System.out.println(getName() + " begin waiting!");
                    long waitTime = System.currentTimeMillis();
                    try {
                        flag.wait();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    waitTime = System.currentTimeMillis() - waitTime;
                    System.out.println("wait time :" + waitTime);
                }
                System.out.println(getName() + " end waiting!");
            }
        }
        Handler handler;
    }

    public static void main(String[] args) throws InterruptedException {
        List<String> list;
        Map<String,String> map;
        Set<String> set = new HashSet<>();
        LinkedHashSet linkedHashSet;
        Vector<String> vector;
        ArrayList arrayList;
        LinkedHashMap linkedHashMap;
        SparseArray<String> sparseArray = new SparseArray();
        sparseArray.put(1,"");
        System.out.println("Main Thread Run!");
        TestThread1 test = new TestThread1();
        NotifyThread notifyThread = test.new NotifyThread("notify01");
        WaitThread waitThread01 = test.new WaitThread("waiter01");
        WaitThread waitThread02 = test.new WaitThread("waiter02");
        WaitThread waitThread03 = test.new WaitThread("waiter03");
        notifyThread.start();
        waitThread01.start();
        waitThread02.start();
        waitThread03.start();
    }
}
