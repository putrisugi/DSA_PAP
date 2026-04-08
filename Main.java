package main;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Kelas Main
 * Entry point program. Menjalankan demonstrasi operasi Array dan ArrayList,
 * kemudian menampilkan perbandingan waktu eksekusi keduanya.
 */
public class Main {

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
        Comparison comparison = new Comparison();
        comparison.compare();
    }
}
