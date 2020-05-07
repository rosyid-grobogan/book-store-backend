package com.rosyid.book.store.storage.controller;

import com.rosyid.book.store.storage.payload.request.ImageRequest;
import com.rosyid.book.store.storage.payload.response.ImageResponse;
import com.rosyid.book.store.storage.payload.response.UploadFileResponse;
import com.rosyid.book.store.storage.service.ImageService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Api
@RestController
@RequestMapping("/api/v1/user/upload")
public class ImageRestController
{
    @Autowired
    private ImageService imageService;


//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/image")
    public ImageResponse uploadImage(@RequestBody ImageRequest request,
                                     @RequestParam("file") MultipartFile file)
    {
        return imageService.uploadImage(request, file);
    }

//    @PostMapping("/uploadFile")
//    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
//        String fileName = fileStorageService.storeFile(file);
//
//        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("/downloadFile/")
//                .path(fileName)
//                .toUriString();
//
//        return new UploadFileResponse(fileName, fileDownloadUri,
//                file.getContentType(), file.getSize());
//    }
}
