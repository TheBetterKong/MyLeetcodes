/******************************* Java：最长公共前缀 *******************************/
// 编写一个函数来查找字符串数组中的最长公共前缀。
//
// 如果不存在公共前缀，返回空字符串 ""。 
//
// 示例 1: 
//
// 输入: ["flower","flow","flight"]
// 输出: "fl"
// 
//
// 示例 2: 
//
// 输入: ["dog","racecar","car"]
// 输出: ""
// 解释: 输入不存在公共前缀。
// 
//
// 说明: 
//
// 所有输入只包含小写字母 a-z 。 
// Related Topics 字符串 
// 👍 1283 👎 0


/******************************* 题目思路 *******************************/
// 两种常见的思路:
// （1）横向：先求两个字符串的最长公共前缀，再以这个公共前缀和第三个字符串比较求新的公共前缀... 以此类推，在比较完所有字符串后的公共前缀即为所求
// （2）纵向：将所有字符串，从第一个字符匹配起，依次向后进行，直到某个字符串的字符出现不匹配，那么前面匹配完成的字符即为公共前缀。

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

class P14_LongestCommonPrefix {
    public static void main(String[] args) {
        Solution solution = new P14_LongestCommonPrefix().new Solution();
        // TO TEST
        String []strs = {"flower","flow","flight"};
        String answer = solution.longestCommonPrefix(strs);
        System.out.println(answer);
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length == 0) return "";
        int length = strs[0].length();
        int count = strs.length;
        for(int i = 0; i < length; i++){
            char m = strs[0].charAt(i);
            for(int j = 0; j < count; j++){
                if (i == strs[j].length() || strs[j].charAt(i) != m){
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度 O(m*n)
// 空间复杂度 O(1)