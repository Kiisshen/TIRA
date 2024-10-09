package oy.interact.tira.student;

import java.util.Comparator;

public class Algorithms {

   private Algorithms() {
      // nada
   }

   ///////////////////////////////////////////
   // Insertion Sort for the whole array
   ///////////////////////////////////////////

   public static <T extends Comparable<T>> void insertionSort(T[] array) {
      insertionSort(array, 0, array.length);
   }

   ///////////////////////////////////////////
   // Insertion Sort for a slice of the array
   ///////////////////////////////////////////

   public static <T extends Comparable<T>> void insertionSort(T[] array, int fromIndex, int toIndex) {
      for( int i = fromIndex; i < toIndex; i++){
         //Save the current value to be moved to sorted position.
         T c = array[i];
         //Take starting point of sort to compare current value to the values to left of it.
         int j = i;
         //Move elements to right untill we find element that is smaller than current value.
         while( j > fromIndex && c != null && c.compareTo( array[j-1]) < 0 ){
            array[j] = array[j-1];
            j--;
         }
         //Set the current value to its right sorted spot.
         array[j] = c;
      }
   }

   //////////////////////////////////////////////////////////
   // Insertion Sort for the whole array using a Comparator
   //////////////////////////////////////////////////////////

   public static <T> void insertionSort(T[] array, Comparator<T> comparator) {      
      insertionSort(array, 0, array.length, comparator);
   }

   ////////////////////////////////////////////////////////////
   // Insertion Sort for slice of the array using a Comparator
   ////////////////////////////////////////////////////////////

   public static <T> void insertionSort(T[] array, int fromIndex, int toIndex, Comparator<T> comparator) {
      for (int i = fromIndex; i < toIndex; i++) {
        T c = array[i];
        int j = i;
        while (j > fromIndex && c != null && comparator.compare(c, array[j - 1]) < 0) {
            array[j] = array[j - 1];
            j--;
        }
        array[j] = c;
    }
   }

   ///////////////////////////////////////////
   // Reversing an array
   ///////////////////////////////////////////

   public static <T> void reverse(T[] array) {
      reverse(array, 0, array.length);
   }

   ///////////////////////////////////////////
   // Reversing a slice of an array
   ///////////////////////////////////////////

   public static <T> void reverse(T[] array, int fromIndex, int toIndex) {
      // Take end and start points.
      int startIndx = fromIndex;
      int lastIndx = toIndex-1;
      // Swap first and last untill startpoint passes or reaches endpoint.
      while( startIndx <= lastIndx){
         // Save first.
         T dummy = array[startIndx];
         // Swap.
         array[startIndx] = array[lastIndx];
         array[lastIndx] = dummy;
         // Move increment first and decrement last.
         startIndx++;
         lastIndx--;
      }
   }


   ///////////////////////////////////////////
   // Recursive binary search bw indices
   ///////////////////////////////////////////

   public enum BSearchImplementation {
      RECURSIVE,
      ITERATIVE
   }

   public static <T extends Comparable<T>> int binarySearch(T aValue, T[] fromArray, int fromIndex, int toIndex) {
      return binarySearchRecursive(aValue, fromArray, fromIndex, toIndex - 1);
   }

   public static <T extends Comparable<T>> int binarySearch(T aValue, T[] fromArray, int fromIndex, int toIndex, BSearchImplementation impl) {
      if (impl == BSearchImplementation.RECURSIVE) {
         return binarySearchRecursive(aValue, fromArray, fromIndex, toIndex - 1);
      }
      else if(impl == BSearchImplementation.ITERATIVE){
         return binarySearchIterative(aValue, fromArray, fromIndex, toIndex - 1, Comparator.naturalOrder());
      }
      // TODO: IF implementing iterative binary search, call that here.
      return -1;
   }

   public static <T extends Comparable<T>> int binarySearchRecursive(T aValue, T[] fromArray, int fromIndex, int toIndex) {
      if (fromIndex > toIndex) {
         return -1;
      }
     
     int mid = (fromIndex + toIndex) / 2;
     
      if(fromArray[mid].compareTo(aValue) == 0) {
         return mid;
      } 
      else if(fromArray[mid].compareTo(aValue) < 0) {
         return binarySearchRecursive(aValue, fromArray, mid + 1, toIndex);
      } 
      else{
         return binarySearchRecursive(aValue, fromArray, fromIndex, mid - 1);
      }
   }

   ///////////////////////////////////////////
   // Binary search using a Comparator
   ///////////////////////////////////////////

   public static <T> int binarySearch(T aValue, T[] fromArray, int fromIndex, int toIndex, Comparator<T> comparator) {
      return binarySearchRecursive(aValue, fromArray, fromIndex, toIndex - 1, comparator);
   }

   public static <T> int binarySearch(T aValue, T[] fromArray, int fromIndex, int toIndex, Comparator<T> comparator, BSearchImplementation impl) {
      if (impl == BSearchImplementation.RECURSIVE) {
         return binarySearchRecursive(aValue, fromArray, fromIndex, toIndex - 1, comparator);
      }
      else if(impl == BSearchImplementation.ITERATIVE){
         return binarySearchIterative(aValue, fromArray, fromIndex, toIndex - 1, comparator);
      }
      // TODO: IF implementing iterative binary search, call that here.
      return -1;
   }

   public static <T> int binarySearchRecursive(T aValue, T[] fromArray, int fromIndex, int toIndex, Comparator<T> comparator) {
      if (fromIndex > toIndex) {
         return -1;
      }

      int mid = (fromIndex + toIndex) / 2;
      
      if(comparator.compare(aValue, fromArray[mid]) == 0) {
         return mid;
      } 
      else if(comparator.compare(aValue, fromArray[mid]) < 0) {
         return binarySearchRecursive(aValue, fromArray, fromIndex, mid - 1, comparator);
      } 
      else{
         return binarySearchRecursive(aValue, fromArray, mid + 1, toIndex, comparator);
      }
   }

   public static <T> int binarySearchIterative(T aValue, T[] fromArray, int fromIndex, int toIndex, Comparator<T> comparator) {
      int L = fromIndex;
      int R = toIndex;
      while (L <= R) {
          int m = L + (R - L) / 2;
          int cmp = comparator.compare(aValue, fromArray[m]);
  
          if (cmp < 0) {
              R = m - 1;
          } else if (cmp > 0) {
              L = m + 1;
          } else {
              return m;
          }
      }
      return -1;
   }

   public enum FastSortAlgorithm {
      QUICKSORT,
      HEAPSORT,
      MERGESORT
   }

   public static <E> void fastSort(E[] array, Comparator<E> comparator) {
      fastSort(array, 0, array.length, comparator, FastSortAlgorithm.QUICKSORT);
   }

   public static <E extends Comparable<E>> void fastSort(E[] array) {
      fastSort(array, 0, array.length, Comparator.naturalOrder(), FastSortAlgorithm.QUICKSORT);
   }

   public static <E> void fastSort(E[] array, int fromIndex, int toIndex, Comparator<E> comparator) {
      fastSort(array, fromIndex, toIndex, comparator, FastSortAlgorithm.QUICKSORT);
   }

   public static <E> void fastSort(E[] array, int fromIndex, int toIndex, Comparator<E> comparator, FastSortAlgorithm algorithm) {
      switch (algorithm) {
         case QUICKSORT:
            // TODO: Call your quicksort algorithm here.
            break;
         case HEAPSORT:
            // TODO: IF implementing heapsort, call your algorithm here.
            break;
         case MERGESORT:
            // TODO: IF implementing mergesort, call your algorithm here.
            break;
         default:
            break;
      }
   }

} // End of class Algorithms
