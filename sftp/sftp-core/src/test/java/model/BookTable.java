package model;

import java.util.ArrayList;
import java.util.List;

public class BookTable
{
    private List<BookInfo> bookInfos;

    public BookTable()
    {
        this.bookInfos = new ArrayList<>(30);
    }

    public void add(BookInfo bookInfo)
    {
        bookInfos.add(bookInfo);
    }

    public BookInfo get(int index)
    {
        return bookInfos.get(index);
    }

    public int size()
    {
        return bookInfos.size();
    }
}
