/******************************* Java：二进制链表转整数 *******************************/
// 给你一个单链表的引用结点 head。链表中每个结点的值不是 0 就是 1。已知此链表是一个整数数字的二进制表示形式。
//
// 请你返回该链表所表示数字的 十进制值 。 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：head = [1,0,1]
// 输出：5
// 解释：二进制数 (101) 转化为十进制数 (5)
// 
//
// 示例 2： 
//
// 输入：head = [0]
// 输出：0
// 
//
// 示例 3： 
//
// 输入：head = [1]
// 输出：1
// 
//
// 示例 4： 
//
// 输入：head = [1,0,0,1,0,0,1,1,1,0,0,0,0,0,0]
// 输出：18880
// 
//
// 示例 5： 
//
// 输入：head = [0,0]
// 输出：0
// 
//
// 
//
// 提示： 
//
// 
// 链表不为空。 
// 链表的结点总数不超过 30。 
// 每个结点的值不是 0 就是 1。 
// 
// Related Topics 位运算 链表 
// 👍 58 👎 0


/******************************* 题目思路 *******************************/
// 本来第一反应，先反转链表，然后遍历，相当于按照传统方式，将二进制数，从最低位开始转十进制。但是这样多一个反转，慢！
//
// 再仔细看一下，二进制到十进制的转换，我们可以转变思路，直接从最高位开始，例如：
//      1101 = ((((1 * 2) + 1 ) * 2) + 0) * 2 + 1
// 这样，就简单了，直接模拟这个过程即可

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;
import leetcode.editor.cn.myDataStructure.*;

class P1290_ConvertBinaryNumberInALinkedListToInteger {
    public static void main(String[] args) {
        Solution solution = new P1290_ConvertBinaryNumberInALinkedListToInteger().new Solution();
        // TO TEST
        ListNode head = new ListNode(1);
        head.next = new ListNode(1);
        head.next.next = new ListNode(0);
        head.next.next.next = new ListNode(1);

        int answer = solution.getDecimalValue(head);
        System.out.println(answer);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) { val = x; }
     * }
     */
    class Solution {
        public int getDecimalValue(ListNode head) {
            int ans = 0;
            ListNode p = head;
            while (p != null) {
                ans = ans * 2 +p.val;
                p = p.next;
            }
            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 请主要从时间和空间复杂度的角度，对算法性能分析
// ......