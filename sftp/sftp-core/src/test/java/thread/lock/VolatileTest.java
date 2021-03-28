package thread.lock;

import java.awt.geom.GeneralPath;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VolatileTest
{
    public static void main(String[] args)
    {
        EventChecker.test(new EvenGenerator());
    }
}

class EventChecker implements Runnable
{

    private IntGenerator generator;

    public EventChecker(IntGenerator generator)
    {
        this.generator = generator;
    }

    @Override
    public void run()
    {
        while (!generator.isCanceled())
        {
            int val = generator.next();
            if (val % 2 != 0)
            {
                System.out.println(val + "not even");
                generator.cancel();
            }
        }
    }

    public static void test(IntGenerator generator)
    {
        ExecutorService execute = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++)
        {
            // 十个EventChecker对象共享一个EvenGenerator对象
            execute.execute(new EventChecker(generator));

        }
        execute.shutdown();
    }
}

class EvenGenerator extends IntGenerator
{
    private int currentEvenValue = 0;

    @Override
    public int next()
    {
        ++ currentEvenValue;
        ++ currentEvenValue;
        return currentEvenValue;
    }
}

abstract class IntGenerator
{
    private volatile boolean canceled = false;

    public abstract int next();

    public void cancel()
    {
        canceled = true;
    }

    public boolean isCanceled()
    {
        return canceled;
    }
}