import java.util.Scanner;


public class Main {

	 public static void main(String[] args) {
		 
		 int l;
		 int c;
		 int nb;

		 Scanner scan=new Scanner(System.in);
		 
		 System.out.println("Nombre de lignes :");
		 l=scan.nextInt();
		 System.out.println("Nombre de colonnes :");
		 c=scan.nextInt();
		 
		 nb=c*l;
		 
		 Matrice matrice = new Matrice(l,c);

		 matrice.remplirMatrice(nb);
		 
		 matrice.parcourirMatrice(l,c,nb);
		 
		 scan.close();
		 
		 
	 }
}
