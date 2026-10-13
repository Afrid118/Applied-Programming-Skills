import java.util.ArrayList;
import java.util.List;

public class Solution {
    private int[] count;

    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        int[][] items = new int[n][2]; // Store [value, original_index]
        for (int i = 0; i < n; i++) {
            items[i][0] = nums[i];
            items[i][1] = i;
        }

        count = new int[n];
        mergeSort(items, 0, n - 1);

        List<Integer> result = new ArrayList<>();
        for (int c : count) {
            result.add(c);
        }
        return result;
    }

    private void mergeSort(int[][] items, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSort(items, left, mid);
        mergeSort(items, mid + 1, right);
        merge(items, left, mid, right);
    }

    private void merge(int[][] items, int left, int mid, int right) {
        int[][] temp = new int[right - left + 1][2];
        int i = left, j = mid + 1, k = 0;
        int rightCount = 0;

        while (i <= mid && j <= right) {
            if (items[i][0] <= items[j][0]) {
                // Elements from the right subarray that are smaller than items[i]
                count[items[i][1]] += rightCount;
                temp[k++] = items[i++];
            } else {
                rightCount++;
                temp[k++] = items[j++];
            }
        }

        while (i <= mid) {
            count[items[i][1]] += rightCount;
            temp[k++] = items[i++];
        }

        while (j <= right) {
            temp[k++] = items[j++];
        }

        System.arraycopy(temp, 0, items, left, temp.length);
    }
}