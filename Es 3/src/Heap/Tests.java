package Heap;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import java.util.Comparator;

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

    private Integer i1, i2, i3, i4, i5, i6, i7;
    private String s1, s2, s3, s4, s5, s6, s7;
    private Heap<Integer> heap;
    private Heap<String> heap_two;

    @Before
    public void createArrayHeap() throws HeapException{
        i1 = 12;
        i2 = 0;
        i3 = 4; //3
        i4 = 6;
        i5 = 4;
        i6 = 5;
        i7 = 10;

        s1 = "a";
        s2 = "b";
        s3 = "c";
        s4 = "d";
        s5 = "e";
        s6 = "f";
        s7 = "g";
        heap = new Heap<>(new IntegerComparator());
        heap_two = new Heap <>(new StringComparator());
    }

    @Test
    public void testIsEmpty_zeroEl(){
        assertTrue(heap.isEmpty());
        assertTrue(heap_two.isEmpty());
    }

    @Test
    public void testIsEmpty_oneEl() throws Exception{
        heap.insert(i1);
        heap_two.insert(s1);
        assertFalse(heap.isEmpty());
        assertFalse(heap_two.isEmpty());
    }

    @Test
    public void testSize_zeroEl() throws Exception{
        assertEquals(0,heap.size());
        assertEquals(0,heap_two.size());
    }

    @Test
    public void testSize_AllEl() throws Exception{
        heap.insert(i1);
        heap.insert(i2);
        heap.insert(i3);
        heap.insert(i4);
        heap.insert(i5);
        heap.insert(i6);
        heap.insert(i7);

        heap_two.insert(s1);
        heap_two.insert(s2);
        heap_two.insert(s3);
        heap_two.insert(s4);
        heap_two.insert(s5);
        heap_two.insert(s6);
        heap_two.insert(s7);

        assertEquals(7,heap.size());
        assertEquals(7,heap_two.size());
    }

    /*@Test
    public void testSize_HashMap() throws Exception{

        heap.insert(i1);
        heap.insert(i2);
        heap.insert(i3);
        heap.insert(i4);
        heap.insert(i5);
        heap.insert(i6);
        heap.insert(i7);

        heap_two.insert(s1);
        heap_two.insert(s2);
        heap_two.insert(s3);
        heap_two.insert(s4);
        heap_two.insert(s5);
        heap_two.insert(s6);
        heap_two.insert(s7);

        assertEquals(7,heap.size_HashMap());
        assertEquals(7,heap_two.size_HashMap());
    }*/

    @Test
    public void testInsert_array() throws Exception{

        Integer[] arrExpected = {i2,i5,i3,i1,i4,i6,i7};
        String[] arrExpected_two = {s1,s2,s3,s4,s5,s6,s7};

        heap.insert(i1);
        heap.insert(i2);
        heap.insert(i3);
        heap.insert(i4);
        heap.insert(i5);
        heap.insert(i6);
        heap.insert(i7);

        heap_two.insert(s1);
        heap_two.insert(s2);
        heap_two.insert(s3);
        heap_two.insert(s4);
        heap_two.insert(s5);
        heap_two.insert(s6);
        heap_two.insert(s7);

        Integer[] arrActual = new Integer[7];
        String[] arrActual_two = new String[7];

        for(int i=0;i<7;i++)
            arrActual[i] = heap.get(i);

        for(int i=0;i<7;i++)
            arrActual_two[i] = heap_two.get(i);

        assertArrayEquals(arrExpected,arrActual);
        assertArrayEquals(arrExpected_two,arrActual_two);

    }

    @Test
    public void testArray_parentEl() throws Exception{
        heap.insert(i1);
        heap.insert(i2);
        heap.insert(i3);
        heap.insert(i4);
        heap.insert(i5);
        heap.insert(i6);
        heap.insert(i7);

        heap_two.insert(s1);
        heap_two.insert(s2);
        heap_two.insert(s3);
        heap_two.insert(s4);
        heap_two.insert(s5);
        heap_two.insert(s6);
        heap_two.insert(s7);

        assertTrue(0==heap.get_parent_value(i3));
        assertTrue(s1 ==heap_two.get_parent_value(s3));
    }

    @Test
    public void testArray_rightEl() throws Exception{
        heap.insert(i1);
        heap.insert(i2);
        heap.insert(i3);
        heap.insert(i4);
        heap.insert(i5);
        heap.insert(i6);
        heap.insert(i7);

        heap_two.insert(s1);
        heap_two.insert(s2);
        heap_two.insert(s3);
        heap_two.insert(s4);
        heap_two.insert(s5);
        heap_two.insert(s6);
        heap_two.insert(s7);

        assertTrue(6==heap.get_right_value(i5));
        assertTrue(s5==heap_two.get_right_value(s2));
    }

    @Test
    public void testArray_leftEl() throws Exception{
        heap.insert(i1);
        heap.insert(i2);
        heap.insert(i3);
        heap.insert(i4);
        heap.insert(i5);
        heap.insert(i6);
        heap.insert(i7);

        heap_two.insert(s1);
        heap_two.insert(s2);
        heap_two.insert(s3);
        heap_two.insert(s4);
        heap_two.insert(s5);
        heap_two.insert(s6);
        heap_two.insert(s7);

        assertTrue(4==heap.get_left_value(i2));
        assertTrue(s4==heap_two.get_left_value(s2));
    }

    @Test
    public void testExtract_value() throws Exception{
        heap.insert(i1);
        heap.insert(i2);
        heap.insert(i3);
        heap.insert(i4);
        heap.insert(i5);
        heap.insert(i6);
        heap.insert(i7);

        heap_two.insert(s1);
        heap_two.insert(s2);
        heap_two.insert(s3);
        heap_two.insert(s4);
        heap_two.insert(s5);
        heap_two.insert(s6);
        heap_two.insert(s7);

        /*System.out.println("Elements of ArrayList are:");
        for (int i = 0; i < heap.size(); i++) {
            System.out.println(heap.get(i) + " ");
        }*/

        assertTrue(0== heap.extract_value());
        assertTrue(s1== heap_two.extract_value());
        assertTrue(4==heap.get_parent_value(i5));
        assertTrue(s2==heap_two.get_parent_value(s2));
        assertTrue(4==heap.get_right_value(i3));
        assertTrue(s3==heap_two.get_right_value(s2));
        assertTrue((6==heap.get_left_value(i3)) || (5==heap.get_left_value(i3)));
        assertTrue(s4==heap_two.get_left_value(s2));

        assertTrue(4== heap.extract_value());
        assertTrue(s2== heap_two.extract_value());
        assertTrue(4== heap.extract_value());
        assertTrue(s3== heap_two.extract_value());
        assertTrue(5== heap.extract_value());
        assertTrue(s4== heap_two.extract_value());

        assertTrue(6==heap.get_parent_value(i1));
        assertTrue(s5== heap_two.get_parent_value(s6));
        assertTrue(10==heap.get_right_value(i4));
        assertTrue(s6==heap_two.get_right_value(s5));
        assertTrue(12==heap.get_left_value(i4));
        assertTrue(s7==heap_two.get_left_value(s5));

        assertTrue(6== heap.extract_value());
        assertTrue(s5== heap_two.extract_value());
        assertTrue(10== heap.extract_value());
        assertTrue(s6== heap_two.extract_value());
        assertTrue(12== heap.extract_value());
        assertTrue(s7== heap_two.extract_value());

        /*System.out.println("Elements of ArrayList are:");
        for (int i = 0; i < heap.size(); i++) {
            System.out.println(heap.get(i) + " ");
        }*/

        assertTrue(heap.isEmpty());
        assertTrue(heap_two.isEmpty());

    }

    @Test
    public void testDecr_El() throws Exception{

        Integer[] arrExpected = {-12,1,0,4,6,2,4};
        String[] arrExpected_two = {"a","b","a","b","e","c","g"};

        heap.insert(i1);
        heap.insert(i2);
        heap.insert(i3);
        heap.insert(i4);
        heap.insert(i5);
        heap.insert(i6);
        heap.insert(i7);

        heap_two.insert(s1);
        heap_two.insert(s2);
        heap_two.insert(s3);
        heap_two.insert(s4);
        heap_two.insert(s5);
        heap_two.insert(s6);
        heap_two.insert(s7);

        /*System.out.println("Elements of ArrayList are:");
        for (int i = 0; i < heap.size(); i++) {
            System.out.println(heap.get(i) + " ");
        }*/

        heap.decr_elem(6, 2);
        heap_two.decr_elem(5,"a");
        heap.decr_elem(3, 1);
        heap_two.decr_elem(3,"b");
        heap.decr_elem(5, -12);

        /*System.out.println("Elements of ArrayList are:");
        for (int i = 0; i < heap.size(); i++) {
            System.out.println(heap_two.get(i) + " ");
        }*/

        Integer[] arrActual = new Integer[7];
        String[] arrActual_two = new String[7];

        for(int i=0;i<7;i++)
            arrActual[i] = heap.get(i);

        for(int i=0;i<7;i++)
            arrActual_two[i] = heap_two.get(i);

        assertArrayEquals(arrExpected,arrActual);
        assertArrayEquals(arrExpected_two,arrActual_two);

    }


}
