package thread.consumer;

public class Father
{
    private String name;

    private Family family;

    public Father(String name, Family family)
    {
        this.name = name;
        this.family = family;
        this.family.setFather(this);
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

    public void earnMoney(double money)
    {
        // 一个线程给family对象加锁后，直到这个线程解锁前，其他线程无法再给这个对象加锁
        synchronized (this.family)
        {
            double now = this.family.getAccount();
            System.out.println(this.getClass().getSimpleName() + "赚取前余额" + now);
            double left = now + money;
            this.family.setAccount(left);
            System.out.println(this.getClass().getSimpleName() + "赚取后账户余额" + left);
        }
    }
}

