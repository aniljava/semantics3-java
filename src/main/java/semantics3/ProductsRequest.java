package semantics3;

import java.util.List;
import java.util.Map;

public class ProductsRequest extends APIRequest {

	@Override
	public String getEndpoint() {
		return "products";
	}

	public ProductsRequest(String api_key, String api_secret) {
		super(api_key, api_secret);
	}

	public Iterable<Map<String, Object>> getProducts() {
		if (result == null) super.request();

		if (!"OK".equals(result.get("code"))) {
			return null;
		} else {
			return (List<Map<String, Object>>) result.get("results");
		}
	}

}
