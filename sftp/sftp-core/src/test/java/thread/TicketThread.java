package thread;

import model.TicketInfo;

import java.util.LinkedList;

public class TicketThread extends Thread
{
    private LinkedList<TicketInfo> ticketInfo;

    private long startTime;

    public TicketThread(LinkedList<TicketInfo> ticketInfo, long startTime)
    {
        this.ticketInfo = ticketInfo;
        this.startTime = startTime;
    }

    @Override
    public void run()
    {
        /*try
        {
            Thread.sleep(200);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }*/
        while (ticketInfo.size() > 0)
        {
            synchronized (ticketInfo)
            {
                if (ticketInfo.size() > 0)
                {
                    ticketInfo.removeLast();
                    System.out.println(Thread.currentThread().getName() + ":剩余" + ticketInfo.size());
                }
            }
        }
        System.out.println("执行时间(ms)" + (System.currentTimeMillis() - startTime));
    }
}
