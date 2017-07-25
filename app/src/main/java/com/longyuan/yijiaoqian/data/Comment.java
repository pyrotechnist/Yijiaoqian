package com.longyuan.yijiaoqian.data;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Created by loxu on 10/07/2017.
 */

public class Comment extends DisplayData{


    public Comment(String content, String contributor) {
        this(content,contributor,false);
    }

    public Comment(String content, String contributor, Boolean isChildrenComment) {
        this.id = UUID.randomUUID().toString();
        this.content = content;
        this.contributor = contributor;
        this.date = getRandomDate();
        this.isChildrenComment = isChildrenComment;
    }

    private String content;

    private Date date;

    private String parentComment;

    private Boolean isChildrenComment;

    private List<Comment> childrenComments;


    public List<Comment> getChildrenComments() {

        if(!isChildrenComment) {
            return childrenComments;
        }

        return  null;
    }

    public void addChildrenComments(Comment childrenComment) {
        if(!this.isChildrenComment && this.childrenComments == null)
        {
            this.childrenComments = new ArrayList<Comment>();
        }

        this.childrenComments.add(childrenComment);
    }

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

    public Boolean isChildrenComment() {
        return isChildrenComment;
    }

    public void setChildrenComment(boolean childrenComment) {
        isChildrenComment = childrenComment;
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
