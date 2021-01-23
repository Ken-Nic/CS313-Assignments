package polynomialLinkedList;
class Node<T>{
	private Term data;
	private Node<T>nextNode;
	
	public Node(){
		
	}
	
	public Node(int coeff,int expo) {
		Term newData = new Term(coeff,expo);
		this.data = newData;
	}
	
	public Node(Term newData) {
		this.data = newData;
	}

	//	Setters
	void setData(Term d) 
	{
		this.data = d;
	}
	
	public void setNext(Node<T> spot)
	{
		this.nextNode = spot;
	}
	
//	Getters
	Term getData() 
	{
		return this.data;
	}
	
	Node<T> getNext()
	{
		return this.nextNode;
	}	
	
//	Methods
	@Override
	public String toString() {
		StringBuilder output = new StringBuilder();
		int coeff = this.data.getCoefficent();
		int pow = this.data.getPower();
		
		if(coeff != 1) {
			output.append(coeff);
		}
		
		output.append('X');
		
		if(pow != 0) {
			output.append("^" + pow);
		}
		
		return output.toString();
	}
	
//	Adds the Coefficient of another node to this node
	public void addCoeff(Node<T> other) {
		
		this.getData().setCoefficent(this.getData().getCoefficent() + other.getData().getCoefficent());
		
	}
	
//	Overloading the addition method
	public void addCoeff(int x) {
		
		this.getData().setCoefficent(this.getData().getCoefficent() + x);
		
	}
	
//	Subtracts the Coefficient of another node from this node
	public void subtractCoeff(Node<T> other) {
		
		this.getData().setCoefficent(this.getData().getCoefficent() - other.getData().getCoefficent());
		
	}
}