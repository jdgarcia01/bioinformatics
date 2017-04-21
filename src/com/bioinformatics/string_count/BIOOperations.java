package com.bioinformatics.string_count;

/**
 * Created by johngarcia on 4/20/17.
 */

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class BIOOperations {

    private String mDNAStrand;
    private Set<BIOKmer> mFreq;
    private Set<String> mPatternSet;
    private Set<String> mTmpPattern;


    /**
     * Pass in the sequence we wish to operate on.
     * @param dna_strand
     */
    public static BIOOperations newInstance(String sequence){

        return new BIOOperations(sequence);

    }


    /**
     * Private constructor.....use the newInstance method to use.
     * @param dna_strand
     */
    private BIOOperations(String dna_strand){

        mDNAStrand = dna_strand;


    }

    /**
     * Method to look for patterns in a DNA String.
     * This method DOES handle overlaps.
     * @param pattern
     * @return count
     */

    public int getPatternOccurrence(String pattern){

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

    /**
     * This method will look for maximum occurrences of patterns of k length.
     * These patterns are known as K-mers.
     * @param k
     * @return HashSet of most frequently found patterns of k length.
     */

    public Set<String> getKmers(int k){

        /**
         * Get the first k length substring of the DNA Strand.
         */

        int max_count = 0;


        mFreq = new HashSet<>();
        mPatternSet = new HashSet<>();
        mTmpPattern = new HashSet<>();
        int count = 0;


        System.out.println(mDNAStrand);
            for(int i = 0; i <  mDNAStrand.length() && k <= mDNAStrand.length() ; i++,k++) {

            String pattern = mDNAStrand.substring(i,k);

            count = this.getPatternOccurrence(pattern);

                /**
                 * IF we find a count greater than zero add it to the list.
                 */
                if(count > 0 ){

                        mFreq.add(new BIOKmer(pattern,count));
                    /**
                     * Keep track of our max occurrence.
                     */
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


}
