package main;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Kelas Comparison
 * Membandingkan waktu eksekusi operasi dasar antara Array dan ArrayList
 * menggunakan System.nanoTime() untuk presisi pengukuran waktu.
 */
public class Comparison {

    private static final int DATA_SIZE = 1000;

    /**
     * Menjalankan benchmark dan menampilkan hasil dalam bentuk tabel
     */
    public void compare() {
        // Persiapan data uji
        int[] array = new int[DATA_SIZE];
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < DATA_SIZE; i++) {
            array[i] = i + 1;
            arrayList.add(i + 1);
        }

        ArrayOperations     arrOps  = new ArrayOperations();
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
