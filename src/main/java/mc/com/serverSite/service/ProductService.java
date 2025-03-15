package mc.com.serverSite.service;

import mc.com.serverSite.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    public Page<Product> getAllProducts() {
        return Page.empty();
    }

    public Product getProductById(Long id) {
        return null;
    }

}
