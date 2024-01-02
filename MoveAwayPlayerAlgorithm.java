import java.util.HashMap;
import java.util.Random;

public class MoveAwayPlayerAlgorithm extends AbstractPlayerAlgorithm {
    private Vertex currVer;
    private Random rand;

    public MoveAwayPlayerAlgorithm(Graph graph) {
        super(graph);
        rand = new Random();
    }

    @Override
    public Vertex chooseStart() {
        int randIndex = rand.nextInt(graph.getVertices().size());
        Vertex start = graph.getVertices().get(randIndex);
        setCurrentVertex(start);
        return start;
    }

    @Override
    public Vertex chooseStart(Vertex other) {
        HashMap<Vertex, Double> distances = graph.distanceFrom(other);
        double maxDistance = 0;
        Vertex farthestVertex = other;
        for (Vertex vertex : getGraph().getVertices()) {
            if (distances.get(vertex) > maxDistance) {
                maxDistance = distances.get(vertex);
                farthestVertex = vertex;
            }
        }
        setCurrentVertex(farthestVertex);
        return farthestVertex;
    }

    @Override
    public Vertex chooseNext(Vertex otherPlayer) {
        // Move away from the other player
        currVer = moveAway(graph, otherPlayer);
        setCurrentVertex(currVer);
        return currVer;
    }

    public Vertex moveAway(Graph graph, Vertex source) {
        HashMap<Vertex, Double> distances = graph.distanceFrom(source);
        double maxDistance = distances.get(getCurrentVertex());
        Vertex farthestVertex = getCurrentVertex();
        for (Vertex vertex : getCurrentVertex().adjacentVertices()) {
            if (vertex != source && distances.get(vertex) > maxDistance) {
                maxDistance = distances.get(vertex);
                farthestVertex = vertex;
            }
        }
        System.out.println("Chose farthest vertex " + farthestVertex);
        return farthestVertex;
    }
}
