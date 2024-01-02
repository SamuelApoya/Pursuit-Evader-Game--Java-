import java.util.ArrayList;

public class Vertex{
    public ArrayList<Edge> edges;


    public Vertex(){  
        this.edges = new ArrayList<Edge>();
    }


    public Edge getEdgeTo(Vertex vertex){
        for(Edge edge : edges){
            if(edge.other(this) == vertex){
                return edge;
            }

        }
        return null;
    }

    public void addEdge(Edge edge){
        edges.add(edge);
    }

    public boolean removeEdge(Edge edge){
        return edges.remove(edge);
    }

    public ArrayList<Vertex> adjacentVertices(){
        ArrayList<Vertex> adjacentVertices = new ArrayList<Vertex>();
        for(Edge edge : edges){
            adjacentVertices.add(edge.other(this));
        }
        return adjacentVertices;
    }

    public Iterable<Edge> incidentEdges(){
        return edges;
    }




}