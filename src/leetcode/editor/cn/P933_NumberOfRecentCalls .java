/******************************* Java：最近的请求次数 *******************************/
// 写一个 RecentCounter 类来计算特定时间范围内最近的请求。
//
// 请你实现 RecentCounter 类： 
//
// 
// RecentCounter() 初始化计数器，请求数为 0 。 
// int ping(int t) 在时间 t 添加一个新请求，其中 t 表示以毫秒为单位的某个时间，并返回过去 3000 毫秒内发生的所有请求数（包括新请求
// ）。确切地说，返回在 [t-3000, t] 内发生的请求数。
// 
//
// 保证每次对 ping 的调用都使用比之前更大的 t 值。 
//
// 
//
// 示例 1： 
//
// 输入：
// ["RecentCounter", "ping", "ping", "ping", "ping"]
// [[], [1], [100], [3001], [3002]]
// 输出：
// [null, 1, 2, 3, 3]
//
// 解释：
// RecentCounter recentCounter = new RecentCounter();
// recentCounter.ping(1);     // requests = [1]，范围是 [-2999,1]，返回 1
// recentCounter.ping(100);   // requests = [<u>1</u>, <u>100</u>]，范围是 [-2900,100]，返回 2
// recentCounter.ping(3001);  // requests = [<u>1</u>, <u>100</u>, <u>3001</u>]，范围是 [1,3001]，返回 3
// recentCounter.ping(3002);  // requests = [1, <u>100</u>, <u>3001</u>, <u>3002</u>]，范围是 [2,3002]，返回 3
// 
//
// 
//
// 提示： 
//
// 
// 1 <= t <= 104 
// 保证每次对 ping 的调用都使用比之前更大的 t 值 
// 至多调用 ping 方法 104 次 
// 
// Related Topics 队列 
// 👍 68 👎 0


/******************************* 题目思路 *******************************/
// 看到这个题，我是真的没看懂，哭 o(╥﹏╥)o，翻看评论，发现一个特别直观的解释：
//      “ 这道题说人话就是：t 代表这个员工的工号，每次新员工 t 加入 q 公司前先把工号小于 t-3000 的老家伙都辞退，
//      然后再让 t 入职，统计 q 公司现有几个员工”
// 再来一个别人的理解：
//      “每ping()一次，把当前时间t存起来，同时要返回[t - 3000, t]这个区间(左闭右闭)里ping()过的个数有多少个。”
// 总算是懂了，那实现起来自然就简单了，加个队列即可。

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

import java.util.LinkedList;
import java.util.Queue;

class P933_NumberOfRecentCalls {
    public static void main(String[] args) {
        // Solution solution = new P933_NumberOfRecentCalls().new Solution();
        // TO TEST
        RecentCounter exam = new RecentCounter();
        int num1 = exam.ping(1);
        System.out.println(num1);
        int num100 = exam.ping(100);
        System.out.println(num100);
        int num3001 = exam.ping(3001);
        System.out.println(num3001);
        int num3002 = exam.ping(3002);
        System.out.println(num3002);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    static class RecentCounter {
    // class RecentCounter {
        private Queue<Integer> queue;

        public RecentCounter() {
            queue = new LinkedList<Integer>();
        }

        public int ping(int t) {
            queue.offer(t);
            int min = t-3000;
            while(queue.peek() < min){
                queue.poll();
            }
            return queue.size();
        }
    }

    /**
     * Your RecentCounter object will be instantiated and called as such:
     * RecentCounter obj = new RecentCounter();
     * int param_1 = obj.ping(t);
     */
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度：O(Q)，其中 Q 是 ping 的次数。
// 空间复杂度：O(W)，其中 W = 3000 是队列中最多存储的 ping 的记录数目。