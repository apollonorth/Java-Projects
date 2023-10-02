/*
Apollo Schaefer
Description: GenericOrderedCollection is a generic class for storing ordered elements, upper bound by Comparable<T>
I have implemented commonly found methods in Collection classes. 
Requires: Driver class with a main method to construct an instance of this class.
*/
public class OrderedCollection<T extends Comparable<T>> {
    int size;
    T[] arr;
    public OrderedCollection() { 
        arr = (T[]) new Comparable[10];
        size = 10;
        //populate array 1-10
        for(int i = 0; i < 10; i++)
            arr[i] = (T) Integer.valueOf(i);  
    }
        
    public boolean isEmpty() {
		for(int i = 0; i < 10; i++) {
			if (arr[i] != null)
				return false;
		}
		return true;
	}
	//set every object in array to null
	public void makeEmpty() {
		for(int i = 0; i < 10; i++) 
			arr[i] = null;
        size = 0;
	}
	//inserts object into index of array that is given as argument
	public void insert(T obj, int index) {
		if(index >= 0 && index < 10)
			arr[index] = obj;
        size ++;
	}
	//search list for argument and make it null at that index
	public void remove(T obj) {
		for (int i = 0; i < 10; i++) {
			if(obj.equals(arr[i]))
				arr[i] = null;
		}
        size--;
	}

    //search list for compareTo return values less than 0 and updating min
    public T findMin() {
        if(size == 0)
            return null;
        T min = arr[0];
        for (int i = 0; i < 10; i++) {
            if(arr[i].compareTo(min) < 0)
                min = arr[i];
        }
        return min;
    }

    //search list for compareTo values more than 0, then update max
    public T findMax() {
        if(size == 0)
            return null;
        T max = arr[0];
        for (int i = 0; i < 10; i++) {
            if(arr[i].compareTo(max) > 0)
                max = arr[i];
        }
        return max;
    }

}
