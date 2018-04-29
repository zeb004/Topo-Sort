import java.util.*;

public class TopologicalSort {

    public static void main(String[] args) {
        String s = "1, 2, 3, 4, 5, 6, 7, 8, 9,";

        Graph g = new Graph(s, new int[][]{
			//offset by -1 since string start at 0
         {0,1}, {0,2}, {1,2}, {3,0}, {2,7}, {7,8},
		 {7,1}, {3,1}, {3,4}, {5,3}, {4,6}, {1,6},
         {6,8}, {8,7}, {8,5}
        });

        System.out.println("Topologically sorted order: ");

        System.out.println(g.topoSort());
    }
}
class Graph {
    String[] vertices;
    boolean[][] adjacency;
    int numVertices;

    public Graph(String s, int[][] edges) {
        vertices = s.split(",");
        numVertices = vertices.length;
        adjacency = new boolean[numVertices][numVertices];

        for (int[] edge : edges)
            adjacency[edge[0]][edge[1]] = true;
    }

    List<String> topoSort() {
        List<String> result = new ArrayList<>();
        List<Integer> todo = new LinkedList<>();

        for (int i = 0; i < numVertices; i++)
            todo.add(i);

        try {
            outer:
            while (!todo.isEmpty()) {
                for (Integer r : todo) {
                    if (!hasDependency(r, todo)) {
                        todo.remove(r);
                        result.add(vertices[r]);
                         // no need to worry about concurrent modification
                        continue outer;
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

    boolean hasDependency(Integer r, List<Integer> todo) {
        for (Integer c : todo) {
            if (adjacency[r][c])
                return true;
        }
        return false;
    }
}