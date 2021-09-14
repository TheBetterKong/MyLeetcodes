/******************************* Java：找打只出现一次的数字 *******************************/
// 给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 k 次 。请你找出并返回那个只出现了一次的元素。
//
// 示例：
//
// 输入：nums = [0,1,0,1,0,1,99], k = 3
// 输出：99

/******************************* 题目思路 *******************************/
// 出现 k 次就不能再用异或的方法了，因为 k(奇数)个相同的数异或还是得到本身。
// 但是还是可以采用位运算的思想，因为出现 k(奇数)次的数字每个位（0 或者 1）也是出现 k(奇数)次，因此可以每一位的和能够被k整除（对k取余为0）。
// 所以如果把每个数的二进制表示的每一位加起来，对于每一位的和，如果能被k整除，那对应那个只出现一次的数字的那一位就是0，否则对应的那一位是1。
//
// 我们需要用一个长度为32（int型二进制表示最多为32位，4字节）的数组bitSum保存每一位的和
// 具体来讲实现过程是，先初始化为0，然后对于每个数字，遍历它二进制表示的每一位，如果这一位是1，bitSum对应的那一位就加1。
//
// 注意：对比 leetcode 上其他 “只出现一次的数字” 类型的题，本题是一个更加通用的题型
/******************************* 题目代码 *******************************/
package leetcode.editor.cn.myproblems;

public class P5_FoundOnceNumber {
    public static void main(String[] args) {
        int[] nums = {5,4,1,1,5,1,5};
        int k = 3;
        int answer = new P5_FoundOnceNumber().foundOnceNumber(nums, k);
        System.out.println(answer);
    }

    public int foundOnceNumber (int[] arr, int k) {
        // write code here
        int[] binarySum = new int[32];
        for (int i = 0; i < 32; i++) {
            int sum = 0;
            for (int num : arr) {
                sum += (num >> i & 1);
            }
            binarySum[i] = sum;
        }
        int res = 0;
        for (int i = 0; i < 32; i++) {
            if (binarySum[i] % k != 0) {
                res += 1 << i;
            }
        }
        return res;
    }
}
