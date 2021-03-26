package thread.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * >>生产者消费者模式
 *  生产者插入的数据多个消费者去抢，但是只能有一个消费者抢到.take()一旦抢到即被移除队列
 * >>观察者模式
 *  被观察者生成消息，会通知所有监听者(通知信息会被所有的监听者收到)
 *  采用对象引用通知的模式实现
 * >>JMS中的发布订阅模式
 *  分布式架构 异步消息通知
 * 基于BlockingQueue实现的生产者消费者模式与JMS中的P2P模式基本一致
 */
public class ProducerConsumer
{
    public static void main(String[] args)
    {
        BlockingQueue q = new LinkedBlockingQueue(); // 生产者与消费者共享一个BlockingQueue
        Producer producer = new Producer(q);
        Consumer consumer1 = new Consumer(q);
        Consumer consumer2 = new Consumer(q);
        new Thread(producer).start();
        new Thread(consumer1).start();
        new Thread(consumer2).start();
    }
}

class Producer implements Runnable
{
    private final BlockingQueue queue;

    public Producer(BlockingQueue queue)
    {
        this.queue = queue;
    }

    @Override
    public void run()
    {
        for (int i = 0; i < 100; i++)
        {
            try
            {
                queue.put(Integer.valueOf(i));
                System.out.println("+++++++" + Thread.currentThread().getName() + ":" + i);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}

class Consumer implements Runnable
{

    private final BlockingQueue queue;

    public Consumer(BlockingQueue queue)
    {
        this.queue = queue;
    }

    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName() + ":" + queue.take() + "--------");
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}