package com.app.server.repository.appbasicsetup.usermanagement;
import java.util.List;

import com.spartan.server.password.interfaces.PassRecoveryInterface;

public interface UserManagementRepository {

	List<PassRecoveryInterface> findByUserId(String userId) throws Exception;

	Object findQuestionById(String questionId);

	boolean isCorrectAnswer(String passRecoveryId, String answer) throws Exception;

}
