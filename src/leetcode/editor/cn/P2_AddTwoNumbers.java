/******************************* Java：两数相加 *******************************/
// 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
//
// 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。 
//
// 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。 
//
// 示例： 
//
// 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
// 输出：7 -> 0 -> 8
// 原因：342 + 465 = 807
// 
// Related Topics 链表 数学 
// 👍 5408 👎 0


/******************************* 题目思路 *******************************/
// 直接用链表模拟数字相加的过程即可
// 注意点：
//      如果两个数位数不同时，因为进位的存在，所以不能像链表合并那样，将多出的子链直接拼接在答案链表后，还是需要单个节点的考虑；
//      简单的办法是，直接将差的节点视为 0，参与到节点相加中；

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

class P2_AddTwoNumbers {
    public static void main(String[] args) {
        Solution solution = new P2_AddTwoNumbers().new Solution();
        // TO TEST
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);
        l1.next.next.next = new ListNode(1);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        ListNode answer = solution.addTwoNumbers(l1,l2);

        ListNode i = answer;
        while (i != null) { // 结果是反向的
            System.out.print(i.val);
            i = i.next;
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode res = null;    // 用来返回结果链表，res 始终指向链表表头
            ListNode p = null;      // 用来遍历，方便向 res 链尾插入元素

            int carry = 0;  // 进位

            while (l1 != null || l2 != null) {
                int n1 = (l1 == null ? 0 : l1.val);
                int n2 = (l2 == null ? 0 : l2.val);

                // 处理相加位
                int sum = n1 + n2 + carry;
                if (res == null) { // 第一次加入元素，初始化
                    res = p = new ListNode(sum % 10);
                } else {
                    p.next = new ListNode(sum % 10);
                    p = p.next;
                }

                // 处理进位
                carry = sum / 10;

                if (l1 != null) l1 = l1.next;
                if (l2 != null) l2 = l2.next;
            }

            // 相加完毕后，还有进位
            if (carry != 0) {
                p.next = new ListNode(carry);
            }

            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度和空间复杂度都是 O( max(m,n))，m，n 为两个链表的长度