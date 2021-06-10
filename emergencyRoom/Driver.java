package emergencyRoom;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Driver {
public static void main(String[] args) {
		
		BufferedReader patient = null;
		BufferedReader cardiac = null;
		BufferedReader cancer = null;
		BufferedReader neuro = null;
		
		String line;

		
		long start= 0;
		ArrayList<String> cardiacList = new ArrayList<>();
		ArrayList<String> cancerList = new ArrayList<>();
		ArrayList<String> neuroList = new ArrayList<>();

		
		PriorityQueue<Patient> patientQueue = new PriorityQueue<Patient>(16); 
		
		
		try {
			cardiac = new BufferedReader(new FileReader(args[1]));
			while ((line = cardiac.readLine()) != null) {
				cardiacList.add(line);	
			}
			}catch (IOException e) {
				
				e.printStackTrace();
			}
		try {
				cancer = new BufferedReader(new FileReader(args[2]));
				while ((line = cancer.readLine()) != null) {
					cancerList.add(line);
				}
				}catch (IOException e) {
					
					e.printStackTrace();
				}
		try {
					neuro = new BufferedReader(new FileReader(args[3]));
					while ((line = neuro.readLine()) != null) {
						neuroList.add(line);
					}
					}catch (IOException e) {
						
						e.printStackTrace();
					}
		
		try {
			patient = new BufferedReader(new FileReader(args[0]));
			
			while ((line = patient.readLine()) != null) {
				line = line.replaceAll(", ",",");
				String[] arrOfStr = line.split(",");
				start = System.nanoTime(); 
				Patient currentPatient = new Patient();
				boolean cardiacMeds = false;
				boolean cancerMeds = false;
				boolean neuroMeds = false;
				currentPatient.setName(arrOfStr[0]);
				currentPatient.setAge(arrOfStr[1]);
				currentPatient.setGender(arrOfStr[2]);
				currentPatient.setComplaint(arrOfStr[3]);
				currentPatient.setAlert(arrOfStr[4]);
				currentPatient.setheartR(arrOfStr[5]); 
				currentPatient.setbloodP(arrOfStr[6]);
				currentPatient.setRespR(arrOfStr[7]);
				currentPatient.setTemp(arrOfStr[8]);
				currentPatient.setOxygen(arrOfStr[9]);
				currentPatient.setPain(arrOfStr[10]);
				for(int i = 11; i < arrOfStr.length; i++) {
					
					String meds = arrOfStr[i];
					currentPatient.setMeds(meds);
					if(cardiacList.contains(meds)) {
						cardiacMeds = true;
						
					}
					if(cancerList.contains(meds)) {
						cancerMeds = true;
						
					}
					if(neuroList.contains(meds)) {
						neuroMeds = true;
						
					}
				}
				
				currentPatient.setDoctor(cardiacMeds,cancerMeds,neuroMeds);
				currentPatient.checkTriaga();
				patientQueue.add(currentPatient);
				
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
			PrintWriter writer;
			int i = 0;
	        while (!patientQueue.isEmpty()) {
	        i++;
	        try {
			writer = new PrintWriter(i + ".txt", "UTF-8");
		    long end = System.nanoTime(); 
			patientQueue.peek().setWaitT(end - start);
			writer.print(patientQueue.remove());
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	   }
	 }
	}