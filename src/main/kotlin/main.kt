/**
 * Given a fixed-length integer array arr, duplicate each occurrence of zero, shifting the remaining elements to
 * the right.
 * Note that elements beyond the length of the original array are not written. Do the above modifications to the
 * input array in place and do not return anything.



 * Example 1:

 * Input: arr = [1,0,2,3,0,4,5,0]
 * Output: [1,0,0,2,3,0,0,4]
 * Explanation: After calling your function, the input array is modified to: [1,0,0,2,3,0,0,4]

 * Example 2:

 * Input: arr = [1,2,3]
 * Output: [1,2,3]
 * Explanation: After calling your function, the input array is modified to: [1,2,3]



 * Constraints:

 * 1 <= arr.length <= 104
 * 0 <= arr\[i] <= 9
 *
 * Java
 *
 * for (int i = arr.length-1; i >= 0; i--){
 *      if(arr[i] == 0){
 *          for(int j = arr.length-2; j > i; j--){
 *              arr[j+1] = arr[j];
 *          }
 *          if(i < arr.length-1) arr[i+1] = 0;
 *      }
 * }


 */

fun duplicateZeros(arr: IntArray) {
    for (i in arr.indices.reversed()) {
        if (arr[i] == 0 && i < arr.size - 1) {
            for (j in arr.indices.reversed()) {
                if (j == i) break
                arr[j] = arr[j - 1]
            }
            if (i < arr.size - 1) arr[i + 1] = 0
        }
    }
    println(arr.toList())
}

fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int) {
    var l = m + n - 1
    var x = m
    var y = n

    while (x > 0 && y > 0) {
        if (nums1[x - 1] > nums2[y - 1]) {
            nums1[l] = nums1[x - 1]
            x--
        } else {
            nums1[l] = nums2[y - 1]
            y--
        }
        l--
    }

    while (y > 0) { // copy the second array if there are still element that left there.
        nums1[l] = nums2[y - 1]
        y--
        l--
    }
    println("nums1: ${nums1.toList()} | nums2: ${nums2.toList()}")
}

fun removeElement(nums: IntArray, `val`: Int): Int {
    var k = 0
    for (i in nums.indices) {
        if (nums[i] != `val`) {
            nums[k] = nums[i]
            k++
        }
    }
    return k
}

fun removeDuplicatesFirst(nums: IntArray): Int {
    if (nums.isEmpty()) return 0
    var k = 0
    for (i in nums.indices) {
        if (nums[i] != nums[k]) {
            k++
            nums[k] = nums[i]
        }
    }
    return k
}

fun checkIfExists1(arr: IntArray): Boolean {
    val seen = hashSetOf<Int>()
    for (i in arr.indices) {
        if (seen.contains(2 * arr[i]) || (seen.contains(arr[i] / 2) && (arr[i] % 2 == 0))) return true
        else seen.add(arr[i])
    }
    return false
}

fun checkIfExists(arr: IntArray): Boolean {
    val seen = mutableSetOf<Int>()
    arr.forEach { if (seen.contains(2 * it) || (seen.contains(2 / it) && (it % 2 == 0))) return true else seen.add(it) }
    return false
}

fun validMountainArray1(arr: IntArray): Boolean {
    if (arr.size < 3 || arr[0] >= arr[1]) return false
    var p = 0
    for (i in 1 until arr.size) {
        p = i
        if (arr[i - 1] >= arr[i]) break
    }

    if (arr[p - 1] == arr[p]) return false

    for (i in p until arr.size)
        if (arr[i - 1] <= arr[i]) return false

    return true
}

fun validMountainArray(arr: IntArray): Boolean {
    if (arr.size < 3 || arr[0] >= arr[1]) return false
    var increase = false
    var decrease = false

    for (i in 1 until arr.size) {
        if (arr[i - 1] == arr[i]) return false
        else if (arr[i - 1] > arr[i])
            decrease = true
        else {
            if (decrease) return false
            else increase = true
        }
    }

    return (increase && decrease)
}

/***
 * Input: arr = [17,18,5,4,6,1]
Output: [18,6,6,6,1,-1]
Explanation:
- index 0 --> the greatest element to the right of index 0 is index 1 (18).
- index 1 --> the greatest element to the right of index 1 is index 4 (6).
- index 2 --> the greatest element to the right of index 2 is index 4 (6).
- index 3 --> the greatest element to the right of index 3 is index 4 (6).
- index 4 --> the greatest element to the right of index 4 is index 5 (1).
- index 5 --> there are no elements to the right of index 5, so we put -1.

 */

fun replaceElements(arr: IntArray): IntArray {
    if (arr.isEmpty()) return intArrayOf()
    if (arr.size == 1) return intArrayOf(-1)

    var before = 0
    var biggest = 0
    for (i in arr.indices.reversed()) {
        if (i != arr.size - 1) {
            biggest = maxOf(before, biggest)
            before = arr[i]
            arr[i] = biggest
        } else {
            before = arr[arr.lastIndex]
            arr[i] = -1
        }
    }

    return arr
}

/***
 * Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique
 * element appears only once. The relative order of the elements should be kept the same.

Since it is impossible to change the length of the array in some languages, you must instead have the result be placed
in the first part of the array nums. More formally, if there are k elements after removing the duplicates, then the first
k elements of nums should hold the final result. It does not matter what you leave beyond the first k elements.

Return k after placing the final result in the first k slots of nums.

Do not allocate extra space for another array. You must do this by modifying the input array in-place with O(1) extra memory.

Custom Judge:

The judge will test your solution with the following code:

int[] nums = [...]; // Input array
int[] expectedNums = [...]; // The expected answer with correct length

int k = removeDuplicates(nums); // Calls your implementation

assert k == expectedNums.length;
for (int i = 0; i < k; i++) {
assert nums\[i] == expectedNums\[i];
}

If all assertions pass, then your solution will be accepted.



Example 1:

Input: nums = [1,1,2]
Output: 2, nums = [1,2,_]
Explanation: Your function should return k = 2, with the first two elements of nums being 1 and 2 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores).

Example 2:

Input: nums = [0,0,1,1,1,2,2,3,3,4]
Output: 5, nums = [0,1,2,3,4,_,_,_,_,_]
Explanation: Your function should return k = 5, with the first five elements of nums being 0, 1, 2, 3, and 4 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores).


Constraints:

1 <= nums.length <= 3 * 104
-100 <= nums\[i] <= 100
nums is sorted in non-decreasing order.

Show Hint #1
In this problem, the key point to focus on is the input array being sorted. As far as duplicate elements are concerned,
what is their positioning in the array when the given array is sorted? Look at the image above for the answer. If we know
the position of one of the elements, do we also know the positioning of all the duplicate elements?

Show Hint #2
We need to modify the array in-place and the size of the final array would potentially be smaller than the size of the
input array. So, we ought to use a two-pointer approach here. One, that would keep track of the current element in the
original array and another one for just the unique elements.

Show Hint #3
Essentially, once an element is encountered, you simply need to bypass its duplicates and move on to the next unique element.
Kotlin

 */

fun removeDuplicates(nums: IntArray): Int {
    if (nums.isEmpty()) return 0
    var k = 0
    for (i in nums.indices) {
        if (nums[i] != nums[k]) {
            k++
            nums[k] = nums[i]
        }
    }
    return k + 1
}

/***
 * Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.

Note that you must do this in-place without making a copy of the array.



Example 1:

Input: nums = [0,1,0,3,12]
Output: [1,3,12,0,0]

Example 2:

Input: nums = [0]
Output: [0]



Constraints:

1 <= nums.length <= 104
-231 <= nums\[i] <= 231 - 1


Follow up: Could you minimize the total number of operations done?
Show Hint #1
In-place means we should not be allocating any space for extra array. But we are allowed to modify the existing array. However, as a first step, try coming up with a solution that makes use of additional space. For this problem as well, first apply the idea discussed using an additional array and the in-place solution will pop up eventually.
Hide Hint #2
A two-pointer approach could be helpful here. The idea would be to have one pointer for iterating the array and another pointer that just works on the non-zero elements of the array.

 */

fun moveZeroes(nums: IntArray) {
//    if (nums.isEmpty()) return
//    if (nums.size == 1) print(nums[0])
//    var a = 0
//    for(i in nums.indices){
//        if(nums[i] == 0){
//            a = i
//        }else
//    }
}

fun main(args: Array<String>) {
    val nums = intArrayOf(3, 3, 5, 5); // Input array false
    val nums1 = intArrayOf(1, 2, 3, 5, 7); // Input array false
    val nums2 = intArrayOf(3, 1, 7, 11); // Input array false
    val nums3 = intArrayOf(0, 3, 2, 1); // Input array true
    val nums4 = intArrayOf(3, 5, 3); // Input array true
    val nums5 = intArrayOf(2, 1); // Input array false
    val nums6 = intArrayOf(0, 2, 1, 3, 5, 4); // Input array false
    val nums7 = intArrayOf(1, 3, 2); // Input array true
    val nums8 = intArrayOf(); // Input array false
    val nums9 = intArrayOf(17, 18, 5, 4, 6, 1); // Input array false
    val nums10 = intArrayOf(1, 1, 2)
    val nums11 = intArrayOf(0, 0, 1, 1, 1, 2, 2, 3, 3, 4)

    val target = 1; // Value to remove
    val expectedNums = intArrayOf(); // The expected answer with correct length.
    // It is sorted with no values equaling val.

    /*    val k = removeDuplicates(nums) // Calls your implementation

        if (k == expectedNums.size) {
            sort(nums, 0, k); // Sort the first k elements of nums
            for (i in expectedNums.indices) {
                if (nums[i] != expectedNums[i]) break
            }
        } else {
            println("Mismatch")
        }

     */
    println("test")


}