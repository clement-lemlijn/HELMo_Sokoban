/*
Preblèmes connus : 
	Joueur qui spawn sur la caisse/objectif
	Caisse qui spawn sur l'objectif
	parfois problème lors de la génération(suffit de relancer)
	Duplication de l'objetctif quand on pousse la caisse en passant dessus
*/

package Sokoban.src;
import io.*;

public class Sokoban{
	static int dist = aleatoire(5,15);
	static int gridSize = 11, nbSols=0;
/*---------------------------------------------------------------------*/
/*--------------------------- MAIN ------------------------------------*/
/*---------------------------------------------------------------------*/
	public static void main(String[] args) throws Exception{

		grille = new int[gridSize][gridSize];


		generateGame();
		Fenetre.creerFenetre("Sokoban", gridSize*64, gridSize*64, 1, 1); // car bonhomme=64*64 => 8cases sur 8
		Fenetre.setFonctionAuditeur(Sokoban.class.getMethod("executerAction", String.class, ActionFenetre.class, String.class));
		actualiserAffichage();
	}
/*---------------------------------------------------------------------*/
/*------------------------- PATTERNS ----------------------------------*/
/*---------------------------------------------------------------------*/
	static final int [][] PATTERNS= {
			////////////////	1
			{0, 0, 0, 0, 0},
			{0, 1, 1, 1, 0},
			{0, 1, 1, 1, 0},
			{0, 1, 1, 1, 0},
			{0, 0, 0, 0, 0},
			////////////////	2
			{0, 0, 0, 0, 0},
			{0, 2, 1, 1, 0},
			{0, 1, 1, 1, 0},
			{0, 1, 1, 1, 0},
			{0, 0, 0, 0, 0},
			////////////////	3
			{0, 0, 0, 1, 1},
			{0, 2, 2, 1, 1},
			{0, 1, 1, 1, 0},
			{0, 1, 1, 1, 0},
			{0, 0, 0, 0, 0},
			////////////////	4
			{0, 0, 0, 0, 0},
			{0, 2, 2, 2, 0},
			{0, 1, 1, 1, 0},
			{0, 1, 1, 1, 0},
			{0, 0, 0, 0, 0},
			////////////////	5
			{0, 0, 0, 0, 0},
			{0, 2, 2, 2, 0},
			{0, 2, 1, 1, 0},
			{0, 2, 1, 1, 0},
			{0, 0, 0, 0, 0},
			////////////////	6
			{0, 0, 1, 0, 0},
			{0, 2, 1, 1, 0},
			{1, 1, 1, 1, 0},
			{0, 1, 1, 2, 0},
			{0, 0, 0, 0, 0},
			////////////////	7
			{0, 0, 0, 0, 0},
			{0, 2, 1, 1, 0},
			{1, 1, 1, 1, 0},
			{0, 2, 1, 1, 0},
			{0, 0, 0, 0, 0},
			////////////////	8
			{0, 0, 1, 0, 0},
			{0, 2, 1, 1, 0},
			{1, 1, 1, 1, 0},
			{0, 2, 1, 2, 0},
			{0, 0, 1, 0, 0},
			////////////////	9
			{0, 0, 1, 0, 0},
			{0, 2, 1, 2, 0},
			{1, 1, 1, 1, 1},
			{0, 2, 1, 2, 0},
			{0, 0, 1, 0, 0},
			////////////////	10
			{0, 0, 1, 0, 0},
			{0, 2, 1, 2, 0},
			{0, 2, 1, 1, 1},
			{0, 2, 2, 2, 0},
			{0, 0, 0, 0, 0},
			////////////////	11
			{0, 0, 0, 0, 0},
			{0, 2, 2, 2, 0},
			{1, 1, 1, 1, 1},
			{0, 2, 2, 2, 0},
			{0, 0, 0, 0, 0},
			////////////////	12
			{0, 0, 0, 0, 0},
			{0, 1, 1, 1, 1},
			{0, 1, 2, 1, 1},
			{0, 1, 1, 1, 0},
			{0, 0, 0, 0, 0},
			////////////////	13
			{0, 0, 0, 0, 0},
			{0, 2, 2, 2, 0},
			{0, 2, 2, 2, 0},
			{0, 2, 2, 2, 0},
			{0, 0, 0, 0, 0},
			////////////////	14
			{0, 0, 0, 0, 0},
			{0, 2, 2, 2, 0},
			{0, 2, 1, 1, 0},
			{1, 1, 1, 1, 0},
			{1, 1, 0, 0, 0},
			////////////////	15
			{0, 1, 0, 1, 0},
			{0, 1, 1, 1, 0},
			{0, 2, 1, 2, 0},
			{0, 1, 1, 1, 0},
			{0, 1, 0, 1, 0},
			////////////////	16
			{0, 0, 0, 0, 0},
			{0, 2, 2, 2, 0},
			{0, 2, 2, 2, 0},
			{0, 1, 1, 1, 0},
			{0, 1, 1, 1, 0},
			////////////////	17
			{0, 0, 0, 0, 0},
			{0, 2, 2, 2, 0},
			{1, 1, 2, 1, 1},
			{0, 1, 1, 1, 0},
			{0, 1, 1, 0, 0},
			};
	public static void generateGame(){
		grille[yAleat][xAleat] = 1;
		victoire = false;
		generateMap();
		generateLevel();
		xJoueur = groundPos[1];
		yJoueur = groundPos[0];
		grille[yJoueur][xJoueur] = 0;
		System.out.println("-----------------------------------------------------");
		System.out.println("xCaisse = " + xCaisse + "  |  yCaisse = " + yCaisse);
		System.out.println("xAleat = " + xAleat + "  |  yAleat = " + yAleat);
		System.out.println("-----------------------------------------------------");
	}
	/*---------------------------------------------------------------------*/
	/*---------------------- GENERATION DE LA MAP -------------------------*/
	/*---------------------------------------------------------------------*/
	public static void generateMap(){
		int nbPats = (gridSize-2)/3;
		int rdmPat;
		int rdmPatRot;
		int [][]selectedPat = new int [5][5];
		int [][]selectedPatRot = new int [5][5];
		
		///////////////////////
		// Collage des patterns
		///////////////////////
		for(int i = 0; i<nbPats ; i++){
			for(int j = 0 ; j<nbPats ; j++){
				rdmPat = aleatoire(1, 17);
				rdmPatRot = aleatoire(1, 4);

				////////////////////////////////////
				// Extraction du PATTERN selectionné
				////////////////////////////////////
				for(int k = 0; k<5 ; k++){
					for(int l = 0; l<5 ; l++){
						selectedPat[k][l] = PATTERNS[k + 5*(rdmPat-1)][l];
					}
				}

				//////////////////////////////
				// Rotation du PATTERN extrait
				//////////////////////////////
				for(int k = 0; k<5 ; k++){
					for(int l = 0; l<5 ; l++){
						switch(rdmPatRot){
							case 1:
								selectedPatRot[k][l] = selectedPat[k][l];
								break;
							case 2:
								selectedPatRot[k][l] = selectedPat[4-l][k]; 
								break;
							case 3:
								selectedPatRot[k][l] = selectedPat[4-k][l];
								break;
							case 4:
								selectedPatRot[k][l] = selectedPat[l][k];
								break;
						} 
					}
				}
				
				/////////////////////////////////////////////////
				// Insertion du pattern dans la grille principale
				/////////////////////////////////////////////////
				for(int k = 0; k<5 ; k++){
					for(int l = 0; l<5 ; l++){
						//System.out.println("k :" + k + " i :" + i + " l :" + l + "	j :" + j);
						//System.out.println((l + 3*j) + " : " + (k + 3*i));
						if(selectedPatRot[k][l] != 0){
							grille[k + 3*i][l + 3*j] = selectedPatRot[k][l];
						}
					}
				}
			}			
		}
		placerMursExt();
		scanLevel();
	}

	/*---------------------------------------------------------------------*/
	/*------ REMPLISSAGE DE TOUTES LES BORDURES PAR DES MURS --------------*/
	/*---------------------------------------------------------------------*/
	public static void generateLevel(){
		/////////////////////////////////////////////////
		// Placement de l'objectif
		/////////////////////////////////////////////////
		int objAleat = aleatoire(1, nbSols);
		//System.out.println(objAleat + " objectif");
		for (int j = 1;j < grille.length-1;j++) {
	        for(int i = 1; i<grille.length-1; i++){
	        	if(testingGrid[i][j] == 9){
	        		objAleat--;
	        		//System.out.println("scan");
	        		if(objAleat==1){
		        		grille[i][j] = 3;
		        		testingGrid[i][j] = 3;
		        		//System.out.println("x" + j + "y" + i + "Est l'objectif");
		        		xAleat = j;
		        		yAleat = i;
		        		objAleat=0;
	        		}
	        	}
	        	
	        }

	    /////////////////////////////////////////////////
		// Placement de la caisse
		/////////////////////////////////////////////////
    	xCaisse = xAleat;
    	yCaisse = yAleat;
	    }
	    /////////////////////////////////////////////////
		// Placement + Deplacement de la caisse
		/////////////////////////////////////////////////


		grille[yCaisse][xCaisse] = 4;

	    // Determination de la distance
	    int directionAleat;
	    int nbDir;
	    for(int dist = aleatoire(64,128); dist>0;dist--){
	    	//System.out.println("move");
	    	nbDir=4;
	    	area[0] = true;
	    	area[1] = true;
			area[2] = true;
	    	area[3] = true;
	    	//System.out.println("xCaisse" + xCaisse);
	    	//System.out.println("yCaisse" + yCaisse);
	    	if(grille[yCaisse-1][xCaisse] == 2 && grille[yCaisse-1][xCaisse] == 6){
	    		area[0] = false;
	    		nbDir--;
	    		//System.out.println("top wall");
	    	}
	    	if(grille[yCaisse][xCaisse+1] == 2 && grille[yCaisse][xCaisse+1] == 6){
	    		area[1] = false;
	    		nbDir--;
	    		//System.out.println("right wall");
	    	}
	    	if(grille[yCaisse+1][xCaisse] == 2 && grille[yCaisse+1][xCaisse] == 6){
	    		area[2] = false;
	    		nbDir--;
	    		//System.out.println("bottom wall");
	    	}
	    	if(grille[yCaisse][xCaisse-1] == 2 && grille[yCaisse][xCaisse-1] == 6){
	    		area[3] = false;
	    		nbDir--;
	    		//System.out.println("left wall");
	    	}
	    	//System.out.println(nbDir+"cotes libres");
	    	
	    	directionAleat = aleatoire(1, nbDir);
	    	
	    	if(area[0] == false){
	    		directionAleat++;
	    	}
	    	if(area[1] == false){
	    		directionAleat++;
	    	}
	    	if(area[2] == false){
	    		directionAleat++;
	    	}
	    	if(area[3] == false){
	    		directionAleat++;
	    	}
	    	//System.out.println("move" + directionAleat);
	    	moveCaisseAleat(directionAleat);	
	    	
	    	/* test de rendre le path 100% réalisable
	    	if(directionAleat == 1){
	    		grille[yCaisse-1][xCaisse] = 1;
	    	}
	    	if(directionAleat == 2){
	    		grille[yCaisse][xCaisse+1] = 1;
	    	}
	    	if(directionAleat == 3){
	    		grille[yCaisse+1][xCaisse] = 1;
	    	}
	    	if(directionAleat == 4){
	    		grille[yCaisse][xCaisse-1] = 1;
	    	}
	    	*/
	    }
	    
	    grille[yCaisse-1][xCaisse-1] = 1;
	    grille[yCaisse-1][xCaisse] = 1;
	    grille[yCaisse-1][xCaisse+1] = 1;
	    grille[yCaisse][xCaisse+1] = 1;
	    grille[yCaisse+1][xCaisse+1] = 1;
	    grille[yCaisse+1][xCaisse] = 1;
	    grille[yCaisse+1][xCaisse-1] = 1;
	    grille[yCaisse][xCaisse-1] = 1;



	    grille[yAleat][xAleat] = 3;// Remettre l'objectif à son emplacement initial
	}
	static boolean[] area = new boolean [4];
	static int xAleat, yAleat;
	/*---------------------------------------------------------------------*/
	/*------ REMPLISSAGE DE TOUTES LES BORDURES PAR DES MURS --------------*/
	/*---------------------------------------------------------------------*/
	public static void placerMursExt(){
		for (int i = 0;i < grille.length; i++) {
	        for(int j = 0; j<grille.length; j++){
	        	if(i == 0 || i == (grille.length-1) || j == 0 || j == (grille.length-1)){
	        		grille[i][j] = 6; // Mur
	        	}
	        }
	    }
	}

	/*---------------------------------------------------------------------*/
	/*--------- RECHERCHE DU 1ER SOL EN PARTANT D'EN HAUT A GAUCHE --------*/
	/*---------------------------------------------------------------------*/
	public static int[] findGround(){
		boolean find = false;
		while(find == false){
			if(testingGrid[pos[0]][pos[1]] != 1){
				if(pos[1]<((testingGrid.length)-1)){
					pos[1]+=1;
				}else{
					pos[1]=1;
					pos[0]+=1;
				}
			}else{
				find=true;
			}
		}
		//System.out.println(pos[1] + "," + pos[0]+"=sol");
		return pos;
	}

	/*---------------------------------------------------------------------*/
	/*-------------- VALIDATION /?REGENERATION DU LEVEL -------------------*/
	/*---------------------------------------------------------------------*/
	public static void scanLevel(){
		
		////////////////////////////////
		// 1.Compter le nombre de "sols"
		////////////////////////////////
		nbSols = 0;
		for(int i=0; i<grille.length; i++){
			for(int j=0; j<grille.length; j++){
				if(grille[i][j] == 1){
					nbSols+=1;
				}
			}
		}
		//System.out.println("nombre de sols : "+nbSols);

		//////////////////////////////////////
		// 2. definir le nombre de sols isolés
		//////////////////////////////////////		
		for(int i=0; i<grille.length; i++){
			for(int j=0; j<grille.length; j++){
				testingGrid[i][j] = grille[i][j];
			}
		}
		solsTrouves=0;
		
		/*
		System.out.println("TestingGrid");
		for(int i=0; i<testingGrid.length; i++){
			for(int j=0; j<testingGrid.length; j++){
				System.out.print(testingGrid[j][i]);
			}
			System.out.println();
		}
		*/
		groundPos = findGround();
		compteur=0;
		recoAlgo(groundPos[1], groundPos[0], 0);
		/*
		System.out.println("TestingGridrecognized");
		for(int i=0; i<testingGrid.length; i++){
			for(int j=0; j<testingGrid.length; j++){
				System.out.print(testingGrid[j][i]);
			}
			System.out.println();
		}
		*/

		////////////////////////////////////////////////////////////////////////////////////////////////
		// 3. Si nombre de sols trouvés = à nombre de sols tot -> niveau validé, sinon, regénérer niveau
		////////////////////////////////////////////////////////////////////////////////////////////////
		if(solsTrouves != nbSols){
			System.out.println("-------------------------------------------------------------------");
			System.out.println("----------------------- " + solsTrouves + " != " + nbSols + " REGENERATION ---------------------");
			System.out.println("-------------------------------------------------------------------");
			generateMap();
			scanLevel();
		}
	}

	/*---------------------------------------------------------------------*/
	/*---------------- ALGO DE COMPTAGE DES CASES V1 ----------------------*/
	/*---------------------------------------------------------------------*/
	static int compteur=0; 
	public static void recoAlgo(int x, int y, int lvl){
		testingGrid[y][x] = 9;
		solsTrouves+=1;
		compteur+=1;
		//System.out.println(x +","+ y + "recognized, " + "level:" + lvl);
		// si top = sol
		if(testingGrid[y-1][x] == 1){
			recoAlgo(x, y-1, compteur);				
		}
		// si right = sol
		if(testingGrid[y][x+1] == 1){
			recoAlgo(x+1, y, compteur);	
		}
		// si bottom = sol
		if(testingGrid[y+1][x] == 1){
			recoAlgo(x, y+1, compteur);
		}
		// si left = sol
		if(testingGrid[y][x-1] == 1){
			recoAlgo(x-1, y, compteur);
		}		
	}




	// Variables de classes (de la classe Sokoban) 						[ne surtout pas faire ça à l'examen !!!]
	static int[][] grille;
	static int[] pos = {1,1};
	static int[] groundPos = new int [2];
	static int[] posJoueur;
	static int [][]testingGrid = new int [gridSize][gridSize];
	static final int TAILLE_CASE = 64;
	static int solsTrouves = 0;
	static int xJoueur, yJoueur, joueurLastPos=1, caisseLastPos=3, xCaisse = 3*TAILLE_CASE, yCaisse = 3*TAILLE_CASE, xGoal, yGoal, xGen, yGen;
	////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////// EXECUTER ACTION ///////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void executerAction(String nomElement, ActionFenetre action, String valeur){
		//System.out.print(valeur+"   ");

		switch(valeur){
		case "Up":
			collideCaisse(1);
			movePlayer(1);
			break;
		case "Right":
			collideCaisse(2);
			movePlayer(2);
			break;
		case "Down":
			collideCaisse(3);
			movePlayer(3);
			break;
		case "Left":
			collideCaisse(4);
			movePlayer(4);
			break;
		case "Z":
			collideCaisse(1);
			movePlayer(1);
			break;
		case "D":
			collideCaisse(2);
			movePlayer(2);
			break;
		case "S":
			collideCaisse(3);
			movePlayer(3);
			break;
		case "Q":
			collideCaisse(4);
			movePlayer(4);
			break;	
		case "R":
			//grille[yJoueur][xJoueur] = 
			//xJoueur = groundPos[1];
			//yJoueur = groundPos[0];
			break;
		case "E":
		case "M":
			generateGame();	
			break;	
		}
		// Si collision, déplacer la caisse
		if(xJoueur == xCaisse && yJoueur == yCaisse){
			switch(valeur){
			case "Up":
				yCaisse-=TAILLE_CASE;
				break;
			case "Right":
				xCaisse+=TAILLE_CASE;
				break;
			case "Down":
				yCaisse+=TAILLE_CASE;
				break;
			case "Left":
				xCaisse-=TAILLE_CASE;
				break;
			case "Z":
				yCaisse-=TAILLE_CASE;
				break;
			case "D":
				xCaisse+=TAILLE_CASE;
				break;
			case "S":
				yCaisse+=TAILLE_CASE;
				break;
			case "Q":
				xCaisse-=TAILLE_CASE;
				break;
			}
		}
		// Logs des positions
		//System.out.println("JOUEUR | x = " + xJoueur + " | y = " + yJoueur + " | CAISSE | x = " + xCaisse + " | y = " + yCaisse);
		checkVictoire();
		actualiserAffichage();
	}	
	public static int aleatoire(int min, int max){
		double aleat = Math.random();
		aleat = aleat* (max-min +1);
		aleat = aleat + min;
		return (int)aleat;
	}
	static boolean victoire = false;
	public static void checkVictoire(){
		if(xCaisse == xAleat && yCaisse == yAleat){
			grille[yAleat][xAleat] = 5;
			victoire = true;
			System.out.println("-------------------------------------------");
			System.out.println("------------- Victoire !!! ----------------");
			System.out.println("-------------------------------------------");
		}
	}
	
	public static void collideCaisse(int playerMove){
		//System.out.println(playerMove);
		switch(playerMove){
			case 1:
				if(yJoueur-1 == yCaisse && xJoueur == xCaisse){
					//System.out.println("collision up ! ");
					if(grille[yCaisse-1][xCaisse] != 2 && grille[yCaisse-1][xCaisse] != 6){
						moveCaisse(1);
					}
				}
				break;
			case 2:
				if(yJoueur == yCaisse && xJoueur+1 == xCaisse){
					//System.out.println("collision right ! ");
					if(grille[yCaisse][xCaisse+1] != 2 && grille[yCaisse][xCaisse+1] != 6){
						moveCaisse(2);	
					}
				}
				break;
			case 3:
				if(yJoueur+1 == yCaisse && xJoueur == xCaisse){
					//System.out.println("collision bottom ! ");
					if(grille[yCaisse+1][xCaisse] != 2 && grille[yCaisse+1][xCaisse] != 6){
						moveCaisse(3);
					}
				}
				break;
			case 4:
				if(yJoueur == yCaisse && xJoueur-1 == xCaisse){
					//System.out.println("collision left ! ");
					if(grille[yCaisse][xCaisse-1] != 2 && grille[yCaisse][xCaisse-1] != 6){
						moveCaisse(4);
					}
					
				}
				break;
		}
	}
	public static void moveCaisseAleat(int move){
		switch(move){
		case 1:
			if(grille[yCaisse-2][xCaisse] != 6){
				moveCaisse(1);
			}
			break;
		case 2:
			if(grille[yCaisse][xCaisse+2] != 6){
				moveCaisse(2);
			}
			break;
		case 3:
			if(grille[yCaisse+2][xCaisse] != 6){
				moveCaisse(3);
			}
			break;
		case 4:
			if(grille[yCaisse][xCaisse-2] != 6){
				moveCaisse(4);
			}
			break;
		}
	}	
	public static void moveCaisse(int move){
		if(!victoire){
			switch(move){
			case 1:
				if(grille[yCaisse-1][xCaisse] != 2 && grille[yCaisse-1][xCaisse] != 6){
					//System.out.println("move Forward");
					grille[yCaisse][xCaisse] = joueurLastPos;
					yCaisse-=1;
					caisseLastPos = grille[yCaisse][xCaisse];
					grille[yCaisse][xCaisse] = 4;
				}
				break;
			case 2:
				if(grille[yCaisse][xCaisse+1] != 2 && grille[yCaisse][xCaisse+1] != 6){
					//System.out.println("move Right");
					grille[yCaisse][xCaisse] = joueurLastPos;
					xCaisse+=1;
					caisseLastPos = grille[yCaisse][xCaisse];
					grille[yCaisse][xCaisse] = 4;
				}
				break;
			case 3:
				if(grille[yCaisse+1][xCaisse] != 2 && grille[yCaisse+1][xCaisse] != 6){
					//System.out.println("move Backward");
					grille[yCaisse][xCaisse] = joueurLastPos;
					yCaisse+=1;
					caisseLastPos = grille[yCaisse][xCaisse];
					grille[yCaisse][xCaisse] = 4;
				}
				break;
			case 4:
				if(grille[yCaisse][xCaisse-1] != 2 && grille[yCaisse][xCaisse-1] != 6){
					//System.out.println("move Left");
					grille[yCaisse][xCaisse] = joueurLastPos;
					xCaisse-=1;
					caisseLastPos = grille[yCaisse][xCaisse];
					grille[yCaisse][xCaisse] = 4;
				}
				break;
			}	
		}
		
	}
	public static void movePlayerForward(){
		grille[yJoueur][xJoueur] = joueurLastPos;
		yJoueur-=1;
		joueurLastPos = grille[yJoueur][xJoueur];
		grille[yJoueur][xJoueur] = 0;
	}
	public static void movePlayerRight(){
		grille[yJoueur][xJoueur] = joueurLastPos;
		xJoueur+=1;
		joueurLastPos = grille[yJoueur][xJoueur];
		grille[yJoueur][xJoueur] = 0;
	}
	public static void movePlayerBackward(){
		grille[yJoueur][xJoueur] = joueurLastPos;
		yJoueur+=1;
		joueurLastPos = grille[yJoueur][xJoueur];
		grille[yJoueur][xJoueur] = 0;
	}
	public static void movePlayerLeft(){
		grille[yJoueur][xJoueur] = joueurLastPos;
		xJoueur-=1;
		joueurLastPos = grille[yJoueur][xJoueur];
		grille[yJoueur][xJoueur] = 0;
	}
	public static void movePlayer(int move){ // 1->UP | 2->RIGHT | 3->DOWN | 4->LEFT
		//System.out.println();
		//System.out.println("Player moved !" + move);
		switch(move){
			case 1:
				if(grille[yJoueur-1][xJoueur] != 2 && grille[yJoueur-1][xJoueur] != 6){
					//System.out.println("move Forward");
					if(grille[yJoueur-1][xJoueur] == 4){
						//System.out.println("Caisse Devant !");
						if(grille[yJoueur-2][xJoueur] != 2 && grille[yJoueur-2][xJoueur] != 6){
							//System.out.println("Pas de mur devant la caisse !");
							movePlayerForward();	
						}
					}else if(grille[yJoueur-1][xJoueur] != 5){
						movePlayerForward();
					}
					
				}
				break;
			case 2:
				if(grille[yJoueur][xJoueur+1] != 2 && grille[yJoueur][xJoueur+1] != 6){
					//System.out.println("move Right");
					if(grille[yJoueur][xJoueur+1] == 4){
						//System.out.println("Caisse à Droite !");
						if(grille[yJoueur][xJoueur+2] != 2 && grille[yJoueur][xJoueur+2] != 6){
							//System.out.println("Pas de mur devant la caisse !");
							movePlayerRight();	
						}
					}else if(grille[yJoueur][xJoueur+1] != 5){
						movePlayerRight();
					}
				}
				break;
			case 3:
				if(grille[yJoueur+1][xJoueur] != 2 && grille[yJoueur+1][xJoueur] != 6){
					//System.out.println("move Backward");
					if(grille[yJoueur+1][xJoueur] == 4){
						//System.out.println("Caisse en Bas !");
						if(grille[yJoueur+2][xJoueur] != 2 && grille[yJoueur+2][xJoueur] != 6){
							//System.out.println("Pas de mur devant la caisse !");
							movePlayerBackward();	
						}
					}else if(grille[yJoueur+1][xJoueur] != 5){
						movePlayerBackward();
					}
					
				}
				break;
			case 4:
				if(grille[yJoueur][xJoueur-1] != 2 && grille[yJoueur][xJoueur-1] != 6){
					//System.out.println("move Left");
					if(grille[yJoueur][xJoueur-1] == 4){
						//System.out.println("Caisse à Gauche !");
						if(grille[yJoueur][xJoueur-2] != 2 && grille[yJoueur][xJoueur-2] != 6){
							//System.out.println("Pas de mur devant la caisse !");
							movePlayerLeft();	
						}
					}else if(grille[yJoueur][xJoueur-1] != 5){
						movePlayerLeft();
					}
					
				}
				break;
		}
		
	}
	/*---------------------------------------------------------------------*/
	/*---------------------------------------------------------------------*/
	/*---------------------------------------------------------------------*/
	private static void actualiserAffichage(){
		//System.out.println("Affichage actualisé");
		Fenetre.effacerImages();
		actualiserGrille();
		Fenetre.dessinerImages();
		Fenetre.afficher();
	}
	/*---------------------------------------------------------------------*/
	/*------- LECTURE DE LA GRILLE ET MAJ GRAPHIQUE DE CELLE_CI -----------*/
	/*---------------------------------------------------------------------*/
	public static void actualiserGrille(){
		for(int i = 0; i<grille.length; i++) {
	        for(int j = 0; j<grille.length; j++){
	        	switch(grille[j][i]){
	        		case 0:
	        			Fenetre.preparerImage("img\\sol.png", i*TAILLE_CASE, j*TAILLE_CASE);
	        			Fenetre.preparerImage("img\\joueur.png", i*TAILLE_CASE, j*TAILLE_CASE);
	        			//System.out.print("0");
	        			break;
        			case 1:
	        			Fenetre.preparerImage("img\\sol.png", i*TAILLE_CASE, j*TAILLE_CASE);
	        			//System.out.print("1");
	        			break;
	        		case 2:
	        			Fenetre.preparerImage("img\\sol.png", i*TAILLE_CASE, j*TAILLE_CASE);
	        			Fenetre.preparerImage("img\\mur.png", i*TAILLE_CASE, j*TAILLE_CASE);
	        			//System.out.print("2");
	        			break;
	        		case 3:
	        			Fenetre.preparerImage("img\\sol.png", i*TAILLE_CASE, j*TAILLE_CASE);
	        			Fenetre.preparerImage("img\\objectif.png", i*TAILLE_CASE, j*TAILLE_CASE);
	        			//System.out.print("3");
	        			break;
	        		case 4:
	        			Fenetre.preparerImage("img\\sol.png", i*TAILLE_CASE, j*TAILLE_CASE);
	        			Fenetre.preparerImage("img\\caisse_normale.png", i*TAILLE_CASE, j*TAILLE_CASE);
	        			//System.out.print("4");
	        			break;
	        		case 5:
	        			Fenetre.preparerImage("img\\sol.png", i*TAILLE_CASE, j*TAILLE_CASE);
	        			Fenetre.preparerImage("img\\caisse_verte.png", i*TAILLE_CASE, j*TAILLE_CASE);
	        			//System.out.print("5");
	        			break;
	        		case 6: // mur incassable
	        			Fenetre.preparerImage("img\\sol.png", i*TAILLE_CASE, j*TAILLE_CASE);
	        			Fenetre.preparerImage("img\\mur.png", i*TAILLE_CASE, j*TAILLE_CASE);
	        			//System.out.print("2");
	        			break;			
	        	}
	        }
	    }
	}
}