package getFramedata;

import java.io.*;
//キャラ名を入力し、フレーム情報を取得する。
public class FilemakeJT {
    public static void main(String[] args) {
    	//英語名を小文字で入力
    	String name="bob";
    	Homepage_Getnewdata data=new Homepage_Getnewdata("https://geppopotamus.info/game/tekken7fr/"+name+"/data.htm");
    	String getname=data.getname();
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
            	for(int i=0;i<data.getalllist().get(ii).size();i++) {
                	if(value0.contains("投げ")) {
                		writer.println("			\""+(i+1)+"\":{");
                		writer.println("				"+"\""+(i+1)+"\":\""+data.getalllist().get(ii).get(i).getvalue1()+"\",");
                    	writer.println("				"+"\"発生\":\""+data.getalllist().get(ii).get(i).getvalue2()+"\",");
                    	writer.println("				"+"\"投げ後の相手位置\":\""+data.getalllist().get(ii).get(i).getvalue3()+"\",");
                    	writer.println("				"+"\"投げ抜け\":\""+data.getalllist().get(ii).get(i).getvalue4()+"\",");
                    	writer.println("				"+"\"全体硬直\":\""+data.getalllist().get(ii).get(i).getvalue5()+"\",");
                    	writer.println("				"+"\"備考\":\""+data.getalllist().get(ii).get(i).getvalue6()+"\"");
                    	writer.print("			}");
                	}
                	else {
                		writer.println("			\""+(i+1)+"\":{");
                		writer.println("				"+"\""+(i+1)+"\":\""+data.getalllist().get(ii).get(i).getvalue1()+"\",");
                    	writer.println("				"+"\"発生\":\""+data.getalllist().get(ii).get(i).getvalue2()+"\",");
                    	writer.println("				"+"\"Grd\":\""+data.getalllist().get(ii).get(i).getvalue3()+"\",");
                    	writer.println("				"+"\"NH\":\""+data.getalllist().get(ii).get(i).getvalue4()+"\",");
                    	writer.println("				"+"\"CH\":\""+data.getalllist().get(ii).get(i).getvalue5()+"\",");
                    	writer.println("				"+"\"全体硬直\":\""+data.getalllist().get(ii).get(i).getvalue6()+"\",");
                    	writer.println("				"+"\"備考\":\""+data.getalllist().get(ii).get(i).getvalue7()+"\"");
                    	writer.print("			}");
                	}
                	if((i+1)<data.getalllist().get(ii).size()) writer.println(",");
                }
            	writer.print("\n		}");
            	if((ii+1)==data.getlist().size()) writer.println("\n	},\n	{\n		\"NAME\":\""+name+"\"\n	}\n]");
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
			for(int i=1;i<data.getalllist().get(ii).size();i++) {
				writer.print(data.getalllist().get(ii).get(i).getvalue1());
				if((i+1)<data.getalllist().get(ii).size()) writer.print("、");
			}
			writer.println("\n");
		}
		writer.close();
		} catch (IOException e) {
		System.out.println(e);
		}
    }
}