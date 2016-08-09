package controller;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import com.vtcmer.app.model.entities.User;

public class TestRestUserController {
	
	  public static final String REST_SERVICE_URI = "http://localhost:8080/SpringRestApiI/api/v1";

	    RestTemplate restTemplate = new RestTemplate();

	    

	    @Test
	    public void findAllUsers() {

	        final List<LinkedHashMap<String, Object>> usersMap = this.restTemplate.getForObject(REST_SERVICE_URI + "/user/", List.class);
	        Assert.assertNotNull(usersMap);

	    }

	    @Test
	    public void getUser() {

	        final User user = this.restTemplate.getForObject(REST_SERVICE_URI + "/user/1", User.class);
	        Assert.assertNotNull(user);

	    }

	    @Test
	    public void createUser() {

	        final User user = new User(0, "Sarah", 51, 134);

	        final URI uri = this.restTemplate.postForLocation(REST_SERVICE_URI + "/user/", user, User.class);
	        Assert.assertNotNull(uri);

	    }

	    @Test
	    public void updateUser() {

	        final User user = new User(1, "Tomy", 33, 70000);
	        this.restTemplate.put(REST_SERVICE_URI + "/user/1", user);
	        Assert.assertNotNull(user);

	    }

	    @Test
	    public void deleteUser() {

	        this.restTemplate.delete(REST_SERVICE_URI + "/user/3");

	    }


}
