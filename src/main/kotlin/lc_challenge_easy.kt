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
    var map = hashMapOf<Int, Int>()
    for (i in nums.indices) {
        var complement = target - nums[i]
        if (map.containsKey(complement)) return intArrayOf(map[complement], i)
    }
}

/***
804. Unique Morse Code Words
Easy

International Morse Code defines a standard encoding where each letter is mapped to a series of dots and dashes, as follows:

'a' maps to ".-",
'b' maps to "-...",
'c' maps to "-.-.", and so on.

For convenience, the full table for the 26 letters of the English alphabet is given below:

[".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."]

Given an array of strings words where each word can be written as a concatenation of the Morse code of each letter.

For example, "cab" can be written as "-.-..--...", which is the concatenation of "-.-.", ".-", and "-...". We will call such a concatenation the transformation of a word.

Return the number of different transformations among all words we have.



Example 1:

Input: words = ["gin","zen","gig","msg"]
Output: 2
Explanation: The transformation of each word is:
"gin" -> "--...-."
"zen" -> "--...-."
"gig" -> "--...--."
"msg" -> "--...--."
There are 2 different transformations: "--...-." and "--...--.".

Example 2:

Input: words = ["a"]
Output: 1



Constraints:

1 <= words.length <= 100
1 <= words[i].length <= 12
words[i] consists of lowercase English letters.

 */

fun uniqueMorseRepresentation(words: Array<String>): Int {
    val morse = arrayOf(
        ".-",
        "-...",
        "-.-.",
        "-..",
        ".",
        "..-.",
        "--.",
        "....",
        "..",
        ".---",
        "-.-",
        ".-..",
        "--",
        "-.",
        "---",
        ".--.",
        "--.-",
        ".-.",
        "...",
        "-",
        "..-",
        "...-",
        ".--",
        "-..-",
        "-.--",
        "--.."
    )

    val seen = hashSetOf<String>()

    for (word in words) {
        var code = ""
        for (c in word.toCharArray()) code += morse[c - 'a']
        seen.add(code)
    }

    return seen.size
}

/***
 * 9. Palindrome Number
 * @source: https://leetcode.com/problems/palindrome-number/
 */

fun isPalindrome(x: Int): Boolean {
    return x.toString() == x.toString().reversed()
}

/***
 * 13. Roman to Integer
 * @source: https://leetcode.com/problems/roman-to-integer/
 */

fun romanToInt(s: String): Int {
    val nums = hashMapOf<Char, Int>(
        'I' to 1,
        'V' to 5,
        'V' to 5,
        'X' to 10,
        'L' to 50,
        'C' to 100,
        'D' to 500,
        'M' to 1000
    )

    var total = 0

    for (i in s.indices.reversed()) {
        val currEl = nums[s[i]]!!
        val elToRight = nums[s[i]]!!

        if (i < s.length - 1 && currEl < elToRight) total -= currEl
        else total += currEl
    }
    return total
}

/***
 * 12. Integer to Roman
 * @source: https://leetcode.com/problems/integer-to-roman/
 */
fun intToRoman(s: String): Int {
    val nums = hashMapOf<Char, Int>(
        'I' to 1,
        'V' to 5,
        'X' to 10,
        'L' to 50,
        'C' to 100,
        'D' to 500,
        'M' to 1000
    )

    return 0
}

/***
 * https://leetcode.com/problems/binary-tree-paths/
 * https://leetcode.com/problems/sum-of-root-to-leaf-binary-numbers/
 * https://leetcode.com/problems/sum-of-root-to-leaf-binary-numbers/
 * https://leetcode.com/problems/partition-equal-subset-sum/
 * https://leetcode.com/problems/coin-change/
 * https://leetcode.com/problems/substring-with-largest-variance/
 */
fun main(args: Array<String>) {
    val nums0 = intArrayOf(2, 7, 11, 15)
    val nums1 = intArrayOf(3, 2, 4)
    val nums2 = intArrayOf(0, 1)
    val nums3 = intArrayOf(121, -121, 10)
    val str0 = arrayOf("gin", "zen", "gig", "msg")

    println(twoSum(nums0, 9).toList())
    println(twoSum(nums1, 6).toList())
    println(twoSum(nums2, 6).toList())
}