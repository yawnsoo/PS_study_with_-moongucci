package BOJ;

import java.util.*;

/**
 * 문제에 사용되는 알고리즘은 어렵지 않았음.
 * BUT, 주어진 문제의 input값을 결정하는데 오래 걸림.
 * 다음과 같은 시도를 함.
 * 1. 톱니바퀴의 input값을 회전을 목표로 deque를 사용 -> deque의 중간 순서의 값을 가져오기가 안됨
 * 2. StringBuffer를 이용 -> 반시계 방향으로 회전시 끝 값 -> 처음 으로 이동. StringBuffer는 뒤에 값을 추가하기에 유용, 앞에 추가하는 메서드는 없음
 *      ---->>>> 찾아보니 insert()메서드가 있었음....!
 *      수정 후 제출 결과, String 보다 StringBuffer가 속도, 메모리 더 효율적.
 * 3. String 이용 -> charAt()과 substring()메서드 이용하여 회전 구현
* */

public class BOJ14891 {
    static int k;
    static StringBuffer[] cogwheels = new StringBuffer[4];


    public static int solution(Queue<int[]> commands) {
        int ans = 0;

        while (!commands.isEmpty()) {
            int[] cmd = commands.poll();
            bfs(cmd[0], cmd[1]);
        }

        for (int i = 0; i < 4; i++) {
            ans += (cogwheels[i].charAt(0) - 48) * Math.pow(2, i);
        }

        return ans;
    }

    private static void bfs(int cogIdx, int param) {
        Queue<int[]> cogIdxAndParam = new LinkedList<>();
        boolean[] turned = new boolean[4];
        cogIdxAndParam.add(new int[]{cogIdx, param}); // n번째 톱니, 회전 방향

        while (!cogIdxAndParam.isEmpty()) {
            int[] cmd = cogIdxAndParam.poll();

            if(cmd[0]==0){ //1번째
                if(!turned[cmd[0]+1]&&cogwheels[cmd[0]].charAt(2)!=cogwheels[cmd[0]+1].charAt(6)){
                    cogIdxAndParam.add(new int[]{cmd[0]+1, cmd[1]*-1});
                }
            } else if (cmd[0] == 3) { //4번째
                if(!turned[cmd[0]-1]&&cogwheels[cmd[0]].charAt(6)!=cogwheels[cmd[0]-1].charAt(2)){
                    cogIdxAndParam.add(new int[]{cmd[0]-1, cmd[1]*-1});
                }
            } else { //중간
                if(!turned[cmd[0]+1]&&cogwheels[cmd[0]].charAt(2)!=cogwheels[cmd[0]+1].charAt(6)){
                    cogIdxAndParam.add(new int[]{cmd[0]+1, cmd[1]*-1});
                }
                if(!turned[cmd[0]-1]&&cogwheels[cmd[0]].charAt(6)!=cogwheels[cmd[0]-1].charAt(2)){
                    cogIdxAndParam.add(new int[]{cmd[0]-1, cmd[1]*-1});
                }
            }

            StringBuffer s = cogwheels[cmd[0]];
            if (cmd[1] == 1) { // 회전 : 시계 방향
                s.insert(0,s.charAt(7));
                s.deleteCharAt(8);
            } else { // 회전 : 반 시계
                s.append(s.charAt(0));
                s.deleteCharAt(0);
            }
            turned[cmd[0]] = true;
        }
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < 4; i++) {
            //오른쪽 idx=2, 왼쪽 idx=6
            StringBuffer sb = new StringBuffer(sc.nextLine());
            cogwheels[i] = sb;
        }


        k = sc.nextInt();
        Queue<int[]> commands = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            commands.add(new int[]{sc.nextInt()-1,sc.nextInt()});
        }

        System.out.println(solution(commands));
    }
}