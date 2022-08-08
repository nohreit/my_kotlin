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

fun removeElementFirst(nums: IntArray, `val`: Int): Int {
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
assert nums[i] == expectedNums[i];
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
-231 <= nums[i] <= 231 - 1


Follow up: Could you minimize the total number of operations done?
Show Hint #1
In-place means we should not be allocating any space for extra array. But we are allowed to modify the existing array.
However, as a first step, try coming up with a solution that makes use of additional space. For this problem as well,
first apply the idea discussed using an additional array and the in-place solution will pop up eventually.
Hide Hint #2
A two-pointer approach could be helpful here. The idea would be to have one pointer for iterating the array and another
pointer that just works on the non-zero elements of the array.
 */

/*
    fun moveZeroes(nums: IntArray): Unit {
        var i = 0

        for (j in (0..nums.lastIndex)) {
            if (nums[j] != 0) {
                nums[i] = nums[j]

                if (i != j) {
                    nums[j] = 0
                }
                i++
            }
        }
    }

 */

fun moveZeroes(nums: IntArray) {
    if (nums.isEmpty() || nums.size == 1) return

    var j = 0

    for (i in nums.indices) {
        if (nums[i] != 0 && nums[j] == 0) {
            nums[j] = nums[i].also { nums[i] = nums[j] }
            j++
        } else if (nums[i] == 0 && nums[j] != 0) j = i
    }
}

/***
 * Given an integer array nums, move all the even integers at the beginning of the array followed by all the odd integers.

Return any array that satisfies this condition.



Example 1:

Input: nums = [3,1,2,4]
Output: [2,4,3,1]
Explanation: The outputs [4,2,3,1], [2,4,1,3], and [4,2,1,3] would also be accepted.

Example 2:

Input: nums = [0]
Output: [0]



Constraints:

1 <= nums.length <= 5000
0 <= nums[i] <= 5000
 */

/*
fun sortArrayByParity(nums: IntArray): IntArray {
    var left = 0
    var right = nums.size - 1
    while (left < right) {
        val isLeftOdd = nums[left] % 2 == 1
        val isRightEven = nums[right] % 2 == 0
        if (isLeftOdd && isRightEven) {
            val temp = nums[left]
            nums[left] = nums[right]
            nums[right] = temp
            left++
            right--
        }

        if (!isLeftOdd) {
            left++
        }
        if (!isRightEven) {
            right--
        }
    }

    return nums
}

*/

fun sortArrayByParity(nums: IntArray): IntArray {
    if (nums.isEmpty() || nums.size == 1) return nums

    var j = 0

    for (i in nums.indices) {
        val isEven: Boolean = (nums[i] % 2 == 0) // true
        val isOdd: Boolean = (nums[j] % 2 != 0) // true
        if (isEven && isOdd) {
            nums[j] = nums[i].also { nums[i] = nums[j] }
            j++
        } else if (!isEven && !isOdd) j = i
    }
    return nums
}

/***
 * Given an integer array nums and an integer val, remove all occurrences of val in nums in-place. The relative order of
 * the elements may be changed.

Since it is impossible to change the length of the array in some languages, you must instead have the result be placed in
the first part of the array nums. More formally, if there are k elements after removing the duplicates, then the first k
elements of nums should hold the final result. It does not matter what you leave beyond the first k elements.

Return k after placing the final result in the first k slots of nums.

Do not allocate extra space for another array. You must do this by modifying the input array in-place with O(1) extra memory.

Custom Judge:

The judge will test your solution with the following code:

int[] nums = [...]; // Input array
int val = ...; // Value to remove
int[] expectedNums = [...]; // The expected answer with correct length.
// It is sorted with no values equaling val.

int k = removeElement(nums, val); // Calls your implementation

assert k == expectedNums.length;
sort(nums, 0, k); // Sort the first k elements of nums
for (int i = 0; i < actualLength; i++) {
assert nums[i] == expectedNums[i];
}

If all assertions pass, then your solution will be accepted.



Example 1:

Input: nums = [3,2,2,3], val = 3
Output: 2, nums = [2,2,_,_]
Explanation: Your function should return k = 2, with the first two elements of nums being 2.
It does not matter what you leave beyond the returned k (hence they are underscores).

Example 2:

Input: nums = [0,1,2,2,3,0,4,2], val = 2
Output: 5, nums = [0,1,4,0,3,_,_,_]
Explanation: Your function should return k = 5, with the first five elements of nums containing 0, 0, 1, 3, and 4.
Note that the five elements can be returned in any order.
It does not matter what you leave beyond the returned k (hence they are underscores).



Constraints:

0 <= nums.length <= 100
0 <= nums[i] <= 50
0 <= val <= 100

Show Hint #1
The problem statement clearly asks us to modify the array in-place and it also says that the element beyond the new length
of the array can be anything. Given an element, we need to remove all the occurrences of it from the array. We don't
technically need to remove that element per-say, right?
Show Hint #2
We can move all the occurrences of this element to the end of the array. Use two pointers!
Show Hint #3
Yet another direction of thought is to consider the elements to be removed as non-existent. In a single pass, if we keep
copying the visible elements in-place, that should also solve this problem for us.

 */
/*


 */
fun removeElement(nums: IntArray, `val`: Int): Int {
    if (nums.isEmpty()) return 0
    if (nums.size == 1) return 1

    var k = 0
    for (i in nums.indices) {
        if (nums[i] != `val`) {
            nums[k] = nums[i]
            k++
        }
    }
    return k
}

/***
 * A school is trying to take an annual photo of all the students. The students are asked to stand in a single file line
 * in non-decreasing order by height. Let this ordering be represented by the integer array expected where expected[i] is the expected height of the ith student in line.

You are given an integer array heights representing the current order that the students are standing in. Each heights[i]
is the height of the ith student in line (0-indexed).

Return the number of indices where heights[i] != expected[i].



Example 1:

Input: heights = [1,1,4,2,1,3]
Output: 3
Explanation:
heights:  [1,1,4,2,1,3]
expected: [1,1,1,2,3,4]
Indices 2, 4, and 5 do not match.

Example 2:

Input: heights = [5,1,2,3,4]
Output: 5
Explanation:
heights:  [5,1,2,3,4]
expected: [1,2,3,4,5]
All indices do not match.

Example 3:

Input: heights = [1,2,3,4,5]
Output: 0
Explanation:
heights:  [1,2,3,4,5]
expected: [1,2,3,4,5]
All indices match.



Constraints:

1 <= heights.length <= 100
1 <= heights[i] <= 100

Show Hint #1
Build the correct order of heights by sorting another array, then compare the two arrays.

 */

fun heightChecker(heights: IntArray): Int {
    if (heights.isEmpty()) return 0
    var k = 0
    val srt = heights.sortedArray()
    for (i in srt.indices) if (srt[i] != heights[i]) k++
    return k
}

/***
 * Given an integer array nums, return the third distinct maximum number in this array. If the third maximum does not exist,
 * return the maximum number.



Example 1:

Input: nums = [3,2,1]
Output: 1
Explanation:
The first distinct maximum is 3.
The second distinct maximum is 2.
The third distinct maximum is 1.

Example 2:

Input: nums = [1,2]
Output: 2
Explanation:
The first distinct maximum is 2.
The second distinct maximum is 1.
The third distinct maximum does not exist, so the maximum (2) is returned instead.

Example 3:

Input: nums = [2,2,3,1]
Output: 1
Explanation:
The first distinct maximum is 3.
The second distinct maximum is 2 (both 2's are counted together since they have the same value).
The third distinct maximum is 1.



Constraints:

1 <= nums.length <= 104
-231 <= nums[i] <= 231 - 1


Follow up: Can you find an O(n) solution?
 */

/*
    fun thirdMax(nums: IntArray): Int {
        var fir : Int? = null
        var sec : Int? = null
        var third : Int? = null

        for (v in nums) {
            when {
                v == fir || v == sec || v == third -> {}
                fir == null || v > fir -> {
                    third = sec
                    sec = fir
                    fir = v
                }
                sec == null || v > sec -> {
                    third = sec
                    sec = v
                }
                third == null || v > third -> {
                    third = v
                }
            }
        }

        return third ?: fir!!

    }
 */

fun thirdMax(nums: IntArray): Int {
//    if (nums.isEmpty()) return 0
    val arr = nums.toSortedSet().reversed()
    if (arr.size > 2) return arr[2]
    return arr[0]
}

/***
 * Given an array nums of n integers where nums[i] is in the range [1, n], return an array of all the integers in the
 * range [1, n] that do not appear in nums.



Example 1:

Input: nums = [4,3,2,7,8,2,3,1]
Output: [5,6]

Example 2:

Input: nums = [1,1]
Output: [2]



Constraints:

n == nums.length
1 <= n <= 105
1 <= nums[i] <= n



Follow up: Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra
space.
Show Hint #1
This is a really easy problem if you decide to use additional memory. For those trying to write an initial solution using
additional memory, think counters!
Show Hint #2
However, the trick really is to not use any additional space than what is already available to use. Sometimes, multiple
passes over the input array help find the solution. However, there's an interesting piece of information in this problem
that makes it easy to re-use the input array itself for the solution.
Show Hint #3
The problem specifies that the numbers in the array will be in the range [1, n] where n is the number of elements in the
array. Can we use this information and modify the array in-place somehow to find what we need?

 */

/*
    fun findDisappearedNumbers(nums: IntArray): List<Int> {
       val res: MutableList<Int> = mutableListOf()
        for(i in 0..nums.size-1){

            var idx=Math.abs(nums[i]);
            idx--;
            if(nums[idx]>0)
              nums[idx]*=-1;


        }


        for(i in 0..nums.size-1){

            if(nums[i]>0)
                res.add(i+1);

        }

        return res;
    }
*/

fun findDisappearedNumbers(nums: IntArray): List<Int> {
    val srt: MutableSet<Int> = nums.toMutableSet()
    for (i in nums.indices) if (!srt.contains(i + 1)) srt.add(i + 1) else srt.remove(i + 1)
    return srt.toList()
}

/***
 * Given an integer array nums sorted in non-decreasing order, return an array of the squares of each number sorted in
 * non-decreasing order.



Example 1:

Input: nums = [-4,-1,0,3,10]
Output: [0,1,9,16,100]
Explanation: After squaring, the array becomes [16,1,0,9,100].
After sorting, it becomes [0,1,9,16,100].

Example 2:

Input: nums = [-7,-3,2,3,11]
Output: [4,9,9,49,121]



Constraints:

1 <= nums.length <= 104
-104 <= nums[i] <= 104
nums is sorted in non-decreasing order.


Follow up: Squaring each element and sorting the new array is very trivial, could you find an O(n) solution using a different approach?
 */

fun sortedSquares(nums: IntArray): IntArray {
 return intArrayOf()
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
    val nums12 = intArrayOf(0, 1, 0, 3, 12)
    val nums13 = intArrayOf(0)
    val nums14 = intArrayOf(1, 2, 0, 9, 0, 0, 0, 13)
    val nums15 = intArrayOf(1, 4, 9, 0, 13, 0, 0, 0, 15, 0, 19)
    val nums16 = intArrayOf(3, 1, 2, 4)
    val nums17 = intArrayOf(1, 4, 9, 11, 13, 5, 8, 6, 15, 3, 19)

    val target = 1; // Value to remove
    val expectedNums = intArrayOf(); // The expected answer with correct length.
    // It is sorted with no values equaling val.

    /*
        val k = removeDuplicates(nums) // Calls your implementation

        if (k == expectedNums.size) {
            sort(nums, 0, k); // Sort the first k elements of nums
            for (i in expectedNums.indices) {
                if (nums[i] != expectedNums[i]) break
            }
        } else {
            println("Mismatch")
        }

     */

    println(findDisappearedNumbers(intArrayOf(4, 3, 2, 7, 8, 2, 3, 1)))
    println(findDisappearedNumbers(intArrayOf(1, 1)))
    println(findDisappearedNumbers(nums11))
    println(findDisappearedNumbers(nums3))


}