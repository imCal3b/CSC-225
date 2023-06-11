import java.util.*;

public class Group {

  private int numFrogs;
  private ArrayList<Frog> frogGroup;

  public Group() {
    this.numFrogs = 0;
    this.frogGroup = new ArrayList<Frog>();
  }
  
  public void addFrog(Frog f) {
    
    if (numFrogs == 0) {
      frogGroup.add(f);
      numFrogs++;

    } else {
      System.out.println("numFrogs :: " + numFrogs);
      System.out.println(frogGroup.toString());
      System.out.println("--- new frog --- :" + f);
      int index = frog_index_search(frogGroup, f, 0, numFrogs);
      frogGroup.add(index,f);
      numFrogs++;

      System.out.println("\ninsert Frog (" + f.toString() + ") index :: " + index);
      System.out.println(frogGroup.toString() + "\n\n");
      
    }

  }

  /*
   * Desc:  An index searching algorithm that finds the appropriate index in a list of
   *        frog objects such that the list maintains its order when the new element
   *        is inserted.
   * Inp:   ArrayList<Frog> frogs - Takes in a list of frog objects.
   *        Frog f - The new frog object to be inserted to the list.
   *        int lowIndex - The low index bound to be searched.
   *        int highIndex - The high index bound to be searched.
   * Outp:  [int] - The index for the element to be inserted.
   */
  private int frog_index_search(ArrayList<Frog> frogs, Frog f, int lowIndex, int highIndex) {
    if (lowIndex == highIndex || lowIndex > highIndex) {
      //TODO: broken :( pls fix
      if (f.compareTo(frogs.get(lowIndex)) >= 0) return lowIndex+1;
      else return lowIndex;
      
    }

    int midIndex = (lowIndex + highIndex) / 2;
    System.out.println("low: " + lowIndex + " high: " + highIndex + " mid: " + midIndex);
    System.out.println("cmp [" + f +"," + frogs.get(midIndex) + "] : " + f.compareTo(frogs.get(midIndex)));

    if (f.compareTo(frogs.get(midIndex)) == 0) return midIndex;
    else if (f.compareTo(frogs.get(midIndex)) > 0) return frog_index_search(frogs,f,midIndex+1,highIndex);
    else return frog_index_search(frogs,f,lowIndex,midIndex-1);
  }

  public int size() {
    return numFrogs;
  }


  public Frog get(int i) {
    return frogGroup.get(i);
  }

  public Group[] halfGroups() {
    //TODO:
    Group[] frogSplit = {};
    return frogSplit;
  }

  @Override
  public String toString() {
    //TODO:
    return frogGroup.toString();
  }

  public static boolean FrogEquals(Group g1, Group g2) {
    //TODO:
    return false;
  }
}
