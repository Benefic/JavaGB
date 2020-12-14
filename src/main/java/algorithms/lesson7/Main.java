package algorithms.lesson7;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph(5);
        graph.addEdge(1, 2);
        graph.addEdge(0, 4);
        graph.addEdge(1, 4);
        graph.addEdge(1, 0);

//        System.out.println(graph.getAdjList(1));

//        DepthFirstPaths dfp = new DepthFirstPaths(graph, 2);
//        System.out.println(dfp.hasPathTo(0));
//        System.out.println(dfp.pathTo(0));


        BreadthFirstPaths bfp = new BreadthFirstPaths(graph, 2);
        System.out.println(bfp.hasPathTo(0));
        System.out.println(bfp.pathTo(0));

    }
}
