/******************************* Java：寻找数组第 K 大的数 *******************************/
// 题目描述：
//      有一个整数数组，请你根据快速排序的思路，找出数组中第 K 大的数。
//      给定一个整数数组 a,同时给定它的大小 n 和要找的 K(1 <= K <= N)，请返回第 K 大的数(包括重复的元素，不用去重)，保证答案存在。
// 要求：
//      时间复杂度 O(n）
//
//
// 示例 1：
//      输入：[1,3,5,2,2],5,3
//      返回值：2
//
// 示例2
//      输入：[10,10,9,9,8,7,5,6,4,3,4,2],12,3
//      返回值：9
//      说明：去重后的第 3 大是 8，但本题要求包含重复的元素，不用去重，所以输出 9

/******************************* 题目思路 *******************************/

/******************************* 题目代码 *******************************/
package leetcode.editor.cn.myproblems;

public class P4_FindKth {
    public static void main(String[] args) {
        int[] a = {1,3,5,2,2};
        int n = 5;
        int K = 3;
        int answer = new P4_FindKth().findKth(a, n, K);
        System.out.println(answer);
    }

    public int findKth(int[] a, int n, int K) {
        // write code here
        return helper(a, 0, n - 1, K);
    }

    private int helper(int[] arr, int left, int right, int K) {
        if (left <= right) {
            int index = partition(arr, left, right);

            if (index == K - 1) {
                return arr[index];
            } else if (index < K - 1) {
                // 因为前面的 index 始终是相对于 arr 全局的索引，所以这里的 K 值不用变
                return helper(arr, index + 1, right, K);
            } else {
                return helper(arr, left, index - 1, K);
            }
        }
        return -1;
    }

    // 降序，一趟快排
    private int partition(int[] a, int left, int right) {
        int key = a[left];

        while (left < right) {
            while (key >= a[right] && left < right) {
                right--;
            }
            a[left] = a[right];
            while (key <= a[left] && left < right) {
                left++;
            }
            a[right] = a[left];
        }
        a[left] = key;
        return left;
    }
}

/******************************* 代码评价 *******************************/
