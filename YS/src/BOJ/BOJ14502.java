package BOJ;

import java.util.*;

public class BOJ14502 {
    static int n;
    static int m;
    static int zeros;
    static int[][] map;
    static int[][] mapSimul;
    static boolean[][] visited;
    static List<int[]> spreaders = new ArrayList<>();
    static int answer;
    static int infected;
    static int[] dy = new int[]{0,1,0,-1};
    static int[] dx = new int[]{1,0,-1,0};

    //완전탐색?
    //벽 3개 : 62*61*60
    //BFS 시간 복잡도 : 64
    // 총 시간 복잡도(최악) = (62*61*60)*64 = 약 1000만대, 굿
    public int solution() {
        answer = 0;

        // 벽 3개 세우기 : dfs
        dfs(0,0,3);

        return answer;
    }

    private void dfs(int y, int x, int pick) {
        //  - 벽 3개 세웠을 때
        if(pick == 0){
            //  바이러스 퍼지는거 BFS
            bfs();
            return;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(!visited[i][j]&&(i==y&&j>=x||i>y)){
                    visited[i][j] = true;
                    map[i][j] = 1;
                    dfs(i,j,pick-1);
                    visited[i][j] = false;
                    map[i][j] = 0;
                }
            }
        }
    }

    private void bfs() {

        Queue<int[]> q = new LinkedList<>(spreaders);
        infected = 0;

        for (int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                mapSimul[i][j] = map[i][j];
            }
        }

        while (!q.isEmpty()) {
            int[] spreader = q.poll();

            for (int i = 0; i < 4; i++) {
                int ny = spreader[0] + dy[i];
                int nx = spreader[1] + dx[i];

                //범위 이탈
                if(ny<0||nx<0||ny>=n||nx>=m){
                    continue;
                }
                //0->2로 바뀌면 infected++
                if(mapSimul[ny][nx]==0){
                    mapSimul[ny][nx]=2;
                    infected++;
                    q.add(new int[]{ny,nx});
                }
            }
        }

        //  - answer = zeros-(infected+3)
        answer = Math.max(answer, zeros - (infected + 3));
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        map = new int[n][m];
        mapSimul = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int input = sc.nextInt();
                if(input==0){
                    zeros++;
                }else{ //1,2 이면 벽 못 세움, 전염 못함
                    visited[i][j] = true;
                    //2 위치 파악
                    if(input==2){
                        spreaders.add(new int[]{i,j});
                    }
                }
                map[i][j] = input;

            }
        }

        System.out.println(new BOJ14502().solution());

    }
}
