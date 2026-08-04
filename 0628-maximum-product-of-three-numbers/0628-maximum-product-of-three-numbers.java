class Solution {
    public int maximumProduct(int[] n) {
        Arrays.sort(n);
        return Math.max(n[n.length - 1] * n[n.length - 2] * n[n.length - 3],
        n[n.length - 1] * n[0] * n[1]);
    }
}