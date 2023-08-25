#include <stdlib.h>
#include <stdio.h>
#include "skip_list.h"

#define MAX_HEIGHT 15

/*
  MAX_HEIGHT: è una costante che definisce il massimo numero di puntatori che possono esserci in un singolo nodo della skip_list.
  unsigned int size: è il numero di puntatori in un dato nodo della skip_list.
  unsigned int max_level: determina il massimo attuale tra i vari size.
 */

struct _SkipList {
    Node* head;
    unsigned int max_level;
    int (*compare) (void*,void*);
};

struct _Node {
    Node **next;
    unsigned int size;
    void* item;
};

//funzione che determina il numero di puntatori da includere nel nuovo nodo.
int random_level(){  

    int lvl = 1;

    //random() returns a random value in [0...1)

    while(((rand()%(1+1-0)+0)<0.5) && (lvl < MAX_HEIGHT)){        
        lvl = lvl + 1;
    }

    return lvl;

}

Node* createNode(void* I, unsigned int size_node){   

    //la size viene passata tramite la funzione random level.
    Node* nodo = (Node*)malloc(sizeof(Node));   
    nodo->size = size_node;
    nodo->item = I; 
    
    Node** next_node = (Node**)malloc(nodo->size*sizeof(Node*)); 

    for (int i = 0; i < size_node; i++) {
        next_node[i] = NULL;
    }

    nodo->next = next_node;
    return nodo;

}

void printNode(Node* nodo) {

    if(nodo->item != NULL){
        printf("size: %d, ", nodo->size);
        printf("item: %s \n", nodo->item);
        //printf("pointer node: %p;\n", nodo);
        //printf("pointer item: %p;\n", nodo->item);
    }
    else {
        printf("DUMMY NODE!\n");
    }

    int i = 0;
       
    while (i <= nodo->size-1) {
        if (nodo->next[i] != NULL)
            printf("nodo->next[pos]->item: %s in pos: %d;\n", nodo->next[i]->item, i);
        else
            printf("nodo->next[pos] is NULL in pos:%d.\n", i);
        i++;         
    }

}

void printList(SkipList* list) {   

    if (list != NULL) {

        Node* start = (Node*)malloc(sizeof(Node));
        start = list->head;

        if (list->head != NULL) {

            while (list->head != NULL) {

                printNode(list->head);
                list->head = list->head->next[0];
            }
        }
        else {
            printf("printList: LIST->HEAD IS NULL!\n");
        }
    }
    else {
        printf("printList: LIST IS NULL!\n");
    }

}

SkipList* create_empty_list(int (*compare)(void*, void*)) { 

    SkipList* list = (SkipList*)malloc(sizeof(SkipList));
    
    list->head = createNode(NULL, MAX_HEIGHT);      
    list->compare = compare;
    list->max_level = 1;

    return list;

}

void insert_Skip_List(SkipList* list, void* elem) { 
   
    Node* new = (Node*)malloc(sizeof(Node));
    new = createNode(elem, random_level());

    if(new->size > list->max_level){
        list->max_level = new->size;       
    }

    Node* x = (Node*)malloc(sizeof(Node));  
    x = list->head;
    
    for (int k = (list->max_level) - 1; k >= 0; k--) {
              
        if(x->next[k] == NULL || (list->compare(elem, x->next[k]->item)) < 0) { //elem < x->next[k]->item
                      
            if(k < new->size){                
                new->next[k] = x->next[k];
                x->next[k] = new;               
            }
            
        }else{          
            x = x->next[k];
            k++;
        }

    }
   
}


Node* delete_node(Node* pointer, int level) {
    
    Node* skip = (Node*)malloc(sizeof(Node));   

    if (pointer != NULL) {
        if (pointer->next[level] != NULL) {
            skip = delete_node(pointer->next[level], level);           
            if (level == 0) {
                pointer->next[level] = skip;
                free(pointer->next[level]);
                if (pointer->next[level] == NULL) {
                    //printf("SI CAZZO!\n");                   
                }
            }           
            pointer->next[level] = skip;
            return pointer->next[level];
        }
        else {                     
            return pointer->next[level];
        }   
    }
       
}


//metodo per eliminare e deallocare la lista.
void delete_skipList(SkipList* list) {
    Node* pointer = (Node*)malloc(sizeof(Node));

    if (list->head != NULL) { 
        
        int i = list->max_level - 1;
        while (i >= 0) {
            if (list->head->next[i] != NULL) {
                pointer = list->head->next[i];
                list->head->next[i] = delete_node(pointer, i);               
            }
            i--;
        }    
    }
}


void* search_skip_list(SkipList* list, void* item) {
    Node* x = (Node*)malloc(sizeof(Node));
    x = list->head;

    for (int i = list->max_level - 1; i >= 0; i--) {       
        while (x->next[i] && (list->compare(x->next[i]->item, item) < 0)) {            
            x = x->next[i];    
        }
    }
    
    //x->item < I <= x->next[0]->item
    x = x->next[0]; 
    
    if (x && (list->compare(x->item, item) == 0)) {
        return x->item;
    }
    else {
        return 0;
    }
}

void* return_head_item(SkipList* list) {
    return list->head->item;
}

void* return_next_item(SkipList* list, int pos) {   
   Node* next = list->head->next[0]; 
   while (pos != 0) {
       next = next->next[0];
       pos--;
   }
   return next->item;
}