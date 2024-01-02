
public class Edge{
    public double distance;
    public Vertex u;
    public Vertex v;


    public Edge(Vertex u, Vertex v, double distance){
        this.u = u;
        this.v = v;
        this.distance = distance;
    }

    public double distance(){
        return distance;
    }

    public Vertex other(Vertex vertex){
        if(vertex.equals(u)){
            return v;
        } if (vertex.equals(v)){
            return u;
        }
        else{
            return null;
        }
    }

    public Vertex[] vertices(){
        return new Vertex[] {u, v};
    }




}