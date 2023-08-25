package grafo;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Set;

public class Tests {

    class IntegerComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer i1, Integer i2) {
            return i1.compareTo(i2);
        }

    }

    class StringComparator implements Comparator<String> {
        @Override
        public int compare(String s1, String s2) {
            return s1.compareTo(s2);
        }

    }

    private Integer i1, i2, i3, i4, i5, i6, i7, i8, i9;
    private String s1, s2, s3, s4, s5, s6, s7;
    private Grafo<Integer, Integer> grafo;
    private Grafo<String, Integer> grafo_two;

    @Before
    public void createGrafo() throws GrafoException{

        i1 = 12;
        i2 = 0;
        i3 = 3;
        i4 = 6; //fino a qua nodi/vertici
        i5 = 4;
        i6 = 5;
        i7 = 10;
        i8 = 34; //fino a qua pesi
        //i9 = 15;

        s1 = "a";
        s2 = "b";
        s3 = "c";
        s4 = "d"; //nodi/vertici

        boolean direct = false;
        boolean direct_two = true;
        grafo = new Grafo<>(new IntegerComparator(), direct);
        grafo_two = new Grafo<>(new StringComparator(), direct_two);

    }

    @Test
    public void testSizeNode() throws Exception{

        grafo.addVertex(i1);
        grafo.addVertex(i2);
        grafo.addVertex(i3);
        grafo.addVertex(i4);

        grafo_two.addVertex(s1);
        grafo_two.addVertex(s2);
        grafo_two.addVertex(s3);
        grafo_two.addVertex(s4);

        assertEquals(4, grafo.sizeVertex());
        assertEquals(4, grafo_two.sizeVertex());
    }

    @Test
    public void testSizeArchi() throws Exception{

        grafo.addVertex(i1);
        grafo.addVertex(i2);
        grafo.addVertex(i3);
        grafo.addVertex(i4);

        grafo_two.addVertex(s1);
        grafo_two.addVertex(s2);
        grafo_two.addVertex(s3);
        grafo_two.addVertex(s4);

        grafo.addArco(i1, i2, i5);
        grafo.addArco(i2, i3, i6);
        grafo.addArco(i3, i4, i7);
        grafo.addArco(i4, i1, i8);

        grafo_two.addArco(s1, s2, i5);
        grafo_two.addArco(s2, s3, i6);
        grafo_two.addArco(s3, s4, i7);
        grafo_two.addArco(s4, s1, i8);

        assertEquals(4, grafo.sizeArchi());
        assertEquals(4, grafo_two.sizeArchi());
    }

    @Test
    public void testIsDirect() throws Exception{
        assertFalse(grafo.isDirect());
        assertTrue(grafo_two.isDirect());
    }

    @Test
    public void testContainsNode(){

        grafo.addVertex(i1);
        grafo.addVertex(i2);
        grafo.addVertex(i3);
        grafo.addVertex(i4);

        grafo_two.addVertex(s1);
        grafo_two.addVertex(s2);
        grafo_two.addVertex(s3);
        grafo_two.addVertex(s4);

        grafo.addArco(i1, i2, i5);
        grafo.addArco(i2, i3, i6);
        grafo.addArco(i3, i4, i7);
        grafo.addArco(i4, i1, i8);

        grafo_two.addArco(s1, s2, i5);
        grafo_two.addArco(s2, s3, i6);
        grafo_two.addArco(s3, s4, i7);
        grafo_two.addArco(s4, s1, i8);

        assertTrue(grafo.existVertex(0));
        assertTrue(grafo.existVertex(12));
        assertTrue(grafo.existVertex(3));
        assertTrue(grafo.existVertex(6));

        assertFalse(grafo.existVertex(22));

        assertTrue(grafo_two.existVertex("a"));
        assertTrue(grafo_two.existVertex("b"));
        assertTrue(grafo_two.existVertex("c"));
        assertTrue(grafo_two.existVertex("d"));

        assertFalse(grafo_two.existVertex("g"));
    }

    @Test
    public void testContainsArco() throws Exception{

        grafo.addVertex(i1);
        grafo.addVertex(i2);
        grafo.addVertex(i3);
        grafo.addVertex(i4);

        grafo_two.addVertex(s1);
        grafo_two.addVertex(s2);
        grafo_two.addVertex(s3);
        grafo_two.addVertex(s4);

        grafo.addArco(i1, i2, i5);
        grafo.addArco(i2, i3, i6);
        grafo.addArco(i3, i4, i7);
        grafo.addArco(i4, i1, i8);

        grafo_two.addArco(s1, s2, i5);
        grafo_two.addArco(s2, s3, i6);
        grafo_two.addArco(s3, s4, i7);
        grafo_two.addArco(s4, s1, i8);

        assertTrue((grafo.existArco(0, 12)));
        assertTrue((grafo.existArco(3, 0)));
        assertTrue((grafo.existArco(6, 3)));
        assertTrue((grafo.existArco(12, 6)));

        assertTrue((grafo_two.existArco("b", "a")));
        assertTrue((grafo_two.existArco("c", "b")));
        assertTrue((grafo_two.existArco("d", "c")));
        assertTrue((grafo_two.existArco("a", "d")));
    }

    /*@Test
    public void testEquals() {
        Arco<Integer, Integer> arco1 = new Arco<>(new IntegerComparator(), 12, 0, 32);
        Arco<Integer, Integer> arco2 = new Arco<>(new IntegerComparator(), 12, 0, 24);
        assertTrue(arco1.equals(arco2));
    }*/

    @Test
    public void testdeleteNode() throws Exception{

        grafo.addVertex(i1);
        grafo.addVertex(i2);
        grafo.addVertex(i3);
        grafo.addVertex(i4);

        grafo_two.addVertex(s1);
        grafo_two.addVertex(s2);
        grafo_two.addVertex(s3);
        grafo_two.addVertex(s4);

        grafo.addArco(i1, i2, i5);
        grafo.addArco(i2, i3, i6);
        grafo.addArco(i3, i4, i7);
        grafo.addArco(i4, i1, i8);

        grafo_two.addArco(s1, s2, i5);
        grafo_two.addArco(s2, s3, i6);
        grafo_two.addArco(s3, s4, i7);
        grafo_two.addArco(s4, s1, i8);

        assertTrue(grafo.deleteVertex(12));
        assertFalse(grafo.existVertex(12));
        assertFalse(grafo.existArco(0,12));
        assertFalse(grafo.existArco(12,6));

        assertFalse(grafo.deleteVertex(22));

        assertTrue(grafo_two.deleteVertex("a"));
        assertFalse(grafo_two.existVertex("a"));
        assertFalse(grafo_two.existArco("b","a"));
        assertFalse(grafo_two.existArco("a","d"));

        assertFalse(grafo_two.deleteVertex("g"));
    }

    @Test
    public void testdeleteArco() throws Exception{

        grafo.addVertex(i1);
        grafo.addVertex(i2);
        grafo.addVertex(i3);
        grafo.addVertex(i4);

        grafo_two.addVertex(s1);
        grafo_two.addVertex(s2);
        grafo_two.addVertex(s3);
        grafo_two.addVertex(s4);

        grafo.addArco(i1, i2, i5);
        grafo.addArco(i2, i3, i6);
        grafo.addArco(i3, i4, i7);
        grafo.addArco(i4, i1, i8);

        grafo_two.addArco(s1, s2, i5);
        grafo_two.addArco(s2, s3, i6);
        grafo_two.addArco(s3, s4, i7);
        grafo_two.addArco(s4, s1, i8);

        assertTrue(grafo.deleteArco(0, 12));
        assertFalse(grafo.existArco(0,12));

        assertTrue(grafo_two.deleteArco("b", "a"));
        assertFalse(grafo_two.existArco("b","a"));

    }

    @Test
    public void testlistofVertex() throws Exception{

        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        arrayList.add(i1);
        arrayList.add(i2);
        arrayList.add(i3);
        arrayList.add(i4);

        ArrayList<String> arrayList_two = new ArrayList<String>();
        arrayList_two.add(s1);
        arrayList_two.add(s2);
        arrayList_two.add(s3);
        arrayList_two.add(s4);

        grafo.addVertex(i1);
        grafo.addVertex(i2);
        grafo.addVertex(i3);
        grafo.addVertex(i4);

        grafo_two.addVertex(s1);
        grafo_two.addVertex(s2);
        grafo_two.addVertex(s3);
        grafo_two.addVertex(s4);

        Set<Integer> set = grafo.listofVertex();
        boolean assert_test = true;
        for (int i = 0; i < arrayList.size(); i++){
            if(!(set.contains(arrayList.get(i)))){
                assert_test = false;
            }
        }

        Set<String> set_two = grafo_two.listofVertex();
        boolean assert_test_two = true;
        for (int i = 0; i < arrayList_two.size(); i++){
            if(!(set_two.contains(arrayList_two.get(i)))){
                assert_test_two = false;
            }
        }
        assertTrue(assert_test);
        assertTrue(assert_test_two);

    }

    @Test
    public void testlistofArchi() throws Exception{

        ArrayList<Arco<Integer, Integer>> arrayList = new ArrayList<Arco<Integer, Integer>>();
        arrayList.add(new Arco<>(new IntegerComparator(), i1, i2, i5));
        arrayList.add(new Arco<>(new IntegerComparator(), i2, i3, i6));
        arrayList.add(new Arco<>(new IntegerComparator(), i3, i4, i7));
        arrayList.add(new Arco<>(new IntegerComparator(), i4, i1, i8));
        arrayList.add(new Arco<>(new IntegerComparator(), i2, i1, i5));
        arrayList.add(new Arco<>(new IntegerComparator(), i3, i2, i6));
        arrayList.add(new Arco<>(new IntegerComparator(), i4, i3, i7));
        arrayList.add(new Arco<>(new IntegerComparator(), i1, i4, i8));

        ArrayList<Arco<String, Integer>> arrayList_two = new ArrayList<Arco<String, Integer>>();
        arrayList_two.add(new Arco<>(new StringComparator(), s2, s1, i5));
        arrayList_two.add(new Arco<>(new StringComparator(), s3, s2, i6));
        arrayList_two.add(new Arco<>(new StringComparator(), s4, s3, i7));
        arrayList_two.add(new Arco<>(new StringComparator(), s1, s4, i8));

        grafo.addVertex(i1);
        grafo.addVertex(i2);
        grafo.addVertex(i3);
        grafo.addVertex(i4);

        grafo_two.addVertex(s1);
        grafo_two.addVertex(s2);
        grafo_two.addVertex(s3);
        grafo_two.addVertex(s4);

        grafo.addArco(i1, i2, i5);
        grafo.addArco(i2, i3, i6);
        grafo.addArco(i3, i4, i7);
        grafo.addArco(i4, i1, i8);

        grafo_two.addArco(s1, s2, i5);
        grafo_two.addArco(s2, s3, i6);
        grafo_two.addArco(s3, s4, i7);
        grafo_two.addArco(s4, s1, i8);

        Set<Arco<Integer, Integer>> set = grafo.listofArchi();
        boolean assert_test = true;
        for (int i = 0; i < arrayList.size(); i++){
            if(!(set.contains(arrayList.get(i)))){
                assert_test = false;
            }
        }

        Set<Arco<String, Integer>> set_two = grafo_two.listofArchi();
        boolean assert_test_two = true;

        for (int i = 0; i < arrayList_two.size(); i++){
            if(!(set_two.contains(arrayList_two.get(i)))){
                assert_test_two = false;
            }
        }
        assertTrue(assert_test);
        assertTrue(assert_test_two);

    }

    @Test
    public void testadjacencyVertex() throws Exception{

        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        //test per i1
        arrayList.add(i2);
        arrayList.add(i4);

        ArrayList<Integer> arrayList1 = new ArrayList<Integer>();
        //test per i4
        arrayList1.add(i1);
        arrayList1.add(i3);

        ArrayList<String> arrayList_two = new ArrayList<String>();
        //test per s3
        arrayList_two.add(s4);

        grafo.addVertex(i1);
        grafo.addVertex(i2);
        grafo.addVertex(i3);
        grafo.addVertex(i4);

        grafo_two.addVertex(s1);
        grafo_two.addVertex(s2);
        grafo_two.addVertex(s3);
        grafo_two.addVertex(s4);

        grafo.addArco(i1, i2, i5);
        grafo.addArco(i2, i3, i6);
        grafo.addArco(i3, i4, i7);
        grafo.addArco(i4, i1, i8);

        grafo_two.addArco(s1, s2, i5);
        grafo_two.addArco(s2, s3, i6);
        grafo_two.addArco(s3, s4, i7);
        grafo_two.addArco(s4, s1, i8);

        Set<Integer> set = grafo.adjacencyVertex(i1);
        boolean assert_test = true;
        for (int i = 0; i < arrayList.size(); i++){
            if(!(set.contains(arrayList.get(i)))){
                assert_test = false;
            }
        }
        assertTrue(assert_test);

        Set<Integer> set1 = grafo.adjacencyVertex(i4);
        boolean assert_test1 = true;
        for (int i = 0; i < arrayList1.size(); i++){
            if(!(set1.contains(arrayList1.get(i)))){
                assert_test1 = false;
            }
        }
        assertTrue(assert_test1);

        Set<String> set_two = grafo_two.adjacencyVertex(s3);
        boolean assert_test_two = true;
        for (int i = 0; i < arrayList_two.size(); i++){
            if(!(set_two.contains(arrayList_two.get(i)))){
                assert_test_two = false;
            }
        }
        assertTrue(assert_test_two);

    }

    @Test
    public void testweightbetweenVertex() throws Exception{

        grafo.addVertex(i1);
        grafo.addVertex(i2);
        grafo.addVertex(i3);
        grafo.addVertex(i4);

        grafo_two.addVertex(s1);
        grafo_two.addVertex(s2);
        grafo_two.addVertex(s3);
        grafo_two.addVertex(s4);

        grafo.addArco(i1, i2, i5);
        grafo.addArco(i2, i3, i6);
        grafo.addArco(i3, i4, i7);
        grafo.addArco(i4, i1, i8);

        grafo_two.addArco(s1, s2, i5);
        grafo_two.addArco(s2, s3, i6);
        grafo_two.addArco(s3, s4, i7);
        grafo_two.addArco(s4, s1, i8);

        assertEquals(i5, grafo.weightbetweenVertex(i2, i1));
        assertEquals(i6, grafo_two.weightbetweenVertex(s3, s2));
    }
}
