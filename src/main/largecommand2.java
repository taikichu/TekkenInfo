package tekken7;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
public class largecommand2 extends JFrame{
	JPanel pane2=(JPanel)getContentPane();
	Search search1;
	largecommand2(){}
	private static String charaName;
	private static String charaName2;
	private static JLabel label2;
	private static JPanel vspanel1;
	private static JPanel vspanel2;
	private static JButton button2;
	private String[] elements;
	private int w=0;
	private int h=0;
	public largecommand2(JPanel pane,String name) {//String[] elements,String element,Search search,JPanel pane,JPanel menu
		super("フレーム計算");
		w=0;h=0;
		charaName=name;
		Maintekken a=new Maintekken();
		a.getgui().setVisible(false);
		vspanel2=new JPanel();
		vspanel2.setPreferredSize(new Dimension(900,800));//前は600,800
		vspanel2.setLayout(null);
		label2=new JLabel(new ImageIcon());
		button2=new JButton(new Firstchoice2(1,"キャラを選ぶ"));
		button2.setBounds(300,300,200,100);
		vspanel2.add(button2);
		vspanel2.add(label2);
		vspanel1=makepanel1(charaName);
    	pane2.add(vspanel1,BorderLayout.WEST);
    	pane2.add(vspanel2,BorderLayout.EAST);
	}
	class Firstchoice extends AbstractAction {
		public Firstchoice(int no,String text){
			putValue( Action.NAME,text);
			putValue( Action.SMALL_ICON,new ImageIcon( "C:\\pleiades\\workspace\\Pokemon\\icon.png\\クエスチョンマーク.png" ));
			putValue( Action.SHORT_DESCRIPTION, "クリックで選ぶ" );
		}
	    public void actionPerformed( ActionEvent e ){
	    	Gui1 gui=new Gui1() {};
	    	File info=new File("C:\\pleiades\\workspace\\Pokemon\\TEKKEN7.json");
	    	File character[]=info.listFiles();
	    	Object[] a=new Object[character.length];
	    	for(int i=0;i<character.length;i++) {
	    		a[i]=character[i].toString().replace("C:\\pleiades\\workspace\\Pokemon\\TEKKEN7.json\\", "").replace(".json", "");	
	    		}
	      Object msg = "調べたいキャラを選択してください\n（取消を選択するとプログラムが終了します）";
	      Object b = JOptionPane.showInputDialog( gui.getpane(), msg, "キャラを選び直す",
	                         JOptionPane.PLAIN_MESSAGE, new ImageIcon( "exit.gif" ), a, a[0] );
	      if(b==null) System.exit(0);
	          gui.setcharaName(charaName=b.toString());
	          new Maintekken().getgui().setTitle(charaName);
	          
	          gui.setpicmainmenu(charaName);
        	  vspanel1.setVisible(false);
        	  pane2.add(makepanel1(charaName),BorderLayout.WEST);
	    }
	  }
	//メソッド
	public JPanel makepanel1(String charaName) {
		vspanel1=new JPanel();
		
		vspanel1.setPreferredSize(new Dimension(w=600,h=800));
		vspanel1.setLayout( new FlowLayout());
		
		search1=new Search(charaName);
	    elements=search1.elementslist();
	    int space=60;
	    for(;elements.length>=600/space;space-=5) {}
    	for(int i=0;i<elements.length;i++) {
	    	JButton button=new JButton(new Bigcommandchoice(elements,elements[i],search1,pane2,vspanel1,vspanel2,w,h));
	    	button.setPreferredSize(new Dimension(550,space));
	    	vspanel1.add(button);
	    }
    	JButton retmain=new JButton(new Ret1(0));
    	retmain.setPreferredSize(new Dimension(400,space));
    	vspanel1.add(retmain);
    	
    	JButton changechara=new JButton(new Firstchoice(2,"キャラクターを変更する（現在："+charaName+"）"));
    	changechara.setPreferredSize(new Dimension(400,space));
    	vspanel1.add(changechara);
    	
    	return vspanel1;
	}
	public void setcharaName(String name) {
		charaName=name;
	}
	public void setcharaName2(String name) {
		charaName2=name;
	}
	public String getcharaName2() {
		return charaName2;
	}
	public JButton getbutton2() {
		return button2;
	}
	public JLabel getlabel2() {
		return label2;
	}
	public JPanel getvspanel1() {
		return vspanel1;
	}
	public JPanel getvspanel2() {
		return vspanel2;
	}
	public void setTitle() {
		setTitle(charaName2);
	}
	public void setpicmenu2(String text) {
		search1=new Search(charaName2=text);
		 label2.setIcon(new ImageIcon( "C:\\pleiades\\workspace\\Pokemon\\TEKKEN7.png\\"+search1.getName()+".png" ));
		 label2.setBounds(0,0,600,800);
		 button2.setText("キャラを選び直す");
	}
	public JPanel getpane2() {
		return pane2;
	}
}