/******************************* Java：删除链表中的节点 *******************************/
// 请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点。传入函数的唯一参数为 要被删除的节点 。
//
// 
//
// 现有一个链表 -- head = [4,5,1,9]，它可以表示为: 
//
// 
//
// 
//
// 示例 1： 
//
// 输入：head = [4,5,1,9], node = 5
// 输出：[4,1,9]
// 解释：给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
// 
//
// 示例 2： 
//
// 输入：head = [4,5,1,9], node = 1
// 输出：[4,5,9]
// 解释：给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
// 
//
// 
//
// 提示： 
//
// 
// 链表至少包含两个节点。 
// 链表中所有节点的值都是唯一的。 
// 给定的节点为非末尾节点并且一定是链表中的一个有效节点。 
// 不要从你的函数中返回任何结果。 
// 
// Related Topics 链表 
// 👍 778 👎 0


/******************************* 题目思路 *******************************/
// 额....这个题，我一眼看上去这不是很简单吗？？？吧这个结点的上一个结点的 next 指向这个结点的下一个节点就好了
// 然后，正要动手写代码，就发现了不对，这也没告诉我们要进行操作的链表啊！！那我们就没法知道当前节点的下一个节点
//
// 然后，就懵了。以为是题目写错了？（日常怀疑题目）跑去看了一下评论，好吧，懂了。。。。。原来这就是这题的难点。

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

class P237_DeleteNodeInALinkedList {
    public static void main(String[] args) {
        Solution solution = new P237_DeleteNodeInALinkedList().new Solution();
        // TO TEST
        ListNode head = new ListNode(4);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(2);

        solution.deleteNode(head.next);
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
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
        public void deleteNode(ListNode node) {
            node.val = node.next.val;
            node.next = node.next.next;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 请主要从时间和空间复杂度的角度，对算法性能分析
// ......