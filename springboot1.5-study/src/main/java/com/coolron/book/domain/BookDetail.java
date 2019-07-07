package com.coolron.book.domain;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @Auther: xf
 * @Date: 2018/7/30 10:02
 * @Description:
 */
public class BookDetail extends Book {
    private List<Tag> tags = Lists.newArrayList();

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
