package com.rosyid.book.store.service.implement;

import com.rosyid.book.store.entity.Category;
import com.rosyid.book.store.model.CategoryModel;
import com.rosyid.book.store.persistence.EntityPersistence;
import com.rosyid.book.store.repository.CategoryRepository;
import com.rosyid.book.store.service.CategoryService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpServerErrorException;

import java.util.ArrayList;
import java.util.List;

/*
* # Implement
*
* saveOrUpdate()
* delete()
* deleteById()
* findById()
* List<> findAll()
* countAll()
 */


@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public CategoryModel saveOrUpdate(CategoryModel entity) {

        Category category;

        // logic
        if ( entity.getId() != null ) {
            category = categoryRepository.findById( entity.getId() ).orElse(null );

            if ( category == null ) {
                throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Category with id: " + entity.getId() + "not found");
            }

            category.setCode( entity.getCode() );
            category.setCode( entity.getName() );
            categoryRepository.save( category );

            entity.setUpdatedBy( category.getUpdatedBy() );
            entity.setUpdatedTime( category.getUpdatedTime() );

            return entity;

        } else {
            category = new Category();
            category.setCode( entity.getCode() );
            category.setName( entity.getName() );

            BeanUtils.copyProperties( category, entity);
        }

        return entity;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public CategoryModel delete(CategoryModel entity) {

        //
        if ( entity.getId() != null ) {
            Category category = categoryRepository.findById( entity.getId() ).orElse( null );

            if ( category == null ) {
                throw new HttpServerErrorException( HttpStatus.BAD_REQUEST, "Category with id: "+ entity.getId() +" not found");
            }

            category.setStatus( EntityPersistence.Status.INACTIVE );
            categoryRepository.save( category );
            BeanUtils.copyProperties( category, entity );

            return entity;

        }

        return entity;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public CategoryModel deleteById(Integer id) {

        if ( id != null ) {
            CategoryModel entity = new CategoryModel();
            Category category = categoryRepository.findById( id ).orElse( null );

            if (category == null ) {
                throw new HttpServerErrorException( HttpStatus.BAD_REQUEST, "Category with id: "+ id +" not found");
            }

            category.setStatus( EntityPersistence.Status.INACTIVE );
            categoryRepository.save( category );
            BeanUtils.copyProperties( category, entity );

            return entity;

        }

        return null;
    }

    @Transactional( readOnly = true, propagation = Propagation.SUPPORTS)
    public CategoryModel findById(Integer id) {

        if ( id != null ) {
            CategoryModel entity = new CategoryModel();
            Category category = categoryRepository.findById( id ).orElse( null );

            if (category == null ) {
                throw new HttpServerErrorException( HttpStatus.BAD_REQUEST, "Category with id: "+ id + " not found");
            }

            BeanUtils.copyProperties( category, entity );

        }

        return null;
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<CategoryModel> findAll() {

        List<CategoryModel> entities = new ArrayList<>();

        categoryRepository.findAll().forEach(data -> {
            CategoryModel entity = new CategoryModel();
            BeanUtils.copyProperties(data, entity);
            entities.add(entity);

        });

        return entities;
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Long countAll() {

        return categoryRepository.count();
    }
}
