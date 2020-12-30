package algorithms;

import graph.IGraph;
import graph.UndirectedGraph;
import graph.edge.IEdge;
import graph.vertex.IVertex;

import java.util.*;
import java.util.function.Consumer;

import static java.util.Collections.sort;

public class GraphExecutor {

    private Map<IVertex, Boolean> initVisitedMap(IGraph graph) {
        Map<IVertex, Boolean> visited = new HashMap<>();
        for (IVertex vertex : graph.getVertices()) {
            visited.put(vertex, false);
        }
        return visited;
    }

    public void breadthFirstWalk(IGraph graph, IVertex startVertex, Consumer<IVertex> visitor) {
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

    public void depthFirstWalk(IGraph graph, IVertex startVertex, Consumer<IVertex> visitor) {
        Map<IVertex, Boolean> visited = initVisitedMap(graph);
        Stack<IVertex> stack = new Stack<>();
        stack.push(startVertex);
        visited.put(startVertex, true);
        while (!stack.empty()) {
            IVertex currentVertex = stack.pop();
            visitor.accept(currentVertex);
            for (IVertex vertex : graph.getAdjacencyMap().get(currentVertex)) {
                if (!visited.get(vertex)) {
                    stack.push(vertex);
                    visited.put(vertex, true);
                }
            }
        }
    }

    public UndirectedGraph minSpanTree(UndirectedGraph graph) {
        UndirectedGraph spanTree = new UndirectedGraph();
        DSU dsu = new DSU(graph.getVertices().size());
        List<IEdge> edges = new LinkedList<>(graph.getEdges());
        sort(edges);
        Map<IVertex, Integer> numberingVertices = numberingVertices(graph);
        for (IEdge edge : edges) {
            if (dsu.unite(numberingVertices.get(edge.getStart()), numberingVertices.get(edge.getEnd()))) {
                spanTree.addEdge(edge);
            }
        }
        return spanTree;
    }

    private Map<IVertex, Integer> numberingVertices(IGraph graph) {
        Map<IVertex, Integer> numberingVertices = new HashMap<>();
        Integer number = 0;
        for (IVertex vertex : graph.getVertices()) {
            numberingVertices.put(vertex, number);
            number++;
        }
        return numberingVertices;
    }

    private Map<IVertex, Double> initLengthMap(IGraph graph) {
        Map<IVertex, Double> lengthMap = new HashMap<>();
        for (IVertex currentVertex : graph.getVertices()) {
            lengthMap.put(currentVertex, Double.MAX_VALUE);
        }
        return lengthMap;
    }

    public Path shortPath(IGraph graph, IVertex start, IVertex target) {
        Path path = new Path();
        if (start.equals(target)) {
            return null;
        }
        Map<IVertex, Boolean> visited = initVisitedMap(graph);

        Map<IVertex, Double> lengthMap = initLengthMap(graph);
        lengthMap.put(start, (double) 0);

        Map<IVertex, IVertex> previous = new HashMap<>();
        Set<IVertex> vertices = graph.getVertices();
        int unvisited = vertices.size();


        for (IVertex adjacentVertex : graph.getAdjacencyMap().get(start)) {
            lengthMap.put(adjacentVertex, graph.getEdge(start, adjacentVertex).getWeight());
        }

        while (unvisited > 0) {
            IVertex currentVertex = null;
            double min = Double.MAX_VALUE;
            for (Map.Entry<IVertex, Double> item : lengthMap.entrySet()) {
                if (item.getValue() < min && !visited.get(item.getKey())) {
                    min = item.getValue();
                    currentVertex = item.getKey();
                }
            }
            if (currentVertex == null) {
                return null;
            }
            for (IVertex vertexTo : graph.getAdjacencyMap().get(currentVertex)) {
                double weight = graph.getEdge(currentVertex, vertexTo).getWeight();
                if (lengthMap.get(vertexTo) >= lengthMap.get(currentVertex) + weight) {
                    lengthMap.put(vertexTo, lengthMap.get(currentVertex) + weight);
                    previous.put(vertexTo, currentVertex);
                }
            }
            visited.put(currentVertex, true);
            unvisited--;
        }
        restorePath(path, graph, previous, start, target);
        return path;
    }

    private void restorePath(Path path, IGraph graph, Map<IVertex, IVertex> previous, IVertex startVertex, IVertex finalVertex) {
        while (!finalVertex.equals(startVertex)) {
            path.addEdge(graph.getEdge(finalVertex, previous.get(finalVertex)));
            finalVertex = previous.get(finalVertex);
        }
    }


}
