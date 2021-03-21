import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class TestThread extends Thread
{
    private volatile static Integer total = 10;

    private final String name;

//    private CountDownLatch countDownLatch = new CountDownLatch(20);

    public TestThread(String name)
    {
        this.name = name;
    }

    @Override
    public void run()
    {
        try
        {
            Thread.sleep(500);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }


        synchronized (total)
        {
            if (total > 0)
            {
                synchronized (total)
                {
                    if (total > 0)
                    {
                        total -- ;
                        System.out.println(Thread.currentThread().getName() + "[" + name + "]买到了票，票号[" + total + "]");
                    }

                }
            } else
            {
                System.out.println("无票啦");
            }
        }
        }

}
