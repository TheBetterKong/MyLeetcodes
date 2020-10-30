/******************************* Java：最小栈 *******************************/
// 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
//
// 
// push(x) —— 将元素 x 推入栈中。 
// pop() —— 删除栈顶的元素。 
// top() —— 获取栈顶元素。 
// getMin() —— 检索栈中的最小元素。 
// 
//
// 
//
// 示例: 
//
// 输入：
// ["MinStack","push","push","push","getMin","pop","top","getMin"]
// [[],[-2],[0],[-3],[],[],[],[]]
//
// 输出：
// [null,null,null,null,-3,null,0,-2]
//
// 解释：
// MinStack minStack = new MinStack();
// minStack.push(-2);
// minStack.push(0);
// minStack.push(-3);
// minStack.getMin();   --> 返回 -3.
// minStack.pop();
// minStack.top();      --> 返回 0.
// minStack.getMin();   --> 返回 -2.
// 
//
// 
//
// 提示： 
//
// 
// pop、top 和 getMin 操作总是在 非空栈 上调用。 
// 
// Related Topics 栈 设计 
// 👍 699 👎 0


/******************************* 题目思路 *******************************/
// 1. 最简单的方式就是另外构造一个 辅助栈 在保存栈的元素同时，保存下当前的最小值，但是这样空间复杂度太高；
// 2. 如果只用一个栈，那么可以将讲 <val, 当前 min> 绑定在一起，每次 push pop 同时操作两个数；
// 3. 上面的方法，很显然并没有很有效的降低空间复杂度。那么，还有一个更加经典的方法：存差值：
//      具体来说，就是用一个 min 变量保存最小值。只不过栈里边不去保存原来的值，而是去存储入栈的值和最小值的差值。
//      然后得到之前的最小值的话，就可以通过 min 值和栈顶元素得到，例如：
//            入栈 3，存入 3 - 3 = 0
//            |   |   min = 3
//            |   |
//            |_0_|
//            stack
//
//            入栈 5，存入 5 - 3 = 2
//            |   |   min = 3
//            | 2 |
//            |_0_|
//            stack
//
//            入栈 2，出现了更小的数，于是会存入一个负数（2 - 3 = -1）, 并且更新 min = 2
//            对于之前的 min 值 3, 我们只需要用更新后的 min - 栈顶元素 就可以得到
//            | -1|   min = 2
//            | 2 |
//            |_0_|
//            stack
//
//            入栈 6，存入  6 - 2 = 4
//            | 4 |   min = 2
//            | -1|
//            | 2 |
//            |_0_|
//            stack
//
//            出栈，返回的值就是栈顶元素 4 加上 min，就是 6
//            |   |   min = 2
//            | -1|
//            | 2 |
//            |_0_|
//            stack
//
//            出栈，此时栈顶元素是负数，说明之前对 min 值进行了更新。
//            入栈元素 - min = 栈顶元素：
//              入栈元素其实就是当前的 min 值 2
//              所以更新前的 min 就等于入栈元素 2 - 栈顶元素(-1) = 3
//            |   | min = 3
//            | 2 |
//            |_0_|
//            stack
//


/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

import java.util.Stack;

class P155_MinStack {
    public static void main(String[] args) {
        // Solution solution = new P155_MinStack().new Solution();
        // TO TEST
//        MinStack stack = new MinStack();
//        stack.push(3);
//        stack.push(5);
//        stack.push(2);
//        int min = stack.getMin();
//        System.out.println("push(3), push(5), push(2), min = " + min);
//        stack.push(6);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    // static class MinStack {
    class MinStack {
        long min; // 差值，long 类型防止溢出
        Stack <Long>stack;

        /** initialize your data structure here. */
        public MinStack() {
            stack = new Stack();
        }

        public void push(int x) {
            if (stack.isEmpty()) {
                min = x;
                stack.push(x - min);
            }
            else {
                stack.push(x - min);
                if (x < min) {
                    min = x;
                }
            }
        }

        public void pop() {
            if (stack.isEmpty()) {
                return;
            }
            long pop = stack.pop();
            // 负数，需要更新 min
            if (pop < 0) {
                min = min -pop;
            }
        }

        public int top() {
            long top = stack.peek();
            if (top < 0) {  // 负数，出栈的值就是最小值
                return (int) (min);
            } else {        // 正数，出栈元素加上最小值
                return (int) (top + min);
            }
        }

        public int getMin() {
            return (int) min;
        }
    }

    /**
     * Your MinStack object will be instantiated and called as such:
     * MinStack obj = new MinStack();
     * obj.push(x);
     * obj.pop();
     * int param_3 = obj.top();
     * int param_4 = obj.getMin();
     */
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 本题利用差值法存储，相比于辅助栈，空间复杂度仅为 O(1)