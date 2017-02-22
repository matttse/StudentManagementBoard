/**
 * 
 */
package model;


/**
 * @author Matthew Tse
 *
 */
public class Course<E> extends CourseDetails<E> {
	
	
	/** Nested class
	 * GNode is a generic class representing a node in a list
	 * E is generic type parameter of data
	 * Has both previous and next pointers
	**/
   	private static class GNode<E> {
		private E data;
		private GNode<E> previous;
        private GNode<E> next;
		
        // constructor
        public GNode (E e) {
			data = e;
			previous = null;
            next = null;
		}
		
		public E getData() { return data; }
		public GNode<E> getPrevious() { return previous; }
        public GNode<E> getNext() { return next; }
		public void setData(E e) { data = e; }
		public void setPrevious(GNode<E> p) { previous = (GNode<E>) p; }
        public void setNext(GNode<E> p) { next = (GNode<E>) p; }
	}
   
   private GNode<E> letterGrade;
   private GNode<E> head;
   private GNode<E> tail;
   private int size;       // number of nodes in a list
   private GNode<E> prev;
   
   /** no-arg constructor creates an empty list
   **/ 

	public Course() {
	      head = null;
	      tail = null;
	      size = 0;
	}
	
	/** insert a new node with data e after node n
	 * n is not null
	 * if a node with e already exists, return 1
	 * if not, create and add a new node with e after node n, and return 0
	 * increment the size
	**/
	public int addAfter(GNode<E> n, E e){
		
		if (n == null)
	         throw new IllegalArgumentException
	         ("The node n cannot be null");
		
		if (findNode(head, e) != null) return 1;
		if (n == tail){
			addToTail(e);
		}
		else{
			GNode<E> temp = new GNode<E>(e);
			GNode<E> tempNext = (GNode<E>) n.getNext();
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
	public int addBefore(GNode<E> n, E e){
		
		if (n == null)
	         throw new IllegalArgumentException
	         ("The node n cannot be null");
		
		if (findNode(head, e) != null) return 1;		
		if (n == head) addToHead(e);
		else{
			GNode<E> temp = new GNode<E>(e);
			GNode<E> tempPrevious = (GNode<E>) n.getPrevious();
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
			GNode<E> temp = new GNode<E>(e);
			if (head == null){
				head = temp;
				tail = temp;
			}
			else{
				if (findNode(head, e) == null) {
//					letterGrade.setData(e);
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
			
			GNode<E> temp = new GNode<E>(e);
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
		public int addToPos(E e, int pos){
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
				GNode<E> temp = new GNode<E>(e);
				//at the beginning
				if (pos == 0) {
					addToHead(e);
				//at the end
				} else if (pos == size || pos > size) {
					addToTail(e);
				} else {
					GNode<E> current = head;
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
		public GNode<E> deleteNode(E e){
			//new node with selected
			GNode<E> temp = findNode(head, e);		
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
					GNode<E> tempLt = temp.getPrevious();
					GNode<E> tempRt = temp.getNext();
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
		public GNode<E> replacePos(E e, int pos){
			//temp node
			GNode<E> temp = findNode(head, e);
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
					GNode<E> newNode = new GNode<E>(e);
					GNode<E> current = head;
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
		public void exchange(GNode<E> n1, GNode<E> n2){	
			//check head and tail
	 		if (n1 == head && n2 == tail) {
	 			GNode<E> temp = findNode(head, n1.getData());
	 			head = n2;
	 			temp.setNext(n1.getNext());
	 			temp.setPrevious(null);
	 			tail = n1;
	 		//check tail and head	
			} else if (n1 == tail && n2 == head) {
	 			GNode<E> temp = findNode(head, n2.getData());
	 			head = n1;
	 			temp.setNext(n2.getNext());
	 			temp.setPrevious(null);
	 			tail = n2;
			//update only head
			} else if (n1 == head && n2 != tail) {
	 			GNode<E> temp = findNode(head, n1.getData());
	 			head = n2;
	 			temp.setNext(n1.getNext());
	 			temp.setPrevious(null);
	 		//update only tail	
			} else if (n1 != head && n2 == tail) {
				GNode<E> temp = findNode(head, n2.getData());
				tail = n1;
				temp.setPrevious(n2.getPrevious());
			}
	 		//Adjacent nodes
	 		if (n1 == n2.getPrevious() || n2 == n1.getPrevious()) {
		        //Order is relevant
				GNode<E> first;
				GNode<E> second;
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
					GNode<E> prevN1 = n1.getPrevious();
					GNode<E> nextN1 = n1.getNext();
					GNode<E> prevN2 = n2.getPrevious();
					GNode<E> nextN2 = n2.getNext();
				  
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
	
	/**
	 * find a node with element e
	 * start the search beginning at node p
	 * if node with e does not exist, return null
	 * if node with e exists, return the pointer to the node
	**/
	public GNode<E> findNode(GNode<E> p, E e){
		GNode<E> current = p;
		while (current != null && current.data != e)
			current = (GNode<E>) current.getNext();
		return current; 
	}
	
	/*
	 * @Method size
	 * @returns
	 * 		returns the number of elements in collection	
	 */
	public int size() {
		//initialize counter
		int cntr = 0;
		GNode<E> current = head;
		//check for exception before iterate over current
		if (current != null) {
			//loop to the requested index or the last element in the list
			for (int cnt = 0; cnt < size && current.getNext() != null; cnt++) {
				cntr++;				
			}
		}			
		
		return cntr;
		
	}
	
	/*
	 * @Method isEmpty
	 * @returns
	 * 		returns boolean
	 * 	
	 */
	public boolean isEmpty() {
		//initialize return value; default to empty
		boolean stat = true;
		//check if size has been incremented
		if (this.size() != 0) {
			stat = false;
		}//end if
		
		return stat;
	}

	public void printCourses(){
		System.out.print("Number of Courses = " + size + "\nCourse List is: ");
		if (head != null){
			GNode<E> current = head;
		    while (current != null){
			   System.out.print(current.data + " ");
			   current = (GNode<E>) current.getNext();
		   }
		}
		else {
			System.out.println("The Course List is empty");
		}
		System.out.println();
	}
	
	public void printCourseDetails(){
		System.out.print("Number of Courses = " + size + "\nCourse List is:\n");
		if (head != null){
			GNode<E> current = head;
//			getCredits();
			Course<E> course = new Course<E>();
			
			
		    while (current != null){
			   System.out.print(current.data + ":\t");
			   System.out.println(course.getCredits() + " ");
			   current = (GNode<E>) current.getNext();
		   }
		}
		else {
			System.out.println("The Course List is empty");
		}
		System.out.println();
	}
	
	public <E> E convertInstanceOfObject(Object o, Class<E> c) {
		try {
			return c.cast(o);
		} catch (ClassCastException e) {
			return null;
		}
	}

	/**
	 * @return the letterGrade
	 */
	protected GNode<E> getLetterGrade() {
		return letterGrade;
	}

	/**
	 * @param letterGrade the letterGrade to set
	 */
	protected void setLetterGrade(GNode<E> letterGrade) {
		this.letterGrade = letterGrade;
	}


	
}
