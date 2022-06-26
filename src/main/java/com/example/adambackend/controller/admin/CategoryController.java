package com.example.adambackend.controller.admin;

import com.example.adambackend.entities.Category;
import com.example.adambackend.exception.HandleExceptionDemo;
import com.example.adambackend.payload.CategoryDTO;
import com.example.adambackend.payload.CategoryResponse;
import com.example.adambackend.payload.response.IGenericResponse;
import com.example.adambackend.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("admin/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ModelMapper modelMapper;

    @PostMapping("create")
    public ResponseEntity<?> createCategory(@RequestBody CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setCategoryName(categoryDTO.getCategoryName());
        if(categoryDTO.getCategoryParentId()==0) {
            category.setCategoryParentId(null);
        }
            category.setCategoryParentId(categoryDTO.getCategoryParentId());

        category.setIsDeleted(false);
        category.setCreateDate(LocalDateTime.now());
        category.setIsActive(true);
        return ResponseEntity.ok().body(new IGenericResponse<Category>(categoryService.save(category), 200, ""));

    }

    @GetMapping("findAllCategoryParentId")
    public ResponseEntity<IGenericResponse> findAllCategoryParentId() {
        List<CategoryResponse> categoryResponseList = new ArrayList<>();
        List<Category> categories = categoryService.findAllCategoryParentId();
        for (Category category : categories
        ) {
            CategoryResponse categoryResponse = new CategoryResponse();
            categoryResponse.setCategoryParentId(category.getCategoryParentId());
            categoryResponse.setId(category.getId());
            categoryResponse.setCategoryName(category.getCategoryName());
            categoryResponse.setIsDeleted(category.getIsDeleted());
            categoryResponse.setCategoryChildren(categoryService.findByCategoryParentId(category.getId()));
            categoryResponseList.add(categoryResponse);
            categoryResponse.setIsActive(category.getIsActive());
        }
        return ResponseEntity.ok().body(new IGenericResponse<List<CategoryResponse>>(categoryResponseList, 200, "findAll Category parent successfully"));

    }

    @PutMapping("update")
    public ResponseEntity<?> updateEvent(@RequestBody Category category) {
        Optional<Category> categoryOptional = categoryService.findById(category.getId());
        if (categoryOptional.isPresent()) {

            return ResponseEntity.ok().body(new IGenericResponse<Category>(categoryService.save(category), 200, ""));
        } else {
            return ResponseEntity.badRequest().body(new HandleExceptionDemo(400, "not found category"));
        }
    }

    @DeleteMapping("delete")
    public ResponseEntity<?> deleteEvent(@RequestParam("category_id") Integer id) {
        Optional<Category> categoryOptional = categoryService.findById(id);
        if (categoryOptional.isPresent()) {
            categoryService.deleteById(id);
            return ResponseEntity.ok().body(new HandleExceptionDemo(200, ""));
        } else {
            return ResponseEntity.badRequest().body(new HandleExceptionDemo(400, "not found category"));
        }
    }

    @GetMapping("findAll")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(new IGenericResponse<List<Category>>(categoryService.findAll(), 200, ""));
    }

    @GetMapping("findCategoryByParentId")
    public ResponseEntity<?> findCategoryByParentId(@RequestParam("category_parent_id") Integer id) {
        Optional<Category> categoryOptional = categoryService.findById(id);
        if (categoryOptional.isPresent()) {

            return ResponseEntity.ok().body(new IGenericResponse<>(categoryService.findByCategoryParentId(id), 200, ""));

        }
        return ResponseEntity.badRequest().body(new HandleExceptionDemo(400, "not found category"));

    }

}
