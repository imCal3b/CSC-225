import java.util.*;

public class Group {

  private int numFrogs;
  private ArrayList<Frog> frogGroup;

  /*
   * Desc:  Group class constructor. Initializes numFrogs and
   *        frogGroup array.
   */
  public Group() {
    this.numFrogs = 0;
    this.frogGroup = new ArrayList<Frog>();
  }
  
  /*
   * Desc:  Adds a new frog element to the frogGroup ArrayList in 
   *        alphabetical order.
   * Inp:   Frog f - The new frog element to be input to the list.
   */
  public void addFrog(Frog f) {
    
    if (numFrogs == 0) {
      frogGroup.add(f);
      numFrogs++;

    } else {
      int index = frog_index_search(frogGroup, f, 0, numFrogs-1);
      frogGroup.add(index,f);
      numFrogs++;      
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
   * Ret:  [int] - The index for the element to be inserted.
   */
  private int frog_index_search(ArrayList<Frog> frogs, Frog f, int lowIndex, int highIndex) {
    if (lowIndex > highIndex) return lowIndex;

    int midIndex = (lowIndex + highIndex) / 2;

    if (f.compareTo(frogs.get(midIndex)) == 0) return midIndex;
    else if (f.compareTo(frogs.get(midIndex)) > 0) return frog_index_search(frogs,f,midIndex+1,highIndex);
    else return frog_index_search(frogs,f,lowIndex,midIndex-1);
  }

  /*
   * Desc:  Returns the number of frogs in the group.
   */
  public int size() {
    return numFrogs;
  }

  /*
   * Desc: Retruns the frog element at the specified index position.
   */
  public Frog get(int i) {
    return frogGroup.get(i);
  }

  /*
   * Desc:  Takes a frog group and returns a group array containing
   *        two new groups, each containing half the elements of
   *        the original frog group.
   * Ret:   [Group[]] - Returns an array containing the two new groups
   *        after the original group is split in two.
   */
  public Group[] halfGroups() {
    Group[] frogSplit = new Group[2];
    Group g1 = new Group();
    Group g2 = new Group();

    if (numFrogs == 1) {
      g1.frogGroup.add(this.frogGroup.get(0));

    } else if (numFrogs > 1) {
      int splitIndex = numFrogs/2 -1;

      for (int i=0;i <= splitIndex;i++) { 
        g1.frogGroup.add(i, this.frogGroup.get(i)); 
        g1.numFrogs++;
      }
      for (int j=splitIndex+1;j < numFrogs;j++) { 
        g2.frogGroup.add( j-(splitIndex+1), this.frogGroup.get(j)); 
        g2.numFrogs++;
      }
    }

    frogSplit[0] = g1;
    frogSplit[1] = g2;

    return frogSplit;
  }

  /*
   * Desc:  Returns a string representation of the frog group.
   */
  @Override
  public String toString() {
    return frogGroup.toString();
  }

  /*
   * Desc:  Takes in two frog groups and compares them to see if they are equal.
   *        Two groups are equal if they have the following properties:
   *          1) the sizes of g1 and g2 are equal
   *          2)     a) all elements are the same in the same positions
   *              OR b) g1 and g2 Groups are split; g1top equals g2bottom
   *              OR c) g1 and g2 Groups are split; g1bottom equals g2top
   * Inp:   Group g1 - The first frog group to be compared.
   *        Group g2 - The second frog group to be compared.
   * Ret:   [Boolean] - The truth value of whether or not the groups are equal.
   */
  public static boolean FrogEquals(Group g1, Group g2) {
    if (g1.size() == g2.size()) 
    {
      boolean eqChk = true;
      for (int i=0;i<g1.size();i++) { 
        if (g1.get(i).compareTo(g2.get(i)) != 0) eqChk = false; 
      }
      if (eqChk) return true;

      Group[] g1_split = g1.halfGroups();
      Group[] g2_split = g2.halfGroups();

      return FrogEquals(g1_split[0], g2_split[1]) || FrogEquals(g1_split[1], g2_split[0]);
    } 
    return false;
  }
}
