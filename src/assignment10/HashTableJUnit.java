package assignment10;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Rithie Penn, Johnny Le
 *
 */
public class HashTableJUnit {
	
	QuadProbeHashTable qHash;
	ChainingHashTable cHash;
	
	GoodHashFunctor gFunctor;
	MediocreHashFunctor mFunctor;
	BadHashFunctor bFunctor;
	
	String a, b, c, d, e, f, g, h;

	ArrayList<String> collectTest = new ArrayList<String>();
	ArrayList<String> collectTest2 = new ArrayList<String>();
	
	@Before
	public void setUp() throws Exception {
		
		gFunctor = new GoodHashFunctor();
		
		qHash = new QuadProbeHashTable(13, gFunctor);
		
		cHash = new ChainingHashTable(13, gFunctor);
		
		a = "Yellow";
		
		b = "jeopardy";
		
		c = "hello";
		
		d = "lmao";
		
		e = "ayy";
		
		f = "ffat20";
		
		g = "42";
		
		h = "wite";
		
		collectTest.add("Hi");
		collectTest.add("My");
		collectTest.add("Friend");
		collectTest.add("biceps");
		collectTest.add("be");
		collectTest.add("with");
		collectTest.add("you");
		collectTest.add("abcdefghijklmnopqrtuvwxyz");
		collectTest.add("0123456789");
		
		collectTest2.add("Hi");
		collectTest2.add("My");
		collectTest2.add("Friend");
		collectTest2.add("biceps");
		collectTest2.add("be");
		collectTest2.add("with");
		collectTest2.add("you");
		collectTest2.add("Pasz");
		collectTest2.add("abcdefghijklmnopqrtuvwxyz");
		collectTest2.add("0123456789");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testQuadAdd() {
		assertEquals(true, qHash.add(a));
		assertEquals(true, qHash.add(h));
		qHash.add("what");
		assertEquals(false, qHash.add("what"));
	}
	
	@Test
	public void testQuadAddAll() {
		assertEquals(true, qHash.addAll(collectTest));
		assertEquals(true, qHash.contains("Friend"));
	}
	
	
	@Test
	public void testQuadContains() {
		qHash.add(a);
		assertEquals(true, qHash.contains(a));
	}
	
	@Test
	public void testQuadContainsAll() {
		qHash.addAll(collectTest);
		assertEquals(false, qHash.containsAll(collectTest2));
	}
	
	@Test
	public void testQuadSize() {
		assertEquals(0, qHash.size());
		qHash.add(a);
		assertEquals(1, qHash.size());
		qHash.add(b);
		assertEquals(2, qHash.size());
		qHash.add(c);
		assertEquals(3, qHash.size());
		qHash.add(f);
		assertEquals(4, qHash.size());
	}
	
	@Test
	public void testQuadClear() {
		assertEquals(0, qHash.size());
		qHash.add(a);
		assertEquals(1, qHash.size());
		qHash.add(b);
		assertEquals(2, qHash.size());
		qHash.add(g);
		assertEquals(3, qHash.size());
		qHash.add(f);
		assertEquals(4, qHash.size());
		
		qHash.clear();
		assertEquals(0, qHash.size());
		
		qHash.add(a);
		assertEquals(1, qHash.size());
		assertEquals(true, qHash.contains(a));
		
		qHash.clear();
		assertEquals(0, qHash.size());
		
		assertEquals(false, qHash.contains(a));
		
	}
	
	@Test
	public void testQuadIsEmpty() {
		assertEquals(0, qHash.size());
		assertEquals(true, qHash.isEmpty());
		
		qHash.add(a);
		assertEquals(1, qHash.size());
		assertEquals(false, qHash.isEmpty());
		
		qHash.clear();
		assertEquals(true, qHash.isEmpty());
	}
	
	@Test
	public void testChainAdd() {
		assertEquals(true, cHash.add(a));
		
	}
	
	@Test
	public void testChainAddAll() {
		assertEquals(true, cHash.addAll(collectTest));
		assertEquals(true, cHash.contains("biceps"));
	}
	
	@Test
	public void testChainContains() {
		cHash.add(a);
		assertEquals(false, cHash.contains(c));
		assertEquals(false, cHash.contains(b));
	}
	
	@Test
	public void testChainContainsAll() {
		cHash.addAll(collectTest);
		assertEquals(false, cHash.containsAll(collectTest2));
	}
	
	@Test
	public void testChainSize() {
		assertEquals(0, cHash.size());
		cHash.add(a);
		assertEquals(1, cHash.size());
		cHash.add(b);
		assertEquals(2, cHash.size());
		cHash.add(c);
		assertEquals(3, cHash.size());
		cHash.add(f);
		assertEquals(4, cHash.size());
	}
	
	@Test
	public void testChainClear() {
		assertEquals(0, cHash.size());
		cHash.add(a);
		assertEquals(1, cHash.size());
		cHash.add(b);
		assertEquals(2, cHash.size());
		cHash.add(c);
		assertEquals(3, cHash.size());
		cHash.add(f);
		assertEquals(4, cHash.size());
		
		cHash.clear();
		assertEquals(0, cHash.size());
		
		cHash.add(a);
		assertEquals(1, cHash.size());
		assertEquals(true, cHash.contains(a));
		
		cHash.clear();
		assertEquals(0, cHash.size());
		
		assertEquals(false, cHash.contains(a));
		
	}
	
	@Test
	public void testChainIsEmpty() {
		assertEquals(0, cHash.size());
		assertEquals(true, cHash.isEmpty());
		
		cHash.add(a);
		assertEquals(1, cHash.size());
		assertEquals(false, cHash.isEmpty());
		
		cHash.clear();
		assertEquals(true, cHash.isEmpty());
	}
}
