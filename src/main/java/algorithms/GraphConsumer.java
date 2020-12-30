package algorithms;

import graph.IGraph;
import graph.vertex.IVertex;

import java.util.function.Consumer;

public interface GraphConsumer {

    void execute(IGraph graph);

}
