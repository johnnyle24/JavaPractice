package assignment10;

import java.util.LinkedList;

public class HashDebugger {
	
	public static void main (String[] args) {
		
		LinkedList<String>[] mHashTable = (LinkedList<String>[]) new LinkedList[13];
		
		mHashTable[0].addFirst((String) "yellow");
		
		GoodHashFunctor gFunctor = new GoodHashFunctor();
		QuadProbeHashTable qHash = new QuadProbeHashTable(13, gFunctor);
		ChainingHashTable cHash = new ChainingHashTable(13, gFunctor);
		
		MediocreHashFunctor mFunctor;
		BadHashFunctor bFunctor;

		qHash.add("Yellow");
		
		cHash.add("yellow");
		
		
	}
	
}
