package test1;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ElementCollection;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import java.util.List;

@Entity
@Table(name = "answers")
public class Answer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@ManyToOne
	public Question question;
	@Column(name="answer_text")
	public String answerText;
	@Column(name="is_correct")
	public boolean isCorrect;
	
	public Answer() {}
	public long getId() {
		return id;
	}
	public String getAnswerText() {
		return this.answerText;
	}
	public boolean getCorrect() {
		return this.isCorrect;
	}
	public void setAnswerText(String t) {
		this.answerText = t;
	}
	public void setCorrect(boolean c) {
		this.isCorrect = c;
	}
}
