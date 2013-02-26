semantics3-java
===============

Unofficial Semantics3 Java API

### EXAMPLES
##ProductsRequest

    ProductsRequest products = new ProductsRequest("KEY", "SECRET");
    products.setField("cat_id", 4992);
    products.setField("offset", 100);
    for (Map<String, Object> product : products.getProducts()) {
    	System.out.println(product.get("name"));
    }


## From semantics3-python

    CategoriesRequest categories = new CategoriesRequest(key, secret);
    categories.setField("cat_id", 4992);
    for (Map category : categories.getCategories()) {
    	System.out.println(category.get("name"));
    }


    //NESTED QUIERIES

    {
        "cat_id" : 4992, 
        "brand"  : "Toshiba",
        "weight" : { "gte":1000000, "lt":1500000 },
        "sitedetails" : {
            "name" : "newegg.com",
            "latestoffers" : {
                "currency": "USD",
                "price"   : { "gte" : 100 } 
            }
        }
    }


	import static semantics3.APIRequest.map;

	categories.setField(map(
		"cat_id", 4992,
		"brand", "Toshiba",
		"weight", map("gte", 1000000, "lt", 1500000),
		"sitedetails", map(
			"name", "newegg.com",
			"latestoffers", map(
				"currency", "USD",
				"price", map("gte", 100)
				)
			)
	));


	TODO : Offers