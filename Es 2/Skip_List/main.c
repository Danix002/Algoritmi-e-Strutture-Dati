#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "skip_list.h"
#include <time.h>

//#define FILE_PATH "C:/Users/danie/OneDrive/Documenti/Lezioni/Secondo anno/Algoritmi e Strutture dati/Laboratorio/Es 2/Test/dictionary.txt"
//#define FILE_PATH_2 "C:/Users/danie/OneDrive/Documenti/Lezioni/Secondo anno/Algoritmi e Strutture dati/Laboratorio/Es 2/Test/correctme.txt"

static int compare_function(void* elem1, void* elem2) {

    if (elem1 == NULL) {
        printf("compare_function error : first parameter null");
        exit(EXIT_FAILURE);
    }

    if(elem2 == NULL){
        printf("compare_function error : second parameter null");
        exit(EXIT_FAILURE);
    }

    return strcmp((char*)elem1, (char*)elem2);

}


static void readfile(const char* file_path, SkipList* list){

    int max_line_length = 50;
    char line[max_line_length];
    char* elem;
    char* elem_2;

    //open file:
    FILE *fp = fopen(file_path,"r");

    if(!fp){
        printf("ERROR, UNABLE TO OPEN FILE.\n");
        exit(EXIT_FAILURE);
    }

    printf("Ho aperto il file.\n");

    //get lines:
    while(fgets(line, max_line_length, fp) != NULL){
  
        elem_2 = strtok(line, "\n");
        elem = strdup(elem_2);         
        if (elem != NULL) {
            insert_Skip_List(list, (void*)elem);
        }

    }
    fclose(fp);
}


static void readfile_wrong(const char* file_path, SkipList* list) { //vedere foto su discord

    int max_line_length = 300; 
    int i = 0;
    char line[max_line_length];    
    char* words[max_line_length];
    char* elem; 

    //open file:
    FILE* fp = fopen(file_path, "r");

    if (!fp) {
        printf("ERROR, UNABLE TO OPEN FILE.\n");
        exit(EXIT_FAILURE);
    }

    printf("Ho aperto wrong file.\n");
    
    //get words:
    while (fgets(line, max_line_length, fp)) {
        
        elem = strtok(line, " ,.:!;\n");
        words[i] = elem;
        i++;
              
        while (elem != NULL) {
            elem = strtok(NULL, " ,.:!;\n");
            words[i] = elem;
            i++;
        }
    }
    
    fclose(fp);

    for (int j = 0; j < max_line_length && words[j]; j++) {
        if ((search_skip_list(list, (void*)words[j])) == 0) {
            printf("Elemento da correggere: %s\n", words[j]);
        }
    }

}

//gcc main.c skip_list.c -o eseguibile ---- eseguibile path1 path2
int main(int argc, char const* argv[]) {
   
    time_t start_t, end_t;
    double diff_t;

    /*
    char* elem1 = "21";
    char* elem2 = "12";
    char* elem3 = "11";
    char* elem4 = "15";
    char* elem5 = "14";
    char* elem6 = "10";
    */

    SkipList* list /*= (SkipList*)malloc(sizeof(SkipList))*/;

    /*Node* test = (Node*)malloc(sizeof(Node));
    test = createNode((void*)elem1, random_level());
    printNode(test);
    Node* test2 = (Node*)malloc(sizeof(Node));
    test2 = createNode((void*)elem2, random_level());
    printNode(test2);
    Node* test3 = (Node*)malloc(sizeof(Node));
    test3 = createNode((void*)elem3, random_level());
    printNode(test3);
    */

    list = create_empty_list(compare_function);

    /*
    insert_Skip_List(list, (void*)elem1);
    insert_Skip_List(list, (void*)elem2);
    insert_Skip_List(list, (void*)elem3); 
    insert_Skip_List(list, (void*)elem4); 
    insert_Skip_List(list, (void*)elem5);
    insert_Skip_List(list, (void*)elem6);
    
    //search_skip_list(list, (void*)"21");
    //search_skip_list(list, (void*)"23");
    printList(list);
    //delete_skipList(list); 
    //printList(list);
    */

    if (argc < 3) {
        printf("Usage: ordered_array_main <path_to_data_file>\n");
        exit(EXIT_FAILURE);
    }

    time(&start_t);

    readfile(argv[1] /*FILE_PATH*/, list);
    readfile_wrong(argv[2] /*FILE_PATH_2*/, list);
    //search_skip_list(list, (void*)"glittico");
    //printList(list);

    time(&end_t);
    diff_t = difftime(end_t, start_t);

    printf("Time to read files: %f\n", diff_t);

    return 0;

}
