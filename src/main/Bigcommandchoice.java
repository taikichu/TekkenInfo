package tekken7;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;



public class Bigcommandchoice extends AbstractAction{ // Action クラスの定義
	 String[] command;
	 String select;
	 String element;
		private JTextField tf;
		private Search search1;
		private static JPanel menu;
		private JTextField textfield1;
		private JTextField textfield2;
		private JPanel menu2;
		private int i;
		private int ii;
		private final String text1="フレーム｜";
		private final String text2="状態｜";
		private JPanel menu12;
		private String[] elements;
		private JPanel pane;
		private JPanel panel2_area;
		private JTextArea area1;
		private JTextArea area2;
		private int w;
		private int h;
		private Search search2;
	    Bigcommandchoice(String[] elements,String element,Search search1,JPanel pane,JPanel mennu,JPanel menuu2,int w,int h){
	      putValue( Action.NAME, element );
//	      putValue( Action.SMALL_ICON, new ImageIcon("open.gif"));
	      putValue( Action.SHORT_DESCRIPTION, "クリックで開く" );
	      this.elements=elements;
	      this.element=element;
	      this.search1=search1;
	      this.pane=pane;
	      menu=mennu;
	      menu2=menuu2;
	      this.w=w;
	      this.h=h;
	    }
	    public void actionPerformed( ActionEvent e ){ // ボタンがクリックされたときの処理
		    menu.setVisible(false);
		    pane.setLayout( new BorderLayout());
		    
		    int i=0;ii=0;
		    for(;ii<elements.length;ii++) {
		    	if(elements[ii].equals(element))break;
		    }
		    menu12=new JPanel();
		    menu12.setLayout(new BorderLayout());
		    menu12.setPreferredSize(new Dimension(w-8,h));
		    
		    command=new String[search1.getlist().get(ii).length];
		    for(;i<search1.getlist().get(ii).length;i++) {
		    	command[i]=search1.getlist().get(ii)[i][0];
		    }
		    JList<?> commandList=new JList<Object>(command);
		    commandList.addListSelectionListener( new MyListSelect() ); // リスナの設定 list.setLayoutOrientation( JList.VERTICAL ); // 縦一列
		    commandList.setVisibleRowCount( 10 );
		    JScrollPane scrC = new JScrollPane( commandList ); // スクロールできるようにする
		    JPanel panel=new JPanel();
		    panel.setLayout( new BoxLayout( panel, BoxLayout.X_AXIS ) );
		    tf=new JTextField();
		    tf.setBorder( new TitledBorder( "選択された項目" ) );
		    JButton button=new JButton(new ActCommand());
		    JButton button2=new JButton(new ret2());
		    scrC.setPreferredSize(new Dimension(Short.MAX_VALUE,Short.MAX_VALUE));
		    JPanel one=new JPanel();
		    JPanel two=new JPanel();
		    panel.add(tf);
		    panel.add(button);
		    menu12.add(one,BorderLayout.EAST);
		    menu12.add(two,BorderLayout.WEST);
		    menu12.add(button2,BorderLayout.NORTH);
		    menu12.add(scrC,BorderLayout.CENTER);
		    menu12.add(panel,BorderLayout.SOUTH);
		    pane.add(menu12,BorderLayout.WEST);
	    }
	    class MyListSelect implements ListSelectionListener { // リスナの定義
		    public void valueChanged( ListSelectionEvent e ){ // 項目の変化イベントが起こると呼び出される
		    	 JList<?> li = (JList<?>)e.getSource();
		    	 select = (String)li.getSelectedValue();
		      if( e.getValueIsAdjusting()==false ){ // 変更中のイベントか？
		        tf.setText( select  ); // テキストエリアに表示
		      }
		    }
		  }
	    class ActCommand extends AbstractAction{ // Action クラスの定義
	        ActCommand(){
	        	String word;
	        	if(w==650)word="開く";
	        	else word="計算する";
	        	putValue( Action.NAME, word );
       		 putValue( Action.SHORT_DESCRIPTION, "クリックで"+word );
//	          putValue( Action.SMALL_ICON, new ImageIcon( "open.gif" ) );
	        }
	        public void actionPerformed( ActionEvent e ){ // ボタンがクリックされたときの処理
	        	i=0;
	        	//選択したコマンド名から配列の中の位置を特定、位置を取得
		    	 for(;i<command.length;i++) {
		    		 if(command[i].equals(select))break;
		    	 }
		    	 //選択したコマンドが配列に無かったら選択しなおし
		    	 if(i<command.length) {
		    		 StringBuilder sb = new StringBuilder();
			    	 sb.append(search1.getlist().get(ii)[i][6]);
			    	 //備考が長すぎる場合改行を用いて文を整理
			    	if(search1.getlist().get(ii)[i][6]!=null&&search1.getlist().get(ii)[i][6].equals(null)==false) {
			    		for(int iii=1;(iii<(search1.getlist().get(ii)[i][6].length()/40));iii++){
				    		 sb.insert(iii*40,"\n");
				    	 }
			    	}
			    	String[] frame= {
			    		  search1.getlist().get(ii)[i][0],
			    		  "発生フレーム	:	"+search1.getlist().get(ii)[i][1],
			    		  "ガードフレーム	:	"+search1.getlist().get(ii)[i][2]+"F",
			    		  "ヒットフレーム	:	"+ search1.getlist().get(ii)[i][3]+"F",
			    		  "カウンターフレーム	:	"+search1.getlist().get(ii)[i][4]+"F",
			    		  "全体硬直	:	"+search1.getlist().get(ii)[i][5]+"F",
			    		  "備考	:	"+sb.toString()
			    		  };
			    	 if(w==650)JOptionPane.showMessageDialog( pane, frame );
			    	 else {
			    		 menu2.setVisible(false);
			    		 if(panel2_area!=null)panel2_area.setVisible(false);
			    		 panel2_area=new JPanel();
			    		 panel2_area.setLayout( new BorderLayout());
			    		 panel2_area.setPreferredSize(new Dimension(900-8,800));
			    		 
			    		 JPanel calpanel=new JPanel();
			    		 calpanel.setLayout( new BoxLayout( calpanel, BoxLayout.X_AXIS ) );
			    		 calpanel.setPreferredSize(new Dimension(900-5,800-40));
			    		 
			    		 JPanel northpanel=new JPanel();
			    		 northpanel.setLayout( new FlowLayout());
			    		 northpanel.setPreferredSize(new Dimension(900-8,80));
			    		 
			    		 JPanel buttonpanel=new JPanel();
			    		 buttonpanel.setLayout( new FlowLayout());
			    		 buttonpanel.setPreferredSize(new Dimension(900-8,40));
			    		 buttonpanel.add(new JButton (new cal("　ガード時　")));
			    		 buttonpanel.add(new JButton (new cal("　ヒット時　")));
			    		 buttonpanel.add(new JButton (new cal("カウンター時")));
			    		 
			    		 textfield1=new JTextField(text1);
			    		 textfield1.setPreferredSize(new Dimension(300,30));
			    		 textfield1.setEnabled(false);
			    		 
			    		 textfield2=new JTextField(text2);
			    		 textfield2.setPreferredSize(new Dimension(300,30));
			    		 textfield2.setEnabled(false);
			    		 
			    		 area1=new JTextArea((900-5-450),h-60);
			    		 area2=new JTextArea("",(900-5-450),h-60);
//			    		 area2.setPreferredSize(new Dimension(w-5,h-40));
			    		 
			    		 
			    		 area1.setFont(new Font("", Font.BOLD, 16));
			    		 area1.setEnabled(false);
			    		 area1.setBorder( new TitledBorder( "攻めるチャンス" ) );
			    		  
			    		 area2.setFont(new Font("", Font.BOLD, 16));
			    		 area2.setEnabled(false);
			    		 area2.setBorder( new TitledBorder( "攻めるリスク" ) );
//			    		 area2.setBackground(new Color(255,15,15));//setBackground//setForeground
			    		 
			    		 area1.append("ボタンを押して計算して下さい\n");
//			    		 area2.append("		"+search1.getlist().get(ii)[i][1]+"\n");
			    		 
//			    		 area1.setText("		"+search.getlist().get(ii)[i][1]);
			    		 JScrollPane scrarea1 = new JScrollPane( area1 );
			    		 JScrollPane scrarea2 = new JScrollPane( area2 );
			    		 
			    		 calpanel.add(scrarea1);
			    		 calpanel.add(scrarea2);
			    		 
			    		 northpanel.add(buttonpanel);
			    		 northpanel.add(textfield1);
			    		 northpanel.add(textfield2);
			    		 
			    		 panel2_area.add(northpanel,BorderLayout.NORTH);
			    		 panel2_area.add(calpanel,BorderLayout.CENTER);
			    		 
			    		 pane.add(panel2_area,BorderLayout.EAST);
			    		 
			    		 String charaName2;
				        	if(null!=(charaName2=new largecommand2().getcharaName2())) search2=new Search(charaName2);
				        	else {
				        		northpanel.setVisible(false);
				        		area2.setText("		相手キャラを選択してください");
				        	}
			    	 }
		    	 }else JOptionPane.showMessageDialog( pane, "項目を選択してください" );
	        }
	      }
	    class cal extends AbstractAction{ // Action クラスの定義
	        cal(String text){
	          putValue( Action.NAME, text );
//	          putValue( Action.SMALL_ICON, new ImageIcon( "open.gif" ) );
//	          putValue( Action.SHORT_DESCRIPTION, "クリックで戻る" );
	        }
	        public void actionPerformed( ActionEvent e ){ // ボタンがクリックされたときの処理
	        	JButton bu=(JButton)e.getSource();
	        	String ect = (String)bu.getActionCommand();
	        	area1.setText("");
	        	area2.setText("");
	        	textfield1.setText(text1);
	        	textfield2.setText(text2);
	        	String youu;
	        	int num=0;
	        	int meInt=-1;String mee=null;String tech;String meeall=null;
	        	
	        	
	        	
	        	if(ect.contains(tech="ガード時")){//割れない連携（青）+確定反撃（赤）+割り込まれる技（赤）
	        		mee=search1.getlist().get(ii)[i][2].replace("+","").replace("±", "");
	        	}
	        	else if(ect.contains(tech="ヒット時")) {//割れない連携+確定反撃（赤）+割り込まれる連携+コンボ始動（青）
	        		mee=search1.getlist().get(ii)[i][3].replace("+","").replace("±", "");	
	        	}
	        	else if(ect.contains(tech="カウンター時")) {//カウンターヒット時//割れない連携+確定反撃（赤）+割り込まれる連携+コンボ始動（青）
	        		mee=search1.getlist().get(ii)[i][4].replace("+","").replace("±", "");	
	        	}
	        	else {
	        		 JOptionPane.showMessageDialog( pane, "正しくボタンが選択されていません", "Error", JOptionPane.ERROR_MESSAGE );
	        		return;
	        	}
	        	
	        	if(mee.equals("")) {
	        		textfield1.setText(text1+"この技は"+tech+"のフレームが存在しません");
	        		return;
	        	}
	        	
	        	if(mee.contains("B")||mee.contains("Y")||mee.contains("A")||mee.contains("K")||mee.contains("G")||mee.contains("S")||mee.contains("D")) {
	        		if(mee.contains("B")) textfield2.setText(text2+"		"+tech+"：	背面取り状態");
	        		else if(mee.contains("Y")) textfield2.setText(text2+"		"+tech+"：	側面取り状態");
	        		else if(mee.contains("A")) textfield2.setText(text2+"		"+tech+"：	ハーフ浮きやられ始動");
	        		else if(mee.contains("K")) textfield2.setText(text2+"		"+tech+"：	きりもみやられ");
	        		else if(mee.contains("G")) textfield2.setText(text2+"		"+tech+"：	ガード可能有利状態");
	        		else if(mee.contains("S")) textfield2.setText(text2+"		"+tech+"：	強制しゃがみ状態");
	        		mee=mee.replace("B", "").replace("Y", "").replace("A", "").replace("K", "").replace("G", "").replace("S", "").replace("G", "");
	        	}
	        	if(mee.contains("浮")) {//byakgs
	        		textfield2.setText(text2+"	"+tech+"：	"+mee.replace("※", ""));//攻撃をされたときのフレーム
        			meInt=-10;
        		}
	        	else if(mee.contains("D")) {//byakgs
	        		textfield2.setText(text2+"	"+tech+"：	ダウン状態");
        			meInt=-10;
        		}
        		else {
        			meInt=Integer.parseInt(mee);
        			if(meInt<0) textfield1.setText(text1+"	"+tech+"：	"+mee);//攻撃をガードされたときのフレーム
	        		else textfield1.setText(text1+"	"+tech+"：	"+"+"+mee);//攻撃をガードされたときのフレーム
        		}
	        	
	        	
	        	//割れない連携area1
        		area1.append("発生フレーム"+(meInt+10)+"Fまでが割れない連携\n");//フレームの追記も
        		area1.append("該当する技は：");
        		if(meInt>=0) {
        			area1.append("あります\n\n");
        			for(int count=0;count<search1.getlist().size();count++) {
        				for(int count2=0;count2<search1.getlist().get(count).length;count2++) {
        					if((meeall=search1.getlist().get(count)[count2][1].replace("F","")).equals("")||(Integer.parseInt(meeall))>meInt+10)continue;
        					else {
        						if(num==count) {
        							area1.append("\n"+search1.elementslist()[num]+"\n");
        							num++;
        						}
        						area1.append(search1.getlist().get(count)[count2][0]+"	:"+meeall+"F\n");
        					}
        				}
        			}
        			
        			area1.append("\n");
        		}
        		else area1.append("ありません\n\n");
        		//コンボ始動area1
        		area1.append("\n\nこの技は"+tech+"コンボ始動");
        		if(mee.contains("浮")) {//　浮　が入っていたらコンボ始動と認識
        			area1.append("になります\n\n");
        		}
        		else area1.append("にはなりません\n\n");
        		//確定反撃//setTextColor(Color.parseColor("#FF00C0"))//area2
//        		line="この技は"+tech+"確定反撃が";
        		area2.append("この技は"+tech+"確定反撃が");
        		if(meInt>-10||(mee.contains("D")||mee.contains("浮"))) {
        			area2.append(("ありません\n\n"));
        		}
        		else {
        			area2.append("あります\n\n");
        			num=0;
        			for(int count=0;count<search2.getlist().size();count++) {
        				for(int count2=0;count2<search2.getlist().get(count).length;count2++) {
        					if((youu=search2.getlist().get(count)[count2][1].replace("F","").replace("-","")).equals("")||(Integer.parseInt(youu))>-meInt)continue;
        					else {
        						if(num==count) {
        							area2.append("\n"+search2.elementslist()[num]+"\n");
        							num++;
        						}
        						area2.append(search2.getlist().get(count)[count2][0]+"	:"+youu+"F\n");
        					}
        				}
        			}
        		}
        		//割り込まれる技//area2
        		area2.append("\n\nこの技は"+tech+"割り込まれる技が");
        		if(meInt>0||(mee.contains("D")||mee.contains("浮"))) {
        			area2.append("ありません\n\n");
        		}
        		else {
        			area2.append("あります\n\n");
        			num=0;
        			for(int count=0;count<search2.getlist().size();count++) {
        				for(int count2=0;count2<search2.getlist().get(count).length;count2++) {
        					if((youu=search2.getlist().get(count)[count2][1].replace("F","").replace("-","")).equals("")||(Integer.parseInt(youu))>-meInt+10)continue;
        					else {
        						if(num==count) {
        							area2.append("\n"+search2.elementslist()[num]+"\n");
        							num++;
        						}
        						area2.append(search2.getlist().get(count)[count2][0]+"	:"+youu+"F\n");
        					}
        				}
        			}
        		}
	        }
	    }
	    class ret2 extends AbstractAction{ // Action クラスの定義
	        ret2(){
	          putValue( Action.NAME, "コマンドの種類選択画面に戻る" );
	          putValue( Action.SMALL_ICON,new ImageIcon( "C:\\pleiades\\workspace\\Pokemon\\icon.png\\戻る.png" ));
	          putValue( Action.SHORT_DESCRIPTION, "クリックで戻る" );
	        }
	        public void actionPerformed( ActionEvent e ){ // ボタンがクリックされたときの処理
	        	menu12.setVisible(false);
	        		if(panel2_area!=null)panel2_area.setVisible(false);
	        		if(menu2!=null)menu2.setVisible(true);
	        	
	        	menu.setVisible(true);
	        }
	    }
}