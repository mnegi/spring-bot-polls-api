package com.pollsapi.springboot.service;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Service;
import com.pollsapi.springboot.model.Question;

@Service("questionService")
public class QuestionServiceImpl implements QuestionService{
	
	private static final AtomicLong counter = new AtomicLong();
	private static List<Question> questions;
	static{
		questions= populateDummyQuestions();
	}

	public List<Question> findAllQuestions() {
		return questions;
	}
	
	public Question findById(long id) {
		for(Question question : questions){
			if(question.getId() == id){
				return question;
			}
		}
		return null;
	}
	
	public Question findByName(String name) {
		for(Question question : questions){
			if(question.getName().equalsIgnoreCase(name)){
				return question;
			}
		}
		return null;
	}
	
	public void saveQuestion(Question question) {
		question.setId(counter.incrementAndGet());
		questions.add(question);
	}

	public void updateQuestion(Question question) {
		int index = questions.indexOf(question);
		questions.set(index, question);
	}

	public void deleteQuestionById(long id) {
		
		for (Iterator<Question> iterator = questions.iterator(); iterator.hasNext(); ) {
		    Question question = iterator.next();
		    if (question.getId() == id) {
		        iterator.remove();
		    }
		}
	}

	public boolean isQuestionExist(Question question) {
		return findByName(question.getName())!=null;
	}
	
	public void deleteAllQuestions(){
		questions.clear();
	}

	private static List<Question> populateDummyQuestions(){
		List<Question> questions = new ArrayList<Question>();
		questions.add(new Question(counter.incrementAndGet(),"Sam",30, 70000));
		questions.add(new Question(counter.incrementAndGet(),"Tom",40, 50000));
		questions.add(new Question(counter.incrementAndGet(),"Jerome",45, 30000));
		questions.add(new Question(counter.incrementAndGet(),"Silvia",50, 40000));
		return questions;
	}

}
