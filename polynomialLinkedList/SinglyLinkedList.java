package polynomialLinkedList;
class SinglyLinkedList<T> implements LinkedListInterface<T>{
	
//	Stored values
	private Node<T> head,tail = null;
	private int size;
	
//	Constructors
	public SinglyLinkedList() {
		
	}

//	Interface methods
	@Override
	public boolean isEmpty() {
		
		return size == 0;
		
	}

	@Override
	public int size() {

		return this.size;
		
	}
	
//	Methods

	@Override
	public void insert(Node<T> spot) {
		if(this.isEmpty()) {
			this.head = spot;
			this.tail = spot;
			size++;
		} else {
//		Using a "Runner" node called currentNode as a way check through the LinkedList and find a spot.
		Node<T> currentNode = this.head;
		
//		First Check if the new node is greater then the current list head node
		if(spot.getData().getPower() > currentNode.getData().getPower()) {
			addFirst(spot);
		} else {
			
//			While the runner is not at the end of the list
			while(currentNode.getNext() != null) {
//				Check if the new node belongs in between the current and the next node
				if(spot.getData().getPower() > currentNode.getNext().getData().getPower()) {
					
/*			Use the addBetween function then break from the loop, this is due to powers only appearing once in the input string
 * 			So once the first spot is found, it is the only spot for the power to be in*/
					addBetween(currentNode,spot);
					break;
				}
				
//				Iterate to the next node if a spot wasnt found yet
				currentNode = currentNode.getNext();
			}
			
//			If the runner made it to the end of the list then add the new node to the back of the list
			if(currentNode.getNext() == null) {
				addLast(spot);
			}
		}
	}
}
	
//	Additional methods
	
//	Adding node to front of list
	void addFirst(Node<T> newNode) 
	{
		newNode.setNext(this.head);
		this.head = newNode;
		this.size++;
		
	}
	
//	Adding node to the back of the list
	void addLast(Node<T> newNode) 
	{
		this.tail.setNext(newNode);
		this.tail = newNode;
		this.size++;
	}
	
//	Adding node in between two other nodes
	void addBetween(Node<T> target,Node<T> newNode) 
	{
		newNode.setNext(target.getNext());
		target.setNext(newNode);
		this.size++;
	}
	
//	Searching for a specified power
	Node<T> searchFor(int pow){
		Node runner = this.head;
		while(runner != null) {
			if(runner.getData().getPower() == pow) {
				return runner;
			}
			runner = runner.getNext();
		}
		return null;
	}
	
//	Getter
	Node<T> getHead(){
		return this.head;
	}

	public void clone(SinglyLinkedList poly) {
		Node runner = poly.getHead();
		
		while(runner != null) {
			Term newTerm = new Term(runner.getData().getCoefficent(),runner.getData().getPower());
			Node newNode = new Node(newTerm);
			this.insert(newNode);
			runner = runner.getNext();
		}
		
	}
}