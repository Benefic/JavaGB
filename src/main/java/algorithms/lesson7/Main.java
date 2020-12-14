package algorithms.lesson7;

public class Main {
    public static void main(String[] args) {

        Graph graph = new Graph(10);
        graph.addEdge(0, 1);
        graph.addEdge(1, 4);
        graph.addEdge(1, 5);
        graph.addEdge(5, 6);
        graph.addEdge(5, 8);
        graph.addEdge(5, 9);
        graph.addEdge(6, 3);
        graph.addEdge(9, 0);

        BreadthFirstPaths bfp = new BreadthFirstPaths(graph, 0);
        System.out.println(bfp.pathTo(9));
        System.out.println(bfp.pathTo(7));
        System.out.println(bfp.pathTo(5));

/*
        Graph graph = new Graph(5);
        graph.addEdge(1, 2);
        graph.addEdge(0, 4);
        graph.addEdge(1, 4);
        graph.addEdge(1, 0);

        System.out.println(graph.getAdjList(1));

        DepthFirstPaths dfp = new DepthFirstPaths(graph, 2);
        System.out.println(dfp.hasPathTo(0));
        System.out.println(dfp.pathTo(0));


        BreadthFirstPaths bfp = new BreadthFirstPaths(graph, 2);
        System.out.println(bfp.hasPathTo(0));
        System.out.println(bfp.pathTo(0));
        */

    }
}
