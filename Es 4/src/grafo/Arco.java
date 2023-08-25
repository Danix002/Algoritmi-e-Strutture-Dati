package grafo;

import java.util.Comparator;
import java.util.Objects;

public class Arco <T, E> {
    //vertice di partenza, arrivo e peso
    private T destination;
    private T start;
    private E weight;

    private Comparator<? super T> comparator_T = null;

    public Arco(Comparator<? super T> comparator_T, T destination, T start, E weight){
        this.destination = destination;
        this.start = start;
        this.weight = weight;
        this.comparator_T  = comparator_T;
    }

    @Override
    public boolean equals(Object o){
        if(o == this)
            return true;
        if(!(o instanceof Arco))
            return false;

        return( comparator_T.compare(this.start, ((Arco<T, E>) o).getStart()) == 0 &&
                comparator_T.compare(this.destination, ((Arco<T, E>) o).getDestination()) == 0 );
    }

    @Override
    public int hashCode(){
        int hash = 31;
        hash = 17 * hash + this.start.hashCode();
        hash = 17 * hash + this.destination.hashCode();
        return hash;

    }

    public T getDestination(){
        return this.destination;
    }

    public T getStart(){
        return this.start;
    }

    public E getWeight(){
        return this.weight;
    }

    public void setDestination(T destination){
        this.destination = destination;
    }

    public void setStart(T start) {
        this.start = start;
    }

    public void setWeight(E weight) {
        this.weight = weight;
    }

    public String toString(){
        return this.start + " " + this.destination;
    }

}
