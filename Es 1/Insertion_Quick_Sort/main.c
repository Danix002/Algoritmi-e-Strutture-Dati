#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>	
#include "Insertion_Quick_Sort.h"

//#define FILE_PATH "C:/Users/danie/OneDrive/Documenti/Lezioni/Secondo anno/Algoritmi e Strutture dati/Laboratorio/Es 1/Test/records.csv"

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

static void load_array(const char* file_path, sorting* sort, int read_lines) {

	char* read_line;
	char buffer[1024];
	int buf_size = 1024;

	FILE* fp;
	printf("Loading csv...\n");
	fp = fopen(file_path, "r");

	if (fp == NULL) {
		printf("ERROR, UNABLE TO OPEN FILE.\n"); 
		exit(EXIT_FAILURE);
	}

	int i = 0;
	while (fgets(buffer, buf_size, fp) != NULL && i < read_lines) {

		read_line = malloc((strlen(buffer) + 1) * sizeof(char));

		if (read_line == NULL) {
			printf("main: unable to allocate memory for the read line.");
			exit(EXIT_FAILURE);
		}
			
		strcpy(read_line, buffer);

		int ID = atoi(strtok(read_line, ","));
		char* string_field_in_read_line = strtok(NULL, ",");
		char* integer_field_in_read_line = strtok(NULL, ",");
		char* float_field_in_read_line = strtok(NULL, ",");
			
		char* field_two = malloc((strlen(string_field_in_read_line) + 1) * sizeof(char));

		if (field_two == NULL) {
			printf("main: unable to allocate memory for the field_two of the read record");
			exit(EXIT_FAILURE);
		}
		strcpy(field_two, string_field_in_read_line);

		int field_three = atoi(integer_field_in_read_line);

		if (ID == 0 && i != 0) {
			printf("main: error for the ID of the read record");
			exit(EXIT_FAILURE);
		}

		float field_four = atof(float_field_in_read_line);

		record* record_array = malloc(sizeof(record));

		if (record_array == NULL) {
			printf("main: unable to allocate memory for the read record");
			exit(EXIT_FAILURE);
		}

		record_array->ID = ID;
		record_array->field_two = field_two;
		record_array->field_three = field_three;
		record_array->field_four = field_four;
		
		sorting_array_add(sort, (void*)record_array);
		
		free(read_line);
		i++;

	}

	printf("Number of items: %d \n", sort_size(sort));
	fclose(fp);
	printf("DONE.\n");

}


static void print_records(sorting* sort, int print) {

	unsigned long element_num = sort_size(sort);
	record* record_element;

	if (print == 2) {

		printf("\nORDERED ELEMENTS OF RECORDS\n");
		for (unsigned long i = 0; i < element_num; i++) {

			record_element = (record*)sorting_array_get(sort, i);
			printf("<%d,%s,%d,%f>\n", record_element->ID, record_element->field_two, record_element->field_three, record_element->field_four);

		}
	}
	else {

		FILE* fp;
		fp = fopen("output_records.csv", "w");

		if (fp == NULL) {
			printf("ERROR, UNABLE TO OPEN FILE.\n");
			exit(EXIT_FAILURE);
		}

		for (unsigned long i = 0; i < element_num; i++) {

			record_element = (record*)sorting_array_get(sort, i);
			fprintf(fp, "<%d,%s,%d,%f>\n", record_element->ID, record_element->field_two, record_element->field_three, record_element->field_four);

		}

		fclose(fp);

	}

	printf("Ho finito la print! \n");

}

static void test_with_comparison_function(const char* file_path, int (*compare)(void*, void*), int read_lines) {

	time_t start_t, end_t;
	double diff_t;

	sorting* sort = create_sorting();
	load_array(file_path, sort, read_lines);

	int method = 0;
	printf("Which method do you choose to order? 1.Binary_InsertionSort, 2.QuickSort..\n");
	scanf("%d", &method);

	time(&start_t);
	
	if (method == 1)
		insertionSort(pointer_of_array(sort), sort_size(sort), compare);
	else 
		quickSort(pointer_of_array(sort), 0, (sort_size(sort) - 1), compare);
	
	time(&end_t);
	diff_t = difftime(end_t, start_t);

	int print = 0;
	printf("I finished the algorithm, you want to print to file or prompt?  1.FILE, 2.PROMPT \n");
	scanf("%d", &print);
	print_records(sort, print);

	printf("Time to sort input into csv files: %f \n", diff_t);

	free_memory(sort);
	
}

int main(int argc, char const* argv[]){

	int read_lines = 0;
	printf("Insert number of read_lines:\n");
	scanf("%d", &read_lines);

	if (argc < 2) {
		printf("Usage: ordered_array_main <path_to_data_file>\n");
		exit(EXIT_FAILURE);
	}

	//gcc main.c Insertion_Quick_Sort.c -o eseguibile ---- eseguibile "C:/Users/danie/OneDrive/Documenti/Lezioni/Secondo anno/Algoritmi e Strutture dati/Laboratorio/Es 1/Test/records.csv"
	test_with_comparison_function(argv[1] /*FILE_PATH*/, compare_record_int_field, read_lines);
	test_with_comparison_function(argv[1] /*FILE_PATH*/, compare_record_string_field, read_lines);
	test_with_comparison_function(argv[1] /*FILE_PATH*/, compare_record_float_field, read_lines);
	
	return (EXIT_SUCCESS);
}
