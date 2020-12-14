package algorithms.lesson7;

public class DepthFirstPaths extends Paths {

    public DepthFirstPaths(Graph g, int source) {
        super(g, source);
    }

    @Override
    protected void doPaths(Graph g, int source) {
        dfs(g, source);
    }

    private void dfs(Graph g, int v) {
        marked[v] = true;
        for (int w : g.getAdjList(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(g, w);
            }
        }
    }

}
