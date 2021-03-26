package thread.pool;

/**
 * 如果一个线程出现异常，则线程池还会去再次创建一个线程
 */
public class SingleThreadPoolTest
{
    public static void main(String[] args)
    {
        /*ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 20; i++)
        {
            executorService.execute(new ListeningTask(i));
        }
        executorService.shutdown();*/

        for (int i = 0; i < 20; i++)
        {
            new Thread(new ListeningTask(i)).start();
        }
    }
}
