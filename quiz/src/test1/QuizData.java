package test1;

import java.util.ArrayList;
import java.util.List;

public class QuizData {
	public long id;
	public long userId;
	public String title;
	public String description;
	public List<QuestionData> questions =  new ArrayList<QuestionData>();
	public QuizData(Quiz quiz) {
		this.id = quiz.getId();
		this.title = quiz.getTitle();
		this.description = quiz.getDescription();
		this.userId = quiz.getUserId();
		for(int i = 0; i < quiz.questions.size(); ++i) {
			Question question = quiz.questions.get(i);
			QuestionData qd = new QuestionData(question);
			this.questions.add(qd);
		}
	}
}
class QuestionData{
	public long id;
	public String text;
	public List<AnswerData> answers = new ArrayList<AnswerData>();
	public QuestionData() {
		this.id = 1;
		this.text = "dfklsj";
	}
	public QuestionData(Question q) {
		this.id = q.getId();
		this.text = q.getQuestionText();
		for(Answer a: q.answers) {
			this.answers.add(new AnswerData(a));
		}
	}
}
class AnswerData{
	public long id;
	public String text;
	public boolean correct;
	public AnswerData(Answer a) {
		this.id = a.getId();
		this.text = a.getAnswerText();
		this.correct = a.getCorrect();
	}
}
