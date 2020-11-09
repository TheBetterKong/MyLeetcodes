/******************************* Java：多数元素 *******************************/
// 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
//
// 你可以假设数组是非空的，并且给定的数组总是存在多数元素。 
//
// 
//
// 示例 1: 
//
// 输入: [3,2,3]
// 输出: 3
//
// 示例 2: 
//
// 输入: [2,2,1,1,1,2,2]
// 输出: 2
// 
// Related Topics 位运算 数组 分治算法 
// 👍 744 👎 0


/******************************* 题目思路 *******************************/
// 本题最简单的解法就是：
//      新建一个 hashmap，key 值为数组元素值，value 值为对应元素出现的次数；
//      遍历数组，存 key 和更新 value；
//      最后再找到最大的 value，检查是否大于 ⌊ n/2 ⌋；
//      时间复杂度 O(n)，空间复杂度 O(n)；
// 在该思路的基础上，可以发散思维：
//      上述有两个关键的步骤：1.遍历查找数组重复次数最多的元素； 2.检查该元素出现次数是否大于 ⌊ n/2 ⌋；
//      第一步是利用 hashmap 实现，第二步是简单的遍历查找。那算法更改也就只能是从第一步着手了，即怎样找出数组重复次数最多的元素？
//      这里可以运用一种 “数量削减（Boyer-Moore 算法）” 的办法，该方法的现实依据：
//          假设三个人需要打擂，他们每人会按流水线，随机收到若干张卡牌，每张卡牌可用来给自己加一滴血，或者扣除台上擂主一滴血；
//          在这个模型下，第一个拿到牌的人会成为擂主，后面玩家在收到牌后，都会攻击他。直到，擂主下台，换上新的擂主，继续发牌，游戏继续......
//          这样，其实只要哪个玩家收到的牌多于总牌数一半（他完全可以一挑二），那获胜的一定就是这个玩家。
// 还看到一个称为随机数生成的办法，这是巧妙的利用了数学概率问题，可以作为发散思维的扩展：
//      由于一个给定的下标对应的数字很有可能是众数，我们随机挑选一个下标，检查它是否是众数，如果是就返回，否则继续随机挑选。

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

class P169_MajorityElement {
    public static void main(String[] args) {
        Solution solution = new P169_MajorityElement().new Solution();
        // TO TEST
        int []nums = {2,2,1,1,1,1,2};
        int answer = solution.majorityElement(nums);
        System.out.println(answer);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int majorityElement(int[] nums) {
            int count = 0;
            Integer winer = null;
            for(int num : nums){
                if(count == 0) winer = num;
                if(winer == num) count++;
                if(winer != num) count--;
            }
            return winer;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度 O(n)
// 空间复杂度 O(1)