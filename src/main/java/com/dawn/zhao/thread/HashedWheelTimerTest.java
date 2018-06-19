package com.dawn.zhao.thread;

import org.jboss.netty.util.HashedWheelTimer;

import java.time.LocalTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * HashedWheelTimer测试
 * HashedWheelTimer为netty中的一个工具类,提供时间tick分片执行
 *
 * tickDuration: 每 tick 一次的时间间隔, 每 tick 一次就会到达下一个槽位
 * ticksPerWheel: 轮中的 slot 数，hash算法计算目标槽位
 *
 * newTimeout(task, delay, unit) 添加定时任务。
 * task执行的任务
 * delay超时时间
 *
 */
public class HashedWheelTimerTest {

    public static void main(String[] args) throws InterruptedException {
        testHashedWheelTimer();
    }

    /**
     * ﻿tickDuration: 每 tick 一次的时间间隔, 每 tick 一次就会到达下一个槽位
     * ticksPerWheel: 轮中的 slot 数
     */
    public static void testHashedWheelTimer() throws InterruptedException {
        HashedWheelTimer hashedWheelTimer = new HashedWheelTimer(1000/**tickDuration**/, TimeUnit.MILLISECONDS, 60 /**ticksPerWheel**/);
        System.out.println(LocalTime.now()+" submitted");
        for (int i = 0; i < 60; i++) {
            hashedWheelTimer.newTimeout((t) -> new Thread(() -> System.out.println(new Date())).start(), 60, TimeUnit.SECONDS);
            Thread.sleep(1000);
        }
        System.out.println(" start ");
        hashedWheelTimer.start();
        hashedWheelTimer.newTimeout((t) -> new Thread(() -> System.out.println(new Date())).start(), 65, TimeUnit.SECONDS);
    }

}
