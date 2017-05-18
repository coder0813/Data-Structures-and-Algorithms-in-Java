public class Solution {
    public void connect(TreeLinkNode root) {
        if( root == null ) return;
        TreeLinkNode preHead = root, dummy, cur;
        root.next = null;
        while( preHead != null ){
            // for current level
            dummy = new TreeLinkNode(-1);
            cur = dummy;
            // for every node of last level
            while( preHead != null ){
                if( preHead.left != null ){
                    cur.next = preHead.left;
                    cur = cur.next;
                }
                if( preHead.right != null ){
                    cur.next = preHead.right;
                    cur = cur.next;
                }
                preHead = preHead.next;
            }
            preHead = dummy.next;
        }
    }
}