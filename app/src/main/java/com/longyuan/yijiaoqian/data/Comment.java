package com.longyuan.yijiaoqian.data;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * Created by loxu on 10/07/2017.
 */

public class Comment {


    public Comment(String content, String contributor) {
        this.content = content;
        this.contributor = contributor;
        this.date = getRandomDate();
    }

    private String content;

    private String contributor;

    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContributor() {
        return contributor;
    }

    public void setContributor(String contributor) {
        this.contributor = contributor;
    }

    private Date getRandomDate(){
        Calendar cal = Calendar.getInstance();

        int randomInt = (new Random().nextInt(1000))-1000;

        cal.add(Calendar.MINUTE, randomInt);

        Date date = cal.getTime();

        cal.add(Calendar.MINUTE, 0- randomInt);

        return  date;
    }

}
