/******************************* Java：相交链表 *******************************/
// 编写一个程序，找到两个单链表相交的起始节点。
//
// 如下面的两个链表： 
//
// 
//
// 在节点 c1 开始相交。 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
// 输出：Reference of the node with value = 8
// 输入解释：相交节点的值为 8 （注意，如果两个链表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1
//,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
// 
//
// 
//
// 示例 2： 
//
// 
//
// 输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
// 输出：Reference of the node with value = 2
// 输入解释：相交节点的值为 2 （注意，如果两个链表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4
// ]。在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
// 
//
// 
//
// 示例 3： 
//
// 
//
// 输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
// 输出：null
// 输入解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。由于这两个链表不相交，所以 intersectVal 必须为 0，而
// skipA 和 skipB 可以是任意值。
// 解释：这两个链表不相交，因此返回 null。
// 
//
// 
//
// 注意： 
//
// 
// 如果两个链表没有交点，返回 null. 
// 在返回结果后，两个链表仍须保持原有的结构。 
// 可假定整个链表结构中没有循环。 
// 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。 
// 
// Related Topics 链表 
// 👍 842 👎 0


/******************************* 题目思路 *******************************/
// 两个链表的第一个公共结点，像不像找两个数组第一个相同的元素？
// 1. 暴力解答：套用两层循环逐个遍历，时间复杂度 O(n^2)；
//
// 2. 想其他更优办法：
//
//        首先，必须想到的一点是：两个链表存在公共结点，而每个结点又只有一个后续结点，那么，从该公共结点开始，后续的所有结点都是被两个链表公用的；
//        例如：
//           链表 1：1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> null
//           链表 2：8 -> 9 -> 5 -> 6 -> 7 -> null
//           公共结点为：5
//           链表 1 长度：4+3
//           链表 2 长度：2+3
//        发现没有，如果有某种办法，能将两个链表从尾向头遍历，那么就是找最后一个不相同的结点，当然了，这种方法是不可能的。
//        但是，链表里有一道很经典的题：『链表中倒数第 k 个结点』，当时就是通过两个指针 “绕”，解决的。那么这里，是不是也可以这么绕？
//
//        我们观察，链表 1 比链表 2 长 2，那么让链表 1 先遍历两个结点，链表再开始和链表 1 同时遍历，那么只要它们有公共结点，就一定能找到；
//
//        现在问题就是，怎么知道链表长度差？这里就需要绕一下了
//        同样思路，先用两指针同时遍历链表，某个指针先遍历到链表（短的那个）末尾，然后将其换成头指针，换成哪个链表的头指针呢？
//            如果换成短链表自己的头指针，那链表 2 比链表 1 领先，这肯定不是我们想要的；
//            所以就换成长链表的头指针，然后再继续同时遍历，长链表的指针遍历的末尾，再换成短链表的头指针，OK，长度差就合理构造出来了；
//        有点绕，可以自己画图模拟一下，关系就能出来了。

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

class P160_IntersectionOfTwoLinkedLists {
    public static void main(String[] args) {
        Solution solution = new P160_IntersectionOfTwoLinkedLists().new Solution();
        // TO TEST
        ListNode headA = new ListNode(2);
        headA.next = new ListNode(1);
        headA.next.next = new ListNode(4);

        ListNode headB = headA.next;
        headB.next = headA.next.next;

        ListNode answer = solution.getIntersectionNode(headA, headB);
        System.out.println(answer.val);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) {
     *         val = x;
     *         next = null;
     *     }
     * }
     */
    public class Solution {
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            if (headA == null || headB == null) return null;
            ListNode p1 = headA;
            ListNode p2 = headB;
            while (p1 != p2){
                p1 = (p1 == null ? headB : p1.next);
                p2 = (p2 == null ? headA : p2.next);
            }
            return p1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 请主要从时间和空间复杂度的角度，对算法性能分析
// ......