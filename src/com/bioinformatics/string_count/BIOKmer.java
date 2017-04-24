package com.bioinformatics.string_count;

/**
 * Created by johngarcia on 4/20/17.
 */

public class BIOKmer {

    private String mKmerPattern;
    private int    mKmerCount;
    private int[]  mPatternToNumber;



    public BIOKmer(String pattern, int count){

        mKmerPattern = pattern;
        mKmerCount   = count;


    }
    public BIOKmer(String pattern, int count, int[] number){

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


    public String getmPatternToNumber(){

        System.out.println();
        System.out.println();
        for(Integer i : mPatternToNumber){

            System.out.println(i);

        }
        return mPatternToNumber.toString();
    }

}
