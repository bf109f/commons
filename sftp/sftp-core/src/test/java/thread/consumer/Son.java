package thread.consumer;

public class Son
{
    private String name;

    private Family family;

    public Son(String name, Family family)
    {
        this.name = name;
        this.family = family;
        this.family.setSon(this);
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Family getFamily()
    {
        return family;
    }

    public void setFamily(Family family)
    {
        this.family = family;
    }

    public void playGame(double money)
    {
        synchronized (this.family)
        {
            double now = this.family.getAccount();
            if (money > now)
            {
                System.out.println(this.getClass().getSimpleName() + "账户余额不足");
            } else
            {
                System.out.println(this.getClass().getSimpleName() + "消费前余额" + now);
                double left = now - money;
                this.family.setAccount(left);
                System.out.println(this.getClass().getSimpleName() + "消费后账户余额" + left);
            }
        }
    }
}
