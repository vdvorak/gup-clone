package ua.com.itproekt.gup.util;

import com.google.gson.Gson;
import ua.com.itproekt.gup.exception.GooglePlusException;
import ua.com.itproekt.gup.model.GooglePlusProfile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collection;
import java.util.Map;

public class GooglePlusAPI {

    public GooglePlusAPI(String API_KEY) {
        this.API_KEY = API_KEY;
    }
    private String API_KEY;

	public GPObject_PublicUser get( String UID ){
		String json = "";
		GPObject_PublicUser result = null;
		try {
			URLConnection gpAPI = new URL( "https://www.googleapis.com/plus/v1/people/" + UID + "?key=" + API_KEY ).openConnection();
			HttpURLConnection gpConnection = ( (HttpURLConnection) gpAPI );
			if( gpConnection.getResponseCode() != 200 ){
				BufferedReader reader = new BufferedReader( new InputStreamReader( gpConnection.getErrorStream() ) );
				String input;
				while( ( input = reader.readLine() ) != null ){
                    json += input;
                }
				reader.close();
				throw new GooglePlusException( json );
			}

			BufferedReader reader = new BufferedReader( new InputStreamReader( gpAPI.getInputStream() ) );
			String input;
			while( ( input = reader.readLine() ) != null ){
                json += input;
            }
			reader.close();

			Gson gson = new Gson();
            result = gson.fromJson( json, GPObject_PublicUser.class );
			
		}catch( GooglePlusException e ){
			e.show();
		}catch( Exception e ){
			System.err.println( "Google+ API Fatal Error: " + e.getMessage() );
		}
		
		return result;
	}

	public class GPObject_PublicUser implements GooglePlusProfile {
		private String kind;
		private String id;
		private String displayName;
		private String tagline;
		private String gender;
		private String aboutMe;
		private String url;
		private Map<String,String> image;
		private Collection<Map<String,String>> urls;
		private Collection<Map<String,String>> organizations;
		private Collection<Map<String,String>> placesLived;
        private String email;
		
		public String getKind(){ return kind; }
		public String getId(){ return id; }
		public String getDisplayName(){ return displayName; }
		public String getTagline(){ return tagline; }
		public String getGender(){ return gender; }
		public String getAboutMe(){ return aboutMe; }
		public String getURL(){ return url; }
		public Map<String,String> getImage(){ return image; }
		public Collection<Map<String,String>> getURLs(){ return urls; }
		public Collection<Map<String,String>> getOrganizations(){ return organizations; }
		public Collection<Map<String,String>> getPlacesLived(){ return placesLived; }
        public String getEmail(){ return email; }

    }
	
}

