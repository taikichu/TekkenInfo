package tekken7;
//半角、全角で文字の間隔が変わる。全てを統一すればレイアウトをもっと綺麗にできる？
//最大文字数を計算、もしくは途中で保持し体裁を最大文字数に合わせて設定すればレイアウトを改善できる？
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
public class Search{
    private String[] elements;
    private ArrayList<String[][]> list;
    private String name;
	public Search(String charaName) {	
		try {
			String line=null;
            ObjectMapper mapper = new ObjectMapper();//Jsonファイルを使うためのObjectMapperを定義
            JsonNode search = mapper.readTree(new File("C:\\pleiades\\workspace\\Pokemon\\TEKKEN7.json\\"+charaName+".json"));//JsonNode型でpokemon.jsonを読み込み、使えるようにする
            list=new ArrayList<String[][]>();
    		elements=new String[search.get(0).size()];
    		for(int i=1;i<=search.get(0).size();i++) {
    			elements[i-1]=search.get(0).get(i+"").asText();
    		}
            for(int count=0;count<search.get(1).size();count++) {
            	String[][][] listsub=new String[20][search.get(1).get(elements[count]).size()][7];
            		for(int count2=1;count2<=search.get(1).get(elements[count]).size();count2++) {
            			if(elements[count].equals("投げ")) {
            				
            				line=search.get(1).get(elements[count]).get(count2+"").get(count2+"").asText();
            				if(line.split("、").length>2) {
            					
            					if(line.split("、")[0].length()<=6) {
            						listsub[count][count2-1][0]=line.split("、")[0]+"	"+line.split("、")[line.split("、").length-2]+"	"+line.split("、")[line.split("、").length-1];
            					}
            					else if (line.split("、")[0].length()<=12){
            						listsub[count][count2-1][0]=line.split("、")[0]+line.split("、")[line.split("、").length-2]+"	"+line.split("、")[line.split("、").length-1];
            					}
            					else {
            						listsub[count][count2-1][0]=line.split("、")[0]+line.split("、")[line.split("、").length-2]+line.split("、")[line.split("、").length-1];
            					}
            					
            				}else {
            					listsub[count][count2-1][0]=line;
            				}
            				
            				listsub[count][count2-1][1]=search.get(1).get(elements[count]).get(count2+"").get("発生").asText();
            				listsub[count][count2-1][2]=search.get(1).get(elements[count]).get(count2+"").get("投げ後の相手位置").asText();
            				listsub[count][count2-1][3]=search.get(1).get(elements[count]).get(count2+"").get("投げ抜け").asText();
            				listsub[count][count2-1][4]=search.get(1).get(elements[count]).get(count2+"").get("全体硬直").asText();
            				listsub[count][count2-1][5]=search.get(1).get(elements[count]).get(count2+"").get("備考").asText();
            				listsub[count][count2-1][6]=null;
            			}
            			else {
            				line=search.get(1).get(elements[count]).get(count2+"").get(count2+"").asText();
            				if(line.split("、").length>2) {
            					if(line.split("、")[0].length()<=6) {
            						listsub[count][count2-1][0]=line.split("、")[0]+"	"+line.split("、")[line.split("、").length-2]+"	"+line.split("、")[line.split("、").length-1];
            					}
            					else if (line.split("、")[0].length()<=12){
            						listsub[count][count2-1][0]=line.split("、")[0]+line.split("、")[line.split("、").length-2]+"	"+line.split("、")[line.split("、").length-1];
            					}
            					else {
            						listsub[count][count2-1][0]=line.split("、")[0]+line.split("、")[line.split("、").length-2]+line.split("、")[line.split("、").length-1];
            					}
            				}
            				else {
            					listsub[count][count2-1][0]=line;
            				}
            				
            				listsub[count][count2-1][1]=search.get(1).get(elements[count]).get(count2+"").get("発生").asText();
            				listsub[count][count2-1][2]=search.get(1).get(elements[count]).get(count2+"").get("Grd").asText();
            				listsub[count][count2-1][3]=search.get(1).get(elements[count]).get(count2+"").get("NH").asText();
            				listsub[count][count2-1][4]=search.get(1).get(elements[count]).get(count2+"").get("CH").asText();
            				listsub[count][count2-1][5]=search.get(1).get(elements[count]).get(count2+"").get("全体硬直").asText();
            				listsub[count][count2-1][6]=search.get(1).get(elements[count]).get(count2+"").get("備考").asText();
            			}
                	}
            	list.add(listsub[count]);
            }
            name=search.get(2).get("NAME").asText();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
	}
	public String[] elementslist() {
		return elements;
	}
	public ArrayList<String[][]> getlist(){
		return list;
	}
	public String getName() {
		return name;
	}
	class charaSmallList{
		private String no1;
		private String no2;
		private String no3;
		private String no4;
		private String no5;
		private String no6;
		private String no7;
		public charaSmallList(String no1,String no2,String no3,String no4,String no5,String no6,String no7) {
			this.no1=no1;
			this.no2=no2;
			this.no3=no3;
			this.no4=no4;
			this.no5=no5;
			this.no6=no6;
			this.no7=no7;
		}
		public String getno1() {
			return no1;
		}
		public String getno2() {
			return no2;
		}
		public String getno3() {
			return no3;
		}
		public String getno4() {
			return no4;
		}
		public String getno5() {
			return no5;
		}
		public String getno6() {
			return no6;
		}
		public String getno7() {
			return no7;
		}
	}
}
