public abstract class AbstractPlayerAlgorithm{
    public Graph graph;
    Vertex curVertex;

    //Constructs the necessary fields of the class
    public AbstractPlayerAlgorithm(Graph graph){
        this.graph = graph;
        this.curVertex = null; 
    }

    // Returns the underlying graph
    public Graph getGraph(){
        return this.graph;
    }

    //Returns the current vertex of the player
    public Vertex getCurrentVertex(){
        return this.curVertex;
    }

    //Udates the current vertex of the player to the given vertex of the graph
    public void setCurrentVertex(Vertex vertex){
        this.curVertex = vertex;
    }

    //Returns a Vertex for the player to start at and updates the current Vertex to that location.
    public abstract Vertex chooseStart();

    //Returns a Vertex for the player to start at 
    public abstract Vertex chooseStart(Vertex other);


    //Returns a Vertex for the player to move
    public abstract Vertex chooseNext(Vertex otherPlayer);
}
