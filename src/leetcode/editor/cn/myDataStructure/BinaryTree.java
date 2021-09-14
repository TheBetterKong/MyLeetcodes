package leetcode.editor.cn.myDataStructure;

import com.sun.source.tree.Tree;

import java.util.*;

/**
 * @Author TheBetterKong
 * @Description //TODO 二叉树的实现
 * @Date 19:38 2021/08/07
 * @Param
 * @return
 */
public class BinaryTree {
    /**
     * 成员变量
     */
    public TreeNode root = null;

    /**
     * 构造方法
     */
    public BinaryTree() {}
    public BinaryTree(TreeNode root) {
        this.root = root;
    }

    /**
     * 一些基础方法
     */
    public boolean isEmpty() {
        return root == null;
    }

    public int height() {
        return getHeight(root);
    }

    public int getHeight(TreeNode node) {
        if (node == null) {
            return 0;
        } else {
            int lheight = getHeight(node.left);
            int rheight = getHeight(node.right);
            return (lheight > rheight) ? (lheight + 1) : (rheight + 1);
        }
    }

    public int size() {
        return getSize(root);
    }

    public int getSize(TreeNode node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + getSize(node.left) + getSize(node.right);
        }
    }

    public TreeNode parent(TreeNode node) {
        return (node == null || root == null || root == node) ? null : getParent(root, node);
    }

    public TreeNode getParent(TreeNode root, TreeNode node) {
        if (root.left == node || root.right == node) {
            return root;
        }

        TreeNode p;
        if ((p = getParent(root.left, node)) != null) {
            return p;
        } else {
            return getParent(root.right, node);
        }
    }

    public void destorySubtree(TreeNode subtree) {
        if (subtree != null) {
            destorySubtree(subtree.left);
            destorySubtree(subtree.right);
            subtree = null;
        }
    }

    /**
     * 从数组创建二叉树
     */
    public TreeNode buildTree(List<Integer> nums) {
        // 创建所有的节点
        List<TreeNode> nodeList = new LinkedList<>();
        for (int num : nums) {
            nodeList.add(new TreeNode(num));
        }

        // 前 length / 2 - 1 个节点是同时存在左右孩子节点的
        for (int i = 0 ; i < nums.size() / 2 - 1; i++) {
            nodeList.get(i).left = nodeList.get(i * 2 + 1);     // 左孩子
            nodeList.get(i).right = nodeList.get(i * 2 + 2);     // 右孩子
        }

        // 最后一个父节点（可能没有右孩子）
        int lastPaIdx = nums.size() / 2 - 1;
        nodeList.get(lastPaIdx).left = nodeList.get(lastPaIdx * 2 + 1);
        if (nums.size() % 2 == 1) {
            nodeList.get(lastPaIdx).right = nodeList.get(lastPaIdx * 2 + 2);
        }

        return nodeList.get(0);
    }

    /**
     * 遍历方法相关
     */
    // 输出节点相关信息
    private void visited(TreeNode node) {
        if (node != null) {
            node.isVisted = true;
            System.out.println("key:" + node.key + "--val:" + node.val);;
        }
    }

    // （1）层次遍历
    public void levelOrder(TreeNode root) {
        if (root == null) {
            return;
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int curLevelSize = queue.size();
            for (int i = 0; i < curLevelSize; i++) {
                TreeNode curNode = queue.poll();
                visited(curNode);
                assert curNode != null;
                if (curNode.left != null) {
                    queue.offer(curNode.left);
                }
                if (curNode.right != null) {
                    queue.offer(curNode.right);
                }
            }
        }
    }

    // （2）深度优先遍历 == 前序遍历
    public void preOrder(TreeNode root) {
        if (root != null) {
            visited(root);
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    public void nonRecPreOrder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                visited(node);
                stack.push(node);
                node = node.left;
            }
            if (!stack.isEmpty()) {
                node = stack.pop();
                node = node.right;
            }
        }
    }

    // （3）中序遍历
    public void inOrder(TreeNode root) {
        if (root != null) {
            inOrder(root.left);
            visited(root);
            inOrder(root.right);
        }
    }

    public void nonRecInOrder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            if (!stack.isEmpty()) {
                node = stack.pop();
                visited(node);
                node = node.right;
            }
        }
    }

    // （4）后序遍历
    public void postOrder(TreeNode root) {
        if (root != null) {
            postOrder(root.left);
            postOrder(root.right);
            visited(root);
        }
    }

    public void nonRecPostOrder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;   // 用于遍历的节点
        TreeNode preNode = node;    // 记录上一个输出节点
        while (node != null) {
            // 找最左端节点
            while (node.left != null) {
                stack.push(node);
                node = node.left;
            }
            // 节点无右子树或者右子树已经输出
            while (node != null && (node.right == null || node.right == preNode)) {
                visited(node);
                preNode = node;
                if (stack.isEmpty()) {
                    return;
                }
                node = stack.pop(); // 相当于回退至其父节点
            }
            // 处理右子树
            stack.push(node);
            assert node != null;
            node = node.right;
        }
    }

    /**
     * 一些高级扩展方法
     */

    // 判断以 p q 为根节点的树是否相同（结构 + 节点值 都相同）
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        }

        Queue<TreeNode> p_queue = new LinkedList<>();
        Queue<TreeNode> q_queue = new LinkedList<>();
        p_queue.offer(p);
        q_queue.offer(q);

        while (!p_queue.isEmpty() && !q_queue.isEmpty()) {
            TreeNode node_p = p_queue.poll();
            TreeNode node_q = q_queue.poll();

            // 值比较
            if (node_p.val != node_q.val) {
                return false;
            }

            // 结构比较
            TreeNode left_p = node_p.left, right_p = node_p.right;
            TreeNode left_q = node_q.left, right_q = node_q.right;

            if (left_p == null ^ left_q == null) {
                return false;
            }
            if (right_p == null ^ right_q == null) {
                return false;
            }

            if (left_p != null) p_queue.offer(left_p);
            if (right_p != null) p_queue.offer(right_p);
            if (left_q != null) q_queue.offer(left_q);
            if (right_q != null) q_queue.offer(right_q);
        }
        return p_queue.isEmpty() && q_queue.isEmpty(); // 都为空才说明结构相同
    }


    // 判断以 root 为根的树是否为平衡二叉树
    public boolean isBalanced(TreeNode root) {
        return balanced(root) != -1;
    }

    private int balanced(TreeNode node) {
        if (node == null) return 0;
        int leftHeight, rightHeight;
        // 如果左子树已经返回 -1 了就不需要再递归右子树了，直接返回 -1
        if ((leftHeight = balanced(node.left)) == -1
                || (rightHeight = balanced(node.right)) == -1
                || Math.abs(leftHeight - rightHeight) > 1)
            return -1;
        return Math.max(leftHeight, rightHeight) + 1;
    }

    /**
     * 测试用例：
     *      手动创建一棵二叉树，如下所示：
     *               1
     *          2          3
     *      4     5            6
     *
     *               A
     *         B          C
     *      D     E            F
     */
    public void createExampleBinTree(){
        TreeNode root = new TreeNode("A", 1);
        TreeNode newNodeB = new TreeNode("B", 2);
        TreeNode newNodeC = new TreeNode("C", 3);
        TreeNode newNodeD = new TreeNode("D", 4);
        TreeNode newNodeE = new TreeNode("E", 5);
        TreeNode newNodeF = new TreeNode("F", 6);

        newNodeC.right = newNodeF;
        newNodeB.left = newNodeD;
        newNodeB.right = newNodeE;

        root.left = newNodeB;
        root.right = newNodeC;

        this.root = root;
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.createExampleBinTree();

        System.out.println("the height of the tree is " + tree.height());
        System.out.println("the size of the tree is " + tree.size());
        System.out.println("the parent of the node E is " + tree.parent(tree.root.left.right).key);

        System.out.println("*******(层次遍历)[ABDECF]遍历*****************");
        tree.levelOrder(tree.root);

        System.out.println("*******(前序遍历)[ABDECF]遍历*****************");
        tree.preOrder(tree.root);
        System.out.println("***非递归实现****(前序遍历)[ABDECF]遍历*****************");
        tree.nonRecPreOrder(tree.root);

        System.out.println("*******(中序遍历)[DBEACF]遍历*****************");
        tree.inOrder(tree.root);
        System.out.println("***非递归实现****(中序遍历)[DBEACF]遍历*****************");
        tree.nonRecInOrder(tree.root);

        System.out.println("*******(后序遍历)[DEBFCA]遍历*****************");
        tree.postOrder(tree.root);
        System.out.println("***非递归实现****(后序遍历)[DEBFCA]遍历*****************");
        tree.nonRecPostOrder(tree.root);
    }
}
