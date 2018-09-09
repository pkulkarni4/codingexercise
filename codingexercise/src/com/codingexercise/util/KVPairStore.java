package com.codingexercise.util;


/**
 * Interface for a key-value pair store.
 * 
 * @author Praveen Kulkarni
 *
 * @param <K>
 * @param <V>
 */
public interface KVPairStore<K,V> {

	/**
	 * Get the key
	 * @param key
	 * @return returns value for the key
	 */
	V get(K key);
	
	/**
	 * removes the entry that is associated with key. 
	 * @param key
	 * @return value for the key
	 */
	V remove(K key);
	
	/**
	 * Adds a k-v pair to hashtable
	 * 
	 * @param key
	 * @param val
	 */
	void add(K key, V val);
	
	/**
	 * returns true if hashtable is empty  and false otherwise.
	 * @return
	 */
	boolean isEmpty();
	
	/**
	 * Gets the size of the hashtable.
	 * @return
	 */
	int getSize();
}
