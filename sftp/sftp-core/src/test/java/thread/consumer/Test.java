package thread.consumer;

public class Test
{
    public static void main(String[] args)
    {
        Family family = new Family();
        Father father = new Father("张三丰", family);
        Wife wife = new Wife("孙尚香", family);
        Son son = new Son("孙权", family);
        new Thread(new fatherThread(father)).start();
        new Thread(new WifeThread(wife)).start();
        new Thread(new SonThread(son)).start();
    }
}

class fatherThread implements Runnable
{
    private Father father;

    public fatherThread(Father father)
    {
        this.father = father;
    }

    @Override
    public void run()
    {
        for (int i = 0; i < 1; i++)
        {
            try
            {
                Thread.sleep(1000);
                this.father.earnMoney(10);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}

class WifeThread implements Runnable
{

    private Wife wife;

    public WifeThread(Wife wife)
    {
        this.wife = wife;
    }

    @Override
    public void run()
    {
        for (int i = 0; i < 1; i++)
        {
            try
            {
                Thread.sleep(1000);
                this.wife.shopping(7);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}

class SonThread implements Runnable
{

    private Son son;

    public SonThread(Son son)
    {
        this.son = son;
    }

    @Override
    public void run()
    {
        for (int i = 0; i < 1; i++)
        {
            try
            {
                Thread.sleep(1000);
                this.son.playGame(5);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}