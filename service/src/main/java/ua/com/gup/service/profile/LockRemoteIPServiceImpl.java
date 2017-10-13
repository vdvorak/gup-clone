package ua.com.gup.service.profile;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.gup.mongo.model.profiles.LockRemoteIP;
import ua.com.gup.repository.profile.LockRemoteIPRepository;

@Service
public class LockRemoteIPServiceImpl implements LockRemoteIPService {

    @Autowired
    private LockRemoteIPRepository lockRemoteIPRepository;

    @Override
    public boolean findLockRemoteIPByIpAndUpdateLastTryLoginDate(String ip){
        LockRemoteIP lockRemoteIP = findLockRemoteIPByIp(ip);
        if (lockRemoteIP!=null){
            lockRemoteIP.setLastTryLoginDateEqualsToCurrentDate();
            lockRemoteIPRepository.updateLockRemoteIP(lockRemoteIP);
            return lockRemoteIP.isUnlockAccount();
        }
        return true;
    }

    @Override
    public void createLockRemoteIP(LockRemoteIP lockRemoteIP) {
        lockRemoteIPRepository.createLockRemoteIP(lockRemoteIP);
    }

    @Override
    public void updateLockRemoteIP(LockRemoteIP lockRemoteIP) {
        lockRemoteIPRepository.updateLockRemoteIP(lockRemoteIP);
    }

    @Override
    public LockRemoteIP findLockRemoteIPById(String id) {
        return lockRemoteIPRepository.findLockRemoteIPById(id);
    }

    @Override
    public boolean lockRemoteIPExists(String id) {
        return (lockRemoteIPRepository.findLockRemoteIPById(id)!=null) ? true : false;
    }

    @Override
    public LockRemoteIP findLockRemoteIPByIp(String ip) {
        return lockRemoteIPRepository.findLockRemoteIPByIp(ip);
    }
}
