import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class JPan extends JPanel 
{	

	private static final long serialVersionUID = 1L;

	private final Position image[]={
	//bas
	new Position(61,258),new Position(221,258),
	new Position(381,258),new Position(541,258),
	new Position(701,258),new Position(861,258),
	//haut
	new Position(61,581),new Position(221,581),
	new Position(381,581),new Position(541,581),
	new Position(701,581),new Position(861,581)};

	public int occupeH[] = new int [6];
	private String nameH[] = new String [6];
	private String nameB[] = new String [6];
	private int occupeB[] = new int [6];
	public JPan()
	{
		super();
//		-1=vide
//		autre=plein
		for (int i=0;i<6;i++)
		{
			this.occupeB[i]=-1;
			this.occupeH[i]=-1;
			this.nameB[i]="vide";
			this.nameH[i]="vide";

		}		
	}

	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);		 
        ImageIcon m = new ImageIcon("Plateau.png");
        Image monImage = m.getImage();
        g.drawImage(monImage, 0, 0,this);
        for (int i=0;i<6;i++)
        {
        	try 
    		{
    			Image img = ImageIO.read(new File("vide.png"));
    			g.drawImage(img,this.image[i+6].getPos_x(),this.image[i+6].getPos_y(), this);
    		} 
    		catch (IOException e) 
    		{
    		     e.printStackTrace();		
    		}
        }
        try 
		{
			Image boss = ImageIO.read(new File("Boss.png"));
			g.drawImage(boss,470,20, this);
			this.getGraphics().drawString(""+60, 430, 110);
		} 
		catch (IOException e) 
		{
		     e.printStackTrace();		
		}
        g.drawString("80", 430, 110);
	}
	
	public void piocher(int start, String name)
	{
		try 
		{
			Image img = ImageIO.read(new File(name+".png"));
			this.getGraphics().drawImage(img,this.image[start].getPos_x(),this.image[start].getPos_y(), this);
			this.occupeB[start-6]=start;
			this.nameB[start-6]=name;
		} 
		catch (IOException e) 
		{
		     e.printStackTrace();		
		}
	}
	
	public void poser(int start, int end, String name)
	{
		try 
		{
			Image img = ImageIO.read(new File(name+".png"));
			Image vide = ImageIO.read(new File("Vide.png"));
			this.getGraphics().drawImage(img,this.image[end].getPos_x(),this.image[end].getPos_y(), this);
			this.getGraphics().drawImage(vide,this.image[start].getPos_x(),this.image[start].getPos_y(), this);
			this.occupeB[start-6]=-1;
			this.occupeH[end]=end;
			this.nameB[start-6]="vide";
			this.nameH[end]=name;
			
		} 
		catch (IOException e) 
		{
		     e.printStackTrace();		
		}
	}
	
	public int[] videBas()
	{
		//-1=vide
		//autre=plein
		return this.occupeB;
	}
	
	public int[] videHaut()
	{
		//-1=vide
		//autre=plein

		return this.occupeH;
	}
	
	public String[] getNameH()
	{
		return this.nameH;
	}
	
	public String[] getNameB()
	{
		return this.nameB;
	}

	public void writeM(int cpt)
	{
		try 
		{
			Image img = ImageIO.read(new File("videC.png"));
			this.getGraphics().drawImage(img,1059,822, this);
			this.getGraphics().drawString(""+cpt, 1072, 841);
		} 
		catch (IOException e) 
		{
		     e.printStackTrace();		
		}
		
	}
	
	public void writeV(int cpt)
	{
		try 
		{
			Image img = ImageIO.read(new File("videC.png"));
			this.getGraphics().drawImage(img,1059,783, this);
			this.getGraphics().drawString(""+cpt, 1068, 802);
		} 
		catch (IOException e) 
		{
		     e.printStackTrace();		
		}
		
	}

	public void attaquer(int degats)
	{
		try 
		{
			Image img = ImageIO.read(new File("videC.png"));
			this.getGraphics().drawImage(img,420,90, this);
			this.getGraphics().drawString(""+degats, 430, 110);
		} 
		catch (IOException e) 
		{
		     e.printStackTrace();		
		}
	}

	public void win()
	{
		try 
		{
			Image img = ImageIO.read(new File("YW.png"));
			this.getGraphics().drawImage(img,265,400, this);
		} 
		catch (IOException e) 
		{
		     e.printStackTrace();		
		}
	}
	public void lose()
	{
		try 
		{
			Image img = ImageIO.read(new File("YL.png"));
			this.getGraphics().drawImage(img,265,400, this);
		} 
		catch (IOException e) 
		{
		     e.printStackTrace();		
		}
	}
}