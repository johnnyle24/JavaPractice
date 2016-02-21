package assignment4;
 
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Comparator;
import java.util.ArrayList;
 
public class AnagramUtil {
 
        /**
         * This method returns the sorted version of the input string. The sorting
         * must be accomplished using an insertion sort.
         *
         * @param String s
         * @return String strS
         */
        public static String sort(String s) {
                String lowerCaseS = s.toLowerCase();
                Character[] characterS = toArray(lowerCaseS);
                insertionSort(characterS, new Comparator<Character>() {
                        @Override
                        public int compare(Character o1, Character o2) {
                                return o1.compareTo(o2);
                        }
                });
                String strS = toString(characterS);
                return strS;
        }
        
        /**
         * This method changes the generic Character array into a char[] then takes that array and changes it to a string.
         * 
         * @param Character[] characterS
         * return String strS
         * 
         */
 
        private static String toString(Character[] characterS) {
                char[] charC = new char[characterS.length];
                for (int i = 0; i < characterS.length; i++) {
                        charC[i] = characterS[i];
                }
                String strS = new String(charC);
                return strS;
        }
 
        /**
         * This method takes the lowerCaseS string and separates the values into a generic
         * character array.
         *
         * @param String lowerCaseS
         * @return Character[] charC
         */
        
        private static Character[] toArray(String lowerCaseS) {
                char[] charS = lowerCaseS.toCharArray();
                Character[] charC = new Character[charS.length];
                for (int i = 0; i < charS.length; i++) {
                        charC[i] = new Character(charS[i]);
                }
                return charC;
        }
 
        /**
         * This generic method sorts the input array using an insertion sort and the
         * input Comparator object.
         *
         * @param T[] t, Comparator<? super T> c
         * 
         */
        public static <T> void insertionSort(T[] t, Comparator<? super T> c) {
        	
                for (int i = 1; i < t.length; i++) {
                        T key = (T) t[i];
                        int j = i - 1;
                        while ((j > -1) && (c.compare(t[j], (T) key) > 0)) {
                                t[j + 1] = t[j];
                                j--;
                        }
                        t[j + 1] = key;
                }
        }
 
        /**
         * This method returns true if the two input strings are anagrams of each
         * other, otherwise returns false.
         *
         * @param String
         *            a, String b
         * @return boolean
         */
        public static boolean areAnagrams(String a, String b) {
                if (sort(a).equals(sort(b))) {
                        return true;
                }
                return false;
        }
 
        /**
         * This method returns the largest group of anagrams in the input array of
         * words, in no particular order. It returns an empty array if there are no
         * anagrams in the input array.
         * 
         * Each word is tagged with the sorted version of itself and the entire array is sorted
         * alphabetically. Once done, it will determine the size of a group of anagrams by reading them based 
         * on the first half of each of the modified strings. A integer is taken for the number in each
         * group.
         * 
         * The location of the largest group is tracked and when a larger group is found, that position
         * replaces the old.
         * 
         * This is repeated until the values remaining are location of the largest group,
         * the size of the largest group, and the string.
         *
         * @param String
         *            [] s
         * @return String[]
         */
        public static String[] getLargestAnagramGroup(String[] s) {
                String[] sortAnagram = new String[s.length];
                for (int i = 0; i < s.length; i++) {
                        sortAnagram[i] = sort(s[i]) + s[i];
                }
                insertionSort(sortAnagram, new Comparator<String>() {
                        @Override
                        public int compare(String o1, String o2) {
                                return o1.compareTo(o2);
                        }
                });
                int largest = 0;
                int endOfLargestIndex = 0;
                int index = 0;
                int sizeOfGroup = 1;
                for (int i = 1; i < sortAnagram.length; i++) {
                        // initialize size of group to 1
 
                        // check for largest anagram group
                        String string = (String) sortAnagram[i].subSequence(0,
                                        sortAnagram[i].length() / 2);
                        String beforeString = (String) sortAnagram[i - 1].subSequence(0,
                                        sortAnagram[i - 1].length() / 2);
                       
                        if (string.equals(beforeString)) {
                                sizeOfGroup++;
                                index++;
                                if (sizeOfGroup > largest) {
                                        largest = sizeOfGroup;
                                        endOfLargestIndex = index;
                                }
                        }
                        if (!(string.equals(beforeString))) {
                                index++;
                                if (sizeOfGroup > largest) {
                                        largest = sizeOfGroup;
                                }
                                sizeOfGroup = 1;
                                continue;
                        }
                }
               
                String[] largestGroup = new String[largest];
                for(int j = 1; j <= largest; j++) {
                        String sortAndWord = sortAnagram[endOfLargestIndex - largest + j];
                        String word = (String) sortAndWord.subSequence(sortAndWord.length()/2, sortAndWord.length());
                largestGroup[j - 1] = word;
                }
                return largestGroup;
        }
 
        /**
         * Behaves the same as the previous method, but reads the list of words from
         * the input filename. It is assumed that the file contains one word per
         * line. If the file does not exist or is empty, the method returns an empty
         * array because there are no anagrams.
         *
         * @param String
         *            filename
         * @return String[]
         */
        public static String[] getLargestAnagramGroup(String filename) {
        	String[] file = readFile(filename);
        	if (file.length == 0) {
        		return file;
        	}
            return getLargestAnagramGroup(file);
        }
       
        /**
         * Returns a String[] given a filename and will return an empty array if an exception is given.
         *
         * @param String
         *            filename
         * @return String[]
         */
        public static String[] readFile(String filename)
        {
                ArrayList<String> results = new ArrayList<String>();
                try
                {
                        BufferedReader input = new BufferedReader(new FileReader(filename));
                        while(input.ready())
                        {
                                results.add(input.readLine());
                        }
                        input.close();
                }
                catch(Exception e) {
                	e.printStackTrace();
                }
                String[] retval = new String[results.size()];
                return results.toArray(retval);
        }
        
        public static int dbl(int x) { return x*x;}
       
        /**
         * This method is a main file used to determine the output of the getLargestAnagramGroup.
         * It ensures that the file can be located and that the file works for the suggested test text files.
         * Unfortunately, largesample.txt returns the second largest group, not the largest.
         * 
         * @param 
         */
        
        public static void main (String[] args){
                String A[] = getLargestAnagramGroup("newsample.txt");
                for(int i=0;i<A.length;i++){
                        System.out.println(A[i]);
                }
                System.out.println(A.length);
        }
}