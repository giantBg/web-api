package com.bingcore.web;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.math.IntMath;
import com.google.common.math.LongMath;
import com.google.common.primitives.Ints;
import com.ishansong.common.date.Dates;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
/*
        List<Integer> a = Lists.newArrayList(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                1, 3, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 3, 0, 1, 3, 1,
                1, 0, 3, 0, 1, 1, 3, 1, 1, 2, 3, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 0, 3, 0, 1);

        Map<String, List<Integer>> timeMaps = Maps.newLinkedHashMap();

        Integer startIndex = a.indexOf(0);
        Integer endIndex = a.lastIndexOf(0);
        System.out.println("start:" + startIndex + "\t end:" + endIndex);

        for (int i = startIndex+1; i < endIndex; i++) {
            Integer innerStart = i;


            List<Integer> lastestData = Lists.newArrayList();
            lastestData.add(currentEle);

            if (currentEle > 0)
                continue;
            lastestData.add(currentEle);

            timeMaps.put("span_" + i, lastestData);
        }


        for (Map.Entry<String, List<Integer>> entry : timeMaps.entrySet()) {
            String titileDay = entry.getKey();
            List<Integer> timePeriods = entry.getValue();
            System.out.println("title:" + titileDay + "\t [" + timePeriods.get(0) +
                    "," + timePeriods.get(1) + "]");
        }*/
    }
}
