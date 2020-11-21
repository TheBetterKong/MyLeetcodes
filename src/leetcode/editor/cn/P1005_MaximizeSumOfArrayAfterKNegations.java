/******************************* Java：K 次取反后最大化的数组和 *******************************/
// 给定一个整数数组 A，我们只能用以下方法修改该数组：我们选择某个索引 i 并将 A[i] 替换为 -A[i]，然后总共重复这个过程 K 次。（我们可以多次选
// 择同一个索引 i。）
//
// 以这种方式修改数组后，返回数组可能的最大和。 
//
// 
//
// 示例 1： 
//
// 输入：A = [4,2,3], K = 1
// 输出：5
// 解释：选择索引 (1,) ，然后 A 变为 [4,-2,3]。
// 
//
// 示例 2： 
//
// 输入：A = [3,-1,0,2], K = 3
// 输出：6
// 解释：选择索引 (1, 2, 2) ，然后 A 变为 [3,1,0,2]。
// 
//
// 示例 3： 
//
// 输入：A = [2,-3,-1,5,-4], K = 2
// 输出：13
// 解释：选择索引 (1, 4) ，然后 A 变为 [2,3,-1,5,4]。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= A.length <= 10000 
// 1 <= K <= 10000 
// -100 <= A[i] <= 100 
// 
// Related Topics 贪心算法 
// 👍 65 👎 0


/******************************* 题目思路 *******************************/
// 最简单的方法：直接对数组求和后，去最小的 K 个数取反，然后求和即可
// 但是排序算法比较耗时，这里可以用桶排序的思想：
//      直接构造一个 numbers 数组来存放各个数字出现的次数，然后就可以避免了排序操作。

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

class P1005_MaximizeSumOfArrayAfterKNegations {
    public static void main(String[] args) {
        Solution solution = new P1005_MaximizeSumOfArrayAfterKNegations().new Solution();
        // TO TEST
        int[] A = {2,-3,-1,5,-4};
        int K = 2;
        int answer = solution.largestSumAfterKNegations(A, K);
        System.out.println(answer);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int largestSumAfterKNegations(int[] A, int K) {
            int[] numbers = new int[201];

            // 将 A 里的数转存到 numbers 里
            //      注意，不能有负数，因此整体右移 100
            for (int i : A) {
                numbers[i+100] ++;
            }

            int i = 0;
            // 将 numbers 里最小的 K 个数取反
            while (K > 0) {
                // 先找到最小的数
                while (numbers[i] == 0) {
                    i++;
                }
                // 取反
                numbers[i]--;
                numbers[200-i]++;
                // 如果现在最小数为正数（索引超过 100），取反后新的最小数为负数（索引为 200 - i）
                if (i > 100) {
                    i = 200 - i;
                }
                K--;
            }

            // 操作完成后，求和
            int sum = 0;
            for (int j = i; j < numbers.length ; j++) {
                sum += (j-100) * numbers[j];  // 不要忘了，可能有些数会多次出现
            }
            return sum;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 请主要从时间和空间复杂度的角度，对算法性能分析
// ......
