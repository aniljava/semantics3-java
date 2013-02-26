package semantics3;

import java.util.List;
import java.util.Map;

public class CategoriesRequest extends APIRequest {
	public CategoriesRequest(String api_key, String api_secret) {
		super(api_key, api_secret);
	}

	@Override
	public String getEndpoint() {
		return "categories";
	}

	public List<Map<String, Object>> getCategories() {
		if (result == null) super.request();

		if (!"OK".equals(result.get("code"))) {
			return null;
		} else {
			return (List<Map<String, Object>>) result.get("results");
		}
	}

}
