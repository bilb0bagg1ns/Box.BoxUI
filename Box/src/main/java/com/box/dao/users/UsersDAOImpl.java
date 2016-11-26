package com.box.dao.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.box.config.SpringMongoConfig;
import com.box.model.User;

/**
 * Temporary - Used in reference implementation logic in HomeController.
 * 
 * @author mike.prasad
 *
 */
public class UsersDAOImpl implements UsersDAO{

	@Autowired
	private ApplicationContext appContext;

	@Autowired
	private MongoOperations mongoOperation;
		
	public UsersDAOImpl() {

        appContext = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
        mongoOperation = (MongoOperations)appContext.getBean("mongoTemplate");
        
    	
//        String[] beanNames = appContext.getBeanDefinitionNames();
//        Arrays.sort(beanNames);
//        for (String beanName : beanNames) {
//            System.out.println(beanName);
//        }		

		
	}

	@Override
	public void save (User user) {
        // save
        mongoOperation.save(new User("James", "Bond"), "Users");
	}
	

	@Override
	public User findByUserNamePassword(String userName, String password) {
		User savedUser = null;
		
    	// query to search user
    	Query searchUserQuery = new Query(Criteria.where("userName").is(userName).and("password").is(password));

    	// find the saved user again.
    	savedUser = mongoOperation.findOne(searchUserQuery, User.class, "Users");
    	System.out.println("2. find - user : " + savedUser); 
    	
    	return savedUser;
	}

}
