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
            System.out.println("[*] The clump L is: " + pos_list.get(0) + " to " + pos_list.get(pos_list.size() - 1));
        }
            return count;

    }

    public int findClumps(String pattern){

        int lastIndex = 0;
        int len_of_clump = 0;
        int start_of_clump = 0;
        int end_of_clump = 0;
        long pos = 0;

        while(pos != -1){

            pos = mDNAStrand.indexOf(pattern, lastIndex);
            if(pos != -1) {
                    System.out.println("[*] Start of position: " + pos);
                lastIndex++;

            }

        }

        return len_of_clump;



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

    /**
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

    public int[] computingFrequency(String text, int k){

        int[] result = new int[4^k];
        Set<String> pattern = new HashSet<String>();
        int num = 0;

        /**
         * Initialize all elements to zero.
         */
        for(int i = 0; i < result.length; i++){
            result[i] = 0;
        }

        for(int i = 0; i < text.length() - k; i++){
        pattern = getKmers(k);



        }

        return result;


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



}
