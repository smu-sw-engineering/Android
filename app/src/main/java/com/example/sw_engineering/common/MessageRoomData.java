package com.example.sw_engineering.common;

public class MessageRoomData {
    private int profileImage;
    private String sender;
    private String date;
    private String preContents;

    public MessageRoomData(int profileImage, String sender, String date, String preContents){
        this.profileImage = profileImage;
        this.sender = sender;
        this.date = date;
        this.preContents = preContents;
    }

    public int getprofileImage()
    {
        return this.profileImage;
    }

    public String getsender()
    {
        return this.sender;
    }

    public String getDate() {
        return this.date;
    }

    public String getpreContents()
    {
        return this.preContents;
    }
}