package 프로그래머스.DFS_BFS;
import java.util.*;

public class 게임맵최단거리 {
    class Solution {

        //1. 최단거리 : bfs

        int answer = -1;

        public void bfs(int y, int x, int[][] maps, boolean[][] visited){
            int[] dy = new int[]{0,1,0,-1};
            int[] dx = new int[]{1,0,-1,0};

            Queue<int[]> q = new LinkedList<>();
            q.offer(new int[]{y,x,1});
            visited[0][0] = true;

            while(!q.isEmpty()){
                int[] pos = q.poll();

                for(int i = 0; i<4; i++){
                    int ny = pos[0]+dy[i];
                    int nx = pos[1]+dx[i];
                    int cnt = pos[2];

                    if(ny<0||nx<0||ny>=maps.length||nx>=maps[0].length){
                        continue;
                    }

                    if(ny==maps.length-1&&nx==maps[0].length-1){
                        answer = cnt+1;
                        return;
                    }

                    if(maps[ny][nx]==1&&!visited[ny][nx]){
                        visited[ny][nx] = true;
                        q.offer(new int[]{ny,nx,cnt+1});
                    }
                }
            }
        }

        public int solution(int[][] maps) {

            boolean[][] visited = new boolean[maps.length][maps[0].length];

            bfs(0,0,maps, visited);

            return answer;
        }
    }
}
