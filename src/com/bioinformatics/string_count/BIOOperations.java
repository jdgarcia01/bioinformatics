package com.bioinformatics.string_count;

/**
 * Created by johngarcia on 4/20/17.
 */

import java.util.*;


public class BIOOperations {

    private String mDNAStrand;
    private Set<BIOKmer> mFreq;
    private Set<String> mTmpPattern;
    private String[] mConvertedList;
    private List<BIOKmer> frequencyArray = new ArrayList<BIOKmer>();
    private int[] mFrequencyNumber;

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
     * These patterns are known as K-mers. This method will not give duplicates.
     * @param k
     * @return HashSet of most frequently found patterns of k length.
     */

    public Set<String> getKmers(int k){

        /**
         * Get the first k length substring of the DNA Strand.
         */

        int max_count = 0;


        mFreq = new HashSet<>();
        mTmpPattern = new HashSet<>();
        int count = 0;


        System.out.println(mDNAStrand);
            for(int i = 0; i <  mDNAStrand.length() && k <= mDNAStrand.length() ; i++,k++) {

            String pattern = mDNAStrand.substring(i,k);

            count = this.getPatternOccurrence(pattern);
                /**
                 * We found a k-mer and now convert it from pattern A,C,G,T => 0,1,2,3
                 */
                int[] pattern_to_number = this.patternToNumber(pattern);
                System.out.println("The occurence is: " + count + " pattern: " + pattern + " kmer number: " + java.util.Arrays.toString(pattern_to_number));

                /**
                 * Create a new instance of a BIOKmer with pattern, frequency count and pattern number.
                 */

                frequencyArray.add(new BIOKmer(pattern,count, pattern_to_number));


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
                System.out.println(tmp.getmKmerCount() + " K-mer: " + tmp.getmKmerPattern() );
            }
        }

        return mTmpPattern;

    }


    private String[] convertNumberToPattern(int[] list_to_convert){

         mConvertedList = new String[list_to_convert.length];

        for(int i = 0; i < list_to_convert.length; i++){
           switch(list_to_convert[i]){
               case 0: mConvertedList[i] = "A"; break;
               case 1: mConvertedList[i] = "C"; break;
               case 2: mConvertedList[i] = "G"; break;
               case 3: mConvertedList[i] = "T"; break;
               default: System.out.println("Bad input");
           }

        }
         return mConvertedList;

    }

    public String[] numberToPattern(int number, int len, int base){

        String[] pattern = new String[len];
        int[] capture_remainder = new int[len];
        int i = 0;

         int remainder = 0;
         while(number != 0){
              remainder = number % base;
              number = number / base;
              capture_remainder[i] = remainder;
              i++;
         }

         pattern = this.convertNumberToPattern(capture_remainder);

          return pattern;
    }

    /**
     * Convert a string pattern to its base 4 number;
     * Example: A C G T = 0,1,2,3.
     * @param pattern
     * @return
     */
    public int[] patternToNumber(String pattern){

        int[] toNumber = new int[pattern.length()];

        for(int i = 0; i < pattern.length(); i++){
            switch (pattern.charAt(i)){
                case 'A': toNumber[i] = 0; break;
                case 'C': toNumber[i] = 1; break;
                case 'G': toNumber[i] = 2; break;
                case 'T': toNumber[i] = 3; break;
                default: System.out.println("Bad input");
            }
        }

       return toNumber;

    }

    public int[] computingFrequency(String text, int k){

        int[] result = new int[4^k];
        Set<String> pattern = new HashSet<String>();
        int num = 0;

        for(int i = 0; i < result.length; i++){
            result[i] = 0;
        }

        for(int i = 0; i < text.length() - k; i++){
        pattern = getKmers(k);



        }

        return result;


    }

    public void getmFrequencyNumber(){


        for(int i = 0; i < frequencyArray.size(); i++){
            frequencyArray.get(i).getmPatternToNumber();
        }



    }

}
