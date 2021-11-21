package com.pear.algorithm;

import java.util.Arrays;

/*
     删除有序数组中的重复项
 */

public class Easy2 {
    public static int removeDuplicates(int[] num) {
        if (num.length == 0) return 0;
        int i,j;
        i=j=0;
        while (++j<num.length) {
            while (num[i] == num[j]) {
                if (++j == num.length) {
                    return ++i;
                }
            }
            num[++i] = num[j];
        }

        return ++i;
    }

    public int removeDuplicates2(int[] nums) {
        int index = 0;
        for(int i = 1; i < nums.length; ++i){
            if(nums[i] != nums[index]){
                nums[++index] = nums[i];
            }
        }
        return index + 1;
    }

    public static void main(String[] args) {
        int[] num = {0,0,1,1,1,2,2,3,3,4};
        System.out.println(Arrays.toString(num));
        System.out.println(removeDuplicates(num));
        System.out.println(Arrays.toString(num));
    }
}
