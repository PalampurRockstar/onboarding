package com.service.impl;


import com.model.table.Document;
import com.repository.DocumentRepository;
import com.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentRepository imageRepo;
    @Override
    public List<Document> findAll() {
        return imageRepo.findAll();
    }

    @Override
    public Document findById(String id) {
        return imageRepo.findById(id).orElseThrow(() -> new RuntimeException ("** Document not found for id :: " + id));
    }

    @Override
    public Document save(Document pet) {
        return imageRepo.save(pet);
    }

    @Override
    public Document update(String id, Document Document) {
    	imageRepo.findById(id).orElseThrow(() -> new RuntimeException ("** Document not found for id :: " + id));
    	Document.setId(id);
    	return imageRepo.save(Document);
    }

    @Override
    public void delete(String id) {
        imageRepo.findById(id).ifPresent(Document -> imageRepo.delete(Document));
    }
}
