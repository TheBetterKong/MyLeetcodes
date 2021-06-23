/******************************* Java：LRU 缓存机制 *******************************/
// 运用你所掌握的数据结构，设计和实现一个 LRU (最近最少使用) 缓存机制 。
//
// 实现 LRUCache 类： 
//
// LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
//
// int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
//
// void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。
//      当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
// 
//
// 进阶：你是否可以在 O(1) 时间复杂度内完成这两种操作？
//
// 示例： 
//
// 输入
// ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
// [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
// 输出
// [null, null, null, 1, null, -1, null, -1, 3, 4]
//
// 解释
// LRUCache lRUCache = new LRUCache(2);
// lRUCache.put(1, 1); // 缓存是 {1=1}
// lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
// lRUCache.get(1);    // 返回 1
// lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
// lRUCache.get(2);    // 返回 -1 (未找到)
// lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
// lRUCache.get(1);    // 返回 -1 (未找到)
// lRUCache.get(3);    // 返回 3
// lRUCache.get(4);    // 返回 4
// 
//
//
// 提示： 
//
// 1 <= capacity <= 3000 
// 0 <= key <= 3000 
// 0 <= value <= 104 
// 最多调用 3 * 104 次 get 和 put 
// 
// Related Topics 设计 
// 👍 1457 👎 0


/******************************* 题目思路 *******************************/
// 主要就是涉及 linklist 和 hashmap 的运用，主要思路将代码

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

class P146_LruCache {
    public static void main(String[] args) {
        // Solution solution = new P146_LruCache().new Solution();
        // TO TEST
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1);                     // 缓存是 {1=1}
        lRUCache.put(2, 2);                     // 缓存是 {1=1, 2=2}
        System.out.println(lRUCache.get(1));    // 返回 1
        lRUCache.put(3, 3);                     // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        System.out.println(lRUCache.get(2));    // 返回 -1 (未找到)
        lRUCache.put(4, 4);                     // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        System.out.println(lRUCache.get(1));    // 返回 -1 (未找到)
        System.out.println(lRUCache.get(3));    // 返回 3
        System.out.println(lRUCache.get(4));    // 返回 4
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    static class LRUCache {
            // 构建一个双向链表，用来按 LRU 存储使用的序列
            private class DLinkedNode {
                int key;
                int value;
                DLinkedNode prev;
                DLinkedNode next;
                public DLinkedNode() {}
                public DLinkedNode(int _key, int _value) {key = _key; value = _value;}
            }

            // 用 hashmap 将缓存数据的 key 映射到其在双向链表中的位置
            private Map<Integer, DLinkedNode> cache = new HashMap<Integer, DLinkedNode>();

            private int size;               // LRUCache 已使用的容量
            private int capacity;           // LRUCache 的容量
            private DLinkedNode head, tail; // 双向链表的头和尾，它们是两个哨兵指针，越靠近头部是最近使用的，越靠近尾部是很长未被使用的

            public LRUCache(int capacity) {
                this.size = 0;
                this.capacity = capacity;
                // 使用伪头部和伪尾部节点
                head = new DLinkedNode();
                tail = new DLinkedNode();
                head.next = tail;
                tail.prev = head;
            }

            public int get(int key) {
                DLinkedNode node = cache.get(key);
                if (node == null) {
                    return -1;
                }
                moveToHead(node);
                return node.value;
            }

            public void put(int key, int value) {
                DLinkedNode node = cache.get(key);
                if (node == null) {
                    DLinkedNode newNode = new DLinkedNode(key, value);
                    cache.put(key, newNode);
                    addToHead(newNode);
                    // 避免超过容量
                    size++;
                    if (size > capacity) {
                        DLinkedNode tail = removeTail();    // 如果超出容量，删除双向链表的尾部节点
                        cache.remove(tail.key);             // 删除哈希表中对应的项
                        --size;
                    }
                } else {
                    node.value = value;
                    moveToHead(node);
                }
            }

        private void addToHead(DLinkedNode node) {
            node.prev = head;
            node.next = head.next;
            head.next.prev = node;
            head.next = node;
        }

        private void removeNode(DLinkedNode node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        private void moveToHead(DLinkedNode node) {
            removeNode(node);
            addToHead(node);
        }

        private DLinkedNode removeTail() {
            DLinkedNode res = tail.prev;
            removeNode(res);
            return res;
        }
    }

    /**
     * Your LRUCache object will be instantiated and called as such:
     * LRUCache obj = new LRUCache(capacity);
     * int param_1 = obj.get(key);
     * obj.put(key,value);
     */
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度：对于 put 和 get 都是 O(1)。
// 空间复杂度：O(capacity)，因为哈希表和双向链表最多存储 capacity+1 个元素。