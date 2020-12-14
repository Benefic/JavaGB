package algorithms.lesson7;

import java.util.LinkedList;

public abstract class Paths {
    protected boolean[] marked;
    protected int[] edgeTo;
    protected int source;

    public Paths(Graph g, int source) {
        this.source = source;
        edgeTo = new int[g.getVertexCount()];
        marked = new boolean[g.getVertexCount()];
        doPaths(g, source);
    }


    public boolean hasPathTo(int dist) {
        return marked[dist];
    }

    public LinkedList<Integer> pathTo(int dist) {
        if (!hasPathTo(dist)) {
            return null;
        }
        LinkedList<Integer> stack = new LinkedList<>();
        int vertex = dist;
        while (vertex != source) {
            stack.push(vertex);
            vertex = edgeTo[vertex];
        }
        return stack;
    }

    protected abstract void doPaths(Graph g, int source);
}
