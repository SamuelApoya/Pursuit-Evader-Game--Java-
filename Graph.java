import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Random;
import java.util.PriorityQueue;

public class Graph {

    ArrayList<Vertex> vertices;
    ArrayList<Edge> edges;
    private int numVertices;

    public Graph() {
        this(0);
    }

    public Graph(int n) {
        this(n, 0.0);
    }

    public Graph(int n, double probability) {
        this.numVertices = n;
        this.vertices = new ArrayList<>(n);
        this.edges = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            addVertex();
        }

        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (rand.nextDouble() < probability) {
                    addEdge(vertices.get(i), vertices.get(j), 1);
                }
            }
        }
    }


    public int size() {
        return vertices.size();
    }

    public ArrayList<Vertex> getVertices() {
        return vertices;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public Vertex addVertex() {
        Vertex newVertex = new Vertex();
        vertices.add(newVertex);
        return newVertex;

    }

    public Edge addEdge(Vertex u, Vertex v, double distance) {
        Edge newEdge = new Edge(u, v, distance);
        u.addEdge(newEdge);
        v.addEdge(newEdge);
        edges.add(newEdge);
        return newEdge;
    }

    public Edge getEdge(Vertex u, Vertex v) {
        for (Edge e : u.incidentEdges()) {
            if (e.other(u) == v) {
                return e;
            }
        }
        return null;

    }

    public boolean remove(Vertex vertex) {
        if (vertices.remove(vertex)) {
            numVertices--;
            for (Edge e : vertex.incidentEdges()) {
                Vertex other = e.other(vertex);
                other.removeEdge(e);
                edges.remove(e);
            }
            return true;
        }
        return true;
    }

    public boolean remove(Edge edge) {
        if (edges.remove(edge)) {
            edge.vertices()[0].removeEdge(edge);
            edge.vertices()[1].removeEdge(edge);
            return true;
        }
        return false;
    }

    
    public HashMap<Vertex, Double> distanceFrom(Vertex source) {
        HashMap<Vertex, Double> distances = new HashMap<>();
        for (Vertex v : vertices) {
            distances.put(v, Double.MAX_VALUE);
        }
        distances.put(source, 0.0);

        PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>(new Comparator<Vertex>() {
            @Override
            public int compare(Vertex v1, Vertex v2) {
                return Double.compare(distances.get(v1), distances.get(v2));
            }
        });
        for (Vertex v : vertices) {
            pq.offer(v);
        }

        while (!pq.isEmpty()) {
            Vertex u = pq.poll();
            for (Edge e : u.incidentEdges()) {
                Vertex v = e.other(u);
                double newDistance = distances.get(u) + e.distance();
                if (newDistance < distances.get(v)) {
                    distances.put(v, newDistance);
                    pq.remove(v);
                    pq.offer(v);
                }
            }
        }

        return distances;
    }
}
