package com.app.server.repository.appbasicsetup.usermanagement;
import com.app.server.repository.core.CommonUtilRepository;
import com.spartan.server.interfaces.UserRepositoryInterface;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import java.util.List;
import com.app.shared.appbasicsetup.usermanagement.PassRecovery;

@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "2", comments = "Repository for UserDetail Transaction table", complexity = Complexity.MEDIUM)
public interface UserDetailRepository<T> extends CommonUtilRepository, UserRepositoryInterface {

    List<T> findAll() throws Exception;

    long getTotalPageCount() throws Exception;

    List<T> findPageWiseData(Integer pageSize, Integer pageNo) throws Exception;

    T save(T entity) throws Exception;

    List<T> save(List<T> entity) throws Exception;

    void delete(String id) throws Exception;

    void remove(String id) throws Exception;

    public void deletePassRecovery(List<PassRecovery> passrecovery);

    void update(T entity) throws Exception;

    List<T> findByUserAccessLevelId(String userAccessLevelId) throws Exception;

    List<T> findByUserAccessDomainId(String userAccessDomainId) throws Exception;

    T findById(String userId) throws Exception;
}
