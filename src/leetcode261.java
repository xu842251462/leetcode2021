import java.util.Arrays;

public class leetcode261 {
    public static boolean validTree(int n, int[][] edges) {
        DisjointSet disjointSet = new DisjointSet(n);
        for (int[] edge : edges) {
            if (!disjointSet.union(edge[0], edge[1])) {
                return false;
            }
        }
        return disjointSet.getCount() == 1;
    }

    public static void main(String[] args) {
        int n = 5;
        int[][] edges = {{0,0},{2,2},{3,10},{5,2},{7,0}};
        boolean res = validTree(n, edges);
        System.out.println(res);
    }
}

class DisjointSet {
    int n;
    int[] parent;
    int[] rank;
    int count;

    DisjointSet(int n) {
        this.n = n;
        this.parent = new int[n];
        Arrays.fill(parent, -1);
        this.rank = new int[n];
        this.count = n;
    }


    private int findRoot(int x) {
        int root = x;
        while (parent[root] != -1) {
            root = parent[root];
        }
        return root;
    }

    public boolean union(int x, int y) {
        int xRoot = findRoot(x);
        int yRoot = findRoot(y);
        if (xRoot == yRoot) {
            return false;
        }
        if(rank[xRoot] > rank[yRoot]) {
            parent[yRoot] = xRoot;
        } else if (rank[xRoot] < rank[yRoot]) {
            parent[xRoot] = yRoot;
        } else if (rank[xRoot] == rank[yRoot]) {
            parent[xRoot] = yRoot;
            rank[yRoot]++;
        }
        //
        count--;
        return true;
    }

    public int getCount() {
        return this.count;
    }
}


