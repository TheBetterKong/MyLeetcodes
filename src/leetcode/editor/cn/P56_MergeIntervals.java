/******************************* Java：合并区间 *******************************/
// 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返
// 回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
//
//
// 示例 1： 
//
// 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
// 输出：[[1,6],[8,10],[15,18]]
// 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
// 
//
// 示例 2： 
//
// 输入：intervals = [[1,4],[4,5]]
// 输出：[[1,5]]
// 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
//
// 
//
// 提示： 
//
// 1 <= intervals.length <= 104 
// intervals[i].length == 2 
// 0 <= starti <= endi <= 104 
// 
// Related Topics 排序 数组 
// 👍 929 👎 0


/******************************* 题目思路 *******************************/
// 要合并重叠区间：
//      ① 按各区间左端点排序，那么可以合并的区间一定连续；
//      ② 接下里，遍历数组，挨个合并至新的 ans 数组：
//              如果当前区间的左端点在 ans 数组中最后一个区间的右端点之后，那么它们不会重合，可以直接将这个区间加入数组 ans 的末尾；
//              否则，它们重合，我们需要用当前区间的右端点更新数组 merged 中最后一个区间的右端点，将其置为二者的较大值。

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class P56_MergeIntervals {
    public static void main(String[] args) {
        Solution solution = new P56_MergeIntervals().new Solution();
        // TO TEST
        int[][] intervals = {{1,3},{2,6},{8,10},{15,18}};
        int[][] answer = solution.merge(intervals);
        System.out.println(Arrays.deepToString(answer));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[][] merge(int[][] intervals) {
            if (intervals.length == 0) {
                return new int[0][2];
            }
            Arrays.sort(intervals, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[0] - o2[0];
                }
            });
            List<int[]> ans = new ArrayList<int[]>();

            for (int i = 0; i < intervals.length; i++) {
                int left = intervals[i][0], right = intervals[i][1];
                if (ans.size() == 0 || ans.get(ans.size() - 1)[1] < left) {
                    ans.add(new int[]{left, right});
                } else {
                    ans.get(ans.size() - 1)[1] = Math.max(ans.get(ans.size() - 1)[1], right);
                }
            }

            return ans.toArray(new int[ans.size()][]);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度：O(n log n），它是来自排序的开销，算法实际上只需要一次遍历
// 空间复杂度：O(log n)，来自于排序的空间开销