import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TwoSum {
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        // 构建数字和索引的映射
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        // 查找满足条件的数字对
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[] { i, map.get(complement) };
            }
        }
        // 如果没有找到满足条件的数字对，抛出异常
        throw new IllegalArgumentException("No two sum solution");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of elements (n): ");
        int n = scanner.nextInt();

        int[] nums = new int[n];
        System.out.println("Enter " + n + " numbers:");
        // 输入 n 个数字
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }

        System.out.print("Enter the target sum: ");
        int target = scanner.nextInt();

        TwoSum twoSum = new TwoSum();
        try {
            int[] result = twosum.twoSum(nums, target);
            // 输出满足条件的数字对的索引
            System.out.println("Indices of the two numbers: " + result[0] + ", " + result[1]);
        } catch (IllegalArgumentException e) {
            // 如果没有找到满足条件的数字对，输出提示信息
            System.out.println("No two sum solution");
        }

        scanner.close();
    }
}
