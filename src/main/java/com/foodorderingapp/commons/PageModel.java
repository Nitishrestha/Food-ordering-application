package com.foodorderingapp.commons;

/**
 * Created by TOPSHI KREATS on 1/29/2018.
 */
public class PageModel {
    private long count;
    private int firstResult;
    private int maxResult;

    public PageModel(){}

    public PageModel(int firstResult, int maxResult){
        this.firstResult = firstResult;
        this.maxResult = maxResult;
    }
    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public int getFirstResult() {
        return firstResult;
    }

    public void setFirstResult(int firstResult) {
        this.firstResult = firstResult;
    }

    public int getMaxResult() {
        return maxResult;
    }

    public void setMaxResult(int maxResult) {
        this.maxResult = maxResult;
    }
}
