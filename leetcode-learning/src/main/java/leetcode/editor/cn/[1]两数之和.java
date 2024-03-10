package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

/**
 * [1]两数之和
 *
 * @author guorong
 * @date 2024-01-11 23:16:07
 */
class P1_TwoSum {
    public static void main(String[] args) {
        Solution solution = new P1_TwoSum().new Solution();
    }

    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] twoSum(int[] nums, int target) {
           return towSum1(nums, target);
        }

        // 使用循环
        private int[] towSum1(int[] nums, int target) {
            for (int i = 0; i < nums.length; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[i] + nums[j] == target) {
                        return new int[]{i, j};
                    }
                }
            }
            return new int[0];
        }
        // 使用Map缓存期望值和索引
        private int[] towSum2(int[] nums, int target) {
            // 用于存储该值期望匹配的值和该值的索引
            Map<Integer,Integer> numMap = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                Integer expectValue = target - nums[i];
                // 判断map中是否存在期望值
                if (numMap.containsKey(expectValue)) {
                    return new int[] {i, numMap.get(expectValue)};
                }
                // 存入当前值
                numMap.put(nums[i], i);
            }
            return new int[0];
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}