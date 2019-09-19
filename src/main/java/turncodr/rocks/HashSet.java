package turncodr.rocks;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class HashSet<E> implements Iterable<E>{
	
	private ArrayList<List<E>> table;
	private int initalCapacity=100;
	private double loadFactor = 0.75;
	private int size = 0;
	
	public HashSet() {
		this(100);
	}
	
	public HashSet(int initalCapacity) {
		super();
		this.initalCapacity = initalCapacity;
		int tableSize = (int)Math.ceil(initalCapacity*1/loadFactor);
		table = new ArrayList<>(tableSize);
		for (int i=0; i<tableSize; i++)
			table.add(null);
	}
	
	public boolean add(E element) {
		int key = hashFunction(element.hashCode());
		List<E> ll = table.get(key);
		if (ll==null) {
			LinkedList<E> nll = new LinkedList<>();
			nll.add(element);
			table.set(key, nll);
		} else {
			if (ll.contains(element))
				return false;
			else
				ll.add(0,element);
		}
		size++;
		return true;
	}

	public boolean contains(E element) {
		int key = hashFunction(element.hashCode());
		List<E> ll = table.get(key);
		return ll!=null && ll.contains(element);
	}

	public boolean remove(E element) {
		int key = hashFunction(element.hashCode());
		List<E> ll = table.get(key);
		boolean wasRemoved =  ll!=null&&ll.remove(element);
		if (wasRemoved) size--;
		return wasRemoved;
	}
	
	private int hashFunction(int key) {
		return key % table.size();
	}

	public String toString() {
		return "HashSet [table=" + table + "]";
	}
	
	@Override
	public Iterator<E> iterator() {
		/* TODO Implement Iterator interface, 
		 * see: https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
		 * In a first version you can skip the remove method ;-)
		 */
		return new Iterator<E>() {
			ListIterator<List<E>> firstLevelIterator;
			ListIterator<E> secondLevelIterator;
			ListIterator<E> oldSecondLevelIterator;
			List<E> secondLevelList;
	
			E objectForRemove;
			E object;		

			@Override
			public boolean hasNext() {
				if(firstLevelIterator == null){
					firstLevelIterator = table.listIterator();
					return findNextNonEmptySecondLevelList();
				}else{
					if(object != null){
						return true;
					}	
					
					if(secondLevelIterator.hasNext()){
						object = secondLevelIterator.next();
						return true;
					}else{
						return findNextNonEmptySecondLevelList();
					}	
				}
			}

			@Override
			public E next() {
				if(hasNext()){
					E nextObject = object;
					objectForRemove = object;
					object = null;
					return nextObject;
				}else{
					throw new NoSuchElementException();
				}
			}

			@Override
			public void remove(){
				if(objectForRemove == null){
					throw new IllegalStateException();
				}
				
				if(secondLevelList.contains(objectForRemove)){
					secondLevelIterator.remove();
				}else{
					oldSecondLevelIterator.remove();
				}

				objectForRemove = null;
				size--;	
				
			}			
			
			private boolean findNextNonEmptySecondLevelList(){
				while(firstLevelIterator.hasNext()){
					secondLevelList = firstLevelIterator.next();
					if(secondLevelList != null){
						if(!secondLevelList.isEmpty()){
							oldSecondLevelIterator = secondLevelIterator;
							secondLevelIterator = secondLevelList.listIterator();
							object = secondLevelIterator.next();
							return true;
						}
					}
				}
				object = null;
				return false;
			}
		};
	}

	public int size() {
		return size;
	}
}
