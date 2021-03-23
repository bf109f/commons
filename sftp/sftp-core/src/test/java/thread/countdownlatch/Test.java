package thread.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class Test
{
    private static final int SIZE = 10;

    public static void main(String[] args)
    {
        CountDownLatch countDownLatch = new CountDownLatch(SIZE);
        new Thread(new WaitingTask(countDownLatch)).start();
        for (int i = 0; i < SIZE; i++)
        {
            new Thread(new WorkingTask(countDownLatch)).start();
        }
    }
}
