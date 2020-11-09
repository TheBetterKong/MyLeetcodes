/******************************* Java：搜索插入位置 *******************************/
//给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。 
//
// 你可以假设数组中无重复元素。 
//
// 示例 1: 
//
// 输入: [1,3,5,6], 5
//输出: 2
// 
//
// 示例 2: 
//
// 输入: [1,3,5,6], 2
//输出: 1
// 
//
// 示例 3: 
//
// 输入: [1,3,5,6], 7
//输出: 4
// 
//
// 示例 4: 
//
// 输入: [1,3,5,6], 0
//输出: 0
// 
// Related Topics 数组 二分查找 
// 👍 689 👎 0


/******************************* 题目思路 *******************************/
// 典型的查找类型题目，根据题目所述，是静态有序查找，而这种查找用的最多的就是二分查找
// 二分查找思想不难，但是有两个关键点：
//      1.循环结束的条件一点是 left <= right，即使在相等时，也还需要一次循环进行比较；
//      2.有些人会追求代码的简洁，会将循环简写为：
//          while(left <= right){
//            int mid = left + (right - l)/2;
//            if(nums[mid] < target)
//                left = mid+1;
//            else right = mid-1;
//          }
//          仔细观察这种写法，
//              假设有输入：[1,3,5,6,7],5 。
//              在 nums[2] = 5 时，程序并不会立即输出 2 并退出，而是会继续进入循环。
//              此时的程序就相当于重新运行输入为 [1,3],5 的测试，毫无疑问，输出还会是 2。
//          上面分析了代码简写的原理，但是就能发现问题，程序好像跑了许多次不必要的循环，造成了额外的时间开销。
//          个人觉得有点得不偿失，并且这样书写会让代码的可读性下降。

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

class P35_SearchInsertPosition {
    public static void main(String[] args) {
        Solution solution = new P35_SearchInsertPosition().new Solution();
        // TO TEST
        int []nums = {1,3,5,6};
        int tagget = 5;
        int answer = solution.searchInsert(nums,tagget);
        System.out.println(answer);
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int searchInsert(int[] nums, int target) {
        if(nums.length == 0) return 0;
        int left = 0;
        int right = nums.length-1;
        int mid = 0;
        while (right >= left) {
            mid = (left + right) / 2;
            if(nums[mid] == target) return mid;
            if(nums[mid] < target){
                left = mid + 1;
            }
            if(nums[mid] > target){
                right = mid - 1;
            }
        }
        return left;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度：O(logn)