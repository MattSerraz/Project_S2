import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


public class Fenetre {
	public JFrame fen = new JFrame();
	public JPan jpanel = new JPan();
	private static int mana = 1;
	private static int vie = 30;
	private static int vieB = 30;
	public static int start;
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
	public Button piocher = new Button("piocher");//à remplacer pa un automatisme
	private Paquet Card = new Paquet();
	public int dejAtt[] = new int[6];
	
	@SuppressWarnings("deprecation")
	public Fenetre()
	{
		this.fen.setTitle("Ma première fenêtre Java");
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
			this.button[i].setLabel("ici");
			this.button[i].setLayout(null);
			this.button[i].setBounds(this.pos_but[i].getPos_x(), this.pos_but[i].getPos_y(), 100, 20);
			this.dejAtt[i]=0;
		}
		for(int i =6; i<12;i++)
		{
			Button myButt = new Button();
			this.button[i]=myButt;
			this.button[i].setLabel("Poser");
			this.button[i].setLayout(null);
			this.button[i].setBounds(this.pos_but[i].getPos_x(), this.pos_but[i].getPos_y(), 100, 20);
		}
		for(int i=0;i<12;i++)
		{
			fen.add(this.button[i]);
			this.button[i].setEnabled(false);
		}
		this.piocher.setBounds(0, 0, 100, 20);
		this.fen.add(this.piocher);
		this.piocher.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event)
			{
				jpanel.writeM(mana);
				jpanel.writeV(vie);
				mana=mana+1;
				vie = vie-2;
				if(mana+1>10)
				{
					mana=10;
				}
				if(vie<0)
				{
					vie=0;
				}
				piocher();
			}
		});
		this.fen.setVisible(true);
		fen.setLocation(0,0);	
	}

	public void piocher()
	{
		refreshButtonAttaquer();
		//10=vide
		//autre=plein
		Carte test = this.Card.getHeadCard();
		String name = test.getNom();
		int empla[] = new int[6];
		empla=jpanel.videBas();
		refreshAttaquer();
		for(int i=6;i<12;i++)
		{
			if(empla[i-6]==-1 && this.button[i].active==0)
			{
				this.jpanel.piocher(i,name);
				this.button[i].active=1;
				this.button[i].addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent event)
					{
						refreshOther();
						start=getEmp(event);
						disp(6,12);
						poser();
						button[start].active=0;;
					}
				});
				this.button[i].setEnabled(true);
				break;
			}
		}
				
	}		
	
	public void poser()
	{		
		int emplacement[] = new int[6];
		emplacement=this.jpanel.videHaut();
		refreshOther();
		for (int i=0;i<6;i++)
		{
			if(emplacement[i]==-1)
			{
				this.button[i].active=1;
				
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
					vieB = vieB - 2;
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
	
	@SuppressWarnings("deprecation")
	private void refreshOther()
	{
		String nameB[] = jpanel.getNameB();
		for(int i=0;i<6;i++)
		{
			this.button[i].setLabel("ici");;
			ActionListener al[] = this.button[i].getActionListeners();
			if(al.length!=0)
			{
				this.button[i].removeActionListener(al[0]);
			}
			this.button[i].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event)
				{
					int stop=getEmp(event);
					jpanel.poser(start, stop,nameB[start-6]);
					disp(0,6);
					reap(6,12);
					refreshAttaquer();
				}
			});
		}		
	}

}
