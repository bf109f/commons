package thread;

import model.BookInfo;
import model.BookTable;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 使用lock进行读写分离，顺序执行
 * 读可以共享资源
 * 写不可以共享资源
 */
public class LockTest
{
    public static void main(String[] args)
    {
        BookTable table = new BookTable();
        ReadWriteLock lock = new ReentrantReadWriteLock();
        // 写数据
        new Thread(() -> {
            lock.writeLock().lock();
            for (int i = 0; i < 30; i++)
            {
                try
                {
                    Thread.sleep(200);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                table.add(new BookInfo("a" + i, i));
                System.out.println(Thread.currentThread().getName() + "添加a" + i);
            }
            lock.writeLock().unlock();
        }).start();

        // 写数据
        new Thread(() -> {
            lock.writeLock().lock();
            for (int i = 0; i < 20; i++)
            {
                try
                {
                    Thread.sleep(200);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                table.add(new BookInfo("b" + i, i));
                System.out.println(Thread.currentThread().getName() + "添加b" + i);
            }
            lock.writeLock().unlock();
        }).start();

        // 读数据
        new Thread(()-> {
            lock.readLock().lock();
            for (int i = 0; i < table.size(); i++)
            {
                try
                {
                    Thread.sleep(200);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                System.err.println(Thread.currentThread().getName() + ":" + (table.get(i).toString()));
            }
            lock.readLock().unlock();
        }).start();

        // 读数据
        new Thread(()-> {
            lock.readLock().lock();
            for (int i = 0; i < table.size(); i++)
            {
                try
                {
                    Thread.sleep(200);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                System.err.println(Thread.currentThread().getName() + ":" + (table.get(i).toString()));
            }
            lock.readLock().unlock();
        }).start();
    }
}
