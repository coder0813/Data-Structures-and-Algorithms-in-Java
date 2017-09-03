public class Solution {
    public List<List<String>> printTree(TreeNode root) {
        List<List<String>> res = new ArrayList<>();
        int height = root == null ? 1 : getHeight(root);
        int width = (int)(Math.pow(2, height) - 1);
        List<String> list = new ArrayList<>();
        for(int i = 0; i < width; i++)  list.add("");
        for(int i = 0; i < height; i++)  res.add(new ArrayList<>(list));  
        dfs(root, res, 0, height, 0, width - 1);
        return res;
    }

    public void dfs(TreeNode root, List<List<String>> res, int row, int totalRows, int i, int j) {
        if (row == totalRows || root == null) return;
        res.get(row).set((i+j)/2, Integer.toString(root.val));
        dfs(root.left, res, row+1, totalRows, i, (i+j)/2 - 1);
        dfs(root.right, res, row+1, totalRows, (i+j)/2+1, j);
    }

    public int getHeight(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }
}