import java.util.*;

public class Sorting {

    public static void main(String[] args) {
        String nums = "1, 2, 3, 4, 5, 6, 7, 8, 9,";

        Sort S = new Sort(nums, new int[][]{
			//offset by -1 since string start at 0
         {0,1},
         {0,2},
         {3,0},
         {2,7},
         {7,1},
         {3,1},
		 {3,4},
		 {5,3},
		 {4,6},
		 {1,6},
		 {8,7},
		 {8,5},
         {1,6},
         {3,1},
         {8,7}
        });

        System.out.println("Sorted:");
        //PRINTS IN REVERSE ORDER!!!! FIX
        System.out.println(S.Tsort());
    }
}
class Sort {
    String[] numbers;
    boolean[][] dependency;
    int numnum;

    public Sort(String nums, int[][] values) {
		//split string
        numbers = nums.split(",");
        numnum = numbers.length;
        dependency = new boolean[numnum][numnum];

        for (int[] value : values)
            dependency[value[0]][value[1]] = true;
    }

    List<String> Tsort() {
        List<String> result = new ArrayList<>();
        List<Integer> left = new LinkedList<>();

        for (int i = 0; i < numnum; i++)
            left.add(i);

        try {
            top:
            while (!left.isEmpty()) {
                for (Integer r : left) {
                    if (!hasDependency(r, left)) {
                        left.remove(r);
                        result.add(numbers[r]);
                        continue top;
                    }
                }
                throw new Exception("Graph has cycles");
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
        return result;
    }

    boolean hasDependency(Integer y, List<Integer> left) {
        for (Integer z : left) {
            if (dependency[y][z])
                return true;
        }
        return false;
    }
}