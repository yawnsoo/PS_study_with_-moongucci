package Stack_Queue;

import java.util.*;

public class 같은_숫자는_싫어 {

    public class Solution {
        public int[] solution(int []arr) {
            List<Integer> answer = new ArrayList<>();
            Stack<Integer> stack = new Stack<>();

            // 1. stack가 비어 있으면 arr[i]를 stack에 추가
            for(int i = 0; i<arr.length;i++){
                if(stack.isEmpty()){
                    stack.add(arr[i]);
                } else{
                    int temp = stack.pop();
                    // 2-1. stack.pop과 숫자가 같으면 pass
                    if(temp==arr[i]){
                        stack.add(temp);
                    }
                    // 2-2. stack.pop과 숫자가 다르면 answer에 추가
                    else{
                        answer.add(temp);
                        stack.add(arr[i]);
                    }
                }
            }

            // 3. stack에는 항상 값 존재, answer에 추가
            answer.add(stack.pop());

            return answer.stream().mapToInt(Integer::intValue).toArray();
        }
    }
}

/* Python
def solution(arr):
    answer = []


    for i in arr:

        answer.append(i)
        j = len(answer)-1

        if j>0 :
            if (answer[j] == answer[j - 1]):
                answer.pop()


    return answer
* */