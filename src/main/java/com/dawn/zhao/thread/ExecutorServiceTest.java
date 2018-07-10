package com.dawn.zhao.thread;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * 并发API引入了ExecutorService作为一个在程序中直接使用Thread的高层次的替换方案。Executos支持运行异步任务，通常管理一个线程池，这样一来我们就不需要手动去创建新的线程。
 * 在不断地处理任务的过程中，线程池内部线程将会得到复用，因此，在我们可以使用一个executor service来运行和我们想在我们整个程序中执行的一样多的并发任务。
 */
public class ExecutorServiceTest {

    public static void main(String[] args) throws InterruptedException {
        TimeUnit.SECONDS.sleep(60);
    }

    private static void scheduledExecutorWithFixedDelayTest(){
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        Runnable task = () -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("Scheduling: " + System.currentTimeMillis());
            }
            catch (InterruptedException e) {
                System.err.println("task interrupted");
            }
        };

        executor.scheduleWithFixedDelay(task, 0, 1, TimeUnit.SECONDS);

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
    }

    /**
     * 用来以固定频率来执行一个任务
     */
    private static void scheduledExecutorAtFixedRateTest(){
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        Runnable task = () -> {
            try {
                TimeUnit.SECONDS.sleep(10);
                System.out.println("Scheduling: " + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        int initialDelay = 0;
        int period = 1;
        executor.scheduleAtFixedRate(task, initialDelay, period, TimeUnit.SECONDS);

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
    }

    /**
     * 调度一个任务将会产生一个专门的future类型——ScheduleFuture，它除了提供了Future的所有方法之外，他还提供了getDelay()方法来获得剩余的延迟。在延迟消逝后，任务将会并发执行。
     *
     */
    private static void scheduledExecutorTest() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        Runnable task = () -> System.out.println("Scheduling: " + System.nanoTime());
        ScheduledFuture<?> future = executor.schedule(task, 3, TimeUnit.SECONDS);

        try {
            TimeUnit.MILLISECONDS.sleep(1337);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long remainingDelay = future.getDelay(TimeUnit.MILLISECONDS);//剩余的时间
        System.out.printf("Remaining Delay: %sms", remainingDelay);
        executor.shutdown();
    }

    private static void invokeAnyTest(){
        ExecutorService executor = Executors.newWorkStealingPool();

        List<Callable<String>> callables = Arrays.asList(
                callable("task1", 2),
                callable("task2", 1),
                callable("task3", 3));

        String result = null;
        try {
            result = executor.invokeAny(callables);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(result);
//        executor.shutdown();
    }

    static Callable<String> callable(String result, long sleepSeconds) {
        return () -> {
            TimeUnit.SECONDS.sleep(sleepSeconds);
            return result;
        };
    }

    private static void invokeAllTest() {
        ExecutorService executor = Executors.newWorkStealingPool();

        List<Callable<String>> callables = Arrays.asList(
                () -> "task1",
                () -> "task2",
                () -> "task3");

        try {
            executor.invokeAll(callables)
                    .stream()
                    .map(future -> {
                        try {
                            return future.get();
                        }
                        catch (Exception e) {
                            throw new IllegalStateException(e);
                        }
                    })
                    .forEach(System.out::println);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
    }

    private static void executorTest() {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        Runnable runnable = () -> {
            String threadName = Thread.currentThread().getName();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Hello " + threadName);
        };
        Future future = executor.submit(runnable);
        try {
            System.out.println("res : "+future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        executor.shutdown();//shutdownNow()
    }

    /**
     * Callable 返回一个Future,可以获取返回值,获取返回值时会阻塞当前线程,直到Callable执行结束
     * 也可以调用Future.get(long timeout, TimeUnit unit) 设置最大的阻塞时间,Callable响应超时就抛出异常.
     */
    private static void callableTest(){

        ExecutorService executor = Executors.newFixedThreadPool(4);
        Callable<Integer> task = () -> {
            try {
                String threadName = Thread.currentThread().getName();
                System.out.println("Hello " + threadName);
                TimeUnit.SECONDS.sleep(3);
                return 1231231;
            } catch (InterruptedException e) {
                throw new IllegalStateException("task interrupted", e);
            }
        };
        Future<Integer> future = executor.submit(task);
        try {
            System.out.println(future.get());
//            System.out.println(future.get(2, TimeUnit.SECONDS));
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executor.shutdown();//shutdownNow()
    }
}
