public class Main {

        //1. for문으로 node 방문
        //2. dfs로 node와 이어진 부분 모두 true
        //  2.1 n만큼 돌면 종료
        //  2.2 하나라도 true로 바뀌었으면 answer++;

        int answer = 0;
        boolean[] visited;
        int nodes;

        public void dfs(int[][] computers, int node, boolean flag, int idx){
            if(idx==nodes){
                if(flag) answer++;
                return;
            }

            for(int i = idx; i < nodes; i++){
                if(!visited[i]&&computers[node][i]==1){
                    flag = true;
                    visited[i] = true;
                    dfs(computers, i, flag, i+1);
                }
            }
        }

        public int solution(int n, int[][] computers) {

            nodes = n;
            visited = new boolean[n];

            for(int i = 0; i < n; i++){
                //i 번째 node와 이어진 모든 node 방문
                dfs(computers, i, false, 0);
            }

            return answer;
        }

    public static void main(String[] args) {
        int a = new Main().solution(3, new int[][]{{1,1,0},{1,1,0},{0,0,1}});

        System.out.println(a);
    }
}
