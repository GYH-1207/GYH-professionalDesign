package com.xiaoxi.common;

import java.util.ArrayList;
import java.util.List;

/**
 * 基于ThreadLocal封装工具类，用户保存和获取当前登录用户id
 */
public class BaseContext {
//    private static Integer integer=0;
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();
    private static List<ThreadLocal<Long>> threadLocalList=new ArrayList<>();
    /**
     * 设置值,threadLocal是一次请求中临时的，threadLocalList是一次请求中全局的
     * @param id
     */
    public static void setCurrentId(Long id) {
        threadLocal.set(id);
//        BaseContext.setThreadLocalList(threadLocal);
    }

    /**
     * 获取值
     *
     * @return
     */
    public static Long getCurrentId() {
        return threadLocal.get();
    }

    /**
     * 拿到所有线程
     * @return
     */
    public static List<ThreadLocal<Long>> getThreadLocalList() {
        return threadLocalList;
    }

    /**
     * 返回值为设置的值的编号
     * @param threadLocal
     * @return
     */
    public static Integer setThreadLocalList(ThreadLocal<Long> threadLocal ) {
//        BaseContext.threadLocalList.set(integer++,threadLocal);
        return 1;
    }
}