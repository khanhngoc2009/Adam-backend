package com.example.adambackend.controller.admin;

import com.example.adambackend.entities.Color;
import com.example.adambackend.exception.HandleExceptionDemo;
import com.example.adambackend.payload.ColorDTO;
import com.example.adambackend.payload.ListColorIdDTO;
import com.example.adambackend.payload.response.IGenericResponse;
import com.example.adambackend.repository.DetailProductRepository;
import com.example.adambackend.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(value = "*", maxAge = 3600)
@RequestMapping("admin/color")
public class ColorController {
    @Autowired
    ColorService colorService;
    @Autowired
    DetailProductRepository detailProductRepository;

    @GetMapping("findAll")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(new IGenericResponse<List<Color>>(colorService.findAll(), 200, ""));
    }

    @PostMapping("create")
    public ResponseEntity<?> create(@RequestBody ColorDTO colorDTO) {
        try {
            Color color = new Color();
            color.setColorName(colorDTO.getColorName());
            color.setIsDeleted(false);
            color.setCreateDate(LocalDateTime.now());
            color.setIsActive(true);
            return ResponseEntity.ok().body(new IGenericResponse<Color>(colorService.save(color), 200, "success"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new HandleExceptionDemo(500, "can't duplicate name"));
        }
    }

    @PutMapping("update")
    public ResponseEntity<?> update(@RequestBody Color color) {
        Optional<Color> colorOptional = colorService.findById(color.getId());
        if (colorOptional.isPresent()) {
            return ResponseEntity.ok().body(new IGenericResponse<Color>(colorService.save(color), 200, "success"));
        }
        return ResponseEntity.badRequest().body(new HandleExceptionDemo(400, "not found"));
    }

    @DeleteMapping("delete")
    public ResponseEntity<?> delete(@RequestParam("color_id") Integer colorId) {
        Optional<Color> colorOptional = colorService.findById(colorId);
        if (colorOptional.isPresent()) {
            colorService.deleteById(colorId);
            return ResponseEntity.ok().body(new HandleExceptionDemo(200, "success"));
        }
        return ResponseEntity.badRequest().body(new HandleExceptionDemo(400, "not found"));
    }

    @DeleteMapping("deleteByListId")
    public ResponseEntity<?> deleteArrayTagId(@RequestBody ListColorIdDTO listColorIdDTO) {
        List<Integer> list = listColorIdDTO.getListColorId();
        System.out.println(list.size());
        if (list.size() > 0) {
            for (Integer x : list
            ) {
                Optional<Color> colorOptional = colorService.findById(x);

                if (colorOptional.isPresent()) {

                    colorService.deleteById(x);

                }
            }
            return ResponseEntity.ok().body(new IGenericResponse<>("", 200, ""));
        }
        return ResponseEntity.badRequest().body(new HandleExceptionDemo(400, " not found"));
    }
}
