class Solution {
    public int[] twoSum(int[] nums, int target) {
        
        HashMap<Integer, Integer> map = new HashMap<>();
        int[]res = new int[2];
        for(int i = 0 ;i<nums.length;i++){
            if(map.containsKey(target - nums[i])){
              // i will be do something.
                res[0] = map.get(target - nums[i]);
                res[1] = i;
            }else{
              //put the .
                map.put(nums[i], i);
            }
        }
        
        return res;
    }
}