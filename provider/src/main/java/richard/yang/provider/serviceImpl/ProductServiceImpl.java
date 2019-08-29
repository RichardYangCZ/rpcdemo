package richard.yang.provider.serviceImpl;

import richard.yang.po.Product;
import richard.yang.service.ProductService;

public class ProductServiceImpl implements ProductService {

    public Product getProduct(Long id) {
        Product product = new Product();
        product.setName("娃娃");
        product.setPrice(100);
        product.setId(id);

        return product;
    }
}
