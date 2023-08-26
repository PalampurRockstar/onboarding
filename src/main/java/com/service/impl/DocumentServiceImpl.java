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
    private DocumentRepository documentRepo;
    @Override
    public List<Document> findAll() {
        return documentRepo.findAll();
    }

    @Override
    public Document findById(String id) {
        return documentRepo.findById(id).orElseThrow(() -> new RuntimeException ("** Document not found for id :: " + id));
    }

    @Override
    public Document save(Document pet) {
        return documentRepo.save(pet);
    }

    @Override
    public List<Document> findByPetId(String id){
        return documentRepo.findByPetId(id);
    }

    @Override
    public Document update(String id, Document Document) {
    	documentRepo.findById(id).orElseThrow(() -> new RuntimeException ("** Document not found for id :: " + id));
    	Document.setId(id);
    	return documentRepo.save(Document);
    }

    @Override
    public void delete(String id) {
        documentRepo.findById(id).ifPresent(Document -> documentRepo.delete(Document));
    }
}
