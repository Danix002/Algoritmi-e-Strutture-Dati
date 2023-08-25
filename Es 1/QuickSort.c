#include <stdlib.h>
#include <stdio.h>

int bSearch(int n, int array[], int start, int end) {
    int middle = (start + end) / 2;

    if (start > end)
        return 0;

    if (n == array[middle])
        return 1;

    if (n > array[middle])
        return bSearch(n, array, middle + 1, end);
    else
        return bSearch(n, array, start, middle - 1);
}

void swap(int v[], int i, int j) {
    int temp = v[i];
    v[i] = v[j];
    v[j] = temp;
    return;
}

int partition(int v[], int start, int end) {
    int x, l, j;

    x = v[end - 1];
    l = start - 1;

    for (j = start; j < end - 1; j++) {
        if (v[j] < x) {
            l++;
            swap(v, j, l);
        }
    }
    l++;
    swap(v, l, end - 1);
    return l;
}

void quickSort(int v[], int start, int end) {
    int q;

    if (end > start) {
        q = partition(v, start, end);
        quickSort(v, start, q);
        quickSort(v, q + 1, end);
    }
    return;
}