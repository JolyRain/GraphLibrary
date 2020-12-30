package algorithms;

import graph.IGraph;
import graph.vertex.IVertex;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.function.Consumer;

public class BFS implements GraphConsumer{
    private IVertex startVertex;
    private Consumer<IVertex> visitor;

    public IVertex getStartVertex() {
        return startVertex;
    }

    public void setStartVertex(IVertex startVertex) {
        this.startVertex = startVertex;
    }

    public Consumer<IVertex> getVisitor() {
        return visitor;
    }

    public void setVisitor(Consumer<IVertex> visitor) {
        this.visitor = visitor;
    }

    public BFS(IVertex startVertex, Consumer<IVertex> visitor) {
        this.startVertex = startVertex;
        this.visitor = visitor;
    }

    @Override
    public void execute(IGraph graph) {
        Map<IVertex, Boolean> visited = initVisitedMap(graph);
        Queue<IVertex> queue = new LinkedList<>();
        queue.add(startVertex);
        visited.put(startVertex, true);
        while (queue.size() > 0) {
            IVertex currentVertex = queue.remove();
            visitor.accept(currentVertex);
            for (IVertex vertex : graph.getAdjacencyMap().get(currentVertex)) {
                if (!visited.get(vertex)) {
                    queue.add(vertex);
                    visited.put(vertex, true);
                }
            }
        }
    }

    private Map<IVertex, Boolean> initVisitedMap(IGraph graph) {
        Map<IVertex, Boolean> visited = new HashMap<>();
        for (IVertex vertex : graph.getVertices()) {
            visited.put(vertex, false);
        }
        return visited;
    }
}
