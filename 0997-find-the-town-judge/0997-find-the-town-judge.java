class Solution {
    public int findJudge(int n, int[][] trust) {
        int[] trustsSomeone = new int[n + 1];
        int[] trustedBy = new int[n + 1];
        for (int i = 0; i < trust.length; i++) {
    int[] relation = trust[i];
    int personaA = relation[0];
    int personB = relation[1];
    
    trustsSomeone[personaA]++;
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