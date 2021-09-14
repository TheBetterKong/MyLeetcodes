/******************************* Java：找出迷宫出口的路径 *******************************/
// 给你一个整数数组 maze ，找到从入口 (0,0) 到出口 (n-1, n-1)  的路径。
// 其中，maze[i][j] == 0，表示该点可到；maze[i][j] == 1，表示该点为墙，不可走；
//
// 示例：
//
// 输入：nums = [
//                  [0, 0, 1, 1, 1],
//                  [1, 0, 1, 1, 0],
//                  [1, 0, 0, 1, 0],
//                  [1, 1, 0, 0, 0],
//                  [1, 1, 1, 1, 0]
//             ]
// 输出：9

/******************************* 题目思路 *******************************/
// 试一试变形为求最短路径

/******************************* 题目代码 *******************************/

package leetcode.editor.cn.myproblems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class P6_PathInMaze {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<List<Integer>> areas = new ArrayList<>();

        List<String> list1 = Arrays.asList(input.nextLine().split(" "));
        List<Integer> l1 = list1.stream().map(Integer::parseInt).collect(Collectors.toList());
        areas.add(l1);

        int n = l1.size();
        for (int i = 0; i < n - 1; i++) {
            List<String> listi = Arrays.asList(input.nextLine().split(" "));
            List<Integer> li = listi.stream().map(Integer::parseInt).collect(Collectors.toList());
            areas.add(li);
        }

        int[][] maze = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                maze[i][j] = areas.get(i).get(j);
            }
        }
        // System.out.println(Arrays.deepToString(areas));

        int[] start = {0, 0};
        int[] end = {maze.length - 1, maze.length - 1};
        P6_PathInMaze cls = new P6_PathInMaze();
        Boolean flag = cls.findShortestPath(maze, start, end);
        ArrayList<List> path = cls.getPath();
        System.out.println(flag);
        System.out.println(path);
    }

    // 记录路径
    ArrayList<List> path = new ArrayList<>();
    public ArrayList<List> getPath() {
        return path;
    }

    public void addPoiont(int[] point) {
        List<Integer> p = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            p.add(point[i]);
        }
        path.add(p);
    }

    // 标记该路径已走
    public void mark(int[][] maze, int[] start) {
        maze[start[0]][start[1]] = 2;
    }

    public boolean findShortestPath(int[][] maze, int[] start, int[] end) {
        mark(maze, start);

        // 到达终点
        if (start[0] == end[0] && start[1] == end[1]) {
            addPoiont(start);
            return true;
        }

        int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int i = 0; i < 4; i++) {
            // 四个方向行走的下一个位置
            int[] next = new int[2];
            next[0] = start[0] + direction[i][0];
            next[1] = start[1] + direction[i][1];
            // 判断该点能否到达
            if (next[0] >= 0 && next[0] < maze.length && next[1] >= 0 && next[1] < maze.length) { // 未出界
                if (maze[next[0]][next[1]] == 0) {    // 位置可达
                    if (findShortestPath(maze, next, end)) {
                        addPoiont(start);
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
