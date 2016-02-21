package assignment6;
 
import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;
 
/**
 * Test cases for MyLinkedList<E>
 *
 * @author Emily Dennis, Johnny Le
 *
 */
public class MyLinkedListTest {
	
		MyLinkedList<Integer> testerAddition, testerIsEmpty, testerGet, testerRemove, testerIndex,
								testerSize, testerClear, testerToArray;
		
		Integer[] referenceArray = { 0, 1, 2, 3, 4, 5 };
		
		@Before
		public void setup() {
			testerAddition = new MyLinkedList<Integer>();
			
			testerIsEmpty = new MyLinkedList<Integer>();
			
			testerGet = new MyLinkedList<Integer>();
			
			testerRemove = new MyLinkedList<Integer>();
			
			testerIndex = new MyLinkedList<Integer>();
			
			testerSize = new MyLinkedList<Integer>();
			
			testerClear = new MyLinkedList<Integer>();
			
			testerToArray = new MyLinkedList<Integer>();
			
		}
 
        @Test
        public void testAddFirst() {
                // Tests adding first, for the first time
				testerAddition.addFirst(0);
                assertEquals(0, (int) testerAddition.getFirst());
 
                // Tests adding first, for the second time
                testerAddition.addFirst(1);
                assertEquals(1, (int) testerAddition.getFirst());

        }
 
        @Test
        public void testIsEmpty() {
                assertEquals(true, testerIsEmpty.isEmpty());
                
                testerIsEmpty.addFirst(0);

                assertEquals(false, testerIsEmpty.isEmpty());
        }
 
        @Test
        public void testAdd() {
                // Tests adding at index, for the first time
                testerAddition.addFirst(1);
                testerAddition.addFirst(0);
                testerAddition.addLast(2);
                testerAddition.add(1, 3);
                assertEquals(3, (int) testerAddition.get(1));
 
                testerAddition.add(2, 4);
                assertEquals(4, (int) testerAddition.get(2));
 
                // Tests adding at index, for the second time
                testerAddition.add(2, 5);
                assertEquals(5, (int) testerAddition.get(2));
 
                testerAddition.add(0, 6);
                assertEquals(6, (int) testerAddition.getFirst());
 
                // Test adding to the final position
                testerAddition.add(7, 7);
                assertEquals(7, (int) testerAddition.getLast());
        }
 
        @Test(expected = IndexOutOfBoundsException.class)
        public void testAddExceptionNegativeIndex() {
                testerAddition.add(-1, 5);
        }
 
        @Test(expected = IndexOutOfBoundsException.class)
        public void testAddExceptionTooLarge() {
                testerAddition.addFirst(1);
                testerAddition.addLast(4);
                testerAddition.add(1, 2);
                testerAddition.add(2, 3);
                testerAddition.add(5, 5); // Should throw the exception here
        }
 
        @Test
        public void testAddLast() {
                // Tests adding last, for the first time
                testerAddition.addFirst(0);
                testerAddition.addLast(1);
                assertEquals(1, (int) testerAddition.getLast());
 
                // Tests adding last, for the second time
                testerAddition.addLast(2);
                assertEquals(2, (int) testerAddition.getLast());
 
                // Tests adding last, again
                testerAddition.addLast(3);
                assertEquals(3, (int) testerAddition.getLast());
        }
 
        @Test
        public void testGetFirst() {
                testerGet.addFirst(0);
                assertEquals(0, (int) testerGet.getFirst());
 
                testerGet.addLast(5);
                testerGet.add(1, 1);
                testerGet.add(2, 2);
                testerGet.add(3, 3);
                testerGet.add(4, 4);
                assertEquals(0, (int) testerGet.getFirst());
        }
 
        @Test(expected = NoSuchElementException.class)
        public void testGetFirstException() {
                testerGet.getFirst();
        }
 
        @Test
        public void testGetLast() {
                testerGet.addFirst(0);
                assertEquals(0, (int) testerGet.getLast());
 
                testerGet.addLast(5);
                testerGet.add(1, 1);
                testerGet.add(2, 2);
                testerGet.add(3, 3);
                testerGet.add(4, 4);
                assertEquals(5, (int) testerGet.getLast());
        }
 
        @Test(expected = NoSuchElementException.class)
        public void testGetLastException() {
                testerGet.getLast();
        }
 
        @Test
        public void testGet() {
                testerGet.addFirst(0);
                testerGet.addLast(5);
                testerGet.add(1, 1);
                testerGet.add(2, 2);
                testerGet.add(3, 3);
                testerGet.add(4, 4);
                assertEquals(0, (int) testerGet.get(0));
                assertEquals(1, (int) testerGet.get(1));
                assertEquals(2, (int) testerGet.get(2));
                assertEquals(3, (int) testerGet.get(3));
                assertEquals(4, (int) testerGet.get(4));
                assertEquals(5, (int) testerGet.get(5));
        }
 
        @Test(expected = IndexOutOfBoundsException.class)
        public void testGetExceptionNegativeIndex() {
                testerGet.addFirst(1);
                testerGet.get(-1);
        }
 
        @Test(expected = IndexOutOfBoundsException.class)
        public void testGetExceptionTooLarge() {
                testerGet.addFirst(1);
                testerGet.addLast(4);
                testerGet.add(1, 2);
                testerGet.add(2, 3);
                testerGet.get(5); // Should throw the exception here
        }
 
        @Test
        public void testRemoveFirst() {
 
                testerRemove.addFirst(0);
                assertEquals(0, (int) testerRemove.removeFirst());
                
                assertEquals(true, testerRemove.isEmpty());
 
                testerRemove.addFirst(0);
                testerRemove.addLast(5);
                testerRemove.add(1, 1);
                testerRemove.add(2, 2);
                testerRemove.add(3, 3);
                testerRemove.add(4, 4);
                assertEquals(0, (int) testerRemove.getFirst()); //First value will return 0
                
                assertEquals(0, (int) testerRemove.removeFirst()); //Removes the first value and returns it
                
                assertEquals(1, (int) testerRemove.getFirst()); //First value should now be 1
        }
 
        @Test(expected = NoSuchElementException.class)
        public void testRemoveFirstException() {
                testerGet.removeFirst();
        }
 
        @Test
        public void testRemoveLast() {
 
                testerRemove.addFirst(0);
                assertEquals(0, (int) testerRemove.removeLast()); //Last value removed is 0
                
                assertEquals(true, testerRemove.isEmpty()); //List should become empty
 
                testerRemove.addFirst(0);
                testerRemove.addLast(5);
                testerRemove.add(1, 1);
                testerRemove.add(2, 2);
                testerRemove.add(3, 3);
                testerRemove.add(4, 4);
                assertEquals(5, (int) testerRemove.getLast()); //Last value should 5
                
                assertEquals(5, (int) testerRemove.removeLast()); //Last value should be removed and returned
                
                assertEquals(4, (int) testerRemove.getLast()); //New last value should be 4
        }
 
        @Test(expected = NoSuchElementException.class)
        public void testRemoveLastException() {
                testerGet.removeLast();
        }
 
        @Test
        public void testRemove() {
                // Tests a basic removal
                testerRemove.addFirst(0);
                testerRemove.addLast(5);
                testerRemove.add(1, 1);
                testerRemove.add(2, 2);
                testerRemove.add(3, 3);
                testerRemove.add(4, 4);
                assertEquals(6, testerRemove.size());

                assertEquals(3, (int) testerRemove.remove(3));
                
                assertEquals(5, testerRemove.size());
 
                // Tests removing the last item with this method
                assertEquals(5, (int) testerRemove.remove(4));
                
                assertEquals(4, testerRemove.size());
 
                // Tests removing the first item with this method
                assertEquals(0, (int) testerRemove.remove(0));
                assertEquals(3, testerRemove.size());
        }
 
        @Test(expected = IndexOutOfBoundsException.class)
        public void testRemoveExceptionNegativeIndex() {
                testerRemove.addFirst(1);
                testerRemove.remove(-1);
        }
 
        @Test(expected = IndexOutOfBoundsException.class)
        public void testRemoveExceptionTooLarge() {
                testerRemove.addFirst(1);
                testerRemove.addLast(4);
                testerRemove.add(1, 2);
                testerRemove.add(2, 3);
                testerRemove.remove(5); // Should throw the exception here
        }
 
        @Test
        public void testLastIndexOf() {
                testerIndex.addFirst(0);
                testerIndex.addLast(5);
                testerIndex.add(1, 1);
                testerIndex.add(2, 2);
                testerIndex.add(3, 3);
                testerIndex.add(4, 4);
 
                assertEquals(4, testerIndex.lastIndexOf(4));
 
                testerIndex.addLast(4);
                assertEquals(6, testerIndex.lastIndexOf(4));
 
                testerIndex.addFirst(4);
                assertEquals(7, testerIndex.lastIndexOf(4));
 
                assertEquals(-1, testerIndex.lastIndexOf(10));
        }
 
        @Test
        public void testIndexOf() {
                testerIndex.addFirst(0);
                testerIndex.addLast(5);
                testerIndex.add(1, 1);
                testerIndex.add(2, 2);
                testerIndex.add(3, 3);
                testerIndex.add(4, 4);
                assertEquals(4, testerIndex.indexOf(4));
 
                testerIndex.addLast(4);
                assertEquals(4, testerIndex.indexOf(4));
 
                testerIndex.addFirst(4);
                assertEquals(0, testerIndex.indexOf(4));
 
                assertEquals(-1, testerIndex.indexOf(10));
        }
 
        @Test
        public void testSize() {
                testerSize.addFirst(0);
                testerSize.addLast(5);
                testerSize.add(1, 1);
                testerSize.add(2, 2);
                testerSize.add(3, 3);
                testerSize.add(4, 4);
                assertEquals(6, testerSize.size());
        }
 
        @Test
        public void testClear() {
                testerClear.addFirst(0);
                testerClear.addLast(5);
                testerClear.add(1, 1);
                testerClear.add(2, 2);
                testerClear.add(3, 3);
                testerClear.add(4, 4);
                testerClear.clear();
                assertEquals(true, testerClear.isEmpty());
        }
 
        @Test
        public void testToArray() {
                testerToArray.addFirst(0);
                testerToArray.addLast(5);
                testerToArray.add(1, 1);
                testerToArray.add(2, 2);
                testerToArray.add(3, 3);
                testerToArray.add(4, 4);

                assertArrayEquals(referenceArray, testerToArray.toArray());
        }
 
}