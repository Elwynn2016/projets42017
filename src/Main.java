import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
/*
		int l,c;
		int l2 = 2,c2 = 2;

		//Euclide.euclideBezout(4, 7);

		Scanner scan=new Scanner(System.in);

		do {
			System.out.println("Nombre de lignes :");
			l=scan.nextInt();
			System.out.println("Nombre de colonnes :");
			c=scan.nextInt();
		} while(l<1 || c<1);
		
		//scan.close();
		Matrice matrice = new Matrice(l,c);
		matrice.remplirMatrice();

		System.out.println("Matrice 1: \n");
		matrice.parcourirMatrice();
		
		//System.out.println(matrice.toString());
		//scan.close();
		scan = new Scanner(System.in);
		//scan.nextLine();
		
		do {
			System.out.println("Nombre de lignes :");
			l2=scan.nextInt();
			System.out.println("Nombre de colonnes :");
			c2=scan.nextInt();
		} while(l2<1 || c2<1);
		
		Matrice matrice2 = new Matrice(l2,c2);
		matrice2.remplirMatrice();

		System.out.println("Matrice 2:");
		matrice2.parcourirMatrice();

		//System.out.println(matrice2.toString());    
		Matrice matrice3 = new Matrice(0,0);
		matrice3 = matrice.multiplier(matrice2);
		System.out.println("\n M2 * M1");
		matrice3.parcourirMatrice();
		matrice = matrice.enflerMatrice(matrice2);
		System.out.println("\n M1 enflée");
		matrice.parcourirMatrice();


*/
		Matrice matrice = new Matrice(3,3);
		matrice.remplirMatrice();
		System.out.println("Matrice");
		matrice.parcourirMatrice();
		
		Matrice m = new Matrice(matrice);
		System.out.println("M avant");
		m.parcourirMatrice();
		m.toMatriceVide();
		System.out.println("M vidée");
		m.parcourirMatrice();
		
		Matrice m2 = new Matrice(matrice);
		System.out.println("M2 avant");
		m2.parcourirMatrice();
		m.toMatriceId();
		System.out.println("M2 Id");
		m.parcourirMatrice();
		
		
		
		
		//si taille m1<m2   on fait enfler matrice avant de la multiplier

		/*
		 * Test sur le changement de signe d'une ligne
		 */
		System.out.println("Opposition d'une ligne");
		int b = matrice.getLigneMatrice();
		matrice.opposeLigne(b);

		/*
		 * Fin du test
		 */

		/*
		 * Test transposition de ligne 
		 */
		matrice.parcourirMatrice();
		System.out.println("Quelle ligne voulez vous échanger avec la premiere ");
		int i;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		do{
			i = sc.nextInt();
		}while(i < 2 || i > matrice.getLigne());
		
		
		matrice.transposeLigne(i);
		matrice.parcourirMatrice();

		/*
		 * Fin du Test
		 */
	}
}
