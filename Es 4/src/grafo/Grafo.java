package grafo;

import java.util.*;

public class Grafo<T, E> {
    /*Si implementi una libreria che realizza la struttura dati Grafo in modo che sia ottimale per dati sparsi
    (IMPORTANTE: le scelte implementative che farete dovranno essere giustificate in relazione alle nozioni presentate
    durante le lezioni in aula). La struttura deve consentire di rappresentare sia grafi diretti che grafi non diretti
    (suggerimento:  un grafo non diretto può essere rappresentato usando un'implementazione per grafi diretti modificata
    per garantire che, per ogni arco (a,b), etichettato w, presente nel grafo, sia presente nel grafo anche l'arco (b,a),
    etichettato w. Ovviamente, il grafo dovrà mantenere l'informazione che specifica se esso è un grafo diretto o non diretto.).
    L'implementazione deve essere generica sia per quanto riguarda il tipo dei nodi, sia per quanto riguarda le etichette
    degli archi.
    La struttura dati implementata dovrà offrire (almeno) le seguenti operazioni (accanto a ogni operazione è specificata la
    complessità richiesta; n può indicare il numero di nodi o il numero di archi, a seconda del contesto):

        Creazione di un grafo vuoto – O(1)
        Aggiunta di un nodo – O(1)
        Aggiunta di un arco – O(1)
        Verifica se il grafo è diretto – O(1)
        Verifica se il grafo contiene un dato nodo – O(1)
        Verifica se il grafo contiene un dato arco – O(1)  (*)
        Cancellazione di un nodo – O(n)
        Cancellazione di un arco – O(1)  (*)
        Determinazione del numero di nodi – O(1)
        Determinazione del numero di archi – O(n)
        Recupero dei nodi del grafo – O(n)
        Recupero degli archi del grafo – O(n)
        Recupero nodi adiacenti di un dato nodo – O(1)  (*)
        Recupero etichetta associata a una coppia di nodi – O(1) (*)

    (*) quando il grafo è veramente sparso, assumendo che l'operazione venga effettuata su un nodo la cui lista di adiacenza
    ha una lunghezza in O(1).*/

    private Comparator<? super T> comparator_T = null;
    private HashMap <T, HashSet<Arco<T, E>>> adjacency_list = null;
    private boolean direct;

    public Grafo(Comparator<? super T> comparator_T, boolean direct) throws GrafoException{
        this.adjacency_list = new HashMap<>();
        this.comparator_T = comparator_T;
        this.direct = direct;
    }

    public boolean addVertex(T vertex){

        if((this.adjacency_list).containsKey(vertex)){
            return false;
        }else{
            (this.adjacency_list).put(vertex, new HashSet<>());
            return true;
        }

    }

    public boolean addArco(Arco <T, E> a){

        if(this.direct){
            //grafo diretto(orientato)
            HashSet<Arco <T, E>> hashSet = (this.adjacency_list).get(a.getStart());
            hashSet.add(a);
            return true;

        }else{
            //grafo indiretto(non orientato)
            HashSet<Arco <T, E>> hashSet1 = (this.adjacency_list).get(a.getStart());
            hashSet1.add(a);
            HashSet<Arco <T, E>> hashSet2 = (this.adjacency_list).get(a.getDestination());
            hashSet2.add(new Arco<>(comparator_T, a.getStart(), a.getDestination(), a.getWeight()));
            return true;
        }

    }

    public boolean addArco(T start, T destination, E weight){
        return addArco(new Arco<>(comparator_T,destination,start,weight));
    }

    public boolean isDirect() {
        return direct;
    }

    public boolean existVertex(T vertex){
        return (this.adjacency_list).containsKey(vertex);
    }

    public boolean existArco(Arco <T, E> a) {
        HashSet<Arco <T, E>> hashSet = (this.adjacency_list).get(a.getStart());

        if(hashSet == null){
            return false;
        }

        for (Arco <T, E> b: hashSet){
            if(b.equals(a)){
                return true;
            }
        }
        return false;
    }

    public boolean existArco(T destination, T start){
        return existArco(new Arco<>(comparator_T, destination, start, null));
    }

    public boolean deleteVertex(T vertex){

        if((this.adjacency_list).containsKey(vertex)){

            (this.adjacency_list).remove(vertex);
            Collection<HashSet<Arco <T, E>>> list = (this.adjacency_list).values();

            for (HashSet<Arco <T, E>> hashSet: list) {

                ArrayList<Arco<T, E>> arrayList = new ArrayList<>();

                for (Arco<T, E> a: hashSet) {
                    if(comparator_T.compare(a.getDestination(), vertex) == 0){
                        arrayList.add(new Arco<>(comparator_T, a.getDestination(), a.getStart(), a.getWeight()));
                    }
                }

                for(int i = 0; i < arrayList.size(); i++){
                    hashSet.remove(arrayList.get(i));
                }

            }

            return true;
        }else{
            return false;
        }

    }

    public boolean deleteArco(Arco <T, E> a){

        HashSet<Arco <T, E>> hashSet = (this.adjacency_list).get(a.getStart());
        //if(hashSet.contains(a)){
        if(existArco(a)){

            ArrayList<Arco<T, E>> arrayList = new ArrayList<>();

            for (Arco<T, E> b : hashSet) {

                if (b.equals(a)) {
                    arrayList.add(new Arco<>(comparator_T, b.getDestination(), b.getStart(), b.getWeight()));
                }

            }

            for(int i = 0; i < arrayList.size(); i++){
                hashSet.remove(arrayList.get(i));
            }

            if(!direct){

                HashSet<Arco <T, E>> hashSet1 = (this.adjacency_list).get(a.getDestination());
                Arco<T, E> reverse_a = new Arco<>(comparator_T, a.getStart(), a.getDestination(), a.getWeight());

                ArrayList<Arco<T, E>> arrayList1 = new ArrayList<>();

                for (Arco<T, E> b : hashSet1) {
                    if (b.equals(reverse_a)) {
                        arrayList1.add(new Arco<>(comparator_T, b.getDestination(), b.getStart(), b.getWeight()));
                    }
                }

                for(int i = 0; i < arrayList1.size(); i++){
                    hashSet.remove(arrayList1.get(i));
                }

            }

            return true;
        }else{
            return false;
        }
    }

    public boolean deleteArco(T destination, T start){
        return deleteArco(new Arco<>(comparator_T, destination, start, null));
    }

    public int sizeVertex(){
        return((this.adjacency_list).size());
    }

    public int sizeArchi(){

        Collection<HashSet<Arco <T, E>>> list = (this.adjacency_list).values();
        int count = 0;

        for (HashSet<Arco <T, E>> hashSet: list) {
            count+= hashSet.size();
        }

        if(direct)
            return count;
        else
            return (count/2);

    }

    public Set<T> listofVertex(){
        return ((this.adjacency_list).keySet());
    }

    public Set<Arco <T, E>> listofArchi(){

        Collection<HashSet<Arco <T, E>>> list = (this.adjacency_list).values();
        Set<Arco <T, E>> set = new HashSet<>();

        for (HashSet<Arco <T, E>> hashSet: list) {

            for (Arco<T, E> a: hashSet) {
                set.add(a);
            }

        }
        return set;
    }

    public Set<T> adjacencyVertex(T vertex){

        HashSet<Arco <T, E>> hashSet = (this.adjacency_list).get(vertex);
        Set<T> set = new HashSet<>();

        for (Arco<T, E> a: hashSet){
            set.add(a.getDestination());
        }
        return set;
    }

    public E weightbetweenVertex(T destination, T start){

        if(existArco(destination, start)){

            HashSet<Arco <T, E>> hashSet = (this.adjacency_list).get(start);

            for (Arco<T, E> a : hashSet) {
                if(a.equals(new Arco<>(comparator_T, destination, start, null))){
                    return a.getWeight();
                }
            }
            return null;
        }else{
            return null;
        }
    }

    public void print(){
        System.out.println("Grafo:\n" + (this.adjacency_list));
    }

}
