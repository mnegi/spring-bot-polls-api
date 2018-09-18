package com.pollsapi.springboot.service;
import java.util.List;
import com.pollsapi.springboot.model.Choice;

public interface ChoiceService {
	Choice findById(long id);
	Choice findByName(String name);
	void saveChoice(Choice Choice);
	void updateChoice(Choice Choice);
	void deleteChoiceById(long id);
	List<Choice> findAllChoices(long questionId);
	void deleteAllChoices(long questionId);
	boolean isChoiceExist(Choice Choice, long questionId);
}
