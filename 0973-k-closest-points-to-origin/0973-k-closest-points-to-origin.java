class Solution {
    public int[][] kClosest(int[][] points, int k) {

        PriorityQueue<int[]> pq =
            new PriorityQueue<>((a, b) -> b[0] - a[0]);

        for(int i = 0; i < points.length; i++) {

            int x = points[i][0];
            int y = points[i][1];

            int distance = x * x + y * y;

            if(pq.size() < k) {
                pq.offer(new int[]{distance, i});
            }
            else {
                pq.offer(new int[]{distance, i});
                pq.poll();
            }
        }

        int[][] result = new int[k][];

        for(int i = 0; i < k; i++) {
            result[i] = points[pq.poll()[1]];
        }

        return result;
    }
}