/******************************* Java：根据数字二进制下 1 的数目排序 *******************************/
// 给你一个整数数组 arr 。请你将数组中的元素按照其二进制表示中数字 1 的数目升序排序。
//
// 如果存在多个数字二进制中 1 的数目相同，则必须将它们按照数值大小升序排列。 
//
// 请你返回排序后的数组。 
//
// 
//
// 示例 1： 
//
// 输入：arr = [0,1,2,3,4,5,6,7,8]
// 输出：[0,1,2,4,8,3,5,6,7]
// 解释：[0] 是唯一一个有 0 个 1 的数。
// [1,2,4,8] 都有 1 个 1 。
// [3,5,6] 有 2 个 1 。
// [7] 有 3 个 1 。
// 按照 1 的个数排序得到的结果数组为 [0,1,2,4,8,3,5,6,7]
// 
//
// 示例 2： 
//
// 输入：arr = [1024,512,256,128,64,32,16,8,4,2,1]
// 输出：[1,2,4,8,16,32,64,128,256,512,1024]
// 解释：数组中所有整数二进制下都只有 1 个 1 ，所以你需要按照数值大小将它们排序。
// 
//
// 示例 3： 
//
// 输入：arr = [10000,10000]
// 输出：[10000,10000]
// 
//
// 示例 4： 
//
// 输入：arr = [2,3,5,7,11,13,17,19]
// 输出：[2,3,5,17,7,11,13,19]
// 
//
// 示例 5： 
//
// 输入：arr = [10,100,1000,10000]
// 输出：[10,100,10000,1000]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= arr.length <= 500 
// 0 <= arr[i] <= 10^4 
// 
// Related Topics 排序 位运算 
// 👍 91 👎 0


/******************************* 题目思路 *******************************/
// 二进制中 1 的个数，有如下递归关系：
//      cn[i+1] = cn[i>>1] + (i&1)，相当于前面的串中 1 的个数加上最后一位是否为 1；
// 题目给了 0 <= arr[i] <= 10^4，那就想递归求出所有的 cn[i]，然后再在这个基础上作比较排序；

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

import java.util.*;

class P1356_SortIntegersByTheNumberOf1Bits {
    public static void main(String[] args) {
        Solution solution = new P1356_SortIntegersByTheNumberOf1Bits().new Solution();
        // TO TEST
        int[] arr = {2,3,5,7,11,13,17,19};
        int[] answer = solution.sortByBits(arr);
        System.out.println(Arrays.toString(answer));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] sortByBits(int[] arr) {
            // 初始化各个数里 1 出现的次数
            int[] bit = new int[10001];     // java 初始值全为 0
            for (int i = 1; i < 10001; i++) {
                bit[i] = bit[i >> 1] + (i & 1);
            }

            // 重写 Arraylist 的排序器
            List<Integer> list = new ArrayList<Integer>();
            for (int x : arr) {
                list.add(x);
            }
            Collections.sort(list, (o1, o2) -> {
                if (bit[o1] != bit[o2]) {
                    return bit[o1] - bit[o2];
                } else {
                    return o1 - o2;
                }
            });

            // 转换为 数组
            for (int i = 0; i < arr.length; i++) {
                arr[i] = list.get(i);
            }
            return arr;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/