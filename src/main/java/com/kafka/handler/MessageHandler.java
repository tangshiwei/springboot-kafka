package com.kafka.handler;

import com.kafka.constants.KafkaConsts;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.AcknowledgingMessageListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * <p>
 * 消息处理器
 * </p>

 */
@Component
@Slf4j
public class MessageHandler implements AcknowledgingMessageListener<Integer, String> {

//
//    @KafkaListener(topics = KafkaConsts.TOPIC_TEST)
//    public void handleMessage(ConsumerRecord record, Acknowledgment acknowledgment) {
//        try {
//            String message = (String) record.value();
//            log.info("handleMessage()收到消息:", message);
//        } catch (Exception e) {
//            log.error(e.getMessage(), e);
//        } finally {
//            // 手动提交 offset
//            acknowledgment.acknowledge();
//        }
//    }

    @KafkaListener(topics = KafkaConsts.TOPIC_TEST)
    public void onMessage(String message){
        //insertIntoDb(buffer);//这里为插入数据库代码
        System.out.println("onMessage()收到消息: "+message);

    }

    //设置监听器，手动提交消息
    @Override
    public void onMessage(ConsumerRecord<Integer, String> record, Acknowledgment acknowledgment) {
        String message = record.value();
        System.out.println("监听器onMessage()收到消息: "+message);
        acknowledgment.acknowledge();//提交offset

    }
}
