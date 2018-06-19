package com.dawn.zhao.CPU_Cache;

public class L1CacheMiss {

    private static final int RUNS = 10;
    private static final int DIMENSION_1 = 1024 * 1024;
    private static final int DIMENSION_2 = 62;

    private static long[][] longs;

    public static void main(String[] args) throws Exception {
        cacheMiss();
        cacheMiss2();
    }

    /**
     * 两种循环方式，效率差别
     * 遍历数据时会缓存相邻一位内存地址的数据，等到遍历到时，直接命中
     * 如果遍历非正序，则会出现缓存未命中，重新根据内存地址访问数据，效率大打折扣
     * 物理内存正序   : 1008534831ns
     * 物理内存非正序 : 12168194563ns
     * http://ifeve.com/from-javaeye-cpu-cache
     * @throws InterruptedException
     */
    private static void cacheMiss(){
        longs = new long[DIMENSION_1][];
        for (int i = 0; i < DIMENSION_1; i++) {
            longs[i] = new long[DIMENSION_2];
            for (int j = 0; j < DIMENSION_2; j++) {
                longs[i][j] = 1L;
            }
        }
        System.out.println("starting cacheMiss....");

        final long start = System.nanoTime();
        long sum = 0L;
        for (int r = 0; r < RUNS; r++) {
            for (int i = 0; i < DIMENSION_1; i++) {
                for (int j = 0; j < DIMENSION_2; j++) {
                    sum += longs[i][j];
                }
            }

//            for (int j = 0; j < DIMENSION_2; j++) {
//                for (int i = 0; i < DIMENSION_1; i++) {
//                    sum += longs[i][j];
//                }
//            }
        }
        System.out.println(sum + " : duration = " + (System.nanoTime() - start));
    }

    private static void cacheMiss2(){
        longs = new long[DIMENSION_1][];
        for (int i = 0; i < DIMENSION_1; i++) {
            longs[i] = new long[DIMENSION_2];
            for (int j = 0; j < DIMENSION_2; j++) {
                longs[i][j] = 1L;
            }
        }
        System.out.println("starting cacheMiss2....");

        final long start = System.nanoTime();
        long sum = 0L;
        for (int r = 0; r < RUNS; r++) {
            for (int i = DIMENSION_1-1; i >= 0 ; i--) {
                for (int j = DIMENSION_2-1; j >=0 ; j--) {
                    sum += longs[i][j];
                }
            }
//            for (int i = 0; i < DIMENSION_1; i++) {
//                for (int j = 0; j < DIMENSION_2; j++) {
//                    sum += longs[i][j];
//                }
//            }
        }
        System.out.println(sum + " : duration = " + (System.nanoTime() - start));
    }
}
