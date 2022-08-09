// https://leetcode.com/problems/two-sum/

/***
1. Two Sum
Easy

Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

You can return the answer in any order.



Example 1:

Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].

Example 2:

Input: nums = [3,2,4], target = 6
Output: [1,2]

Example 3:

Input: nums = [3,3], target = 6
Output: [0,1]



Constraints:

2 <= nums.length <= 104
-109 <= nums[i] <= 109
-109 <= target <= 109
Only one valid answer exists.


Follow-up: Can you come up with an algorithm that is less than O(n2) time complexity?
 */

fun twoSum(nums: IntArray, target: Int): IntArray {
//    nums.sort()
//
//    var x = 0
//    var y = nums.lastIndex
//    while (x < y) {
//        val temp = nums[x] + nums[y]
//        if (temp == target) return intArrayOf(x, y)
//
//        if (temp < target) x++
//        else y++
//    }
//
//    return intArrayOf()

    val record = hashMapOf<Int, Int>()
    for (x in 1 until nums.size) {
        record[x] = nums[x]
        val y = target - nums[x]
        if (record.contains(y)) {
            return intArrayOf(record.filterValues { it == y }.keys.toIntArray()[0])
        }
    }
    return intArrayOf()
}


fun main(args: Array<String>) {
    val nums0 = intArrayOf(2, 7, 11, 15)
    val nums1 = intArrayOf(3, 2, 4)
    val nums2 = intArrayOf(0, 1)

//    println(twoSum(nums0, 9).toList())
    println(twoSum(nums1, 6).toList())
    println(twoSum(nums2, 6).toList())
}