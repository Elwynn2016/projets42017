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
		this.ligne = m.getLigne();
		this.colonne = m.getColonne();
		this.tab = new int[ligne][colonne];
		for (int i=0; i<this.getLigne(); i++ ){
			for (int j=0; j<this.getColonne(); j++) {
				this.tab[i][j] = m.tab[i][j];
			}
		}
	}

	/*public Matrice(int[][] t) {
		if (t.length == 4) {
			this.ligne = 2;
			this.colonne = 2;
			this.tab = new int[ligne][colonne];
			for(int i=0; i<2; i++) {
				for(int j=0; j<2; j++) {
					this.tab[i][j] = t[i][j];
				}
			}
		}
	}*/

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
		this.tab = t;
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

	/*

	public Matrice enflerMatrice(Matrice m2)
	{ 
		Matrice a = new Matrice(this.getLigne(), this.getColonne());
		//int x = 0;

		if(this.getLigne()<m2.getLigne() || this.getColonne()<m2.getColonne()) {
			System.out.println("Matrice trop grande pour être enflée.");
			return m2;
		}
		else if(!estCarree() || !m2.estCarree()) {
			System.out.println("Les matrices doivent être carrée.");
			return m2;
		}

		int i,j;
		for ( i=0; i<m2.getLigne(); i++){
			for ( j=0; j<m2.getColonne(); j++){
				this.tab[i][j]=a.tab[i][j];
			}
		}

		for ( i=m2.getLigne(); i<this.getLigne(); i++){
			for ( j=m2.getColonne(); j<this.getColonne(); j++){
				a.tab[i][i]=1;
			}
		}
		return a;
	}
	 */
	/*
	public Matrice enflerMatrice(Matrice m2)
	{ 
		Matrice a = new Matrice(this.getLigne(), this.getColonne());
		//int x = 0;
		int i,j;

		for ( i=0; i<m2.getLigne(); i++){
			for ( j=0; j<m2.getColonne(); j++)
			{
				a.tab[i][j]=m2.tab[i][j];

			}
		}

		for ( i=m2.getLigne(); i<this.getLigne(); i++){
			for ( j=m2.getColonne(); j<this.getColonne(); j++)
			{
				a.tab[i][i]=1;
			}
		}

		return a;


	}
	 */
	/**
	 * TODO
	 * @param n : taille de la matrice enflée
	 */
	public Matrice enflerMatrice(int k, int l, int n)
	{
		if(!this.estCarree() || this.getLigne()!=2) {
			System.out.println("Ce n'est pas une matrice 2x2? ");
			return this;
		}

		Matrice a = new Matrice(n, n);
		k--;
		l--; 

		a.toMatriceId();

		a.getTab()[k][k] = this.getTab()[0][0];
		a.getTab()[k][l] = this.getTab()[0][1];
		a.getTab()[l][k] = this.getTab()[1][0];
		a.getTab()[l][l] = this.getTab()[1][1];
		return a;
	}

	// multiplication
	/*
	public Matrice multiplier(Matrice matrice){
		Matrice a = new Matrice(this.getLigne(), this.getColonne());
		int k,i,j;
		int value = 0;

		for (k=0; k<this.getColonne(); k++){
			for (i=0; i<this.getLigne(); i++){
				for (j=0; j<this.getColonne(); j++)
					value += this.tab[i][j]*matrice.tab[j][k];
				a.tab[i][k]=value;
				value = 0;
			}
		}
		return a;
	}
	 */
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

	public int getLigneMatrice(){         /** TODO Ligne en paraletre **/
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);

		/*	
		 * Si la matrice n'a qu'une ligne pas besoin de choisir une ligne
		 */

		int i=1;
		if(this.getLigne() != 1){

			/*
			 * affiche la matrice ainsi que le nombre de ligne entre 1 et n, donc l'utilisateur 
			 * ne choisi pas entre la ligne 0 et n-1
			 */

			System.out.println("Quelle ligne voulez vous recuperer" );
			this.parcourirMatrice();

			System.out.println("entre " + 1 + " et "+ this.getLigne() +"?");
			do{	
				i = sc.nextInt();
			}while(i < 1 || i > this.getLigne() );
		}
		i--;
		return i;
	}

	public void opposeLigne(int A){
		int taille = this.getColonne();
		for(int i = 0 ; i < taille; i++){
			this.tab[A][i] = -this.tab[A][i];
		}
	}


	/** EDIT: choix de la ligne par parametre **/
	public void transposeLigne(int i){
		if(this.ligne <= 1){
			System.out.println("Il n'y a qu'une seule ligne");
			return;
		}
		else if(i<2 || i > this.ligne){
			System.out.println("La ligne n'est pas dans la matrice");
		}

		i--;
		int tmp;

		for(int j = 0; j < this.getColonne(); j++){
			tmp = this.tab[0][j];
			this.tab[0][j] = this.tab[i][j]; 
			this.tab[i][j] = tmp;
		}

	}

	public int[] multiplierValeurLigne(int ligne, int x){
		int A[] = this.tab[ligne];
		for(int i = 0; i < this.getColonne(); i++){
			A[i] = A[i] * x;
		}
		return A;
	}

	public void operationLigne(){  /** TODO mettre ligne quon veut inverser multiplier par paramètre **/
		System.out.println("Addition de ligne: \n");
		//int x = 1;
		int y = 1;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);

		int a = this.getLigneMatrice();

		/*
		 * 	System.out.println("Par quelle valeur multipliÃ© la premiere ligne"  );
		 *
		 *	do{
		 *		x = sc.nextInt();
		 *	}while(x != 0);
		 *
		 *
		 *
		 * 	multiplierValeurLigne(a, x);
		 */

		int b = this.getLigneMatrice();
		System.out.println("Par quelle valeur multipliÃ© la deuxieme ligne sÃ©lectionnÃ©"  );

		do{
			y = sc.nextInt();
		}while(y == 0);
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

		int i = 1;

		while(i<B.getLigne() && B.tab[i][0] == 0) i += 1;

		System.out.println("i="+i);

		if(i<=n) {
			if(i>=2){
				B.transposeLigne(i);
			}
			if(B.tab[0][0]<0){
				B.opposeLigne(1);
			}
			System.out.println("LIGNE TRANSPOSEE");
			for(int j=1; j<p;j++){
				int duv[]= euclideBezout(B.tab[0][0],B.tab[0][j]);
				System.out.println("b00:" + B.tab[0][0]);
				System.out.println("b0j:" + B.tab[0][j]);
				System.out.println("duv:" + duv[0]+" "+duv[1]+" "+duv[2]);
				if(duv[0]!=0){
					Matrice Q = new Matrice(2,2);
					Q.tab[0][0] = duv[1];
					Q.tab[0][1] = -B.tab[0][j]/duv[0];
					Q.tab[1][0] = duv[2];
					Q.tab[1][1] = B.tab[0][0]/duv[0];
					
					System.out.println("Q:");
					Q.parcourirMatrice();
					Q = Q.enflerMatrice(1,j+1,p);
					System.out.println("Q enflée:");
					Q.parcourirMatrice();
					B = B.multiplier(Q);
					System.out.println("B Multiplié : ");
					B.parcourirMatrice();
				}
			}		
		}
		return B;
	}

	//Retourne B1,1 | C1(B)
	public boolean divR2(Matrice B){
		boolean divisible = false;
		for(int j = 0; j<B.getColonne(); j++){
			divisible = divisible && (B.tab[0][0]%B.tab[0][j]==0);
		}
		return divisible;
	}

	public Matrice reduction2(Matrice A){
		Matrice B = new Matrice (A);
		int n = A.getLigne();

		if(B.tab[0][0]!=0){
			
			for(int j=0; j<B.getLigne();j++){
				while(B.tab[0][j]!=0 && B.tab[0][0]%B.tab[0][j] != 0) {
					int i=1;
					while(i<=B.getLigne() && B.tab[0][0]% B.tab[i][0] == 0 ){
						i = i+1;
					}
					System.out.println("i = "+i);
					int duv[]=euclideBezout(B.tab[0][0],B.tab[i][1]);
					Matrice P = new Matrice(2,2);
					P.tab[0][0] = duv[1];
					P.tab[0][1] = duv[2];
					P.tab[1][0] = -B.tab[0][j]/duv[0];
					P.tab[1][1] = B.tab[0][0]/duv[0];
					
					System.out.println("P :");
					P.parcourirMatrice();
					P = P.enflerMatrice(1,i,n);
					System.out.println("P enflée:");
					P.parcourirMatrice();
					System.out.println("Okjukhghyfgd");
					
					B = P.multiplier(B);
					System.out.println("B dans reduction 2 avant reduc 1  " );
					B.parcourirMatrice();
					B = reduction1(B);
				}
				for(int i=2;i<n;i++){
					B.operationLigne();
				}
				int duv[]=euclideBezout(B.tab[0][0],B.tab[0][0]);

				Matrice P = new Matrice(2,2);
				P.tab[0][0] = duv[1];
				P.tab[0][1] = -B.tab[0][j]/duv[0];
				P.tab[1][0] = duv[2];
				P.tab[1][1] = B.tab[0][j]/duv[0];
						B = P.multiplier(B);
				B = reduction1(B);
			}
			for(int j=1;j<n;j++){
				////???

			}
		}
		return B;
	}
	/*
	public Matrice reduction3(Matrice A){
		Matrice B = new Matrice (A.getLigne(),A.getColonne()); 
		int x,j;
		for ( x=0; x<A.getLigne(); x++){
			for ( j=0; j<A.getColonne(); j++)
			{
				B.tab[x][j]=A.tab[x][j];

			}
		}


		int n = A.getLigne();
		int p = A.getColonne();
		if(B.tab[0][0] != 0){
			
			for(int a=0; a)
			while(B.tab[0][0] % B == 0 //????????){
				int i =2;
				while(B.tab[1][1]) | //?????){
					i = i+1;
				}

				// L1 <- L1+Li  sur B 
				B = reduction1(B);
				B = reduction2(B);

			}



		}

		return B;
	}
	 */



}