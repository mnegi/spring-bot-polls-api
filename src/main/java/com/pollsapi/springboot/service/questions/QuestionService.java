package com.pollsapi.springboot.service;
import java.util.List;
import com.pollsapi.springboot.model.Question;

public interface QuestionService {
	Question findById(long id);
	Question findByName(String name);
	void saveQuestion(Question Question);
	void updateQuestion(Question Question);
	void deleteQuestionById(long id);
	List<Question> findAllQuestions();
	void deleteAllQuestions();
	boolean isQuestionExist(Question Question);
}
