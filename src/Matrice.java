import java.util.Arrays;
import java.util.Scanner;


public class Matrice {

	private int ligne;
	private int colonne;
	private int[][] tab;

	public Matrice(int ligne, int colonne) {
		this.ligne = ligne;
		this.colonne = colonne;
		this.tab = new int[ligne][colonne];
	}

	public void remplirMatrice(){
		Scanner scan=new Scanner(System.in);

		for (int i=0; i<ligne; i++ ){
			for (int j=0; j<colonne; j++) {
				int rest = ligne*colonne - (i*colonne + j);
				System.out.println("Veuillez saisir un nombre à insérer à la suite dans la matrice ("+rest+" restants) :");
				tab[i][j]=scan.nextInt();
			}
		}
		scan.close();
	}

	public void parcourirMatrice(){
		for (int i=0; i<ligne; i++) {
			for (int j=0; j<colonne; j++) {
				System.out.print(tab[i][j] + " ");
			}
			System.out.println();
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
		int y = Math.abs(b);
		int [] triplet = new int[3];

		if (y == 0) {
			triplet[0] = x;
			triplet[1] = 1;
			triplet[2] = 0;
		} else {
			triplet = euclideBezout(y,x%y);
			int u = triplet[2];
			int v = triplet[1] - (x/y) * triplet[2];
			triplet[1] = u;
			triplet[2] = v;
		}

		if(a<0) triplet[1] = -triplet[1];
		if(b<0) triplet[2] = -triplet[2];

		return triplet;

	}


}