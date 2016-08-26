package ua.com.itproekt.gup;

import ua.com.itproekt.gup.util.API;

/**
 * email | UID | ACCESS_TOKEN | API_KEY | socWendor [gup.com.ua : graph.facebook.com : www.googleapis.com : vkontakte.ru]
 */
public class APITest {

	public static void main( String[] args ){
        API api = new API();

        final String FACEBOOK_WENDOR = "graph.facebook.com";
        final String FACEBOOK_ACCESS_TOKEN = "EAACEdEose0cBAEwVRP9cIjlnay1Pw4RjRCVlZCT4Hi6nUdeQdfLV77bZAykSa4sEbXz7lG6LUlIDo1byBd76OX2zlBFYuyTk15L7bcYsZBSXzToDiFZB2xOsfMZAqr4309UHdZBHfDeHYn3pn0VoyVvVDLK7rGDaRstwXakkCpZBAZDZD";
        final String FACEBOOK_UID = "1077154112339703";   // "1106460174";
        api.getProfile(FACEBOOK_WENDOR, FACEBOOK_ACCESS_TOKEN, FACEBOOK_UID);

        final String GOOGLEPLUS_WENDOR = "www.googleapis.com";
        final String GOOGLEPLUS_API_KEY = "AIzaSyAMjpEzLQJDYZdrgBGp-zNQ27xjw_6xEDA";
        final String GOOGLEPLUS_UID = "107235630368984173445"; // "117976156812233500456";
        api.getProfile(GOOGLEPLUS_WENDOR, GOOGLEPLUS_API_KEY, GOOGLEPLUS_UID);

        api.getProfile(null, "bbbbb@bbb.com", "123456");
        api.getProfile("bbbbb@bbb.com", "123456");
	}

}