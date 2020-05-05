package com.rosyid.book.store.transaction.util;

import org.springframework.data.domain.PageRequest;

public class PageRequestUtil
{
    private static final int MAX_PAGE = 10;

    public static PageRequest constructPageRequest(Integer page, Integer perPage)
    {
        if (page == null)
            page = 0;
        else
            page = page - 1;

        if (perPage == null)
            perPage = MAX_PAGE;

        PageRequest request = PageRequest.of(page, perPage);
        return request;
    }
}
