/******************************* Java：杨辉三角 II *******************************/
//给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。 
//
// 
//
// 在杨辉三角中，每个数是它左上方和右上方的数的和。 
//
// 示例: 
//
// 输入: 3
//输出: [1,3,3,1]
// 
//
// 进阶： 
//
// 你可以优化你的算法到 O(k) 空间复杂度吗？ 
// Related Topics 数组 
// 👍 181 👎 0


/******************************* 题目思路 *******************************/
// 本题我采用的也可以说是一种暴力解题法，按照杨辉三角的生成规律，直接循环生成第 k 行数据；
// 由于省去了中间值的保存，因此节省了相关的内存空间。但是时间复杂度较高。
//
// 网上有一种利用二项式定理的解题方案：杨辉三角的各项即 C_n^i，再找到 C_n^(k+1) = （n-k)/(k+1) * C_n^k，
// 这样就可以直接一次生成杨辉三角的各项，大大节省了时间。

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class P119_PascalsTriangleIi {
    public static void main(String[] args) {
        Solution solution = new P119_PascalsTriangleIi().new Solution();
        // TO TEST
        int rowIndex = 3;
        List ans = solution.getRow(rowIndex);
        System.out.println(ans);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<Integer> getRow(int rowIndex) {
            List<Integer> ans = new ArrayList<Integer>(rowIndex+1);

            for(int i = 0; i <= rowIndex; i++){
                ans.add(1);
                for(int j = i-1; j >0; j--){
                    ans.set(j, ans.get(j-1) + ans.get(j));
                }
            }
            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度 O(n^2)