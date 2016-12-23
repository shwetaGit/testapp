package com.app.server.repository.appbasicsetup.usermanagement;
import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spartan.server.password.interfaces.PassRecoveryInterface;

@Repository
public class UserManagementRepositoryImpl implements UserManagementRepository {

	@Autowired
	private com.athena.server.pluggable.utils.helper.ResourceFactoryManagerHelper emfResource;

	@Override
	public List<PassRecoveryInterface> findByUserId(String userId) throws Exception {
		Query query = emfResource.getResource().createQuery("select e from PassRecovery e where e.user.userId=:userId");
		query.setParameter("userId", userId);
		return query.getResultList();
	}

	@Override
	public Object findQuestionById(String questionId) {
		Query query = emfResource.getResource().createQuery("select e.question from Question e where e.questionId =:questionId");
		query.setParameter("questionId", questionId);
		return query.getSingleResult();
	}

	@Override
	public boolean isCorrectAnswer(String passRecoveryId, String answer) throws Exception {
		Query query = emfResource.getResource().createQuery("select e from PassRecovery e where e.passRecoveryId=:passRecoveryId and e.answer=:answer");
		query.setParameter("passRecoveryId", passRecoveryId);
		query.setParameter("answer", answer);
		return query.getResultList().isEmpty() ? false : true;
	}

}
