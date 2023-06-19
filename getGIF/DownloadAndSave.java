package getGIF;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class DownloadAndSave {
	static int count=0;
  public  DownloadAndSave(String urll,String charaName) {
	    try {
	        URL url = new URL(urll);
	        HttpURLConnection conn =
	            (HttpURLConnection) url.openConnection();
	        conn.setAllowUserInteraction(false);
	        conn.setInstanceFollowRedirects(true);
	        conn.setRequestMethod("GET");
	        conn.connect();
	        int httpStatusCode = conn.getResponseCode();
	        if (httpStatusCode != HttpURLConnection.HTTP_OK) {
	          throw new Exception("HTTP Status " + httpStatusCode);
	        }

	        String contentType = conn.getContentType();
	        System.out.println("Content-Type: " + contentType+":"+count+urll);

	        // Input Stream
	        DataInputStream dataInStream
	            = new DataInputStream(
	            conn.getInputStream());

	        count++;
	        // Output Stream
	        DataOutputStream dataOutStream
	            = new DataOutputStream(
	            new BufferedOutputStream(
	                new FileOutputStream("C:\\pleiades\\workspace\\Pokemon\\TEKKEN7.png\\"+charaName+".png")));

	        // Read Data
	        byte[] b = new byte[4096];
	        int readByte = 0;

	        while (-1 != (readByte = dataInStream.read(b))) {
	          dataOutStream.write(b, 0, readByte);
	        }

	        // Close Stream
	        dataInStream.close();
	        dataOutStream.close();

	      } catch (FileNotFoundException e) {
	        e.printStackTrace();
	      } catch (ProtocolException e) {
	        e.printStackTrace();
	      } catch (MalformedURLException e) {
	        e.printStackTrace();
	      } catch (IOException e) {
	        e.printStackTrace();
	      } catch (Exception e) {
	        System.out.println(e.getMessage()+":"+count+urll);
	        e.printStackTrace();
	      }
  }
}