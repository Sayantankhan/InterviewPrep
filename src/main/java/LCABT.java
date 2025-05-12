import java.util.*;

public class LCABT {
    //Lowest Common Ancestor of a Binary III
    static class BinaryTreeNode {

        int data;
        BinaryTreeNode left;
        BinaryTreeNode right;
        BinaryTreeNode parent;

        BinaryTreeNode(int data) {
            this.data = data;
            left = null;
            right = null;
            parent = null;
        }
    }

    public static BinaryTreeNode lowestCommonAncestor(BinaryTreeNode n1, BinaryTreeNode n2) {
        // Write your code here.
        Set<BinaryTreeNode> set = new HashSet<>();
        BinaryTreeNode root = null;
        set.add(n1);
        set.add(n2);

        while(n1.parent != null && n2.parent != null) {
            if(set.contains(n1.parent)) return n1.parent;
            else set.add(n1.parent);

            if(set.contains(n2.parent)) return n2.parent;
            else set.add(n2.parent);

            n1 = n1.parent;
            n2 = n2.parent;
        }

        while(n1.parent != null) {
            if(set.contains(n1.parent)) return n1.parent;
            else set.add(n1.parent);
            n1 = n1.parent;
        }

        while(n2.parent != null) {
            if(set.contains(n2.parent)) return n2.parent;
            else set.add(n2.parent);
            n2 = n2.parent;
        }

        return null;
    }

    // o(h)
    public BinaryTreeNode lca(BinaryTreeNode p, BinaryTreeNode q) {
        List<BinaryTreeNode> pnode = new ArrayList<>();
        List<BinaryTreeNode> qnode = new ArrayList<>();

        findPathToRoot(p, pnode);
        findPathToRoot(q, qnode);

        int psize = pnode.size()-1;
        int qsize = qnode.size()-1;

        while(psize >= 0 && qsize >= 0 && pnode.get(psize) == qnode.get(qsize)) {
            psize--;
            qsize--;
        }

        return qnode.get(qsize+1);
    }

    void findPathToRoot(BinaryTreeNode x, List<BinaryTreeNode> nodes) {
        nodes.add(x);
        if(x.parent == null) return;

        findPathToRoot(x.parent, nodes);
    }

    public static void main(String[] args) {
        // 3 -1 8 13 -1 19 -1 18 -1 -1 1 -1 -1
        BinaryTreeNode root = new BinaryTreeNode(3);
        BinaryTreeNode right = new BinaryTreeNode(8);
        BinaryTreeNode rl = new BinaryTreeNode(13);
        BinaryTreeNode rll = new BinaryTreeNode(19);
        BinaryTreeNode rlll = new BinaryTreeNode(18);
        BinaryTreeNode rlllr = new BinaryTreeNode(1);


        rlllr.parent = rlll;
        rlll.right = rlllr;
        rlll.parent = rll;

        rll.left = rlll;
        rll.parent = rl;

        rl.left = rll;
        rl.parent = right;

        right.left = rl;
        right.parent = root;

        root.right = right;

        System.out.println(lowestCommonAncestor(right, rlllr).data);

    }
}
