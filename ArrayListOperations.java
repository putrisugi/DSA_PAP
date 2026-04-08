package main;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Kelas ArrayListOperations
 * Berisi metode-metode untuk operasi dasar pada ArrayList:
 * traversal, add, remove, search, dan sort.
 */
public class ArrayListOperations {

    /**
     * Traversal - menampilkan seluruh isi ArrayList
     * Time Complexity: O(n)
     */
    public void traversal(ArrayList<Integer> list) {
        System.out.println("ArrayList Traversal: " + list);
    }

    /**
     * Add - menambahkan elemen di posisi tertentu
     * ArrayList menggeser elemen lainnya secara otomatis
     * Time Complexity: O(n)
     */
    public void add(ArrayList<Integer> list, int index, int value) {
        list.add(index, value);
    }

    /**
     * Remove - menghapus elemen di posisi tertentu
     * Time Complexity: O(n)
     */
    public void remove(ArrayList<Integer> list, int index) {
        list.remove(index);
    }

    /**
     * Search - mencari elemen menggunakan indexOf (linear search)
     * Time Complexity: O(n)
     * @return indeks jika ditemukan, -1 jika tidak
     */
    public int search(ArrayList<Integer> list, int target) {
        return list.indexOf(target);
    }

    /**
     * Sort - mengurutkan elemen secara ascending
     * Menggunakan Collections.sort() dengan algoritma TimSort
     * Time Complexity: O(n log n)
     */
    public void sort(ArrayList<Integer> list) {
        Collections.sort(list);
    }
}
