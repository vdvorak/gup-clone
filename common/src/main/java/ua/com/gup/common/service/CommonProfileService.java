package ua.com.gup.common.service;

import ua.com.gup.common.model.mongo.CommonProfile;

public interface CommonProfileService<T extends CommonProfile> {

    T findById(String id);


}
