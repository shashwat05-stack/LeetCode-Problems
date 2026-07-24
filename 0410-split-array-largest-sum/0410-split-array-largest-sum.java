class Solution {
    public int splitArray(int[] nums, int k) {
        int start = 0;
        int end = 0;

        for(int i = 0; i<nums.length;i++){
            start = Math.max(start, nums[i]);
            end += nums[i];
        }

        //binary search
        while(start < end){
            int mid = start + (end-start)/2;

            int sum = 0;
            int count = 1;
            for(int num : nums){
                if(sum + num > mid){
                    sum = num;
                    count++;
                }
                else{
                    sum += num;
                }
            }
            if(count > k){
                start = mid + 1;
            }
            else {
                end = mid;
            }
        }
        return end;
    }
}