/******************************* Java：删除链表的倒数第 N 个结点 *******************************/
// 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
//
// 进阶：你能尝试使用一趟扫描实现吗？ 
//
// 
//
// 示例 1： 
//
// 输入：head = [1,2,3,4,5], n = 2
// 输出：[1,2,3,5]
// 
//
// 示例 2： 
//
// 输入：head = [1], n = 1
// 输出：[]
// 
//
// 示例 3： 
//
// 输入：head = [1,2], n = 1
// 输出：[1]
// 
//
//
// 提示： 
//
// 链表中结点的数目为 sz 
// 1 <= sz <= 30 
// 0 <= Node.val <= 100 
// 1 <= n <= sz 
// 
// Related Topics 链表 双指针 
// 👍 1201 👎 0


/******************************* 题目思路 *******************************/
// 这是之前用过的 “快慢双指针” 的思想
// 注意一下，双指针的间隔设置，以及边界情况

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

class P19_RemoveNthNodeFromEndOfList {
    public static void main(String[] args) {
        Solution solution = new P19_RemoveNthNodeFromEndOfList().new Solution();
        // TO TEST
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        int n = 5;
        ListNode answer = solution.removeNthFromEnd(head, n);
        while (answer != null) {
            System.out.println(answer.val);
            answer = answer.next;
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            ListNode fast = head;
            ListNode slow = head;

            // 找到所需删除的节点的前一个节点，由 slow 指向
            int count = 0;
            while (fast != null) {
                fast = fast.next;
                if (count > n) {
                    slow = slow.next;
                }
                count ++;
            }

            // 删除相关节点（也可以设置头部的哑节点来避免判断）
            if (count < n) {  // 判断倒数第 n 个节点是否存在
                throw new IllegalArgumentException("倒数第 n 个节点不存在！");
            } else if (count == n) {  // 删除头结点
                head = head.next;
                return head;
            } else if (n == 1) {    // 删除最后一个节点
                slow.next = null;
            } else {
                slow.next = slow.next.next;
            }

            return head;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度：O(n)
// 空间复杂度：O(1)