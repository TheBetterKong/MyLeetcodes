/******************************* Java：寻找序列的平衡点 *******************************/
// 写一个函数，返回给定序列的平衡点（任何一个），如果没有返回-1，假定这个序列可达到非常大。
//
// 平衡点就是：左边元素和等于他右边元素和
//
//
// 示例 1：
//
// 输入：nums = [1,1,5,1,1]
// 输出：5
// 解释：
// 5 左边的元素和：1+1，右边的元素和：1+1

/******************************* 题目思路 *******************************/

/******************************* 题目代码 *******************************/
package leetcode.editor.cn.myproblems;

public class P1_GetBalancePoint {
    public static void main(String[] args) {
        int[] nums = {0,0,0,0,0};
        int answer = new P1_GetBalancePoint().getBalancePoint(nums);
        System.out.println(answer);
    }

    public int getBalancePoint(int[] nums) {
        int leftSum = 0;
        int rightSum = 0;
        int p = 0;

        for (int i = 0; i < nums.length; i++) {
            rightSum += nums[i];
        }
        if (leftSum == rightSum) {
            return 0;
        }
        rightSum -= nums[0];
        for (int i = 1; i < nums.length - 1; i++) {
            leftSum += nums[i-1];
            rightSum -= nums[i];
            if (leftSum == rightSum) {
                return i;
            }
        }
        return -1;
    }
}

/******************************* 代码评价 *******************************/