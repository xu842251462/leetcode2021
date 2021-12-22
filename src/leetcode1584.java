import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
//why use two array: fa and rank
//how union operate the fa and rank
//how find works
public class leetcode1584 {
    /**
     * 不清楚并查集、路径压缩、按秩合并的请先自行学习 https://zhuanlan.zhihu.com/p/93647900/
     */
    int[] fa;
    int[] rank;
    public int minCostConnectPoints(int[][] points) {
        int pointCount = points.length;
        if (pointCount < 2){
            return 0;
        }
        //计算每两点之间(一条边)的距离, 加入列表中
        List<Edge> allEdgeList = new ArrayList<>();
        for (int i = 0; i < pointCount - 1; i++) {
            for (int j = i + 1; j < pointCount; j++) {
                //求点i和点j组成的边的长度
                int distance = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                allEdgeList.add(new Edge(i, j, distance));
            }
        }
        //按照边的长度升序排序
        allEdgeList.sort(Comparator.comparingInt(o -> o.len));
        //遍历所有边, 将未连接点的边长度相加, 得到结果.
        //初始化
        fa = new int[pointCount];
        rank = new int[pointCount];
        for (int i = 0; i < pointCount; i++) {
            fa[i] = i;
            rank[i] = 1;
        }
        //总长度
        int minCost = 0;
        //累计合并点数, 初始值为1
        int mergePointCount = 1;
        for (Edge edge : allEdgeList) {
            //求出两个点的根节点, 判断是否可以完成合并, true表示可以合并并进行了合并操作(但是还没累计长度)
            int point1Root = find(edge.point1);
            int point2Root = find(edge.point2);
            if (union(point1Root, point2Root)){
                minCost += edge.len;
                mergePointCount++;
                //已连接所有点, 结束循环
                if (mergePointCount == pointCount){
                    break;
                }
            }
        }
        return minCost;
    }

    /**
     * 合并
     */
    public boolean union(int x, int y) {
        if (x == y){
            return false;
        }
        if (rank[x] < rank[y]){
            fa[x] = y;
        } else if (rank[x] > rank[y]){
            fa[y] = x;
        } else {
            fa[x] = y;
            rank[y] += 1;
        }
        return true;
    }

    /**
     * 压缩路径查找
     */
    public int find(int x){
        return fa[x] == x ? x : (fa[x] = find(fa[x]));
    }

    /**
     * 边
     */
    class Edge {
        int point1;
        int point2;
        int len;
        public Edge(int point1, int point2, int len) {
            this.point1 = point1;
            this.point2 = point2;
            this.len = len;
        }
    }


    public static void main(String[] args) {
        leetcode1584 test = new leetcode1584();
        int[][] point = {{0,0},{2,2},{3,10},{5,2},{7,0}};
        System.out.println(test.minCostConnectPoints(point));
    }
}
