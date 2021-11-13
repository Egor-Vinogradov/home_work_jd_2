package by.it_academy.jd2.food_control.controller;

import by.it_academy.jd2.food_control.model.Product;
import by.it_academy.jd2.food_control.model.search.SearchFilter;
import by.it_academy.jd2.food_control.service.api.IService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@CrossOrigin
public class ProductController {

    private final IService<Product, Long> productService;

    public ProductController(IService<Product, Long> productService) {
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Product>> getAllProducts(@RequestParam(value = "page", required = false) long page,
                                                        @RequestParam(value = "size", required = false) long size,
                                                        @RequestParam(value = "name", required = false) String name) {
        SearchFilter filter = new SearchFilter();
        filter.setName(name);
        filter.setSize(size);
        filter.setPage(page);

        List<Product> products = this.productService.findAll(filter);
        if (products != null) {
            return new ResponseEntity<>(products, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        Long id = this.productService.addItem(product);
        if (id != null) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getProduct(@PathVariable("id") Long id) {
        Product product = null;
        try {
            product = this.productService.findById(id);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value = "/{id}/dt_update/{dt_update}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateProduct(@RequestBody Product product,
                                           @PathVariable("id") Long id,
                                           @PathVariable("dt_update") Long version) {
        product.setVersion(version);
        Product productUpdate = null;
        try {
            productUpdate = this.productService.updateItem(id, product);
            return new ResponseEntity<>(productUpdate, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }
    }

    @RequestMapping(value = "/{id}/dt_update/{dt_update}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id,
                                           @PathVariable("dt_update") Long version) {
//        boolean result = this.productService.deleteId(id, version);
        if (this.productService.deleteId(id, version)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}

