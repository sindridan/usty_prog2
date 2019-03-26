package com.ru.usty.scheduling;


public class ProcessComp implements Comparable<ProcessComp> {
	//this class is used for comparing run length in later cases for priority queue
	int processID;
	long run;
	long waiting;
	long respRatio;
	
	
	public ProcessComp(int processID, long run, long waiting, long respRatio) {
		this.processID = processID;
		this.run = run;
		this.waiting = waiting;
		this.respRatio = respRatio; //used for HRRN
	}


	@Override
	public int compareTo(ProcessComp o) {
		// TODO Auto-generated method stub
		return 0;
	}

}


