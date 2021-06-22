/******************************* Java：环形链表 II *******************************/
// 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
//
// 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
// 如果 pos 是 -1，则在该链表中没有环。注意，pos 仅仅是用于标识环的情况，并不会作为参数传递到函数中。
//
// 说明：不允许修改给定的链表。 
//
// 进阶： 
//
// 你是否可以使用 O(1) 空间解决此题？ 
// 
//
// 示例 1： 
//
// 输入：head = [3,2,0,-4], pos = 1
// 输出：返回索引为 1 的链表节点
// 解释：链表中有一个环，其尾部连接到第二个节点。
// 
//
// 示例 2： 
//
// 输入：head = [1,2], pos = 0
// 输出：返回索引为 0 的链表节点
// 解释：链表中有一个环，其尾部连接到第一个节点。
// 
//
// 示例 3： 
//
// 输入：head = [1], pos = -1
// 输出：返回 null
// 解释：链表中没有环。
// 
//
// 
//
// 提示： 
//
// 链表中节点的数目范围在范围 [0, 104] 内 
// -105 <= Node.val <= 105 
// pos 的值为 -1 或者链表中的一个有效索引 
// 
// Related Topics 链表 双指针 
// 👍 1039 👎 0


/******************************* 题目思路 *******************************/
// 本题和 P141 一样，直接使用快慢双指针遍历即可判断是否有环，但是问题在于，怎样确定入环点的位置
//
// 同样，我们先确定 fast 和 slow 指针，其中 fast 指针一次走两个节点，slow 指针一次走一个节点；
//
// 假设，起点到入环点的距离为 a，入环点到相遇点的距离为 b，相遇点距下一次入环点的距离为 c，此时，链表总长 a+b+c
// 现在，我们假设相遇时，fast 走过的距离为 a+n(b+c)+b
// 我们知道 fast 指针走的路程一直是 slow 指针的二倍，所以当 slow 指针走环形走一半时，fast 指针走的路程一定大于
// 等于一圈，所以当 slow 指针走一半或者一半以前两者必定相遇，所以可以得到关系：
// a+n(b+c)+b = 2(a+b) ==> a=c+(n−1)(b+c)
// 这意味着：从相遇点到入环点的距离加上 n−1 圈的环长，恰好等于从链表头部到入环点的距离。
//
// 于是，当发现 slow 与 fast 相遇时，我们再额外使用一个指针 ptr。
// 起始，它指向链表头部；随后，它和 slow 每次向后移动一个位置。
// 最终，它们会在入环点相遇。

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

class P142_LinkedListCycleIi {
    public static void main(String[] args) {
        Solution solution = new P142_LinkedListCycleIi().new Solution();
        // TO TEST
        ListNode head = new ListNode(3);
        head.next = new ListNode(2);
        head.next.next = new ListNode(0);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = head.next;
        ListNode answer = solution.detectCycle(head);
        System.out.println(answer.val);
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
        public ListNode detectCycle(ListNode head) {
            if (head == null || head.next == null) {
                return null;
            }
            ListNode fast = head, slow = head;
            while (fast != null) {
                slow = slow.next;
                if (fast.next != null) {
                    fast = fast.next.next;
                } else {
                    return null;
                }
                // 相遇了，开始找连接点
                if (fast == slow) {
                    ListNode ptr = head;
                    while (ptr != slow) {
                        ptr = ptr.next;
                        slow = slow.next;
                    }
                    return ptr;
                }
            }
            return null;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度：O(n)，在判断快慢指针是否相遇时，slow 指针走过的距离不会超过链表的总长度；寻找入环点时，走过的距离也不会超过链表的总长度。
// 空间复杂度：O(1)