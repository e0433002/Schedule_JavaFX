package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Team extends ArrayList<Person> {
	private static final long serialVersionUID = 1L;
	static int teammateAmount = 18;

	public Team() {

	}

	public int getWorkMateAmt(int day) {
		int count = teammateAmount; // 所有役男總數
		for (Person mate : this) {
			int state = mate.schedule[day - 1];
			if (state == 1)
				count--;
		}
		return count;
	}

	public Team getWorkTeam(int day) {
		Team t = new Team();
		for (Person mate : this) {
			int state = mate.schedule[day - 1];
			if (state == 0)
				t.add(mate);
		}
		return t;
	}

	public Queue<Person> counterCandidate(int day) {
		Team s = getWorkTeam(day);
		Queue<Person> t = new LinkedList<Person>();
		if (day == Assigner.days)
			return new LinkedList<>(); // 當月的最後一天,無法得知下月一日狀況
		else {
			for (Person mate : s) {
				int state = mate.schedule[day - 1 + 1];
				if (state == 1) t.offer(mate);
			}
			return t;
		}
	}

	public void switchDay(String day, String oldVal, String newVal) {
		Person oldMate = null, newMate = null;
		for (int i = 0, _day = Integer.parseInt(day); i < teammateAmount; i++) {
			String currentDay = this.get(i).getTheDaySchedule(_day);
			if (currentDay.equals(oldVal))
				oldMate = this.get(i);
			if (currentDay.equals(newVal))
				newMate = this.get(i);
		}
		if (oldMate != null && newMate != null) {
			oldMate.setSechule(Integer.parseInt(day), Integer.parseInt(newVal));
			newMate.setSechule(Integer.parseInt(day), Integer.parseInt(oldVal));
		}
	}

	public int getTeammateAmount() {
		return teammateAmount;
	}
}