//need list of elements, and graph for dependencys
public class Topo {
	//max num of relations for c option +1
  private final int longest=19;
	// verticy list
  private Vtx vList[];
	//graph of adjacents
  private int graph[][];
	//number of vertecys
  private int num;
	//sorted array
  private char Sort[];
  //array pos
  private int num1[];
  private int num2[];

  public Topo() {
    vList=new Vtx[longest];
    graph=new int[longest][longest];
    num1=new int[longest];
    num2=new int[longest];
    num=0;
    for (int i=0;i<longest;i++)
    for (int j=0;j<longest;j++)
      graph[i][j]=0;
      //sorted labels
    Sort = new char[longest];
  }

  class Vtx {
      public char name;

      public Vtx(char n) {
        name=n;
      }
}
  //sort
 public void topo() {

	  //origninal num of verticies
    int OGV=num;

    while(num>0)
    {
      // get a vertex with no children
      int curV=edgefinder();
      //loop condition
      if(curV==-1)
      {
        printloop();
        return;
      }

      // insert vertex at end of array
      Sort[num-1]=vList[curV].name;
		//delete the vertex
      deleteV(curV);
    }

    // print order after vertexs gone
    System.out.print("Sorted Order:");
    for(int j=0;j<OGV;j++){
      System.out.print(Sort[j]);
  }
    System.out.println("");
  }
	//loop encountered
	public int printloop() {
		System.out.println("Loop encountered. Sort not preformed on following values:");
		 for(int j=0;j<longest-1;j++) {
		      System.out.print(num1[j]+1);
		      System.out.print(",");
		      System.out.print(num2[j]+1);
		      System.out.println(" ");
		  }
    return -1;
}
	//add vertex
  public void addV(char y) {
    vList[num++]=new Vtx(y);
  }
	//add dependency
	int t=0;
	int p=0;
  public void addD(int first, int last) {
    graph[first][last] = 1;
	num1[t]=first;
	num2[p]=last;
	t++;
	p++;
  }
	//print vertex name
  public void PrintV(int v) {
    System.out.print(vList[v].name);
  }

	// find edges
  public int edgefinder() {
	  //looking for edge to row & col
    boolean edge;

    for (int row=0;row<num;row++) {
      edge=false;
      //traverse and check for edge
      for (int col=0;col<num;col++) {
        if (graph[row][col]>0)
        {
			//has child
          edge=true;
          break;
        }
      }
      //no edge, no child
      if(!edge)
        return row;
    }
    //no vertex
    return -1;
  }

	//delete
  public void deleteV(int delVert) {
	  //if not last, remove
    if (delVert!=num-1)
    {
      for (int j=delVert;j<num-1;j++)
        vList[j] = vList[j+1];

      for (int row=delVert;row<num-1;row++)
        moveRow(row,num);

      for (int col=delVert;col<num-1;col++)
        moveCol(col,num-1);
    }
    //vertex KIA
    num--;
  }

	//switch rows
  private void moveRow(int row,int length) {
    for (int col=0;col<length;col++)
      graph[row][col]=graph[row+1][col];
  }

	//switch cols
  private void moveCol(int col,int length) {
    for (int row=0;row<length;row++)
      graph[row][col]=graph[row][col+1];
  }


  public static void main(String[] args) {
    Topo v=new Topo();
	//vertex name
    v.addV('1');
    v.addV('2');
    v.addV('3');
    v.addV('4');
    v.addV('5');
    v.addV('6');
    v.addV('7');
	v.addV('8');
	v.addV('9');
	//dependencys
	//must be -1 of original value
	//since names start at value 0
/*
	//No loop condition data
    v.addD(0,1);
    v.addD(0,2);
    v.addD(3,0);
    v.addD(2,7);
    v.addD(7,1);
    v.addD(3,1);
	v.addD(3,4);
	v.addD(5,3);
	v.addD(4,6);
    v.addD(1,6);
    v.addD(8,7);
    v.addD(8,5);
    v.addD(1,6);
    v.addD(3,1);
    v.addD(8,7);
*/
    // loop data

    v.addD(0,1);
    v.addD(0,2);
    v.addD(1,2);
	v.addD(3,0);
    v.addD(2,7);
    v.addD(7,8);
	v.addD(7,1);
	v.addD(3,1);
    v.addD(3,4);
    v.addD(5,3);
	v.addD(4,6);
	v.addD(1,6);
	v.addD(6,8);
	v.addD(8,7);
	v.addD(8,5);
	v.addD(1,6);
	v.addD(3,1);
    v.addD(8,7);

	//start
    v.topo();
  }
}
