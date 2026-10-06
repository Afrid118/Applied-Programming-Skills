class Solution {
    public int findJudge(int n, int[][] trust) {
        int[] trustsSomeone = new int[n + 1];
        int[] trustedBy = new int[n + 1];
        for (int[] relation : trust) {
            int personA = relation[0];
            int personB = relation[1];
            
            trustsSomeone[personA]++; 
            trustedBy[personB]++;    
        }
        for (int i = 1; i <= n; i++) {
            if (trustsSomeone[i] == 0 && trustedBy[i] == n - 1) {
                return i;
            }
        }
        return -1;
    }
}