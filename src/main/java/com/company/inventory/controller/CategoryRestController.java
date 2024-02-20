package com.company.inventory.controller;

import com.company.inventory.model.Category;
import com.company.inventory.response.CategoryResponseRest;
import com.company.inventory.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class CategoryRestController {
    @Autowired
    private ICategoryService service;

    /**
     * Get all the categories
     * @return response
     * */
    @GetMapping("/categories")
    public ResponseEntity<CategoryResponseRest> serchCategories(){
        ResponseEntity<CategoryResponseRest> response = service.search();
        return response;
    }
    /**
     * Get category by id
     * @param id
     * @return response
     * */
    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryResponseRest> serchCategoriesById(@PathVariable Long id){
        ResponseEntity<CategoryResponseRest> response = service.searchById(id);
        return response;
    }
    /**
     * Save categogy
     * @param category
     * @return response
     * */
    @PostMapping("/categories")
    public ResponseEntity<CategoryResponseRest> save(@RequestBody Category category){
        ResponseEntity<CategoryResponseRest> response = service.save(category);
        return response;
    }
    /**
     * Update categogy
     * @param category
     * @param id
     * @return response
     * */
    @PutMapping("/categories/{id}")
    public ResponseEntity<CategoryResponseRest> update(@RequestBody Category category,@PathVariable Long id){
        ResponseEntity<CategoryResponseRest> response = service.update(category, id);
        return response;
    }
    /**
     * Delete categogy
     * @param id
     * @return response
     * */
    @DeleteMapping("/categories/{id}")
    public ResponseEntity<CategoryResponseRest> delete(@PathVariable Long id){
        ResponseEntity<CategoryResponseRest> response = service.deleteById(id);
        return response;
    }



}
