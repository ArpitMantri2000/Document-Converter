package net.codejava.Repository.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import net.codejava.Repository.DocRepository;
import net.codejava.Repository.UserRepository;
import net.codejava.model.Doc;
import net.codejava.model.User;

@Service
public class DocStorageService {
@Autowired
private DocRepository docRepository;

@Autowired
private UserRepository repo;
//saving the multipartfile  to the database and then saving file to local repository ,and calling pdf conversions

public Doc saveFile(MultipartFile file)
{
	 String docname = file.getOriginalFilename();
	 String[] fileFrags = file.getOriginalFilename().split("\\.");
	 String extension = fileFrags[fileFrags.length-1];	 
	try
	{
		Doc doc = new Doc(docname , extension , file.getContentType());
		User user = repo.getById((long) 1);
		doc.setUser(user);
		docRepository.save(doc);
		doc.SaveDocument(file);
		doc.ConvertToPdf();
		doc.ImageToPdf();
		return doc;
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return null;
}

//method which returns the document data from db
public Optional<Doc> getFile(Long id)
{
return docRepository.findById(id);	
}

// return a list of documents
public List<Doc> getFiles()
{
	return docRepository.findAll();
}

public Doc findByDocumentuid(String documentuid) {
	
	return docRepository.findByDocumentuid(documentuid);
}




}

