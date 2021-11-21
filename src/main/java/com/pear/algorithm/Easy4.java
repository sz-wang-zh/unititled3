package com.pear.algorithm;

import org.junit.Test;

/*
    二分查找
 */
public class Easy4 {

    @Test
    public void t40() {
        int[] num = {1,3,5,6};
        System.out.println(divideS(num,2 ));
    }

    private int divideS(int[] nums, int target) {
        int l = 0;
        int r = nums.length-1;
        int index;
        do {
            index = (r+l)/2;
            if (nums[index] == target) {
                return index;
            } else if (target < nums[index]) {
                r = index-1;

            }  else if (target > nums[index]) {
                l = index+1;
            }
        } while (l<=r);

        return index+1;
    }
}
