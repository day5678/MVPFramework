package com.wxf.mvpframework;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/12.
 */

public class Test3
{
    enum TestEnum
    {
        TEXT(1,"文本"),AUDIO(2,"语音");
        int value;
        String name;

        TestEnum(int value,String name)
        {
            this.value = value;
            this.name = name;
        }
        public int getValue() {
            return value;
        }

        public String getName()
        {
            return name;
        }

        @Override
        public String toString() {
            return "编码"+value+","+name;
        }
    }
    public static void main(String[] args)
    {
        TestEnum te = TestEnum.AUDIO;
        System.out.print(te);
    }
}
