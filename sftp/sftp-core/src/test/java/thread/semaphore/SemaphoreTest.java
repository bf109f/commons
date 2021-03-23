package thread.semaphore;

import java.util.concurrent.Semaphore;

public class SemaphoreTest
{
    public static void main(String[] args)
    {
        // 五个许可证
        Semaphore semaphore = new Semaphore(5);
        for (int i = 0; i < 20; i++)
        {
            new Thread(new StudentTask(semaphore, i)).start();
        }

    }
}


class StudentTask implements Runnable
{
    private Semaphore semaphore;

    private int num;

    public StudentTask(Semaphore semaphore, int num)
    {
        this.semaphore = semaphore;
        this.num = num;
    }

    @Override
    public void run()
    {
        try
        {
            // 排队等待获取许可证
            semaphore.acquire();
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + "结束任务" + num);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        } finally
        {
            semaphore.release();
        }
    }
}