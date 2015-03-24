package model;

import static java.lang.System.out;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.Random;

public class Assigner {
	public static int days = 31;
	static ArrayList<Integer> workList = new ArrayList<>(Arrays.asList(41, 42,
			43, 44, 45, 49, 50, 51, 52, 46, 47, 48, 53, 54, 55, 56, 57));
	public static Team team = new Team();
	static Team needWorkTeam = new Team();

	// 1: 41,42,43,44,45,49,50,51,52,46,47,48,53,54,55,56,57
	public static void start() {
		init(team);
		for (int i = 1; i <= days; i++) {
			needWorkTeam = team.getWorkTeam(i); // get today work mate list
			ArrayList<Integer> todayArrangeList = appointWorkList(team, i,
					workList);// get today working list
			assignCounter(needWorkTeam, todayArrangeList, i); // assign counter
																// member
			assignNormal(needWorkTeam, todayArrangeList, i); // assign remain
																// member
		}
		// show schedule
		for (int i = 0; i < days; i++)
			out.printf("%2d ", i + 1);
		out.println();
		for (int i = 0; i < team.size(); i++, out.println())
			team.get(i).showSechule();
	}

	private static void assignNormal(Team needWorkTeam,
			ArrayList<Integer> workList, int day) {
		Random r = new Random();
		Team t = needWorkTeam.getWorkTeam(day);
		for (Person p : t) {
			int workNum = workList.get(r.nextInt(workList.size()));
			workList.remove(new Integer(workNum));
			p.setSechule(day, workNum);
		}
	}

	private static void assignCounter(Team needWorkTeam, ArrayList<Integer> workList, int day) {
		Queue<Person> ctrCdt = needWorkTeam.counterCandidate(day);
		ctrCdt = flushQueue(ctrCdt); // flush queue order
		int tomorrowHolidayMateAmt = ctrCdt.size();
		for (int i = 0, st = 49; i < tomorrowHolidayMateAmt; i++, st++) {
			if (!ctrCdt.isEmpty()) {
				Person p = ctrCdt.poll();
				workList.remove(new Integer(st)); // refresh appointWorkList
				p.setSechule(day, st);
			}
		}
	}

	private static Queue<Person> flushQueue(Queue<Person> q) {
		// prevent number sequential
		ArrayList<Person> temp = new ArrayList<Person>();
		Random r = new Random();
		int size = q.size();
		for (int i = 0; i < size; i++)
			temp.add(q.poll());
		for (int i = 0; i < size; i++)
			q.add(temp.remove(r.nextInt(temp.size())));
		return q;
	}

	private static ArrayList<Integer> appointWorkList(Team team, int day,
			ArrayList<Integer> workList) {
		ArrayList<Integer> sub = new ArrayList<>();
		for (int i = 0; i < team.getWorkMateAmt(day); i++)
			sub.add(workList.get(i));
		return sub;
	}
	
	public static int getTeammateAmount(){
		return team.getTeammateAmount();
	}

	private static void init(Team team) {
		int count = 0;
		team.add(new Person(136, 1, "周雲鵬"));
		team.get(count++).addVacay(2, 4, 8, 11, 15, 18, 22, 25, 29);
		team.add(new Person(136, 2, "鄭冠言"));
		team.get(count++).addVacay(1, 4, 8, 9, 11, 15, 18, 22, 25, 29);
		team.add(new Person(136, 3, "林子傑"));
		team.get(count++).addVacay(4, 8, 9, 10, 11, 17, 18, 22, 23, 30, 31);
		team.add(new Person(136, 4, "張宗豪"));
		team.get(count++).addVacay(1, 7, 8, 14, 15, 21, 22, 28, 29);
		team.add(new Person(136, 5, "葉維恆"));
		team.get(count++).addVacay(1, 7, 8, 14, 15, 21, 22, 28, 29, 30);
		team.add(new Person(136, 6, "蔡佳佑"));
		team.get(count++).addVacay(1, 2, 7, 8, 14, 15, 21, 22, 28, 29, 30);

		team.add(new Person(142, 7, "王仲堃"));
		team.get(count++).addVacay(1, 2, 6, 7, 13, 14, 20, 21, 25, 30);
		team.add(new Person(142, 8, "李翊辰"));
		team.get(count++).addVacay(8, 9, 13, 16, 17, 23, 24, 28, 31);
		team.add(new Person(142, 9, "宋豐洺"));
		team.get(count++).addVacay(1, 4, 7, 11, 14, 15, 18, 20, 21, 25, 27, 29);
		team.add(new Person(142, 10, "王仲勳"));
		team.get(count++).addVacay(2, 3, 10, 11, 19, 20, 21, 28, 29, 30);
		team.add(new Person(142, 11, "陳慶名"));
		team.get(count++).addVacay(6, 7, 13, 14, 20, 21, 22, 27, 28);
		team.add(new Person(142, 12, "達虎"));
		team.get(count++).addVacay(1, 2, 3, 9, 10, 16, 17, 23, 24);
		team.add(new Person(142, 13, "謝秉軒"));
		team.get(count++).addVacay(5, 6, 13, 14, 21, 22, 23, 28, 29);

		team.add(new Person(143, 14, "蔡建宇"));
		team.get(count++).addVacay(4, 7, 11, 15, 16, 20, 25, 26, 31);
		team.add(new Person(143, 15, "賴鴻昇"));
		team.get(count++).addVacay(1, 13, 14, 15, 16, 27, 28, 29, 30);
		team.add(new Person(143, 16, "胡佑嘉"));
		team.get(count++).addVacay(5, 6, 11, 17, 18, 19, 20, 25, 26, 27);
		team.add(new Person(143, 17, "曾冠榮"));
		team.get(count++).addVacay(1, 2, 3, 9, 12, 18, 19, 26, 27);

		team.add(new Person(142, 18, "王翊庭"));
		team.get(count++).addVacay(3, 7, 8, 11, 15, 16, 20, 24, 25, 30);
	}
}
