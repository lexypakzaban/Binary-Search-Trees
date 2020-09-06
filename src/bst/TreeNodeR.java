package bst;

public class TreeNodeR {
    private String value;
    private TreeNodeR left, right;

    public TreeNodeR(String v)
    {
        value = v;
        left = null;
        right = null;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public TreeNodeR getLeft() {
        return left;
    }

    public void setLeft(TreeNodeR left) {
        this.left = left;
    }

    public TreeNodeR getRight() {
        return right;
    }

    public void setRight(TreeNodeR right) {
        this.right = right;
    }

}
