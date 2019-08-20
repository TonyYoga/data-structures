package lookuptable;

import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Example of Lookup Table with String key and parameterized V value
 * This is probable replacement of TreeMap<String, V>
 * - giving sorted order of String keys
 * - giving 0(1) get access
 * - but very greedy by memory
 */

public class LookupTable<V> {
	private static final int MAX_STRING_KEY_LENGHT = 15;
	private static final char MAX_LETTER_INDEX = 127;

	private static class Node<V> {
		V value = null;
		Node<V>[] subnodes = null;
		
		@SuppressWarnings("unchecked")
		Node<V> getSubnode(int id){
			if (subnodes == null) {
				subnodes = new Node[MAX_LETTER_INDEX+1];
				subnodes[id] = new Node<>();
			}
			return subnodes[id];
		}
	}
	
	private Node<V> head = new Node<V>();
	private int size =0 ;
	
	public boolean put(String key, V value) {
		isKeyCorrect(key);
		Node<V> cur = head;
		for (Character let: key.toCharArray()) {
			cur = cur.getSubnode((int)let);
		}
		
		if (cur.value != null) {
			return false;
		}
		
		cur.value = value;
		size++;
		return true;
	}
	
	public V get(String key) {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		isKeyCorrect(key);
		Node<V> cur = head;
		for (char let: key.toCharArray()) {
			if (cur.subnodes[(int)let] == null) {
				throw new NoSuchElementException();
			}
			cur = cur.subnodes[(int)let];
		}
		if (cur.value == null) {
			throw new NoSuchElementException();
		}
		return cur.value;
	}

	private boolean isKeyCorrect(String key) {
		Objects.requireNonNull(key);
		if (key.length() > MAX_STRING_KEY_LENGHT) {
			throw new IllegalArgumentException("Key too long");
		}
		for (char let: key.toCharArray()) {
			if ((int)let > MAX_LETTER_INDEX) {
				throw new IllegalArgumentException("Illegal letter");
			}
		}
		return true;
	}

}
