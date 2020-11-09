/******************************* Java：环形链表 *******************************/
// 给定一个链表，判断链表中是否有环。
//
// 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的
// 位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
//
// 如果链表中存在环，则返回 true 。 否则，返回 false 。 
//
// 
//
// 进阶： 
//
// 你能用 O(1)（即，常量）内存解决此问题吗？ 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：head = [3,2,0,-4], pos = 1
// 输出：true
// 解释：链表中有一个环，其尾部连接到第二个节点。
// 
//
// 示例 2： 
//
// 
//
// 输入：head = [1,2], pos = 0
// 输出：true
// 解释：链表中有一个环，其尾部连接到第一个节点。
// 
//
// 示例 3： 
//
// 
//
// 输入：head = [1], pos = -1
// 输出：false
// 解释：链表中没有环。
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目范围是 [0, 104] 
// -105 <= Node.val <= 105 
// pos 为 -1 或者链表中的一个 有效索引 。 
// 
// Related Topics 链表 双指针 
// 👍 824 👎 0


/******************************* 题目思路 *******************************/
// 环的判断一直都是热点
//      在图的算法中，就经常需要判断是否有环，图中我们是不断的标记节点的邻接节点，在这个
//      过程中若出现节点已经被标记，并且它不是正在被遍历的节点，那图中一定存在环；
// 那链表里，也可以采用类似的办法，给每个节点添加一个标记域，然后遍历链表，若出现节点已被
// 标记，那链表里一定有环；
// 但是上述方法会带来很大的空间开销，而题目要求 O(1) 的内存，那就只能采用一种双指针的方式，
// 那么只要赛跑是环形，跑的快的一定会追上跑的慢的；

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

class P141_LinkedListCycle {
    public static void main(String[] args) {
        Solution solution = new P141_LinkedListCycle().new Solution();
        // TO TEST
        ListNode head = new ListNode(3);
        head.next = new ListNode(2);
        head.next.next = new ListNode(0);
        head.next.next.next = new ListNode(6);
        head.next.next.next.next = head.next;

        boolean answer = solution.hasCycle(head);
        System.out.println(answer);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    /**
     * Definition for singly-linked list.
     * class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) {
     *         val = x;
     *         next = null;
     *     }
     * }
     */
    public class Solution {
        public boolean hasCycle(ListNode head) {
            if( head == null || head.next == null)
                return false;

            ListNode fast = head;
            ListNode slow = head;
            do {
                if ( fast == null || fast.next == null ) {
                    return false;
                }
                slow = slow.next;
                fast = fast.next.next;
            } while (fast != slow);
            return true;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 空间复杂度 O(1)
// 时间复杂度 O(n)