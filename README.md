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