/******************************* Java：验证回文串 *******************************/
// 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
//
// 说明：本题中，我们将空字符串定义为有效的回文串。 
//
// 示例 1: 
//
// 输入: "A man, a plan, a canal: Panama"
// 输出: true
// 
//
// 示例 2: 
//
// 输入: "race a car"
// 输出: false
// 
// Related Topics 双指针 字符串 
// 👍 280 👎 0


/******************************* 题目思路 *******************************/
// 首先，得说一下什么是回文串：
//      回文串是一个中心对称的字符串，在上面的例子中，删掉 "A man, a plan, a canal: Panama" 里的空格和特殊字符
//      剩下 "AmanaplanacanalPanama"，在不考虑大小写的情况下，它是关于字符 "c" 左右对称的，这就是回文串。
// 知道上述定义后，最直接的思路就是根据定义来写算法，分为两步：
//      1. 去除字符串中无关字符；
//      2. 判断剩下的字符串是否是中心对称的；
//      判断字符串是否中心对称很简单：
//          1. 判断字符串和它的反转是否相等；
//          2. 利用双指针，左右同时遍历判断；
// 但是上述实现有一个问题，第一步去除无关字符的过程中，我们需要改变字符串，需要引入 stringbuffer 带来了额外的空间
// 开销，而且比较麻烦。所以，我们转变方式，利用双指针直接在原字符串上进行遍历实现。

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

class P125_ValidPalindrome {
    public static void main(String[] args) {
        Solution solution = new P125_ValidPalindrome().new Solution();
        // TO TEST
        String s = new String("A man, a plan, a canal: Panama");
        boolean answer = solution.isPalindrome(s);
        System.out.print(answer);
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) return true;

        int n = s.length();
        int left = 0 , right = n-1;
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            if(left < right) {
                if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                    return false;
                }
                ++left;
                --right;
            }
        }

        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度 O(n)
// 空间复杂度 O(1)