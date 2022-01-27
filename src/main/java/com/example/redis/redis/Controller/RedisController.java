package com.example.redis.redis.Controller;

import com.example.redis.redis.entity.Product;
import com.example.redis.redis.repository.ProductDuo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@EnableCaching
public class RedisController {
    @Autowired
    private ProductDuo duo;

    @PostMapping
    public Product save(@RequestBody Product product){
        return duo.save(product);
    }

    @GetMapping
    public List<Product> getAllProducts(){
        return duo.findAll();
    }

    @GetMapping("/{id}")
    @Cacheable(key = "#id", value = "Product")
    public Product findProductById(@PathVariable int id){
        return duo.findProductById(id);
    }

    @DeleteMapping("/{id}")
    @CacheEvict(key = "#id", value = "Product")
    public  String remove(@PathVariable int id){
        return duo.deleteProduct(id);
    }
}
