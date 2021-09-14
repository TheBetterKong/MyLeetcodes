/******************************* Java：回文链表 *******************************/
// 请判断一个链表是否为回文链表。
//
// 示例 1: 
//
// 输入: 1->2
// 输出: false
//
// 示例 2: 
//
// 输入: 1->2->2->1
// 输出: true
// 
//
// 进阶： 
// 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
// Related Topics 链表 双指针 
// 👍 663 👎 0


/******************************* 题目思路 *******************************/
// 本题我的第一思路是，利用栈，首先遍历链表，然后不断将节点压栈，同时记录总节点数 count
//      一次遍历结束后，再循环 count/2 次，每次遍历中，将 链表节点 与 栈出栈元素 比较，
//      如果都相等，则链表是回文串，否则返回 false；
// 但是上述思路，遍历了两次，并且引入了 O(n) 的空间复杂度，就想着节省这 O(n) 的空间；
//
// 再回顾上述方法，构造栈，无非就是为了链表前半段和后半段比较，那么关键的问题，就是如何将链表一分为二：
//      同样设置快慢指针的方式，快指针速度是慢指针两倍，即：每次遍历快指针跨两个节点，慢指针跨一个节点。
//      这样，当快指针走完链表时，慢指针指向的位置即链表的中间处。
//  在这里还有一个问题，就是栈出栈时，链表相当于反向，所以比较时，还需要对后半段链表进行反转；
//  对比结束，自然也就还需要将链表还原。

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;
import leetcode.editor.cn.myDataStructure.*;

class P234_PalindromeLinkedList {
    public static void main(String[] args) {
        Solution solution = new P234_PalindromeLinkedList().new Solution();
        // TO TEST
        ListNode head = new ListNode(1);
        head.next = new ListNode(0);
        head.next.next = new ListNode(0);

        boolean answer = solution.isPalindrome(head);
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
        public boolean isPalindrome(ListNode head) {
            if (head == null || head.next == null) {
                return true;
            }
            // 设置快慢指针，找链表中间位置
            ListNode slow = head;
            ListNode fast = head;
            while (fast.next != null && fast.next.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }

            // 从 slow 的下一个位置断开。
            //      注意：链表节点数为奇数时，中间的节点算在了前半段
            ListNode last = slow.next;
            slow.next = null;

            // 反转后半段链表
            last = reverseList(last);

            // 遍历比较
            ListNode p1 = head;
            ListNode p2 = last;
            while (p1 != null && p2 != null) {
                if (p1.val != p2.val) return false;
                p1 = p1.next;
                p2 = p2.next;
            }

            // 恢复链表
            last = reverseList(last);
            slow.next = last;

            return true;
        }

        // 链表反转
        private ListNode reverseList(ListNode head) {
            if (head == null || head.next == null )  return head;

            ListNode p1 = null;
            ListNode p2 = null;
            while (head != null) {
                p2 = head.next;
                head.next = p1;
                p1 = head;
                head = p2;
            }
            return p1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 本算法是典型的时间换空间