package expressoes;



public class ExpBinTree{
	private ExpNode root;

  public ExpBinTree(){
		this.root = null;
	}

  public void setRoot(ExpNode root){
    this.root = root;
  }

  public ExpNode getRoot(){
    return root;
  }

  public boolean isEmpty() {
    return size() == 0;
  }

  public int size(){
    return size(root);
  }

  private int size(ExpNode x) {
    if (x == null)
			return 0;
    else
			return x.size();
  }

  public int height() {
    return height(root);
  }

  private int height(ExpNode x) {
    if (x == null)
      return -1;
    return 1 + Math.max(height(x.getLeft()), height(x.getRight()));
  }

  public int getMaxWidth(ExpNode node){
    int maxWidth = 0;
    int width;
    int h = height(node);
    int i;

    //Get width de cada nivel e compara progressivamente com o width maximo ate ao momento
    //
    for (i = 1; i <= h; i++)
    {
        width = getWidth(node, i);
        if (width > maxWidth)
            maxWidth = width;
    }

    return maxWidth;
  }

  // retorna recursivamente a Width de um dado nivel da arvore
  public int getWidth(ExpNode node, int level){
    if (node == null)
        return 0;

    if (level == 1)
        return 1;
    else if (level > 1)
        return getWidth(node.getLeft(), level - 1) + getWidth(node.getRight(), level - 1);
    return 0;
  }


  //GraphDraw
	// recebe o nó a adicionar ao graphdraw
  private void addTree(ExpNode x, int coordX, int coordY, GraphDraw f,int j,int nivel,int larg){
    if(x!=null){

			//verifica se o nó é uma folha
      if(x.isLeaf()){
          f.addNode(""+x.getValue(), coordX,coordY);
      }
      else{
			//se não for uma folha é um operador
          f.addNode(""+x.getOp(), coordX,coordY);
      }

      int i=f.nodesSize()-1;
      if(j!=-1){
        f.addEdge(j,i);
      }
      int n = (int)Math.pow(2,nivel);
      int dist=larg/(2*n);
      if(x.getLeft() != null)
        addTree(x.getLeft(),coordX-dist,coordY+50,f,i,nivel+1,larg);

      if (x.getRight() != null)
        addTree(x.getRight() ,coordX+dist ,coordY+50,f,i,nivel+1,larg);

    }

  }

  //GraphDraw
  public void draw(String s){
    GraphDraw frame = new GraphDraw(s);
    int h = height();
    int d = 30; // De maneira a ser visivel
    int nos_nivel_h=(int) Math.pow(2,h+1);

    int larguraFrame=d*(nos_nivel_h +1);
    int alturaFrame=70*(h+1);
    frame.setSize(larguraFrame,alturaFrame);

    frame.setVisible(true);
    if (!isEmpty())
      addTree(root, larguraFrame/2,50,frame,-1,1,larguraFrame);
    else
      frame.setSize(50,150);
  }

}
