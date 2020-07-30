import java.util.HashMap;
import java.util.Arrays;

// programmers hash problems

/*
 1. findPlayer
    input: participant, completion
      participant: 마라톤에 참여한 선수들의 이름이 담긴 배열
      completion: 완주한 선수들의 이름이 담긴 배열
    output: 완주하지 못한 선수의 이름
    
    예시) participant = ["Kim", "Lee", "Park"]
         completion = ["Lee", "Park"]   
         output = "Kim"

  2. findPrefixNum
    input: phone_book
      phone_book: 전화번호를 담은 배열
    output: 어떤 번호가 다른 번호의 접두어인 경우면 false, 그렇지 않으면 true
    
    예시) phone_book = ["123", "456", "789"]
         output = true

         phone_book = ["119", "1193742874", "57042834"]
         output = false

  3. spyWear
    input: clothes
        clothes: 스파이가 가진 의상들이 담긴 2차원 배열
    output: 서로 다른 옷의 조합의 수   
    
    예시) clothes = [["yellow_hat", "headgear"],
                    ["blue_sunglasses", "eyewear"],
                    ["green_turban", "headgear"]]   
            output = 5
*/

public class Main {
    public String findPlayer(String[] participant, String[] completion) {
        String answer = "";
        HashMap<String, Integer> participantMap = new HashMap<>();
        
        for (String person : participant) participantMap.put(person, participantMap.getOrDefault(person, 0) + 1);
        for (String person: completion) participantMap.put(person, participantMap.get(person) - 1);
        
        for (String person : participantMap.keySet()) {
            if (participantMap.get(person) > 0) {
                return person;
            }
        }
        return answer;
    }

    public boolean findPrefixNum(String[] phone_book) {
        Arrays.sort(phone_book);
        for (int i=0; i<phone_book.length;i++) {
            for (int j=0;j<phone_book.length;j++) {
                if (i >= j) {
                    continue;
                }
                else if (phone_book[j].startsWith(phone_book[i])) {
                    return false;
                } else {
                    continue;
                }
            }
        }
        return true;
    }

    public int spyWear(String[][] clothes) {
        HashMap<String, Integer> clothesMap = new HashMap<>();
        
        for (String[] cloth : clothes) clothesMap.put(cloth[1], clothesMap.getOrDefault(cloth[1], 1) + 1);
        int answer = 1;
        for (Integer num : clothesMap.values()) answer *= num;
        return answer - 1;
    }

    
}
