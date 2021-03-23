package thread.vector;

import model.BookInfo;
import model.BookTable;

public class Test
{
    public static void main(String[] args)
    {
        BookTable table = new BookTable();
        new Thread(() -> {
            synchronized (table)
            {
                for (int i = 0; i < 30; i++)
                {

                    table.add(new BookInfo("a" + i, i));
                    System.out.println(Thread.currentThread().getName() + "添加a" + i);
                }
            }
        }).start();
        new Thread(() -> {
            synchronized (table)
            {
                for (int i = 0; i < 20; i++)
                {

                    table.add(new BookInfo("b" + i, i));
                    System.out.println(Thread.currentThread().getName() + "添加b" + i);
                }
            }
        }).start();
        new Thread(()-> {

            synchronized (table)
            {
                for (int i = 0; i < table.size(); i++)
                {

                    System.err.println(Thread.currentThread().getName() + ":" + (table.get(i).toString()));
                }
            }
        }).start();
    }
}
