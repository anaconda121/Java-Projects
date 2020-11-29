package data_sturcs.linked_list;

import java.util.LinkedList;
import java.util.Arrays;
import java.util.Iterator;

public class LinkedListImplementation {
    static LinkedList<String> start = new LinkedList<String>();

    public static void main(String[] args) {
        LinkedList<String> linky = new LinkedList<String>();
        //adding elements to linked list
        linky.add("rob");
        linky.add("tanish");
        linky.add("jose");
        linky.add("alex");

        System.out.println(linky);
        //removes first value of linked list
        linky.remove();        
        System.out.println("\n" + linky);

        linky.clear(); //clears all the values of the linked list
        System.out.println("\n" + linky);

        linky.add("jose");
        linky.add("alex");
        System.out.println("\n" + linky.getFirst()); //gets first value        
        System.out.println("\n" + linky.getLast()); //gets last value
        System.out.println("\n" + linky.get(1)); //gets value of 1 first index in linked list
        
        //searching through linked lists
        Iterator it = linky.iterator();
        int node = 0;
        while (it.hasNext()) {
            node++;
            if ((boolean) it.next().equals("jose")) {
                System.out.println("\njose is found @ node: " + node);
            }
        }

        //concatenating linked lists
        start.add("1");
        start.add("2");
        start.add("3");        
        System.out.println("\nbefore appending: " + start);

        LinkedList<String> end = new LinkedList<String>();
        for (int i = 0; i < 7; i++) {
            end.add("4");
        }
        start.addAll(end);
        System.out.println("after appending: " + start);
        
        //inserting values into linkedlists
        reset();
        System.out.println("\nbefore inserting: " + start);

        start.addAll(1, end);
        System.out.println("before inserting: " + start);

        start.offerFirst("firstelement"); //add new value to front of linkedlist
        System.out.println("after inserting new first element: " + start);

        start.set(2, "2nd element");
        start.set(3, "3rd element");
        System.out.println("inserting new elements into 2 and 3 indexes: " + start);

        start.offerLast("lastelement"); //adding new value as last element
        System.out.println("after inserting new last element: " + start);

        //cloning linkedlists
        reset();
        System.out.println("\noriginal linkedlist: " + start);
        LinkedList<String> cloneList = new LinkedList<String>();
        cloneList = (LinkedList<String>) start.clone();
        System.out.println("cloned linkedlist: " + start);

        //size method
        System.out.println("\nsize of start linked list: " + start.size());

        //converting linkedlist into an array
        reset();
        int size = start.size();
        String[] values = new String[size];
        values = start.toArray(values);
        System.out.println("\nlinked list: " + start);
        System.out.println("array: " + Arrays.toString(values));
    }

    public static void reset() {
        start.clear();
        start.add("1");
        start.add("2");
        start.add("3");
    }
}