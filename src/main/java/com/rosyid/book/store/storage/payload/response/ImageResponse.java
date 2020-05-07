package com.rosyid.book.store.storage.payload.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rosyid.book.store.storage.persistence.StorageModelPersistence;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageResponse extends StorageModelPersistence
{
    private String name;
}
