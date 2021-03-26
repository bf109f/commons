package thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CacheThreadPoolTest
{
    public static void main(String[] args)
    {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 20; i++)
        {
            executorService.execute(new WorkThread());
        }
        executorService.shutdown();
    }
}
