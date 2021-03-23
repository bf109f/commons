package thread.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class WorkingTask implements Runnable
{
    private CountDownLatch countDownLatch;

    public WorkingTask(CountDownLatch countDownLatch)
    {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run()
    {
        try
        {
            Thread.sleep((int)(Math.random() * 10) * 1000);
            System.out.println(Thread.currentThread().getName() + this.getClass().getSimpleName() + "工作完毕");
            countDownLatch.countDown(); // 任务完成后计数减一
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
