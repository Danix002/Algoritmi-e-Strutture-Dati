package grafo;

public class Values<T> {

    public double d;
    public T pi;
    public T vertex;

    public Values(double d, T pi, T vertex){
        this.d = d;
        this.pi = pi;
        this.vertex = vertex;
    }

    public int isEquals(Values v){
        if(v.vertex.equals(this.vertex))
            return 0;
        else
            return -1;

    }

    @Override
    public int hashCode(){
        int hash = 31;
        //hash = (int) (17 * hash + this.d);
        //hash = 17 * hash + this.pi.hashCode();
        hash = 17 * hash + this.vertex.hashCode();
        return hash;

    }

    @Override
    public boolean equals(Object o){
        if(o == this)
            return true;
        if(!(o instanceof Values))
            return false;

        return( ((Values<?>) o).vertex.equals(this.vertex) );
    }

    @Override
    public String toString() {
        return (this.vertex).toString();
    }

    public void setD(double d) {
        this.d = d;
    }

    public void setPi(T pi) {
        this.pi = pi;
    }

    public void setVertex(T vertex) {
        this.vertex = vertex;
    }
}
