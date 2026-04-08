package main;

import java.util.Arrays;

/**
 * Kelas ArrayOperations
 * Berisi metode-metode untuk operasi dasar pada Array:
 * traversal, linear search, binary search, insert, dan delete.
 */
public class ArrayOperations {

    /**
     * Traversal - menampilkan seluruh isi array
     * Time Complexity: O(n)
     */
    public void traversal(int[] arr) {
        System.out.println("Array Traversal: " + Arrays.toString(arr));
    }

    /**
     * Linear Search - mencari nilai satu per satu dari awal
     * Tidak memerlukan array terurut
     * Time Complexity: O(n)
     * @return indeks jika ditemukan, -1 jika tidak
     */
    public int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) return i;
        }
        return -1;
    }

    /**
     * Binary Search - mencari dengan membagi dua rentang pencarian
     * SYARAT: array harus sudah terurut (sorted)
     * Time Complexity: O(log n)
     * @return indeks jika ditemukan, -1 jika tidak
     */
    public int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target)     return mid;
            else if (arr[mid] < target) left = mid + 1;
            else                        right = mid - 1;
        }
        return -1;
    }

    /**
     * Insert - menyisipkan nilai di posisi tertentu
     * Menggunakan System.arraycopy() untuk efisiensi pergeseran elemen
     * Time Complexity: O(n)
     * @return array baru hasil penyisipan
     */
    public int[] insert(int[] arr, int index, int value) {
        int[] newArr = new int[arr.length + 1];
        System.arraycopy(arr, 0, newArr, 0, index);
        newArr[index] = value;
        System.arraycopy(arr, index, newArr, index + 1, arr.length - index);
        return newArr;
    }

    /**
     * Delete - menghapus elemen di posisi tertentu
     * Menggunakan System.arraycopy() untuk efisiensi pergeseran elemen
     * Time Complexity: O(n)
     * @return array baru hasil penghapusan
     */
    public int[] delete(int[] arr, int index) {
        int[] newArr = new int[arr.length - 1];
        System.arraycopy(arr, 0, newArr, 0, index);
        System.arraycopy(arr, index + 1, newArr, index, arr.length - index - 1);
        return newArr;
    }
}
