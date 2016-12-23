package com.app.server.businessservice.appbasicsetup.usermanagement;
import com.app.server.repository.appbasicsetup.usermanagement.UserDetailRepository;
import com.app.shared.appbasicsetup.usermanagement.UserDetail;
import java.lang.Override;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class UserDetailBusinessServiceImpl implements UserDetailBusinessService {

    @Autowired
    private UserDetailRepository userDetailRepository;

    /**
     * Update the <UserDetail> object
     * @Params Object of UserDetail
     * @throws java.lang.Exception
     */
    @Override
    public void update(UserDetail entity) throws Exception {
        if (entity.isHardDelete()) {
            userDetailRepository.delete(entity.getUserId());
        } else {
            userDetailRepository.deletePassRecovery(entity.getDeletedPassRecoveryList());
            userDetailRepository.update(entity);
        }
    }

    /**
     * Update the list of <UserDetail> object
     * @Params List of UserDetail Object
     * @throws java.lang.Exception
     */
    @Override
    public void update(List<UserDetail> entity) throws Exception {
        for (UserDetail userdetail : entity) {
            if (userdetail.isHardDelete()) {
                userDetailRepository.delete(userdetail.getUserId());
            } else {
                userDetailRepository.deletePassRecovery(userdetail.getDeletedPassRecoveryList());
                userDetailRepository.update(userdetail);
            }
        }
    }
}
