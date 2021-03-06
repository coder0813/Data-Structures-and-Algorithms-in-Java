# Typical Solution for Calculating Subarray Sum 

### 1.Use slidng window, move left point when the current sum is bigger than result

```java
class myCode {
    public static void main (String[] args) throws java.lang.Exception {        
        int[] nums = new int[]{2,3,1,5,8};
        System.out.println(subArraySum(nums,18));    
    }
    
    public static boolean subArraySum(int[] nums, int k){
        if( nums == null || nums.length == 0 ) return false;
        int start = 0 , end = 0 , sum = 0;
        
        while( end < nums.length ){
            sum += nums[end++];
            if( sum == k ) {
                return true;
            }
            while( sum > k ){
                sum -= nums[start];
                start++;
            }
        }
        return false;
    }
}
```

### 2.Use HashMap or HashSet to save prefix sum results

```java
class myCode {
    public static void main (String[] args) throws java.lang.Exception {        
        int[] nums = new int[]{0,2,3,1,5,-1,-1,8};
        System.out.println(subArraySum(nums,7));    
    }
    
    public static boolean subArraySum(int[] nums, int k){
        if( nums == null || nums.length == 0 ) return false;
        Map<Integer,Integer> map = new HashMap<>();

        int sum = 0;
        for(int i = 0 ; i < nums.length ; i++){
            sum += nums[i];
            map.putIfAbsent(sum,1);
            if(map.get(sum-k) != null ) return true;
        }
        return false;
    }
}
```