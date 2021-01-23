package polynomialLinkedList;
import java.io.*;

public class Polynomial implements PolynomialInterface{
	
//	Variables
	private SinglyLinkedList poly;

//	Constructor(s)
    public Polynomial(String s) {
        breakString(s);
    }
    
    public Polynomial(SinglyLinkedList p) {
    	this.poly = p;
    }
    
//    Methods

    
/*		Breaking inputed polynomial strings into terms or "tokens".
* 		Each token will hold the coefficient,power,and sign of the term.
* 		Each token will be stored into a array then later turned into nodes*/
    private void breakString(String inputString) {
    	
    	inputString = inputString.replaceAll("\\+", " \\+");
    	inputString = inputString.replaceAll("-", " -");
    	String[] tokens = inputString.split(" ");
    	String[] term;

//    	Initializing the LinkedList for this polynomial
    	
    	poly = new SinglyLinkedList();
    	int coeff,expo;
    	
    	try {
    		
//    	Looping for the polynomial
    	for(int i = 0; i < tokens.length; i++) {
    		
    		if(tokens[i].indexOf('X') == 0 || (((tokens[i].charAt(0) == '-' || tokens[i].charAt(0) == '+') && tokens[i].charAt(1) == 'X')))
        	{
        		coeff = 1;
        	} else {
        		coeff = Integer.parseInt(tokens[i].substring(0,tokens[i].indexOf("X")));
        	}
        	if(tokens[i].contains("^")) {
        		expo = Integer.parseInt(tokens[i].substring(tokens[i].indexOf("^")+1));
        	} else {
        		expo = 0;
        	}
        	
//        	Creating  a new node and adding it to the list
        	Node newNode = new Node(coeff,expo);
        	poly.insert(newNode);
        	
    		}
    	
    	} catch(Exception e) {
    		System.out.println(e);
    	}
    }
    
    public final String toString() {
    	
    	StringBuilder output = new StringBuilder();
    	Node runner = this.poly.getHead();
    	
//    	Get first element
    	output.append(runner.toString());
		runner = runner.getNext();
    	
    	while(runner != null) {
    		if(runner.toString().charAt(0) != '-') {
    			output.append("+");
    		}
    		output.append(runner.toString());
    		runner = runner.getNext();
    	}
    	
		return output.toString();
    }
    

   
//  Getters
    public SinglyLinkedList getPoly() {
    	return this.poly;
    }
    
//  Setters
    public void setPoly(SinglyLinkedList newPoly) {
    	this.poly = newPoly;
    }

    

	@Override
	public Polynomial add(Polynomial p) {
		
//		Creating new Linked list for new polynomial output
		SinglyLinkedList outputPoly = new SinglyLinkedList();
		outputPoly.clone(this.poly);
		
//		Runner for other polynomial
		Node runner2 = p.getPoly().getHead();
		
		while(runner2 != null) {
			
			Node outcome = outputPoly.searchFor(runner2.getData().getPower());
			if(outcome == null) {
				
				Node newNode = new Node (runner2.getData().getCoefficent(),runner2.getData().getPower());
				outputPoly.insert(newNode);
				
			} else {
				
				outcome.addCoeff(runner2);
				
			}
			runner2 = runner2.getNext();
		}
		
		Polynomial result = new Polynomial(outputPoly);
		return result;
	}

	@Override
	public Polynomial subtract(Polynomial p) {
		
//		Creating new Linked list for new polynomial output
		SinglyLinkedList outputPoly = new SinglyLinkedList();
		outputPoly.clone(this.poly);
		
//		Runner for other polynomial
		Node runner2 = p.getPoly().getHead();
		
		while(runner2 != null) {
			
			Node outcome = outputPoly.searchFor(runner2.getData().getPower());
			if(outcome == null) {
				
				Node newNode = new Node (-(runner2.getData().getCoefficent()),runner2.getData().getPower());
				outputPoly.insert(newNode);
				
			} else {
				
				outcome.subtractCoeff(runner2);
				
			}
			runner2 = runner2.getNext();
		}
		
		Polynomial result = new Polynomial(outputPoly);
		return result;
	}

	@Override
	public Polynomial multiply(Polynomial p) {
		
		
//		P1: Runner for this polynomial, P2: Runner for other polynomial
		Node p1 = this.poly.getHead();
		Node p2 = p.getPoly().getHead();
		
		SinglyLinkedList newList = new SinglyLinkedList();
		int newCoeff, newPow;
		while(p1 != null) {
			while(p2 != null) {
				
				newCoeff = p1.getData().getCoefficent() * p2.getData().getCoefficent();
				newPow = p1.getData().getPower() + p2.getData().getPower();
				
				
				Node spot = newList.searchFor(newPow);
				
				if(spot == null) {
					Node newNode = new Node (newCoeff,newPow);
					newList.insert(newNode);
				} else {
					spot.addCoeff(newCoeff);
				}
				
				p2 = p2.getNext();
			}
			
//			Increment outer loop, reset inner loop
			p1 = p1.getNext();
			p2 = p.getPoly().getHead();
		}
		SinglyLinkedList outputPoly = newList;
		Polynomial result = new Polynomial(newList);
		return result;
	}
	
	 public Polynomial divide(Polynomial p) throws Exception{
	        throw new UnsupportedOperationException("Not implemented");
	    }
	 
	    public Polynomial remainder(Polynomial p) throws Exception{
	        throw new UnsupportedOperationException("Not implemented");
	    }
}