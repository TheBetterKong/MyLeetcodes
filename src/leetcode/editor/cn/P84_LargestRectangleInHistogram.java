/******************************* Java：柱状图中最大的矩形 *******************************/
// 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
//
// 求在该柱状图中，能够勾勒出来的矩形的最大面积。 
//
//
//
// 以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。 
//
//
//
// 图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位 (5, 5)。
//
// 
//
// 示例: 
//
// 输入: [2,1,5,6,2,3]
// 输出: 10
// Related Topics 栈 数组 
// 👍 1365 👎 0


/******************************* 题目思路 *******************************/
// 没有思路时，暴力就是最好的思路；
// 回想之前做过的 T42，这种柱状图问题，无非就是两个维度的遍历方式：
//   ① 横向：遍历所有可能的宽，求对应的矩阵最小高度，从而求得最大面积
//         假设，宽度为 k，符合长度的宽可以在矩阵里挑选出 n-k 种，从宽为 k 的相邻元素求最小高度，需要 k 次遍历；
//         那么遍历所有可能的情况，总共需要 \sum^n_{k=1} k * (n-k) 次遍历
//         时间复杂度为 O(n^3)
//   ② 纵向：遍历所有可能的高，求对应的矩形最大宽，从而求得最大面积
//         假设元素 i 的高度为 k，从该元素向两侧扩展，找能达到该高度的矩形的最大宽：
//         能扩展的程度就是，该位置往左、往右最后一个不比 k 小的元素
//         此时的时间复杂度依旧为 O(n^3)
//
// 有了上面的思路，问题在于，怎么优化呢？
//
// 横向分析主要的时间开销来自于 “遍历”，所以优化的点应该在于遍历策略，而这往往没有体系化的思考方式，所以不太容易做到；
//
// 纵向分析主要的时间开销来自于 “求左右符合条件的值”，这是查找问题，有了问题，有了类型，要更优的办法解决，就有了思考点；
//      在 T42 中，是维护两个数组 leftmax[n] 和 rightmax[n]，来保存每个位置 i 左（右）边最大的数
//      现在，我们要找的是第一个不比它小的数的位置，我们也可以维护这样的两个数组 leftlow[n] 和 rightlow[n] 来记录每个位置 i 的该信息
//
//      这两个数组怎么求？“在一维数组中找第一个满足某种条件的数” 的场景就是典型的单调栈应用场景。
//          维护一个单调栈：里面的元素必须是递增关系，每次遍历到元素 i 时，必须递增才入栈，否则就出栈，直至元素 i 能入栈；
//          这种场景下，元素 i 入栈时，栈顶元素必然是 元素 i 左边第一个小于 i 的元素
//
//      首先从左往右对数组进行遍历，借助单调栈可求出每根柱子的左边界，随后从右往左对数组进行遍历，借助单调栈求出每根柱子的右边界。
//      那么我们是否可以只遍历一次就求出答案呢？
//          我们在对位置 i 进行入栈操作时，确定了它的左边界。从直觉上来说，与之对应的我们在对位置 i 进行出栈操作时可以确定它的右边界；
//              解释：
//                  当位置 i 被弹出栈时，说明此时遍历到的位置 i0 的高度小于等于 height[i]，并且在 i0 与 i 之间没有其他高度小于等于 height[i] 的柱子。
//                  这是因为，如果在 i 和 i0 之间还有其它位置的高度小于等于 height[i] 的，那么在遍历到那个位置的时候，i 应该已经被弹出栈了。
//                  所以位置 i0 就是位置 i 的右边界。
//          我们需要的是「一根柱子的左侧且最近的小于其高度的柱子」，但这里我们求的是小于等于，那么会造成什么影响呢？
//                  答案是：我们确实无法求出正确的右边界，但对最终的答案没有任何影响。这是因为在答案对应的矩形中，如果有若干个柱子的高度都等于矩形的高度，
//                  那么最右侧的那根柱子是可以求出正确的右边界的，而我们没有对求出左边界的算法进行任何改动，因此最终的答案还是可以从最右侧的与矩形高度相同
//                  的柱子求得的。
//          在遍历结束后，栈中仍然有一些位置，这些位置对应的右边界就是位置为 n 的「哨兵」。我们可以将它们依次出栈并更新右边界，也可以在初始化右边界数组时就
//          将所有的元素的值置为 n。




/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

import java.util.Arrays;
import java.util.Map;
import java.util.Stack;

class P84_LargestRectangleInHistogram {
    public static void main(String[] args) {
        Solution solution = new P84_LargestRectangleInHistogram().new Solution();
        // TO TEST
        int[] heights = {2,1,5,6,2,3};
        int answer = solution.largestRectangleArea(heights);
        System.out.println(answer);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int largestRectangleArea(int[] heights) {
            int n = heights.length;
            int[] leftlow = new int[n];
            int[] rightlow = new int[n];
            Arrays.fill(rightlow, n);

            Stack<Integer> mono_stack = new Stack<Integer>();
            for (int i = 0; i < n; i++) {
                while (!mono_stack.isEmpty() && heights[mono_stack.peek()] >= heights[i]) {
                    rightlow[mono_stack.peek()] = i;
                    mono_stack.pop();
                }
                leftlow[i] = (mono_stack.isEmpty() ? -1 : mono_stack.peek());
                mono_stack.push(i);
            }

            int ans = 0;
            for (int i = 0; i < n; i++) {
                ans = Math.max(ans, (rightlow[i] - leftlow[i] - 1) * heights[i]);
            }
            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度：O(N)
// 空间复杂度：O(N)