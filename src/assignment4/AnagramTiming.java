package assignment4;
 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

import assignment6.Timer;

 
public class AnagramTiming {
 
        /**
         * Creates random strings
         *
         * @param int length
         * @return String
         */
        public static String randomString(int length) {
                Random rand = new Random();
                String retval = "";
                for (int i = 0; i < length; i++) {
                        // ASCII values a-z,A-Z are contiguous (52 characters)
                        retval += (char) ('a' + (rand.nextInt(26)));
                }
                return retval;
        }
 
        public static String sort(String s) {
                String lowerCaseS = s.toLowerCase();
                Character[] characterS = toArray(lowerCaseS);
                Arrays.sort(characterS, new Comparator<Character>() {
                        @Override
                        public int compare(Character o1, Character o2) {
                                return o1.compareTo(o2);
                        }
                });
                String strS = toString(characterS);
                return strS;
        }
 
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
 
        private static String toString(Character[] characterS) {
                char[] charC = new char[characterS.length];
                for (int i = 0; i < characterS.length; i++) {
                        charC[i] = characterS[i];
                }
                String strS = new String(charC);
                return strS;
        }
 
        private static Character[] toArray(String lowerCaseS) {
                char[] charS = lowerCaseS.toCharArray();
                Character[] charC = new Character[charS.length];
                for (int i = 0; i < charS.length; i++) {
                        charC[i] = new Character(charS[i]);
                }
                return charC;
        }
 
        public static String[] getLargestAnagramGroupQuickSort(String[] s) {
                String[] sortAnagram = new String[s.length];
                for (int i = 0; i < s.length; i++) {
                        sortAnagram[i] = sort(s[i]) + s[i];
                }
                Arrays.sort(sortAnagram, new Comparator<String>() {
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
                for (int j = 1; j <= largest; j++) {
                        String sortAndWord = sortAnagram[endOfLargestIndex - largest + j];
                        String word = (String) sortAndWord.subSequence(
                                        sortAndWord.length() / 2, sortAndWord.length());
                        largestGroup[j - 1] = word;
                }
                return largestGroup;
        }
 
        public static void main(String[] args) {
 
                String[] array = new String[100];
                Timer methodT = new Timer();
                Timer loopT = new Timer();
 
                // // timing for areAnagram Method
                // for (int i = 0; i < 50; i++) {
                // int size = 100 * i;
                //
                // for (int j = 0; j < 100; j++) {
                // array[j] = randomString(size);
                // }
                //
                // loopT.start();
                // for (int j = 1; j < 100; j++) {
                // }
                // double loopTime = loopT.stop();
                //
                // methodT.start();
                // for (int j = 1; j < 100; j++) {
                // AnagramUtil.areAnagrams(array[j], array[j - 1]);
                // }
                // double methodTime = methodT.stop();
                //
                // double time = (methodTime - loopTime) / 99;
                // System.out.println(size + " " + time + '\n');
                // }
                //
                // timing for getLargestAnagramGroup method
                int arraySize = 100;
                String[] getLargestAnagramArray = new String[arraySize];
                int size = 8;
 
                for (int j = 0; j < 100; j++) {
                        getLargestAnagramArray[j] = randomString(size);
                }
 
                loopT.start();
                for (int j = 0; j < 100; j++) {
                }
                double loopTime = loopT.stop();
 
                methodT.start();
                AnagramUtil.getLargestAnagramGroup(getLargestAnagramArray);
                double methodTime = methodT.stop();
 
                double time = (methodTime - loopTime);
                System.out.println(arraySize + " " + time + '\n');
 
                // timing for getLargestAnagramGroup method
                // for (int i = 0; i < 50; i++) {
                // int size = 8;
                //
                // for (int j = 0; j < 100 * i; j++) {
                // arraylist.add(randomString(size));
                // }
                //
                // loopT.start();
                // for (int j = 0; j < 100 * i; j++) {
                // }
                // double loopTime = loopT.stop();
                //
                // methodT.start();
                // getLargestAnagramGroupQuickSort(array);
                // double methodTime = methodT.stop();
                //
                // double time = (methodTime - loopTime);
                // System.out.println(size + " " + time + '\n');
                // }
                // }
        }
}