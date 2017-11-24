package ua.com.gup.service.security;


public interface UserSecurityAccessService {

    public boolean isOwner(String ownerId, String objectId, String collectionName, String propertyName);
}
