package polynomialHashMap;




import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;



public class Polynomial implements PolynomialInterface{


	private HashMap<Integer, Integer> polynomialMap = new HashMap<>();
    private int numExponent;
    private ArrayList<Integer> exponentMap = new ArrayList<>();
    
    public Polynomial() {
    	
 	}

    public Polynomial(String x) {
    	numExponent = 0;

		x = x.replaceAll("\\+", " \\+");
		x = x.replaceAll("-", " -");

		String[] arrOfStr = x.split(" ");
		for(int i = 0; i<arrOfStr.length; i++) {
			if(arrOfStr[i].contains("^") == false) {
				arrOfStr[i] = arrOfStr[i].replace("X","y1"); 
			} else {
				arrOfStr[i] = arrOfStr[i].replace("X^","y");
			}
			
			
			String[] term = arrOfStr[i].split("y");
			if(term[0].isEmpty() == true) {
				term[0] = "0";
			}
			
		
    	
			//Place number in the hash
			int coeff = Integer.parseInt(term[0]);
			int expon = Integer.parseInt(term[1]);
			
			this.exponentMap.add(expon);
			this.polynomialMap.put(expon,coeff);
			this.numExponent++;
			
		}
    
 
    }
    
   
    
    
    
    
    public Polynomial divide(Polynomial p) throws Exception{
        throw new UnsupportedOperationException("Not implemented");
    }
    public Polynomial remainder(Polynomial p) throws Exception{
        throw new UnsupportedOperationException("Not implemented");
    }

    
    
    public final String toString() { 
    	this.innerSort();
    	
     	
    	String returnString = "";
    	returnString = returnString + Integer.toString(polynomialMap.get(this.exponentMap.get(0)));
    	boolean state = true;
  
    	for(int i = 0; i < this.exponentMap.size(); i++) {
    		if(state == true) {
    			returnString = returnString + "X^" +Integer.toString(this.exponentMap.get(i));
    			state = false;
    		} else if(state == false) {
    			if(polynomialMap.get(this.exponentMap.get(i)) > 0 || polynomialMap.get(this.exponentMap.get(i)) == 0 ) {
    				returnString = returnString + "+" + polynomialMap.get(this.exponentMap.get(i)) + "X^" + Integer.toString(this.exponentMap.get(i));
    			} 
    				else if(state == false) {
        			if(polynomialMap.get(this.exponentMap.get(i)) < 0 ) {
        				returnString = returnString + polynomialMap.get(this.exponentMap.get(i)) + "X^" + Integer.toString(this.exponentMap.get(i));
        			}
    			}
    		}
    		
    	}
    	
		return returnString;
    }
    
    private void innerSort() {
		int n = this.exponentMap.size()-1;
		for(int i = 0; i<n; i++) {
			if(this.exponentMap.get(i) < this.exponentMap.get(i + 1)) {
				int temp = this.exponentMap.get(i);
				this.exponentMap.set(i, this.exponentMap.get(i+1));
				this.exponentMap.set(i+1, temp);
				i = 0;
			}
			
		}
		
	}

	//Getters
    public int getHashValue(int x) {
    	return this.polynomialMap.get(x);
    }
    
    public int getExponent() {
    	return this.numExponent;
    }
    
    public HashMap getHashMap() {
    	return this.polynomialMap;
    }
    public int getKeys(int x) {
		return this.exponentMap.get(x);
    	
    }
    public ArrayList<Integer> retKeys(){
		return this.exponentMap;
    }
    
    
    //Setters
    public void putHashV(int k, int v) {
    	this.polynomialMap.put(k,v);
	
    }
    public void setNumP(int x) {
    	this.numExponent = x;
    }
    public void setNewM(HashMap nx) {
    	this.polynomialMap = nx;
    	
    }
    public void setKeys(ArrayList<Integer> x) {
    	for(int i = 0; i < x.size();i++) {
    		this.exponentMap.add(x.get(i));
    	}
    }
    
   


    
   //Methods
	@Override
	public Polynomial add(Polynomial p) {
		Polynomial answer = new Polynomial();
		Polynomial polyA = this;
		Polynomial polyB = p;
		HashMap<Integer, Integer> Temp = new HashMap<>();
		ArrayList<Integer> keyHold = new ArrayList<>();
		int newNum = 0;
		int newCoeff = 0;
		int newExpo = 0;
		ArrayList<Integer> holdList = new ArrayList<>();
	    boolean exist = false;
	    
		if(this.numExponent > p.getExponent()) {
			polyB = this;
			polyA = p;
			
		}
		for(int i = 0; i < polyA.getExponent(); i++) {
		
			for(int j = 0; j< polyB.getExponent(); j++) {
				
				newExpo = polyA.getHashValue(polyA.getKeys(i));
				newCoeff = polyA.getKeys(i);
				if(polyA.getKeys(i) == polyB.getKeys(j)) {
					newExpo = (polyB.getKeys(j));
					newCoeff = polyA.getHashValue(polyA.getKeys(i)) + polyB.getHashValue(polyB.getKeys(j)); 
 		
					holdList.add(polyA.getKeys(i));
					holdList.add(polyA.getHashValue(polyA.getKeys(i)) + polyB.getHashValue(polyB.getKeys(j)));
					exist = true;
				}

			}
			if(exist == false) {
				holdList.add(newCoeff);
				holdList.add(newExpo);
				keyHold.add(newCoeff);
				newNum++;
			}
			exist = false;
			Temp = (HashMap<Integer, Integer>) polyB.getHashMap().clone();
		}
		for(int i = 0; i < holdList.size(); i = i+2) {
			Temp.put(holdList.get(i),holdList.get(i+1));
		}
		answer.setKeys(keyHold);
		answer.setKeys(polyB.retKeys());
		answer.setNumP(polyB.getExponent() + newNum);
		answer.setNewM(Temp);
	
		return answer;
	}

	@Override
	public Polynomial subtract(Polynomial p) {
		Polynomial answer = new Polynomial();
		Polynomial polyA = this;
		Polynomial polyB = p;
		HashMap<Integer, Integer> Temp = new HashMap<>();
		int newCoeff = 0;
		int newExpo = 0;
		ArrayList<Integer> holdK = new ArrayList<>();
		if(this.numExponent > p.getExponent()) {
			polyB = this;
			polyA = p;
		}
		
		Temp = (HashMap<Integer, Integer>) polyB.getHashMap().clone();
		for(int w = 0; w < polyB.retKeys().size(); w++) {
			holdK.add(polyB.getKeys(w));
		}
		
		
		for(int i = 0; i < polyA.getExponent(); i++) {
			newExpo = polyA.getKeys(i);
			if(Temp.containsKey(newExpo) == true) {
				int x = polyA.getHashValue(newExpo) - Temp.get(newExpo);
				if(x == 0) {
					Temp.remove(newExpo);
					for(int z = 0; z < holdK.size(); z++) {
						if(holdK.get(z) == newExpo) {
							holdK.remove(z);
						}
					}
					
				} else {
					Temp.put(newExpo,x);
				}
			} else if (Temp.containsKey(newExpo) == false) {
				int y = -1 * polyA.getHashValue(newExpo);
				Temp.put(newExpo,y);
				holdK.add(newExpo);
				newCoeff++;
			}
		}
	answer.setKeys(holdK);
	answer.setNewM(Temp);
	answer.setNumP(newCoeff);
	return answer;
	}

	@Override
	public Polynomial multiply(Polynomial p) {
		Polynomial polyA,polyB,answer = new Polynomial();
		int newCoeff = 0, newExpo,currentExpo,currentCoeff = 0;
		HashMap<Integer, Integer> Temp = new HashMap<>();
		ArrayList<Integer> holdK = new ArrayList<>();
		
		if(this.numExponent > p.getExponent()) {
			polyB = this;
			polyA = p;
		} else {
			polyB = p;
			polyA = this;
		}
		
		for(int i = 0; i < polyA.getExponent(); i++) {
			currentExpo = polyA.getKeys(i);
			currentCoeff = polyA.getHashValue(polyA.getKeys(i));
			for(int j = 0; j < polyB.getExponent(); j++) {
				newExpo = currentExpo + polyB.getKeys(j);
				if(currentCoeff == 0 || polyB.getHashValue(polyB.getKeys(j)) == 0) {
					newCoeff = currentCoeff + polyB.getHashValue(polyB.getKeys(j));
				} else {
					newCoeff = currentCoeff * polyB.getHashValue(polyB.getKeys(j));
				}
				if(Temp.containsKey(newExpo) == true) {
					int x = Temp.get(newExpo) + newCoeff;
					newCoeff = x;
					Temp.put(newExpo,newCoeff);
				} else {
					holdK.add(newExpo);
					Temp.put(newExpo,newCoeff);
				}
				
			}
		}
		
		answer.setKeys(holdK);
		answer.setNewM(Temp);
		answer.setNumP(polyA.getExponent() * polyB.getExponent());
		return answer;
	}
	
}