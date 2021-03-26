package thread.lock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantTest
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
                lock.lock(); // 排队获得锁
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + "进入公园");
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
