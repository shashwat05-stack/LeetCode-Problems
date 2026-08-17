class Solution {
    public int countOdds(int low, int high) {
       int total = high - low + 1;
       int result = total/2;
       if(total % 2 != 0 && low % 2 != 0){
          result = result + 1;
       }
       return result;
    }
}