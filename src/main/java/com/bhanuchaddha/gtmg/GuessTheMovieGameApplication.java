package com.bhanuchaddha.gtmg;

import com.bhanuchaddha.gtmg.quiz.model.Question;
import com.bhanuchaddha.gtmg.quiz.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class GuessTheMovieGameApplication {

	public static void main(String[] args) {
		SpringApplication.run(GuessTheMovieGameApplication.class, args);
	}

	@Autowired
	private QuestionRepository questionRepository;

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		// Do any additional configuration here
		return builder.build();
	}

	@EventListener(ApplicationReadyEvent.class)
	public void populateQuestions(){
		Question q1 = new Question();
		q1.setDescription("What is the capital of Denmark.");
		q1.addOption("Arhus",false);
		q1.addOption("Copenhagen",true);
		q1.addOption("Amager",false);
		q1.addOption("Lyngby",false);

		questionRepository.save(q1);
	}
}
