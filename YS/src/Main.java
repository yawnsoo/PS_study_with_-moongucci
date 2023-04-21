import java.util.*;

public class Main {

    public long solution(int n, int[] room, int b, int c){
        long answer = 0;

//      각각의 시험장에 총감독관은 오직 1명만 있어야 하고
        for (int i = 0; i < n; i++) {
            room[i] -= b;
            answer++;
        }
//      부감독관은 여러 명 있어도 된다.
        for (int i = 0; i < n; i++) {
            if(room[i]>0){
                answer += Math.ceil(room[i]/(float)c);
            }
        }


//        출력
//        각 시험장마다 응시생을 모두 감독하기 위해 필요한 감독관의 최소 수를 출력한다.
//        주어진 문제에서 answer는 최대 1,000,000 * 1,000,000 = 1,000,000,000,000
//        int의 범위 는 -2147483648 ~ 2,147,483,647
//        long의 범위는 -9223372036854775808 ~ 9,223,372,036,854,775,807
//        그러므로, answer의 자료형은 long이여야 한다.
        return answer;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] room = new int[n];
        for (int i = 0; i < n; i++) {
            int a = sc.nextInt();
            room[i] = a;
        }
        int b = sc.nextInt();
        int c = sc.nextInt();


        System.out.println(new Main().solution(n,room,b,c));
    }
}