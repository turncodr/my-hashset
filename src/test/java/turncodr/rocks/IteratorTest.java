package turncodr.rocks;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IteratorTest {
	
	private HashSet<TestObject> hashSet;
	Iterator<TestObject> it;
	TestObject testObject0;
	TestObject testObject1;
	TestObject testObject2;
	TestObject testObject3;
	TestObject testObject4;
	TestObject testObject5;
	TestObject testObject6;

	@BeforeEach
	void setUp() throws Exception {
		hashSet = new HashSet<>(10);
		testObject0 = new TestObject(0);
		testObject1 = new TestObject(1);
		testObject2 = new TestObject(2);
		testObject3 = new TestObject(3);
		testObject4 = new TestObject(4);
		testObject5 = new TestObject(5);
		testObject6 = new TestObject(6);

		hashSet.add(testObject0);
		hashSet.add(testObject1);
		hashSet.add(testObject2);
		hashSet.add(testObject3);
		hashSet.add(testObject4);
		hashSet.add(testObject5);
		hashSet.add(testObject6);

		/* Internal structure is:
		 * ArrayList Size= 14
		 * |__0_LinkedList
		 * 		|__0_testObject0
		 * |__1_LinkedList
		 * 		|__0_testObject2
		 * 		|__1_testObject1
		 * |__2_LinkedList
		 * 		|__0_testObject4
		 * 		|__1_testObject3 
		 * |__3_LinkedList 
		 * 		|__0_testObject6
		 * 		|__1_testObject5  			
		 */

		it = hashSet.iterator();
	}

	@Test
	void testHasNextOnFirstPosition() {
		assertTrue(it.hasNext());
	}

	@Test
	void testHasNextOnLastPosition() {
		it.next();
		it.next();
		it.next();
		it.next();
		it.next();
		it.next();
		it.next();
		assertFalse(it.hasNext());
	}

	@Test
	void testCompleteIteration() {
		assertEquals(testObject0, it.next()); 
		assertEquals(testObject2, it.next()); 
		assertEquals(testObject1, it.next()); 
		assertEquals(testObject4, it.next()); 
		assertEquals(testObject3, it.next());
		assertEquals(testObject6, it.next());
		assertEquals(testObject5, it.next());
	}

	@Test
	void testRemoveIllegalState() {
		assertThrows(IllegalStateException.class, () -> {it.remove();});
	}

	@Test
	void testRemoveFirst() {
		it.next();
		it.remove();
		assertEquals(6, hashSet.size());
		assertEquals(testObject2, it.next()); 
		assertEquals(testObject1, it.next()); 
		assertEquals(testObject4, it.next()); 
		assertEquals(testObject3, it.next());
		assertEquals(testObject6, it.next());
		assertEquals(testObject5, it.next());

		it = hashSet.iterator();
		assertEquals(testObject2, it.next()); 
		assertEquals(testObject1, it.next()); 
		assertEquals(testObject4, it.next()); 
		assertEquals(testObject3, it.next());
		assertEquals(testObject6, it.next());
		assertEquals(testObject5, it.next());

		
	}

	@Test
	void testRemoveLast() {
		it.next();
		it.next();
		it.next();
		it.next();
		it.next();
		it.next();
		it.next();
		it.remove();
		assertEquals(6, hashSet.size());

		it = hashSet.iterator();
		assertEquals(testObject0, it.next()); 
		assertEquals(testObject2, it.next()); 
		assertEquals(testObject1, it.next()); 
		assertEquals(testObject4, it.next()); 
		assertEquals(testObject3, it.next());
		assertEquals(testObject6, it.next());
	}

	@Test
	void testRemoveAll() {
		it.next();
		it.remove();
		it.next();
		it.remove();
		it.next();
		it.remove();
		it.next();
		it.remove();
		it.next();
		it.remove();
		it.next();
		it.remove();
		it.next();
		it.remove();
		assertEquals(0, hashSet.size());

		it = hashSet.iterator();
		assertFalse(it.hasNext());
		
	}
	
}
