package com.ru.usty.scheduling;
import java.util.*;
import com.ru.usty.scheduling.process.ProcessExecution;
import com.ru.usty.scheduling.process.Process;

public class Scheduler {

	ProcessExecution processExecution;
	Process proc;
	Policy policy;
	int quantum;
	
	/* OTHER VARIABLES IN RELATION TO TIMING AND PROCESSES */
	
	final int amountOfProcs = 15; //hardcoded, can be fixed by getting the size of arraylist from testsuite
	int procsFinished = 0;
	
	/* DATA STRUCTURES FOR IMPLEMENTATIONS */
	
	Queue<Integer> FCFSstructure;

	/* TIMING OF PROCESSES */
	long[] waiting = new long[amountOfProcs];	//
	long[] startTime = new long[amountOfProcs];	// when process reaches execution
	long[] endTime = new long [amountOfProcs];	// after execution
	


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

		switch(policy) {
		case FCFS:	//First-come-first-served
			System.out.println("Starting new scheduling task: First-come-first-served");
			
			//linked list because nodes should point to the next node in the list
			//which creates a solution to keep track of front node and next one
			//keeping order of the queue
			this.FCFSstructure = new LinkedList<Integer>();
			
			break;
		case RR:	//Round robin
			System.out.println("Starting new scheduling task: Round robin, quantum = " + quantum);
			/**
			 * Add your policy specific initialization code here (if needed)
			 */
			break;
		case SPN:	//Shortest process next
			System.out.println("Starting new scheduling task: Shortest process next");
			/**
			 * Add your policy specific initialization code here (if needed)
			 */
			break;
		case SRT:	//Shortest remaining time
			System.out.println("Starting new scheduling task: Shortest remaining time");
			/**
			 * Add your policy specific initialization code here (if needed)
			 */
			break;
		case HRRN:	//Highest response ratio next
			System.out.println("Starting new scheduling task: Highest response ratio next");
			/**
			 * Add your policy specific initialization code here (if needed)
			 */
			break;
		case FB:	//Feedback
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
		
		switch(policy) {
			case FCFS:	//First-come-first-served
				//if the queue is empty, then the process is added and executed
				//else, it's just added to queue and not executed until it has reached the front
				if(this.FCFSstructure.isEmpty()) {
					this.FCFSstructure.add(processID);
					
					processExecution.switchToProcess(processID);
					// this timestamp in addition to waiting timestamp will give a clear 
					// total time of the processes lifespan
					startTime[processID] = System.currentTimeMillis();
				}
				else {
					//the list isn't empty, it must wait for its turn
					this.FCFSstructure.add(processID);
				}
				
				break;
			case RR:	//Round robin
				/**
				 * Add your policy specific initialization code here (if needed)
				 */
				break;
			case SPN:	//Shortest process next
				/**
				 * Add your policy specific initialization code here (if needed)
				 */
				break;
			case SRT:	//Shortest remaining time
				/**
				 * Add your policy specific initialization code here (if needed)
				 */
				break;
			case HRRN:	//Highest response ratio next
				/**
				 * Add your policy specific initialization code here (if needed)
				 */
				break;
			case FB:	//Feedback
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
		switch(policy) {
		case FCFS:
			//gets the head of the list and removes it
			this.FCFSstructure.remove(processID);
			endTime[processID] = System.currentTimeMillis();
			//now if list isn't empty, we execute the next node
			if(!this.FCFSstructure.isEmpty()) {
				//get next head and execute that process
				processExecution.switchToProcess(this.FCFSstructure.element());
				startTime[this.FCFSstructure.element()] = System.currentTimeMillis();
			}
			break;
		case RR:	//Round robin
			/**
			 * Add your policy specific initialization code here (if needed)
			 */
			break;
		case SPN:	//Shortest process next
			/**
			 * Add your policy specific initialization code here (if needed)
			 */
			break;
		case SRT:	//Shortest remaining time
			/**
			 * Add your policy specific initialization code here (if needed)
			 */
			break;
		case HRRN:	//Highest response ratio next
			/**
			 * Add your policy specific initialization code here (if needed)
			 */
			break;
		case FB:	//Feedback
			/**
			 * Add your policy specific initialization code here (if needed)
			 */
			break;
	}
		if(procsFinished == amountOfProcs) {
			printTime();
		}

	}
	
	public void printTime() {
		// calculations for response time and total turnaround time
		
		long responseTime = 0;
		long tat = 0;
		
		//response time
		for (int i = 0; i < amountOfProcs; i++) {
			responseTime += startTime[i] - waiting[i];
			tat += endTime[i] - waiting[i];
		}
		System.out.println("Policy: " + policy);
		System.out.println("Average response time: " + responseTime / amountOfProcs);
		System.out.println("Average turnaround time: " + tat / amountOfProcs);
		
	}
}
