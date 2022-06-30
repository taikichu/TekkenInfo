package tekken7;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Firstchoice2 extends AbstractAction {
	static String charaName;
	static String charaName2;
	public Firstchoice2(int no,String text){
		putValue( Action.NAME,text);
		putValue( Action.SMALL_ICON,new ImageIcon( "C:\\pleiades\\workspace\\Pokemon\\icon.png\\クエスチョンマーク.png" ));
		putValue( Action.SHORT_DESCRIPTION, "クリックで選ぶ" );
	}
    public Firstchoice2( int i2 ,JPanel paneC){ 
    }
    public void actionPerformed( ActionEvent e ){
    	Gui1 gui=new Gui1() {};
    	largecommand2 command=new largecommand2();
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
    	  command.setcharaName2(charaName2=b.toString());
          command.setpicmenu2(charaName2);
    }
    public String getcharaName1() {
    	return charaName;
    }
    public String getcharaName2() {
    	return charaName2;
    }
  }