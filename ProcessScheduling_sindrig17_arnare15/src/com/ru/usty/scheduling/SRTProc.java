package com.ru.usty.scheduling;

import com.ru.usty.scheduling.process.ProcessInfo;
//not going to be implemented, can delete this

public class SRTProc implements Comparable{
	//this class is used for comparing run length in later cases for priority queue
	int processID;
	Comparable compVar;
	ProcessInfo pInfo;
	//ProcessInfo info;
	
	public SRTProc(int processID, ProcessInfo getInfo) {
		this.processID = processID;
		//this.runTime = info.totalServiceTime;
		this.pInfo = getInfo; 
		this.compVar = pInfo.totalServiceTime - pInfo.elapsedExecutionTime;
	}
	
	
	public int getProcessID() {
		return processID;
	}
	
   public Object getCompVar() {
        return compVar;
    }
   
   public String toString() {
	   return "SRT object ID: " + processID + ", runtime: " + compVar;
   }

   //for iterator
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return this.compVar.compareTo(((SRTProc)o).compVar);
	}
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o instanceof SRTProc) {
			SRTProc tmp = (SRTProc) o;
			return (this.processID == tmp.processID);
		}
		return false;
	}

}