package com.ru.usty.scheduling;

import java.util.*;
import com.ru.usty.scheduling.process.*;
import com.ru.usty.scheduling.ProcessComp;

public class Scheduler {

	ProcessExecution processExecution;
	ProcessInfo pInfo;
	ProcessInfo currProcess;
	
	Policy policy;
	int quantum;

	/* OTHER VARIABLES IN RELATION TO TIMING AND PROCESSES */

	final int amountOfProcs = 15; // hardcoded, can be fixed by getting the size of arraylist from testsuite
	int procsFinished = 0;

	/* DATA STRUCTURES FOR IMPLEMENTATIONS */

	Queue<Integer> FCFSstructure;
	PriorityQueue<SPNProc> pQueue;
	PriorityQueue<ProcessComp> hRRNQueue;

	/* TIMING OF PROCESSES */
	
	long[] waiting;	//
	long[] startTime;	// when process reaches execution
	long[] endTime;	// after execution

	
	/**
	 * DO NOT CHANGE DEFINITION OF OPERATION
	 */
	public Scheduler(ProcessExecution processExecution) {
		this.processExecution = processExecution;

		/**
		 * Add general initialization code here (if needed)
		 */
	}

	/**
	 * DO NOT CHANGE DEFINITION OF OPERATION
	 */
	public void startScheduling(Policy policy, int quantum) {

		this.policy = policy;
		this.quantum = quantum;
		//reset the time counters
		
		waiting = new long[amountOfProcs];
		startTime = new long[amountOfProcs];
		endTime = new long[amountOfProcs];
		

		switch (policy) {
		case FCFS: // First-come-first-served
			System.out.println("Starting new scheduling task: First-come-first-served");

			// linked list because nodes should point to the next node in the list
			// which creates a solution to keep track of front node and next one
			// keeping order of the queue
			this.FCFSstructure = new LinkedList<Integer>();

			break;
		case RR: // Round robin
			System.out.println("Starting new scheduling task: Round robin, quantum = " + quantum);
			/**
			 * Add your policy specific initialization code here (if needed)
			 */
			break;
		case SPN: // Shortest process next
			System.out.println("Starting new scheduling task: Shortest process next");
			/* Comparator<ProcessComp> compTime = new TimeCompare(); */
			this.pQueue = new PriorityQueue<SPNProc>();
			// SPNProc spn = new SPNProc(processID,
			// processExecution.getProcessInfo(processID));
			break;
		case SRT: // Shortest remaining time
			System.out.println("Starting new scheduling task: Shortest remaining time");
			/**
			 * Add your policy specific initialization code here (if needed)
			 */
			break;
		case HRRN: // Highest response ratio next
			System.out.println("Starting new scheduling task: Highest response ratio next");
			this.hRRNQueue = new PriorityQueue<ProcessComp>();
			break;
		case FB: // Feedback
			System.out.println("Starting new scheduling task: Feedback, quantum = " + quantum);
			/**
			 * Add your policy specific initialization code here (if needed)
			 */
			break;
		}

		/**
		 * Add general scheduling or initialization code here (if needed)
		 */

	}

	/**
	 * DO NOT CHANGE DEFINITION OF OPERATION
	 */
	public void processAdded(int processID) {
		// process mapped to a timestamp when it first entered
		waiting[processID] = System.currentTimeMillis();
		
		switch (policy) {
		case FCFS: // First-come-first-served
			// if the queue is empty, then the process is added and executed
			// else, it's just added to queue and not executed until it has reached the
			// front
			if (this.FCFSstructure.isEmpty()) {
				this.FCFSstructure.add(processID);

				processExecution.switchToProcess(processID);
				// this timestamp in addition to waiting timestamp will give a clear
				// total time of the processes lifespan
				startTime[processID] = System.currentTimeMillis();
			} else {
				// the list isn't empty, it must wait for its turn
				this.FCFSstructure.add(processID);
			}

			break;
		case RR: // Round robin
			/**
			 * Add your policy specific initialization code here (if needed)
			 */
			break;
		case SPN: // Shortest process next
			SPNProc spnproc = new SPNProc(processID, processExecution.getProcessInfo(processID));
			if (pQueue.isEmpty()) {
				//currProcess = processExecution.getProcessInfo(processID);
				//System.out.println("Add if empty: " + processID);
				//System.out.println("TotalServiceTime " + spnproc.getCompVar() + " for ID: " + spnproc.getProcessID());;
				pQueue.add(spnproc);
				processExecution.switchToProcess(processID);
				currProcess = processExecution.getProcessInfo(processID);
				startTime[processID] = System.currentTimeMillis();

			} else {
				pQueue.add(spnproc);
				//System.out.println("Not empty add: " + processID);
				//System.out.println("TotalServiceTime " + spnproc.getCompVar() + " for ID: " + spnproc.getProcessID());;
			}

			break;
		case SRT: // Shortest remaining time
			/**
			 * Add your policy specific initialization code here (if needed)
			 */
			break;
		case HRRN: // Highest response ratio next
			ProcessComp pComp = new ProcessComp(processID, processExecution.getProcessInfo(processID));
			if(hRRNQueue.isEmpty()) {
				hRRNQueue.add(pComp);
				processExecution.switchToProcess(processID);
				currProcess = processExecution.getProcessInfo(processID);
				startTime[processID] = System.currentTimeMillis();
			}
			else {
				updateHRRNqueue();
				hRRNQueue.add(pComp);
			}
			break;
		case FB: // Feedback
			/**
			 * Add your policy specific initialization code here (if needed)
			 */
			break;
		}

	}

	/**
	 * DO NOT CHANGE DEFINITION OF OPERATION
	 */
	public void processFinished(int processID) {

		procsFinished++;
		switch (policy) {
		case FCFS:
			// gets the head of the list and removes it
			this.FCFSstructure.remove(processID);
			//endTime[processID] = System.currentTimeMillis();
			// now if list isn't empty, we execute the next node
			if (!this.FCFSstructure.isEmpty()) {
				// get next head and execute that process
				processExecution.switchToProcess(this.FCFSstructure.element());
				startTime[this.FCFSstructure.element()] = System.currentTimeMillis();
			}
			break;
		case RR: // Round robin
			/**
			 * Add your policy specific initialization code here (if needed)
			 */
			break;
		case SPN: // Shortest process next
			System.out.println("attempting to remove id: " + processID);
			System.out.println("pQueue peek id: " + pQueue.peek().getProcessID());
			if(pQueue.peek().getProcessID() == processID) {
				System.out.println("Success.");	
				pQueue.remove(pQueue.element());
			}
			else {
				System.out.println("Process has been moved in queue, removing correct one.");
				SPNProc temp = new SPNProc(processID, currProcess);
				pQueue.remove(temp);
			}
			//endTime[processID] = System.currentTimeMillis();
			if (!pQueue.isEmpty()) {
				//System.out.println("Inside remove: " + pQueue.peek().getProcessID());
				processExecution.switchToProcess(pQueue.peek().getProcessID());
				startTime[pQueue.peek().getProcessID()] = System.currentTimeMillis();
				currProcess = processExecution.getProcessInfo(pQueue.peek().getProcessID());
			}
			System.out.println("Size of pQueue: " + pQueue.size());
			break;
		case SRT: // Shortest remaining time
			/**
			 * Add your policy specific initialization code here (if needed)
			 */
			break;
		case HRRN: // Highest response ratio next
			if(hRRNQueue.peek().getProcessID() == processID) {
				System.out.println("Success.");	
				hRRNQueue.remove(hRRNQueue.element());
			}
			else {
				System.out.println("Process has been moved in queue, removing correct one.");
				ProcessComp temp = new ProcessComp(processID, currProcess);
				hRRNQueue.remove(temp);
			}
			endTime[processID] = System.currentTimeMillis();
			updateHRRNqueue();
			if(!hRRNQueue.isEmpty()) {
				processExecution.switchToProcess(hRRNQueue.peek().getProcessID());
				startTime[hRRNQueue.peek().getProcessID()] = System.currentTimeMillis();
				currProcess = processExecution.getProcessInfo(hRRNQueue.peek().getProcessID());
			}
			break;
		case FB: // Feedback
			/**
			 * Add your policy specific initialization code here (if needed)
			 */
			break;
		}
		if (procsFinished == amountOfProcs) {
			printTime();
		}
	}

	public void printTime() {
		// calculations for response time and total turn-around time

		long responseTime = 0;
		long tat = 0;

		// response time
		for (int i = 0; i < amountOfProcs; i++) {
			responseTime += startTime[i] - waiting[i];
			tat += endTime[i] - waiting[i];
		}
		
		System.out.println("Policy: " + policy);
		System.out.println("Average response time: " + responseTime / amountOfProcs);
		System.out.println("Average turnaround time: " + tat / amountOfProcs);
	}
	
	private void updateHRRNqueue() {
		PriorityQueue<ProcessComp> temp = new PriorityQueue<ProcessComp>();
		for (int i = 0; i < amountOfProcs; i++) {
			//check if endTime has been recorded
			if(waiting[i] != 0) {
				if(endTime[i] == 0) {
					ProcessComp pComp = new ProcessComp(i, processExecution.getProcessInfo(i));
					temp.add(pComp);
				}
			}
		hRRNQueue.clear();
		hRRNQueue.addAll(temp);
		}
	}
}
