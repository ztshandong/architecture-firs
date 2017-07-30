package com.zhangtao.common;

import com.google.common.collect.HashBasedTable;
import com.greenpineyu.fel.Expression;
import com.greenpineyu.fel.FelEngine;
import com.greenpineyu.fel.context.FelContext;

import java.util.*;

/**
 * Created by zhangtao on 2017/7/30.
 */
public final class Fel {
    private static Hashtable<String, Integer> allmap = new Hashtable<>();
    private static List<String> optExpression = new ArrayList<>();
    private static Hashtable<String, Integer> allresult = new Hashtable<>();

    public static Fel getSingleton() {
        return Holder.singleton;
    }

    private static class Holder {
        private static Fel singleton = new Fel();
    }

    private Fel() {
        allmap.put("1", 1);
        optExpression.add("1");
        for (Integer integer = 2; integer <= 10; integer++) {
            if (Objects.equals(integer, 10)) {
                integer = 0;
            }
            for (String s : optExpression) {
                allmap.put(s + "+" + integer, integer);
                allmap.put(s + "-" + integer, integer);
                allmap.put(s + "*" + integer, integer);
                if (!Objects.equals(integer, 0))
                    allmap.put(s + "/" + integer, integer);
                allmap.put(s + integer, integer);
            }
            optExpression.clear();
            for (Map.Entry entry : allmap.entrySet()) {
                if (Objects.equals(entry.getValue(), integer)) {
                    optExpression.add(entry.getKey().toString());
                }
            }

            if (Objects.equals(integer, 0)) {
                integer = 10;
            }
        }

        FelEngine e = FelEngine.instance;
        final FelContext ctx = e.getContext();
        Object eval = null;
        String evalstr = "";
        int i = -1;
        for (Map.Entry entry : allmap.entrySet()) {
            com.greenpineyu.fel.Expression exp = e.compile(entry.getKey().toString(), ctx);
            eval = exp.eval(ctx);
            evalstr = eval.toString();
            i = evalstr.indexOf(".");
//            if (i > 0) {
//                evalstr = evalstr.substring(0, i);
//            }
            if (i < 0) {
//                evalstr = evalstr.substring(0, i);
                allresult.put(entry.getKey().toString(), Integer.parseInt(evalstr));
            }

        }
    }


    public Hashtable<String, Integer> GetResult(int resultnum) {

        Hashtable<String, Integer> currectResult = new Hashtable<>();

        for (Map.Entry entry : allresult.entrySet()) {

            if (Objects.equals(entry.getValue(), resultnum)) {
                currectResult.put(entry.getKey().toString(), resultnum);
            }
        }
        return currectResult;

    }
}
