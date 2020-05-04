package com.rosyid.book.store.catalog.service.implement;

import com.rosyid.book.store.catalog.entity.Category;
import com.rosyid.book.store.catalog.payload.response.CategoryGetByNameResponse;
import com.rosyid.book.store.catalog.payload.response.CategoryResponse;
import com.rosyid.book.store.catalog.persistence.CatalogEntityPersistence;
import com.rosyid.book.store.catalog.repository.CategoryRepository;
import com.rosyid.book.store.catalog.service.CategoryService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpServerErrorException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



/**
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
public class CategoryServiceImpl implements CategoryService
{
    private static final long serialVersionUID = 1L;

    @Autowired
    private CategoryRepository categoryRepository;


    /**
     * Save or Update
     * @param entity
     * @return
     */

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public CategoryResponse saveOrUpdate(CategoryResponse entity)
    {

        Category category;

        // logic create - update
        if ( entity.getId() != null )
        {
            category = categoryRepository.findById( entity.getId() ).orElse(null );

            if ( category == null )
            {
                throw new HttpServerErrorException(HttpStatus.BAD_REQUEST,
                        "Category with id: " + entity.getId() + "not found");
            }

            // update dengan data baru
            category.setName( entity.getName() );
            category.setSlug( entity.getSlug() );
            category.setParentId( entity.getParentId() );
            category.setVisibility( entity.getVisibility() );

            categoryRepository.save( category );

            entity.setUpdatedBy( category.getUpdatedBy() );
            entity.setCreatedAt( category.getUpdatedAt() );

            return entity;

        } else {
            category = new Category();
            category.setName( entity.getName() );
            category.setSlug( entity.getSlug() );
            category.setParentId( entity.getParentId() );
            category.setVisibility( entity.getVisibility() );

            categoryRepository.save(category);

            BeanUtils.copyProperties( category, entity);
        }

        return entity;
    }


    /**
     * Delete
     * @param entity
     * @return
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public CategoryResponse delete(CategoryResponse entity)
    {

        //
        if ( entity.getId() != null )
        {
            Category category = categoryRepository.findById( entity.getId() ).orElse( null );

            if ( category == null )
            {
                throw new HttpServerErrorException( HttpStatus.BAD_REQUEST,
                        "Category with id : "+ entity.getId() +" not found or has been deleted");
            }

            category.setStatus( CatalogEntityPersistence.Status.INACTIVE);
            categoryRepository.save( category );
            BeanUtils.copyProperties( category, entity );

            return entity;
        }else{
            throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Id cannot be null");
        }
    }


    /**
     * Delete By Id
     * @param id
     * @return
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public CategoryResponse deleteById(Long id)
    {

        if ( id != null )
        {
            CategoryResponse entity = new CategoryResponse();
            Category category = categoryRepository.findById( id ).orElse( null );

            if (category == null )
            {
                throw new HttpServerErrorException( HttpStatus.BAD_REQUEST, "Category with id: "+ id + " not found");
            }

            category.setStatus( CatalogEntityPersistence.Status.INACTIVE );
            categoryRepository.save( category );
            BeanUtils.copyProperties( category, entity );

            return entity;

        }else {
            throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Id cannot be null");
        }


    }


    /**
     * Find By Id
     * @param id
     * @return
     */
    @Transactional( readOnly = true, propagation = Propagation.SUPPORTS)
    public CategoryResponse findById(Long id)
    {

        if ( id != null )
        {
            CategoryResponse entity = new CategoryResponse();
            Category category = categoryRepository.findById( id ).orElse( null );

            if (category == null )
            {
                throw new HttpServerErrorException( HttpStatus.BAD_REQUEST, "Category with id: "+ id + " has been deleted!");
            }

            BeanUtils.copyProperties( category, entity );
            return entity;
        }
        else {
            throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Id cannot be null");
        }

    }

    /**
     * Search by Name
     * @param name
     * @return
     */
    @Transactional( readOnly = true, propagation = Propagation.SUPPORTS)
    public CategoryGetByNameResponse findByName(String name )
    {

        if ( name != null )
        {
            CategoryGetByNameResponse entity = new CategoryGetByNameResponse();
            Category category = categoryRepository.findByName( name );

            if (category == null )
            {
                throw new HttpServerErrorException( HttpStatus.BAD_REQUEST, "Category with id: "+ name + " has been deleted!");
            }

            BeanUtils.copyProperties( category, entity );
            return entity;
        }
        else {
            throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Id cannot be null");
        }

    }

    /**
     * Find All
     * @return
     */
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<CategoryResponse> findAll()
    {

        List<CategoryResponse> entities = new ArrayList<>();

        categoryRepository.findAll()
                .forEach( data -> {
                    CategoryResponse entity = new CategoryResponse();

                    BeanUtils.copyProperties(data, entity);
                    entities.add(entity);
                });

        return entities;
    }


    /**
     * Count All
     * @return
     */
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Long countAll()
    {
        return categoryRepository.count();
    }
}