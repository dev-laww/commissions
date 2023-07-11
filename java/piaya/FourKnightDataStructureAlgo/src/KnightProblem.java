import java.util.*;

public class KnightProblem {
    static final int ROWS = 8;
    static final int COLS = 8;
    static final int NUM_SQUARES = ROWS * COLS;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Knight Position: ");
        int knightPosition = in.nextInt();

        System.out.print("Target Position: ");
        int target = in.nextInt();

        System.out.print("Moves: ");
        List<Integer> moves = getPath(knightPosition, target);
        for(int move: moves){
            System.out.print(move + " ");
        }
        System.out.println();
    }

    static List<Integer> getNeighbors(int pos){
        int row = getRow(pos), col = getCol(pos);

        int[][] neighborhood = {
                {row - 2,col + 1},{row - 1,col + 2},
                {row + 1,col + 2},{row + 2,col + 1},
                {row + 2,col - 1},{row + 1,col - 2},
                {row - 1,col - 2},{row - 2,col - 1}
        };

        List<Integer> neighbors = new ArrayList<>();
        for(int[] n: neighborhood){
            int neighborRow = n[0];
            int neighborCol = n[1];
            if(( neighborRow >= 0 && neighborRow < ROWS) &&
                    (neighborCol >= 0 && neighborCol < COLS)){

                neighbors.add(getPos(neighborRow, neighborCol));

            }
        }
        return neighbors;
    }

    static int getRow(int pos){
        return pos / ROWS;
    }

    static int getCol(int pos){
        return pos % ROWS;
    }

    static int getPos(int row, int col){
        return row * ROWS + col;
    }

    static List<Integer> getPath(int s, int t){
        int[] parent = bfs(s);
        List<Integer> path = new LinkedList<>();

        if(parent[t] != -1){
            int cur = t;
            path.add(0,cur);
            int p = parent[cur];
            while (cur != s){
                cur = p;
                path.add(0,cur);
                p = parent[cur];
            }
        }
        return path;
    }

    static int[] bfs(int s){
        int[] parent = new int[NUM_SQUARES];

        for(int i = 0; i< parent.length; i++){
            parent[i] = -1;
        }

        parent[s] = s;
        List<Integer> frontier = new LinkedList<>();

        frontier.add(s);
        while(!frontier.isEmpty()){
            int cur = frontier.remove(0);
            for(int v: getNeighbors(cur)){
                if(parent[v] == -1){
                    parent[v] = cur;
                    frontier.add(v);
                }
            }
        }

        return parent;
    }
}
