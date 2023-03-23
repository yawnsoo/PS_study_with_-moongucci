package Hash;
import java.util.*;

public class 완주하지_못한_선수 {

    class Solution {
        public String solution(String[] participant, String[] completion) {
            String answer = "";

            Map<String, Integer> map = new HashMap<>();

            for(String s : participant){
                map.put(s, map.getOrDefault(s,0)+1);
            }

            for(String s : completion){
                map.replace(s, map.get(s)-1);
            }

            for(String s : map.keySet()){
                if(map.get(s)>0){
                    return s;
                }
            }

            return answer;
        }
    }

}
