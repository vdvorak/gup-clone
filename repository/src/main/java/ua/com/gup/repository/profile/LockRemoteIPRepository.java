package ua.com.gup.repository.profile;


import ua.com.gup.mongo.model.profiles.LockRemoteIP;

public interface LockRemoteIPRepository {

    /**
     * Create lockRemoteIP.
     *
     * @param lockRemoteIP the lockRemoteIP
     */
    void createLockRemoteIP(LockRemoteIP lockRemoteIP);

    /**
     * Update lockRemoteIP.
     *
     * @param lockRemoteIP the lockRemoteIP
     */
    void updateLockRemoteIP(LockRemoteIP lockRemoteIP);

    /**
     * Find lockRemoteIP.
     *
     * @param id the id
 * @return the lockRemoteIP
     */
    LockRemoteIP findLockRemoteIPById(String id);

    /**
     * Find lockRemoteIP.
     *
     * @param ip the ip
     * @return the lockRemoteIP
     */
    LockRemoteIP findLockRemoteIPByIp(String ip);

    /**
     * LockRemoteIP exists boolean.
     *
     * @param id the id
     * @return the boolean
     */
    boolean lockRemoteIPExists(String id);
}
