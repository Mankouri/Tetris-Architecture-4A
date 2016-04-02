/**
 * Piece definie par l'utilisateur.
 */
public class UserPiece extends Piece {
/*
  Constructeur de la classe. Initialise la piece.
  @param posX L'abscisse de la piece dans le canvas en colonne.
  @param posY L'ordonnee de la piece dans le canvas en ligne.
  @param matrice La matrice definissant la piece.
 */
public UserPiece(int posX, int posY, Matrix matrice) {
	super(posX, posY);
	this.matrice = matrice;
}
}
