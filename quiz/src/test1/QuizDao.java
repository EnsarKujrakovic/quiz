package test1;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import test1.SecurityUtil;

final public class QuizDao extends AbstractDao {
	
	public void QuizDao() {
		
	}
	
	public List<Quiz> findAll() {
		EntityManager em = createEntityManager();
		Query q = em.createQuery("SELECT q FROM Quiz q");
		List<Quiz> resultList = q.getResultList();
		em.close();
		return resultList;
	}
	public Quiz findById(long id) {
		EntityManager em = createEntityManager();
		try {
			Query q = em.createQuery("SELECT q FROM Quiz q WHERE q.id= :id").setParameter("id", id);
			Quiz quiz = (Quiz) q.getSingleResult();
			return quiz;					
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		} finally {		
			if (em!= null) {
				em.close();
			}
		}		
		return null;
	}
	public List<Quiz> findByUserId(long id) {
		EntityManager em = createEntityManager();
		Query q = em.createQuery("SELECT q FROM Quiz q WHERE q.userId = :id").setParameter("id", id);
		List<Quiz> resultList = q.getResultList();
		em.close();
		return resultList;
	}
	public void save(Quiz quiz) {
		EntityManager em = createEntityManager();
		em.getTransaction().begin();
		em.persist(quiz);
		em.getTransaction().commit();
		em.close();	
	}
	public void remove(long id) {
		EntityManager em = createEntityManager();
		Quiz quiz = em.find(Quiz.class, id);
		em.getTransaction().begin();
		em.remove(quiz);
		em.getTransaction().commit();
		em.close();
	}
	public void addQuestion(Question question, long id) {
		EntityManager em = createEntityManager();
		Query q = em.createQuery("SELECT q FROM Quiz q WHERE q.id= :id").setParameter("id", id);
		//Quiz quiz = em.find(Quiz.class, id);
		Quiz quiz = (Quiz) q.getSingleResult();
		em.getTransaction().begin();
		quiz.questions.add(question);
		question.quiz=quiz;
		em.getTransaction().commit();
		em.close();
	}
	public void removeQuestion(long id, long quizId) {
		EntityManager em = createEntityManager();
		Question question = em.find(Question.class, id);
		Quiz quiz = em.find(Quiz.class, quizId);
		em.getTransaction().begin();
		em.remove(question);
		quiz.questions.remove(quiz.questions.indexOf(question));
		em.getTransaction().commit();
		em.close();
	}
	public void addAnswer(Answer answer, long id) {
		EntityManager em = createEntityManager();
		Query q = em.createQuery("SELECT q FROM Question q WHERE q.id= :id").setParameter("id", id);
		//Quiz quiz = em.find(Quiz.class, id);
		Question question = (Question) q.getSingleResult();
		em.getTransaction().begin();
		question.answers.add(answer);
		answer.question=question;
		em.getTransaction().commit();
		em.close();
	}
	public void removeAnswer(long id, long questionId) {
		EntityManager em = createEntityManager();
		Answer answer = em.find(Answer.class, id);
		Question question = em.find(Question.class, questionId);
		em.getTransaction().begin();
		em.remove(answer);
		question.answers.remove(question.answers.indexOf(answer));
		em.getTransaction().commit();
		em.close();
	}
	public Question findQuestionById(long id) {
		EntityManager em = createEntityManager();
		try {
			Query q = em.createQuery("SELECT q FROM Question q WHERE q.id= :id").setParameter("id", id);
			Question question = (Question) q.getSingleResult();
			return question;					
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		} finally {		
			if (em!= null) {
				em.close();
			}
		}		
		return null;
	}
	public Answer findAnswerById(long id) {
		EntityManager em = createEntityManager();
		try {
			Query q = em.createQuery("SELECT a FROM Answer a WHERE a.id= :id").setParameter("id", id);
			Answer answer = (Answer) q.getSingleResult();
			return answer;					
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		} finally {		
			if (em!= null) {
				em.close();
			}
		}		
		return null;
	}
	public void editAnswer(long id, String aText, boolean isCorrect) {
		EntityManager em = createEntityManager();
		Answer a = em.find(Answer.class, id);
		em.getTransaction().begin();
		a.setAnswerText(aText);
		a.setCorrect(isCorrect);
		em.getTransaction().commit();
	}
	public void moveQuestion(long id, long quizId, String direction) {
		EntityManager em = createEntityManager();
		Quiz quiz = em.find(Quiz.class, quizId);
		Question question = em.find(Question.class, id);
		int index = quiz.questions.indexOf(question);
	/*	if((direction.equals("up") && index == 0) || (direction.equals("down") && index == quiz.questions.size()-1)) {
			em.close();
			return;
		}*/
		if(direction.equals("up")) {
		//	if(index > 0) {
				Question question2 = quiz.questions.get(index-1);
				em.getTransaction().begin();
				
			//	quiz.questions.set(index-1, question2);
			//	quiz.questions.set(index, question);
				em.merge(quiz);
				em.getTransaction().commit();
	//		}
		}
		em.close();
	}
	public List<Result> findAllResults() {
		EntityManager em = createEntityManager();
		Query q = em.createQuery("SELECT r FROM Result r");
		List<Result> resultList = q.getResultList();
		em.close();
		return resultList;
	}
	public List<Result> findResultsById(long userId) {
		EntityManager em = createEntityManager();
		Query q = em.createQuery("SELECT r FROM Result r WHERE r.userId =:userId").setParameter("userId", userId);
		List<Result> resultList = q.getResultList();
		em.close();
		return resultList;
	}
	public void saveResult(Result r) {
		EntityManager em = createEntityManager();
		em.getTransaction().begin();
		em.persist(r);
		em.getTransaction().commit();
	}
	public void changePassword(long id, String pass) {
		EntityManager em = createEntityManager();
		String hashPassword = SecurityUtil.hashPassword(pass);
		User user = em.find(User.class, id);
		em.getTransaction().begin();
		user.setPassword(hashPassword);
		em.getTransaction().commit();
		em.close();
	}
}