package com.bioinformatics.string_count;

/**
 * Bioinformatic program that can handle looking for
 * patterns in a DNA string.  This program can handle overlaps.
 */
import java.util.Set;
import java.util.HashSet;



public class Main {

    public static void main(String[] args) {

       Set<String> freq;
       final String pattern = "TTTTA";
       final String dna_string = "GTATCTAGCGACGCGGTGAAGTAACTAGCTGTCTACGGTATCTAGCGCTGTCTACGGACGCGGTGGACGCGGTGGTATCTAGCAAGTAACTAGTATCTAGCAAGTAACTAAAGTAACTAAAGTAACTAGCTGTCTACGGCTGTCTACGAGCCATAAGCTGTCTACGAGCCATAAGCTGTCTACGAGCCATAAGTATCTAGCAAGTAACTAGCTGTCTACGAAGTAACTAGTATCTAGCGTATCTAGCGCTGTCTACGGACGCGGTGGACGCGGTGGCTGTCTACGGTATCTAGCGCTGTCTACGAAGTAACTAAAGTAACTAGTATCTAGCGACGCGGTGGACGCGGTGAGCCATAAGTATCTAGCGTATCTAGCGTATCTAGCGCTGTCTACGAAGTAACTAGTATCTAGCGTATCTAGCAGCCATAAGCTGTCTACGGCTGTCTACGAGCCATAAGACGCGGTGAAGTAACTAGTATCTAGCGTATCTAGCAAGTAACTAAAGTAACTAAGCCATAAGTATCTAGCGTATCTAGCAAGTAACTAGCTGTCTACGGCTGTCTACGGTATCTAGCAAGTAACTAGTATCTAGCGCTGTCTACGAGCCATAAGTATCTAGCGCTGTCTACGGACGCGGTGAGCCATAAGACGCGGTGGTATCTAGCGCTGTCTACGAAGTAACTAGCTGTCTACGGACGCGGTGGCTGTCTACGGCTGTCTACGAAGTAACTAGACGCGGTGAAGTAACTAAGCCATAAAAGTAACTAGACGCGGTGGCTGTCTACGAGCCATAAGACGCGGTGGACGCGGTGAAGTAACTAAGCCATAAGACGCGGTGAAGTAACTAGTATCTAGCGCTGTCTACGGCTGTCTACGAAGTAACTAGCTGTCTACG";
       int count = 0;

       BIOOperations bio = BIOOperations.newInstance(dna_string);


        count = bio.getPatternOccurrence(pattern);


        System.out.println("Number of Occurences: " + count + " And Pattern: " + pattern);


        freq = bio.getKmers(14);

        System.out.println("Most Frequent K-mer: "+ freq);

        bio = null;
    }
}
