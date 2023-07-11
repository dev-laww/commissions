import java.util.*;


class Knight {
    public String name;
    public int position;

    public Knight(String name, int position){
        this.name = name;
        this.position = position;
    }
}

public class Main {
    private static final int ROWS = 8;
    private static final int COLS = 8;

    private static List<Integer> getNeighbors(int pos) {
        int row = getRow(pos), col = getCol(pos);

        int[][] neighborhood = {
                {row - 2, col + 1}, {row - 1, col + 2},
                {row + 1, col + 2}, {row + 2, col + 1},
                {row + 2, col - 1}, {row + 1, col - 2},
                {row - 1, col - 2}, {row - 2, col - 1}
        };

        List<Integer> neighbors = new ArrayList<>();
        for (int[] n : neighborhood) {
            int neighborRow = n[0];
            int neighborCol = n[1];
            if ((neighborRow >= 0 && neighborRow < ROWS) &&
                    (neighborCol >= 0 && neighborCol < COLS)) {

                neighbors.add(getPos(neighborRow, neighborCol));

            }
        }
        return neighbors;
    }

    private static int getRow(int pos) {
        return pos / ROWS;
    }

    private static int getCol(int pos) {
        return pos % ROWS;
    }

    private static int getPos(int row, int col) {
        return row * ROWS + col;
    }

//    private List<Integer> getPath(int s, int t){
//        int[] parent = bfs(s);
//        List<Integer> path = new LinkedList<>();
//
//        if(parent[t] != -1){
//            int cur = t;
//            path.add(0,cur);
//            int p = parent[cur];
//            while (cur != s){
//                cur = p;
//                path.add(0,cur);
//                p = parent[cur];
//            }
//        }
//        return path;
//    }

    // Make a method that returns a list of all possible paths from s to t
    private static List<List<Integer>> getPaths(int[] s, int t) {
        List<int[]> parents = bfs(s);

        List<List<Integer>> paths = new ArrayList<>();

        for (int i = 0; i < parents.size(); i++) {
            List<Integer> path = new LinkedList<>();
            int[] parent = parents.get(i);

            if (parent[t] != -1) {
                int cur = t;
                path.add(0, cur);
                int p = parent[cur];
                while (cur != s[i]) {
                    cur = p;
                    path.add(0, cur);
                    p = parent[cur];
                }
            }

            paths.add(path);
        }

        return paths;
    }

    private static List<int[]> bfs(int[] s) {
        List<int[]> parents = new ArrayList<>();

        for (int start : s) {
            int NUM_SQUARES = ROWS * COLS;
            int[] parent = new int[NUM_SQUARES];

            for (int i = 0; i < parent.length; i++) {
                parent[i] = -1;
            }

            parent[start] = start;
            List<Integer> frontier = new LinkedList<>();

            frontier.add(start);

            while (!frontier.isEmpty()) {
                int cur = frontier.remove(0);
                for (int v : getNeighbors(cur)) {
                    if (parent[v] == -1) {
                        parent[v] = cur;
                        frontier.add(v);
                    }
                }
            }

            parents.add(parent);
        }

        return parents;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the knights' positions (0-63) seperated by spaces (blue, green, orange, yellow, flag): ");

        String input = in.nextLine();
        in.close();

        String[] positions = input.split(" ");

        try {
            if (positions.length != 5) {
                throw new NumberFormatException();
            }

            for (String s : positions) {
                int position = Integer.parseInt(s);
                if (position < 0 || position > 63) {
                    throw new NumberFormatException();
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input");
            System.exit(0);
        }

        Knight[] knights = {
                new Knight("B", Integer.parseInt(positions[0])),
                new Knight("G", Integer.parseInt(positions[1])),
                new Knight("O", Integer.parseInt(positions[2])),
                new Knight("Y", Integer.parseInt(positions[3])),
        };

        int flag = Integer.parseInt(positions[4]);

        int[] startingPositions = new int[4];
        for (int i = 0; i < startingPositions.length; i++) {
            startingPositions[i] = knights[i].position;
        }

        List<List<Integer>> paths = getPaths(startingPositions, flag);

        for (List<Integer> path : paths) {
            System.out.print("Knight " + knights[paths.indexOf(path)].name + ": ");
            for (int move : path) {
                System.out.print(move + " ");
            }
            System.out.println();
        }

        // get the shortest path and print the knight's name and path
        List<Integer> shortestPath = paths.get(0);

        for (List<Integer> path : paths) {
            if (path.size() < shortestPath.size()) {
                shortestPath = path;
            }
        }

        System.out.print("\nShortest path: ");
        for (int move : shortestPath) {
            System.out.print(move + " ");
        }
        System.out.println();

        System.out.print(knights[paths.indexOf(shortestPath)].name);
    }
}