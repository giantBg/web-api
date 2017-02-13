package com.bingcore.web;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.math.IntMath;
import com.google.common.math.LongMath;
import com.google.common.primitives.Ints;
import com.ishansong.common.date.Dates;

import java.util.*;

/**
 * Created by xubing on 16/8/9.
 * <p/>
 * function: test class
 */
public class UtilTest {

    /**
     * ip test
     */
    public static void mineTest1() {
        String ip = "192.168.0.104";
        List<String> result = Splitter.on('.').splitToList(ip);
        System.out.println("size is:" + result.size());
        Long ipSum = 0l;
        for (int i = 0; i < result.size(); i++) {
            Integer curValue = Ints.tryParse(result.get(i));
            ipSum += LongMath.checkedMultiply(curValue, IntMath.checkedPow(256, (result.size() - i - 1)));
            System.out.println("i is:" + (result.size() - i - 1));
            System.out.println(curValue + "\t");
        }

        System.out.println("sum result is:" + ipSum);
    }

    /**
     * replace test
     */
    public static void mineTest2() {
        String str = "aaaa,sdf，df";
        System.out.println("replace str:" + str.replaceAll(",", " ").replaceAll("，", " "));

        String strb = "aaaa,sdf，df";
        System.out.println("replace str:" + strb.replaceAll("\"", "\'"));

        String strc = "adf  haha\nha" +
                " dafadada";

        String strd = "张三里斯玩哥哥似懂非懂根深蒂固接口连接了werewrewrewrew";

        System.out.println("str is:" + strc + "replace str:" + strc.replaceAll("\\s", "&"));

        System.out.println("str length is: " + strd.length() + "\t dealStr: " + strd.substring(0, 60));
    }


    /**
     * arr test
     */
    public static void mineTest3() {

        int[] a = {1, 2, 3, 4, 5};
        //浅拷贝
//        int b[] = a;

        //深拷贝
//        int b[] = a.clone();

//        int b[] = new int[a.length];
//        System.arraycopy(a, 0, b, 0, a.length);

//        int b[] = Arrays.copyOf(a,a.length);

        int b[] = Arrays.copyOfRange(a, 0, a.length);
        b[3] = 10;


        int[] c = new int[111];
        String aArr = Arrays.toString(a);
        String bArr = Arrays.toString(b);
        String cArr = Arrays.toString(c);

        System.out.println("arr1: " + aArr);
        System.out.println("arr2: " + bArr);
    }


    public static void main(String args[]) {
        String nowDayStr = Dates.now("yyyyMMdd");
        System.out.println("now Day is :" + nowDayStr);

        ArrayList a = Lists.newArrayList(8,3);
        System.out.println("list length is :"+a.size());
        System.out.println("a:"+a.get(0)+"\t"+a.get(a.size()-1));

    }
}
