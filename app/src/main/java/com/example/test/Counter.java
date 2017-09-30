package com.example.test;

import java.util.Date;

/**
 * Created by Yi on 2017/9/29.
 */

public class Counter {

    private String name;
    private int initialValue;
    private int currentValue;
    private Date date;
    private String comment;

    public Counter(String name, int initialValue){
        this.name = name;
        this.initialValue = initialValue;
        this.currentValue = initialValue;
        this.date = new Date();
    }

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
}
