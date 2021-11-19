import java.util.*;
public class AP1 {
	// This counter is for patient id
	public static int counter=1;
	// Instutute adds institue
	public static class institute {
		String name;
		float criteria;
		float ol;
		int beds;
		institute(String name, float criteria,float ol,int beds){
			this.name=name;
			this.criteria=criteria;
			this.ol=ol;
			this.beds=beds;
		}
	}
	
	//Patients carry all the info about a patient
	public static class patients{
		String name;
		float temp;
		float ol;
		int age;
		int id;
		String status;
		String ins="blank";
		int recovery=0;
		patients(String name,float temp,float ol,int age){
			this.name=name;
			this.temp=temp;
			this.ol=ol;
			this.age=age;
			this.id=counter;
		}
	}
	
	//displays info about institute
	public static void insinfo(institute temp) {
		System.out.println("Institute "+temp.name);
		System.out.println("Temperature should be <= "+temp.criteria);
		System.out.println("Oxygen levels should be >= "+temp.ol);
		System.out.println("No. of beds are "+temp.beds);
		if(temp.beds==0) {
			System.out.println("Institute is Closed");
		}
		else {
			System.out.println("Institute is open");
		}
	}
	
	//displays info about patients
	public static void patinfo(patients temp) {
		System.out.println(temp.name);
		System.out.println("Temperature is "+temp.temp);
		System.out.println("Oxygen levels is "+temp.ol);
		System.out.println("Admission Status – "+temp.status);
		System.out.println("Admitting Institute - "+temp.ins);
	}
	
	//printing all names of patients
	public static void AllP(patients temp) {
		System.out.println(temp.id+" "+temp.name);
	}
	
	//printing patients of a institution with recoverytime
	public static void AllP2(patients temp) {
		System.out.println(temp.name+", recovery time is "+temp.recovery+" days");
	}
	

	public static void main(String[] args) {

		ArrayList<institute> insList=new ArrayList<>(); //this arraylist contains list of institue
		ArrayList<patients> patList=new ArrayList<>();	//this arraylist contains list of patients
		Scanner scn=new Scanner(System.in);
		
		System.out.println("Number of patients ");
		int g=scn.nextInt();
		for(int i=0;i<g;i++) {		//taking info about each patient
			String name=scn.next();
			float temp=scn.nextFloat();
			float ol=scn.nextFloat();
			int age=scn.nextInt();
			patients p=new patients(name,temp,ol,age);
			if(ol>=90 && ol<=100) {
				patList.add(p);		//adding that patient to patients list
			}
			counter++;
		}
		
		
		
		
		
		
		System.out.println("Number of Queries ");
		int testcases=scn.nextInt();
		for(int i=0;i<testcases;i++) {
			int option =scn.nextInt(); 	//taking one option
										//conditions 
			if(option==1) {			//add institute
				System.out.println("Enter institute name "); //taking inputs- name,criteria,beds
				String insname=scn.next();
				System.out.println("Enter temperature criteria of institute ");
				float temp=scn.nextFloat();
				System.out.println("Enter Oxygen level criteria of institute ");
				float ol=scn.nextFloat();
				System.out.println("Enter no. of beds in institute ");
				int beds=scn.nextInt();
				institute t=new institute(insname,temp,ol,beds); //making a new institute
				insList.add(t);	//adding this institute to the list
				
				//Assigning Patients to institute
				insinfo(t);
				
				for(int j=0;j<patList.size();j++) {
					if(t.beds>0) {
						if(patList.get(j).ol>=t.ol && patList.get(j).status!="admitted") {	//if patient fullfill the criteria
							patList.get(j).ins=insname;
							patList.get(j).status="admitted";	//admit him
							System.out.println("Recovery days for admitted patient ID "+patList.get(j).id);
							int rd = scn.nextInt();		//Taking recovery date as input
							patList.get(j).recovery=rd;
							t.beds--;
							counter++;				//update patient id
						}
					}
				}
				
			}
			else if(option==2) {		//remove recovered people and print
				System.out.println("Account ID removed of admitted patients");
				for(int j=0;j<patList.size();j++) {
					if(patList.get(j).status=="admitted") {
						System.out.println(patList.get(j).id); 	//if removed, print id
					}
				}
			}
			else if(option==3) {		//closed institutes
				System.out.println("Accounts removed of Institute whose admission is closed");
				for(int j=0;j<insList.size();j++) {
					if(insList.get(j).beds==0) {
						System.out.println(insList.get(j).name);  //print institutes with no beds
					}
				}
			}
			else if(option==4) {		//all patients who are not yet admitted
				int LeftPatients=0;
				for(int j=0;j<patList.size();j++) {
					if(patList.get(j).status!="admitted") {
						LeftPatients++;
					}
				}
				System.out.println(LeftPatients+" patients");
			}
			else if(option==5) {		//Available institutes
				int LeftIns=0;
				for(int j=0;j<insList.size();j++) {
					if(insList.get(j).beds!=0) {
						LeftIns++;		//institutes with available beds
					}
				}
				System.out.println(LeftIns+ " institutes are admitting patients currently");
			}
			else if(option==6) {		//print details about the institute
				String name=scn.next();
				for(int j=0;j<insList.size();j++) {
					if(insList.get(j).name.equals(name)) {
						insinfo(insList.get(j));
//						System.out.println(insList.get(j).name);
					}
				}
			}
			else if(option==7) {		//print info about patient
				int PI=scn.nextInt();
				for(int j=0;j<patList.size();j++) {
					if(patList.get(j).id==PI) {
						patinfo(patList.get(j));
					}
				}
			}
			else if(option==8) {		//Display All Patients Names
				for(int j=0;j<patList.size();j++) {
					AllP(patList.get(j));
				}
			}
			else if(option==9){//printing patients of a institution with recoverytime
				String ins=scn.next();
				for(int j=0;j<patList.size();j++) {
					if(patList.get(j).ins.equals(ins)) {
						AllP2(patList.get(j));
					}
				}
			}
		}
	}

}