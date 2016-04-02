import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;

/*
 Definition du canvas affichant la piece suivante.
 @author Ange FOFE, Jalil MANKOURI & Andy HARVEL;
 @version Version 1.0
 */
public class CanvasPieceSuivante extends JComponent {
	
	private static final long serialVersionUID = -428394176851523678L;
	// La dimension du canvas
	Dimension size;
	// La piece suivante qu'il faut afficher
	Piece suivante;

 //Constructeur par defaut du canvas. On se contente d'initialiser le JComponent
 
public CanvasPieceSuivante() {
	super();
}
/*
  Definition de la dimension minimale du Canvas.
  @return La dimension minimale
 */
public Dimension getMinimumSize() {
	return new Dimension(162, 162);
}
/*
  Definition de la dimension du canvas egale a la taille minimale du canvas.
  @return La dimension souhaitee du canvas.
 */
public Dimension getPreferredSize() {
	return getMinimumSize();
}
/*
 Surcharge de la methode d'affichage du canvas.
 @param g Le Graphics du canvas.
 */
public void paint(Graphics g) {
	g.clearRect(0, 0, 162, 162); // Effacement du canvas.
	g.setColor(Color.black);
	g.drawLine(0, 0, 161, 0); // Effet 3D.
	g.drawLine(0, 0, 0, 161);
	g.setColor(Color.white);
	g.drawLine(161, 0, 161, 161);
	g.drawLine(0, 161, 161, 161);

	g.setColor(new java.awt.Color(220, 220, 220));
	g.fillRect(1, 1, 160, 160); // Fond en gris.
	suivante.drawSuivante(g); // Affichage de la piece suivante.
}
/*
 Affectation de la piece suivante a afficher.
 @param suivante La piece a afficher.
 */
public void setPiece(Piece suivante) {
	this.suivante = suivante;
	repaint();
}
/*
  Surcharge de la methode update. Appelle la methode paint.
  @param g Le Graphics du canvas.
 */
public void update(Graphics g) {
	paint(g);
}
}
