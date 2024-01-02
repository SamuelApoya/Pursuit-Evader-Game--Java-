import java.util.Scanner;

public class Driver {
    // int count = 0;

    public Driver(int n, double p) throws InterruptedException{
        // Create a random graph on which to play
        Graph graph = new Graph(n, p);

        // Create the pursuer and evader
        AbstractPlayerAlgorithm pursuer = new RandomPlayer(graph);
        AbstractPlayerAlgorithm evader = new RandomPlayer(graph);

        // Have each player choose a starting location
        pursuer.chooseStart();
        // Since the evader has a harder objective, they get to play second
        // and see where the pursuer chose
        evader.chooseStart(pursuer.getCurrentVertex());

        // Make the display
        GraphDisplay display = new GraphDisplay(graph, pursuer, evader, 40);
        display.repaint();

        // Play the game until the pursuer captures the evader
        while (pursuer.getCurrentVertex() != evader.getCurrentVertex()){
            // count ++;
            Thread.sleep(200);
            pursuer.chooseNext(evader.getCurrentVertex());
            display.repaint();
            if (pursuer.getCurrentVertex() != evader.getCurrentVertex()){
                Thread.sleep(200);
                evader.chooseNext(pursuer.getCurrentVertex());  
                display.repaint();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        // int n = 10;
        // double p = .3;
        // new Driver(n, p);

        Scanner newScanner = new Scanner(System.in);

        System.out.println("Enter n: ");
        int n = Integer.parseInt(newScanner.nextLine());

        System.out.println("Enter p:");
        double p = Double.parseDouble(newScanner.nextLine());

        newScanner.close();
        new Driver(n, p);
    }
}