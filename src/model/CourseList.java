/**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.List;

import model.CourseList.Course;


/**
 * @author Matthew Tse
 *
 */
public class CourseList<E> implements Cloneable{
	
	/** Nested class
	 * Course is a generic class representing a node in a list
	 * E is generic type parameter of data
	 * Has both previous and next pointers
	**/
   	public static class Course<E> extends CourseDetails {
   		public E data;
   		public Course<E> previous;
   		public Course<E> next;
		
        // constructor
        public Course (E e) {
			data = e;
			previous = null;
            next = null;
		}
		
		public E getData() { return data; }
		public Course<E> getPrevious() { return previous; }
        public Course<E> getNext() { return next; }
		public void setData(E e) { data = e; }
		public void setPrevious(Course<E> p) { previous = (Course<E>) p; }
        public void setNext(Course<E> p) { next = (Course<E>) p; }
	}
   
   	
   	public Course<E> head;
   	public Course<E> tail;
   	public int size;       // number of nodes in a list
   	public Course<E> prev;
   
   /** no-arg constructor creates an empty list
   **/ 
  
   public CourseList( )
   {
      head = null;
      tail = null;
      size = 0;
   }	
	
	public CourseList(CourseList courses) {
		
		head = courses.head;
		tail = courses.tail;
		size = courses.size;
	}

	/** insert a new node with data e after node n
	 * n is not null
	 * if a node with e already exists, return 1
	 * if not, create and add a new node with e after node n, and return 0
	 * increment the size
	**/
	public int addAfter(Course<E> n, E e){
		
		if (n == null)
	         throw new IllegalArgumentException
	         ("The node n cannot be null");
		
		if (findNode(head, e) != null) return 1;
		if (n == tail){
			addToTail(e);
		}
		else{
			Course<E> temp = new Course<E>(e);
			Course<E> tempNext = (Course<E>) n.getNext();
			temp.setNext(tempNext);
			tempNext.setPrevious(temp);
			temp.setPrevious(n);
			n.setNext(temp);
			size++;
		}		
		return 0;
	}
	
	/** insert a new node with data e before node n
	 * n is not null
	 * if a node with e already exists, return 1
	 * if not, create and add a new node with e before node n, and return 0
	 * increment the size
	**/
	public int addBefore(Course<E> n, E e){
		
		if (n == null)
	         throw new IllegalArgumentException
	         ("The node n cannot be null");
		
		if (findNode(head, e) != null) return 1;		
		if (n == head) addToHead(e);
		else{
			Course<E> temp = new Course<E>(e);
			Course<E> tempPrevious = (Course<E>) n.getPrevious();
			temp.setNext(n);
			n.setPrevious(temp);
			tempPrevious.setNext(temp);		
			temp.setPrevious(tempPrevious);		
			size++;
		}		
		return 0;
	}
	
	   /** add a new node with data e to the head
	    * if a node with e already exists, return 1
	    * if not, create and add a new node with e to the head (this new node is the head now), and return 0
	    * increment the size
	   **/
	   public int addToHead(E e){
			Course<E> temp = new Course<E>(e);
			if (head == null){
				head = temp;
				tail = temp;
			}
			else{
				if (findNode(head, e) == null) {
					temp.setNext(head);
					head.setPrevious(temp);
					head = temp;
				}
				else return 1;
			}
			size++;
			return 0;
		}
	   
		/** add a new node with data e to the tail
		 * if a node with e already exists, return 1
		 * if not, creatre and add a new node with e to the tail (this new node is the tail now), and return 0
		 * increment the size
		**/
		public int addToTail(E e){
			
			Course<E> temp = new Course<E>(e);
			if (head == null){
				head = temp;
				tail = temp;
			}
			else{
				if (findNode(head, e) == null){
					temp.setPrevious(tail);
					tail.setNext(temp);
					tail = temp;
					
				}
				else return 1;
			}
			size++;
			return 0;
		}
		
		/** add a new node with e at the specified position
		 * pos specifies where new node is added
		 * pos of the first element in a list is 0
		 * pos >= 0
		 * if a node with e already exists, return 1
		 * if not, create and add a new node with e at position pos and return 0
		 * increment the size
		 * @resource
		 * http://crunchify.com/how-to-implement-a-linkedlist-class-from-scratch-in-java/
		 * https://www.java2novice.com/data-structures-in-java/linked-list/doubly-linked-list/
		 */
		public int addPos(E e, int pos){
			//return val default to 0
			int stat = 0;
			//only if already exists
			if (findNode(head, e) != null) {
				stat = 1;
				return stat;
			}
			//do nothing if position is less than 0
			if (pos >= 0) {
				//temp node 
				Course<E> temp = new Course<E>(e);
				//at the beginning
				if (pos == 0) {
					addToHead(e);
				//at the end
				} else if (pos == size || pos > size) {
					addToTail(e);
				} else {
					Course<E> current = head;
					//check for exception before iterate over current
					if (current != null) {
						//loop to the requested index or the last element in the list
						for (int cnt = 0; cnt < pos - 1 && current.getNext() != null; cnt++) {
							current = current.getNext();						
						}
					}			 
					//set the new node's next-node reference to this node's next-node reference
					temp.setNext(current.getNext());
					current.setPrevious(temp);			 
					//set this node's next-node reference to the new node
					current.setNext(temp);			 
					//update size
					size++;
				}
			}		    
		    return stat;
		}
		
		/** delete the node with data e
		 * if a node with e does not exist, return null
		 * if exists, delete the node and return the pointer to the deleted node
		 * decrement the size
		**/
		public Course<E> deleteNode(E e){
			//new node with selected
			Course<E> temp = findNode(head, e);		
			//if selected is not there
			if (temp == null) {
				return null;
			//set the data at link
			} else {
				//check head
				if (temp.previous == null) {
					head = head.getNext();
				//check tail
				} else if (temp.next == null) {					
					tail = tail.getPrevious();					
				//set new links
				} else {
					Course<E> tempLt = temp.getPrevious();
					Course<E> tempRt = temp.getNext();
					tempLt.next = tempRt;					
				}
				//update size
				size--;
			}
			return temp;
		}
		
		/** replace the node at the specified position with a new node with e
		 * pos specifies the position of the node to be replaced
		 * pos of the first element in a list is 0
		 * pos >= 0
		 * if a node with e already exists, return null
		 * if not, create a new node with e and let it replace the node at position pos
		 * return the pointer to the replaced node
		 */
		public Course<E> replacePos(E e, int pos){
			//temp node
			Course<E> temp = findNode(head, e);
			if (temp == null && pos < size) {
				//check head
				if (pos == 0) {
					head = head.getNext();
					addToHead(e);
				//check tail and adjust for index
				} else if (pos == (size - 1)) {
					tail = tail.getPrevious();
					addToTail(e);
				} else {
					//add to position
					Course<E> newNode = new Course<E>(e);
					Course<E> current = head;
					//check for exception before iterate over current
					if (current != null) {
						//loop to the requested index or the last element in the list
						for (int cnt = 0; cnt < pos && current.getNext() != null; cnt++) {
							current = current.getNext();						
						}
					}			 
					//set the new node's next-node reference to this node's next-node reference
					newNode.setNext(current.getNext().getNext());
					current.setPrevious(newNode);			 
					//set this node's next-node reference to the new node
					current.setNext(newNode);					
				}
			}			
			
			return temp;
			
		}	
		
		/** exchange two nodes n1 and n2
		 * n1 is not null
		 * n2 is not null
		 * exchange node n1 and node n2 (do not just exchange the data).
		 * @reference http://stackoverflow.com/questions/37637894/java-generic-doubly-linked-list-swap
		 * http://stackoverflow.com/questions/27922497/java-double-linked-list-swap-node
		 * 
		**/
		public void exchange(Course<E> n1, Course<E> n2){	
			//check head and tail
	 		if (n1 == head && n2 == tail) {
	 			Course<E> temp = findNode(head, n1.getData());
	 			head = n2;
	 			temp.setNext(n1.getNext());
	 			temp.setPrevious(null);
	 			tail = n1;
	 		//check tail and head	
			} else if (n1 == tail && n2 == head) {
	 			Course<E> temp = findNode(head, n2.getData());
	 			head = n1;
	 			temp.setNext(n2.getNext());
	 			temp.setPrevious(null);
	 			tail = n2;
			//update only head
			} else if (n1 == head && n2 != tail) {
	 			Course<E> temp = findNode(head, n1.getData());
	 			head = n2;
	 			temp.setNext(n1.getNext());
	 			temp.setPrevious(null);
	 		//update only tail	
			} else if (n1 != head && n2 == tail) {
				Course<E> temp = findNode(head, n2.getData());
				tail = n1;
				temp.setPrevious(n2.getPrevious());
			}
	 		//Adjacent nodes
	 		if (n1 == n2.getPrevious() || n2 == n1.getPrevious()) {
		        //Order is relevant
				Course<E> first;
				Course<E> second;
				if (n1 == n2.getPrevious()) {
					first = n1;
					second = n2;
				} else {
					first = n2;
					second = n1;
				}
		  
				first.setNext(second.getNext());
				second.setPrevious(first.getPrevious());
		  
				if (first.getNext() != null)
					first.getNext().setPrevious(first);
		  
				if (second.getPrevious() != null)
					second.getPrevious().setNext(second);
		  
					second.setNext(first);
					first.setPrevious(second);
				//Non adjacent
				} else {				
					Course<E> prevN1 = n1.getPrevious();
					Course<E> nextN1 = n1.getNext();
					Course<E> prevN2 = n2.getPrevious();
					Course<E> nextN2 = n2.getNext();
				  
					n1.setPrevious(prevN2);
					n1.setNext(nextN2);
					n2.setPrevious(prevN1);
					n2.setNext(nextN1);
				  
					if (prevN1 != null)
						prevN1.setNext(n2);
					if (nextN1 != null)
						nextN1.setPrevious(n2);
					if (prevN2 != null)
						prevN2.setNext(n1);
					if (nextN2 != null)
						nextN2.setPrevious(n1);
				}
		}

	/** delete the node which is located after the node with data e
	 * if the node with e is tail, return null
	 * if a node with e does not exist, return null
	 * if a node with e exists, delete the node after that node and return the pointer to the deleted node
	 * decrement the size
	**/
	public Course<E> deleteAfter(E e){
		Course<E> temp = findNode(head, e);
		if (temp == tail || temp == null) return null;
		return (deleteNode((E)temp.getNext().data));
	}

	/** delete the node which is located before the node with data e
	 * if the node with e is head, return null
	 * if a node with e does not exist, return null
	 * if a node with e exists, delete the node before that node and return the pointer to the deleted node
	 * decrement the size
	**/
	public Course<E> deleteBefore(E e){
		Course<E> temp = findNode(head, e);
		if (temp == head || temp == null) return null;
		return (deleteNode((E)temp.getPrevious().data));
	}
	
	/**
	 * find a node with element e
	 * start the search beginning at node p
	 * if node with e does not exist, return null
	 * if node with e exists, return the pointer to the node
	**/
	public Course<E> findNode(Course<E> p, E e){
		Course<E> current = p;
		while (current != null && current.data != e)
			current = (Course<E>) current.getNext();
		return current; 
	}

	public void printCourseRecord(){
		//list to store integers
		List<Integer> cnt = new ArrayList<Integer>();
		//instantiate with 10 the list of grades
		//TODO make list size dynamic
		Integer[] cntList = new Integer[10];
		//print size
		System.out.print("Number of nodes = " + size + "\n, List is: ");
		if (head != null){
			//get current
			Course<E> current = head;			
		    while (current != null){
		    	//add each grade to list
		    	cnt.add(current.getNumberGrade());
		    	//print title
		    	System.out.print(current.title + " ");
		    	//reset to next 
		    	current = (Course<E>) current.getNext();
		    }
		    //set values in grade list
		    for (int i = 0; i < cnt.size(); i++) {
		    	cntList[i] = cnt.get(i);
		    }
		    //trimtosize
		    if (cnt.size() < cntList.length) {
		    	Integer[] newCntList = new Integer[cnt.size()];
		    	System.arraycopy(cntList, 0, newCntList, 0, cnt.size());
		    	cntList = newCntList;
		    }
		    //reset rec
		    Course<E> newRec = head;
		    //calc avg
		    newRec.setGradePointAverage(newRec.calcAverage(cntList));
		    //print gpa
		    System.out.println("\nGPA: " + newRec.getGradePointAverage());

		}
		else {
			System.out.println("The list is empty");
		}
		System.out.println();
	}
	

}

