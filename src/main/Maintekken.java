package tekken7;

import javax.swing.JFrame;
public class Maintekken {
	static Gui1 gui;
	public static void main(String[] args) {
		Gui1 w=new Gui1("tekken menu");
		gui=w;
		w.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		w.setLocation(250,250);
	    w.setSize( 650, 650 );
	    w.setIconImage(null);
	    w.setResizable(false);
	    w.setVisible(true );
	}
	public Gui1 getgui() {
		return gui;
	}
}
