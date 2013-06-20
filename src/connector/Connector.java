package connector;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.mainapp.mynoppaapp.DisplaySearchResultsActivity;
import com.mainapp.mynoppaapp.MainActivity;

public class Connector {
	private final String key = "key=cdda4ae4833c0114005de5b5c4371bb8";
	private final String host = "http://noppa-api-dev.aalto.fi/api/v1/";
	
	

	public Connector() {
		// examble url
		// http://noppa-api-dev.aalto.fi/api/v1/organizations?key=cdda4ae4833c0114005de5b5c4371bb8
	}

	public String getResults(String parameter) {
		final String url = host + "courses?" + key + "&search=" +parameter;
		String r = "";
		try {
			HttpClient client = new DefaultHttpClient();
			String getURL = url;
			HttpGet get = new HttpGet(getURL);
			HttpResponse responseGet;
			responseGet = client.execute(get);
			HttpEntity resEntityGet = responseGet.getEntity();
			if (resEntityGet != null) {
				// do something with the response
				r = EntityUtils.toString(resEntityGet);
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return r;
		
	}

	

}
