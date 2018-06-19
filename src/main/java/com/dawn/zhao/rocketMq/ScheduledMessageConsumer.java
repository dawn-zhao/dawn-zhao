package com.dawn.zhao.rocketMq;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;

public class ScheduledMessageConsumer {

    public static void main(String[] args) throws Exception {
        // Instantiate message consumer
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer();
        consumer.setNamesrvAddr("192.168.0.153:9876");
        consumer.setConsumerGroup("ExampleMQ");
        consumer.setConsumeMessageBatchMaxSize(1);
        // Subscribe topics
        consumer.subscribe("TestTopic", "*");
        // Register message listener
        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            // Print approximate delay time period
            System.out.println("Receive message[msgId=" + msgs.get(0).getMsgId() + "] " + (System.currentTimeMillis() - msgs.get(0).getStoreTimestamp()) + "ms later");
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        // Launch consumer
        consumer.start();
    }
}
