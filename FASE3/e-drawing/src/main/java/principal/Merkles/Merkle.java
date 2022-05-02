package principal.Merkles;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Merkle {
    public static HashNode generateTree(ArrayList<String> dataBlocks) throws NoSuchAlgorithmException {
        ArrayList<HashNode> childHashNodes = new ArrayList<>();

        for (String message : dataBlocks) {
            childHashNodes.add(new HashNode(null, null, CodificacionHash.sha256(message)));
        }

        return buildTree(childHashNodes);
    }

    public static HashNode buildTree(ArrayList<HashNode> children) throws NoSuchAlgorithmException {
        ArrayList<HashNode> parents = new ArrayList<>();

        while (children.size() != 1) {
            int index = 0, length = children.size();
            while (index < length) {
                HashNode leftChild = children.get(index);
                HashNode rightChild = null;

                if ((index + 1) < length) {
                    rightChild = children.get(index + 1);
                } else {
                    rightChild = new HashNode(null, null, leftChild.getHash());
                }

                String parentHash = CodificacionHash.sha256(leftChild.getHash() + rightChild.getHash());
                parents.add(new HashNode(leftChild, rightChild, parentHash));
                index += 2;
            }
            children = parents;
            parents = new ArrayList<>();
        }
        return children.get(0);
    }

    public static void printLevelOrderTraversal(HashNode root) {
        if (root == null) {
            return;
        }

        if ((root.getLeft() == null && root.getRight() == null)) {
            System.out.println(root.getHash());
        }else{
        Queue<HashNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);

        while (!queue.isEmpty()) {
            HashNode HashNode = queue.poll();
            if (HashNode != null) {
                System.out.println("**** " + HashNode.getHash());
            } else {
                System.out.println();
                if (!queue.isEmpty()) {
                    queue.add(null);
                }
            }

            if (HashNode != null && HashNode.getLeft() != null) {
                queue.add(HashNode.getLeft());
            }

            if (HashNode != null && HashNode.getRight() != null) {
                queue.add(HashNode.getRight());
            }
        }
        }
    }
}
