package com.ru.usty.scheduling;

import com.ru.usty.scheduling.visualization.TestSuite;

public class SchedulingMainProgram {
	public static void main(String[] args) {

		//Visualization starts at stated policy and then continues to the
		//end in order FCFS, RR(500), RR(2000), SPN, SRT, HRRN, FB
		//TestSuite.runVisualization(Policy.FCFS);
<<<<<<< HEAD
		TestSuite.runVisualization(Policy.SRT);
=======
		TestSuite.runVisualization(Policy.HRRN);
>>>>>>> f948c6949cb8afe1962eeb9fd8fe04e6a1f5620f

	}
}
