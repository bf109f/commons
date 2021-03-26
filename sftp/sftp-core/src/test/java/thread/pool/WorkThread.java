package thread.pool;

public class WorkThread implements Runnable
{
    @Override
    public void run()
    {
        try
        {
            Thread.sleep((int)(Math.random() * 10 ) * 1000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
    }
}
