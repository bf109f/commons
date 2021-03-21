public class Test
{
    public static void main(String[] args)
    {

        for (int i = 0; i < 30; i++)
        {
            TestThread thread = new TestThread(String.valueOf(i));
            thread.start();
        }
    }
}
