package com.pollsapi.springboot;
 
import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;

import com.pollsapi.springboot.model.Question;
import org.springframework.web.client.RestTemplate;
 

public class PollsRestTestClient {
 
    public static final String REST_SERVICE_URI = "http://localhost:8080/SpringBootRestApi/api";
     
    /* GET */
    @SuppressWarnings("unchecked")
    private static void listAllQuestions(){
        System.out.println("Testing listAllQuestions API-----------");
         
        RestTemplate restTemplate = new RestTemplate();
        List<LinkedHashMap<String, Object>> questionsMap = restTemplate.getForObject(REST_SERVICE_URI+"/question/", List.class);
         
        if(questionsMap!=null){
            for(LinkedHashMap<String, Object> map : questionsMap){
                System.out.println("Question : id="+map.get("id")+", Name="+map.get("name")+", Age="+map.get("age")+", Salary="+map.get("salary"));;
            }
        }else{
            System.out.println("No question exist----------");
        }
    }
     
    /* GET */
    private static void getQuestion(){
        System.out.println("Testing getQuestion API----------");
        RestTemplate restTemplate = new RestTemplate();
        Question question = restTemplate.getForObject(REST_SERVICE_URI+"/question/1", Question.class);
        System.out.println(question);
    }
     
    /* POST */
    private static void createQuestion() {
        System.out.println("Testing create Question API----------");
        RestTemplate restTemplate = new RestTemplate();
        Question question = new Question(0,"Sarah",51,134);
        URI uri = restTemplate.postForLocation(REST_SERVICE_URI+"/question/", question, Question.class);
        System.out.println("Location : "+uri.toASCIIString());
    }
 
    /* PUT */
    private static void updateQuestion() {
        System.out.println("Testing update Question API----------");
        RestTemplate restTemplate = new RestTemplate();
        Question question  = new Question(1,"Tomy",33, 70000);
        restTemplate.put(REST_SERVICE_URI+"/question/1", question);
        System.out.println(question);
    }
 
    /* DELETE */
    private static void deleteQuestion() {
        System.out.println("Testing delete Question API----------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URI+"/question/3");
    }
 
 
    /* DELETE */
    private static void deleteAllQuestions() {
        System.out.println("Testing all delete Questions API----------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URI+"/question/");
    }
 
    public static void main(String args[]){
        listAllQuestions();
        getQuestion();
        createQuestion();
        listAllQuestions();
        updateQuestion();
        listAllQuestions();
        deleteQuestion();
        listAllQuestions();
        deleteAllQuestions();
        listAllQuestions();
    }
}