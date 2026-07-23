/**
 * // This is MountainArray's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface MountainArray {
 *     public int get(int index) {}
 *     public int length() {}
 * }
 */
 
class Solution {
    public int findInMountainArray(int target, MountainArray mountainArr) {
        int peak = findPeak(mountainArr);

        int left = binarySearch(mountainArr, target, 0, peak, true);

        if (left != -1)
            return left;

        return binarySearch(mountainArr, target, peak + 1,
                mountainArr.length() - 1, false);
    }
        int findPeak(MountainArray arr) {

        int start = 0;
        int end = arr.length() - 1;

        while (start < end) {

            int mid = start + (end - start) / 2;

            if (arr.get(mid) < arr.get(mid + 1))
                start = mid + 1;
            else
                end = mid;
        }

        return start;
    }
    int binarySearch(MountainArray arr, int target, int start, int end, boolean asc){
        while(start<=end){
            int mid = start + (end - start) / 2;
            int value = arr.get(mid);

            if(value==target)
            return mid;

            if(asc){
                if(value<target)
                start = mid + 1;
                else
                end = mid - 1;
            }
            else{
                if(value<target)
                end = mid - 1;
                else
                start = mid + 1;
            }
        }
        return -1;
    }
}