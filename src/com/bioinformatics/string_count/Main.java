package com.bioinformatics.string_count;

/**
 * Bioinformatic program that can handle looking for
 * patterns in a DNA string.  This program can handle overlaps.
 */
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;




public class Main {

    public static void main(String[] args) {

        Set<String> freq;
        Set<String> kmers = new HashSet<String>();
        final String pattern = "AA";
        final String dna_string = "AGTCGCATAGT";
        int len = 9;


       BIOOperations bio = BIOOperations.newInstance(dna_string.toUpperCase());

        System.out.println(bio.getGenomeCompliment());
     //   long pattern_to_number = bio.patternToNumber(dna_string);

     //   System.out.println("Pattern to number: " +pattern_to_number);

    //    kmers = bio.getKmers(len);



     //   bio.getFrequencyOfList();
        bio = null;
    }
}
