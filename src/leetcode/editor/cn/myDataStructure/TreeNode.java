package leetcode.editor.cn.myDataStructure;

/**
 * @Author TheBetterKong
 * @Description //TODO 树的节点
 * @Date 16:55 2021/5/27
 * @Param
 * @return
 */
public class TreeNode {
    // 属性
    public String key;                // 序号
    public int val = 0;                // 值（数据域）
    public TreeNode left = null;       // 左子节点
    public TreeNode right = null;      // 右子节点
    public boolean isVisted = false;   // 用来标记结点是否被访问到

    // 构造方法
    public TreeNode() {}
    public TreeNode(int val) {
        this.val = val;
    }
    public TreeNode(String  key, int val) {
        this.key = key;
        this.val = val;
    }
    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
