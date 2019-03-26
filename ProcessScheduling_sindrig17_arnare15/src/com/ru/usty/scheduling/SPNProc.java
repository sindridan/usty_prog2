package com.ru.usty.scheduling;
import com.ru.usty.scheduling.process.ProcessInfo;
//not going to be implemented, can delete this

public class SPNProc implements Comparable{
	//this class is used for comparing run length in later cases for priority queue
	int processID;
	Comparable compVar;
	//ProcessInfo info;
	
	public SPNProc(int processID, ProcessInfo getInfo) {
		this.processID = processID;
		//this.runTime = info.totalServiceTime;
		ProcessInfo temp = getInfo; 
		this.compVar = temp.totalServiceTime;
		
	}
	
	
	public int getProcessID() {
		return processID;
	}
	
   public Object getCompVar() {
        return compVar;
    }
   
   public String toString() {
	   return "SPN object ID: " + processID + ", runtime: " + compVar;
   }

   //for iterator
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return this.compVar.compareTo(((SPNProc)o).compVar);
	}
	
	//for iterator
	
}

