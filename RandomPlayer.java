import java.util.Random;
import java.util.ArrayList;

public class RandomPlayer extends AbstractPlayerAlgorithm{
    Vertex currVer;

    public RandomPlayer(Graph graph){
        super(graph);
    }

    public Vertex chooseStart(){
        Random rand = new Random();
        int randIndex = rand.nextInt(graph.getVertices().size());
        Vertex start = graph.getVertices().get(randIndex);
        setCurrentVertex(start);
        return start;
    }


    public Vertex chooseStart(Vertex other){
        return chooseStart();
    }



    public Vertex chooseNext(Vertex otherPlayer){
         
          ArrayList<Vertex> neighbors = new ArrayList<Vertex>(chooseStart().adjacentVertices());
          Random rand = new Random();
          if (neighbors.size() > 0) {
              int index = rand.nextInt(neighbors.size());
              currVer = neighbors.get(index);    
              return currVer;
            } 
          return null;
    }

}