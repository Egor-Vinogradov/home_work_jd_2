package by.it_academy.jd2.food_control.controller;

import by.it_academy.jd2.food_control.model.Product;
import by.it_academy.jd2.food_control.service.api.IProductService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Product> getAllProducts(/*@RequestParam(value = "page", required = false) long page,
                                        @RequestParam(value = "size", required = false) long size,
                                        @RequestParam(value = "name", required = false) String name,
                                        Model model*/) {
        List<Product> products = this.productService.getAllProducts();
        return products;
    }

    @CrossOrigin /* временное явление */
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addProduct(@RequestBody Product product, Model model) {
        Product productNew = this.productService.addProduct(product);
        if (productNew != null) {
            return new ResponseEntity<String>(product.toString(), HttpStatus.OK);
        } else {
            return new ResponseEntity<String>(HttpStatus.NOT_IMPLEMENTED);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> getProduct(@PathVariable("id") long id, Model model) {
        Product product = this.productService.getProduct(id);
        if(product != null) {
            return new ResponseEntity<String>(product.toString(), HttpStatus.OK);
        } else {
            return new ResponseEntity<String>(HttpStatus.NOT_IMPLEMENTED);
        }
    }

    @RequestMapping(value = "/{id}/dt_update/{dt_update}", method = RequestMethod.PUT)
    public ResponseEntity<String> putProduct(@RequestBody Product product,
                                             @PathVariable("id") long id,
                                             @PathVariable("dt_update") long version,
                                             Model model) {
        Product productUpdate = this.productService.putProduct(id, product, version);
        if (productUpdate != null) {
            return new ResponseEntity<String>(productUpdate.toString(), HttpStatus.OK);
        } else {
            return new ResponseEntity<String>(HttpStatus.NOT_IMPLEMENTED);
        }
    }

    @RequestMapping(value = "/{id}/dt_update/{dt_update}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteProduct(@PathVariable("id") long id,
                                                @PathVariable("dt_update") long version,
                                                Model model) {
        boolean result = this.productService.deleteProduct(id, version);
        if (result) {
            return new ResponseEntity<String>(HttpStatus.OK);
        } else {
            return new ResponseEntity<String>(HttpStatus.NOT_IMPLEMENTED);
        }
    }
}
