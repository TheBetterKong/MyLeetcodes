package leetcode.editor.cn;

import java.util.*;

/**
 * @Author TheBetterKong
 * @Description //TODO 二叉树的实现
 * @Date 17:00 2021/5/27
 * @Param
 * @return
 */
public class BinaryTree {
    TreeNode root = null;  // 二叉树根节点

    BinaryTree() {}

    BinaryTree(TreeNode root){
        this.root = root;
    }

    /**
     * 相关方法
     */
    // 判断根节点是否为空
    public boolean isEmpty(){
        return root == null;
    }

    // 树的高度
    public int height(){
        return height(root);
    }

    private int height(TreeNode subTree){
        if(subTree == null)
            return 0;//递归结束：空树高度为0
        else{
            int i = height(subTree.left);
            int j = height(subTree.right);
            return (i < j) ? (j + 1) : (i + 1);
        }
    }

    // 树的规模（树中结点个数）
    public int size(){
        return size(root);
    }

    private int size(TreeNode subTree) {
        if(subTree == null){
            return 0;
        } else {
            return 1 + size(subTree.left) + size(subTree.right);
        }
    }

    // 查找某结点的父结点
    public TreeNode parent(TreeNode element){
        return (root == null || root == element) ? null : parent(root, element);
    }

    // 以 subTree 为根的树中，element 的父结点
    public TreeNode parent(TreeNode subTree,TreeNode element){
        if(subTree == null)
            return null;
        if(subTree.left == element || subTree.right == element)
            //返回父结点地址
            return subTree;
        TreeNode p;
        //现在左子树中找，如果左子树中没有找到，才到右子树去找
        if((p = parent(subTree.left, element)) != null)
            //递归在左子树中搜索
            return p;
        else
            //递归在右子树中搜索
            return parent(subTree.right, element);
    }

    // 返回左孩子结点
    public TreeNode getLeftChildNode(TreeNode element){
        return (element != null) ? element.left : null;
    }

    // 返回右孩子结点
    public TreeNode getRightChildNode(TreeNode element){
        return (element != null) ? element.right : null;
    }

    // 返回根节点
    public TreeNode getRoot(){
        return root;
    }

    // 删除根为 subTree 的子树
    //   在释放某个结点时，该结点的左右子树都已经释放，
    //   所以应该采用后续遍历，当访问某个结点时将该结点的存储空间释放
    public void destroy(TreeNode subTree){
        if(subTree != null){
            destroy(subTree.left);
            destroy(subTree.right);
            subTree = null;
        }
    }

    // 遍历二叉树
    public void traverse(TreeNode subTree){
        System.out.println("key:" + subTree.key + "--val:" + subTree.val);;
        traverse(subTree.left);
        traverse(subTree.right);
    }

    // 层次遍历
    public void levelOrder(TreeNode subTree) {
        if (subTree == null) {
            return;
        }
        // 借助队列实现深度优先搜索
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        // 将第一层节点加入
        queue.offer(subTree);
        while (!queue.isEmpty()) {
            int curlevelSize = queue.size();                    // 某一层的节点数
            for (int i = 1; i <= curlevelSize; i++) {
                TreeNode node = queue.poll();
                visted(node);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
    }

    //前序遍历
    public void preOrder(TreeNode subTree){
        if(subTree != null){
            visted(subTree); // 输出根节点
            preOrder(subTree.left); // 递归遍历左子树
            preOrder(subTree.right); // 递归遍历右子树
        }
    }

    //中序遍历
    public void inOrder(TreeNode subTree){
        if(subTree != null){
            inOrder(subTree.left);
            visted(subTree);
            inOrder(subTree.right);
        }
    }

    //后续遍历
    public void postOrder(TreeNode subTree) {
        if (subTree != null) {
            postOrder(subTree.left);
            postOrder(subTree.right);
            visted(subTree);
        }
    }

    //前序遍历的非递归实现
    public void nonRecPreOrder(TreeNode p){
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode node = p;
        while(node != null || stack.size() > 0) {
            while(node != null){
                visted(node);
                stack.push(node);
                node = node.left;
            }
            while (stack.size() > 0) {
                node = stack.pop();
                node = node.right;
            }
        }
    }

    //中序遍历的非递归实现
    public void nonRecInOrder(TreeNode p){
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode node = p;
        while(node != null || stack.size() > 0) {
            //存在左子树
            while(node != null) {
                stack.push(node);
                node = node.left;
            }
            //栈非空
            if(stack.size() > 0){
                node = stack.pop();
                visted(node);
                node = node.right;
            }
        }
    }

    //后序遍历的非递归实现
    public void noRecPostOrder(TreeNode p){
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode node = p;
        while(p != null) {
            //左子树入栈
            for(; p.left != null; p = p.left){
                stack.push(p);
            }
            //当前结点无右子树或右子树已经输出
            while(p != null && (p.right == null || p.right == node)) {
                visted(p);
                //纪录上一个已输出结点
                node = p;
                if(stack.empty())
                    return;
                p = stack.pop();
            }
            //处理右子树
            stack.push(p);
            p = p.right;
        }
    }

    // 被访问到就输出
    public void visted(TreeNode subTree){
        subTree.isVisted = true;
        System.out.println("key:"+subTree.key+"--val:"+subTree.val);;
    }


    //测试
    /**
     * 手动创建一棵二叉树，如下所示：
     *               1
     *          2          3
     *      4     5            6
     *
     *               A
     *         B          C
     *      D     E            F
     */
    public void createExampleBinTree(TreeNode root){
        BinaryTree binTreeRoot = new BinaryTree(root);
        TreeNode newNodeB = new TreeNode(2);
        TreeNode newNodeC = new TreeNode(3);
        TreeNode newNodeD = new TreeNode(4);
        TreeNode newNodeE = new TreeNode(5);
        TreeNode newNodeF = new TreeNode(6);

        newNodeC.right = newNodeF;
        newNodeB.left = newNodeD;
        newNodeB.right = newNodeE;

        root.left = newNodeB;
        root.right = newNodeC;
    }

    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree(new TreeNode(1));
        bt.createExampleBinTree(bt.root);
        System.out.println("the size of the tree is " + bt.size());
        System.out.println("the height of the tree is " + bt.height());

        System.out.println("*******(层次遍历)[ABDECF]遍历*****************");
        bt.levelOrder(bt.root);

        System.out.println("*******(前序遍历)[ABDECF]遍历*****************");
        bt.preOrder(bt.root);

        System.out.println("*******(中序遍历)[DBEACF]遍历*****************");
        bt.inOrder(bt.root);

        System.out.println("*******(后序遍历)[DEBFCA]遍历*****************");
        bt.postOrder(bt.root);

        System.out.println("***非递归实现****(前序遍历)[ABDECF]遍历*****************");
        bt.nonRecPreOrder(bt.root);

        System.out.println("***非递归实现****(中序遍历)[DBEACF]遍历*****************");
        bt.nonRecInOrder(bt.root);

        System.out.println("***非递归实现****(后序遍历)[DEBFCA]遍历*****************");
        bt.noRecPostOrder(bt.root);
    }
}
