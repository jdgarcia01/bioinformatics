package com.bioinformatics.string_count;

/**
 * Created by johngarcia on 4/20/17.
 */

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class BIOOperations {

    private String mDNAStrand;
    private String[] mKmer;
    private Set<BIOKmer> mFreq;
    private Set<BIOKmer> mfinalResult;
    private Set<String> mPatternSet;
    private Set<String> mTmpPattern;



    public BIOOperations(String dna_strand){

        mDNAStrand = dna_strand;



    }

    /**
     * Method to look for patterns in a DNA String.
     * This method DOES handle overlaps.
     * @param pattern
     * @return count
     */

    public int getPatternOccurence(String pattern){

        int lastIndex = 0;
        int count = 0;


        while(lastIndex != -1){

            lastIndex = mDNAStrand.indexOf(pattern, lastIndex);

            if(lastIndex != -1){
                count++;
                lastIndex++;

            }


        }
        return count;

    }

    public Set<String> getKmers(int k){

        /**
         * Get the first k length substring of the DNA Strand.
         */

        int max_count = 0;


        mFreq = new HashSet<>();
        mfinalResult = new HashSet<>();
        mPatternSet = new HashSet<>();
        mTmpPattern = new HashSet<>();
        int count = 0;


        System.out.println(mDNAStrand);
            for(int i = 0; i <  mDNAStrand.length() && k <= mDNAStrand.length() ; i++,k++) {

            String pattern = mDNAStrand.substring(i,k);

            count = this.getPatternOccurence(pattern);

                /**
                 * IF we find a count greater than zero add it to the list.
                 */
                if(count > 0 ){


                    System.out.println("We found occurence greater than zero " + count + " pattern:" + pattern);

                        mFreq.add(new BIOKmer(pattern,count));
                        mPatternSet.add(pattern);
                        if( count >= max_count) {
                            max_count = count ;
                        }
            }


        }

     Iterator<BIOKmer> bi = mFreq.iterator();
        while(bi.hasNext()){

            BIOKmer tmp = bi.next();

            if( tmp.getmKmerCount() == max_count){
                mTmpPattern.add(tmp.getmKmerPattern());
            }
        }



        return mTmpPattern;

    }




    public int search(String pattern){

        int j, M = pattern.length();
        int i, N = mDNAStrand.length();

        for( i = 0, j = 0; i < N && j < M; i++){

            if(mDNAStrand.charAt(i) == pattern.charAt(j)){
                j++;
            } else {
                i -= j; j = 0;
            }

        }
        if(j == M) return i - M; // Found.
        else {
            return N; // Not found.
        }



    }




}
