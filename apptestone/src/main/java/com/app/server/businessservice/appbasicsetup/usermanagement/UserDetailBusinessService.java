package com.app.server.businessservice.appbasicsetup.usermanagement;
import com.app.shared.appbasicsetup.usermanagement.UserDetail;
import java.util.List;

public interface UserDetailBusinessService {

    void update(UserDetail entity) throws Exception;

    void update(List<UserDetail> entity) throws Exception;
}
