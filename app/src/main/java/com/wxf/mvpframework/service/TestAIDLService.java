package com.wxf.mvpframework.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.wxf.mvpframework.ITestAidlInterface;
import com.wxf.mvpframework.TestThread;

public class TestAIDLService extends Service {
    public TestAIDLService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();

//        new TestThread(1000).start();
    }

    ITestAidlInterface.Stub binder = new ITestAidlInterface.Stub(){
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }
    };
}
