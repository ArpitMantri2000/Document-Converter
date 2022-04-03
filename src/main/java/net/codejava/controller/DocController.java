package net.codejava.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.util.StreamUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.http.ResponseEntity.HeadersBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import net.codejava.Repository.DocRepository;
import net.codejava.Repository.UserRepository;
import net.codejava.Repository.service.DocStorageService;
import net.codejava.model.Doc;
import net.codejava.model.User;

@Controller
public class DocController {

	@Autowired
	private DocStorageService docStorageService;
	
	@Autowired
	private DocRepository repo;
	
	@GetMapping("/docs")
	public String get(Model model)
	{
		List<Doc> docs = docStorageService.getFiles();
		model.addAttribute("docs" , docs);
		return "doc";
	}
//uploading the file or files
	@PostMapping("/uploadFiles")
	public String uploadMultipleFiles(@RequestParam("files") MultipartFile[] files)
	{
		for(MultipartFile file: files)
		{	
			docStorageService.saveFile(file);
		}
		return "redirect:/docs";
	}
	
	
}



