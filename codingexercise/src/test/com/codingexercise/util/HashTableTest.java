package test.com.codingexercise.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.codingexercise.util.Hashtable;

public class HashTableTest {

	@Test
	void testHashtable_whenKeysGenerateSameHashCodes() {

		Hashtable<String, String> ht = new Hashtable<>();
		
		ht.add("FB", "v1");
		ht.add("Ea", "v2");
		
		assertEquals(ht.get("FB"), "v1");
		assertEquals(ht.get("Ea"), "v2");
		assertEquals(ht.getSize(), 2);
		ht.remove("FB");
		assertEquals(ht.get("FB"), null);
		assertEquals(ht.getSize(), 1);
		assertEquals(ht.isEmpty(), false);
		ht.remove("Ea");
		assertEquals(ht.get("FB"), null);
		assertEquals(ht.get("Ea"), null);
		assertEquals(ht.getSize(), 0);
		assertEquals(ht.isEmpty(), true);

	}
	@Test
	void testHashtable_whenKeysGenerateDifferentHashCodes() {
		
		Hashtable<String, String> ht = new Hashtable<>();
		
		ht.add("k1", "v1");
		ht.add("k2", "v2");
		
		assertEquals(ht.get("k1"), "v1");
		assertEquals(ht.get("k2"), "v2");
		assertEquals(ht.getSize(), 2);
		ht.remove("k1");
		assertEquals(ht.get("k1"), null);
		assertEquals(ht.getSize(), 1);
		assertEquals(ht.isEmpty(), false);
		ht.remove("k2");
		assertEquals(ht.get("k1"), null);
		assertEquals(ht.get("k2"), null);
		assertEquals(ht.getSize(), 0);
		assertEquals(ht.isEmpty(), true);
	}

}
