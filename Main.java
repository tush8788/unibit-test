import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        int[] nums = {1, 3, 2, 2, -4, -6, -2, 8};
        int target = 4;

        List<List<Integer>> result = findCombinations(nums, target);
        System.out.println("First Combination For 4: " + result.get(0));
        System.out.println("Merge Into a single Array: " + mergeArray(nums, result.get(0)));
        System.out.println("Second Combination For 8: " + findSecondCombinations(mergeArray(nums, result.get(0)), target * 2));
    }

    public static List<List<Integer>> findCombinations(int[] nums, int target) {
        Map<Integer, List<List<Integer>>> combinations = new HashMap<>();

        for (int num : nums) {
            int comp = target - num;
            if (combinations.containsKey(comp)) {
                combinations.get(comp).add(Arrays.asList(num, comp));
            } else {
                List<List<Integer>> pairs = new ArrayList<>();
                pairs.add(Arrays.asList(num, comp));
                combinations.put(comp, pairs);
            }
        }

        List<Integer> mergedArray = new ArrayList<>();
        Arrays.sort(nums);
        for (int num : nums) {
            mergedArray.add(num);
        }

        for (List<List<Integer>> pairs : combinations.values()) {
            for (List<Integer> pair : pairs) {
                mergedArray.addAll(pair);
            }
        }

        List<List<Integer>> result = new ArrayList<>();
        result.add(mergedArray);
        return result;
    }

    public static List<List<Integer>> findSecondCombinations(List<Integer> mergedArray, int doubleTarget) {
        Map<Integer, List<List<Integer>>> combinations = new HashMap<>();

        for (int num : mergedArray) {
            int comp = doubleTarget - num;
            if (combinations.containsKey(comp)) {
                combinations.get(comp).add(Arrays.asList(num, comp));
            } else {
                List<List<Integer>> pairs = new ArrayList<>();
                pairs.add(Arrays.asList(num, comp));
                combinations.put(comp, pairs);
            }
        }

        List<List<Integer>> result = new ArrayList<>();
        for (List<List<Integer>> pairs : combinations.values()) {
            result.addAll(pairs);
        }
        return result;
    }

    public static List<Integer> mergeArray(int[] nums, List<List<Integer>> combinations) {
        List<Integer> mergedArray = new ArrayList<>();
        Arrays.sort(nums);

        for (int num : nums) {
            mergedArray.add(num);
        }

        for (List<Integer> pair : combinations) {
            mergedArray.addAll(pair);
        }

        return mergedArray;
    }
}
