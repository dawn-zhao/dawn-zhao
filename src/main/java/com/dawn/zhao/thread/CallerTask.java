package com.dawn.zhao.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallerTask implements Callable<String> {
    @Override
    public String call() {
        return "hello world 'FutureTask'";
    }

    public static void main(String[] args) throws InterruptedException {
        // 创建异步任务
        FutureTask<String> futureTask  = new FutureTask<>(new CallerTask());

        //启动线程
        new Thread(futureTask).start();

        try {
            //等待任务执行完毕，并返回结果
            String result = futureTask.get();
            System.out.println(result);

        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
