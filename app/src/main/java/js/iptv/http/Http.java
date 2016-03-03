package js.iptv.http;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import js.iptv.utils.Constants;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class Http {

	/*
	 * this class is single design pattern
	 */
	private static Http http = new Http();
	private static URI uri = null;
	private static Boolean status = false; // status is true only if the
											// instance http is Authenticated

	private Http() {
	};

	/**
	 * if the instance is not Authenticated, then should authenticate it first.
	 * if authenticate failed, then return null
	 * @return
	 */
	public static Http getInstance() {
		if (!status) {
			
			try {
				uri = new URI(Constants.Http.auth_url);
				String resultString = http.get(uri);
				if (resultString != null) {
					status = true;
				}
			} catch (URISyntaxException e) {
				e.printStackTrace();
				return null;
			}
		}
		return http;
	}

	/**
	 * the httpClient must be one and only one instances. because we must keep
	 * the session Consistent.
	 */
	private HttpClient httpClient = new DefaultHttpClient();

	/**
	 * http get method, return JsonArray or null if exception happened
	 * 
	 * @param uri
	 */
	public String get(URI uri) {
		System.out.println("---------eeee----"+uri);
		HttpGet httpGet = new HttpGet(uri);
		HttpResponse httpResponse = null;
		String resultString = null;
		try {
			httpResponse = httpClient.execute(httpGet);
			HttpEntity entity = httpResponse.getEntity();
			if (entity != null) {
				resultString = EntityUtils.toString(entity, "utf-8");
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("---------eeee222----"+resultString);
		return resultString;
	}
}
