package model;

import java.util.StringJoiner;

public class BookInfo
{
    private String BookName;

    private int price;

    public BookInfo(String bookName, int price)
    {
        BookName = bookName;
        this.price = price;
    }

    public String getBookName()
    {
        return BookName;
    }

    public void setBookName(String bookName)
    {
        BookName = bookName;
    }

    public int getPrice()
    {
        return price;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }

    @Override
    public String toString()
    {
        StringJoiner joiner = new StringJoiner("-");
        joiner.add(getBookName());
        joiner.add(String.valueOf(getPrice()));
        return joiner.toString();
    }
}
