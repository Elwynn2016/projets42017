import java.util.Arrays;

public class Euclide {

	public static int [] euclideBezout(int a, int b) {
		int x = Math.abs(a);
		System.out.println("a="+x);
		// x=a si a>=0, -a sinon
		
		int y = Math.abs(b);
		System.out.println("b="+y+"\n");
		// y=b si b>=0, -b sinon
		
		int [] triplet = new int[3];
		
		// Dans l'it�ration pr�c�dente, x%y = 0
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
			
			// triplet[PGCD(a,b),Un+1,Vn+1], soit les u et v des lignes sup�rieures
			System.out.println("B) "+Arrays.toString(triplet)); 
			
		}
		
		// On ommet les signes tout au long de l'algorithme,
		// et on les replace � la fin.
		// Cette op�ration ne sera effectu�e au plus qu'une seule fois.
		if(a<0) triplet[1] = -triplet[1];
		if(b<0) triplet[2] = -triplet[2];
		
		// Resultat final, sous la forme [d,u,v]
		// Avec	d = PGCD(a,b)
		//		u = +/- Un+1, selon le signe de a
		//		v = +/- Un+1, selon le signe de b
		// Note: le PGCD peut �tre r�cup�r� � n'importe quelle it�ration
		System.out.println("C) "+Arrays.toString(triplet)+"\n");

		return triplet;
		
	}
	
	
	
	public static void main(String[] args) {
		euclideBezout(-13,4);
	}

}
