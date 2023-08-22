package com.service;


import com.model.table.Document;

import java.util.List;

public interface DocumentService {

    List<?> findAll();

    Document findById(String id);

    Document save(Document image);

	Document update(String id, Document image);

    void delete(String id);

}
