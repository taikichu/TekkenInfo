package getFramedata;


import java.io.*;
//キャラ名を入力し、フレーム情報を取得する。
public class FilemakeT2 {
    public static void main(String[] args) {
    	//英語名を小文字で入力
    	String[] name={
    			"lidia","kunimitsu","fahkumram","leroy","ganryu","zafina","geese","noctis","lei",
    			"anna","marduk",
    			"amking","julia","gouki","nina","bob","raven",
    			"eliza","eddy","miguel",
    			"lee","kuma","claudio",
    			"katarina","lucky","shaheen","josie","gigas","kazumi","kazuya",
    			"heihachi","lars","asuka","lili","alisa","xiaoyu","feng","paul","law","steve","leo","hwoarang","king",
    			"dragunov","bryan","jin","devil","yoshimitsu","jack7"};
    	for(int i=0;i<name.length;i++) {
        	Homepage_Getnewdata data=new Homepage_Getnewdata("http://geppopotamus.info/game/tekken7fr/"+name[i]+"/data.htm");
        	String getname=data.getname();
        	getname=getname.replace("/","_");//kuma/panda専用
            try {
                PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("\\pleiades\\workspace\\Pokemon\\TEKKEN7.json\\"+getname+".json")));
                writer.println("[\n	{");
                String value0;
                for(int ii=0;ii<data.getlist().size();ii++) {
                	if((value0=data.getlist().get(ii)).equals("使わない")) {
                		writer.print("		\""+(ii+1)+"\":\"一般コマンド\"");
                	}else {
            		writer.print("		\""+(ii+1)+"\":\""+value0+"\"");
                	}
                	if(ii+1<data.getlist().size()) writer.println(",");
                }
                writer.println("\n	},\n	{");
                for(int ii=0;ii<data.getlist().size();ii++) {
                	if((value0=data.getlist().get(ii)).equals("使わない")) {
                		writer.println("		\"一般コマンド\":{");
                		}
                	else {
                		writer.println("		\""+value0+"\":{");
                		}
                	for(int o=0;o<data.getalllist().get(ii).size();o++) {
                    	if(value0.contains("投げ")) {
                    		writer.println("			\""+(o+1)+"\":{");
                    		writer.println("				"+"\""+(o+1)+"\":\""+data.getalllist().get(ii).get(o).getvalue1()+"\",");
                        	writer.println("				"+"\"発生\":\""+data.getalllist().get(ii).get(o).getvalue2()+"\",");
                        	writer.println("				"+"\"投げ後の相手位置\":\""+data.getalllist().get(ii).get(o).getvalue3()+"\",");
                        	writer.println("				"+"\"投げ抜け\":\""+data.getalllist().get(ii).get(o).getvalue4()+"\",");
                        	writer.println("				"+"\"全体硬直\":\""+data.getalllist().get(ii).get(o).getvalue5()+"\",");
                        	writer.println("				"+"\"備考\":\""+data.getalllist().get(ii).get(o).getvalue6()+"\"");
                        	writer.print("			}");
                    	}
                    	else {
                    		writer.println("			\""+(o+1)+"\":{");
                    		writer.println("				"+"\""+(o+1)+"\":\""+data.getalllist().get(ii).get(o).getvalue1()+"\",");
                        	writer.println("				"+"\"発生\":\""+data.getalllist().get(ii).get(o).getvalue2()+"\",");
                        	writer.println("				"+"\"Grd\":\""+data.getalllist().get(ii).get(o).getvalue3()+"\",");
                        	writer.println("				"+"\"NH\":\""+data.getalllist().get(ii).get(o).getvalue4()+"\",");
                        	writer.println("				"+"\"CH\":\""+data.getalllist().get(ii).get(o).getvalue5()+"\",");
                        	writer.println("				"+"\"全体硬直\":\""+data.getalllist().get(ii).get(o).getvalue6()+"\",");
                        	writer.println("				"+"\"備考\":\""+data.getalllist().get(ii).get(o).getvalue7()+"\"");
                        	writer.print("			}");
                    	}
                    	if((o+1)<data.getalllist().get(ii).size()) writer.println(",");
                    }
                	writer.print("\n		}");
                	if((ii+1)==data.getlist().size()) writer.println("\n	},\n	{\n		\"NAME\":\""+name[i]+"\"\n	}\n]");
                	else writer.println(",");
                }
                writer.close();
            } catch (IOException e) {
                System.out.println(e);
            }
            try {
    			PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("\\pleiades\\workspace\\Pokemon\\TEKKEN7.txt\\"+getname+".txt")));
    		String value0;
    		for(int ii=0;ii<data.getlist().size();ii++) {
    			if((value0=data.getlist().get(ii)).equals("使わない"))writer.println("一般コマンド");
    			else writer.println(value0);
    			for(int iii=1;iii<data.getalllist().get(ii).size();iii++) {
    				writer.print(data.getalllist().get(ii).get(iii).getvalue1());
    				if((iii+1)<data.getalllist().get(ii).size()) writer.print("、");
    			}
    			writer.println("\n");
    		}
    		writer.close();
    		} catch (IOException e) {
    		System.out.println(e);
    		}
    	}
    }
}