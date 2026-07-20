class Solution {
    public int[] sortArrayByParity(int[] nums) {
        int[] ans = new int[nums.length];
        int index = 0;
        for(int num : nums){
            if(num%2==0){
                ans[index] = num;
                index++;
            }
        }
        for(int num : nums){
            if(num%2!=0){
                ans[index] = num;
                index++;
            }
        }
        return ans;
    }
}