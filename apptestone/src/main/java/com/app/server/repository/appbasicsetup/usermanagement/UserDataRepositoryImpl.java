package com.app.server.repository.appbasicsetup.usermanagement;
import com.app.server.repository.core.CommonUtilRepositoryImpl;
import com.athena.server.pluggable.utils.helper.ResourceFactoryManagerHelper;
import com.spartan.server.interfaces.UserAuthentication;
import com.spartan.server.interfaces.UserDataInterface;
import com.spartan.server.interfaces.UserDataRepositoryInterface;
import java.lang.Override;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDataRepositoryImpl extends CommonUtilRepositoryImpl implements UserDataRepositoryInterface {

    @Autowired
    private ResourceFactoryManagerHelper emfResource;

    @Transactional
    @Override
    public void save(UserAuthentication userAuthentication, String password) throws Exception {
        com.app.shared.appbasicsetup.usermanagement.UserData entity = new com.app.shared.appbasicsetup.usermanagement.UserData();
        entity.setPassword(password);
        entity.setUserDetail(userAuthentication.getUserDetail());
        entity.setSystemInformation(com.athena.server.pluggable.interfaces.CommonEntityInterface.RECORD_TYPE.ADD);
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.persist(entity);
    }

    @Override
    @Transactional
    public void update(UserDataInterface userData) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        javax.persistence.Query query = emanager.createQuery("update UserData u set u.password=:password, u.oneTimePassword=:oneTimePassword, u.oneTimePasswordExpiry=:oneTimePasswordExpiry, u.oneTimePasswordGenDate=:oneTimePasswordGenDate, u.last5Passwords=:last5Passwords where u.userDataId=:userDataId");
        query.setParameter("password", userData.getPassword());
        query.setParameter("oneTimePassword", userData.getOneTimePassword());
        query.setParameter("oneTimePasswordExpiry", userData.getOneTimePasswordExpiry());
        query.setParameter("oneTimePasswordGenDate", userData.getOneTimePasswordGenDate());
        query.setParameter("last5Passwords", userData.getLast5Passwords());
        query.setParameter("userDataId", userData.getUserDataId());
        int updateVal = query.executeUpdate();
    }

    @Override
    @Transactional
    public UserDataInterface findById(String userDataId) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        javax.persistence.Query query = emanager.createNamedQuery("UserData.findById");
        query.setParameter("userDataId", userDataId);
        return (UserDataInterface) query.getSingleResult();
    }

    @Override
    @Transactional
    public UserDataInterface findByUserId(String userId) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        javax.persistence.Query query = emanager.createNamedQuery("UserData.findByUserId");
        query.setParameter("userId", userId);
        return (UserDataInterface) query.getSingleResult();
    }
}
