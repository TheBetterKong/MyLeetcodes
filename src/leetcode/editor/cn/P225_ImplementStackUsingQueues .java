/******************************* Java：用队列实现栈 *******************************/
// 使用队列实现栈的下列操作：
//
// 
// push(x) -- 元素 x 入栈 
// pop() -- 移除栈顶元素 
// top() -- 获取栈顶元素 
// empty() -- 返回栈是否为空 
// 
//
// 注意: 
//
// 
// 你只能使用队列的基本操作-- 也就是 push to back, peek/pop from front, size, 和 is empty 这些操作是合
// 法的。
// 你所使用的语言也许不支持队列。 你可以使用 list 或者 deque（双端队列）来模拟一个队列 , 只要是标准的队列操作即可。 
// 你可以假设所有操作都是有效的（例如, 对一个空的栈不会调用 pop 或者 top 操作）。 
// 
// Related Topics 栈 设计 
// 👍 232 👎 0


/******************************* 题目思路 *******************************/
// 利用栈实现队列，无非就是怎样倒腾队列里的元素，保证先进后出的顺序。
// 在具体一点就是，每次 push 操作时，将新的元素插入对头！！！
// 本题有两种实现方式：
//  1. 利用辅助队列，进行一次倒腾；具体过程见代码
//      时间复杂度：入栈为 O(n)，其余均为 O（1）
//              每次入栈，需将 n 个元素从 que1 出队，将 n+1 个元素压入 que2，共 2n+1 次操作，每次操作的时间复杂度均为 O(1)
//      空间复杂度：O(n)，n 为队列的总元素，需要两个队列
//  2. 一个队列，每次插入新元素后，都将队列原有元素都进行一次 que.offer(que.poll()) 操作
//      时间复杂度：同上
//      空间复杂度：O(n)，n 为队列的总元素，需要一个队列

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

import java.util.LinkedList;
import java.util.Queue;

class P225_ImplementStackUsingQueues {
    public static void main(String[] args) {
        // Solution solution = new P225_ImplementStackUsingQueues().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class MyStack {

        Queue <Integer>que1;     // 存有栈内的元素
        Queue <Integer>que2;     // 每次压栈时的辅助栈

        /** Initialize your data structure here. */
        public MyStack() {
            que1 = new LinkedList<Integer>();
            que2 = new LinkedList<Integer>();
        }

        /** Push element x onto stack. */
        public void push(int x) {
            que2.offer(x);
            // 把 que1 的元素转入 que2
            while (!que1.isEmpty()) {
                que2.offer(que1.poll());
            }
            // 交换两个栈，保证他们各自意义的一致性
            Queue<Integer> temp = que1;
            que1 = que2;
            que2 = temp;
        }

        /** Removes the element on top of the stack and returns that element. */
        public int pop() {
            return que1.poll();
        }

        /** Get the top element. */
        public int top() {
            return que1.peek();
        }

        /** Returns whether the stack is empty. */
        public boolean empty() {
            return que1.isEmpty();
        }
    }

    /**
     * Your MyStack object will be instantiated and called as such:
     * MyStack obj = new MyStack();
     * obj.push(x);
     * int param_2 = obj.pop();
     * int param_3 = obj.top();
     * boolean param_4 = obj.empty();
     */
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 见解题分析部分