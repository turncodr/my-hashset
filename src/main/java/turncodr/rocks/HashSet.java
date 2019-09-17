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

			List<E> secondLevelList;
	
			E object;
			E deleteObject;
		
			boolean objectIsAlreadyFound = false;
			boolean firstSearch = true;
			boolean elementWasRemoved = false;

			@Override
			public boolean hasNext() {

				if(firstSearch){
					firstLevelIterator = table.listIterator();
					firstSearch = false;
					return iterateFirstLevel();
					
				}else{
					if(!objectIsAlreadyFound){
						return true;
					}	
					
					if(elementWasRemoved){
						secondLevelIterator = secondLevelList.
						listIterator(secondLevelIterator.previousIndex());
						elementWasRemoved=false;
					}

					if(secondLevelIterator.hasNext()){
						object = secondLevelIterator.next();
						return true;
					}else{
						return iterateFirstLevel();
					}	
				}
			}

			@Override
			public E next() {
				if(hasNext()){
					objectIsAlreadyFound = true;
					deleteObject = object;
					return object;
				}else{
					throw new NoSuchElementException();
				}
			}

			@Override
			public void remove(){
				if(firstSearch){
					throw new IllegalStateException();
				}
				elementWasRemoved = true;
				HashSet.this.remove(deleteObject);
			}			
			
			private boolean iterateFirstLevel(){
				while(firstLevelIterator.hasNext()){
					secondLevelList = firstLevelIterator.next();
					if(secondLevelList != null){
						if(!secondLevelList.isEmpty()){
							secondLevelIterator = secondLevelList.listIterator();
							object = secondLevelIterator.next();
							objectIsAlreadyFound = false;	
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
