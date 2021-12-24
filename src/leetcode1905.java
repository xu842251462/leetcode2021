public class leetcode1905 {
    //traverse grid2, check if there is corresponding 1 in the grid1
    boolean isContain = true;
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int res = 0;
        for(int i = 0; i < grid2.length; i++) {
            for(int j = 0; j < grid2[0].length; j++) {
                if(grid2[i][j] == 1) {
                    dfs(grid1, grid2, i, j);
                    if(isContain) {
                        res++;
                    } else {
                        isContain = true;
                    }
                }
            }
        }
        return res;
    }

    private void dfs(int[][] grid1, int[][] grid2, int i, int j) {
        //set boundaries for the gird
        if(i < 0 || i >= grid2.length || j < 0 || j >= grid2[0].length) {
            return;
        }
        //in the water
        if(grid2[i][j] == 0) {
            return;
        }
        //check grid1
        if(grid1[i][j] != 1) {
            isContain = false;
        }
        //
        grid2[i][j] = 0;
        //set boundary for search loop
        int[][] next = new int[][]{{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        //main method for traverse in grid2
        for (int k = 0; k < next.length; k++) {
            dfs(grid1, grid2, i + next[k][0], j + next[k][1]);
        }
    }

    public static void main(String[] args) {
        leetcode1905 test = new leetcode1905();
        int[][] grid1 = new int[][]{{1,1,1,0,0},{0,1,1,1,1},{0,0,0,0,0},{1,0,0,0,0},{1,1,0,1,1}};
        int[][] grid2 = new int[][]{{1,1,1,0,0},{0,0,1,1,1},{0,1,0,0,0},{1,0,1,1,0},{0,1,0,1,0}};
        int res = test.countSubIslands(grid1, grid2);
        System.out.println(res);
    }
}
