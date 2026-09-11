class Solution {
    public int totalNumbers(int[] digits) {
        int ans = 0;

        for (int i = 1; i <= 9; i++) {
            for (int j = 0; j <= 9; j++) {
                for (int k = 0; k <= 8; k += 2) {

                    int[] count = new int[10];

                    for (int d : digits) {
                        count[d]++;
                    }

                    count[i]--;
                    count[j]--;
                    count[k]--;

                    if (count[i] >= 0 && count[j] >= 0 && count[k] >= 0) {
                        ans++;
                    }
                }
            }
        }

        return ans;
    }
}