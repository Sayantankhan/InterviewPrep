import tree.TreeNode;
import java.util.*;

public class SerializeDeserializeBinaryTree {
    // Serialize and Deserialize Binary Tree
    // for bst use inorder but as its bt use preorder
    // we can also do level by level

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        String s = serializeRec(root);
        System.out.println(s);
        return s;
    }

    // preorder - N - L - R
    String serializeRec(TreeNode root) {
        if(root == null) return "null";

        return ""+ root.val + "," + serializeRec(root.left) + "," + serializeRec(root.right);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] data_array = data.split(",");
        List<String> data_list = new LinkedList<String>(Arrays.asList(data_array));
        return deserializeRec(data_list);
    }

    TreeNode deserializeRec(List<String> l) {
        if(l.get(0).equals("null")) {
            l.remove(0);
            return null;
        }

        TreeNode node = new TreeNode(Integer.valueOf(l.get(0)));
        l.remove(0);
        node.left = deserializeRec(l);
        node.right = deserializeRec(l);

        return node;
    }
}
