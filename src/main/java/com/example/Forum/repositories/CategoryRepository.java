package com.example.Forum.repositories;

import com.example.Forum.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "select c from Category c where c.id not in (select  sc.id from Category c1 JOIN c1.subCategories sc)")
    List<Category> findCategoriesNotHavingParents();

}
