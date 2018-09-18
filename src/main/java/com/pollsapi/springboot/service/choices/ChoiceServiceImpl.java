package com.pollsapi.springboot.service;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Service;
import com.pollsapi.springboot.model.Choice;

@Service("choiceService")
public class ChoiceServiceImpl implements ChoiceService{
	
	private static final AtomicLong counter = new AtomicLong();
	private static List<Choice> choices;
	static{
		choices= populateDummyChoices();
	}

	public List<Choice> findAllChoices() {
		return choices;
	}
	
	public Choice findById(long id) {
		for(Choice choice : choices){
			if(choice.getId() == id){
				return choice;
			}
		}
		return null;
	}
	
	public Choice findByName(String name) {
		for(Choice choice : choices){
			if(choice.getName().equalsIgnoreCase(name)){
				return choice;
			}
		}
		return null;
	}
	
	public void saveChoice(Choice choice) {
		choice.setId(counter.incrementAndGet());
		choices.add(choice);
	}

	public void updateChoice(Choice choice) {
		int index = choices.indexOf(choice);
		choices.set(index, choice);
	}

	public void deleteChoiceById(long id) {
		
		for (Iterator<Choice> iterator = choices.iterator(); iterator.hasNext(); ) {
		    Choice choice = iterator.next();
		    if (choice.getId() == id) {
		        iterator.remove();
		    }
		}
	}

	public boolean isChoiceExist(Choice choice) {
		return findByName(choice.getName())!=null;
	}
	
	public void deleteAllChoices(){
		choices.clear();
	}

	private static List<Choice> populateDummyChoices(){
		List<Choice> choices = new ArrayList<Choice>();
		choices.add(new Choice(counter.incrementAndGet(),"Sam",30, 70000));
		choices.add(new Choice(counter.incrementAndGet(),"Tom",40, 50000));
		choices.add(new Choice(counter.incrementAndGet(),"Jerome",45, 30000));
		choices.add(new Choice(counter.incrementAndGet(),"Silvia",50, 40000));
		return choices;
	}

}
