import java.awt.Graphics;


/*
 Definition generale d'une piece du jeu 
 @author Ange FOFE, Jalil MANKOURI & Andy HARVEL;
*/
public class Piece {
	// Matrice de la piece
	protected Matrix matrice;
	//Position de la piece en X dans la matrice du jeu
	public int posY;
	public int posX;
	 

/*
 Constructeur de la classe Piece
 @param posY Position en Y
 @param posX Position en X 
 */
public Piece(int posX, int posY) {
	// La matrice n'est pas affectee par defaut
	matrice = null;
        this.posY = posY;
	this.posX = posX;
	
}



 //Permet de descendre la piece d'un cran 
public void down() {
	posY++;
}


/*
 Dessine la piece dans un canvas.
 @param g Zone graphique en cours de travail.
 */
public void draw(Graphics g) {
	int miyeuPiece = matrice.getSize()/2;
	for (int x = 0; x < getSize(); x++) {
		for (int y = 0; y < getSize(); y++) {
			if (matrice.getVal(x, y) != -1) {
				(new BlocGraphique(Couleur.getCouleur(matrice.getVal(x, y)), posX + x - miyeuPiece, posY + y - miyeuPiece)).draw(g);
			}
		}
	}
}


/*
 Dessine la piece suivante.
 @param g Zone graphique d'affichage de la piece suivante.
 */
public void drawSuivante(Graphics g) {
	int miyeuPiece = matrice.getSize() / 2;
	for (int x = 0; x < getSize(); x++) {
		for (int y = 0; y < getSize(); y++) {
			if (matrice.getVal(x, y) != -1) {
				(new BlocGraphique(Couleur.getCouleur(matrice.getVal(x, y)), 3 + x - miyeuPiece, 3 + y - miyeuPiece)).draw(g);
			}
		}
	}
}


  //Retourne la position Y de la piece 
public int getPosY() {
	return posY;
}


 // Retourne la position X de la piece
public int getPosX() {
	return posX;
}


  //Retourne la taille de la matrice de la piece 
public int getSize() {
	return matrice.getSize();
}



/*
 Retourne la couleur de la matrice de la piece a une position donnée.
 @param x La position X de la matrice dans la piece.
 @param y La position Y de la matrice dans la piece.
 */
public int getVal(int x, int y) {
	return matrice.getVal(x, y);
}


/*
 Effectue une rotation standard de la piece
 @param sens Sens de la rotation.
 */
public void rotation(boolean sens) {
	matrice.rotation(sens);
}


/*
 Effectue une translation de la piece
 @param sens Sens de la translation (gauche ou droite).
 */
public void translation(boolean sens) {
	if (sens) {
		posX++;
	} else {
		posX--;
	}
}


/*
 Remonte la piece d'un cran.<P> Cette methode ne peut evidement pas etre appelée
 directement par le joueur!
 */

public void up() {
	posY--;
}
}
