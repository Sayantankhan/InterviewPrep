import java.util.*;


class QueryCount {
    String query;
    int count;

    public QueryCount(String query, int count) {
        this.query = query;
        this.count = count;
    }

    @Override
    public String toString() {
        return query + ": " + count;
    }
}

public class TopKSearchQueries {
    // Count Min Sketch - frequency

    // Count-Min Sketch parameters
    private final int[][] sketch;
    private final int width;
    private final int depth;
    private final int[] hashSeeds;
    private final Random random = new Random();

    // Top-K heap and map
    private final int k;
    private final PriorityQueue<QueryCount> topKHeap;
    private final Map<String, QueryCount> topKMap;

    public TopKSearchQueries(int k, int width, int depth) { // width stands for max o.p from hash func and depth is no of hash
        this.k = k;
        this.width = width;
        this.depth = depth;
        this.sketch = new int[depth][width];
        this.hashSeeds = new int[depth];

        for (int i = 0; i < depth; i++) {
            hashSeeds[i] = random.nextInt(Integer.MAX_VALUE);
        }

        this.topKHeap = new PriorityQueue<QueryCount>(Comparator.comparingInt((QueryCount q) -> q.count));
        this.topKMap = new HashMap<>();
    }

    public void addQuery(String query) {
        // 1. Update Count-Min Sketch
        for (int i = 0; i < depth; i++) {
            int hash = hash(query, hashSeeds[i]);
            sketch[i][hash % width]++;
        }

        // 2. Get estimated count
        int estimate = getEstimate(query);

        if (topKMap.containsKey(query)) {
            QueryCount q = topKMap.get(query);
            q.count = estimate; // update estimated count
            // Rebuild heap to maintain order (could optimize)
            rebuildHeap();
        } else if (topKHeap.size() < k || estimate > topKHeap.peek().count) {
            QueryCount newEntry = new QueryCount(query, estimate);
            topKMap.put(query, newEntry);
            topKHeap.add(newEntry);

            if (topKHeap.size() > k) {
                QueryCount removed = topKHeap.poll();
                topKMap.remove(removed.query);
            }
        }
    }

    public List<QueryCount> getTopK() {
        List<QueryCount> result = new ArrayList<>(topKHeap);
        result.sort((a, b) -> Integer.compare(b.count, a.count));
        return result;
    }

    private int getEstimate(String query) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < depth; i++) {
            int hash = hash(query, hashSeeds[i]);
            min = Math.min(min, sketch[i][hash % width]);
        }
        return min;
    }
    private int hash(String str, int seed) {
        int h = seed;
        for (char c : str.toCharArray()) {
            h = 31 * h + c;
        }
        return Math.abs(h);
    }

    private void rebuildHeap() {
        topKHeap.clear();
        topKHeap.addAll(topKMap.values());
    }

    public static void main(String[] args) {
        TopKSearchQueries tracker = new TopKSearchQueries(3, 1000, 2);

        String[] sampleQueries = {"java", "python", "python", "sql", "c++", "python", "go", "go", "go", "go"};
        for (String q : sampleQueries) {
            tracker.addQuery(q);
        }

        // Output top-K
        System.out.println("Top-K Search Queries:");
        for (QueryCount qc : tracker.getTopK()) {
            System.out.println(qc);
        }

        //PriorityQueue pq = new PriorityQueue();

    }

}
