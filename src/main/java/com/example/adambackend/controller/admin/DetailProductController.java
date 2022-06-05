package com.example.adambackend.controller.admin;

import com.example.adambackend.entities.DetailProduct;
import com.example.adambackend.entities.Product;
import com.example.adambackend.exception.HandleExceptionDemo;
import com.example.adambackend.payload.response.IGenericResponse;
import com.example.adambackend.service.DetailProductService;
import com.example.adambackend.service.ProductSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("admin/detailProduct")
public class DetailProductController {
    @Autowired
    DetailProductService detailProductService;
    @Autowired
    ProductSevice productSevice;
    @PostMapping("create")
    public ResponseEntity<?> createDetailProduct(@RequestParam("product_id")Long productId, @RequestBody DetailProduct detailProduct){
        Optional<Product> product= productSevice.findById(productId);
        if(product.isPresent()){
            detailProductService.save(detailProduct);
            List<DetailProduct> detailProducts= product.get().getDetailProducts();
            detailProducts.add(detailProduct);
            product.get().setDetailProducts(detailProducts);
            productSevice.save(product.get());
            detailProduct.setProduct(product.get());
            return  ResponseEntity.ok().body(new IGenericResponse<DetailProduct>(detailProductService.save(detailProduct),200,"success"));
        }
        return ResponseEntity.badRequest().body(new HandleExceptionDemo(400, "not found product"));

    }
    @PutMapping("update")
    public ResponseEntity<?> updateDetailProduct(@RequestParam("product_id")Long productId, @RequestBody DetailProduct detailProduct){
        Optional<Product> product= productSevice.findById(productId);
        if(product.isPresent()){
        List<DetailProduct> detailProducts= product.get().getDetailProducts();
        for (DetailProduct detailProduct1: detailProducts
             ) {
            if(detailProduct1.equals(detailProduct)){
                detailProductService.save(detailProduct);
                detailProducts.add(detailProduct);
                product.get().setDetailProducts(detailProducts);
                productSevice.save(product.get());
                detailProduct.setProduct(product.get());
                return  ResponseEntity.ok().body(new IGenericResponse<DetailProduct>(detailProductService.save(detailProduct),200,"success"));
            }
        }


        }
        return ResponseEntity.badRequest().body(new HandleExceptionDemo(400, "not found"));

    }
    @DeleteMapping("delete")
    public ResponseEntity<?> deleteDetailProduct(@RequestParam("product_id")Long productId,@RequestParam("detail_product_id")Long detailProductId) {
        Optional<Product> product = productSevice.findById(productId);
        Optional<DetailProduct> detailProduct = detailProductService.findById(detailProductId);
        if (product.isPresent() && detailProduct.isPresent()) {
            List<DetailProduct> detailProducts = product.get().getDetailProducts();
            for (DetailProduct detailProduct1 : detailProducts
            ) {
                if (detailProduct1.equals(detailProduct.get())) {
                    detailProducts.remove(detailProduct1);
                    product.get().setDetailProducts(detailProducts);
                    productSevice.save(product.get());
                    return ResponseEntity.badRequest().body(new IGenericResponse<>(productSevice.save(product.get()), 200, "success"));
                }
            }
            return ResponseEntity.badRequest().body(new HandleExceptionDemo(400, "not found"));
        }
        return ResponseEntity.badRequest().body(new HandleExceptionDemo(400, "not found"));
    }

}