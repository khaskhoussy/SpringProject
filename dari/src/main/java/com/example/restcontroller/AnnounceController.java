package com.example.restcontroller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.entity.Announce;
import com.example.entity.CommentsAnnonce;

import com.example.service.AnnounceService;
import com.example.service.FileStorageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@RequestMapping("/user/announce")
public class AnnounceController {
	
	 private static final Logger logger = LoggerFactory.getLogger(AnnounceController.class);
	@Autowired
	AnnounceService As;
    @Autowired 
    private FileStorageService fileStorageService; 
	
	
	  @PostMapping("/createAd")
	  @ResponseBody
	  public void ajouterAnnonce(@RequestBody Announce ad,HttpServletResponse response,HttpServletRequest request)
	  {
		  
		  As.ajouterAnnonce(ad,Home.connectedUser);
	  }
	  
	  @DeleteMapping("/deleteadbyId/{id}") 
	  @ResponseBody 
		public void deleteReservationById(@PathVariable("id") int id,HttpServletResponse response,HttpServletRequest request) {
			As.deleteAdById(id,Home.connectedUser);
			
		}
	  @RequestMapping(value="/allAds")
	    public List<Announce> getAllAds(HttpServletResponse response,HttpServletRequest request) 
	    {
	    	return As.findAll();
	    }
	  
	    @RequestMapping(value="/adbyuser")
	    public List<Announce> findAnnounceByUser(HttpServletResponse response,HttpServletRequest request) 
	    {
	    	return As.findAnnounceByUser(Home.connectedUser);
	    }
	  

		  @PostMapping("/addcomment/{idannounce}")
		  @ResponseBody
		  public String ajouterComment(@RequestBody CommentsAnnonce com ,@PathVariable("idannounce") int idannounce,HttpServletResponse response,HttpServletRequest request)
		  {
			  
			  return As.ajouterComment(idannounce, Home.connectedUser, com);
		
		  }

		  
		
			@PostMapping("/ajouterAnnonce")
			@ResponseBody
			  public String uploadFile( @RequestParam("file") List <MultipartFile> file,
			    		@RequestParam(value = "annonce", required = true) String adJson ) throws JsonMappingException, JsonProcessingException, IOException{
							
				As.ajouterAd(adJson, Home.connectedUser, file);
				  return adJson;
							
							
                  
			}
			
			@GetMapping("/downloadFile/{fileName:.+}")
		    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
		        // Load file as Resource
		        Resource resource = fileStorageService.loadFileAsResource(fileName);

		        // Try to determine file's content type
		        String contentType = null;
		        try {
		            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		        } catch (IOException ex) {
		            logger.info("Could not determine file type.");
		        }

		        // Fallback to the default content type if type could not be determined
		        if(contentType == null) {
		            contentType = "application/octet-stream";
		        }

		        return ResponseEntity.ok()
		                .contentType(MediaType.parseMediaType(contentType))
		                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
		                .body(resource);
		    }
			 @GetMapping(value = "/{idann}")
			   @ResponseBody
			   public Announce getaddById(@PathVariable("idann")int annId) {
					return As.getAddById(annId);
				}

		
			
}
