class Solution {
    public int maxProfit(int[] prices) {
        int sell = 0, buy = Integer.MIN_VALUE;
        
        for (int price : prices) {
            sell = Math.max(sell, buy + price);
            buy = Math.max(buy, -price);
        }
        return sell;
    }
}

class Solution {
    public int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE;
        int res = 0;
        for( int i = 0 ; i < prices.length ; i++){
            min = Math.min(prices[i],min);
            res = Math.max(prices[i] - min, res);
        }
        return res;
    }
}