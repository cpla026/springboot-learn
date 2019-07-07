package com.coolron.thread.concurrentcontainer;

import java.util.concurrent.LinkedTransferQueue;

/**
 * @Auther: xf
 * @Date: 2018/11/27 17:07
 * @Description:
 * 消费者 先启动 生产者启动之后会去找有没有消费者  有就不往容器中扔了 直接交给消费者
 */
public class T08_TransferQueue {

    public static void main(String[] args) throws InterruptedException {

        LinkedTransferQueue<Object> strs = new LinkedTransferQueue<>();
        // 消费者
      /*  new Thread(()->{
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();*/

        // 调用 transfer 如果此时没有消费者  线程就会阻塞了
        strs.transfer("aaa");

        // strs.put("aaa");  // 不会出问题

        // 消费者
        new Thread(()->{
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
