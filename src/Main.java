import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		int l;
		int c;

		//Euclide.euclideBezout(4, 7);

		Scanner scan=new Scanner(System.in);

		do {
			System.out.println("Nombre de lignes :");
			l=scan.nextInt();
			System.out.println("Nombre de colonnes :");
			c=scan.nextInt();
		} while(l<1 ||c<1);
		
		Matrice matrice = new Matrice(l,c);

		matrice.remplirMatrice();

		System.out.println("Matrice:");
		matrice.parcourirMatrice();
		
		System.out.println(matrice.toString());

		scan.close();


	}
}
