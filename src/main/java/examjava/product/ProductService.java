package examjava.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepo productRepo;

    @Autowired
    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public List<Product> getProducts() {
        return productRepo.findAll();
    }

    public Product getProductById(long id) {
        return productRepo.findById(id).orElse(null);
    }

    public Product saveProduct(ProductDto productDto){
        Product product = new Product(
          productDto.getProductName(),
          productDto.getDescription(),
          productDto.getPrice(),
          ProductStatus.IN_STOCK,
          productDto.getStock()


        );
        return productRepo.save(product);
    }

    public void deleteProductById(long id) {
        productRepo.deleteById(id);
    }
}
