package com.thuannt.algorithm.leetcode.interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ArrayAndString {
    
    //3Sum: Give an integere nums find the triples that have different index
    // and nums[i] + nums[j] + nums[k] = 0
    protected static class ThreeSum {
        //Bad solution, it has a O(n^3) running time. Need to find another way
        private static List<Integer> solve(int[] nums, int i) {
            List<Integer> result = new ArrayList<Integer>();
            int negated = -1 * nums[i];
            for(int j = i + 1; j < nums.length -1; j++) {
                for(int k = j + 1; k < nums.length - 2; k++) {
                    if((nums[i] + nums[k]) == negated) {
                        result.add(nums[i]);
                        result.add(nums[j]);
                        result.add(nums[k]);
                    }
                }
            }
            return result;
        }
        
        public List<List<Integer>> threeSum(int[] nums) {
            Map<Integer, List<Integer>> valueWithIndices = new HashMap<Integer, List<Integer>>();
            for(int i = 0; i < nums.length; i++) {
                valueWithIndices.computeIfAbsent(nums[i], value -> new ArrayList<>()).add(i);
            }
            List<Integer> values = new ArrayList<Integer>();
            int index = 0;
            while(index < nums.length) {
                
                index++;
            }
            return null;
        }
    }
    
    protected static class InplaceDuplicated {
        //Move all the duplicate to the end of the array.
        // => bad
        public static int mySolution(int[] nums) {
            int subtraction = 0;
            for(int i = 0; i < nums.length; i++) {
                if(i+subtraction >= nums.length) break;
                int tmp = 0;
                int duplicated = 0;
                for(int j = i + 1; j < nums.length; j++) {
                    if(nums[j] == nums[i]) {
                        duplicated++;
                        tmp++;
                        subtraction++;
                    }
                    if(nums[j] != nums[i] && duplicated > 0) {
                        int tmpValue = nums[j - tmp];
                        nums[j - tmp] = nums[j];
                        nums[j] = tmpValue;
                    }
                }
            }
            return nums.length - subtraction;
        }
        
        /** leetcode user solution
         * @param nums
         * @return
         */
        public int removeDuplicates(int[] nums) {
            if(nums.length == 0){
                return 0;
            }
            int i = 0;
            for (int j = 1; j < nums.length; j++){
                if(nums[i] != nums[j]){
                    i++;
                    nums[i] = nums[j];
                }
            }
            return i+1;
        }
    }
    
    protected static class TimeBuySellStock {
        
        public int maxProfit(int[] prices) {
            if(prices.length < 1) return 0;
            int max = 0;
            for(int i = 1; i < prices.length; i++) {
                if(prices[i] > prices[i-1]) {
                    max += prices[i] - prices[i-1];
                }
            }
            return max;
        }
        
        public static void reverseString(char[] s) {
            char lastIdx = s[s.length - 1];
            for(int i = 0; i < s.length / 2; i++) {
                char tmp = s[i];
                s[i] = s[lastIdx - i];
                s[lastIdx - i] = s[i];
            }
        }
    }
    
    protected static class RotateArray {
        
        // My solution
        public static void rotate(int[] nums, int k) {
            if(nums.length < 2) return ;
            if(nums.length < k) k = k % nums.length;
            int lastIdx = nums.length - 1;
            int[] temp = new int[nums.length];
            for(int i = 0; i < nums.length; i++) {
                if(i + k <= lastIdx) {
                    temp[i+k] = nums[i];
                } else {
                    temp[i+k - nums.length] = nums[i];
                }
            }
            for(int j = 0; j < temp.length; j++) {
                nums[j] = temp[j];
            }
            temp = null;
        }
        
        //leetcode best solution
        public void rotateLeetCode(int[] nums, int k) {
            int n = nums.length;
            k = k%n;
             //reverse 0 n-1
            reverse(nums, 0 , n-1);
            //reverse 0 to k
            reverse(nums, 0 ,k-1);
            //reverse k to n-1
            reverse(nums, k, n-1);
        }
        
        void reverse(int[] nums, int start, int end){
            while(start<=end){
                int temp = nums[start];
                nums[start]=nums[end];
                nums[end]=temp;
                start++;end--;
            }
        }
    }
    
    protected static class ContainDuplicated {
        public boolean containsDuplicate(int[] nums) {
            Map<Integer, Boolean> container = new HashMap<Integer, Boolean>();
            for(int i = 0; i < nums.length; i++) {
                if(container.get(nums[i]) == null) {
                    container.put(nums[i], true);
                } else return true;
            }
            return false;
        }
    }
    
    protected static class SingleNumber {
        
        // I cannot solve it for naive algorithm with linear runtime & constant extra space
        public static int singleNumber(int[] nums) {
            if(nums.length < 2) return nums[0];
            int sumPositive = 0;
            int sumNegative = 0;
            for(int i = 0; i < nums.length; i++) {
                if(nums[i] >= 0) {
                    sumPositive += nums[i];
                } else sumNegative+=nums[i];
            }
            if(sumPositive % 2 != 0) {
               for(int j = 0; j < nums.length; j++) {
                   if(nums[j] >= 0) {
                       int temp = sumPositive - nums[j];
                       if(temp % 2 == 0) {
                           return nums[j];
                       }
                   }
               }
            } else if(sumPositive % 2 == 0) {
                //Handle more case...
            } else {
                for(int k = 0; k < nums.length; k++) {
                    if(nums[k] < 0) {
                        int temp = sumNegative - nums[k];
                        if(-1*temp % 2 == 0) {
                            return nums[k];
                        }
                    }
                }
            }
            return 0;
        }
        //Using XOR bitwise operator, a ^ a = 0; 0 ^ a = a
        //Duplicated number get wiped and return only the single number
        public static int singNumberWithXorOperator(int[] nums) {
            int result = 0;
            for(int i = 0; i < nums.length; i++) {
                result = result ^ nums[i];
            }
            return result;
        }
        
        public int improveVersion(int [] nums) {
            int res = nums[0]; //start directly with nums[0] reduce re-assign step
            for(int i = 1; i < nums.length; i++) { //we don't have to maintain the state of i
                res ^= nums[i];
            }
            return res;
        }
    }
    
    protected static class IntersectionTwoArrays {
        
        //my solution
        public static int[] intersect(int[] nums1, int[] nums2) {
            int [] tmp1;
            int [] tmp2;
            if(nums1.length <= nums2.length) {
                tmp1 = nums1;
                tmp2 = nums2;
            } else {
                tmp1 = nums2;
                tmp2 = nums1;
            }
            nums1 = nums2 = null;
            int count = 0;
            int i = 0;
            int j = 0;
            int n = 0;
            int[] holder = new int[tmp1.length];
            while(i < tmp1.length) {
                while(j < tmp2.length && i < tmp1.length) {
                    if(tmp1[i] == tmp2[j++]) {
                        tmp2[j-1] = -99;
                        holder[count] = tmp1[i++];
                        count++; n = j-1; j = 0; 
                    }
                }
                if(j >= tmp2.length) i++; j = 0;
            }
            int [] result = new int[count];
            System.arraycopy(holder, 0, result, 0, count);
            return result;
        }
    }
    // some best solution from leetcode
    public int[] intersect(int[] nums1, int[] nums2) {
        int[] hash = new int[1001];
        ArrayList<Integer> arr = new ArrayList<Integer>();
        for(int i=0;i<nums1.length;i++){
            hash[nums1[i]]++;
        }
        for(int i=0;i<nums2.length;i++){
            if(hash[nums2[i]]>0){
                arr.add(nums2[i]);
                hash[nums2[i]]--;
            }
        }
        int[]ans = new int[arr.size()];
        for(int i=0;i<ans.length;i++){
            ans[i] = arr.get(i);
        }
        return ans;
    }
    //solution using map
    public int[] intersectWithMap(int[] nums1, int[] nums2) {
        HashMap<Integer,Integer> map = new HashMap();
        // tally nums1 freq
        for(int i : nums1) {
            map.put(i, map.getOrDefault(i,0) + 1);
        }
        
        // decrement from nums1 freq and build solution
        ArrayList<Integer> res = new ArrayList<Integer>();
        for(int i : nums2) {
            if(map.containsKey(i) && map.get(i) > 0) {
                res.add(i);
                map.put(i, map.get(i) - 1);
            }
        }
        int[] sol = new int[res.size()];
        int j = 0;
        for(int i : res) {
            sol[j] = i;
            j++;
        }
        return sol;
    }
    
    public static void main(String[] args) {
        int[] nums1 = { 61, 24, 20, 58, 95, 53, 17, 32, 45, 85, 70, 20, 83, 62, 35, 89, 5, 95, 12, 86, 58, 77, 30, 64,
                46, 13, 5, 92, 67, 40, 20, 38, 31, 18, 89, 85, 7, 30, 67, 34, 62, 35, 47, 98, 3, 41, 53, 26, 66, 40, 54,
                44, 57, 46, 70, 60, 4, 63, 82, 42, 65, 59, 17, 98, 29, 72, 1, 96, 82, 66, 98, 6, 92, 31, 43, 81, 88, 60,
                10, 55, 66, 82, 0, 79, 11, 81 };
        int[] nums2 = { 5, 25, 4, 39, 57, 49, 93, 79, 7, 8, 49, 89, 2, 7, 73, 88, 45, 15, 34, 92, 84, 38, 85, 34, 16, 6,
                99, 0, 2, 36, 68, 52, 73, 50, 77, 44, 61, 48 };
        System.out.println(Arrays.toString(IntersectionTwoArrays.intersect(nums1, nums2)));
    }
    
}
