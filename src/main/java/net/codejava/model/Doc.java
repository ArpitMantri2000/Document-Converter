package net.codejava.model;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "docs")
public class Doc {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	
	private String document_uid = UUID.randomUUID().toString();
	private String version_uid = UUID.randomUUID().toString();
	
	private String docName;
	private String docType;
	private String mimeType;
	private Date createdDate = new Date(System.currentTimeMillis());
	
	@ManyToOne
	@JoinColumn(name = "cid")
	private User user;

	
	public Doc()
	{
		
	}

	public Doc(String docName, String docType, String mimeType) {
		super();
		this.docName = docName;
		this.docType = docType;
		this.mimeType = mimeType;
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	
    public String getDocument_uid() {
		return document_uid;
	}


	public String getVersion_uid() {
		return version_uid;
	}

	

	public Date getCreatedDate() {
		return createdDate;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void SaveDocument(@RequestParam("File") MultipartFile file) throws IOException 
	{
		String path = "E:\\UPLOADS\\"  + document_uid  + version_uid;
	    File file1 = new File(path);
     	file1.mkdir();
		String FILE_DIRECTORY = path+"\\";
		File myFile = new File(FILE_DIRECTORY+file.getOriginalFilename());
		myFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(myFile);
		fos.write(file.getBytes());
		fos.close();
	}

	
	public void ConvertToPdf()
	{
		{
		
		try
		{
			String docPath =  "E:\\UPLOADS\\"  + document_uid  + version_uid+"\\"+docName;
			String pdfPath =  "E:\\UPLOADS\\"  + document_uid  + version_uid+"\\";
			String[] command = {"cmd.exe", "/c", "soffice --headless --convert-to pdf", docPath};
			ProcessBuilder builder = new ProcessBuilder(command);
			builder = builder.directory(new File(pdfPath));
			Process p = builder.start();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	    }
		
	}
	
	public void ImageToPdf()
	{
		
		String type = docType;
	
		String name = docName.substring(0,docName.lastIndexOf("."));
		String output = name+".pdf";
		try
		{
			String docPath =  "E:\\UPLOADS\\"  + document_uid  + version_uid+"\\"+docName;
			String pdfPath =  "E:\\UPLOADS\\"  + document_uid  + version_uid+"\\";
			String imgPath =  "E:\\UPLOADS\\"  + document_uid  + version_uid+"\\"+output;
				String[] command = {"cmd.exe", "/c","magick convert",docPath, imgPath};
                ProcessBuilder builder = new ProcessBuilder(command);
				builder = builder.directory(new File(pdfPath));
				Process p = builder.start();
			}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}

	}
	
	
}
