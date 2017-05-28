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

    public static String readFileByLine(String fileName) {

         String dna_line = null;
        try {
            Stream<String> stream = Files.lines(Paths.get(fileName));
            dna_line = stream.toString();
            stream.forEach(System.out::println);

        } catch(Exception ex){
            System.out.println("Error with file: " + ex.getMessage());

        }

    System.out.println("String is: " + dna_line.toString());
        return dna_line;
    }




    public static void main(String[] args) {

        Set<String> freq;
        Set<String> kmers = new HashSet<String>();
        final String pattern = "CTTGATCAT";
        int len = 500;
        String dna_string = null;

        try {
             dna_string = new String(Files.readAllBytes(Paths.get("/Users/johngarcia/prog/java/bioinformatics/src/com/bioinformatics/string_count/vibrio.txt")));

        } catch (Exception ex){

            System.out.println("Error getting file: " + ex.getMessage());
        }
     /* String dna_string = "aactctatacctcctttttgtcgaatttgtgtgatttatagagaaaatcttattaactga" +
              "aactaaaatggtaggtttggtggtaggttttgtgtacattttgtagtatctgatttttaa" +
              "ttacataccgtatattgtattaaattgacgaacaattgcatggaattgaatatatgcaaa" +
              "acaaacctaccaccaaactctgtattgaccattttaggacaacttcagggtggtaggttt" +
              "ctgaagctctcatcaatagactattttagtctttacaaacaatattaccgttcagattca" +
              "agattctacaacgctgttttaatgggcgttgcagaaaacttaccacctaaaatccagtat" +
              "ccaagccgatttcagagaaacctaccacttacctaccacttacctaccacccgggtggta" +
              "agttgcagacattattaaaaacctcatcagaagcttgttcaaaaatttcaatactcgaaa" +
              "cctaccacctgcgtcccctattatttactactactaataatagcagtataattgatctga";
*/
      BIOOperations bio = BIOOperations.newInstance(dna_string.toUpperCase());


        System.out.println("Frequency: " + bio.getPatternOccurrence(pattern) + " string length: " + dna_string.length());

   //     char[] compliment = bio.getGenomeCompliment();

    /*    for(char c : compliment){
            System.out.print(c);
        } */

        System.out.println(bio.getGenomeCompliment());
     //   long pattern_to_number = bio.patternToNumber(dna_string);

     //   System.out.println("Pattern to number: " +pattern_to_number);

        kmers = bio.getKmers(len);
     //    bio.getPatternOccueencePositions(pattern);


     //   System.out.println(kmers.toString());



     //   bio.getFrequencyOfList();
     //   bio = null;

        final HashMap<Integer, String>myMap = bio.getFrequencyMap();
         myMap.forEach((k,v)-> {
             System.out.println("Item : " + k + " Count : " + v);
         });

        bio.getFrequencyOfList();
    }
}
