/**
 * Tomitribe Confidential
 * <p/>
 * Copyright(c) Tomitribe Corporation. 2014
 * <p/>
 * The source code for this program is not published or otherwise divested
 * of its trade secrets, irrespective of what has been deposited with the
 * U.S. Copyright Office.
 * <p/>
 */
package com.tomitribe.ee.cdi;

import org.mybatis.jpetstore.persistence.CategoryMapper;

import java.util.List;

public class Category implements CategoryMapper {

    @Override
    public List<org.mybatis.jpetstore.domain.Category> getCategoryList() {
        return null;
    }

    @Override
    public org.mybatis.jpetstore.domain.Category getCategory(final String categoryId) {
        return null;
    }
}
