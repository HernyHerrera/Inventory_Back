package com.company.inventory.services;

import com.company.inventory.dao.ICategoryDao;
import com.company.inventory.model.Category;
import com.company.inventory.response.CategoryResponseRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements ICategoryService{
    @Autowired
    private ICategoryDao categoryDao;
    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoryResponseRest> search() {
        CategoryResponseRest response = new CategoryResponseRest();
        try{
            List<Category> category = (List<Category>) categoryDao.findAll();
            response.getCategoryResponse().setCategory(category);
            response.setMetadata("Respuesta OK", "00", "Respuesta exitosa");

        }catch (Exception exception){
            response.setMetadata("Respuesta Error", "-1", "Error en consulta");
            exception.getStackTrace();
            return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoryResponseRest> searchById(Long id) {
        CategoryResponseRest response = new CategoryResponseRest();
        List<Category> list = new ArrayList<>();
        try{
            Optional<Category> category = categoryDao.findById(id);
            if (category.isPresent()){
                list.add(category.get());
                response.setMetadata("Respuesta OK", "00", "Categoria encontrada");
                response.getCategoryResponse().setCategory(list);
            }else{
                response.setMetadata("Respuesta Error", "-1", "Error en consulta por Id");
                return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.NOT_FOUND);

            }
        }catch (Exception exception){
            response.setMetadata("Respuesta Error", "-1", "Error en consulta");
            exception.getStackTrace();
            return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<CategoryResponseRest> save(Category category) {
        CategoryResponseRest response = new CategoryResponseRest();
        List<Category> list = new ArrayList<>();
        try{
            Category categorySaved = categoryDao.save(category);
            if(categorySaved != null){
                list.add(categorySaved);
                response.getCategoryResponse().setCategory(list);
                response.setMetadata("Respuesta OK", "00", "Categoria guardada");
            }else {
                response.setMetadata("Respuesta Error", "-1", "Categoria no guardada");
                return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.BAD_REQUEST);
            }

        }catch (Exception exception){
            response.setMetadata("Respuesta Error", "-1", "Error al grabar categoria");
            exception.getStackTrace();
            return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<CategoryResponseRest> update(Category category, Long id) {
        CategoryResponseRest response = new CategoryResponseRest();
        List<Category> list = new ArrayList<>();
        try{
            Optional<Category> categorySerch = categoryDao.findById(id);
            if(categorySerch.isPresent()){
                // se procedera a actualizar el registro
                categorySerch.get().setName(category.getName());
                categorySerch.get().setDescription(category.getDescription());
                Category categoryToUpdate = categoryDao.save(categorySerch.get());
                if(categoryToUpdate != null){
                    list.add(categoryToUpdate);
                    response.getCategoryResponse().setCategory(list);
                    response.setMetadata("Respuesta OK", "00", "Categoria actualizada");
                }else{
                    response.setMetadata("Respuesta Error", "-1", "Categoria no actualizada");
                    return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.BAD_REQUEST);
                }
            }else{
                //no se encontro el registro
                response.setMetadata("Respuesta Error", "-1", "Categoria no encontrada");
                return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.NOT_FOUND);
            }

        }catch (Exception exception){
            response.setMetadata("Respuesta Error", "-1", "Error al actualizar categoria");
            exception.getStackTrace();
            return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<CategoryResponseRest> deleteById(Long id) {
        CategoryResponseRest response = new CategoryResponseRest();
        try{
            categoryDao.deleteById(id);
            response.setMetadata("respuesta ok","00","Registro Eliminado");

        }catch (Exception exception){
            response.setMetadata("Respuesta Error", "-1", "No se encontro registro");
            exception.getStackTrace();
            return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
    }
}
