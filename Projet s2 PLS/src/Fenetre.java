import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Fenetre {
	public JFrame fen = new JFrame();
	public JPan jpanel = new JPan();
	private static int mana = 0;
	private static int vie = 32;
	private static int vieB = 80;
	public static int start,stop;
	public int somCouMan=0;
	//1280x1080
	private final Position pos_but[]={
			//haut
			new Position(83,226),new Position(243,226),
			new Position(403,226),new Position(563,226),
			new Position(723,226),new Position(883,226),
			//bas
			new Position(83,549),new Position(243,549),
			new Position(403,549),new Position(563,549),
			new Position(723,549),new Position(883,549)};
	private Button button[] = new Button[12];
	public Button Start = new Button("Start");
	private Paquet Card = new Paquet();
	private Carte CardH[] = new Carte[6];
	private Carte CardB[] = new Carte[6];
	public int dejAtt[] = new int[6];
	private static Carte carteCour;
	
	@SuppressWarnings("deprecation")
	public Fenetre()
	{
		this.fen.setTitle("Game.exe");
		this.fen.setLocationRelativeTo(null);
		this.fen.setLayout(null);
		this.fen.setSize(1100, 900);
		this.fen.setResizable(false);
		this.jpanel.setLayout(null);
		this.fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.fen.setContentPane(this.jpanel);
		for(int i =0; i<6;i++)
		{
			Button myButt = new Button();
			this.button[i]=myButt;
			this.button[i].setLabel("attaquer");
			this.button[i].setLayout(null);
			this.button[i].setBounds(this.pos_but[i].getPos_x(), this.pos_but[i].getPos_y(), 100, 20);
			this.button[i].active=0;
			this.dejAtt[i]=0;
		}
		for(int i =6; i<12;i++)
		{
			Button myButt = new Button();
			this.button[i]=myButt;
			this.button[i].setLabel("Poser");
			this.button[i].setLayout(null);
			this.button[i].setBounds(this.pos_but[i].getPos_x(), this.pos_but[i].getPos_y(), 100, 20);
			this.button[i].active=0;
		}
		for(int i=0;i<12;i++)
		{
			fen.add(this.button[i]);
			this.button[i].setEnabled(false);
		}
		this.Start.setBounds(0, 0, 100, 20);
		this.fen.add(this.Start);
		this.Start.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event)
			{
				mana=mana+1;
				vie = vie-2;
				jpanel.writeM(mana);
				jpanel.writeV(vie);				
				
				if(vie<0)
				{
					vie=0;
				}
				if(vieB==0 || vie==0)
				{
					EndGame();
				}
				else
				{
					if(mana+1>10)
					{
						mana=10;
					}
					if(vie<0)
					{
						vie=0;
					}
					somCouMan=0;
					refreshPos();
					piocher();
					Start.setLabel("Fin du tour");
				}				
			}
		});			
	}
	public void Start()
	{
		this.fen.setVisible(true);
		fen.setLocation(0,0);
	}
	public void piocher()
	{		
		refreshButtonAttaquer();
		refreshAttaquer();
		int i=6;
		while(button[i].active!=0)
		{
			i++;
			if(i>=12)
			{
				break;
			}
		}
		if(i<12)
		{
			carteCour = this.Card.getHeadCard();
			CardB[i-6]=carteCour;
			this.jpanel.piocher(i,carteCour.getNom());
			button[i].active=1;
			button[i].setEnabled(true);
			if(carteCour.getCout()+somCouMan>mana)
			{
				button[i].active=1;
				button[i].setEnabled(false);
			}
			button[i].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event)
				{
					start=getEmp(event);
					button[start].active=0;
					button[start].setEnabled(false);
					Start.setEnabled(false);
					for(int i=6;i<12;i++)
					{
						button[i].setEnabled(false);
					}
					poser();
				}
			});	
		}
	}
	
	public void refreshPos()
	{
		for(int i=6;i<12;i++)
		{
			if(CardB[i-6]!=null)
			{
				if(CardB[i-6].getCout()+somCouMan<=mana)
				{
					button[i].setEnabled(true);
				}
				else
				{
					button[i].setEnabled(false);
				}
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	public void poser()
	{
		String nameH[] = jpanel.getNameH();
		for(int i=0;i<6;i++)
		{
			this.button[i].setLabel("ici");
			ActionListener al[] = this.button[i].getActionListeners();
			if(al.length!=0)
			{
				this.button[i].removeActionListener(al[0]);
			}
			this.button[i].setEnabled(false);
			if(nameH[i]=="vide")
			{
				this.button[i].addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent event)
					{
						Start.setEnabled(true);
						stop = getEmp(event);
						CardH[stop]=CardB[start-6];
						somCouMan=somCouMan+CardH[stop].getCout();
						jpanel.writeM(mana - somCouMan);
						CardB[start-6]=null;
						jpanel.poser(start, stop,CardH[stop].getNom());	
						carteCour=null;
						disp(0,6);
						refreshAttaquer();
						refreshPos();
					}
				});
				this.button[i].setEnabled(true);
			}			
		}
	}

	public void disp(int start,int stop)
	{
		for(int i=start;i<stop;i++)
		{
			this.button[i].setEnabled(false);
		}
	}
	public void reap(int start,int stop)
	{
		for(int i=start;i<stop;i++)
		{
			if (this.button[i].active==1)
			{
				this.button[i].setEnabled(true);
			}			
		}

	}
	
	private int getEmp(ActionEvent event)
	{
		int emp=0;
		for (int i=0;i<12;i++)
		{
			if (event.getSource().equals(this.button[i]))
			{
				emp=i;
			}
		}
		return emp;
	}

	@SuppressWarnings("deprecation")
	private void refreshAttaquer()
	{
		String emp[] = jpanel.getNameH();
		for(int i=0;i<6;i++)
		{
			ActionListener al[] = this.button[i].getActionListeners();
			if(al.length!=0)
			{
				this.button[i].removeActionListener(al[0]);
			}			
			this.button[i].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event)
				{
					int deg =CardH[getEmp(event)].getPDD();
					vieB = vieB - deg;
					if(vieB<0)
					{
						vieB=0;
					}
					jpanel.attaquer(vieB);
					dejAtt[getEmp(event)]=1;
					
					button[getEmp(event)].setEnabled(false);
				}
			});
			this.button[i].setLabel("attaquer");
			this.button[i].setEnabled(true);
			if(emp[i]=="vide" || dejAtt[i]!=0)
			{
				this.button[i].setEnabled(false);
			}
		}

	}

	private void refreshButtonAttaquer()
	{
		for(int i=0;i<6;i++)
		{
			dejAtt[i]=0;
		}
	}
	
	private void EndGame()
	{
		for(int i=0;i<12;i++)
		{
			button[i].setEnabled(false);
		}
		Start.setEnabled(false);
		if(vieB==0 && vie>0)
		{
			jpanel.win();
		}
		else
		{
			jpanel.lose();
		}
	}
}
