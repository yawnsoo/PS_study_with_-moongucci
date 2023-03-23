package Hash;
import java.util.*;

public class 전화번호_목록 {
    /*
    Stack을 이용하면 더 빠르게 연산 가능하지 않을까?
    */

    class Solution {
        boolean answer = true;
        String[] picked = new String[2];

        public boolean isStartWith(String[] p){
            String[] a = p[0].split("");
            String[] b = p[1].split("");

            int min = Math.min(a.length,b.length);

            for(int i = 0; i<min;i++){
                if(!a[i].equals(b[i])){
                    return false;
                }
            }

            return true;
        }

        public void combination(String[] phone_book, int start, int depth, String[] picked){
            if(depth==2){
                if(isStartWith(picked)){
                    answer = false;
                }
                return;
            }

            for(int i = start; i<phone_book.length; i++){
                if(!answer) return;
                picked[depth] = phone_book[i];
                combination(phone_book, i+start+1, depth+1, picked);
            }
        }


        public boolean solution(String[] phone_book) {

            combination(phone_book, 0, 0, picked);

            return answer;
        }
    }
}
