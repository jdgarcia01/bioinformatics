package com.bioinformatics.string_count;

/**
 * Created by johngarcia on 4/20/17.
 */

public class BIOKmer {

    private String mKmerPattern;
    private int    mKmerCount;
    private int  mPatternToNumber;



    public BIOKmer(String pattern, int count){

        mKmerPattern = pattern;
        mKmerCount   = count;


    }

    public boolean equals(Object o){

        return ((BIOKmer) o).mKmerPattern == this.mKmerPattern;

    }

    public int hashCode(){

        return this.mKmerPattern.length();

    }

    public BIOKmer(String pattern, int count, int number){

        mKmerPattern = pattern;
        mKmerCount   = count;
        mPatternToNumber = number;

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


    public int getmPatternToNumber(){

        return mPatternToNumber;
    }

}
