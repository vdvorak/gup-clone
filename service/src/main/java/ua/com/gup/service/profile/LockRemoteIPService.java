package ua.com.itproekt.gup.service.profile;


import ua.com.itproekt.gup.model.profiles.LockRemoteIP;

public interface LockRemoteIPService {

    /**
     * Find private profile by ip and update try login date.
     *
     * @param ip                        - the profile ip.
     * @return                          - the ProfileInfo object.
     */
    boolean findLockRemoteIPByIpAndUpdateLastTryLoginDate(String ip);

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
     * LockRemoteIP exists boolean.
     *
     * @param id the id
     * @return the boolean
     */
    boolean lockRemoteIPExists(String id);

    /**
     * Find lockRemoteIP.
     *
     * @param ip the ip
     * @return the lockRemoteIP
     */
    LockRemoteIP findLockRemoteIPByIp(String ip);
}
