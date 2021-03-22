import model.TicketInfo;
import thread.TicketThread;

import java.util.LinkedList;

public class TestThread
{

    public static void testA()
    {
        new Thread(new thread.TestThread("1"));
        new Thread(new thread.TestThread("2"));
        new Thread(new thread.TestThread("3"));
    }

    public static void testB()
    {
        thread.TestThread thread = new thread.TestThread("thread.TestThread");
        for (int i = 0; i < 30; i++)
        {
            // 多个线程共享同一个对象的run方法
            new Thread(thread).start();
        }
    }


    public static void testC()
    {
        LinkedList<TicketInfo> ticketInfos = new LinkedList<>();
        for (int i = 0; i < 50000; i++)
        {
            ticketInfos.add(new TicketInfo(String.valueOf(i)));
        }
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 5; i++)
        {
            new TicketThread(ticketInfos, startTime).start();
        }
    }

    public static void main(String[] args)
    {
        testC();
    }
}
