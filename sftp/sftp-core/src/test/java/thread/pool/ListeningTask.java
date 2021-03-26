package thread.pool;

public class ListeningTask implements Runnable
{
    private int i;

    public ListeningTask(int i)
    {
        this.i = i;
    }

    @Override
    public void run()
    {
        System.out.println(Thread.currentThread().getName() + "开始:" + i);
        try
        {
            Thread.sleep(200);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        if (i == 3)
        {
            throw new RuntimeException("异常[" + i + "]");
        }
        System.out.println(Thread.currentThread().getName() + "结束:" + i);
    }
}
