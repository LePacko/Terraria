package controleur;

import modele.*;
import ressources.Images;
import vue.Tuile;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


/**
 * <h1>ControleurMap est la classe chargé de transmettre à la vue les changements du terrain</h1>
 * 
 * <p>Un ControleurMap possède :
 * 	<ul>
 * 		<li>Un Jeu permettant d'accéder au terrain.</li>
 * 		<li>Un Pane auquel seront appliqués les modifications.</li>
 * 	</ul>
 * 	La méthode ajouterEcouteur ajoute un ListChangeListener sur la liste observable d'objets
 * 	inventeriables du terrain.
 * </p>
 *  
 * @see Inventeriable
 * @see Jeu
 * @see Terrain
 * 
 * @author Karen
 * @version 1.1
 */

public class ControleurMap {
	
	/**
	 * Le Jeu du contrôleur
	 * 
	 * <p>Il fournit le terrain observé par le contrôleur</p>
	 * 
	 * @see ControleurMap#ControleurMap(Pane, Jeu)
	 * 
	 */
	
	private Jeu jeu ;
	
	/**
	 * Le Pane du contrôleur.
	 * 
	 * <p>C'est lui que modifiera le contrôleur en cas
	 * de changement</p>
	 * 
	 * @see ControleurMap#ControleurMap(Pane, Jeu)
	 */
	
	private Pane pane ;
	
	private Images images;
	
	
	public ControleurMap(Pane pane, Jeu jeu, Images image) {
		
		this.pane =pane ;
		this.jeu=jeu ;
		this.images = image;
		
	}
	
	

	/**
	 * Ajoute un ChangeListener sur chaque ligne du terrain
	 * 
	 * @author Karen
	 * @see Inventeriable
	 * @see Jeu#getTerrain()
	 * @see Terrain#getListeLignes()
	 * @since 1.0
	 */
	
	public void ajouterEcouteur () {
		
		for (ObservableList<Inventeriable> listeCases : this.jeu.getTerrain().getListeLignes()) {
			
			listeCases.addListener (new ListChangeListener<Inventeriable> () {

				@Override
				public void onChanged(Change<? extends Inventeriable> changement) {

					while (changement.next()) {
						if(changement.wasReplaced()) {
							int x = changement.getFrom();
							//get(x).getFrom;//changement.getAddedSubList();
							System.out.println(x);
							remplacerImage(x);
						}
					}

				}

			}) ;
			
		}
		
	}
	public void remplacerImage(int x) {
		
		int y = this.jeu.getTerrain().getPositionBlockY();
		System.out.println("ok");
		System.out.println(pane.getChildren().toString());
		String nom = x + ":" + y;
		//System.out.println(pane.getChildren().get(0).getId());
		for (int i = 0; i < pane.getChildren().size(); i++) {
			if(pane.getChildren().get(i).getId().equals(nom)) {
				System.out.println("cool");
				ImageView b = (ImageView) pane.getChildren().get(i);
				b.setImage(images.getImage("air"));
			}
		}
		
		//Parent parent = pane;
		//System.out.println(parent.toString());
		//Tuile b = (Tuile) parent.lookup("#" + x + ":" + y);
		//b.toString();
		//b.setImg("air");
	}
	
}
