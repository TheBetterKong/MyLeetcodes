/******************************* Java：数组的相对排序 *******************************/
// 给你两个数组，arr1 和 arr2，
//
// 
// arr2 中的元素各不相同 
// arr2 中的每个元素都出现在 arr1 中 
// 
//
// 对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。
//
// 
//
// 示例： 
//
// 输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
// 输出：[2,2,2,1,4,3,3,9,6,7,19]
// 
//
// 
//
// 提示： 
//
// 
// arr1.length, arr2.length <= 1000 
// 0 <= arr1[i], arr2[i] <= 1000 
// arr2 中的元素 arr2[i] 各不相同 
// arr2 中的每个元素 arr2[i] 都出现在 arr1 中 
// 
// Related Topics 排序 数组 
// 👍 85 👎 0


/******************************* 题目思路 *******************************/
// 分配排序（桶式排序）：
//      1. 先分配 arr1 的数组变成桶，
//      2. 然后按 arr2 的顺序收集桶中的数组，
//      3. 最后按升序顺序收集桶中剩余的数组。
// 这个方法主要是利用了题目里的范围 [0,1000]，桶的数目被限定在一个比较小的范围内

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

import java.util.Arrays;

class P1122_RelativeSortArray {
    public static void main(String[] args) {
        Solution solution = new P1122_RelativeSortArray().new Solution();
        // TO TEST
        int[] arr1 = {2,3,1,3,2,4,6,7,9,2,19};
        int[] arr2 = {2, 1, 4, 3, 9, 6};
        int[] answer = solution.relativeSortArray(arr1, arr2);
        System.out.println(Arrays.toString(answer));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] relativeSortArray(int[] arr1, int[] arr2) {
            int[] bucket = new int[1001];   // 元素取值范围 [0,1000]，最多 1001 个桶

            // 第一步：将 arr1 中数据分配到各桶中
            for (int num1 : arr1) {
                bucket[num1]++;
            }

            // 第二步：按照 arr2 的顺序收集桶中数据
            int i = 0;
            for (int num2 : arr2) {
                while (bucket[num2] > 0) {
                    arr1[i++] = num2;
                    bucket[num2]--;
                }
            }

            // 第三步：按升序顺序收集桶中其他数据
            for (int num1 = 0; num1 < bucket.length; num1++) {
                while (bucket[num1] > 0) {
                    arr1[i++] = num1;
                    bucket[num1]--;
                }
            }
            return arr1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度：
// 空间复杂度：