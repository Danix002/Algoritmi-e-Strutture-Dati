#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "unity.h"
#include "Insertion_Quick_Sort.h"

struct _RECORD {
    int ID;
    char* field_two;
    int field_three;
    float field_four;
};

static int compare_record_int_field(void* el_one, void* el_two) {		

    if (el_one == NULL) {
        fprintf(stderr, "record_int_field: the first parameter cannot be NULL");
        exit(EXIT_FAILURE);
    }
    if (el_two == NULL) {
        fprintf(stderr, "record_int_field: the first parameter cannot be NULL");
        exit(EXIT_FAILURE);
    }

    record* element_one = (record*)el_one;
    record* element_two = (record*)el_two;

    if (element_one->field_three < element_two->field_three)
        return(-1);
    else if (element_one->field_three > element_two->field_three)
        return(1);
    else
        return 0;

}

static int compare_record_string_field(void* el_one, void* el_two) {	

    if (el_one == NULL) {
        fprintf(stderr, "record_string_field: the second parameter cannot be NULL");
        exit(EXIT_FAILURE);
    }
    if (el_two == NULL) {
        fprintf(stderr, "record_string_field: the second parameter cannot be NULL");
        exit(EXIT_FAILURE);
    }

    record* element_one = (record*)el_one;
    record* element_two = (record*)el_two;

    //se < 0 elemento uno < elemento due
    return (strcmp(element_one->field_two, element_two->field_two));	

}

static int compare_record_float_field(void* el_one, void* el_two) {		

    if (el_one == NULL) {
        fprintf(stderr, "record_float_field: the fourth parameter cannot be NULL");
        exit(EXIT_FAILURE);
    }
    if (el_two == NULL) {
        fprintf(stderr, "record_float_field: the fourth parameter cannot be NULL");
        exit(EXIT_FAILURE);
    }

    record* element_one = (record*)el_one;
    record* element_two = (record*)el_two;

    if (element_one->field_four < element_two->field_four)
        return(-1);
    else if (element_one->field_four > element_two->field_four)
        return(1);
    else
        return 0;

}


static sorting* sort_array;

static int id_1 = 0;
static int id_2 = 1;
static int id_3 = 2;

static int field_three_1 = -12;
static int field_three_2 = 4;
static int field_three_3 = 0;

static char field_two_1[] = "Stefano";
static char field_two_2[] = "Luigi";
static char field_two_3[] = "Mario";

static float field_four_1 = 4.5768;
static float field_four_2 = 3.6980;
static float field_four_3 = 10.3333;

static record* record_array_1;
static record* record_array_2;
static record* record_array_3;


void print_array(sorting* sort) {

    unsigned long element_num = sort_size(sort);
    record* record_element;

    printf("\nORDERED ELEMENTS OF RECORDS\n");

    for (unsigned long i = 0; i < element_num; i++) {

        record_element = (record*)sorting_array_get(sort, i);
        printf("<%d,%s,%d,%f>\n", record_element->ID, record_element->field_two, record_element->field_three, record_element->field_four);

    }
}

void setUp(void) {

    record_array_1 = malloc(sizeof(record));
    record_array_2 = malloc(sizeof(record));
    record_array_3 = malloc(sizeof(record));

    sort_array = create_sorting();

    record_array_1->ID = id_1;
    record_array_1->field_two = field_two_1;
    record_array_1->field_three = field_three_1;
    record_array_1->field_four = field_four_1;

    record_array_2->ID = id_2;
    record_array_2->field_two = field_two_2;
    record_array_2->field_three = field_three_2;
    record_array_2->field_four = field_four_2;

    record_array_3->ID = id_3;
    record_array_3->field_two = field_two_3;
    record_array_3->field_three = field_three_3;
    record_array_3->field_four = field_four_3;
   

}

void tearDown(void) {

    free_memory(sort_array);

}

void test_size(void) {

    sorting_array_add(sort_array, record_array_3);
    sorting_array_add(sort_array, record_array_2);
    sorting_array_add(sort_array, record_array_1);
    
    //print_array(sort_array);

    TEST_ASSERT_EQUAL_INT(3, sort_size(sort_array));

}

void test_is_empty(void) {

    TEST_ASSERT_TRUE(sorting_array_is_empty(sort_array));

}

void test_isnot_empty(void) {

    sorting_array_add(sort_array, record_array_3);
    
    //print_array(sort_array);

    TEST_ASSERT_FALSE(sorting_array_is_empty(sort_array));

}

void test_insertion_int(void) {

    sorting_array_add(sort_array, record_array_3);
    sorting_array_add(sort_array, record_array_2);
    sorting_array_add(sort_array, record_array_1);

    //print_array(sort_array);

    insertionSort(pointer_of_array(sort_array), sort_size(sort_array), compare_record_int_field);

    //print_array(sort_array);

    void* array_expected[] = { sorting_array_get(sort_array, 0), sorting_array_get(sort_array, 1), sorting_array_get(sort_array, 2) };
    void* array_sorting[] = { record_array_1, record_array_3, record_array_2 };
    TEST_ASSERT_EQUAL_PTR_ARRAY(array_expected, array_sorting, 3);

}

void test_insertion_char(void) {

    sorting_array_add(sort_array, record_array_3);
    sorting_array_add(sort_array, record_array_2);
    sorting_array_add(sort_array, record_array_1);

    //print_array(sort_array);

    insertionSort(pointer_of_array(sort_array), sort_size(sort_array), compare_record_string_field);

    //print_array(sort_array);

    void* array_expected[] = { sorting_array_get(sort_array, 0), sorting_array_get(sort_array, 1), sorting_array_get(sort_array, 2) };
    void* array_sorting[] = { record_array_2, record_array_3, record_array_1 };
    TEST_ASSERT_EQUAL_PTR_ARRAY(array_expected, array_sorting, 3);

}

void test_insertion_float(void) {

    sorting_array_add(sort_array, record_array_3);
    sorting_array_add(sort_array, record_array_2);
    sorting_array_add(sort_array, record_array_1);

    //print_array(sort_array);

    insertionSort(pointer_of_array(sort_array), sort_size(sort_array), compare_record_float_field);

    //print_array(sort_array);

    void* array_expected[] = { sorting_array_get(sort_array, 0), sorting_array_get(sort_array, 1), sorting_array_get(sort_array, 2) };
    void* array_sorting[] = { record_array_2, record_array_1, record_array_3 };
    TEST_ASSERT_EQUAL_PTR_ARRAY(array_expected, array_sorting, 3);

}

void test_quick_int(void) {

    sorting_array_add(sort_array, record_array_3);
    sorting_array_add(sort_array, record_array_2);
    sorting_array_add(sort_array, record_array_1);

    //print_array(sort_array);

    quickSort(pointer_of_array(sort_array), 0, (sort_size(sort_array) - 1), compare_record_int_field);

    //print_array(sort_array);

    void* array_expected[] = { sorting_array_get(sort_array, 0), sorting_array_get(sort_array, 1), sorting_array_get(sort_array, 2) };
    void* array_sorting[] = { record_array_1, record_array_3, record_array_2 };
    TEST_ASSERT_EQUAL_PTR_ARRAY(array_expected, array_sorting, 3);
  
}

void test_quick_char(void) {

    sorting_array_add(sort_array, record_array_3);
    sorting_array_add(sort_array, record_array_2);
    sorting_array_add(sort_array, record_array_1);

    //print_array(sort_array);

    quickSort(pointer_of_array(sort_array), 0, (sort_size(sort_array) - 1), compare_record_string_field);

    //print_array(sort_array);

    void* array_expected[] = { sorting_array_get(sort_array, 0), sorting_array_get(sort_array, 1), sorting_array_get(sort_array, 2) };
    void* array_sorting[] = { record_array_2, record_array_3, record_array_1 };
    TEST_ASSERT_EQUAL_PTR_ARRAY(array_expected, array_sorting, 3);

}

void test_quick_float(void) {

    sorting_array_add(sort_array, record_array_3);
    sorting_array_add(sort_array, record_array_2);
    sorting_array_add(sort_array, record_array_1);

    //print_array(sort_array);

    quickSort(pointer_of_array(sort_array), 0, (sort_size(sort_array) - 1), compare_record_float_field);

    //print_array(sort_array);

    void* array_expected[] = { sorting_array_get(sort_array, 0), sorting_array_get(sort_array, 1), sorting_array_get(sort_array, 2) };
    void* array_sorting[] = { record_array_2, record_array_1, record_array_3 };
    TEST_ASSERT_EQUAL_PTR_ARRAY(array_expected, array_sorting, 3);

}


int main(void) {

    //test session
    UNITY_BEGIN();

    RUN_TEST(test_size);
    RUN_TEST(test_is_empty);
    RUN_TEST(test_isnot_empty);
    RUN_TEST(test_insertion_int);
    RUN_TEST(test_insertion_char);
    RUN_TEST(test_insertion_float);
    RUN_TEST(test_quick_int);
    RUN_TEST(test_quick_char);
    RUN_TEST(test_quick_float);

    return UNITY_END();
}