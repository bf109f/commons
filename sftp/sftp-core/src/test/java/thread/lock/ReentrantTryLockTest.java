package thread.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantTryLockTest
{
    public static void main(String[] args)
    {
        Garden garden = new Garden();
        for (int i = 0; i < 20; i++)
        {
            new Thread(() -> {
                garden.openDoor(); // 20个人排队进入公园
            }).start();
        }

    }

    // 公园
    static class Garden
    {
        private ReentrantLock lock = new ReentrantLock();

        private void openDoor()
        {
            try
            {
                System.out.println(Thread.currentThread().getName() + "排队等待");
                if (lock.tryLock(3, TimeUnit.SECONDS))
                {
                    Thread.sleep((int)(Math.random() * 10) * 1000);
                    System.out.println(Thread.currentThread().getName() + "进入公园");
                }

            } catch (InterruptedException e)
            {
                e.printStackTrace();
            } finally
            {
                lock.unlock(); // 释放锁
            }

        }
    }
}
