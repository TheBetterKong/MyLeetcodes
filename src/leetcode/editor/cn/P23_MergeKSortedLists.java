/******************************* Java：合并K个升序链表 *******************************/
// 给你一个链表数组，每个链表都已经按升序排列。
// 请你将所有链表合并到一个升序链表中，返回合并后的链表。 
//
// 
//
// 示例 1： 
//
// 输入：lists = [[1,4,5],[1,3,4],[2,6]]
// 输出：[1,1,2,3,4,4,5,6]
// 解释：链表数组如下：
// [
//  1->4->5,
//  1->3->4,
//  2->6
// ]
// 将它们合并到一个有序链表中得到。
// 1->1->2->3->4->4->5->6
// 
//
// 示例 2： 
//
// 输入：lists = []
// 输出：[]
// 
//
// 示例 3： 
//
// 输入：lists = [[]]
// 输出：[]
//
//
// 提示： 
//
// k == lists.length 
// 0 <= k <= 10^4 
// 0 <= lists[i].length <= 500 
// -10^4 <= lists[i][j] <= 10^4 
// lists[i] 按 升序 排列 
// lists[i].length 的总和不超过 10^4 
// 
// Related Topics 堆 链表 分治算法 
// 👍 1130 👎 0


/******************************* 题目思路 *******************************/
// 和归并排序的思想基本相同，采用分治法合并

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

class P23_MergeKSortedLists {
    public static void main(String[] args) {
        Solution solution = new P23_MergeKSortedLists().new Solution();
        // TO TEST
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(5);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);

        ListNode l3 = new ListNode(2);
        l3.next = new ListNode(6);

        ListNode[] lists = {l1, l2, l3};
        ListNode answer = solution.mergeKLists(lists);

        while (answer.next != null) {
            System.out.println(answer.val);
            answer = answer.next;
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public ListNode mergeKLists(ListNode[] lists) {
            return merge(lists, 0, lists.length-1);
        }

        private ListNode merge(ListNode[] lists, int l, int r) {
            if (l == r) {
                return lists[l];
            } else if (l > r) {
                return null;
            } else {
                int mid = (l + r)/2;
                return mergeTwoLists(merge(lists, l, mid), merge(lists, mid+1, r));
            }
        }

        private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            if (l1 == null || l2 == null) {
                return l1 != null ? l1 : l2;
            }
            ListNode head = new ListNode(0);
            ListNode tail = head, p1 = l1, p2 = l2;
            while (p1 != null && p2 != null) {
                if (p1.val < p2.val) {
                    tail.next = p1;
                    p1 = p1.next;
                } else {
                    tail.next = p2;
                    p2 = p2.next;
                }
                tail = tail.next;
            }
            tail.next = (p1 != null ? p1 : p2);
            return head.next;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度：O(kn×logk)
//      第一轮合并 k/2 组链表，每一组合并的时间为 O(2^1 n)
//      第二轮合并 k/4 组链表，每一组合并的时间为 O(2^2 n)
//      ......
//      叠加起来，总的时间代价为：#/sum_{i=1}^{∞} {k/(2^i) * 2^i*n}#，因此，渐进时间复杂度：O(kn×logk)
// 空间复杂度：递归会使用到 O(logk) 空间代价的栈空间