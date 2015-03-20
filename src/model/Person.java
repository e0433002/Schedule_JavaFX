package model;

public class Person {
	final int modWorkListNum = 41;
	int number = 0;
	int T = 0;
	public String name = "";
	int[] schedule = new int[Assigner.days];
	int[] state = new int[Assigner.workList.size()];	// working type state
	
	public Person(int T, int number, String name){
		this.T = T;
		this.number = number;
		this.name = String.format("%5s", name);
		for (int i : schedule) schedule[i] = 0;
		for (int i : state) state[i] = 0;
	}
	public void setSechule(int day, int workNum){
		this.schedule[day-1] = workNum;
		this.state[workNum % modWorkListNum]++;		// refresh state
	}
	public void addVacay(int... intArr){
		for (int i : intArr) schedule[i - 1] = 1;	// 1 means holiday, 0 means workday
	}
	public boolean isWork(int day){
		return (schedule[day-1] != 1) ? true : false;
	}
	public boolean isPrepareLeave(int day){
		if(day < Assigner.days)
			if(schedule[day-1+1] == 1) return true;	// [day - 1] + 1 means tomorrow
		return false;
	}
	public void showSechule(){
		for(int i : schedule) System.out.printf("%2d ", i);
	}
	public void showState(){
		for(int i : state) System.out.printf("%2d ", i);
	}
	public String getTheDaySchedule(int day){
		return new String("" + schedule[day]);
	}
	public String getScheduleInStr(){
		String s = new String();
		for (int i = 1; i < schedule.length; i++) {
			if(schedule[i] != 1) s += new String(""+schedule[i]);
			else s += new String("00");
			s += " | ";
		}
		return s;
	}
}