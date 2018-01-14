package com.sg.entity.extra;

import com.sg.alg_csp.Condition;
import com.sg.entity.Lesson;

public class LessonTotal {
	
	private Lesson lesson;
	private int amount;
	private Condition condition;
	
	public LessonTotal(Lesson lesson, int amount, Condition condition) {
		this.lesson = lesson;
		this.amount = amount;
		this.condition = condition;
	}

	public Lesson getLesson() {
		return lesson;
	}

	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Condition getCondition() {
		return condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}

}
