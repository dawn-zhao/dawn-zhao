package com.dawn.zhao.thread;

public class ThreadsTest {

    private int num; //输出数字
    private int runThreadNum; //当前可运行通过的线程号

    public ThreadsTest(int num, int runThreadNum){
        this.num = num;
        this.runThreadNum = runThreadNum;
    }


    /**
     * 打印线程
     */
    static class ThreadDemo extends Thread{
        private ThreadsTest test; //锁对象
        private int threadNum; //当前运行线程编号

        public ThreadDemo(int threadNum, ThreadsTest test){
            this.threadNum = threadNum;
            this.test = test;
        }

        @Override
        public void run() {
            synchronized (test) {
                try{
                    for (int i=1; i<=5; i++){
                        while(true){
                            System.out.println("当前线程为："+threadNum+", 释放线程为："+test.runThreadNum);
                            if(threadNum == test.runThreadNum){
                                break;
                            } else {
                                //如果当前线程不是接下来要运行的线程，进入等待池
                                System.out.println("线程 等待"+threadNum);
//                              wait方法可以理解为释放出该对象锁，notify方法可以理解为把该对象锁交给之前通过wait方法交出该对象锁的线程，
//                              那么无论哪个方法，要交出对象锁显然都要先获取该对象锁，因为它是唯一的。
                                test.wait();
                                System.out.println("线程 等待完毕  "+threadNum);
                            }
                        }

                        test.runThreadNum = test.runThreadNum%3 +1; //计算之后运行的线程编号
                        test.notifyAll(); //唤醒所有等待池中的线程
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getRunThreadNum() {
        return runThreadNum;
    }

    public void setRunThreadNum(int runThreadNum) {
        this.runThreadNum = runThreadNum;
    }

    public static void main(String[] args) throws Exception {
        ThreadsTest test = new ThreadsTest(0,1);
        new ThreadDemo(1,test).start();
        new ThreadDemo(2,test).start();
        new ThreadDemo(3,test).start();
    }
}