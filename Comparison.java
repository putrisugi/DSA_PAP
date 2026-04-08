package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

// ============================================================
// KELAS 1: ArrayOperations
// Operasi dasar pada Array: traversal, search, insert, delete
// ============================================================
class ArrayOperations {

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
            if (arr[mid] == target)      return mid;
            else if (arr[mid] < target)  left = mid + 1;
            else                         right = mid - 1;
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

// ============================================================
// KELAS 2: ArrayListOperations
// Operasi dasar pada ArrayList: add, remove, search, sort
// ============================================================
class ArrayListOperations {

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

// ============================================================
// KELAS 3: PerformanceTest
// Membandingkan waktu eksekusi Array vs ArrayList (1000 elemen)
// ============================================================
class PerformanceTest {

    private static final int DATA_SIZE = 1000;

    /**
     * Menjalankan benchmark dan menampilkan hasil dalam bentuk tabel
     * Menggunakan System.nanoTime() untuk presisi pengukuran waktu
     */
    public void compare() {
        // Persiapan data uji
        int[] array = new int[DATA_SIZE];
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < DATA_SIZE; i++) {
            array[i] = i + 1;
            arrayList.add(i + 1);
        }

        ArrayOperations    arrOps  = new ArrayOperations();
        ArrayListOperations listOps = new ArrayListOperations();

        int target      = 500;
        int insertIndex = 250;
        int insertValue = 9999;

        long start, end;
        double arrayTime, listTime;

        System.out.println("\n" + "=".repeat(62));
        System.out.println("    PERBANDINGAN WAKTU EKSEKUSI: Array vs ArrayList");
        System.out.printf ("    Ukuran Data: %d elemen%n", DATA_SIZE);
        System.out.println("=".repeat(62));
        System.out.printf("%-22s %-18s %-18s%n", "Operasi", "Array (ms)", "ArrayList (ms)");
        System.out.println("-".repeat(62));

        // --- Traversal ---
        start = System.nanoTime();
        for (int i = 0; i < array.length; i++) { }
        end = System.nanoTime();
        arrayTime = (end - start) / 1_000_000.0;

        start = System.nanoTime();
        for (int i = 0; i < arrayList.size(); i++) { }
        end = System.nanoTime();
        listTime = (end - start) / 1_000_000.0;

        System.out.printf("%-22s %-18.4f %-18.4f%n", "Traversal", arrayTime, listTime);

        // --- Linear Search ---
        start = System.nanoTime();
        arrOps.linearSearch(array, target);
        end = System.nanoTime();
        arrayTime = (end - start) / 1_000_000.0;

        start = System.nanoTime();
        listOps.search(arrayList, target);
        end = System.nanoTime();
        listTime = (end - start) / 1_000_000.0;

        System.out.printf("%-22s %-18.4f %-18.4f%n", "Linear Search", arrayTime, listTime);

        // --- Binary Search ---
        start = System.nanoTime();
        arrOps.binarySearch(array, target);
        end = System.nanoTime();
        arrayTime = (end - start) / 1_000_000.0;

        int[] sorted = arrayList.stream().mapToInt(Integer::intValue).toArray();
        Arrays.sort(sorted);
        start = System.nanoTime();
        Arrays.binarySearch(sorted, target);
        end = System.nanoTime();
        listTime = (end - start) / 1_000_000.0;

        System.out.printf("%-22s %-18.4f %-18.4f%n", "Binary Search", arrayTime, listTime);

        // --- Insert ---
        start = System.nanoTime();
        int[] insertedArr = arrOps.insert(array, insertIndex, insertValue);
        end = System.nanoTime();
        arrayTime = (end - start) / 1_000_000.0;

        start = System.nanoTime();
        listOps.add(arrayList, insertIndex, insertValue);
        end = System.nanoTime();
        listTime = (end - start) / 1_000_000.0;

        System.out.printf("%-22s %-18.4f %-18.4f%n", "Insert", arrayTime, listTime);

        // --- Delete ---
        start = System.nanoTime();
        arrOps.delete(insertedArr, insertIndex);
        end = System.nanoTime();
        arrayTime = (end - start) / 1_000_000.0;

        start = System.nanoTime();
        listOps.remove(arrayList, insertIndex);
        end = System.nanoTime();
        listTime = (end - start) / 1_000_000.0;

        System.out.printf("%-22s %-18.4f %-18.4f%n", "Delete", arrayTime, listTime);

        System.out.println("=".repeat(62));
        System.out.println("Keterangan: Semakin kecil nilai = semakin cepat.");
    }
}

// ============================================================
// MAIN - Entry point program
// ============================================================
public class Comparison {

    public static void main(String[] args) {

        ArrayOperations     arrOps  = new ArrayOperations();
        ArrayListOperations listOps = new ArrayListOperations();

        // Data awal
        int[] array = {10, 20, 30, 40, 50};
        ArrayList<Integer> arrayList = new ArrayList<>(Arrays.asList(10, 20, 30, 40, 50));

        // --------------------------------------------------------
        // 1. TRAVERSAL
        // --------------------------------------------------------
        arrOps.traversal(array);
        listOps.traversal(arrayList);

        // --------------------------------------------------------
        // 2. PENCARIAN (Linear Search)
        // --------------------------------------------------------
        int target = 30;
        int idxArr  = arrOps.linearSearch(array, target);
        int idxList = listOps.search(arrayList, target);
        System.out.println("\nPencarian " + target + " dalam Array     : " +
            (idxArr  >= 0 ? "Ditemukan di indeks " + idxArr  : "Tidak ditemukan"));
        System.out.println("Pencarian " + target + " dalam ArrayList : " +
            (idxList >= 0 ? "Ditemukan di indeks " + idxList : "Tidak ditemukan"));

        // --------------------------------------------------------
        // 3. PENYISIPAN elemen 25 di indeks 2
        // --------------------------------------------------------
        int[] arrayInserted = arrOps.insert(array, 2, 25);
        listOps.add(arrayList, 2, 25);
        System.out.println("\nArray setelah penyisipan elemen 25     : " + Arrays.toString(arrayInserted));
        System.out.println("ArrayList setelah penyisipan elemen 25 : " + arrayList);

        // --------------------------------------------------------
        // 4. PENGHAPUSAN elemen di indeks 2
        // --------------------------------------------------------
        int[] arrayDeleted = arrOps.delete(arrayInserted, 2);
        listOps.remove(arrayList, 2);
        System.out.println("\nArray setelah penghapusan indeks 2     : " + Arrays.toString(arrayDeleted));
        System.out.println("ArrayList setelah penghapusan indeks 2 : " + arrayList);

        // --------------------------------------------------------
        // 5. PENGURUTAN ArrayList
        // --------------------------------------------------------
        ArrayList<Integer> unsorted = new ArrayList<>(Arrays.asList(40, 10, 50, 20, 30));
        System.out.println("\nArrayList sebelum sort : " + unsorted);
        listOps.sort(unsorted);
        System.out.println("ArrayList setelah sort : " + unsorted);

        // --------------------------------------------------------
        // 6. WAKTU EKSEKUSI per operasi (demo singkat)
        // --------------------------------------------------------
        long start, end;

        start = System.nanoTime();
        arrOps.linearSearch(array, target);
        end = System.nanoTime();
        System.out.printf("%nWaktu eksekusi pencarian pada Array     : %.4f ms%n",
            (end - start) / 1_000_000.0);

        start = System.nanoTime();
        listOps.search(arrayList, target);
        end = System.nanoTime();
        System.out.printf("Waktu eksekusi pencarian pada ArrayList : %.4f ms%n",
            (end - start) / 1_000_000.0);

        // --------------------------------------------------------
        // 7. PERBANDINGAN KINERJA (1000 elemen)
        // --------------------------------------------------------
        PerformanceTest comparison = new PerformanceTest();
        comparison.compare();
    }
}