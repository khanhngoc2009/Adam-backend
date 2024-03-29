package com.example.adambackend.repository;

import com.example.adambackend.entities.Size;
import com.example.adambackend.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {

    Optional<Tag> findByTagName(String tagName);

    @Transactional
    @Modifying
    @Query(value = "update tags set is_active=0 , is_deleted=1 where id=?1", nativeQuery = true)
    void updateDeletedTagId(Integer productId);

    @Query(value = "select * from tags where is_active=1 and is_deleted=0", nativeQuery = true)
    List<Tag> findAlls();
    @Query(value = "select * from tags where tag_name like '%?1%'",nativeQuery = true)
    List<Tag>  findByName(String name);
}
