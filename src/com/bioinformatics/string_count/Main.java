package com.bioinformatics.string_count;

/**
 * Bioinformatic program that can handle looking for
 * patterns in a DNA string.  This program can handle overlaps.
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;


public class Main {

    public static void main(String[] args) {

        Set<String> freq;
        Set<String> kmers = new HashSet<String>();
        final String pattern = "GACT";
        int len = 9;
/*        String dna_string = null;

        try {
             dna_string = new String(Files.readAllBytes(Paths.get("/Users/johngarcia/prog/java/bioinformatics/src/com/bioinformatics/string_count/vibrio.txt")));

        } catch (Exception ex){

            System.out.println("Error getting file: " + ex.getMessage());
        } */
      String dna_string = "CGGACTCGACAGATGTGAAGAACGACAATGTGAAGACTCGACACGACAGAGTGAAGAGAAGAGGAAACATTGTAA";




      BIOOperations bio = BIOOperations.newInstance(dna_string.toUpperCase());

        bio.findPatternClumps(15, "ACA", 1);

        Set<String> kmer_set = bio.getKmers(len);

        for (String i: kmer_set) {
            System.out.println("[*] Found distinct Kmers: " + i);



        }


    }
}
