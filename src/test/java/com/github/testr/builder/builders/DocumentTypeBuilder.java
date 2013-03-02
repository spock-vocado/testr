package com.github.testr.builder.builders;

import com.github.testr.builder.IBuilder;
import com.github.testr.builder.pojos.DocumentType;

public interface DocumentTypeBuilder extends IBuilder<DocumentType> {

    DocumentTypeBuilder code(String code);

    DocumentTypeBuilder description(String code);

}
