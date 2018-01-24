/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.com.gup.server.security;

import ua.com.gup.service.security.UserSecurityAccessService;

import java.util.Set;

public abstract class ObjectPermissionEvaluator {
    
    protected UserSecurityAccessService accessService;
    protected String userId;
    protected Set<String> authorities;

    public ObjectPermissionEvaluator(String userId, Set<String> authorities) {
        this.authorities = authorities;
        this.userId = userId;
    }

    public abstract String getObjectId();

    public abstract String getCollectionName();

    public abstract String getOwnerPropertyName();

    protected boolean isOwner() {
        return accessService.isOwner(userId, getObjectId(), getCollectionName(), getOwnerPropertyName());
    }

    public abstract Boolean hasAccess();

    public Set<String> getAuthorities() {
        return authorities;
    }

    public UserSecurityAccessService getAccessService() {
        return accessService;
    }

    public void setAccessService(UserSecurityAccessService accessService) {
        this.accessService = accessService;
    }
    
    
}
