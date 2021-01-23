package polynomialLinkedList;
class Term{
    private int coefficent, power;
    
    public Term(int c, int p)
    {
    	this.coefficent = c;
    	this.power = p;
    }
    
//    Setters
    void setCoefficent(int co) {
    	this.coefficent = co;
    }
    
    void setPower(int p) {
    	this.coefficent = p;
    }
    
    
//    Getters
    int getCoefficent() {
    	return this.coefficent;
    }
    
    int getPower() {
    	return this.power;
    }
    
}