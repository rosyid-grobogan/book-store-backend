package com.rosyid.book.store.catalog.service.implement;

import com.rosyid.book.store.catalog.entity.Category;
import com.rosyid.book.store.catalog.entity.Product;
import com.rosyid.book.store.catalog.payload.response.CategoryResponse;
import com.rosyid.book.store.catalog.payload.response.ProductResponse;
import com.rosyid.book.store.catalog.persistence.CatalogEntityPersistence;
import com.rosyid.book.store.catalog.repository.CategoryRepository;
import com.rosyid.book.store.catalog.repository.ProductRepository;
import com.rosyid.book.store.catalog.service.ProductService;
import com.rosyid.book.store.storage.service.MinioService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Find ALl Product
 * Find By
 * Save or Update
 */
@Service
public class ProductServiceImpl implements ProductService
{
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private MinioService minioService;

    /**
     * Find All Product
     * @return
     */
    @Override
    public List<ProductResponse> findAll()
    {
        List<ProductResponse> entities = new ArrayList<>();
        productRepository.findAll().forEach(data -> {
            ProductResponse entity = new ProductResponse();
            BeanUtils.copyProperties(data, entity);

            CategoryResponse categoryResponse = new CategoryResponse();
            BeanUtils.copyProperties(data.getProductCategoryId(), categoryResponse);

            entity.setCategoryId(data.getProductCategoryId().getId());
            entity.setProductCategoryId(categoryResponse);

            entities.add(entity);
        });
        return entities;
    }


    /**
     * Find By Id
     * @param id
     * @return
     */
    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public ProductResponse findById(Long id)
    {
        if (id != null)
        {
            ProductResponse productResponse = new ProductResponse();
            Product product = productRepository.findById(id).orElse(null);

            if (product == null)
            {
                throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Product with id:"+ id + " not found");
            }

            CategoryResponse categoryResponse = new CategoryResponse();
            BeanUtils.copyProperties(product.getProductCategoryId(), categoryResponse);
            productResponse.setCategoryId(product.getProductCategoryId().getId());
            productResponse.setProductCategoryId(categoryResponse);

            BeanUtils.copyProperties(product, productResponse);
            return productResponse;
        }else {
            throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Id cannot be null");
        }

    }


    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public ProductResponse uploadImage(Long id, MultipartFile file) {
        ProductResponse entity = new ProductResponse();
        Product product = productRepository.findById(id).orElse(null);
        if (product == null)
            throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Product with id: " + id + " not found");

        // upload image
        try {
            String imageUrl = minioService.uploadImage(UUID.randomUUID().toString(),
                    file.getInputStream(), file.getContentType());
            product.setImageUrl(imageUrl);
            product = productRepository.save(product);

        } catch (Exception e) {
            e.printStackTrace();
            throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Problem upload file");
        }
        BeanUtils.copyProperties(product, entity);
       return entity;
    }

    /**
     * Save or Update
     * @param entity
     * @return
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public ProductResponse create(ProductResponse entity)
    {
        Product product;
        Category category;

        // Get Id
        if (entity.getId() != null)
        {
            product = productRepository.findById( entity.getId() ).orElse(null);

            if ( product == null )
            {
                throw new HttpServerErrorException(HttpStatus.BAD_REQUEST,
                        "Product with id: "+ entity.getId() +" not found"
                        );
            }
            if (!product.getProductCategoryId().getId().equals(entity.getCategoryId()))
            {
                category = categoryRepository.findById(entity.getCategoryId()).orElse(null);
                product.setProductCategoryId(category);
            }

                //BeanUtils.copyProperties(entity, product);
                product.setName(entity.getName());
                product.setSlug(entity.getSlug());
//            product.setPhotoId(entity.getPhotoId());
                product.setImageUrl(entity.getImageUrl());
                product.setPrice(entity.getPrice());
                product.setQuantity(entity.getQuantity());
                product.setDescription(entity.getDescription());
                product.setProductStatus(entity.getProductStatus());
                product.setVisibility(entity.getVisibility());

                product = productRepository.save(product);

        }
        else {
            // Create
            category = categoryRepository.findById(entity.getCategoryId()).orElse( null);
            if (category == null)
            {
                throw new HttpServerErrorException(HttpStatus.BAD_REQUEST,
                        "Product with id: "+entity.getId() +" not found"
                        );
            }

            product = new Product();
            product.setProductCategoryId(category);
            BeanUtils.copyProperties(entity, product);
            product = productRepository.save(product);

        }

        CategoryResponse categoryResponse = new CategoryResponse();
        BeanUtils.copyProperties(product.getProductCategoryId(), categoryResponse);
        BeanUtils.copyProperties(product, entity);
        entity.setCategoryId(product.getProductCategoryId().getId());
        entity.setProductCategoryId(categoryResponse);

        return entity;

    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public ProductResponse update(ProductResponse entity)
    {
        Product product;
        Category category;

        // Get Id
        if (entity.getId() != null)
        {
            product = productRepository.findById( entity.getId() ).orElse(null);

            if ( product == null )
            {
                throw new HttpServerErrorException(HttpStatus.BAD_REQUEST,
                        "Product with id: "+ entity.getId() +" not found"
                );
            }
            if (!product.getProductCategoryId().getId().equals(entity.getCategoryId()))
            {
                category = categoryRepository.findById(entity.getCategoryId()).orElse(null);
                product.setProductCategoryId(category);
            }

            //BeanUtils.copyProperties(entity, product);
            product.setName(entity.getName());
            product.setSlug(entity.getSlug());
//            product.setPhotoId(entity.getPhotoId());
            product.setImageUrl(entity.getImageUrl());
            product.setPrice(entity.getPrice());
            product.setQuantity(entity.getQuantity());
            product.setDescription(entity.getDescription());
            product.setProductStatus(entity.getProductStatus());
            product.setVisibility(entity.getVisibility());

            product = productRepository.save(product);

        }
        else {
            // Create
            category = categoryRepository.findById(entity.getCategoryId()).orElse( null);
            if (category == null)
            {
                throw new HttpServerErrorException(HttpStatus.BAD_REQUEST,
                        "Product with id: "+entity.getId() +" not found"
                );
            }

            product = new Product();
            product.setProductCategoryId(category);
            BeanUtils.copyProperties(entity, product);
            product = productRepository.save(product);

        }

        CategoryResponse categoryResponse = new CategoryResponse();
        BeanUtils.copyProperties(product.getProductCategoryId(), categoryResponse);
        BeanUtils.copyProperties(product, entity);
        entity.setCategoryId(product.getProductCategoryId().getId());
        entity.setProductCategoryId(categoryResponse);

        return entity;

    }
    /**
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public ProductResponse delete(ProductResponse entity)
    {
        if (entity.getId() != null)
        {
            Product product = productRepository.findById(entity.getId()).orElse(null);

            if (product == null) {
                throw new HttpServerErrorException(HttpStatus.BAD_REQUEST,
                        "Product with id: " + entity.getId() + " not found"
                );
            }
            product.setStatus(CatalogEntityPersistence.Status.INACTIVE);
            product = productRepository.save(product);
            return entity;
        }else {
            throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Id cannot be null");
        }
    }



    /**
     *
     * @param id
     * @return
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public ProductResponse deleteById(Long id)
    {
        if (id != null)
        {
            ProductResponse productResponse =  new ProductResponse();
            Product product = productRepository.findById(id).orElse(null);

            if (product == null)
            {
                throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Product with id: "+ id +" not found");
            }

            product.setStatus(CatalogEntityPersistence.Status.INACTIVE);
            product = productRepository.save(product);

            BeanUtils.copyProperties(product, productResponse);
            return productResponse;
        }else {
            throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Id cannot be null");
        }

    }


    @Override
    public Long countAll()
    {
        return null;
    }
}
