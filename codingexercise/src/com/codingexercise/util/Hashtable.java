package com.codingexercise.util;

import java.util.ArrayList;

/**
 * 
 * An implementation for a key value pair. 
 * goal is to get O(1) for get, add and remove for the best case.
 * 
 * Main points:
 * <ol>
 * <li>Hashtable stores key-value pair.</li>
 * <li>Null keys and values are not allowed.</li>
 * <li>First, a hash code is required for a key and the key is stored as an index using modulo operator.</li>
 * <li>Traverse thru the Nodes and return the value if a matching node is found.</li>
 * <li>Does not preserve the order.</li>
 * </ol>
 *  
 * @author Praveen Kulkarni
 *
 * @param <K>
 * @param <V>
 */
public class Hashtable<K, V> implements KVPairStore<K, V> {

	private ArrayList<Node<?,?>> table;
	int numOfSlots = 10; // each slot will have linked nodes
	private int size; // size of the hashtable
	
	public Hashtable() {
		// initialize table
		table =  new ArrayList<>();
		// create empty 10 slots
		for(int i=0;i<numOfSlots;i++) {
			table.add(null);
		}
	}
	
 class Node<K, V> {
		int hash;
		K key;
		V value;
		Node<K, V> next;
		
		protected Node(int hash, K key, V value) {
			this(hash, key, value, null);
		}
		
		protected Node(int hash, K key, V value, Node<K, V> next) {
			this.hash = hash;
			this.key = key;
			this.value = value;
			this.next = next;
		}
	}
	
	@Override
	public V get(Object key) {
		// throw NPE if key is null
		if(key == null) {
			throw new NullPointerException("key cannot be null");
		}
		// get hash for the key
		int hash = key.hashCode();
		// find the index 
		int index = getIndex(hash);
		//look up linked list for the key
		Node temp = table.get(index);
		// get the matching value
		while(temp!=null) {			
			if(temp.hash == hash && temp.key.equals(key)) {
				return (V)temp.value;
			}
			temp = temp.next;
		}
		
		return null;		
	}

	private int getIndex(int hash) {
		return hash%numOfSlots;
	}

	@Override
	public V remove(Object key) {
		// get the hashcode
		int hash = key.hashCode(); // this will throw NPE
		int index = getIndex(hash);
		Node temp = table.get(index);
		// if the first element is the one to be removed
		if(temp !=null && temp.hash == hash && temp.key.equals(key)) {
			V val = (V) temp.value;
			temp=temp.next;
			table.set(index, temp);
			size--;
			return val;
		}
		
		Node prev = null;
		while(temp!=null) {
			if(temp.hash == hash && temp.key.equals(key)) {
				prev.next=temp.next;
				size--;
				return (V)temp.value;
			}
			prev = temp;
			temp = temp.next;
		}
		if(temp== null) return null;
		V val = (V) temp.value;
		prev.next=temp.next;
		size--;
		return val;
	}

	@Override
	public void add(Object key, Object value) {
		// value cannot be null
		if (value == null) {
            throw new NullPointerException("value cannot be null");
        }
		
		int hash = key.hashCode(); //this will throw NPE if key is null
		int index = getIndex(hash);
		Node temp = table.get(index);
		Node newNode = new Node(hash, key, value);
		// two cases here - either temp is null or there is collision
		if(temp == null) {
			addEntry(index, hash, key, value);
		}else {
			// if there is collision, there are two cases again - either value is being replaced or newly added
			boolean found = false;
			while(temp!=null) {
				if(temp.hash == hash && temp.key.equals(key)) {
					found = true;
					// replace value
					temp.value = value;
					break;
				}
				temp = temp.next;
			}
			
			if(!found) {
				addEntry(index, hash, key, value);
			}
		}	
	}

	private void addEntry(int index, int hash, Object key, Object value) {
		Node<K,V> e = (Node<K,V>) table.get(index);
        table.set(index, new Node(hash, key, value, e));
        size++;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int getSize() {		
		return size;
	}

}
