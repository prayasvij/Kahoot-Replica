package com.bhanuchaddha.gtmg;

import com.bhanuchaddha.gtmg.quiz.QuizGenerator;
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
	private QuizGenerator quizGenerator;


	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		// Do any additional configuration here
		return builder.build();
	}

	@EventListener(ApplicationReadyEvent.class)
	public void populateQuestions(){
		quizGenerator.makeQuiz();
	}
}
