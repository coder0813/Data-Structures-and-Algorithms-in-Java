class Solution {
    public void connect(TreeLinkNode root) {
        TreeLinkNode node = root, dummy = new TreeLinkNode(-1);
        
        // Different levels
        while( node != null ){ 
            TreeLinkNode cur = dummy;
            
            // Same level
            while( node != null ){
                if( node.left != null ){
                    cur.next = node.left;
                    cur = cur.next;
                }
                if( node.right != null ){
                    cur.next = node.right;
                    cur = cur.next;
                }
                // move node to next in same level
                node = node.next; 
            }
            
            node = dummy.next;
            dummy.next = null;  
        }
    }
}