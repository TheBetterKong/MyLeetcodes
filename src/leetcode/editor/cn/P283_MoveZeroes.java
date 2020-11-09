/******************************* Java：移动零 *******************************/
//给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。 
//
// 示例: 
//
// 输入: [0,1,0,3,12]
// 输出: [1,3,12,0,0]
//
// 说明: 
//
// 
// 必须在原数组上操作，不能拷贝额外的数组。 
// 尽量减少操作次数。 
// 
// Related Topics 数组 双指针 
// 👍 742 👎 0


/******************************* 题目思路 *******************************/
// 将非 0 数移动至末尾，很明显就是将数组的元素分为了两大类：非 0 和 0；
// 那么接下来就是需要进行数据交换（0 元素和非 0 元素的互换），那么自然而然想到了双指针。
// 但是这里有个坑点，这两个指针都得靠循环来找下一个元素，然后一起进行交换，但是这两个循环又没法重叠。最后整个程序逻辑都会很乱。
// （描述不太清楚，动手编一下就知道其中的故事了）
// 所以，为了嵌套一下循环，双指针改为：非 0 元、遍历控制的 i，这样第一个指针才能嵌套在第二个里，算法逻辑才方便进行下去；


/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

import java.util.Arrays;

class P283_MoveZeroes {
    public static void main(String[] args) {
        Solution solution = new P283_MoveZeroes().new Solution();
        // TO TEST
        int []nums = {0,1,0,3,12};
        solution.moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public void moveZeroes(int[] nums) {
        int notzero = 0;    // 指向数组里非 0 数的慢指针
        // 只要是非 0 数就往前移动
        for(int num = 0; num < nums.length; num++){
            if(nums[num] != 0)
                nums[notzero++] = nums[num];
        }
        // 将 nums 数组里 nums[notzore] 后面的数全部置 0
        for(int i = notzero; i < nums.length; i++){
            nums[i] = 0;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度 O(n)