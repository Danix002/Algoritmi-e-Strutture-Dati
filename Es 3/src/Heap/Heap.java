package Heap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;

public class Heap<T> {

    /*
    Si implementi una libreria che realizza la struttura dati Heap Minimo. La struttura dati deve:

    - rappresentare internamente lo heap tramite un vettore (� possibile usare anche altre strutture interne di supporto, se necessarie);
    - consentire un numero qualunque e non noto a priori di elementi dello heap;
    - essere generica per quanto riguarda il tipo degli elementi dello heap;
    - essere generica per quanto riguarda il criterio di confronto fra elementi dello heap.

    Essa deve, inoltre, offrire (almeno) le seguenti operazioni (accanto a ogni operazione � specificata la
    complessit� richiesta, in cui n indica il numero di elementi dello heap):

    - creazione di uno heap minimo vuoto - O(1);
    - inserimento di un elemento - O(log n); ---> pseudocodice teoria
    - restituzione della dimensione dello heap - O(1);
    - restituzione del genitore di un elemento - O(1);
    - restituzione del figlio sinistro di un elemento - O(1);
    - restituzione del figlio destro di un elemento - O(1);
    - estrazione dell'elemento con valore minimo - O(log n); ---> pseudocodice teoria
    - diminuzione del valore di un elemento - O(log n).
     */
    private ArrayList<T> ArrayHeap = null;
    private Comparator<? super T> comparator = null;
    private HashMap<T, HashSet<Integer>> HashHeap = null;

    //costruttore: creare un heap minimo vuoto
    public Heap(Comparator<? super T> comparator) throws HeapException {
        this.ArrayHeap = new ArrayList<T>();
        this.comparator = comparator;
        this.HashHeap = new HashMap<T, HashSet<Integer>>();
    }

    //restituisce la dimensione dell'heap
    public int size(){
        return (this.ArrayHeap).size();
    }

    public int size_HashMap(){
        return (this.HashHeap).size();
    }

    //restituisce true se la struttura � vuota
    public boolean isEmpty(){
        return (this.ArrayHeap).isEmpty();
    }

    private void valueofHashMap(int index, T value){

        HashSet<Integer> value_two;
        if((value_two = (this.HashHeap).get(value)) != null){
            value_two.add(index);
        }else{
            value_two = new HashSet<Integer>();
            value_two.add(index);
            (this.HashHeap).put(value, value_two);
        }

    }

    private void switchofHashMap(int index, int index_two, T value, T value_two){

        HashSet<Integer> hashSet_one = (this.HashHeap).get(value);
        HashSet<Integer> hashSet_two = (this.HashHeap).get(value_two);

        hashSet_one.remove(index);
        hashSet_one.add(index_two);
        hashSet_two.remove(index_two);
        hashSet_two.add(index);

    }

    //inserimento di un elemento
    public void insert(T element){

        (this.ArrayHeap).add(element);
        valueofHashMap(size()- 1,element);
        int father_index = get_parent_index(((this.ArrayHeap).size()) - 1);
        int element_index = (this.ArrayHeap).size() - 1;

        while(father_index >= 0 && ((this.comparator).compare(element, (this.ArrayHeap).get(father_index))) < 0) {
            T copy_father = (this.ArrayHeap).get(father_index);
            ArrayHeap.set(father_index, element);
            ArrayHeap.set(element_index, copy_father);

            switchofHashMap(father_index, element_index, copy_father, element);

            element_index = father_index;
            father_index = get_parent_index(element_index);
        }


    }

    public T get(int index){
        return ((this.ArrayHeap).get(index));
    }

    public void print_HashHeap(){
        System.out.println(HashHeap);
    }

    //restituisce il genitore dell'elemento
    public T get_parent_value(T element){

        HashSet<Integer> hashSet= (this.HashHeap).get(element);
        int father_index = get_parent_index(hashSet.iterator().next());

        if(father_index < 0){
            return element;
        }
        return (this.ArrayHeap).get(father_index);

    }

    private int get_parent_index(int index){

        if(index != 0) {
            return (index - 1)/2;
        }else
            return -1;
    }

    //restituisce il figlio sinistro dell'elemento
    public T get_left_value(T element) {

        HashSet<Integer> hashSet= (this.HashHeap).get(element);
        int left_index = get_left_index(hashSet.iterator().next());
        return (this.ArrayHeap).get(left_index);

    }

    private int get_left_index(int index){

        if((2 * index) + 1 < size()) {
            return (2 * index) + 1;
        }else
            return index;

    }

    //restituisce figlio di destra dell'elemento
    public T get_right_value(T element) {

        HashSet<Integer> hashSet= (this.HashHeap).get(element);
        int right_index = get_right_index(hashSet.iterator().next());
        return (this.ArrayHeap).get(right_index);

    }

    private int get_right_index(int index){

        if((2 * index) + 2 < size()) {
            return (2 * index) + 2;
        }else
            return index;

    }

    public T extract_value(){

        T first_value = (this.ArrayHeap).get(0);

        if(size() > 1) {
            (this.ArrayHeap).set(0, (this.ArrayHeap).get(size() - 1));
            (this.ArrayHeap).set(size() - 1, first_value);

            switchofHashMap(0, size() - 1, first_value, (this.ArrayHeap).get(0));
            HashSet<Integer> hashSet = (this.HashHeap).get(first_value);
            hashSet.remove(size() - 1);
            if(hashSet.isEmpty())
                (this.HashHeap).remove(first_value);

            (this.ArrayHeap).remove(size() - 1);
            Heapify(0);
        }else {
            (this.ArrayHeap).remove(0);

            HashSet<Integer> hashSet = (this.HashHeap).get(first_value);
            hashSet.remove(0);
            if(hashSet.isEmpty())
                (this.HashHeap).remove(first_value);

        }

        return first_value;

    }

    private void Heapify(int index){

        int left_index = get_left_index(index);
        int right_index = get_right_index(index);
        int shortest_index;
        T shortest;

        if ((this.comparator).compare((this.ArrayHeap).get(left_index), (this.ArrayHeap).get(right_index)) <= 0) {
            shortest = (this.ArrayHeap).get(left_index);
            shortest_index = left_index;
        } else {
            shortest = (this.ArrayHeap).get(right_index);
            shortest_index = right_index;
        }
        if((this.comparator).compare((this.ArrayHeap).get(index), (this.ArrayHeap).get(shortest_index)) < 0){
            shortest = (this.ArrayHeap).get(index);
            shortest_index = index;
        }
        if (!(((this.comparator).compare(shortest, (this.ArrayHeap).get(index))) == 0)) {
            (this.ArrayHeap).set(shortest_index, (this.ArrayHeap).get(index));
            (this.ArrayHeap).set(index, shortest);
            //aggiornamento hashmap
            switchofHashMap(shortest_index, index, (this.ArrayHeap).get(index), (this.ArrayHeap).get(shortest_index));
            Heapify(shortest_index);
        }

    }

    //diminuzione del valore di un elemento
    public void decr_elem(int index, T new_value){

        if ((this.comparator).compare((this.ArrayHeap).get(index), new_value) < 0) {
            return;
        }else{
            HashSet<Integer> hashSet = (this.HashHeap).get((this.ArrayHeap).get(index));
            hashSet.remove(index);
            if(hashSet.isEmpty())
                (this.HashHeap).remove((this.ArrayHeap).get(index));

            (this.ArrayHeap).set(index, new_value);

            valueofHashMap(index, new_value);
        }

        ArrayHeap_update(index, new_value);

    }

    private void ArrayHeap_update(int index, T new_value) {

        if(index > 0) {

            int father_index = get_parent_index(index);
            T father_value = (this.ArrayHeap).get(father_index);

            if((this.comparator).compare((this.ArrayHeap).get(index), father_value) < 0){

                (this.ArrayHeap).set(father_index, (this.ArrayHeap).get(index));
                (this.ArrayHeap).set(index, father_value);

                switchofHashMap(father_index, index, (this.ArrayHeap).get(index), (this.ArrayHeap).get(father_index));
                ArrayHeap_update(father_index, new_value);
            }

        }else{

            if((this.comparator).compare(new_value, (this.ArrayHeap).get(index)) < 0){

                HashSet<Integer> hashSet_index = (this.HashHeap).get(new_value);

                if(hashSet_index.contains(1) == true) {
                    (this.ArrayHeap).set(1, (this.ArrayHeap).get(index));
                    (this.ArrayHeap).set(index, new_value);
                    switchofHashMap(1, index, new_value, (this.ArrayHeap).get(1));
                }else if (hashSet_index.contains(2) == true) {
                    (this.ArrayHeap).set(2, (this.ArrayHeap).get(index));
                    (this.ArrayHeap).set(index, new_value);
                    switchofHashMap(2, index, new_value, (this.ArrayHeap).get(2));
                }

            }

        }

    }


}
