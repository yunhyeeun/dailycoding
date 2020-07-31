import java.util.*;

class Main {
    public int[] stock(int[] prices) {
        int[] answer = new int[prices.length];
        for (int i=0;i<prices.length;i++) {
            int cnt = 0;
            for (int j=i+1;j<prices.length;j++) {
                if (prices[i] > prices[j]) {
                    cnt += 1;
                    break;
                } else {
                    cnt += 1;
                }
            }
            answer[i] = cnt;
        }
        return answer;
    }

    public int[] func(int[] progresses, int[] speeds) {
        List<Integer> answerList = new ArrayList<Integer>();
        Queue<Integer> endDate = new LinkedList<Integer>();
         
        for (int i=0;i<progresses.length;i++) {
            endDate.add((int)Math.ceil((float)(100 - progresses[i]) / speeds[i]));
        }
        
        while (endDate.size() > 0) {
            int level = endDate.poll();
            int cnt = 0;
            while (endDate.size() > 0 && level >= endDate.peek()) {
                cnt ++;
                endDate.poll();
            }
            cnt ++;
            answerList.add(cnt);
        }
        int[] answer = new int[answerList.size()];
        for (int i=0;i<answerList.size();i++) {
            answer[i] = answerList.get(i);
        }

        return answer;
    }

}

