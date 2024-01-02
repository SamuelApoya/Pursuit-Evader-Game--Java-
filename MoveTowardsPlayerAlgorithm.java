import java.util.HashMap;
import java.util.Random;

public class MoveTowardsPlayerAlgorithm extends AbstractPlayerAlgorithm {
    private Vertex currVer;
    private Random rand;

    public MoveTowardsPlayerAlgorithm(Graph graph) {
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
        // Set the other player's location as the target
        return chooseStart();
    }

    @Override
    public Vertex chooseNext(Vertex otherPlayer) {
        // Move towards the other player
        HashMap<Vertex, Double> distances = graph.distanceFrom(otherPlayer);
        Vertex next = getCurrentVertex();

        for (Vertex v : getCurrentVertex().adjacentVertices()){
            if (distances.get(v) < distances.get(next)){
                next = v;
            }
        }


        setCurrentVertex(next);
        System.out.println("Chose vertex " + currVer);
        return currVer;
    }



    public static void main(String[] args) {
        Graph graph = new Graph(6, 0.5);
    
        MoveTowardsPlayerAlgorithm player1 = new MoveTowardsPlayerAlgorithm(graph);
        player1.chooseStart();
    
        MoveTowardsPlayerAlgorithm player2 = new MoveTowardsPlayerAlgorithm(graph);
        player2.chooseStart();
    
        Vertex target = graph.getVertices().get(0);
    
        for (int i = 0; i < 5; i++) {
            Vertex next1 = player1.chooseNext(target);
            System.out.println("Player 1: " + player1.getCurrentVertex() + " -> " + next1);
            player1.setCurrentVertex(next1);
    
            Vertex next2 = player2.chooseNext(target);
            System.out.println("Player 2: " + player2.getCurrentVertex() + " -> " + next2);
            player2.setCurrentVertex(next2);
        }
    }
    
}
