package ua.com.gup.dao.profile;

/**
 * @see https://www.mkyong.com/mongodb/spring-data-mongodb-hello-world-example/
 */
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public class ProfileRepositoryTest2 {

    public static void main(String[] args) {

		/* For XML */
        ApplicationContext ctx = new GenericXmlApplicationContext("SpringConfig.xml");

		/* For Annotation */
//		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
        MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");

        MyUser user = new MyUser("mkyong", "password123");

		/* save */
        mongoOperation.save(user);

		/* now user object got the created id. */
        System.out.println("1. user : " + user);

		/* query to search user */
        Query searchUserQuery = new Query(Criteria.where("username").is("mkyong"));

		/* find the saved user again. */
        MyUser savedUser = mongoOperation.findOne(searchUserQuery, MyUser.class);
        System.out.println("2. find - savedUser : " + savedUser);

		/* update password */
        mongoOperation.updateFirst(searchUserQuery, Update.update("password", "new password"), MyUser.class);

		/* find the updated user object */
        MyUser updatedUser = mongoOperation.findOne(new Query(Criteria.where("username").is("mkyong")), MyUser.class);

        System.out.println("3. updatedUser : " + updatedUser);

		/* delete */
        mongoOperation.remove(searchUserQuery, MyUser.class);

		/* List, it should be empty now. */
        List<MyUser> listUser = mongoOperation.findAll(MyUser.class);
        System.out.println("4. Number of user = " + listUser.size());
    }
}
