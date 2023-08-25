#include <stdlib.h>
#include <stdio.h>
#include "Insertion_Quick_Sort.h"


struct _SORTING {
	void** ARRAY;  //array di puntatori void
	unsigned long number_of_element;
	unsigned long capacity;
};


int sort_size(sorting* sort) {

	if (sort == NULL) {
		fprintf(stderr, "sorting_array_is_empty: sorting_array parameter cannot be NULL");
		exit(EXIT_FAILURE);
	}else
		return sort->number_of_element;

}

sorting* create_sorting() {

	sorting* sort = (sorting*)malloc(sizeof(sorting));

	if (sort == NULL) {
		fprintf(stderr, "ordered_array_create: unable to allocate memory for the ordered array");
		exit(EXIT_FAILURE);
	}

	sort->ARRAY = (void**)malloc(CAPACITY * sizeof(void*));

	if (sort->ARRAY == NULL) {
		fprintf(stderr, "ordered_array_create: unable to allocate memory for the internal array");
		exit(EXIT_FAILURE);
	}

	sort->number_of_element = 0;
	sort->capacity = CAPACITY;

	return(sort);
}


void sorting_array_add(sorting* sort, void* record_array) {

	if (sort == NULL) {
		printf("sorting_array_add: sort parameter cannot be NULL");
		exit(EXIT_FAILURE);
	}

	if (record_array == NULL) {
		printf("sorting_array_add: element parameter cannot be NULL");
		exit(EXIT_FAILURE);
	}

	if (sort->number_of_element >= sort->capacity) {
		sort->ARRAY = (void**)realloc(sort->ARRAY, 2 * sort->capacity * sizeof(void*));
		
		if (sort->ARRAY == NULL) {
			printf("sorting_array_add: unable to reallocate memory to host the new element in the internal array");
			exit(EXIT_FAILURE);
		}

		sort->capacity = 2 * sort->capacity;

	}

	(sort->ARRAY)[sort->number_of_element] = record_array;
	(sort->number_of_element)++;

}


int binarySearch(void** array,void* selected, int (*compare)(void*, void*), int low, int high){

	if(high <= low)
		return ((*compare)(selected, array[low]) > 0) ? (low + 1) : low; 
	else {
		int mid = (low + high) / 2;

		if ((*compare)(selected, array[mid]) == 0)
			return mid + 1;

		else if ((*compare)(selected, array[mid]) > 0)
			return binarySearch(array, selected, (*compare), mid + 1, high);

		else
			return binarySearch(array, selected, (*compare), low, mid - 1);
	}
} 


void insertionSort(void** array, int n, int (*compare)(void*, void*)){


	int i, j, loc, k;

	for(int i = 1; i < n; i++){

		j = i - 1;
		void* selected = array[i];

		loc = binarySearch(array, selected, (*compare), 0, j);

		while(j >= loc){			
			array[j+1] = array[j];
				j--;
		}
		
		array[j+1] = selected;
	}

}


void swap(void** pointer_one, void** pointer_two) {

	void* temp = *pointer_one;
	*pointer_one = *pointer_two;
	*pointer_two = temp;

}


int partition(void** array, int low, int high, int (*compare)(void*, void*)) {
	
	//start: 
	/*void* pivot = array[low]; //scelta del pivot

	int i = low;
	
	for (int j = low + 1; j <= high - 1; j++) {
		if ((*compare)(array[j], pivot) < 0) {
			i++;
			swap(&array[i], &array[j]);
		}
	}

	swap(&array[i], &array[low]);
	return i;
	*/

	//mid:
	void* pivot = array[(low + high)/2]; //scelta del pivot

	int i = low;
	int j = high;
	while(i <= j){
		while ((*compare)(array[i], pivot) < 0) {
			i++;	
		}
		while ((*compare)(array[j], pivot) > 0) {
			j--;
		}
		if (i <= j) {
			swap(&array[i], &array[j]);
			i++;
			j--;
		}
	}

	return i;

	//end:
	/*void* pivot = array[high]; //scelta del pivot

	int i = low - 1;

	for (int j = low; j <= high - 1; j++) {
		if ((*compare)(array[j], pivot) < 0) {
			i++;
			swap(&array[i], &array[j]);
		}
	}

	swap(&array[i + 1], &array[high]);
	return i + 1;
	*/
}



void quickSort(void** array, int start_array, int end_array, int (*compare)(void*, void*)) {

	if (start_array < end_array) {

		int q = partition(array, start_array, end_array, (*compare));
		quickSort(array, start_array, q - 1, (*compare));
		quickSort(array, q + 1, end_array, (*compare));
	}

}


void* sorting_array_get(sorting* sort, unsigned long i) {

	if (sort == NULL) {
		fprintf(stderr, "sorting_array_is_empty: sorting_array parameter cannot be NULL");
		exit(EXIT_FAILURE);
	}
	else
		return(sort->ARRAY)[i];
}


int sorting_array_is_empty(sorting* sort) {

	if (sort == NULL) {
		fprintf(stderr, "sorting_array_is_empty: sorting_array parameter cannot be NULL");
		exit(EXIT_FAILURE);
	}
	else {
		if (sort->number_of_element == 0) 
			return 1;
		else 
			return 0;
	}

}

void free_memory(sorting* sort) {

	unsigned long element_num = sort->number_of_element;

	for (unsigned long i = 0; i < element_num; i++) {
		free(sort->ARRAY[i]);
	}

	if (sort == NULL) {
		printf("free_memory: sort parameter cannot be NULL");
		exit(EXIT_FAILURE);
	}

	free(sort->ARRAY);
	free(sort);

}


void** pointer_of_array(sorting* sort) {

	if (sort == NULL) {
		printf("free_memory: sort parameter cannot be NULL");
		exit(EXIT_FAILURE);
	}
	else
		return sort->ARRAY;

}



