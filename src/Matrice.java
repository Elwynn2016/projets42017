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

	public Matrice(Matrice m) {
		this.setLigne(m.getLigne());
		this.setColonne(m.getColonne());
		this.setTab(m.getTab());
	}

	public Matrice(int[][] t) {
		this(t.length,t[0].length);
		this.setTab(t);
	}

	public void toMatriceId(){
		if(!estCarree()) {
			System.out.println("La matrice doit être carrée.");
			return;
		}
		this.toMatriceVide();
		for (int i=0; i<ligne; i++ ) {
			tab[i][i] = 1;
		}
	}

	public void toMatriceVide(){
		for (int i=0; i<this.getLigne(); i++ ){
			for (int j=0; j<this.getColonne(); j++) {
				tab[i][j] = 0;
			}
		}
	}

	public boolean estCarree(){
		return this.getLigne() == this.getColonne();
	}

	public void remplirMatrice(){
		@SuppressWarnings("resource")
		Scanner scan=new Scanner(System.in);

		for (int i=0; i<ligne; i++ ){
			for (int j=0; j<colonne; j++) {
				int rest = ligne*colonne - (i*colonne + j);
				System.out.println("Veuillez saisir un nombre à insérer à la suite dans la matrice ("+rest+" restants) :");
				tab[i][j]=scan.nextInt();
			}
		}
		//scan.close();
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

	public int[][] getTab(){
		return this.tab;
	}

	public void setTab(int t[][]){
		this.tab = new int[t.length][t[0].length];
		for (int i=0; i<t.length; i++ ){
			for (int j=0; j<t[0].length; j++) {
				this.tab[i][j] = t[i][j];
			}
		}
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


	// transpose la matrice 
	public Matrice transposerMatrice()
	{
		Matrice a = new Matrice(this.getColonne(), this.getLigne());
		int tmp = 0;

		for (int i=0; i<a.getLigne(); i++)
			for (int j=0; j<a.getColonne(); j++)
			{
				tmp = this.tab[j][i];
				a.tab[i][j]=tmp;
			}


		return a;
	}

	/**
	 * Utilise l'algorithme Rho(k,l,n) sur une matrice carrée
	 * @param k : k
	 * @param l : l
	 * @param n : taille de la matrice enflée
	 */
	public Matrice enflerMatrice(int k, int l, int n)
	{
		if(!this.estCarree() || this.getLigne()!=2) {
			System.out.println("Ce n'est pas une matrice 2x2");
			return this;
		}

		Matrice a = new Matrice(n, n); 

		a.toMatriceId();

		a.getTab()[k][k] = this.getTab()[0][0];
		a.getTab()[k][l] = this.getTab()[0][1];
		a.getTab()[l][k] = this.getTab()[1][0];
		a.getTab()[l][l] = this.getTab()[1][1];
		return a;
	}
	
	public Matrice multiplier(Matrice MA, Matrice MB)
	{
		int l,c;
		int value =0;
		if(MA.getColonne() != MB.getLigne()){
			System.out.println("Matrice de taille différente");
			return MA;
		}
		else{
			l = MA.getLigne();
			c = MB.getColonne();

			Matrice a = new Matrice(l, c);

			l= 0;
			for(int i =0; i< MA.getLigne(); i++){
				c=0;
				for(int k = 0; k<MB.getColonne(); k++){
					value =0;

					for(int j=0; j<MA.getColonne(); j++){
						value += MA.tab[i][j] * MB.tab[j][k];

					}
					a.tab[l][c] = value;
					c++;

				}
				l++;
			}


			return a;
		}
	}

	public Matrice multiplier(Matrice MB)
	{
		if(this.getColonne() != MB.getLigne()){
			System.out.println("Matrice de taille différente");
			return this;
		}

		int value = 0;

		Matrice A = new Matrice(this.getLigne(), MB.getColonne());

		for(int i =0; i< this.getLigne(); i++){
			for(int k = 0; k<MB.getColonne(); k++){
				value =0;
				for(int j=0; j<this.getColonne(); j++)
					value += this.tab[i][j] * MB.tab[j][k];	
				A.tab[i][k] = value;
			}
		}
		return A;
	}

	public int getLigneMatrice(int i){ 

		//Si la matrice n'a qu'une ligne pas besoin de choisir une ligne
		if(this.getLigne() == 1) return 1;

		//L'utilisateur  ne choisi pas entre la ligne 0 et n-1
		if(i<0) return 1;
		if(i>this.getLigne()) return this.getLigne()-1;

		return i;
	}

	public void opposeLigne(int A){
		int taille = this.getColonne();
		for(int i = 0 ; i < taille; i++){
			this.tab[A][i] = -this.tab[A][i];
		}
	}


	public void echangeLigne(int i){

		if(this.ligne == 1){
			System.out.println("Il n'y a qu'une seule ligne");
			return;
		}

		else if(i<0 || i > this.ligne-1){
			System.out.println("La ligne n'est pas dans la matrice");
			return;
		}

		int tmp;

		for(int j = 0; j < this.getColonne(); j++){
			tmp = this.tab[0][j];
			this.tab[0][j] = this.tab[i][j]; 
			this.tab[i][j] = tmp;
		}

	}

	public int[] multiplierValeurLigne(int ligne, int x){

		ligne = this.getLigneMatrice(ligne);
		int A[] = new int[this.getColonne()];
		
		for(int j = 0; j < this.getColonne(); j++){
			A[j] = tab[ligne][j] * x;
		}
		return A;
	}

	// Effectue L1 <- L1 + yL2
	public void operationLigne(int l1, int l2, int y){

		int a = this.getLigneMatrice(l1);
		int b = this.getLigneMatrice(l2);

		if(y == 0) return;

		int yLj[] = this.multiplierValeurLigne(b, y);

		for(int i =0; i < this.getColonne(); i++){
			this.tab[a][i] = this.tab[a][i] + yLj[i];
		}
	}

	/** TODO Reductions **/

	public Matrice reduction1(Matrice A){
		Matrice B = new Matrice (A);

		int n = A.getLigne();
		int p = A.getColonne();

		int i = 0;

		while(i<B.getLigne() && B.tab[i][0] == 0) i += 1;
		
		System.out.println("There");
		B.parcourirMatrice();
		if(i<n) {
			if(i>=1){
				B.echangeLigne(i);
			}
			
			if(B.tab[0][0]<0){
				B.opposeLigne(0);
			}
			

			for(int j=1; j<p;j++){
				int duv[]= euclideBezout(B.tab[0][0],B.tab[0][j]);
				//				System.out.println("b00:" + B.tab[0][0]);
				//				System.out.println("b0j:" + B.tab[0][j]);
				//				System.out.println("duv:" + duv[0]+" "+duv[1]+" "+duv[2]);
				if(duv[0]!=0){

					Matrice Q = new Matrice(2,2);
					Q.tab[0][0] = duv[1];
					Q.tab[0][1] = -B.tab[0][j]/duv[0];
					Q.tab[1][0] = duv[2];
					Q.tab[1][1] = B.tab[0][0]/duv[0];

					System.out.println("Q:");
					Q.parcourirMatrice();
					Q = Q.enflerMatrice(0,j,p);
					System.out.println("Q enflée:");
					Q.parcourirMatrice();
					System.out.println("B avant multiplication : ");
					B.parcourirMatrice();
					B = B.multiplier(Q);
					System.out.println("B Multipliée : ");
					B.parcourirMatrice();

				}
			}		
		}
		return B;
	}

	//Retourne true si B1,1 | C1(B)
	public boolean diviseColonne(int c){
		for(int i = 0; i<this.getLigne(); i++){
			if(this.tab[i][c]%this.tab[0][0]!=0)
				return false;
		}
		return true;
	}

	public Matrice reduction2(Matrice A){
		System.out.println("HEEEEEEEY");
		Matrice B = new Matrice (A);
		int n = A.getLigne();

		
		if(B.tab[0][0]!=0){
			//for(int j=0; j<B.getLigne();j++){
			//while(B.tab[0][j]!=0 && B.tab[0][0]%B.tab[0][j] != 0) {

			//B1 ne divise pas toute la colonne 1
			while(!B.diviseColonne(0)){
				int i=1;
				while(B.tab[i][0]% B.tab[0][0] == 0 ){
					System.out.println("POPOPOP "+ B.tab[i][0]);
					i = i+1;
				}
				System.out.println("OH "+B.tab[i][0]);
				System.out.println("i = "+i);
				int duv[]=euclideBezout(B.tab[0][0],B.tab[i][0]);
				Matrice P = new Matrice(2,2);
				P.tab[0][0] = duv[1];
				P.tab[0][1] = duv[2];
				P.tab[1][0] = -B.tab[i][0]/duv[0];
				P.tab[1][1] = B.tab[0][0]/duv[0];
				
				System.out.println("P :");
				P.parcourirMatrice();
				P = P.enflerMatrice(0,i,n);
				System.out.println("P enflée:");
				P.parcourirMatrice();
				System.out.println("Okjukhghyfgd");

				B = P.multiplier(B);
				System.out.println("B dans reduction 2 avant reduc 1  " );
				B.parcourirMatrice();
				B = reduction1(B);
				System.out.println("B dans reduction 2 après reduc 1  " );
				B.parcourirMatrice();
			}
			for(int i=1;i<n;i++){
				B.operationLigne(i,0,-B.tab[i][0]/B.tab[0][0]);
			}
			System.out.println("QDSLFJNDWJFDSKLMJFMSLDKFGJQLMSDFJQDSLMKFJQSDMLKFJQSLMFK");
			B.parcourirMatrice();
			

			//}
		}
		return B;
	}

	//Retourne true si B1,1 | B
	public boolean diviseB(){
		for(int i = 1; i < this.getLigne(); i++){
			for(int j = 1; j < this.getColonne(); j++){
				if(this.tab[i][j]%this.tab[0][0]!=0)
					return false;
			}
		}
		return true;
	}

	public boolean diviseLigne(int l){
		for(int j = 0; j<this.getColonne(); j++){
			if(this.tab[l][j]%this.tab[0][0]!=0)
				return false;
		}
		return true;
	}

	public Matrice reduction3(Matrice A){
		Matrice B = new Matrice(A); 

		//int n = A.getLigne();
		//int p = A.getColonne();

		if(B.tab[0][0] != 0){
			while(!B.diviseB()){
				int i = 1;
				while(B.diviseLigne(i)){
					i = i+1;
				}

				B.operationLigne(0,i,1);
				B = reduction1(B);
				
				//Fais une pause d'une seconde à chaque itération
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				System.out.println("PAUSE");
				B.parcourirMatrice();
				B = reduction2(B);
			}
		}
		return B;
	}


}