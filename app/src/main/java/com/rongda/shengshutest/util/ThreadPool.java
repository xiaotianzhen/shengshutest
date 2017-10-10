package com.rongda.shengshutest.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Buster on 2017/4/17.
 */

public class ThreadPool {
    public static ExecutorService pool;

    public static ExecutorService getInstance(){
        if(pool == null)
            pool = Executors.newCachedThreadPool();
        return pool;
    }
}
