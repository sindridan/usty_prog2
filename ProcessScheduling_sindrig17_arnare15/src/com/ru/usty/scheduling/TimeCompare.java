package com.ru.usty.scheduling;
import java.util.*;
import com.ru.usty.scheduling.ProcessComp;

public class TimeCompare implements Comparator<ProcessComp> {


	@Override
	public int compare(ProcessComp o1, ProcessComp o2) {
		// TODO Auto-generated method stub
		// here the time to run each process is compared
		// the return values here are default for comparison
		if(o1.run < o2.run) {
			return -1;
		}
		else if(o1.run > o2.run) {
			return 1;
		}
		// if equal
		return 0;
	}
	

}
