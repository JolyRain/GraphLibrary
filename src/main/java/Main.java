import algorithms.Path;
import graph.edge.DirectedEdge;
import graph.edge.IEdge;
import graph.edge.UndirectedEdge;
import graph.vertex.IVertex;
import graph.vertex.Vertex;

public class Main {
    public static void main(String[] args) {
        Path path = new Path();
        path.addEdge(new UndirectedEdge(new Vertex("lol"), new Vertex("lol1")));
        path.addEdge(new DirectedEdge(new Vertex("lol1"), new Vertex("lol2")));
        System.out.println(path);


    }
}


