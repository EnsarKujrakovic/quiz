package test1;
import javax.persistence.CascadeType;
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
@Table(name = "questions")
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@ManyToOne
	public Quiz quiz;
	@Column(name="question_text")
	private String questionText;
	@OneToMany(mappedBy="question", cascade = {CascadeType.ALL}, orphanRemoval=true)
	public List<Answer> answers;
	public Question() {}
	public long getId() {
		return id;
	}
	public String getQuestionText() {
		return questionText;
	}
	public void setQuestionText(String t) {
		this.questionText = t;
	}
}
