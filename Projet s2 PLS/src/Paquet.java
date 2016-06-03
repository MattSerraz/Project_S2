import java.util.ArrayList;
import java.util.Collections;

public class Paquet {

	ArrayList<Carte> l_paquet;
	private static int z=0 ;
	
	public Paquet(){
		l_paquet= new ArrayList<Carte>();
		remplirPaquet();
		shuffle();
	}
	
	public void remplirPaquet(){
		
			this.l_paquet.add(new Le_Nain());
			this.l_paquet.add(new Zombie());
			this.l_paquet.add(new Sauvageon());
			this.l_paquet.add(new Les_Chiens_de_enfer());
			this.l_paquet.add(new eclaireur());
			this.l_paquet.add(new Le_Viking());
			this.l_paquet.add(new Le_Troll());
			this.l_paquet.add(new Le_Seigneur());
			this.l_paquet.add(new Le_Roi());
			this.l_paquet.add(new Le_Magicien());
			this.l_paquet.add(new Le_loup());
			this.l_paquet.add(new le_chevalier());
			this.l_paquet.add(new Le_Chasseur());
			this.l_paquet.add(new Le_Basilic());
			this.l_paquet.add(new Kamikaze_Gobelin());
			this.l_paquet.add(new Elfe());
			this.l_paquet.add(new Druide());
			this.l_paquet.add(new Barils_Explosifs());
			this.l_paquet.add(new Arraignee_loup());
			this.l_paquet.add(new Armees_Fantomes());
			
		}
	
	public void shuffle()
	{
		Collections.shuffle(l_paquet);
	}
	
	public Carte getHeadCard(){
		z++;
		
		if (z>20) { 
			
			return null;
		}
		
		return l_paquet.get(z);
		
	}
	
	
}

