package it.marco.spring.boot2.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import it.marco.spring.boot2.model.Image;
import it.marco.spring.boot2.service.ImageService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class HomeController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);
	
    private static final String API_BASE_PATH = "/api";
	
	private static final String BASE_PATH = "/images";       
	
	private static final String FILENAME = "{filename:.+}";
	
    private final ImageService imageService;        
    
    public HomeController(ImageService imageService) {         
    	this.imageService = imageService;      
    } 
	
	@GetMapping(API_BASE_PATH + "/images")     
	Flux<Image> images() {       
		return Flux.just(         
					new Image("1", "learning-spring-boot-cover.jpg"),         
					new Image("2", "learning-spring-boot-2nd-edition-cover.jpg"),         
					new Image("3", "bazinga.png")       
				);     
		}
	
	@PostMapping(API_BASE_PATH + "/images")     
	Mono<Void> create(@RequestBody Flux<Image> images) {       
		return images.map(image -> {          
			LOGGER.info("We will save " + image + " to a Reactive database soon!");           
			return image;        
		})        
		.then();     
	}

}
