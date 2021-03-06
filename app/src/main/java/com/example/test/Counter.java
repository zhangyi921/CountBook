package com.example.test;

import java.util.Date;

/**
 * Created by Yi on 2017/9/29.
 */
/*
* Counter class contains all data of a counter and all
* method to construct and interact with it
*
* */
public class Counter {

    private String name;
    private int initialValue;
    private int currentValue;
    private Date date;
    private String comment;

    public Counter(String name, int initialValue, String comment){
        this.name = name;
        this.initialValue = initialValue;
        this.currentValue = initialValue;
        this.date = new Date();
        this.comment = comment;
    }
    public String getName(){
        return this.name;
    }
    public int getCurrentValue(){
        return this.currentValue;
    }
    public int getInitialValue(){
        return initialValue;
    }
    public String getDate(){
        return date.toString();
    }
    public String getComment(){
        return comment;
    }
    public void setCurrentValue(int val){
        this.currentValue = val;
    }
    public void setDate(){
        date = new Date();
    }
    public void setComment(String com){
        comment = com;
    }
    public void setInitialValue(int initia){
        initialValue = initia;
    }
    public String toString(){
        Integer cur = new Integer(currentValue);
        return "  Name: "+name+" Current Value: "+cur.toString()+" Date: "+date.toString();
    }

}
