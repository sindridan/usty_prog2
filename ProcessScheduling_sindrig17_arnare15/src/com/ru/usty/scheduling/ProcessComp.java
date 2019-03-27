package com.ru.usty.scheduling;

import com.ru.usty.scheduling.process.ProcessInfo;

public class ProcessComp implements Comparable<ProcessComp> {
	//this class is used for comparing run length in later cases for priority queue
	int processID;
	long run;
	long waiting;
	Comparable respRatio;
	
	
	public ProcessComp(int processID, ProcessInfo getInfo) {
		this.processID = processID;
		this.run = getInfo.totalServiceTime;
		this.waiting = getInfo.elapsedWaitingTime;
		this.respRatio = (waiting + run)/run; //used for HRRN
	}
	
	public int getProcessID() {
		return this.processID;
	}


	@Override
	public int compareTo(ProcessComp o) {
		// TODO Auto-generated method stub
		return (int)(o.respRatio).compareTo(this.respRatio);
	}
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o instanceof ProcessComp) {
			ProcessComp tmp = (ProcessComp) o;
			return (this.processID == tmp.processID);
		}
		return false;
	}

}


