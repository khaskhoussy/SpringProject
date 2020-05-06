package com.example.service;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.Exceptions.FileStorageException;
import com.example.Exceptions.FilesException;
import com.example.entity.Pictures;
import com.example.properties.FilesStorageProperties;
import com.example.repository.PicturesRepository;

@Service 
public class FileStorageService {
	
	 private static final Logger log = LoggerFactory.getLogger( FileStorageService.class); 
	 private final Path fileStorageLocation; 
	 @Autowired 
	 PicturesRepository imgrep;
	
	 
	 
	 @Autowired
	  public FileStorageService( FilesStorageProperties filesStorageProperties ){ 

	        this.fileStorageLocation = Paths.get( filesStorageProperties.getUploadDir() ) 
	                                        //.toAbsolutePath()
	                                        .normalize(); 
	        log.debug( "fileStorageLocation: " + this.fileStorageLocation ); 

	        try{ // try to create a directory if not exist. 
	            Files.createDirectories( this.fileStorageLocation ); 
	        }catch( Exception e ){ 
	            throw new FileStorageException("Coun't not create the directory where the uploaded files will be stored."); 
	        } 

	    } 
	  
	  
	  public String storeFile( MultipartFile file ) throws IOException{ 
	    	
		    File f = new File("C://wamp64//" + file.getOriginalFilename());

			f.createNewFile();
			FileOutputStream fout = new FileOutputStream(f);
			fout.write(file.getBytes());
			fout.close();

			if (f.exists())
				f.delete();
	        String fileName = StringUtils.cleanPath( file.getOriginalFilename() ); // Using StringUtils.cleanPath() to clean the ".." or ".", or normalize the path. 

	        try{ 
	            if( fileName.contains("..") ) throw new FileStorageException("Couldn't store file name with \"..\""); 
	            String newFileName = System.currentTimeMillis() + "_"+ fileName;
	            Path targetLocation = this.fileStorageLocation.resolve( newFileName ); // concat fileName to the fileStorageLocation. 
	            log.debug( "fileName: " + fileName ); 
	            log.debug("targetLocation: " + targetLocation ); 
	            
	            Files.copy( file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING ); // Copy all bytes from an input stream to a file. 
	            // org.springframework.web.multipart.getInputStream() return an InputStream to read the contents of the file from. 

	            return newFileName; 

	        }catch( IOException e ){ 
	            throw new FileStorageException( "Couldn't store files " + fileName + ". Please try again." ); 

	        } 
	    }
	  
	  
	  public Resource loadFileAsResource(String fileName) {
	        try {
	            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
	            Resource resource = new UrlResource(filePath.toUri());
	            if(resource.exists()) {
	                return resource;
	            } else {
	                throw new FilesException("File not found " + fileName);
	            }
	        } catch (MalformedURLException ex) {
	            throw new FilesException("File not found " + fileName);
	        }
	    }
	  
	  
	  public Pictures saveImage (Pictures image){
		  
		  return imgrep.save(image);
	  }
}
