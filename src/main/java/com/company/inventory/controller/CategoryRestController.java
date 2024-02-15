package com.company.inventory.controller;

import com.company.inventory.response.CategoryResponseRest;
import com.company.inventory.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
