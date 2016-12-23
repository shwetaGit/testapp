package com.app.server.repository.appbasicsetup.userrolemanagement;
import com.app.server.repository.core.CommonUtilRepository;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import java.util.List;
import com.app.shared.appbasicsetup.userrolemanagement.RoleMenuBridge;

@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "2", comments = "Repository for Roles Transaction table", complexity = Complexity.MEDIUM)
public interface RolesRepository<T> extends CommonUtilRepository {

    List<T> findAll() throws Exception;

    long getTotalPageCount() throws Exception;

    List<T> findPageWiseData(Integer pageSize, Integer pageNo) throws Exception;

    T save(T entity) throws Exception;

    List<T> save(List<T> entity) throws Exception;

    void delete(String id) throws Exception;

    void remove(String id) throws Exception;

    public void deleteRoleMenuBridge(List<RoleMenuBridge> rolemenubridge);

    void update(T entity) throws Exception;

    void update(List<T> entity) throws Exception;

    T findById(String roleId) throws Exception;
}
