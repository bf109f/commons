package thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolTest
{
    public static void main(String[] args)
    {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 20; i++)
        {
            executorService.execute(new WorkThread());
        }
        executorService.shutdown();
    }
}
