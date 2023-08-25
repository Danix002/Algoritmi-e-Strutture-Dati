package grafo;

import grafo.Heap.HeapException;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Set;
import java.util.HashSet;

public class MainDijkstra {

    static class ValuesComparator implements Comparator<Values> {
        @Override
        public int compare(Values v1, Values v2) {
            return v1.isEquals(v2);
        }

    }
    private static final Charset ENCODING = StandardCharsets.UTF_8;

    public static void printGrafo(Grafo<String, Double> grafo){
        grafo.print();
    }

    private static void loadArray(String filepath, Grafo<Values, Double> grafo) throws Exception, IOException, GrafoException {

        System.out.println("\nLoading data from file...\n");

        Path inputFilePath = Paths.get(filepath);
        try(BufferedReader fileInputReader = Files.newBufferedReader(inputFilePath, ENCODING)){
            String line = null;
            while((line = fileInputReader.readLine()) != null){
                String[] lineElements = line.split(",");

                Values<String> start = new Values<>(0, null, lineElements[0]);
                Values<String> destination = new Values<>(0, null, lineElements[1]);

                grafo.addVertex(start);
                grafo.addVertex(destination);
                grafo.addArco(start, destination, Double.parseDouble(lineElements[2]));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("\nData loaded\n");

    }

    public static void  testwithminimumpathdijkstra(String s, Grafo<Values, Double> g, String destination) throws HeapException {

        Values<String> values = new Values<>(0, null, s);
        Dijkstra<String> d = new Dijkstra<>(g);
        Set<Values> result = d.dijkstra(values);
        for (Values v: result) {
            if(v.vertex.equals(destination)){
                System.out.println("vertice: " + v.vertex + " ----> " + (v.d/1000));
            }
        }

    }

    //cd src
    //(cd .. (tornare indietro di directory))
    //javac .\grafo\MainDijkstra.java
    //java grafo/MainDijkstra "..\Test\italian_dist_graph.csv" --- + String s, String destination
    public static void main(String[] args) throws Exception, IOException, GrafoException {

        if(args.length < 1)
            throw new Exception("Usage: GrafoException <file_name>");

        //String path = "C:\\Users\\danie\\OneDrive\\Documenti\\Lezioni\\Secondo anno\\Algoritmi e Strutture dati\\Laboratorio\\Es 4\\Test\\italian_dist_graph.csv";
        //path assoluto
        Grafo<Values, Double> g = new Grafo<Values, Double>(new ValuesComparator(), false);
        loadArray(args[0]/*path*/ , g);

        //System.out.println("Numero di archi: " + g.sizeArchi());
        //System.out.println("Arco tra montauto - fungaia: " + g.existArco("fungaia", "montauto") + "Peso: " + g.weightbetweenVertex("fungaia", "montauto"));
        //printGrafo(g);

        testwithminimumpathdijkstra(args[1], g, args[2]);

    }
}
