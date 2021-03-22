package model;

import java.time.LocalDateTime;

public class TicketInfo
{
    private String seatNo;

    private LocalDateTime dateTime;

    private String startSite;

    private String endSite;

    public TicketInfo(String seatNo)
    {
        this.seatNo = seatNo;
    }

    public String getSeatNo()
    {
        return seatNo;
    }

    public void setSeatNo(String seatNo)
    {
        this.seatNo = seatNo;
    }
}
