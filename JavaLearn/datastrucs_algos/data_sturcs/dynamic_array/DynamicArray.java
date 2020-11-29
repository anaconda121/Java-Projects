package data_sturcs.dynamic_array;

import java.util.Iterator;

public class DynamicArray<T> implements Iterable<T> {
	private T [] arr;
	private int len = 0; //length that user thinks array is
	private int capacity = 0; //actual array size

	public DynamicArray() {
		this(16); //calls integer constructor which sets length of array to 16 by default
	}
	
	public DynamicArray(int capacity) {
		if (capacity < 0) {
			throw new IllegalArgumentException("illegal capcity");
		}
		this.capacity = capacity;
		arr = (T[]) new Object[capacity]; //creates an aray of data-type T with a length of capacity
	}

	public int getSize() {
		return this.len;
	}

	public boolean isEmpty() {
		return getSize() == 0;
	}

	public T getElement(int index) {
		return arr[index];
	}

	public void set(int index, T elem) {
		arr[index] = elem;
	}

	public void clear() {
		for (int i = 0; i < capacity; i++) {
			arr[i] = null;
		}
		len = 0;
	}

	//appending elements to the back of an array
	public void append(T elem) {
		if (len + 1 >= capacity) {
			//resize array because we are running short on spaces inside the array
			if (capacity == 0) {
				//opens up slot for value to slide into
				capacity = 1;
			}
			capacity *= 2;
			len++;
			T[] newArr = (T[]) new Object[capacity]; //creating new array with new space
			//populating array with old vals, only diff is that newArr has nulls padded 
			for (int i = 0; i < len; i++) {
				newArr[i] = arr[i];
			}
			//setting original array to newarray
			arr = newArr;
		}
		arr[len++] = elem; //appending element to back of the array
	}

	//adding elements in at specific spots in the array
	public void add(int index, T elem) {
		
		if (capacity == 0) {
			capacity = 1;
		} else {
			capacity = (capacity * 2);
		}
		T[] newArr = (T[]) new Object[capacity];
		for (int i = 0; i < len; i++) {
			if (i < index) {
				newArr[i] = arr[i];
			} else if (i == index) {
				newArr[i] = elem;
			} else {
				newArr[i + 1] = arr[i];
			}
		}
		arr = newArr;
		
	}

	//removes the last element of the array
	public void delete() {
		capacity = len - 1;
		len -= 1;
		T[] newArr = (T[]) new Object[capacity];
		for (int i = 0; i < len; i++) {
			newArr[i] = arr[i];
		} 
		arr = newArr;
	}

	//removes elements based off of their values in the array
	public boolean removeValue(T elem) {
		for (int i = 0; i < len; i++) {
			if (arr[i] == elem) {
				remove(i);
				return true;
			}
		}
		return false;
	}

	//removes elements at specific spots in an array
	public void remove(int index) {
		if (index >= len || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		//int length = getSize();
		T[] newArr = (T[]) new Object[capacity];
		for (int i = 0; i < len; i++) {
			if (i < index) {
				newArr[i] = arr[i];
			} else {
				newArr[i] = arr[i+1];
			}
		}
		arr = newArr;
	}

	//returns the index of a value
	public int indexOf(T elem) {
		for (int i = 0; i < len; i++) {
			if (arr[i] == elem) {
				return i;
			}
		}
		return -1;
	}

	//checks to see if elements is inside of the array
	public boolean contains(T elem) {
		return indexOf(elem) != -1; //returns true if not equal to -1, false if equals
	}

	@Override
	public java.util.Iterator<T> iterator() {
		return new java.util.Iterator<T>() {
		int index = 0;

			@Override
			public boolean hasNext() {
				return index < len;
			}

			@Override
			public T next() {
				return arr[index++];
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}

	@Override
	public String toString() {
		if (len == 0) {
			return "[]";
		} else {
			StringBuilder sb = new StringBuilder(len).append("[");
			for (int i = 0; i < len - 1; i++) {
				if (arr[i] == null) {
					continue;
				} else {
					sb.append(arr[i] + ", ");
				}
			}
			return sb.append(arr[len - 1] + "]").toString();
		}
	}

	public static void main(String[] args) {
		DynamicArray<Integer> dr = new DynamicArray<Integer>(50);
		dr.append(5);
		dr.append(6);
		dr.append(7);
		dr.append(8);
		dr.append(9);
		dr.remove(1);
		System.out.println(dr.getSize());
		dr.add(4, 10);
		//dr.removeValue(7);
		System.out.println(dr);
	}
}