package net.codejava.model;

import java.util.Date;
import java.util.UUID;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "docs")
public class Doc {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	
	private String documentuid = UUID.randomUUID().toString();
	private String versionuid = UUID.randomUUID().toString();
	
	private String docName;
	private String docType;
	private String mimeType;
	private Date createdDate = new Date(System.currentTimeMillis());
	
	@Column(insertable = false,updatable = false)
	private Long user_id;
	
	@ManyToOne( cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
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
	
	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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

	public String getDocumentuid() {
		return documentuid;
	}

	public void setDocumentuid(String documentuid) {
		this.documentuid = documentuid;
	}

	public String getVersionuid() {
		return versionuid;
	}

	public void setVersionuid(String versionuid) {
		this.versionuid = versionuid;
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
	


	public void SaveDocument(@RequestParam("File") MultipartFile file) throws IOException {
		String path = "E:\\UPLOADS\\"  + documentuid  + versionuid;
	    File file1 = new File(path);
     	file1.mkdir();
		String FILE_DIRECTORY = path+"\\";
		File myFile = new File(FILE_DIRECTORY+file.getOriginalFilename());
		myFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(myFile);
		fos.write(file.getBytes());
		fos.close();
	}

	//command:soffice --headless --convert-to pdf 
	public void ConvertToPdf() {
		try {
			String docPath =  "E:\\UPLOADS\\"  + documentuid  + versionuid+"\\"+docName;
			String pdfPath =  "E:\\UPLOADS\\"  + documentuid  + versionuid+"\\";
			String[] command = {"cmd.exe", "/c", "soffice --headless --convert-to pdf", docPath};
			ProcessBuilder builder = new ProcessBuilder(command);
			builder = builder.directory(new File(pdfPath));
			Process p = builder.start();
			System.out.println("Document Conversion is done");
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	
	}
	//command :magick convert img filename pdf filename
	public void ImageToPdf() {
		String type = docType;
		String name = docName.substring(0,docName.lastIndexOf("."));
		String output = name+".pdf";
		try {
			String docPath =  "E:\\UPLOADS\\"  + documentuid  + versionuid+"\\"+docName;
			String pdfPath =  "E:\\UPLOADS\\"  + documentuid  + versionuid+"\\";
			String imgPath =  "E:\\UPLOADS\\"  + documentuid  + versionuid+"\\"+output;
			String[] command = {"cmd.exe", "/c","magick convert",docPath, imgPath};
            ProcessBuilder builder = new ProcessBuilder(command);
			builder = builder.directory(new File(pdfPath));
			Process p = builder.start();
			System.out.println("Image Conversion is done");
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
}
