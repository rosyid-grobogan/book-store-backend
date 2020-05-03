package com.rosyid.book.store.catalog.service.implement;

import com.rosyid.book.store.catalog.entity.Category;
import com.rosyid.book.store.catalog.entity.Product;
import com.rosyid.book.store.catalog.payload.response.CategoryResponse;
import com.rosyid.book.store.catalog.payload.response.ProductResponse;
import com.rosyid.book.store.catalog.repository.CategoryRepository;
import com.rosyid.book.store.catalog.repository.ProductRepository;
import com.rosyid.book.store.catalog.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService
{
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public ProductResponse saveOrUpdate(ProductResponse entity) {

        Product product;
        Category category;
        if (entity.getId() != null)
        {
            product = productRepository.findById( entity.getId() ).orElse(null);
            if (product == null)
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

            BeanUtils.copyProperties(entity, product);
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
//        return null;
    }

    @Override
    public ProductResponse delete(ProductResponse entity) {
        return null;
    }

    @Override
    public ProductResponse deleteById(Long aLong) {
        return null;
    }

    @Override
    public ProductResponse findById(Long aLong) {
        return null;
    }

    @Override
    public List<ProductResponse> findAll() {
        return null;
    }

    @Override
    public Long countAll() {
        return null;
    }
}
