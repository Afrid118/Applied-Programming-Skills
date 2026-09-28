class Solution {
    public int orangesRotting(int[][] grid) {
        int n= grid.length, m= grid[0].length;
        boolean visit[][]= new boolean[n][m];
        Queue<int[]> q= new LinkedList<>();
        int fresh=0;

        // push all rotten orange coordinates in the queue
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<m; j++)
            {
                if(grid[i][j]==2)
                {
                    q.offer(new int[]{i,j});
                    visit[i][j]= true;
                }
                else if(grid[i][j]==1)
                    fresh++;            // count fresh oranges
            }
        }
        
        // if no fresh orange exists
        if(fresh==0)
            return 0;

        int minutes=-1;
        int directions[][]= {{-1,0}, {1,0}, {0,-1}, {0,1}};

        while(!q.isEmpty())
        {
            int size= q.size();
            minutes++;

            for(int i=0; i<size; i++)
            {
                int arr[]= q.poll();
                for(int dir[]: directions)
                {
                    int x= arr[0] + dir[0]; 
                    int y= arr[1] + dir[1];

                    if(x<0 || x>=n || y<0 || y>=m || grid[x][y]==0 || visit[x][y])
                        continue;
                    
                    visit[x][y]= true;
                    fresh--;
                    q.offer(new int[]{x,y});
                }
            }
        }

        // if no fresh oranges left then return minutes otherwise -1
        return fresh==0 ? minutes : -1;
    }
}