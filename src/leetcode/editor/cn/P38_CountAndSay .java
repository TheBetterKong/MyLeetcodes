/******************************* Java：外观数列 *******************************/
// 给定一个正整数 n（1 ≤ n ≤ 30），输出外观数列的第 n 项。
//
// 注意：整数序列中的每一项将表示为一个字符串。 
//
// 「外观数列」是一个整数序列，从数字 1 开始，序列中的每一项都是对前一项的描述。前五项如下： 
//
// 1.     1
// 2.     11
// 3.     21
// 4.     1211
// 5.     111221
// 
//
// 第一项是数字 1 
//
// 描述前一项，这个数是 1 即 “一个 1 ”，记作 11 
//
// 描述前一项，这个数是 11 即 “两个 1 ” ，记作 21 
//
// 描述前一项，这个数是 21 即 “一个 2 一个 1 ” ，记作 1211 
//
// 描述前一项，这个数是 1211 即 “一个 1 一个 2 两个 1 ” ，记作 111221 
//
// 
//
// 示例 1: 
//
// 输入: 1
// 输出: "1"
// 解释：这是一个基本样例。
//
// 示例 2: 
//
// 输入: 4
// 输出: "1211"
// 解释：当 n = 3 时，序列是 "21"，其中我们有 "2" 和 "1" 两组，"2" 可以读作 "12"，也就是出现频次 = 1 而 值 = 2；类似
// "1" 可以读作 "11"。所以答案是 "12" 和 "11" 组合在一起，也就是 "1211"。
// Related Topics 字符串 
// 👍 562 👎 0


/******************************* 题目思路 *******************************/
// 首先，本题的题意就属于很难理解。
// 题目的意思是：
//      对序列前一个数进行报数，数列第一项不是1吗；
//      那第二项就报第一项的有1个1，输出11；
//      然后第三项就在第二项的基础上报数，第二项是11，第三项不就是2个1么，然后输出21。。。
// 基于这种生成逻辑，自然而然就想到了递归算法。

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

class P38_CountAndSay {
    public static void main(String[] args) {
        Solution solution = new P38_CountAndSay().new Solution();
        // TO TEST
        int n = 4;
        String answer = solution.countAndSay(n);
        System.out.println(answer);
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String countAndSay(int n) {
        if(n == 1) return "1";
        else {
            String preStr = countAndSay(n-1);  // 对前一项的递归
            String res = "";
            char num = preStr.charAt(0);
            int count = 0;
            for (int i = 0; i < preStr.length(); i++) {
                if (preStr.charAt(i) != num){
                    // 记录某个字符出现次数后，写到字符串
                    res = res + String.valueOf(count) + num;
                    // 更新，进行新一轮的判断
                    num = preStr.charAt(i);
                    count = 1;
                }else {
                    count++;
                }
            }
            return res = res + String.valueOf(count) + num;
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)
}

/******************************* 代码评价 *******************************/
// 递归算法的时间复杂度就不用说了，本题也可以改进为非递归算法来实现