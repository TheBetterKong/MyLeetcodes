/******************************* Java：比较含退格的字符串 *******************************/
// 给定 S 和 T 两个字符串，当它们分别被输入到空白的文本编辑器后，判断二者是否相等，并返回结果。 # 代表退格字符。
//
// 注意：如果对空文本输入退格字符，文本继续为空。 
//
// 
//
// 示例 1： 
//
// 输入：S = "ab#c", T = "ad#c"
// 输出：true
// 解释：S 和 T 都会变成 “ac”。
// 
//
// 示例 2： 
//
// 输入：S = "ab##", T = "c#d#"
// 输出：true
// 解释：S 和 T 都会变成 “”。
// 
//
// 示例 3： 
//
// 输入：S = "a##c", T = "#a#c"
// 输出：true
// 解释：S 和 T 都会变成 “c”。
// 
//
// 示例 4： 
//
// 输入：S = "a#c", T = "b"
// 输出：false
// 解释：S 会变成 “c”，但 T 仍然是 “b”。
//
// 
//
// 提示： 
//
// 
// 1 <= S.length <= 200 
// 1 <= T.length <= 200 
// S 和 T 只含有小写字母以及字符 '#'。 
// 
//
// 
//
// 进阶： 
//
// 
// 你可以用 O(N) 的时间复杂度和 O(1) 的空间复杂度解决该问题吗？ 
// 
//
// 
// Related Topics 栈 双指针 
// 👍 225 👎 0


/******************************* 题目思路 *******************************/
// 最简单的方法，就是直接构造两个栈，分别遍历两个字符串，遇到 # 就弹出栈顶，否则就入栈。遍历完两个字符串后，在比较两个字符串的是否相等
// 这样会带来较大的空间开销，按照提示应该还有一种双指针的解法：
//      那么直接按照常规比较方式，从头开始，发现 # 号，就得回退，重新进行匹配造成性能浪费。那么有没有什么方法可以避免呢？
//      回退是往前回退，那么如果我们直接从后面开始，不就可以避免这个回退操作了？
//      反向遍历字符串，并进行比较。遇到 # 就往前跳过一个字符，但是有个细节：如果是连续的 # 号，是不能简单的直接跳过的。
//      那么在遇到 # 时，需要先继续向前遍历：
//          遇到 # 字符：   # 的 count+1，继续向前；
//          遇到 非# 字符：  # 的 count>0，就跳过，count-1；否则就与另一个字符串进行正常的比较

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

class P844_BackspaceStringCompare {
    public static void main(String[] args) {
        Solution solution = new P844_BackspaceStringCompare().new Solution();
        // TO TEST
        String S = "bbbextm";
        String T = "bbb#extm";
        boolean answer = solution.backspaceCompare(S, T);
        System.out.println(answer);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean backspaceCompare(String S, String T) {
            int l1 = S.length() - 1;
            int l1count = 0;
            int l2 = T.length() - 1;
            int l2count = 0;

            while (l1 >= 0 || l2 >= 0) {
                // 反向遍历 l1，找到真正需要与 l2 进行比较的字符
                while (l1 >= 0) {
                    if (S.charAt(l1) == '#') {
                        l1count ++;
                        l1 --;
                    }
                    else if (l1count > 0) {
                        l1count --;
                        l1 --;
                    }
                    else {
                        break;
                    }
                }

                // 反向遍历 l2，找到真正需要与 l1 进行比较的字符
                while (l2 >= 0) {
                    if (T.charAt(l2) == '#') {
                        l2count ++;
                        l2 --;
                    }
                    else if (l2count > 0) {
                        l2count --;
                        l2 --;
                    }
                    else {
                        break;
                    }
                }

                // 开始比较两个字符串的字符，
                if (l1 >= 0 && l2 >= 0) {
                    if (S.charAt(l1) != T.charAt(l2)) {
                        return false;
                    }
                } else { // 需要考虑到某个字符串，已经跳过的没有字符参与比较了
                    if (l1 >= 0 || l2 >= 0) {
                        return false;
                    }
                }
                l1 --;
                l2 --;
            }

            return true;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度：相当于遍历了 S 和 T 个一遍，O(m+n)，m 和 n 为 S 和 T 的长度
// 空间复杂度，相较与 栈 的实现，有了很大的改进，这里仅仅定义了两个变量 l1 和 l2 用来计数