package com.github.testr.builder.extra;

import com.github.testr.builder.IBuilder;

public interface DocumentTypeBuilder extends IBuilder<DocumentType> {

    DocumentTypeBuilder code(String code);

    DocumentTypeBuilder description(String code);

}
