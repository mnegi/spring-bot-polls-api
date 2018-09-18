package com.pollsapi.springboot.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.websystique.springboot.model.Question;
import com.websystique.springboot.service.QuestionService;
import com.websystique.springboot.util.CustomErrorType;

@RestController
@RequestMapping("/questions")
public class QuestionApiController {

	public static final Logger logger = LoggerFactory.getLogger(QuestionsApiController.class);

	@Autowired
	QuestionService questionService; //Service which will do all data retrieval/manipulation work

	// -------------------Retrieve All Questions---------------------------------------------

	@RequestMapping(value = "/question/", method = RequestMethod.GET)
	public ResponseEntity<List<Question>> listAllQuestions() {
		List<Question> questions = questionService.findAllQuestions();
		if (questions.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Question>>(questions, HttpStatus.OK);
	}

	// -------------------Retrieve Single Question------------------------------------------

	@RequestMapping(value = "/question/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getQuestion(@PathVariable("id") long id) {
		logger.info("Fetching Question with id {}", id);
		Question question = questionService.findById(id);
		if (question == null) {
			logger.error("Question with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Question with id " + id 
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Question>(question, HttpStatus.OK);
	}

	// -------------------Create a Question-------------------------------------------

	@RequestMapping(value = "/question/", method = RequestMethod.POST)
	public ResponseEntity<?> createQuestion(@RequestBody Question question, UriComponentsBuilder ucBuilder) {
		logger.info("Creating Question : {}", question);

		if (questionService.isQuestionExist(question)) {
			logger.error("Unable to create. A Question with name {} already exist", question.getName());
			return new ResponseEntity(new CustomErrorType("Unable to create. A Question with name " + 
			question.getName() + " already exist."),HttpStatus.CONFLICT);
		}
		questionService.saveQuestion(question);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/question/{id}").buildAndExpand(question.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a Question ------------------------------------------------

	@RequestMapping(value = "/question/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateQuestion(@PathVariable("id") long id, @RequestBody Question question) {
		logger.info("Updating Question with id {}", id);

		Question currentQuestion = questionService.findById(id);

		if (currentQuestion == null) {
			logger.error("Unable to update. Question with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to upate. Question with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}

		currentQuestion.setName(question.getName());
		currentQuestion.setAge(question.getAge());
		currentQuestion.setSalary(question.getSalary());

		questionService.updateQuestion(currentQuestion);
		return new ResponseEntity<Question>(currentQuestion, HttpStatus.OK);
	}

	// ------------------- Delete a Question-----------------------------------------

	@RequestMapping(value = "/question/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteQuestion(@PathVariable("id") long id) {
		logger.info("Fetching & Deleting Question with id {}", id);

		Question question = questionService.findById(id);
		if (question == null) {
			logger.error("Unable to delete. Question with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to delete. Question with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		questionService.deleteQuestionById(id);
		return new ResponseEntity<Question>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All Questions-----------------------------

	@RequestMapping(value = "/question/", method = RequestMethod.DELETE)
	public ResponseEntity<Question> deleteAllQuestions() {
		logger.info("Deleting All Questions");

		questionService.deleteAllQuestions();
		return new ResponseEntity<Question>(HttpStatus.NO_CONTENT);
	}

}