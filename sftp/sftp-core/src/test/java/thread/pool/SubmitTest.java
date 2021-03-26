package thread.pool;

import java.util.concurrent.*;

public class SubmitTest
{
    public static void main(String[] args)
    {
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 20; i++)
        {
            Future<String> result = service.submit(new TaskResult(i));
            System.out.println(result.isDone());
            try
            {
                System.out.println(result.get());
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            } catch (ExecutionException e)
            {
                e.printStackTrace();
            }
        }
    }
}


class TaskResult implements Callable<String>
{

    private int index;

    public TaskResult(int index)
    {
        this.index = index;
    }

    @Override
    public String call() throws Exception
    {
        Thread.sleep(200);
        return Thread.currentThread().getName() + ":" + index;
    }
}