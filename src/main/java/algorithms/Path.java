package algorithms;

import graph.edge.IEdge;
import graph.vertex.IVertex;

import java.util.Stack;

public class Path {
    private Stack<IEdge> path;

    public Path() {
        path = new Stack<>();
    }

    public Path(Stack<IEdge> path) {
        this.path = path;
    }

    public void addEdge(IEdge edge) {
        path.push(edge);
    }
    public void removeEdge(IEdge edge) {
        path.remove(edge);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (IEdge edge : path) {
            stringBuilder.append(edge.toString());
        }
        return stringBuilder.toString();
    }
}
