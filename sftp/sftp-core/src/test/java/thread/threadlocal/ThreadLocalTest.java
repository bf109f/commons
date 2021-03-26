package thread.threadlocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadLocalTest
{
    private static final AtomicInteger nextId = new AtomicInteger(0);

    private static final ThreadLocal<Integer> THREAD_LOCAL = new ThreadLocal<>();

    private static int get()
    {
        return THREAD_LOCAL.get();
    }

    private static void set(Integer id)
    {
        THREAD_LOCAL.set(id);
    }

    public static void main(String[] args)
    {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++)
        {
            pool.execute(new Thread(() -> {
                set(nextId.getAndIncrement());
                System.out.println(Thread.currentThread().getName() + ":" + get());
            }));
        }
        pool.shutdown();
    }
}
