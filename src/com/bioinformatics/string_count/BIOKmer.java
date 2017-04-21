package com.bioinformatics.string_count;

/**
 * Created by johngarcia on 4/20/17.
 */

public class BIOKmer {

    private String mKmerPattern;
    private int    mKmerCount;


    public BIOKmer(String pattern, int count){

        mKmerPattern = pattern;
        mKmerCount   = count;


    }

    public String getmKmerPattern() {
        return mKmerPattern;
    }


    public int getmKmerCount() {
        return mKmerCount;
    }

    @Override
    public String toString(){

        return (this.getmKmerPattern() + " " + this.mKmerCount);


    }

}
