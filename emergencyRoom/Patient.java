package emergencyRoom;


import java.util.ArrayList;

public class Patient implements Comparable {
	//private char ;
	private String name,complaint,doctor = "N/A",gender,bloodPressureCondition= "normal",respirationCondition = "normal",temperatureCondition="no",oxygenCount,alertLevel,heartRateCondition= "normal",pain;
	private int age,triage,heartRate,respirationRate;
	private long waitTime;
	private double temp,oxygen;
	private boolean first = true;
	private int[] bloodPressure = new int[2];
	ArrayList<String> meds = new ArrayList<>();
	
	
	public Patient() {
		
	}
	
	public Patient(String x) {
		this.name = x;
	}
	

	//Setters
	public void setName(String x) {
		this.name = x;
	}
	public void setAge (String x) {
		this.age = Integer.parseInt(x);
	}
	
	public void setGender(String a) {
		this.gender = a;
		
	}
	
	public void setComplaint (String a) {
		this.complaint = a;
	}
	
	public void setAlert(String a) {
		this.alertLevel = a;
		
	}
	
	public void setheartR(String a) {
		this.heartRate = Integer.parseInt(a);
		this.checkHR();
	}
	
	public void setbloodP(String a) {

		String[] arrOfStr = a.split("/");
		for(int i = 0; i < 2; i++) {
			this.bloodPressure[i] = Integer.parseInt(arrOfStr[i]);
		}
		this.checkBloodPressure();
		
	}
	
	public void setRespR(String a) {
		this.respirationRate = Integer.parseInt(a);
		this.checkrrC();
	}
	
	public void setTemp(String a) {
		this.temp = Double.parseDouble(a);
		this.checkTemp();
	}
	
	public void setOxygen(String a) {
		a = a.replaceAll("%","");
		this.oxygen = Double.parseDouble(a);
		this.checkOxygen();
	}
	public void setPain(String a) {
		this.pain = a;
	}
	
	public void setWaitT(long a) {
		this.waitTime = a;
	}
	
	public void setMeds(String a) {
		this.meds.add(a);
	}
	
	public void setDoctor(boolean a, boolean b, boolean c) {
	if (a == true)
	{
		addDoctor("cardiologist");
	}
	if (b == true)
	{
		addDoctor("oncologist");
	}
	if (c == true)
	{
		addDoctor("neurologist");
	}
		
	}
	
	void addDoctor(String x) {
	if(first == true) {
			this.doctor = x;
			this.first = false;
		} else {
			this.doctor = this.doctor +","+ x;	
		}
	}
	
	
	//setters
	
	//getters
	
	private int getTriage() {
		
		return this.triage;
	}

	@Override
	public int compareTo(Object temp) {
		Patient other = (Patient) temp;
		if(getTriage() ==  other.getTriage()) {
			return 0;
		} else if (getTriage() > other.getTriage()) {
			return 1;
		} else {
			return -1;
		}
	}
	

	void checkBloodPressure() {
		if(this.bloodPressure[0] > 140 || this.bloodPressure[1] > 90) {
			bloodPressureCondition = "Hypertension";
			return;
		} else if(this.bloodPressure[0] < 90|| this.bloodPressure[1] < 6) {
			bloodPressureCondition = "Hypotension";
			return;
		}	
		return;
	}
	
	void checkrrC() {
		if(this.respirationRate < 16) {
			respirationCondition = "Bradypnea";
			return;
		}
			 else if (this.respirationRate > 20) {
				 respirationCondition = "Tachypnea";
				 return; 
		}
		return;
	}
	
	void checkTemp() {
		if(this.temp > 99.0) {
			temperatureCondition = "Yes";
			return;
		}
		return;
	}
	void checkOxygen() {
		if(oxygen < 95.0 || oxygen < 90) {
			oxygenCount = "Low";
		} else if(oxygen >= 95.0) { 
			oxygenCount = "Normal";
		}
		return;
	}
	
	void checkHR() {
		if (this.heartRate > 100 ) {
			heartRateCondition = "Tachycardia";
			return;
		} else if (this.heartRate < 60) {
			heartRateCondition = "Bradycardia"; 
			return;
		} else if (this.heartRate > 60 && this.heartRate < 100) {
			heartRateCondition = "Normal";
		}
		return;
	}
	
	
	//Internal Functions
	public void checkTriaga () {
		if(levelOne() == true) {
			return;
		}
		if(leveltwo() == true) {
			return;
		}
		if(levelthree() == true) {
			return;
		}
	}
	
	boolean levelOne() {
		if((this.heartRate > 150|| this.heartRate < 30) || this.temp > 105 || this.oxygen < 90.0 || this.respirationRate < 6 || this.bloodPressureCondition == "Hypotensive" || this.alertLevel == "U") {
		this.triage = 1;
		return true;
	}
		return false;
	}
	
	boolean leveltwo() {
		if((this.oxygen > 90.0 && this.oxygen < 95.0) || this.respirationCondition == "Tachypnea" || this.respirationCondition == "Bradypnea" || this.heartRateCondition == "Bradycardia" || this.heartRateCondition == "Tachycardia" || this.bloodPressureCondition == "Hypertensive" || this.alertLevel == "P"|| this.alertLevel == "V") {
			this.triage = 2;
			return true;
		}
		return false;	
	}
	boolean levelthree() {
		if(this.alertLevel == "A" || (this.heartRate <50 && this.heartRate < 110) || (this.temp < 98.7) || (this.oxygen >= 95 && this.oxygen <= 100) || (this.bloodPressure[0] < 140 && this.bloodPressure[0] > 90) && (this.bloodPressure[1] < 90 && this.bloodPressure[1] > 60) || (this.respirationRate > 16 && this.respirationRate < 20)) {
			this.triage = 3;
			return true;
		}
		return false;
	}
	
	public String toString() {
		String x;
		x = this.name+ "\r\n" +this.age+ ","+this.gender+ ","+this.complaint+"\r\n"+this.triage+"\r\n"+this.doctor + "\r\n" +this.waitTime+"ms"+"\r\n"+this.heartRate+" "+this.heartRateCondition+ "\r\n"+this.bloodPressure[0]+"/"+this.bloodPressure[1]+ " "+this.bloodPressureCondition+"\r\n"+this.respirationRate+ " " + this.respirationCondition + "\r\n" + this.temp +" "+this.temperatureCondition+ "\r\n" + this.oxygen +" " + this.oxygenCount + "\r\n";
		int s = 0;
		for(String m : meds){
			if(s == 0 ) {
				x = x + m;
				s = 1;
			} else {
				 x = x + "," + m;
			}
			}
		return x;
		
	}

	


	

}
