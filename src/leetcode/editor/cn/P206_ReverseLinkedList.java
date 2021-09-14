/******************************* Java：反转链表 *******************************/
// 反转一个单链表。
//
// 示例: 
//
// 输入: 1->2->3->4->5->NULL
// 输出: 5->4->3->2->1->NULL
//
// 进阶: 
// 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
// Related Topics 链表 
// 👍 1278 👎 0


/******************************* 题目思路 *******************************/
// 反转，也是链表里很基础的操作，容易想到应该至少需要 两个指针（引用），算上 head 一起，共三个
// 那么接下来就是，这三个指针的移动，每次循环结束，他们三的位置关系应该为 p1 head p2

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;
import leetcode.editor.cn.myDataStructure.*;

class P206_ReverseLinkedList {
    public static void main(String[] args) {
        Solution solution = new P206_ReverseLinkedList().new Solution();
        // TO TEST
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        ListNode answer = solution.reverseList(head);
        while (answer != null) {
            System.out.println(answer.val);
            answer = answer.next;
        }
        System.out.println("null");
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
        public ListNode reverseList(ListNode head) {
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
// 一次遍历，n 次循环，每次循环 4 次赋值，相比于递归算法，迭代更加安全，也更快
// 空间复杂度，O(1)