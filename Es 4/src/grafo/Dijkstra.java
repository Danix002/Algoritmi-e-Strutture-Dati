package grafo;

import grafo.Heap.Heap;
import grafo.Heap.HeapException;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Dijkstra <T> {

    /*Si implementi l'algoritmo di Dijkstra per determinare i cammini minimi da sorgente unica in un grafo orientato pesato,
    con pesi degli archi tutti non negativi.
    L'implementazione dell'algoritmo di Dijkstra dovrà operare su un grafo realizzato tramite la libreria implementata
    secondo le specifiche fornite sopra e dovrà inoltre utilizzare al proprio interno una coda di priorità minima
    rappresentata con un heap realizzato con la libreria implementata secondo le specifiche dell'Esercizio 3*/

    class ValuesComparator implements Comparator<Values> {
        @Override
        public int compare(Values v1, Values v2) {
            if((v1.d - v2.d) > 0)
                return 1; //v1 > v2
            else if((v1.d - v2.d) < 0)
                return -1; // v2 > v1
            else return 0;
        }

    }
    Grafo<Values, Double> grafo = null;

    public Dijkstra(Grafo<Values, Double> grafo){

        this.grafo = grafo;

    }

    private void relax(Values u, Values v, Heap<Values> Q){

        if(grafo.existArco(v, u)) {

            if ((Q.indexOf(v) >= 0) && (v.d > (u.d + (this.grafo).weightbetweenVertex(v, u)))) {
                v.d = u.d + (this.grafo).weightbetweenVertex(v, u);
                v.pi = u.vertex;
                int index = Q.indexOf(v);
                Q.decr_elem(index, new Values(v.d, v.pi, v.vertex));
            }

        }else{
            return;
        }

    }

    public HashSet<Values> dijkstra(Values s) throws HeapException {

        if(!((this.grafo).existVertex(s))){
            return null;
        }

        for (Values v: (this.grafo).listofVertex()) {
            v.d = Integer.MAX_VALUE;
            v.pi = null;
            if(v.vertex.equals(s.vertex)){
                v.d = 0;
                v.pi = null;
            }
        }

        Heap<Values> Q = new Heap<>(new ValuesComparator());
        HashSet<Values> S = new HashSet<>();

        for (Values vvalues: (this.grafo).listofVertex()) {
            Q.insert(vvalues);
        }

        while(!(Q.isEmpty())){
            Values u = Q.extract_value();
            S.add(u);

            for (Values v : (this.grafo).listofVertex()) {

                if(((this.grafo).adjacencyVertex(u)).contains(v))

                    relax(u, v, Q);

            }

        }
        return S;
    }

}
