/******************************* Java：移除链表元素 *******************************/
// 删除链表中等于给定值 val 的所有节点。
//
// 示例: 
//
// 输入: 1->2->6->3->4->5->6, val = 6
// 输出: 1->2->3->4->5
// 
// Related Topics 链表 
// 👍 460 👎 0


/******************************* 题目思路 *******************************/
// 本题咋眼一看是十分简单的题，但是实际动手编写时，就会遇到各种奇葩的边界问题。
// 总的来说，坑点就在于链表的开始节点就是需要我们去删除的节点的情况。
//      要知道，删除链表开头节点 和 删除链表中间节点 是不一样的操作。
// 那既然如此，那就将链表人为补充一个头结点（哨兵节点），这样所有需要删除的节点就都在链表中间了
// 当然，这里对于链表末尾节点，可以视为末尾有一个 null 节点，所以不需要单独考虑

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;
import leetcode.editor.cn.myDataStructure.*;

class P203_RemoveLinkedListElements {
    public static void main(String[] args) {
        Solution solution = new P203_RemoveLinkedListElements().new Solution();
        // TO TEST
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(4);

        ListNode answer = solution.removeElements(head, 1);
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
        public ListNode removeElements(ListNode head, int val) {
            if ( head == null ) return null;

            ListNode sbnode = new ListNode(-1);
            sbnode.next = head;
            ListNode p = sbnode;

            while (p.next != null) {
                if (p.next.val == val) {
                    p.next = p.next.next;
                }
                else {
                    p = p.next;
                }
            }
            return sbnode.next;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 请主要从时间和空间复杂度的角度，对算法性能分析
// ......