import java.awt.Color;
import java.util.*;

public class GraphAlgorithms{

  /* 
   * To draw a list of integers int_list (of type List<Integer)
   * to the canvas, call drawSequence(int_list, writer).
   *
   * The index of each integer in the list will be
   * plotted along the x-axis; the integer value itself
   * is plotted on the y-axis.
   *                                                      */

/*
 * Desc:  Merge Sort Algorithm. Algorithm takes in a list of integers and recursively breaks it
 *        down into its individual elements. The algorithm then rebuilds the list by adding
 *        the elements back in order.
 * Helper Methods:  split(List<Integer> S) - takes in an integer list and splits it into two lists.
 *                  merge(List<Integer> list1, List<Integer> list2) - takes in two integer lists
 *                        and merges them into one sorted list.
 */
  public static List<Integer> MergeSort(List<Integer> S, PixelWriter writer) {
    if (S.size() < 2) {
      drawSequence(S, writer);
      return S;
    }

    List<List<Integer>> lists = split(S);
    List<Integer> list1 = MergeSort(lists.get(0), writer);
    List<Integer> list2 = MergeSort(lists.get(1), writer);
    List<Integer> sorted_S = merge(list1, list2);

    drawSequence(sorted_S, writer);
    return sorted_S;
  }

  private static List<List<Integer>> split(List<Integer> S) {
    List<List<Integer>> lists = new LinkedList<List<Integer>>();
    List<Integer> list1 = new LinkedList<Integer>();
    List<Integer> list2 = new LinkedList<Integer>();

    if (S.size() == 1) list1.add(S.get(0));
    else if (S.size() > 1) {
      int splitIndex = S.size()/2 -1;

      for (int i=0; i <= splitIndex; i++) { 
        list1.add(i, S.get(i)); 
      }
      for (int j=splitIndex+1; j < S.size(); j++) { 
        list2.add(j-(splitIndex+1), S.get(j)); 
      }
    }

    lists.add(0,list1);
    lists.add(1,list2);

    return lists;
  }

  private static List<Integer> merge(List<Integer> list1, List<Integer> list2) {
    int i=0 , j = 0;
    List<Integer> sorted_list = new LinkedList<Integer>();
    while (i < list1.size() && j < list2.size()) {
      if (list1.get(i) <= list2.get(j)) {
        sorted_list.add(i+j, list1.get(i));
        i++;
      } else {
        sorted_list.add(i+j, list2.get(j));
        j++;
      }
    }

    while (i < list1.size()) {
      sorted_list.add(i+j, list1.get(i));
      i++;
    }

    while (j < list2.size()) {
      sorted_list.add(i+j, list2.get(j));
      j++;
    }

    return sorted_list;
  }

/*
 * Desc:  Quick Sort Algorithm. Algorithm takes in a list of integers and recursively breaks it
 *        down into a pivot value and two lists of integers lower and higher than the pivot. The
 *        algorithm then rebuilds the main list by merging the lower elements, pivot, and higher
 *        elements back together.
 * Helper Methods:  quick_split(List<Integer> S, int pivot) - takes in an integer list and the pivot
 *                      integer used to split the list into two.
 *                  quick_merge(List<Integer> list1, List<Integer> List2, int pivot) - Takes in the 
 *                      two sorted integer lists and the pivot point and merges them into one sorted list.
 */
  public static List<Integer> QuickSort(List<Integer> S, PixelWriter writer) {
    if (S.size() < 2) {
      drawSequence(S, writer);
      return S;
    }

    int pivot = S.remove(new Random().nextInt(S.size()));

    List<List<Integer>> lists = quick_split(S, pivot);
    List<Integer> list1 = QuickSort(lists.get(0), writer);
    List<Integer> list2 = QuickSort(lists.get(1), writer);
    List<Integer> sorted_S = quick_merge(list1, list2, pivot);

    drawSequence(sorted_S, writer);
    return sorted_S;
  }

  private static List<List<Integer>> quick_split(List<Integer> S, int pivot) {
    List<List<Integer>> lists = new LinkedList<List<Integer>>();
    List<Integer> list1 = new LinkedList<Integer>();
    List<Integer> list2 = new LinkedList<Integer>();

    for (int i=0; i < S.size(); i++) {
      if (S.get(i) < pivot) list1.add(S.get(i));
      else list2.add(S.get(i));
    }

    lists.add(list1);
    lists.add(list2);

    return lists;
  }

  private static List<Integer> quick_merge(List<Integer> list1, List<Integer> List2, int pivot) {
    List<Integer> sorted_list = new LinkedList<Integer>();

    sorted_list.addAll(list1);
    sorted_list.add(pivot);
    sorted_list.addAll(List2);

    return sorted_list;
  }

/*
 * Desc:  Insertion Sort Algorithm. The algorithm takes in a list of integers and sorts the
 *        elements by taking the element at index 'i' and sorting it with the elements that
 *        preceed it in the list.
 * Helper Methods:  InsertionSort_swap(List<Integer> intList, int index, int newInt) - takes in an
 *                        integer list, the current index i, and the next element to be sorted. The
 *                        method then finds the appropriate index to insert the next element.
 */
  public static List<Integer> InsertionSort(List<Integer> S, PixelWriter writer) {
    for (int i=0; i<S.size()-1; i++) { 
      S = InsertionSort_swap(S, i, S.remove(i+1)); 
      drawSequence(S, writer);
    }
    return S;
  }

  private static List<Integer> InsertionSort_swap(List<Integer> intList, int index, int newInt) {
    while (index >= 0 && intList.get(index) > newInt) { index--; }
    intList.add(index+1,newInt);
    return intList;
  }

/*
 * Desc:  Radix Sort Algorithm.
 */
  public static List<Integer> RadixSort(List<Integer> S, PixelWriter writer) {
    List[] buckets = new LinkedList[10];
    for (int i=0; i<10; i++) buckets[i] = new LinkedList<Integer>();

    
    return S;
  }

  private static List<Integer> bucket(List<Integer> S, int buckets, int mod) {
    for (int x : S)
    {

    }
  }

//=============================================================================================
//=============================================================================================

  /* DO NOT CHANGE THIS METHOD */
  private static void drawSequence(List<Integer> sequence, PixelWriter writer) {
    for (Integer curr : sequence) {
      for (int j=0; j<sequence.size(); j++) {
        Color c = writer.getColor(j, curr);
        if (c.equals(Color.BLACK))
          writer.setPixel(j, curr, Color.WHITE);
      }
      int x = sequence.indexOf(curr);
      if (!writer.getColor(x, curr).equals(Color.BLACK)) {
        writer.setPixel(sequence.indexOf(curr), curr, Color.BLACK);
      }
    }
  } 


  /* THE FOLLOWING METHODS WILL NOT BE MARKED;
   * YOU MAY IMPLEMENT THEM OPTIONALLY
   */

	/* FloodFillDFS(v, writer, fillColour)
	   Traverse the component the vertex v using DFS and set the colour 
	   of the pixels corresponding to all vertices encountered during the 
	   traversal to fillColour.
	   
	   To change the colour of a pixel at position (x,y) in the image to a 
	   colour c, use
			writer.setPixel(x,y,c);
	*/
	public static void FloodFillDFS(PixelVertex v, PixelWriter writer, Color fillColour){
	}
	
	/* FloodFillBFS(v, writer, fillColour)
	   Traverse the component the vertex v using BFS and set the colour 
	   of the pixels corresponding to all vertices encountered during the 
	   traversal to fillColour.
	   
	   To change the colour of a pixel at position (x,y) in the image to a 
	   colour c, use
			writer.setPixel(x,y,c);
	*/
	public static void FloodFillBFS(PixelVertex v, PixelWriter writer, Color fillColour){
	}
	
}
