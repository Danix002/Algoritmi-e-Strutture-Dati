#ifndef INSERTION_QUICK_SORT

#define INSERTION_QUICK_SORT
#define CAPACITY 1000

typedef struct _SORTING sorting;
typedef struct _RECORD record;

int sort_size(sorting* sort);
sorting* create_sorting();
void sorting_array_add(sorting* sort, void* record_array);
int binarySearch(void** array, void* selected, int (*compare)(void*, void*), int low, int high);
void insertionSort(void** array, int k, int (*compare)(void*, void*));
void swap(void** pointer_one, void** pointer_two);
int partition(void** array, int start_array, int end_array, int (*compare)(void*, void*));
void quickSort(void** array, int start_array, int end_array, int (*compare)(void*, void*));
void* sorting_array_get(sorting* sort, unsigned long i);
int sorting_array_is_empty(sorting* sort);
void free_memory(sorting* sort);
void** pointer_of_array(sorting* sort);

#endif //INSERTION_QUICK_SORT