package com.app.server.repository.appbasicsetup.usermanagement;
import com.app.server.repository.core.CommonUtilRepository;
import com.spartan.server.interfaces.LoginSessionRepositoryInterface;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;

@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "2", comments = "Repository for LoginSession Transaction table", complexity = Complexity.MEDIUM)
public interface LoginSessionRepository<T> extends CommonUtilRepository, LoginSessionRepositoryInterface {
}
