package com.bioinformatics.string_count;

/**
 * Created by johngarcia on 4/20/17.
 */
/**
 * TODO - change integers to long long to handle longer input lengths.
 */


import java.util.*;


public class BIOOperations {

    private String mDNAStrand;
    private Set<BIOKmer> mFreq;
    private Set<String> mTmpPattern;
    private String[] mConvertedList;
    private HashMap<Integer,String>frequency_map = new HashMap<>();
    private Set<String> filter_map = new HashSet<>();
    private List<BIOKmer> frequencyArray = new ArrayList<BIOKmer>();
    private int[] mFrequencyNumber;
    private List<BIOGenomeCount> mPatternCount = new ArrayList<>();
    private HashMap<String, Integer> mFreqArray = new HashMap<>();

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

    public ArrayList<Integer> getOccurencePosition(String pattern){

        ArrayList<Integer> patternPosition = new ArrayList<>();

        int lastIndex = 0;
        int count = 0;

        while(lastIndex != -1){

            lastIndex = mDNAStrand.indexOf(pattern, lastIndex);

            if(lastIndex != -1){
                count++;
                 patternPosition.add(lastIndex);
                lastIndex++;
                }
            }



        return patternPosition;

    }

    public void patternCount(String pattern){
        int lastIndex = 0;
        int count = 0;
        ArrayList<Integer> pos_list = new ArrayList<>();

        while(lastIndex != -1){

            lastIndex = mDNAStrand.indexOf(pattern, lastIndex);

            if(lastIndex != -1){
                count++;
                lastIndex++;
            }

        }
        mPatternCount.add(new BIOGenomeCount(count, pattern));
        mFreqArray.put(pattern, count);


    }
    public HashMap<String, Integer> getmFreqArray(){
        return mFreqArray;
    }

    public List<BIOGenomeCount> getPatternCountMap(){

        return mPatternCount;

    }

    /**
     * *******************************GOOD CODE***************************
     * Method to look for patterns in a DNA String.
     * This method DOES handle overlaps.
     * @param pattern
     * @return count
     */


    public int getPatternOccurrence(String pattern){

        int lastIndex = 0;
        int count = 0;
        ArrayList<Integer> pos_list = new ArrayList<>();

        while(lastIndex != -1){

            lastIndex = mDNAStrand.indexOf(pattern, lastIndex);

            if(lastIndex != -1){
                count++;
                if(count > 2){

                    //    System.out.println("[*] The start position is: " + lastIndex + " of pattern: " + pattern);
                    pos_list.add(lastIndex);
                }
                lastIndex++;
            }

        }
        if( pos_list.size() > 0) {
        //    System.out.println("[*] The clump L is: " + pos_list.get(0) + " to " + pos_list.get(pos_list.size() - 1));
        }
        return count;

    }


    public List<Integer> getPatternOccurencePositions(String pattern){

        int lastIndex = 0;
        int len_of_clump = 0;
        int start_of_clump = 0;
        int end_of_clump = 0;

        List pos = new ArrayList<Integer>();

        while(lastIndex != -1){

            lastIndex = mDNAStrand.indexOf(pattern, lastIndex);
            if(lastIndex != -1) {
                pos.add(lastIndex);
                if(start_of_clump == 0){
                    start_of_clump = lastIndex;
                }
                System.out.print(lastIndex + " ");
                lastIndex++;
            }
        }
        len_of_clump = lastIndex - start_of_clump;
        System.out.println("[*] The length is: " + len_of_clump);

        return pos;

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


        for(int i = 0; i <  mDNAStrand.length() && k <= mDNAStrand.length() ; i++,k++) {

            String pattern = mDNAStrand.substring(i,k);

            count = this.getPatternOccurrence(pattern);
            /**
             * We found a k-mer and now convert it from pattern A,C,G,T => 0,1,2,3
             */
            int pattern_to_number = this.patternToNumber(pattern);
            this.patternCount(pattern);
            if(filter_map.add(pattern) == true) {
                //    System.out.println("Adding pattern: " + pattern);
                frequency_map.put(count, pattern);
            }
            //   System.out.println("The occurence is: " + count + " pattern: " + pattern + " kmer pattern to number: " + pattern_to_number);
            //     System.out.print(".");

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


    /**
     * **************************GOOD CODE
     * @param list_to_convert
     * @return
     */
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

    /**
     * ***********************GOOD CODE
     * @param number
     * @param len
     * @param base
     * @return
     */
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
     * *********************GOOD CODE
     * @param number
     * @param length
     * @return
     */
    public int convertToBase10(int[] number, int length){

        int index = length - 1;
        int answer = 0;
        // System.out.println("Length is: " + index);
        for(Integer i : number ){
            //   System.out.println("Number: " +i);
            answer =  answer + ((int)Math.pow(4, index)  * i);
            index--;

        }

        return answer;

    }

    /** GOOD CODE
     * Convert a string pattern to its base 4 number;
     * Example: A C G T = 0,1,2,3.
     * @param pattern
     * @return
     */
    public int patternToNumber(String pattern){

        int base10Number = 0;
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
        base10Number = this.convertToBase10(toNumber,toNumber.length);

        return base10Number;

    }



    public void getFrequencyOfList(){

        for( BIOKmer k : frequencyArray){

            if(k.getmKmerCount()  >= 3 ) {
                System.out.println("Kmer count: " + k.getmKmerCount() + " Kmer Number: " + k.getmPatternToNumber() + " Pattern: " + k.getmKmerPattern());
            }


        }



    }

    public char[] getGenomeCompliment(){

        int genome_length = mDNAStrand.length();
        char[] character_genome = mDNAStrand.toCharArray();
        char[] reverse_genome = new char[genome_length];
        char[] finalComplimentGenome = new char[genome_length];
        /**
         * take the dna_string and reverse it.
         */

        int count = 0;
        while(genome_length != 0 ){

            reverse_genome[count] = character_genome[genome_length - 1];
            count++;
            genome_length--;

        }

        for(int i = 0; i < count; i++){

            switch(reverse_genome[i]){
                case 'A': finalComplimentGenome[i] = 'T'; break;
                case 'T': finalComplimentGenome[i] = 'A'; break;
                case 'G': finalComplimentGenome[i] = 'C'; break;
                case 'C': finalComplimentGenome[i] = 'G'; break;
                default:  System.out.println("Bad input in geGenomeCompliment");


            }


        }

        return finalComplimentGenome;

    }

    public HashMap<Integer,String> getFrequencyMap(){

        return frequency_map;
    }



    public long findInClumpWindow(String pattern, StringBuilder dna_sub_string, int number_of_times){

    //    System.out.println("In method!!!!" + dna_sub_string);
        int count = 0;

        int lastIndex = 0;

        while(lastIndex != -1){

            lastIndex = dna_sub_string.indexOf(pattern, lastIndex);

            if(lastIndex != -1){
                count++;
                lastIndex++;
            }

        }
        if(count >= number_of_times) {

            System.out.println("[**********] Found in Clump Window " + pattern + " " + count + " Times!");
            return count;
        }
        return 0;

    }

    /**
     * Find patterns in window of length L. for K distinct K-mers.
     *
     * @param L length of window
     * @param pattern distinct kmer pattern.
     */


    public void findPatternClumps(int L, String pattern, int number_of_times){

        Set<BIOGenomeCount> myClumpSet = new HashSet<>();
        StringBuilder dna_sub_string;


        long strand_length = mDNAStrand.length();
        int index = 0;

        /**
         * iterate over the entire string
         */
        for(int c = 0; c < strand_length; c++){

            dna_sub_string = new StringBuilder();
          //  System.out.println(mDNAStrand.toCharArray()[c]);

            for(index = c; index < c + L &&  index < strand_length; index++){


            //    System.out.println("[*] Window: "+ mDNAStrand.toCharArray()[index]);

                /**
                 * put the window sub string into a variable.  This is
                 * passed into the find method
                 */

                dna_sub_string.append(  mDNAStrand.charAt(index)) ;
           //     System.out.println("[**] Building Sub String: " + dna_sub_string);
            }

            /**
             * We have our window.  Now search for the pattern in the window.
             */
          //   System.out.println("[***] Checking for pattern");
             this.findInClumpWindow(pattern, dna_sub_string, number_of_times);
             dna_sub_string = null ;


        }



    }

    /**
     * Generate a Frequency array of a dna sequence.
     * @param k
     */
    public int[] computing_freq(int k){


        int[] freq = new int[(int) Math.pow(4,k)];

        ArrayList<Integer> freq_array = new ArrayList<>(200);
        int j = 0;

        for(int i = 0; i < mDNAStrand.length() - k + 1 ; i++){

            System.out.println("[*] Kmer: " + mDNAStrand.substring(i, i + k));
            j = this.patternToNumber(mDNAStrand.substring(i,i + k));
            System.out.println(j);
            freq[j] += 1;

        }

        return freq;

    }

    /**
     * Implement the FasterFrequentWords Algorithm.
     * @param k
     */
    public void FaterFrequentWords(int k){







    }

}

