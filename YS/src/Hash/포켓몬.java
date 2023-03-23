package Hash;
import java.util.*;

public class 포켓몬 {

    class Solution {
        public int solution(int[] nums) {
            int answer = nums.length/2;
            Map<Integer,Integer> map = new HashMap<>();

            for(int i : nums){
                map.put(i, map.getOrDefault(i,0)+1);
            }

            return Math.min(answer, map.size());
        }
    }

}
