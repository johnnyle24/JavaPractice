package assignment4;
 
import static org.junit.Assert.*;
 
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
 
public class AnagramUtilTest {
 
        private String testString1, testString2, testString3, testString4,
                        testString5;
        private String[] testArray1 = new String[7];
        private String[] array1Return = new String[4];
        private String[] array2Return = new String[10];
 
        //Defines variables
        
        @Before
        public void setUp() throws Exception {
                testString1 = "install";
                testString2 = "gentoo";
                testString3 = "muddy";
                testString4 = "and";
                testString5 = "veiled";
               
                testArray1[0] = "acre";
                testArray1[1] = "cares";
                testArray1[2] = "scare";
                testArray1[3] = "race";
                testArray1[4] = "races";
                testArray1[5] = "acres";
                testArray1[6] = "care";
               
                array1Return[0] = "acres";
                array1Return[1] = "cares";
                array1Return[2] = "races";
                array1Return[3] = "scare";
                
                array2Return[0] = "apers";
                array2Return[1] = "asper";
                array2Return[2] = "pares";
                array2Return[3] = "parse";
                array2Return[4] = "pears";
                array2Return[5] = "presa";
                array2Return[6] = "rapes";
                array2Return[7] = "reaps";
                array2Return[8] = "spare";
                array2Return[9] = "spear";
   
        }
 
        @After
        public void tearDown() throws Exception {
        }
        
        //toString() is tested in testSort()
        
        @Test
        public void testToString() {
        	
        }
        
        //toArray() is tested in testSort()
        
        @Test
        public void testToArray() {
        	
        }
 
        /**
         * This test ensures that input strings of varying lengths are organized into alphabetical order.
         * 
         * If this test is correct, toArray(), toString(), and insertionSort() are all working as expected.
         */
        
        @Test
        public void testSort() {
                assertEquals("aillnst", AnagramUtil.sort(testString1));
                assertEquals("egnoot", AnagramUtil.sort(testString2));
                assertEquals("ddmuy", AnagramUtil.sort(testString3));
                assertEquals("adn", AnagramUtil.sort(testString4));
                assertEquals("deeilv", AnagramUtil.sort(testString5));
        }
 
        @Test
        public void testInsertionSort() {
 
        }
        
        /**
         * 
         * Checks if the proper group was returned for array input. Checks this for an instantiated array
         * and a file opened.
         * 
         * If the file does not open or is empty, it will check if an empty array was returned.
         * 
         */
 
        @Test
        public void getLargestAnagramGroup() {
                assertArrayEquals(array1Return, AnagramUtil.getLargestAnagramGroup(testArray1));
                assertArrayEquals(array2Return, AnagramUtil.getLargestAnagramGroup("newsample.txt"));
        }
 
}