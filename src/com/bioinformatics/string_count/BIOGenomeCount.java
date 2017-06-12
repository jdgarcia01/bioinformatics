package com.bioinformatics.string_count;

/**
 * Created by johngarcia on 6/1/17.
 */
public class BIOGenomeCount {

    private int mCount;
    private String mPattern;

    public BIOGenomeCount(int count, String pattern){

        mCount = count;
        mPattern = pattern;



    }
    public int getCount(){

        return mCount;

    }
    public String getmPattern(){
        return mPattern;
    }


}
