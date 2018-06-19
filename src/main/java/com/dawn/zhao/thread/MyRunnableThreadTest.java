package com.dawn.zhao.thread;

public class MyRunnableThreadTest implements Runnable {

    private  int ticket =10;

    @Override
    public void run() {
        // TODO Auto-generated method stub
//        super.run();
        while(true){
            if(ticket>0){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"卖票--->"+(this.ticket--));
            }else{
                break;
            }
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        MyRunnableThreadTest mt = new MyRunnableThreadTest();
        Thread t1 = new Thread( mt,"一号窗口");
        Thread t2 = new Thread( mt,"二号窗口");
        t1.start();
        t2.start();
        t2.interrupt();

    }

}
