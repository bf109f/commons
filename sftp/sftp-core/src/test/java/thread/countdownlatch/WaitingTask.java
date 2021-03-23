package thread.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * 最后执行部分
 */
public class WaitingTask implements Runnable
{
    private CountDownLatch countDownLatch;

    public WaitingTask(CountDownLatch countDownLatch)
    {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run()
    {
        try
        {
            System.err.println(Thread.currentThread().getName() + this.getClass().getSimpleName() + "等待其他任务");
            countDownLatch.await();
            System.err.println(Thread.currentThread().getName() +  this.getClass().getSimpleName() + "任务完成");
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
