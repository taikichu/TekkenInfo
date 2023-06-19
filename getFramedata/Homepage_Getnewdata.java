package getFramedata;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.TypeInfo;
import org.w3c.dom.UserDataHandler;
import org.xml.sax.InputSource;

import nu.validator.htmlparser.dom.HtmlDocumentBuilder;

/** TEKKEN7 */
public class Homepage_Getnewdata{
	private ArrayList<Pokemon> productList;
	private ArrayList<ArrayList<Pokemon>> alllist;
	private ArrayList<String> list;
	private static String namee;
	
	public Homepage_Getnewdata() {}
	public Homepage_Getnewdata(String TEKKEN7_HOMEPAGE) {
		alllist=new ArrayList<ArrayList<Pokemon>>();
		StringBuilder sb = new StringBuilder();
		list=new ArrayList<String>();if(list.size()==0||list==null) list.add("使わない");
		// TLS v1.2 の有効化 (Java 8 以降では指定不要)
		System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
		try {
			// URLオブジェクトを生成
			URL url = new URL(TEKKEN7_HOMEPAGE);
			// URLオブジェクトから、接続にいくURLConnectionオブジェクトを取得
			URLConnection connection = url.openConnection();
			// 接続
			connection.connect();
			// サーバからやってくるデータをInputStreamとして取得
			InputStream inputStream = connection.getInputStream();
			// 次に inputStream を読み込む InputStreamReader のインスタンス inputStreamReader を生成
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
			// さらに inputStreamReader をラップする BufferedReader のインスタンス reader を生成
			BufferedReader reader = new BufferedReader(inputStreamReader);

			// DOMツリーの構築
			HtmlDocumentBuilder builder = new HtmlDocumentBuilder();
			Document document = builder.parse(new InputSource(reader));

			// XPath の表現を扱う XPath オブジェクトを生成
			XPath xPath = XPathFactory.newInstance().newXPath();
			// XPath 式内で接頭辞 h がついている要素を HTML の要素として認識
			xPath.setNamespaceContext(new NamespaceContextHTML());
			
			//キャラの名前を取得する
			NodeList namelist = (NodeList)xPath.evaluate("//h:div[2]",
					document, XPathConstants.NODESET);
			System.out.println(namelist.getLength());
			
				Node nameNode= namelist.item(0);
				String name= xPath.evaluate("h:h2", nameNode).replace("\n", "");
				name=name.replace("：DATA","");
				System.out.println(name);
				namee=name;

			// 1つの新着商品に対応する li 要素のリストを得る
			NodeList itemList2 = (NodeList)xPath.evaluate("//h:div[2]/h:div[1]/h:div/h:table",
					document, XPathConstants.NODESET);
			System.out.println(itemList2.getLength());
			String value0=null;
			for(int ii=1;ii<=itemList2.getLength();ii++) {
				productList = new ArrayList<Pokemon>();
				NodeList itemList = (NodeList)xPath.evaluate("//h:div[2]/h:div[1]/h:div/h:table["+ii+"]/h:tbody/h:tr",
						document, XPathConstants.NODESET);
				for(int i = 0; i < itemList.getLength(); i++) {	
					Thread.sleep(1000);
					Node itemNode= itemList.item(i);
					if(xPath.evaluate("h:td["+1+"]", itemNode).replace("\n", "").contains("10連コンボ"))break;
					sb.setLength(0);
					
					String value1=xPath.evaluate("h:td["+1+"]", itemNode).replace("\n", "、");
					
					String value2= xPath.evaluate("h:td["+2+"]", itemNode).replace("\n", "");
					String value3= xPath.evaluate("h:td["+3+"]", itemNode).replace("\n", "");
					String value4= xPath.evaluate("h:td["+4+"]", itemNode).replace("\n", "");

					String value5= xPath.evaluate("h:td["+5+"]", itemNode).replace("\n", "");

					String value6= xPath.evaluate("h:td["+6+"]", itemNode).replace("\n", "");
					String value7= xPath.evaluate("h:td["+7+"]", itemNode).replace("\n", "");
					System.out.println(ii-1+":"+i+":"+value1+":"+value2+":"+value3+":"+value4+":"+value5+":"+value6+":"+value7+"\n\n");//System.out.println("value4151"+value41+":"+value51);
					if(i==0&&ii!=1) {
						value0=xPath.evaluate("h:td", itemNode);
						list.add(value0);
					}
					if(value1.contains("技名")&&value2.contains("発生")) continue;
					else if(value2.equals("")&&value3.equals("")&&value4.equals("")&&value5.equals("")&&value6.equals("")&&value2.equals("")&&value7.equals("")) continue;
					Pokemon poke=new Pokemon(value1,value2,value3,value4,value5,value6,value7);
					productList.add(poke);
				}
				alllist.add(productList);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public ArrayList<String> getlist(){
		return list;
	}
	public ArrayList<Pokemon> getProductList() {
		return productList;
	}
	public ArrayList<ArrayList<Pokemon>> getalllist() {
		return alllist;
	}
	public String getname() {
		return namee;
	}
}/** TEKKEN7の情報 */
class Pokemon {
	
	private String value1;
	private String value2;
	private String value3;
	private String value4;
	private String value5;
	private String value6;
	private String value7;
	private ArrayList<String> value0;
	

	/**
	 * コンストラクタ
	 * @param title タイトル
	 * @param jURL URL
	 */
	public Pokemon(String value1,String value2,String value3,String value4,String value5,String value6,String value7) {
		this.value1 = value1;
		this.value2 = value2;
		this.value3 = value3;
		this.value4 = value4;
		this.value5 = value5;
		this.value6 = value6;
		this.value7 = value7;
	}
	public String getvalue1() {
		return this.value1;
	}
	public String getvalue2() {
		return this.value2;
	}
	public String getvalue3() {
		return this.value3;
	}
	public String getvalue4() {
		return this.value4;
	}
	public String getvalue5() {
		return this.value5;
	}
	public String getvalue6() {
		return this.value6;
	}
	public String getvalue7() {
		return this.value7;
	}
	public String setvalue1(String value1) {
		return this.value1=value1;
	}
	
	/**
	 * TEKKEN7の文字列表現を返す
	 * @return TEKKEN7情報の文字列表現
	 */
}