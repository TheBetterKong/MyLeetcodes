/******************************* Java：杨辉三角 *******************************/
//给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。 
//
// 
//
// 在杨辉三角中，每个数是它左上方和右上方的数的和。 
//
// 示例: 
//
// 输入: 5
// 输出:
// [
//      [1],
//     [1,1],
//    [1,2,1],
//   [1,3,3,1],
//  [1,4,6,4,1]
// ]
// Related Topics 数组 
// 👍 351 👎 0


/******************************* 题目思路 *******************************/
// 本题并没有什么特殊的思想，主要就是缕清杨辉三角的生成逻辑，一行一行的生成

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class P118_PascalsTriangle {
    public static void main(String[] args) {
        Solution solution = new P118_PascalsTriangle().new Solution();
        // TO TEST
        int numRows = 7;
        List<List<Integer>> answer = solution.generate(numRows);
        System.out.println(answer);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> generate(int numRows) {
            List<List<Integer>> ans = new ArrayList<List<Integer>>();

            if(numRows == 0) return ans;

            // 起始时，初始化第一行
            ans.add(new ArrayList<>());
            ans.get(0).add(1);
            // 每一次迭代新增一行
            for(int i = 1; i < numRows; i++){
                ans.add(new ArrayList<>());
                // 每一行的起始元素总为 1
                ans.get(i).add(1);
                // 从第二个元素开始是由上一行的相应元素决定，这种元素个数为 i-1
                for(int j = 0; j < i-1; j++){
                    ans.get(i).add( ans.get(i-1).get(j) + ans.get(i-1).get(j+1));
                }
                // 每一行的最后一个元素也总为 1
                ans.get(i).add(1);
            }
            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度 O(n^2)