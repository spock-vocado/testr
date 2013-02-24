package com.github.testbed.entity.builder;

import com.github.testbed.builder.IDynamicBuilder;
import com.github.testbed.entity.DocumentType;

public interface DocumentTypeBuilder extends IDynamicBuilder<DocumentType> {

    DocumentTypeBuilder code(String code);

    DocumentTypeBuilder description(String code);

}
