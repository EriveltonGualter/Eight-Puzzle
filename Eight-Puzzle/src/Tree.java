import java.util.Arrays;

public class Tree {
	private int l = 1000;//2500000;
	private int c = 19;
	private int id = 0;
	private int[][] node = new int[l][c];
	int[] mU, mD, mL, mR;
	Heuristica h = new Heuristica();
	
	Tree(){
		for (int i=0; i<l; i++){
			for (int ii=0; ii<c; ii++){
				node[i][ii] = -1;
				node[i][11] = -2;
				node[i][13] = -2;
				node[i][15] = -2;
			}
		}
	}
	
	public int insert(int[] data){
		int nE = nextEmpty();
		node[nE][9] = id;  
		id = id + 1;
		for(int i=0; i<data.length; i++)
		{
			node[nE][i] = data[i];
		}
		int sequence[] = {1,2,3,8,0,4,7,6,5};
		node[nE][18] = h.getManhattan(data, sequence);
		return (id-1);
	}
	
	private int nextEmpty(){
		int pos=0;
		for (int i=0; i<l; i++){
			if (node[i][9] == -1){
				pos = i;
				break;
			}
		}
		return pos;
	}
	
	// Busca em largura
	private int nextEmptyParent(){
		int pos=0;
		for (int i=0; i<l; i++){
			if (node[i][10] == -1 && node[i][12] == -1 && node[i][14] == -1 && node[i][16] == -1){
				pos = i;
				break;
			}
		}
		return pos;
	}
	
	public int[][] getNode(){
		return node;
	}
	
	public int[] moveUp(int[] data){
		int dU[] = data.clone();
		int blank = getBlank(data);
		if (blank > 2){
			dU[blank] = data[blank-3];
			dU[blank-3] = 0;
		}
		return dU;
	}
	
	public int[] moveDown(int[] data){
		int dD[] = data.clone();
		int blank = getBlank(data);
		if (blank < 6){
			dD[blank] = data[blank+3];
			dD[blank+3] = 0;
		}
		return dD;
	}	
	
	public int[] moveLeft(int[] data){
		int dL[] = data.clone();
		int blank = getBlank(data);
		if ( (blank % 3) != 0 ){
			dL[blank] = data[blank-1];
			dL[blank-1] = 0;
		}	
		return dL;
	}
	
	public int[] moveRight(int[] data){
		int dR[] = data.clone();
		int blank = getBlank(data);
		if ( (blank != 2) && (blank != 5) && (blank != 8)){
			dR[blank] = data[blank+1];
			dR[blank+1] = 0;
		}	
		return dR;
	}	
	
	public int getBlank(int[] data){
		int blank=0;
		for (int i=0; i<9; i++){
	    	if (data[i] == 0){
	    		blank = i;
	    	}
	    }		
		return blank;
	}
	
	public boolean nSuccess(int[] result){
		int[] sequence = {1,2,3,4,5,6,7,8,9};
		for (int i=0; i<9; i++) {
			if (result[i] != sequence[i])
				return false;
		}
		return true;
	}
	
	public void insertTree(int[] data){
		int[] p = data;
		int parent = insert(data);
		int k = l/4;
		int n=0;
		
		while (k>0)
		{			
			mU = moveUp(p);
			mD = moveDown(p);
			mL = moveLeft(p);
			mR = moveRight(p);
			int[] idFilho = {-1,-1,-1,-1};

			if(!Arrays.equals(mU, p) && node[parent][17]!=2) {
				idFilho[0] = insert(mU);
				node[parent][10] = idFilho[0];
				node[idFilho[0]][17] = 1;
			}
			else
				node[parent][10] = -1;
			if(!Arrays.equals(mD,p) && node[parent][17]!=1) {
				idFilho[1] = insert(mD);
				node[parent][12] = idFilho[1];
				node[idFilho[1]][17] = 2;
			}
			else
				node[parent][12] = -1;
			if(!Arrays.equals(mL,p) && node[parent][17]!=4) {
				idFilho[2] = insert(mL);
				node[parent][14] = idFilho[2];
				node[idFilho[2]][17] = 3;
			}
			else
				node[parent][14] = -1;
			if(!Arrays.equals(mR,p) && node[parent][17]!=3) {
				idFilho[3] = insert(mR);
				node[parent][16] = idFilho[3];
				node[idFilho[3]][17] = 4;
			}
			else
				node[parent][16] = -1;	
			
			System.arraycopy(node[h.bestHeuristica(idFilho, node)], 0, p, 0, 9);
			parent = h.bestHeuristica(idFilho, node);
			//System.arraycopy(node[nextEmptyParent()], 0, p, 0, 9);
			//parent = nextEmptyParent();
			k--;
			//System.out.printf("\n%d", k);
			if (nSuccess(node[parent])) {
				n++;
				System.out.printf("\n\n%d acertos\n", n);
			}
		}
		
	}
}
