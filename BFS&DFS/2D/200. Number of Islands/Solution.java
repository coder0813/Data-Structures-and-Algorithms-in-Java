// BFS
class Solution {
    public int numIslands(char[][] grid) {
        int res = 0;
        if( grid == null || grid.length == 0) return res;
        if( grid[0] == null || grid[0].length == 0 ) return res;
        int row = grid.length;
        int col = grid[0].length;
        int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        for( int i = 0 ; i < row ; i ++) {
            for(int j = 0 ; j < col ; j++ ){
                if(grid[i][j] == '1'){
                    bfs(grid, row, col, i, j, dir);
                    res++;   
                }
            }
        }
        return res;
       
    }
    public void bfs(char[][] grid, int row, int col, int x, int y, int[][] dir){
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.offer(x*col+y);
        grid[x][y] = '0';
        
        while(!queue.isEmpty()){
            int cur = queue.poll();
            int curX = cur/col;
            int curY = cur%col;
            for(int i = 0 ; i < dir.length ; i++ ){
                int tempX = curX + dir[i][0];
                int tempY = curY + dir[i][1];
                if( isValid(tempX, tempY , row , col) && grid[tempX][tempY] == '1'){
                    queue.offer(tempX  * col + tempY );
                    grid[tempX][tempY] = '0'; 
                }
                
            }
        }
    }
    private boolean isValid( int x , int y , int row , int col){
        return x >=0 && x < row && y >= 0 && y < col;
    }
}

//DFS
public class Solution {
    public int numIslands(char[][] grid) {
        int res = 0;
        if( grid == null || grid.length == 0) return res;
        if( grid[0] == null || grid[0].length == 0 ) return res;
        int row = grid.length;
        int col = grid[0].length;
        int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        for( int i = 0 ; i < row ; i ++) {
            for(int j = 0 ; j < col ; j++ ){
                if(grid[i][j] == '1'){
                    dfs(grid, row,col, i, j , dir);
                    res++;   
                }
            }
        }
        return res;
        
    }
    private void dfs(char[][] grid, int row, int col, int x ,int y,int[][] dir){
        grid[x][y] = '0';
        for( int i = 0 ; i < dir.length ; i++ ){
            int tempX = x + dir[i][0];
            int tempY = y + dir[i][1];
            if( isValid(tempX, tempY , row , col) && grid[tempX][tempY] == '1'){
                dfs(grid,row,col,tempX,tempY,dir);
            }            
        }
    }
    private boolean isValid( int x , int y , int row , int col){
        return x >=0 && x < row && y >= 0 && y < col;
    }
}

// Union Find
class Solution {
    int[][] dirs = {{0, -1}, {-1, 0}};
    public int numIslands(char[][] grid) {
        int res = 0;
        if( grid == null || grid.length == 0) return res;
        if( grid[0] == null || grid[0].length == 0 ) return res;
        int row = grid.length;
        int col = grid[0].length;
        
        int[] unionSet = new int[row * col];
        Arrays.fill(unionSet, -1);
        int[] size = new int[row * col];
        
        for (int i = 0; i < row; i++) {
            for (int j = 0 ; j < col; j++) {
                int id = col * i + j;
                if ( grid[i][j] == '1' && unionSet[id] == -1) {
                    res++;
                    unionSet[id] = id;
                    size[id] = 1;
                    
                    for (int[] dir : dirs) {
                        int indexX = i + dir[0];
                        int indexY = j + dir[1];

                        if( isValid(indexX, indexY , row , col) && grid[indexX][indexY] == '1'){
                            int pos = col * indexX + indexY;
                            int root = find(unionSet, pos);
                            if (root != id) {
                                res--;
                                // WeightedQuickUnion
                                id = union(id, root, unionSet, size);
                            }
                        }
                    }
                }
            }
        }
        return res;
    }
    
    //quick find with path compression 
    public int find(int[] unionSet, int id) {
        while (unionSet[id] != id) {
            unionSet[id] = unionSet[unionSet[id]];
            id = unionSet[id];
        }
        return i;
    }
    // WeightedQuickUnion
    public int union(int root1, int root2, int[] unionSet, int[] size) {
        if (size[root1] < size[root2]) {
            unionSet[root1] = root2;
            size[root2] += size[root1];
            //size[root1] = size[root2];
            return root2;
        } else {
            unionSet[root2] = root1;
            size[root1] += size[root2];
            //size[root2] = size[root1];
            return root1;
        }
    }
    
    private boolean isValid( int x , int y , int row , int col){
        return x >=0 && x < row && y >= 0 && y < col;
    }
}



// Remove islands smaller than k
public class remove {
    public static void main(String[] args) {
        int[][] n = {
                {1,1,1,1,0},
                {1,1,0,0,0},
                {0,0,0,1,1},
                {1,1,0,1,0},
        };
        remove sol = new remove();
        sol.solve(n,3);

        for (int i = 0; i < n.length; i++) {
            for (int j = 0; j < n[0].length; j++) {
                System.out.print(n[i][j]);
            }
            System.out.println();
        }
    }

    int size = 0;

    public void solve(int[][] n, int k) {
        if (n == null || n.length == 0) return;
        if (n[0] == null || n[0].length == 0) return;
        int row = n.length;
        int col = n[0].length;
        int[][] temp = new int[row][col];

        for( int i = 0 ; i < row ; i++){
            for( int j = 0 ; j < col ; j++){
                temp[i][j] = n[i][j];
            }

        }

        for (int i = 0; i < row ; i++) {
            for (int j = 0; j < col ; j++) {
                if (temp[i][j] == 1) {
                    size = 1;
                    int area = checkSize(temp,row,col,i,j);
                    if (area > k) {
                        size = 0;
                    }

                    if( area <= k ){
                        remove(n,row,col,i,j);
                    }
                }
            }
        }
    }
    private int checkSize(int[][] temp, int row, int col, int x, int y) {
        temp[x][y] = 0;
        for (int i = -1; i <= 1 ; i++) {
            for ( int j = - 1; j <= 1 ; j++ ){
                if( isValid( i , j , x , y , row , col ) && temp[x+i][y+j] == 1 ){
                    size++;
                    checkSize( temp , row , col , x+i, y+j);
                }
            }
        }
        return size;
    }
    public  void  remove(int[][] n, int row, int col, int x, int y){
        n[x][y] = 0;
        for(int i = -1 ; i <= 1 ; i++ ){
            for( int j = -1 ; j <= 1 ; j++ ){
                if(isValid(i,j,x,y,row,col) && n[x+i][y+j] == 1){
                    remove(n,row,col,x+i,y+j);
                }
            }
        }

    }
    private boolean isValid(int i , int j , int x , int y , int row , int col){
        return i+x >= 0 && i+x < row && j+y >= 0 && j+y < col;
    }
}
