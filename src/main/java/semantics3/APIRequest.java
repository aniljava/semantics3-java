package semantics3;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;

import org.codehaus.jackson.map.ObjectMapper;

public abstract class APIRequest {

	protected OAuthConsumer			oauth		= null;
	protected static ObjectMapper	JSON_MAPPER	= new ObjectMapper();

	protected Map<String, Object>	fields		= new HashMap<String, Object>();

	public abstract String getEndpoint();
	protected Map<String, Object>	result	= null;
	
	public void request() {
		try {
			String raw = request(getEndpoint(), fields);
			result = JSON_MAPPER.readValue(raw, Map.class);
		} catch (Exception ex) {
			throw new Semantics3Exception(ex);
		}
	}

	public void setField(String name, Object... value) {
		if (value == null) {
			this.fields.put(name, null);
		} else if (value.length == 1) {
			this.fields.put(name, value[0]);
		} else {
			this.fields.put(name, value);
		}
	}

	public APIRequest(String api_key, String api_secret) {
		if (api_key == null) throw new NullPointerException("API Key Missing");
		if (api_secret == null) throw new NullPointerException("API Secret Missing");
		oauth = new DefaultOAuthConsumer(api_key, api_secret);
	}

	public String request(String endPoint, Map<String, Object> params) throws Semantics3Exception {
		try {
			String query = JSON_MAPPER.writeValueAsString(params);
			return request(endPoint, query);
		} catch (Exception ex) {
			throw new Semantics3Exception(ex);
		}
	}

	public String request(String endpoint, String params) throws Semantics3Exception {
		try {
			URL url = new URL("https://api.semantics3.com/v1/" + endpoint + "?q=" + URLEncoder.encode(params, "utf-8"));
			URLConnection connection = (HttpURLConnection) url.openConnection();

			oauth.sign(connection);
			connection.connect();
			InputStream in = connection.getInputStream();

			StringBuffer buffer = new StringBuffer();

			byte data[] = new byte[1024];
			int lastRead = 0;
			while ((lastRead = in.read(data)) != -1) {
				buffer.append(new String(data, 0, lastRead, "utf-8"));
			}

			in.close();
			return buffer.toString();
		} catch (Exception ex) {
			throw new Semantics3Exception(ex);
		}
	}
	
	

}
