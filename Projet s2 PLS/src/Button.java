import javax.swing.JButton;

public class Button extends JButton{
	public int active=0;
	
	public Button()
	{
		super();
	}
	public Button(String name)
	{
		super(name);
	}
	public int getActive()
	{
		return this.active;
	}
	

}
