import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;

/*
 Canvas d'affichage de la matrice de la piece d'edition
 @author Ange FOFE, Jalil MANKOURI & Andy HARVEL;

 */

 public class PieceEditCanvas extends JComponent {
	// Image de la matrice d'edition courante: 
	private Matrix matrice;
/*
 Constructeur du canvas d'edition de piece.
 @param size Taille de la matrice d'edition.
 */
public PieceEditCanvas(int size) {
	super();
	matrice = new Matrix(size);
	// Initialisation de la couleur du canvas
	setBackground(Color.yellow);
}
/*
  Retourne la taille du canvas
  @return Dimension du canvas
 */
public Dimension getMinimumSize() {
	return new Dimension(matrice.getSize()*24+1, matrice.getSize()*24+1);
}
/*
  Retourne la taille du canvas.
  @return Dimension du canvas
 */
public Dimension getPreferredSize() {
	return getMinimumSize();
}
/*
  Dessine le quadrillage et le contenu de la matrice d'edition de la piece.
  @param g java.awt.Graphics
 */
public void paint(Graphics g) {
	g.setColor(Color.black);

	// Dessin du quadrillage
	for (int x = 0; x <= 24 * matrice.getSize(); x += 24) {
		g.drawLine(x, 0, x, 24 * matrice.getSize());
	}
	for (int y = 0; y <= 24 * matrice.getSize(); y += 24) {
		g.drawLine(0, y, 24 * matrice.getSize(), y);
	}

	// Dessine la piece
	for (int x = 0; x < matrice.getSize(); x++) {
		for (int y = 0; y < matrice.getSize(); y++) {
			if (matrice.getVal(x, y) != -1) {
				new BlocGraphique(Couleur.getCouleur(matrice.getVal(x, y)), x, y).draw(g);
			}
		}
	}
}
/*
  Affectet la matrice d'edition a visualiser.
  @param Matrice d'edition
 */
 
public void setMat(Matrix mat) {
	if (matrice.getSize() != this.matrice.getSize()) {
		this.matrice = matrice;
	} else {
		for (int x = 0; x < matrice.getSize(); x++) {
			for (int y = 0; y < matrice.getSize(); y++) {
				this.matrice.setVal(x,y,matrice.getVal(x,y));
			}
		}
	}
}
/*
  Utilise pour le repaint().
  @param g Canvas d'affichage courant
 */
public void update(Graphics g) {
	paint(g);
}
}
