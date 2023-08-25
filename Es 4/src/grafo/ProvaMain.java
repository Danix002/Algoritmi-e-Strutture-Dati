package grafo;

import grafo.Heap.HeapException;

import java.util.Comparator;
import java.util.Set;

public class ProvaMain {

    static class ValuesComparator implements Comparator<Values> {
        @Override
        public int compare(Values v1, Values v2) {
            return v1.isEquals(v2);
        }

    }

    public static void main(String[] args) throws GrafoException, HeapException {

        Grafo<Values, Double> grafo = new Grafo<Values, Double>(new ValuesComparator(), true);
        grafo.addVertex(new Values<>(0,null,1));
        grafo.addVertex(new Values<>(0,null,2));
        grafo.addVertex(new Values<>(0,null,3));
        grafo.addVertex(new Values<>(0,null,4));
        grafo.addVertex(new Values<>(0,null,5));
        grafo.addArco(new Values<>(0,null,1),new Values<>(0,null,2),10.0);
        grafo.addArco(new Values<>(0,null,1), new Values<>(0,null,4), 5.0);
        grafo.addArco(new Values<>(0,null,2),new Values<>(0,null,3),1.0);
        grafo.addArco(new Values<>(0,null,2),new Values<>(0,null,4),2.0);
        grafo.addArco(new Values<>(0,null,3),new Values<>(0,null,5),4.0);
        grafo.addArco(new Values<>(0,null,4),new Values<>(0,null,2),3.0);
        grafo.addArco(new Values<>(0,null,4),new Values<>(0,null,5),2.0);
        grafo.addArco(new Values<>(0,null,4),new Values<>(0,null,3),9.0);
        grafo.addArco(new Values<>(0,null,5),new Values<>(0,null,3),6.0);
        grafo.addArco(new Values<>(0,null,5),new Values<>(0,null,1),7.0);

        Values<Integer> values = new Values<>(0, null, 1);
        Dijkstra<Integer> d = new Dijkstra<>(grafo);
        Set<Values> result = d.dijkstra(values);

        for (Values v: result) {
            System.out.println("vertice: " + v.vertex + " ----> " + v.d);
        }

    }

}
