/******************************* Java：模拟行走机器人 *******************************/
// 机器人在一个无限大小的网格上行走，从点 (0, 0) 处开始出发，面向北方。该机器人可以接收以下三种类型的命令：
//
// 
// -2：向左转 90 度 
// -1：向右转 90 度 
// 1 <= x <= 9：向前移动 x 个单位长度 
// 
//
// 在网格上有一些格子被视为障碍物。 
//
// 第 i 个障碍物位于网格点 (obstacles[i][0], obstacles[i][1]) 
//
// 机器人无法走到障碍物上，它将会停留在障碍物的前一个网格方块上，但仍然可以继续该路线的其余部分。 
//
// 返回从原点到机器人所有经过的路径点（坐标为整数）的最大欧式距离的平方。 
//
// 
//
// 示例 1： 
//
// 输入: commands = [4,-1,3], obstacles = []
// 输出: 25
// 解释: 机器人将会到达 (3, 4)
// 
//
// 示例 2： 
//
// 输入: commands = [4,-1,4,-2,4], obstacles = [[2,4]]
// 输出: 65
// 解释: 机器人在左转走到 (1, 8) 之前将被困在 (1, 4) 处
// 
//
// 
//
// 提示： 
//
// 
// 0 <= commands.length <= 10000 
// 0 <= obstacles.length <= 10000 
// -30000 <= obstacle[i][0] <= 30000 
// -30000 <= obstacle[i][1] <= 30000 
// 答案保证小于 2 ^ 31 
// 
// Related Topics 贪心算法 
// 👍 113 👎 0


/******************************* 题目思路 *******************************/
// 这题直接参照官方解析，在求路径的障碍点时，相当于做了一个 hash 映射



/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

class P874_WalkingRobotSimulation {
    public static void main(String[] args) {
        Solution solution = new P874_WalkingRobotSimulation().new Solution();
        // TO TEST
        int[] commands = {4,-1,4,-2,4};
        int[][] obstacles = {{2,4}};
        int answer = solution.robotSim(commands, obstacles);
        System.out.println(answer);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int robotSim(int[] commands, int[][] obstacles) {
            // 定义四个维度的向量，便于后面行走过程的统一
            int[] dx = new int[]{0, 1, 0, -1};
            int[] dy = new int[]{1, 0, -1, 0};
            int x = 0, y = 0, di = 0;


            // Encode obstacles (x, y) as (x+30000) * (2^16) + (y+30000)
            // 相当于做了一个 hash 映射
            //      +30000 是将横纵坐标都转换为正数
            //      ox，oy 的最大值为 60000，最多需要 16 位表示，所以 ox 左移 16 位 + oy 值一定唯一，相当于做了拼接
            //      为了避免溢出，定义为 long 类型，其位数为 64 位
            Set<Long> obstacleSet = new HashSet();
            for (int[] obstacle: obstacles) {
                long ox = (long) obstacle[0] + 30000;
                long oy = (long) obstacle[1] + 30000;
                obstacleSet.add((ox << 16) + oy);
            }


            // 机器人开始行走
            int ans = 0;
            for (int cmd: commands) {
                if (cmd == -2)          // turn left
                    di = (di + 3) % 4;
                else if (cmd == -1)     // turn right
                    di = (di + 1) % 4;
                else {
                    for (int k = 0; k < cmd; ++k) {
                        // 一步一步行走 (x,y) 是当前坐标，(nx,ny) 是走完后的坐标
                        int nx = x + dx[di];
                        int ny = y + dy[di];
                        long code = (((long) nx + 30000) << 16) + ((long) ny + 30000);  // hash 转换
                        if (!obstacleSet.contains(code)) {  // 遇到障碍就不走了
                            x = nx;
                            y = ny;
                            ans = Math.max(ans, x*x + y*y);
                        }
                    }
                }
            }

            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 请主要从时间和空间复杂度的角度，对算法性能分析
// ......
