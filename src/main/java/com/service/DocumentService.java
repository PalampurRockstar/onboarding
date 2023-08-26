package com.service;


import com.model.table.Document;
import com.model.table.Review;

import java.util.List;

public interface DocumentService {

    List<?> findAll();

    Document findById(String id);

    public List<Document> findByPetId(String id);

    Document save(Document image);

	Document update(String id, Document image);

    void delete(String id);

}
