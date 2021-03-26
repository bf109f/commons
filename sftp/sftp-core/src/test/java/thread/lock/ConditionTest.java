package thread.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ConditionTest.class);
    final Lock lock = new ReentrantLock(); // 唯一的ReentrantLock对象
    final Condition notFull = lock.newCondition(); // 监测buffer是否满了，如果满了不能插入数据put，需要等待
    final Condition notEmpty = lock.newCondition(); // 监测buffer是否为空，如果为空不能take，需要等待
    final Object [] items = new Object[20]; // buffer容量
    int put, take, count;

    public void put(Object obj) throws InterruptedException
    {
        try
        {
            LOGGER.info(Thread.currentThread().getName() + "：准备写入:{}", obj.toString());
            lock.lock(); // 排他锁，读数据与取数据不能同时进行
            while (count == items.length)
            {
//                System.out.println(Thread.currentThread().getName() + "缓存满了");
                LOGGER.info(Thread.currentThread().getName() + "：缓存满了");
                notFull.await();
            }
            items[put] = obj;
            LOGGER.info(Thread.currentThread().getName() + "：已写入" + obj.toString());
            if (++ put == items.length) put = 0;
            ++ count;
            notEmpty.signal();
        } finally
        {
            lock.unlock();
        }

    }

    public Object take()
    {
        try
        {
            lock.lock();
            while (count == 0)
            {
                notEmpty.await();
            }
            Object obj = items[take];
            if (++ take == items.length) take = 0;
            -- count;
            LOGGER.info(Thread.currentThread().getName() + ":取出数据-------:{}", obj.toString());
            notFull.signal(); // 唤醒一个等待线程
            return obj;
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        } finally
        {
            lock.unlock();
        }
    }

    public static void main(String[] args)
    {
        
    }

}
