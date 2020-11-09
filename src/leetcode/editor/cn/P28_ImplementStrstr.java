/******************************* Java：实现 strStr() *******************************/
//实现 strStr() 函数。 
//
// 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如
// 果不存在，则返回 -1。
//
// 示例 1: 
//
// 输入: haystack = "hello", needle = "ll"
// 输出: 2
// 
//
// 示例 2: 
//
// 输入: haystack = "aaaaa", needle = "bba"
// 输出: -1
// 
//
// 说明: 
//
// 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。 
//
// 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。 
// Related Topics 双指针 字符串 
// 👍 580 👎 0


/******************************* 题目思路 *******************************/
// 典型的 KMP 算法题，准备在后面写篇 KMP 算法的详解，参见 Blog：http://www.thebetterkong.cn/

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;


class P28_ImplementStrstr {
    public static void main(String[] args) {
        Solution solution = new P28_ImplementStrstr().new Solution();
        // TO TEST
        String haystack = "aabaabaaf", needle = "aabaaf";
        int answer = solution.strStr(haystack, needle);
        System.out.println(answer);
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int strStr(String haystack, String needle) {
        if(needle == null || needle.length() == 0) return 0;

        // 首先构造 next 数组；
        int []next = new int[needle.length()];
        getNext(next,needle);

        // 利用 next 数组开始匹配
        int i = 0;
        int j = 0; // 模式串最初匹配位置
        for( ; i < haystack.length(); i++) {
            while (j > 0 && haystack.charAt(i) != needle.charAt(j)) { // 不匹配时，根据前缀表找到回退的位置
                j = next[j-1];
            }
            if (haystack.charAt(i) == needle.charAt(j)) { // 当前字符匹配成功，往后继续进行
                j++;
            }
            if(j == needle.length()) { // 匹配成功，返回成功位置
                return i+1-j;
            }
        }
        return -1; // 失败，则返回 -1
    }


    private void getNext(int []next, String needle) {
        int j = 0;       // 前缀末尾
        next[0] = j;    // 前缀表，第一项置 -1，表示到此结束回退
        for(int i = 1; i < needle.length(); i++) {
            while (j > 0 && needle.charAt(i) != needle.charAt(j)) {
                j = next[j-1];
            }
            if(needle.charAt(i) == needle.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// KMP 算法时间复杂度 O(m+n)
// 空间复杂度 O(m)
// m 为模式串的长度，n 为匹配串的长度