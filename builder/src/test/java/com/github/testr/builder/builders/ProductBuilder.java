package com.github.testr.builder.builders;

import com.github.testr.builder.IBuilder;
import com.github.testr.builder.pojos.Product;

public interface ProductBuilder extends IBuilder<Product> {

    ProductBuilder name(String name);

    ProductBuilder quantity(int quantity);

}
