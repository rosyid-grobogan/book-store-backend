package com.rosyid.book.store.transaction.payload.request;

import javax.validation.constraints.NotNull;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * DTO
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionRequest
{

    @NotNull
    private Long userId;

    @NotNull
    private Set<Long> cartDetailIds;
}
