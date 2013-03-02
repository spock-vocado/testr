package com.github.testr.builder.pojos;

import com.github.testr.builder.IBuilder;

public interface DocumentTypeBuilder extends IBuilder<DocumentType> {

    DocumentTypeBuilder code(String code);

    DocumentTypeBuilder description(String code);

}
