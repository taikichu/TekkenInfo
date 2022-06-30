package tekken7;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;

public class Ret1 extends AbstractAction{ // Action クラスの定義
	int number;
    Ret1(int number){
      putValue( Action.NAME, "メイン選択画面に戻る" );
      putValue( Action.SMALL_ICON,new ImageIcon( "C:\\pleiades\\workspace\\Pokemon\\icon.png\\戻る.png" ));
      putValue( Action.SHORT_DESCRIPTION, "クリックで戻る" );
      this.number=number;
    }
    public void actionPerformed( ActionEvent e ){ // ボタンがクリックされたときの処理
		Gui1 one=new Gui1();
		Maintekken two=new Maintekken();
    	if(number==0) {
    		one.getvslarge().setVisible(false);//large
    		two.getgui().setVisible(true);//pane
    	}
    	else if(number==1) {
    		one.getmenu1().setVisible(false);
    		one.getmainmenu().setVisible(true);
    	}
    }
}