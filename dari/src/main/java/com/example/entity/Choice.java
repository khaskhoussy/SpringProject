package com.example.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Choice implements Serializable {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String optionss;
    
    private Long score = 0L;
    
    @ManyToOne
    @JsonIgnore
    private Poll poll;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public String getOptionss() {
		return optionss;
	}

	public void setOptionss(String optionss) {
		this.optionss = optionss;
	}

	public Long getScore() {
		return score;
	}

	public void setScore(Long score) {
		this.score = score;
	}

	public Poll getPoll() {
		return poll;
	}

	public void setPoll(Poll poll) {
		this.poll = poll;
	}

	public Choice(String optionss, Long score) {
		super();
		this.optionss = optionss;
		this.score = score;
	}

	@Override
	public String toString() {
		return "Choice [id=" + id + ", optionss=" + optionss + ", score=" + score + ", poll=" + poll + "]";
	}

	public Choice() {
		super();
	}
    
    
    

}
