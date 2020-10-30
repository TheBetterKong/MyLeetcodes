/******************************* Java：用栈实现队列 *******************************/
//使用栈实现队列的下列操作： 
//
// 
// push(x) -- 将一个元素放入队列的尾部。 
// pop() -- 从队列首部移除元素。 
// peek() -- 返回队列首部的元素。 
// empty() -- 返回队列是否为空。 
// 
//
// 
//
// 示例: 
//
// MyQueue queue = new MyQueue();
//
//queue.push(1);
//queue.push(2);  
//queue.peek();  // 返回 1
//queue.pop();   // 返回 1
//queue.empty(); // 返回 false 
//
// 
//
// 说明: 
//
// 
// 你只能使用标准的栈操作 -- 也就是只有 push to top, peek/pop from top, size, 和 is empty 操作是合法的。
// 
// 你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。 
// 假设所有操作都是有效的 （例如，一个空的队列不会调用 pop 或者 peek 操作）。 
// 
// Related Topics 栈 设计 
// 👍 242 👎 0


/******************************* 题目思路 *******************************/
// 同队列实现栈一样，也利用两个栈实现队列。但是这里，栈的单口进出特殊性，是无法利用一个栈来实现的。但是这里也有两种思路：
//   1. 同样的，选择 stack1 保存队列里的元素，stack2 作为辅助栈。
//          那么，每次 push 操作时，先将 s1 的元素全部 s2.push(s1.pop()) 进 s2，然后将新元素压入 s1，再将 s2 的元素倒腾回来即可
//          其他的操作，直接在 stack1 上进行即可；
//   2. 还有一种思路，将 pop 和 push 操作分摊到 s1 和 s2 上进行，从而降低最坏情况下的时间复杂度：
//          压栈时，元素都可以直接压入 s1；
//          出栈时，如果 s2 非空，就直接从 s2 出，否则先将 s1 的全部元素 s2.push(s1.pop()) 进 s2 了，再从 s2 出栈；
//          注意：因为这次将操作分摊到了两个栈上进行，所以队列非空的判断，和取对首元素的操作都会有一些变化，需要单独定义一个变量保存。
//  那么，方法 2 相对于 方法 1 来说看起来更复杂，为什么又会说有改进呢？
//          仔细，对比上述两种方法，方法 1 每次 push 时，都需要完成元素在两个 stack 间的倒腾。但是，方法 2 只有在 pop 并且 stack2 为空时，
//          才需要一次这样的倒腾；
//          其实，这就相当于在连续几次的操作间，做了一个 “缓存” ，这样，每一次 pop 倒腾过元素后，都可以连续几次 pop，而不用经历这些 O(n^2)
//          的元素倒腾操作；

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

import java.util.Stack;

class P232_ImplementQueueUsingStacks {
    public static void main(String[] args) {
        // Solution solution = new P232_ImplementQueueUsingStacks().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class MyQueue {

        int front; // 用来保存 s1 里的栈底元素，当 s2 为空时，s1 的栈底元素就应该是队头
        private Stack<Integer> s1;
        private Stack<Integer> s2;

        /** Initialize your data structure here. */
        public MyQueue() {
            s1 = new Stack<Integer>();
            s2 = new Stack<Integer>();
        }

        /** Push element x to the back of queue. */
        public void push(int x) {
            if (s1.empty())
                front = x;
            s1.push(x);
        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            if (s2.isEmpty()) {
                while (!s1.isEmpty())
                    s2.push(s1.pop());
            }
            return s2.pop();
        }

        /** Get the front element. */
        public int peek() {
            if (!s2.isEmpty()) {
                return s2.peek();
            }
            return front;
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            return s1.isEmpty() && s2.isEmpty();
        }
    }

    /**
     * Your MyQueue object will be instantiated and called as such:
     * MyQueue obj = new MyQueue();
     * obj.push(x);
     * int param_2 = obj.pop();
     * int param_3 = obj.peek();
     * boolean param_4 = obj.empty();
     */
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 见解题思路部分