package leetcode.editor.cn;

/**
 * @Author TheBetterKong
 * @Description //TODO 树的节点
 * @Date 16:55 2021/5/27
 * @Param
 * @return
 */
public class TreeNode {
    // 属性
    int key = 0;                // 序号
    int val = 0;                // 值（数据域）
    TreeNode left = null;       // 左子节点
    TreeNode right = null;      // 右子节点
    boolean isVisted = false;   // 用来标记结点是否被访问到

    // 构造方法
    TreeNode() {}
    TreeNode(int val) {
        this.val = val;
    }
    TreeNode(int key, int val) {
        this.key = key;
        this.val = val;
    }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
