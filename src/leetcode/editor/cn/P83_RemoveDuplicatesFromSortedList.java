/******************************* Java：删除排序链表中的重复元素 *******************************/
// 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
//
// 示例 1: 
//
// 输入: 1->1->2
// 输出: 1->2
// 
//
// 示例 2: 
//
// 输入: 1->1->2->3->3
// 输出: 1->2->3
// Related Topics 链表 
// 👍 410 👎 0


/******************************* 题目思路 *******************************/
// 既然链表已经有序，那么重复的元素一定相邻，直接比较 当前节点 和 下一个节点 是否相等，相等则跳过下一个节点即可

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;
import leetcode.editor.cn.myDataStructure.*;

class P83_RemoveDuplicatesFromSortedList {
    public static void main(String[] args) {
        Solution solution = new P83_RemoveDuplicatesFromSortedList().new Solution();
        // TO TEST
        ListNode head = new ListNode(1);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(3);

        ListNode answer = solution.deleteDuplicates(head);
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
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode p = head;
        while (p != null && p.next != null) {
            if (p.next.val == p.val) {
                p.next = p.next.next;
            } else {
                p = p.next;
            }
        }
        return head;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 请主要从时间和空间复杂度的角度，对算法性能分析
// ......