#ifndef SKIP_LIST

#define SKIP_LIST
//struttura dati "probabilistica" che permette di realizzare operazioni di ricerca, inserimento
//cancellazione e disallocazione.

typedef struct _SkipList SkipList;
typedef struct _Node Node;

/*struct _SkipList {
    Node* head;
    unsigned int max_level;
    int (*compare) (void*,void*);
};

struct _Node {
    Node** next;
    unsigned int size;
    void* item;
};*/

/*
IsEmpty : List → Bool (predicato “L è vuota”)
IsEmpty (nil) = true
IsEmpty (Cons(z, L)) = false
Length : List → Int (funzione lunghezza)
Length (nil ) = 0
Length (Cons(z, L)) = 1 + Length(L)
 */

Node* createNode(void*, unsigned int); 
//int empty_list(SkipList*);
void insert_Skip_List(SkipList*, void*);

//ritorna l'ultimo elemento della lista;
Node* end_of_list_and_free(Node*);

Node* deleteNode(Node*, int);
void delete_skipList(SkipList*);
int random_level();
void* search_skip_list(SkipList*, void*);
SkipList* create_empty_list(int (*compare)(void*, void*));
void printNode(Node*);
void printList(SkipList*);

void* return_head_item(SkipList*);
void* return_next_item(SkipList*, int);

#endif //SKIP_LIST