package turncodr.rocks;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HashSetTest {
	
	private HashSet<Integer> hashSet;

	@BeforeEach
	void setUp() throws Exception {
		hashSet = new HashSet<>(10);
		hashSet.add(2);
	}

	@Test
	void testAdd() {
		boolean wasAdded = hashSet.add(1);
		assertTrue(wasAdded);
		assertTrue(hashSet.contains(1));
	}
	
	@Test
	void testAddDuplicate() {
		int size = hashSet.size();
		boolean wasAdded = hashSet.add(2);
		assertFalse(wasAdded);
		assertTrue(hashSet.contains(2));
		assertEquals(size, hashSet.size());
	}

	@Test
	void testContains() {
		assertTrue(hashSet.contains(2));
	}

	@Test
	void testRemove() {
		hashSet.remove(2);
		assertFalse(hashSet.contains(2));
	}
	
	@Test
	void testSize() {
		int size = hashSet.size();
		hashSet.add(1);
		assertEquals(size+1, hashSet.size());
		hashSet.remove(1);
		assertEquals(size, hashSet.size());
	}

	@Test
	void testIterator() {
		Iterator<Integer> it = hashSet.iterator();
		//Check twice, the call to hasNext() should not change the state
		assertTrue(it.hasNext());
		assertTrue(it.hasNext());
		//Check if you find the only element 2
		Integer onlyOne = it.next();
		assertEquals(2, onlyOne);
		//Now, there should be no next element
		assertFalse(it.hasNext());
		//Calling next() in this state should throw a NoSuchElemetException
		assertThrows(NoSuchElementException.class, () -> {it.next();});
	}
}
