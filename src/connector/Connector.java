package connector;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
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

	public String getResults(String parameter, final DisplaySearchResultsActivity d) {
		final String url = host + "courses?" + key + "&search=" +parameter;
		final String r = "";
		new Thread(new Runnable() {
			public void run() {
				try {

					HttpClient client = new DefaultHttpClient();
					String getURL = url;
					HttpGet get = new HttpGet(getURL);
					HttpResponse responseGet = client.execute(get);
					HttpEntity resEntityGet = responseGet.getEntity();
					if (resEntityGet != null) {
						// do something with the response
						String r = EntityUtils.toString(resEntityGet);
						
					}
				} catch (Exception e) {
					e.printStackTrace();

				}
			}
		}).start();

		return r;
	}

	

}
