package geometrie;

import java.util.ArrayList;
import java.util.Collections;

/**
 * <h1>Un Polygone est une liste ordonnée de Points appelés sommets.</h1>
 * <p>Un Polygone peut :
 * 	<ul>
 * 		<li>En copier un autre</li>
 * 		<li>Retourne un point à une position donnée</li>
 * 		<li>Ajouter un vecteur à chaque point</li>
 * 		<li>Ajouter un sommet</li>
 * 		<li>Dire s'il contient un point</li>
 * 		<li>Donner son nombre de sommets</li>
 * 		<li>Dire s'il est inclus dans un rectangle</li>
 * 		<li>S'ordonner de façon à être affichable en console</li>
 * 		<li>Donner les extrêmes de ses points</li>
 * 		<li>S'afficher</li>
 * </ul>
 * La classe Polygone permet de manipuler facilement des ensembles de points
 * utiles notamment lors des opérations de collisions.
 * </p>
 * @see Point
 * @see Vecteur
 * 
 * @author Mathys
 * @version 2.0
 */

public class Polygone {
	
	ArrayList<Point> listeSommets ;
	
	/**
	 * Constructeur permettant d'initialiser rapidement un polygone vide
	 * 
	 * @since 1.0
	 */
	
	public Polygone () {
		
		this.listeSommets = new ArrayList<Point> () ;

	}
	
	/**
	 * Crée un Polygone de nbSommets initialisés à 0:0
	 * 
	 * @param nbSommets
	 * @since 2.0
	 */
	
	public Polygone (int nbSommets) {
		
		this () ;
		int i ;
		
		for (i = 0 ; i < nbSommets ; i ++)
			
			this.ajouterSommet(new Point (0, 0)) ;
		
	}
	
	/**
	 * Crée un polygone de nbSommets initialisés à x:y
	 * 
	 * @param nbSommet
	 * @param x
	 * @param y
	 * @since 2.0
	 */
	
	public Polygone (int nbSommet, double x, double y) {
		
		this () ;
		int i ;
		
		for (i = 0 ; i < nbSommet ; i ++) {
			
			this.ajouterSommet(new Point (x, y)) ;
			
		}
		
	}
	
	/**
	 * Crée un Polygone composé des points entrés en paramètre
	 * dans l'ordre
	 * 
	 * @param points
	 * @since 2.0
	 */
	
	public Polygone (Point...points) {
		
		this() ;
		
		for (Point point : points) {
			
			this.ajouterSommet(point) ;
			
		}
		
	}
	
	/**
	 * Copie le contenu d'un autre polygone dans celui-ci
	 * 
	 * @param polygone
	 * @since 2.0
	 */
	
	public void copie (Polygone polygone) {
		
		int i ;
		
		this.listeSommets = new ArrayList<Point> () ;
		
		for (i = 0 ; i < polygone.nbSommets() ; i ++) {
			
			this.ajouterSommet(new Point (polygone.get(i).getX(), polygone.get(i).getY())) ;
			
		}
		
	}
	
	/**
	 * Retourne le point situé à l'indice i dans la liste
	 * 
	 * @param i
	 * @return Point
	 * @since 1.0
	 */
	
	public Point get (int i) {
		
		return this.listeSommets.get(i) ;
		
	}
	
	/**
	 * Ajoute les x et les y du vecteur à chaque point
	 * du polygone
	 * 
	 * @param vecteur
	 * @since 2.0
	 */
	
	public void ajouterAChaquePoint (Vecteur vecteur) {
		
		int i ;
		
		for (i = 0 ; i < this.nbSommets() ; i ++) {
			
			this.listeSommets.set(i, this.get(i).add(vecteur.getX(), vecteur.getY())) ;
			
		}
		
	}
	
	public void ajouterSommet (Point point) {
		
		if (!this.contient(point))
			
			this.listeSommets.add(point) ;
		
	}
	
	public void ajouterSommet (double x, double y) {
		
		this.ajouterSommet(new Point (x, y)) ;
		
	}
	
	public boolean contient (Point point) {
		
		int i ;
		boolean trouve ;
		
		i = 0 ;
		trouve = false ;
		
		while (!trouve && i < this.nbSommets ()) {
			
			if (this.listeSommets.get(i).compareTo(point) == 0)
				
				trouve = true ;
			
			i ++ ;
			
		}
		
		return trouve ;
		
	}
	
	public int nbSommets () {
		
		return this.listeSommets.size() ;
		
	}
	
	public boolean estInclusDans (double xMax, double yMax) {
		
		int i ;
		boolean depasseLesBornes ;
		
		i = 0 ;
		depasseLesBornes = false ;
		
		while (!depasseLesBornes && i < this.nbSommets()) {
			
			if (this.get(i).getX() < 0 || this.get(i).getY() < 0 || this.get(i).getX() >= xMax || this.get(i).getY() >= yMax) {
				
				depasseLesBornes = true ;
				
			}
			
			i ++ ;
			
		}
		
		return !depasseLesBornes ;
		
	}
	
	public boolean estEgalA (Polygone poly2) {
		
		int i ;
		boolean estEgal ;
		
		i = 0 ;
		estEgal = true ;
		
		if (this.nbSommets() == poly2.nbSommets()) 
		
			while (estEgal && i < this.nbSommets() && i < poly2.nbSommets()) {
				
				if (!(this.get(i).compareTo(poly2.get(i)) == 0))
					
					estEgal = false ;
				
				i ++ ;
				
			}
		
		else
			
			estEgal = false ;
		
		return estEgal ;
		
	}
	
	public static ArrayList<ArrayList<Point>> tableauOrdonne (ArrayList<Point> listeOrdonneeParY) {
		
		int i ;
		double y ;
		ArrayList<ArrayList<Point>> nvListe ;
		
		i = 0 ;
		y = listeOrdonneeParY.get(0).getY() ;
		
		nvListe = new ArrayList<> () ;
		nvListe.add(new ArrayList<Point> ()) ;
		
		for (i = 0 ; i < listeOrdonneeParY.size() ; i ++) {

			if (y != listeOrdonneeParY.get(i).getY()) {
				
				nvListe.add(new ArrayList<Point> ()) ;
				y = listeOrdonneeParY.get(i).getY() ;
				
			}

			nvListe.get(nvListe.size() - 1).add(listeOrdonneeParY.get(i)) ;
			
		}
		
		return nvListe ;
		
	}
	
	public ArrayList<Point> listeOrdonneeParY () {
		
		ArrayList<Point> listeAffichage ;
		
		listeAffichage = new ArrayList<Point>() ;
		listeAffichage.addAll(this.listeSommets) ;
		Collections.sort(listeAffichage);
		
		return listeAffichage ;
		
	}
	
	public double[] minMaxX (ArrayList<ArrayList<Point>> tableauOrdonne) {
		
		int i ;
		double min ;
		double max ;
		double[] minMax ;
		
		i = 0 ;
		min = tableauOrdonne.get(0).get(0).getX() ;
		max = tableauOrdonne.get(0).get(tableauOrdonne.get(i).size() - 1).getX() ;
		minMax = new double[2] ;	
		
		while (i < tableauOrdonne.size()) {
			
			if (tableauOrdonne.get(i).get(0).getX() < min)
				
				min = tableauOrdonne.get(i).get(0).getX() ;
			
			if (tableauOrdonne.get(i).get(tableauOrdonne.get(i).size() - 1).getX() > max)
				
				max = tableauOrdonne.get(i).get(tableauOrdonne.get(i).size() - 1).getX() ;
			
			i ++ ;
			
		}
		
		minMax[0] = min ;
		minMax[1] = max ;
		
		return minMax ;
		
	}
	
	public double[] minMaxY (ArrayList<ArrayList<Point>> tableauOrdonne) {
		
		int i ;
		double min ;
		double max ;
		double[] minMax ;
		
		i = 0 ;
		min = tableauOrdonne.get(0).get(0).getY() ;
		max = tableauOrdonne.get(0).get(tableauOrdonne.get(i).size() - 1).getY() ;
		minMax = new double[2] ;	
		
		while (i < tableauOrdonne.size()) {
			
			if (tableauOrdonne.get(i).get(0).getY() < min)
				
				min = tableauOrdonne.get(i).get(0).getY() ;
			
			if (tableauOrdonne.get(i).get(tableauOrdonne.get(i).size() - 1).getY() > max)
				
				max = tableauOrdonne.get(i).get(tableauOrdonne.get(i).size() - 1).getY() ;
			
			i ++ ;
			
		}
		
		minMax[0] = min ;
		minMax[1] = max ;
		
		return minMax ;
		
	}
	
	public String toString () {
		
		int i ;
		int j ;
		int k ;
		int h ;
		double[] minMaxX ;
		double[] minMaxY ;
		boolean trouve ;
		char c ;
		String aff ;
		ArrayList<ArrayList<Point>> listeAffichage ;

		c = '*' ;
		aff = "" ;
		listeAffichage = new ArrayList<> () ;
		listeAffichage = tableauOrdonne(this.listeOrdonneeParY()) ;
		minMaxX = this.minMaxX(listeAffichage) ;
		minMaxY = this.minMaxY(listeAffichage) ;
		
		for (ArrayList<Point> l : listeAffichage)
			
			for (Point p : l)
				
				p.add(-minMaxX[0], -minMaxY[0]) ;
		
		for (i = 0 ; i <= Math.abs(minMaxY[1] - minMaxX[0]) ; i ++) {
			
			h = 0 ;
			trouve = false ;
			
			while (!trouve && h < listeAffichage.size()) {
				
				if (i - minMaxY[0] == listeAffichage.get(h).get(0).getY())
					
					trouve = true ;
				
				h ++ ;
				
			}
			
			if (trouve)
			
				for (j = 0 ; j <= Math.abs(minMaxX[1] - minMaxX[0]) ; j ++) {
					
					k = 0 ;
					trouve = false ;
					
					while (!trouve && k < listeAffichage.get(h).size()) {
	
						if (j + minMaxX[0] == listeAffichage.get(h).get(k).getX())
							
							trouve = true ;
						
						k ++ ;
						
					}
					
					if (!trouve)
						
						System.out.print(' ');
					
					else
						
						System.out.print(c);
					
				}
			
			System.out.println();
			
		}
		
		/*for (i = 0 ; i < this.nbSommets() ; i ++) {
			
			aff += "Point n�" + i + " : " + this.get(i).toString() + "\n" ;
			
		}*/
		
		return aff ;
		
	}

}
