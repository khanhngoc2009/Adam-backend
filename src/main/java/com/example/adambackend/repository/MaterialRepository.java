package com.example.adambackend.repository;

import com.example.adambackend.entities.Material;
import com.example.adambackend.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Integer> {
    @Transactional
    @Modifying
    @Query(value = "update materials set is_active=0 , is_deleted=1 where id=?1", nativeQuery = true)
    void updateDeleteByArrayId(Integer id);

    @Query(value = "select * from materials where is_active=1 and is_deleted=0", nativeQuery = true)
    List<Material> findAlls();
    @Query(value = "select * from materials where material_name like '%?1%'",nativeQuery = true)
    List<Material>  findByName(String name);
}
