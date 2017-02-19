import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
//import java.util.LinkedHashMap;
//import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;



public class Matrice {
	
	 private int ligne;
	 private int colonne;
	 private HashMap<Integer, Integer> hm;
	 int x=0;
	
	public Matrice(int ligne, int colonne) {
		this.ligne = ligne;
		this.colonne = colonne;
		this.hm = new HashMap<>();
	}
	
	
	int i=0;
	int j;
    int res=0;

	Scanner scan=new Scanner(System.in);

	public void remplirMatrice(int nb){
      
	      while(i<nb){
	    	  System.out.println("Veuillez saisir un nombre à insérer à la suite dans la matrice ("+(nb-i)+" restants) :");
	    	  res=scan.nextInt();
	    	  hm.put(i,res);
	    	  i++;
	      }
      
     }
       

	  public void parcourirMatrice(int l,int c, int nb){
		  System.out.println("Matrice : ");  
			Set<Entry<Integer, Integer>> setHm = hm.entrySet();
		      Iterator<Entry<Integer, Integer>> it = setHm.iterator();
		      i=1;
		      j=1;
		      String a="";
		      while(it.hasNext()){
		         Entry<Integer, Integer> e = it.next(); 
		         if(e.getValue()==0)
		        	 a+=" ";
		         else
		        	 a+=e.getValue();
	
		         while(j<c) { 
		        	 x=it.next().getValue();
		        	 if(x==0)
				       	a+="  "+"  ";
		        	 else
		        		 a+="  "+x;
		       	 	j++;
		         }
		         System.out.println(a);
		         j=1;
		         a="";	       	 
		         if(i==l){
		        	 System.out.println("\n");
		        	 i=1;
		         }
		         i++;
		      }
		  
	  }
  
	 
    public int getLigne() {
  		return ligne;
  	}
  	public void setLigne(int ligne) {
  		this.ligne = ligne;
  	}
  	public int getColonne() {
  		return colonne;
  	}
  	public void setColonne(int colonne) {
  		this.colonne = colonne;
  	}
  	
  	public static int [] euclideBezout(int a, int b) {
		int x = Math.abs(a);
		System.out.println("a="+x);
		// x=a si a>=0, -a sinon
		
		int y = Math.abs(b);
		System.out.println("b="+y+"\n");
		// y=b si b>=0, -b sinon
		
		int [] triplet = new int[3];
		
		// Dans l'itération précédente, x%y = 0
		if (y == 0) {
			triplet[0] = x;
			triplet[1] = 1;
			triplet[2] = 0;
			
			// triplet[PGCD(a,b),1,0]
			System.out.println("F) "+Arrays.toString(triplet));
			
		} else {
			triplet = euclideBezout(y,x%y);
			
			// triplet[PGCD(a,b),Un,Vn]
			System.out.println("A) "+Arrays.toString(triplet));
			
			int u = triplet[2];
			int v = triplet[1] - (x/y) * triplet[2];
			triplet[1] = u;
			triplet[2] = v;
			
			// triplet[PGCD(a,b),Un+1,Vn+1], soit les u et v des lignes supérieures
			System.out.println("B) "+Arrays.toString(triplet)); 
			
		}
		
		// On ommet les signes tout au long de l'algorithme,
		// et on les replace à la fin.
		// Cette opération ne sera effectuée au plus qu'une seule fois.
		if(a<0) triplet[1] = -triplet[1];
		if(b<0) triplet[2] = -triplet[2];
		
		// Resultat final, sous la forme [d,u,v]
		// Avec	d = PGCD(a,b)
		//		u = +/- Un+1, selon le signe de a
		//		v = +/- Un+1, selon le signe de b
		// Note: le PGCD peut être récupéré à n'importe quelle itération
		System.out.println("C) "+Arrays.toString(triplet)+"\n");

		return triplet;
		
	}

}