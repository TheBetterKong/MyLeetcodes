/******************************* Java：反转链表 II *******************************/
// 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链
// 表节点，返回 反转后的链表。
// 
//
// 示例 1： 
//
// 输入：head = [1,2,3,4,5], left = 2, right = 4
// 输出：[1,4,3,2,5]
// 
//
// 示例 2： 
//
// 输入：head = [5], left = 1, right = 1
// 输出：[5]
// 
//
//
// 提示： 
//
// 链表中节点数目为 n 
// 1 <= n <= 500 
// -500 <= Node.val <= 500 
// 1 <= left <= right <= n 
//
// 进阶： 你可以使用一趟扫描完成反转吗？ 
// Related Topics 链表 
// 👍 979 👎 0


/******************************* 题目思路 *******************************/
// 思路一：先遍历找到 left right 的节点位置，再用 T206 的思路反转，再将左右两端的链表拼接。该方法需要两次遍历
// 思路二：直接进行，反转过程引用 “插入排序” 类似的思路，以例 1 ：
//      遍历到 2 时，不变
//      遍历到 3 时，将 3 插入到 2 的前面
//      遍历到 4 时，将 4 插入到 3 的前面
//      ......

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;
import leetcode.editor.cn.myDataStructure.*;

class P92_ReverseLinkedListIi {
    public static void main(String[] args) {
        Solution solution = new P92_ReverseLinkedListIi().new Solution();
        // TO TEST
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        int left = 2, right = 4;
        ListNode list = solution.reverseBetween(head, left, right);

        ListNode node = head;
        for (int i = 1; i <= right; i++) {
            System.out.println(node.val);
            node = node.next;
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
        public ListNode reverseBetween(ListNode head, int left, int right) {
            ListNode dumpyNode = new ListNode(-1);
            dumpyNode.next = head; // 哨兵节点保存头结点
            ListNode pre = dumpyNode;

            // 找 left 对应的前一个节点
            for (int i = 0; i < left - 1; i++) {
                pre = pre.next;
            }

            ListNode cur = pre.next;
            ListNode next;

            // 插入反转
            for (int i = 0; i < right - left; i++) {
                next = cur.next;

                cur.next = next.next;
                next.next = pre.next;
                pre.next = next;
            }

            return dumpyNode.next;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度：O(n)
// 空间复杂度：O(1)