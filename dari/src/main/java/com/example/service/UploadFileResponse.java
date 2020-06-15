package com.example.service;

public class UploadFileResponse {
	
	 private String fileName; 
	    private String fileDownloadURI; 
	    private String fileType; 
	    private long size; 

	    public UploadFileResponse(String fileName, String fileDownloadUri, String fileType, long size) {
	        this.fileName = fileName;
	        this.fileDownloadURI = fileDownloadUri;
	        this.fileType = fileType;
	        this.size = size;
	    }

	    // setter 
	    public void setFileName( String s ){ 
	        this.fileName = s; 
	    } 

	    public void setFileDownloadURI( String s ){ 
	        this.fileDownloadURI = s; 
	    } 

	    public void setFileType( String s ){ 
	        this.fileType = s; 
	    } 

	    public void setSize( long s ){ 
	        this.size = s; 
	    } 

	    // getter 
	    public String getFileName(){ 
	        return this.fileName; 
	    } 

	    public String getFileDownloadURI(){ 
	        return this.fileDownloadURI; 
	    } 

	    public String getFileType(){ 
	        return this.fileType; 
	    } 

	    public long getSize(){ 
	        return this.size; 
	    } 


}
