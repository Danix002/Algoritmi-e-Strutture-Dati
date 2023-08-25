#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "unity.h"
#include "skip_list.h"

static int compare_function(void* elem1, void* elem2) {

    if (elem1 == NULL) {
        printf("compare_function error : first parameter null");
        exit(EXIT_FAILURE);
    }

    if (elem2 == NULL) {
        printf("compare_function error : second parameter null");
        exit(EXIT_FAILURE);
    }

    return strcmp(elem1, elem2);

}

static SkipList* list_one;
static SkipList* list_two;

static char* word_1 = "tetrarchiche";
static char* word_2 = "nuclearizare";

static int int_1 = 12;
static int int_2 = 4;

static char* elem1;
static char* elem2;
static char* elem3;
//static char* elem4;
//static char* elem5;
//static char* elem6;

static int elem_two_1;
static int elem_two_2;
static int elem_two_3;
//static int elem_two_4;
//static int elem_two_5;
//static int elem_two_6;

void setUp(void) {
  
    elem1 = "tetrarchiche";  
    elem2 = "zigzagavano";   
    elem3 = "impadronirono"; 
    //elem4 = "nuclearizzare";
    //elem5 = "diseppellissimo";
    //elem6 = "glittico";

    //list_one = (SkipList*)malloc(sizeof(SkipList)); 

    elem_two_1 = 2;
    elem_two_2 = 0;
    elem_two_3 = 12;
    //elem_two_4 = 8;
    //elem_two_5 = 10;
    //elem_two_6 = 5;

    //list_two = (SkipList*)malloc(sizeof(SkipList));   


}

void test_create_empty_list_one(void) {     
    list_one = create_empty_list(compare_function);   
    TEST_ASSERT_NULL(return_head_item(list_one));
}

void test_create_empty_list_two(void) {
    list_two = create_empty_list(compare_function);
    TEST_ASSERT_NULL(return_head_item(list_two));
}

void test_insert_Skip_List_one(void) {

    list_one = create_empty_list(compare_function);
    insert_Skip_List(list_one, (void*)elem1);  //pos 2   
    insert_Skip_List(list_one, (void*)elem2);  //pos 3    
    insert_Skip_List(list_one, (void*)elem3);  //pos 1   
    //insert_Skip_List(list_one, (void*)elem4);  
    //insert_Skip_List(list_one, (void*)elem5);  
    //insert_Skip_List(list_one, (void*)elem6);

    int pos = 0;
    TEST_ASSERT_EQUAL_PTR(elem3, return_next_item(list_one, pos));
    pos = 1;
    TEST_ASSERT_EQUAL_PTR(elem1, return_next_item(list_one, pos));
    pos = 2;
    TEST_ASSERT_EQUAL_PTR(elem2, return_next_item(list_one, pos));

}

void test_insert_Skip_List_two(void) {  

    list_two = create_empty_list(compare_function);
    insert_Skip_List(list_two, (void*)&elem_two_1);  //pos 2
    insert_Skip_List(list_two, (void*)&elem_two_2);  //pos 3  
    insert_Skip_List(list_two, (void*)&elem_two_3);  //pos 1    
    //insert_Skip_List(list_one, (void*)elem4);  
    //insert_Skip_List(list_one, (void*)elem5);     
    //insert_Skip_List(list_one, (void*)elem6);

    int pos = 0;
    TEST_ASSERT_EQUAL_PTR(&elem_two_2, return_next_item(list_two, pos));
    pos = 1;
    TEST_ASSERT_EQUAL_PTR(&elem_two_1, return_next_item(list_two, pos));
    pos = 2;
    TEST_ASSERT_EQUAL_PTR(&elem_two_3, return_next_item(list_two, pos));
    
}

void test_word_1(void) {
    
    list_one = create_empty_list(compare_function);
    insert_Skip_List(list_one, (void*)elem1);     
    insert_Skip_List(list_one, (void*)elem2);      
    insert_Skip_List(list_one, (void*)elem3);   

    TEST_ASSERT_EQUAL_PTR(elem1, search_skip_list(list_one, (void*)word_1)); 
   
}

void test_word_2(void) {

    list_one = create_empty_list(compare_function);
    insert_Skip_List(list_one, (void*)elem1);
    insert_Skip_List(list_one, (void*)elem2);
    insert_Skip_List(list_one, (void*)elem3);

    TEST_ASSERT_NOT_EQUAL(elem1, search_skip_list(list_one, (void*)word_2));
    TEST_ASSERT_NOT_EQUAL(elem2, search_skip_list(list_one, (void*)word_2));
    TEST_ASSERT_NOT_EQUAL(elem3, search_skip_list(list_one, (void*)word_2));

}

void test_int_1(void) {

    list_two = create_empty_list(compare_function);
    insert_Skip_List(list_two, (void*)&elem_two_1);
    insert_Skip_List(list_two, (void*)&elem_two_2);
    insert_Skip_List(list_two, (void*)&elem_two_3);

    TEST_ASSERT_EQUAL_PTR(&elem_two_3, search_skip_list(list_two, &int_1));

}

void test_int_2(void) {

    list_two = create_empty_list(compare_function);
    insert_Skip_List(list_two, (void*)&elem_two_1);
    insert_Skip_List(list_two, (void*)&elem_two_2);
    insert_Skip_List(list_two, (void*)&elem_two_3);

    TEST_ASSERT_NOT_EQUAL(&elem_two_1, search_skip_list(list_two, &int_2));
    TEST_ASSERT_NOT_EQUAL(&elem_two_2, search_skip_list(list_two, &int_2));
    TEST_ASSERT_NOT_EQUAL(&elem_two_3, search_skip_list(list_two, &int_2));

}

void tearDown(void) {

    list_one = create_empty_list(compare_function);
    insert_Skip_List(list_one, (void*)elem1);
    insert_Skip_List(list_one, (void*)elem2);
    insert_Skip_List(list_one, (void*)elem3);

    delete_skipList(list_one);
    TEST_ASSERT_NULL(return_head_item(list_one));

    list_two = create_empty_list(compare_function);
    insert_Skip_List(list_two, (void*)&elem_two_1);
    insert_Skip_List(list_two, (void*)&elem_two_2);
    insert_Skip_List(list_two, (void*)&elem_two_3);

    delete_skipList(list_two);
    TEST_ASSERT_NULL(return_head_item(list_two));

}

int main(void) {

    // test session
    UNITY_BEGIN();

    RUN_TEST(test_create_empty_list_one);
    RUN_TEST(test_create_empty_list_two);
    RUN_TEST(test_insert_Skip_List_one);
    RUN_TEST(test_insert_Skip_List_two);
    RUN_TEST(test_word_1);  
    RUN_TEST(test_word_2);  
    RUN_TEST(test_int_1);   
    RUN_TEST(test_int_2);
    RUN_TEST(tearDown);

    return UNITY_END();
}
