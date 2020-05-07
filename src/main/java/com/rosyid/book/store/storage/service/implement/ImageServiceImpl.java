package com.rosyid.book.store.storage.service.implement;

import com.rosyid.book.store.storage.entity.Image;
import com.rosyid.book.store.storage.payload.request.ImageRequest;
import com.rosyid.book.store.storage.payload.response.ImageResponse;
import com.rosyid.book.store.storage.repository.ImageRepository;
import com.rosyid.book.store.storage.service.ImageService;
import com.rosyid.book.store.storage.service.MinioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService
{

    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private MinioService minioService;


    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public ImageResponse uploadImage(ImageRequest request, MultipartFile file)
    {
        ImageResponse entity = new ImageResponse();
//        Image image = imageRepository.findById(response.getId()).orElse(null);
//        if (image == null)
//            throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Image not found");

        // upload image
        try {
            Image image = new Image();

            String imageUrl = minioService.uploadImage(UUID.randomUUID().toString(),
                    file.getInputStream(),
                    file.getContentType());

            image.setName(imageUrl);
            imageRepository.save(image);
        } catch (Exception e) {
            e.printStackTrace();
            throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Problem upload file");
        }
        //BeanUtils.copyProperties(image, entity);
        return entity;
    }
    @Override
    public List findAll() {
        return null;
    }

    @Override
    public Object saveOrUpdate(Object entity) {
        return null;
    }

    @Override
    public Object findById(Object o) {
        return null;
    }

    @Override
    public Object delete(Object entity) {
        return null;
    }

    @Override
    public Object deleteById(Object o) {
        return null;
    }

    @Override
    public Long countAll() {
        return null;
    }
}
