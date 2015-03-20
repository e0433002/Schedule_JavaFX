package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Team extends ArrayList<Person>{
	private static final long serialVersionUID = 1L;
	static int teammateAmount = 18;
	
	public Team() {
		
	}
	
	public int getWorkMateAmount(int day){
		int count = teammateAmount;	// all teammate
		
		for (Person mate : this) {
			int state = mate.schedule[day-1];
			if(state == 1) count--;
		}
		return count;
	}
	public Team needWorkMateList(int day){
		Team t = new Team();
		for (Person mate : this) {
			int state = mate.schedule[day-1];
			if(state == 0) t.add(mate);
		}
		return t;
	}
	public Queue<Person> counterCandidate(int day){
		Team s = needWorkMateList(day);
		Queue<Person> t = new LinkedList<Person>();
		if(day >= Assigner.days) return new LinkedList<>();	// last day of the month
		else{
			for (Person mate : s) {
				int state = mate.schedule[day-1+1];
				if(state == 1) t.offer(mate);
			}
			return t;
		}
	}
	public int getTeammateAmount(){
		return teammateAmount;
	}
}