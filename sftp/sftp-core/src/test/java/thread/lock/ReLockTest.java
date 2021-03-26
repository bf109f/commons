package thread.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock是重入锁调用lock方法时
 *  1.如果锁没有被另一个线程占用会立即返回，并计数为1。
 *  2.如果当前线程已经保持锁定，则保持计数加1，该方法立即返回。(当前线程可以多次锁)
 *  3.如果锁被另一个线程保持，则当前线程将被禁用以进行线程调度，并且在锁定已被获取之前处于休眠状态，此时锁定保持计数被设置为1。(排队等待)
 */
public class ReLockTest
{
    public static void main(String[] args)
    {
        GameRoom gameRoom = new GameRoom();
        for (int i = 0; i < 20; i++)
        {
            new Thread(() -> {
                gameRoom.openDoor(); // 排队进入游戏厅
            }).start();
        }

    }

    // 公园
    static class Garden
    {
        private ReentrantLock lock = new ReentrantLock();

        public void openDoor()
        {
            try
            {
                System.out.println(Thread.currentThread().getName() + "排队等待");
                lock.lock(); // 排队获得锁
                lock.lock(); // 排队获得锁
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + "进入公园");
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            } finally
            {
                lock.unlock(); // 释放锁
                lock.unlock(); // 释放锁
            }

        }
    }

    /**
     * 多层锁
     */
    static class GameRoom
    {
        private ReentrantLock lock = new ReentrantLock();

        public void openDoor()
        {
            try
            {
                System.out.println(Thread.currentThread().getName() + "排队等待");
                lock.lock(); // 排队获得锁
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + "进入游戏厅");
                // 开始玩游戏
                if ((int)(Math.random() * 10) % 2 == 0)
                {
                    // 两层锁
                    shooting();
                } else
                {
                    // 三层锁
                    boating();
                    shooting();
                }
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            } finally
            {
                lock.unlock(); // 释放锁
            }
        }

        public void shooting()
        {
            try
            {
                lock.lock(); // 排队获得锁
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + "射击游戏");
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            } finally
            {
                lock.unlock(); // 释放锁
            }
        }

        public void boating()
        {
            try
            {
                lock.lock(); // 排队获得锁
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + "划船游戏");
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
