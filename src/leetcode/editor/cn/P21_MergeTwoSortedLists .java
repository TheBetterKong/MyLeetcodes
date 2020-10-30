/******************************* Java：合并两个有序链表 *******************************/
// 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
//
// 
//
// 示例： 
//
// 输入：1->2->4, 1->3->4
// 输出：1->1->2->3->4->4
// 
// Related Topics 链表 
// 👍 1314 👎 0


/******************************* 题目思路 *******************************/
// 十分常规的链表操作，类似归并排序中的，归并操作

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

class P21_MergeTwoSortedLists {
    public static void main(String[] args) {
        Solution solution = new P21_MergeTwoSortedLists().new Solution();
        // TO TEST
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);

        ListNode answer = solution.mergeTwoLists(l1,l2);
        while (answer != null) {
            System.out.print(answer.val + "->");
            answer = answer.next;
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
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(-1);
        ListNode walk = head;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                walk.next = l1;
                l1 = l1.next;
            }
            else {
                walk.next = l2;
                l2 = l2.next;
            }
            walk = walk.next;
        }
        walk.next = ( l1 == null ? l2 : l1 );
        return head.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度 O(n)