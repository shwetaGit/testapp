package com.app.server.repository.organization.contactmanagement;
import com.app.server.repository.core.CommonUtilRepository;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import java.util.List;
import com.app.shared.organization.locationmanagement.Address;

@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "2", comments = "Repository for CoreContacts Transaction table", complexity = Complexity.MEDIUM)
public interface CoreContactsRepository<T> extends CommonUtilRepository {

    List<T> findAll() throws Exception;

    long getTotalPageCount() throws Exception;

    List<T> findPageWiseData(Integer pageSize, Integer pageNo) throws Exception;

    T save(T entity) throws Exception;

    List<T> save(List<T> entity) throws Exception;

    void delete(String id) throws Exception;

    void remove(String id) throws Exception;

    public void deleteAddress(List<Address> address);

    void update(T entity) throws Exception;

    void update(List<T> entity) throws Exception;

    List<T> findByTitleId(String titleId) throws Exception;

    List<T> findByNativeLanguageCode(String nativeLanguageCode) throws Exception;

    List<T> findByGenderId(String genderId) throws Exception;

    List<T> findByTimeZoneId(String timeZoneId) throws Exception;

    T findById(String contactId) throws Exception;
}
