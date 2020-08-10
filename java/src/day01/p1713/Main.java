package day01.p1713;

import java.io.FileInputStream;
import java.util.*;

public class Main {

    static int N;
    static int total;
    static Candidate[] people;

	public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("java/src/day01/p1713/input.txt"));
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        total = sc.nextInt();

        // System.out.println(N + ", " + total);

        people = new Candidate[101];
        List<Candidate> list = new ArrayList<>();

        for (int i = 0; i < total; i++) {
            int tmp = sc.nextInt();
            // 초기화
            if (people[tmp] == null) {
                people[tmp] = new Candidate(tmp, 0, 0, false);
            }
            // 사진틀에 있는경우 -> count++;
            if (people[tmp].isIn == true) {
                people[tmp].count++;
            }
            // 사진틀에 없는경우
            else {
                // 꽉찬경우 -> 제거
                if (list.size() == N) {
                    Collections.sort(list);
                    Candidate person = list.remove(0);
                    person.isIn = false;
                }
                // 후보 추가
                people[tmp].count = 1;
                people[tmp].isIn = true;
                people[tmp].date = i;
                list.add(people[tmp]);
            }
        }

        Collections.sort(list, new Comparator<Candidate>() {
            @Override
            public int compare(Candidate arg0, Candidate arg1) {
                if (arg0.value < arg1.value) {
                    return -1;
                } else if (arg0.value == arg1.value) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
        for (Candidate  person: list) {
            System.out.println(person.value);
        }
    }
}

class Candidate implements Comparable<Candidate> {
    int value;
    int count;
    int date;
    boolean isIn;
    
	public Candidate(int value, int count, int date, boolean isIn) {
		this.value = value;
		this.count = count;
		this.date = date;
		this.isIn = isIn;
	}

	@Override
	public String toString() {
		return "Candidate [count=" + count + ", date=" + date + ", isIn=" + isIn + ", value=" + value + "]";
	}

    @Override
	public int compareTo(final Candidate arg0) {
        if (count < arg0.count) {
            return -1;
        } else if (count == arg0.count) {
            // date 비교
            if (date < arg0.date) {
                return -1;
            } else if (date == arg0.date) {
                return 0;
            } else {
                return 1;
            }
        } else {
            return 1;
        }
	} 
}


