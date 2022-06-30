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

public class Gui1 extends JFrame{
	JPanel pane=(JPanel)getContentPane();
	private static largecommand2 large;
	private static String charaName;
	private int w=0;
	private int h=0;
	private String charaName2;
	private String[] elements;
	private static Search search1;
	private static JPanel menu1;
	private static JPanel mainmenu;
	private static JPanel picmainmenu;
	private static JLabel label;
	public Gui1() {	}
	//コンストラクタ
	public Gui1( String title ){
		super(title);
		w=0;h=0;
	    new Firstchoice(0,pane);
	    setTitle(charaName);
	    search1=new Search(charaName);
	    new Mainmenu();
  }
	//コマンドのフレームを確認　対キャラのフレーム計算　キャラ変更　を選択できるパネルを表示
	 class Mainmenu{
		 public Mainmenu() {
			 mainmenu=new JPanel();
			 mainmenu.setLayout(new BorderLayout());			 
			 JPanel choicemainmenu=new JPanel();
			 choicemainmenu.setPreferredSize(new Dimension(350,400));
			 choicemainmenu.setLayout(new FlowLayout());
			 JButton button1=new JButton(new largecommand());
			 button1.setPreferredSize(new Dimension(350,40));
			 JButton button2=new JButton(new Vschara());
			 button2.setPreferredSize(new Dimension(350,40));
			 JButton button3=new JButton(new Firstchoice(0,"キャラを選び直す"));
			 button3.setPreferredSize(new Dimension(350,40));
			 choicemainmenu.add(button1);
			 choicemainmenu.add(button2);
			 choicemainmenu.add(button3);
			 choicemainmenu.setOpaque(false);
			 picmainmenu=new JPanel();
			 label=new JLabel(new ImageIcon( "C:\\pleiades\\workspace\\Pokemon\\TEKKEN7.png\\"+search1.getName()+".png" ));
			 picmainmenu.add(label);//+search.getName()+
			 mainmenu.add(choicemainmenu,BorderLayout.SOUTH);//体裁調整
			 mainmenu.add(picmainmenu,BorderLayout.NORTH);
			 pane.add(mainmenu);
		 }
	 }
	 //選択したキャラのコマンド情報の大まかな項目を表示する
	class largecommand extends AbstractAction{
		public largecommand() {
			putValue( Action.NAME, "コマンドのフレームを確認" );
			putValue( Action.SMALL_ICON,new ImageIcon( "C:\\pleiades\\workspace\\Pokemon\\icon.png\\技.png" ));
		}
		public void actionPerformed( ActionEvent e ){
			mainmenu.setVisible(false);
			int space=50;
			menu1=new JPanel();
			menu1.setLayout( new FlowLayout());
			w=650;h=650;//.setSize( 650, 650 );
		    elements=search1.elementslist();
		    for(;elements.length>=550/space;space-=5) {}
	    	for(int i=0;i<elements.length;i++) {
		    	JButton button=new JButton(new Bigcommandchoice(elements,elements[i],search1,pane,menu1,null,w,h));
		    	button.setPreferredSize(new Dimension(600,space));
		    	menu1.add(button);
		    }
	    	JButton retmain=new JButton(new Ret1(1));
	    	menu1.add(retmain);
		    pane.add(menu1,BorderLayout.CENTER);
	    }
	}
	//対キャラのフレーム情報に関するパネルを表示
	class Vschara extends AbstractAction{
		public Vschara() {
			putValue( Action.NAME,"対キャラ：有利不利フレーム、確反、割れない連携");
			putValue( Action.SMALL_ICON,new ImageIcon( "C:\\pleiades\\workspace\\Pokemon\\icon.png\\対.png" ));
	        putValue( Action.SHORT_DESCRIPTION, "クリックで戻る" );
		}
		public void actionPerformed( ActionEvent e ){
			large=new largecommand2(pane,charaName);
			large.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
			large.setLocation(200,100);
		    large.setSize( 1500, 800 );
		    large.setResizable(false);
		    large.setVisible( true );
		}
	}
	//パネルを展開する前のキャラ選択、パネル展開後のキャラ変更ダイアログ設定
	class Firstchoice extends AbstractAction {
		public Firstchoice(int no,String text){
			putValue( Action.NAME,text);
	        putValue( Action.SMALL_ICON,new ImageIcon( "C:\\pleiades\\workspace\\Pokemon\\icon.png\\クエスチョンマーク.png" ));
			putValue( Action.SHORT_DESCRIPTION, "クリックで選ぶ" );
		}
	    public Firstchoice( int i2 ,JPanel paneC){ 
	    	super( "キャラを選ぶ" );
	    	File info=new File("C:\\pleiades\\workspace\\Pokemon\\TEKKEN7.json");
	    	File character[]=info.listFiles();
	    	Object[] a=new Object[character.length];
	    	for(int i=0;i<character.length;i++) {
	    		a[i]=character[i].toString().replace("C:\\pleiades\\workspace\\Pokemon\\TEKKEN7.json\\", "").replace(".json", "");
	    		}
	      Object msg = "調べたいキャラを選択してください\n（取消を選択するとプログラムが終了します）";
	      Object b=JOptionPane.showInputDialog( paneC, msg, "キャラを選択",
	              JOptionPane.PLAIN_MESSAGE, new ImageIcon( "exit.gif" ),
	              a, a[0] );
	      if(b==null) System.exit(0);
	     if(i2==0) {
	    	 Gui1 gui=new Gui1();
	    	 gui.setcharaName(charaName=b.toString());
	     }
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
	    }
	    public String getcharaName1() {
	    	return charaName;
	    }
	    public String getcharaName2() {
	    	return charaName2;
	    }
	  }
	//値取得、値設定のメソッド
	public void setcharaName(String Name) {
		charaName=Name;
	}
	public JPanel getpane() {
		return pane;
	}
	public void setpicmainmenu(String charapic) {
		search1=new Search(charaName=charapic);
		label.setIcon(new ImageIcon( "C:\\pleiades\\workspace\\Pokemon\\TEKKEN7.png\\"+search1.getName()+".png"));
		
	}
	public JPanel getmenu1() {
		return menu1;
	}
	public JPanel getmainmenu() {
		return mainmenu;
	}
	public largecommand2 getvslarge() {
		return large;
	}
}



