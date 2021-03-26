package thread.lock;

public class SynchronizedTest3
{
    public static void main(String[] args)
    {
        GardenSync garden = new GardenSync();
        for (int i = 0; i < 20; i++)
        {
            new Thread(() -> {
                garden.openDoor(); // 20个人排队进入公园
            }).start();
        }

    }

    // 公园
    static class GardenSync
    {

        private Object obj = new Object();

        private void openDoor()
        {
            try
            {
                System.out.println(Thread.currentThread().getName() + "排队等待");
                synchronized (obj)
                {
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + "进入公园");
                }

            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }

        }
    }
}
