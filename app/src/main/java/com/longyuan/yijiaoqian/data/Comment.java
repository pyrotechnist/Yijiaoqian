package com.longyuan.yijiaoqian.data;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * Created by loxu on 10/07/2017.
 */

public class Comment extends DisplayData{


    public Comment(String content, String contributor) {
        this.id = UUID.randomUUID().toString();
        this.content = content;
        this.contributor = contributor;
        this.date = getRandomDate();
    }

    private String content;

    private Date date;

    private String parentComment;

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

    public String getParentComment() {
        return parentComment;
    }

    public void setParentComment(String parentComment) {
        this.parentComment = parentComment;
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
