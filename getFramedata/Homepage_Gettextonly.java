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

/** TEKKEN7の新着商品情報 */
public class Homepage_Gettextonly{
	private ArrayList<Pokemo> productList;
	private ArrayList<ArrayList<Pokemo>> alllist;
	private ArrayList<String> list;
	
	public Homepage_Gettextonly() {}
	public Homepage_Gettextonly(String TEKKEN7_HOMEPAGE) {
		alllist=new ArrayList<ArrayList<Pokemo>>();
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
			// 1つの新着商品に対応する li 要素のリストを得る
			NodeList itemList2 = (NodeList)xPath.evaluate("//h:div[@id='nocopy']/h:div[2]/h:table",
					document, XPathConstants.NODESET);
			System.out.println(itemList2.getLength());
			String value0=null;
			for(int ii=1;ii<=itemList2.getLength();ii++) {
				productList = new ArrayList<Pokemo>();
				NodeList itemList = (NodeList)xPath.evaluate("//h:div[@id='nocopy']/h:div[2]/h:table["+ii+"]/h:tbody/h:tr",
						document, XPathConstants.NODESET);
				for(int i = 0; i < itemList.getLength(); i++) {	
					Thread.sleep(1000);
					Node itemNode= itemList.item(i);
					if(xPath.evaluate("h:td["+1+"]", itemNode).replace("\n", "").contains("10連コンボ"))break;
					String value1= xPath.evaluate("h:td["+1+"]", itemNode).replace("\n", "");
					String value2= xPath.evaluate("h:td["+2+"]", itemNode).replace("\n", "");
					System.out.println(ii-1+":"+i+":"+value1+":"+value2+"\n\n");
					Pokemo poke=new Pokemo(value1,value2);
					if(i==0&&ii!=1) {
						value0=xPath.evaluate("h:td", itemNode);
						list.add(value0);
					}
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
	public ArrayList<Pokemo> getProductList() {
		return productList;
	}
	public ArrayList<ArrayList<Pokemo>> getalllist() {
		return alllist;
	}

}/** TEKKEN7の情報 */
class Pokemo {
	
	private String value1;
	private String value2;
	private ArrayList<String> value0;
	

	/**
	 * コンストラクタ
	 * @param title タイトル
	 * @param jURL URL
	 */
	public Pokemo(String value1,String value2) {
		this.value1 = value1;
		this.value2 = value2;
	}
	public String getvalue1() {
		return this.value1;
	}
	public String getvalue2() {
		return this.value2;
	}
	public String setvalue1(String value1) {
		return this.value1=value1;
	}
	
	/**
	 * TEKKEN7の文字列表現を返す
	 * @return TEKKEN7情報の文字列表現
	 */
}