package test1;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JsonResult {

@SerializedName("quizId")
@Expose
private Integer quizId;
@SerializedName("quizTitle")
@Expose
private String quizTitle;
@SerializedName("userId")
@Expose
private Integer userId;
@SerializedName("firstName")
@Expose
private String firstName;
@SerializedName("lastName")
@Expose
private String lastName;
@SerializedName("email")
@Expose
private String email;
@SerializedName("numOfCorrect")
@Expose
private Integer numOfCorrect;
@SerializedName("questions")
@Expose
private List<JsonQuestion> questions = null;

public Integer getQuizId() {
return quizId;
}

public void setQuizId(Integer quizId) {
this.quizId = quizId;
}

public String getQuizTitle() {
return quizTitle;
}

public void setQuizTitle(String quizTitle) {
this.quizTitle = quizTitle;
}

public Integer getUserId() {
return userId;
}

public void setUserId(Integer userId) {
this.userId = userId;
}

public String getFirstName() {
return firstName;
}

public void setFirstName(String firstName) {
this.firstName = firstName;
}

public String getLastName() {
return lastName;
}

public void setLastName(String lastName) {
this.lastName = lastName;
}

public String getEmail() {
return email;
}

public void setEmail(String email) {
this.email = email;
}

public Integer getNumOfCorrect() {
return numOfCorrect;
}

public void setNumOfCorrect(Integer numOfCorrect) {
this.numOfCorrect = numOfCorrect;
}

public List<JsonQuestion> getQuestions() {
return questions;
}

public void setQuestions(List<JsonQuestion> questions) {
this.questions = questions;
}
public Result toJPA() {
	Result r = new Result();
	r.setUserId(this.getUserId());
	r.setTitle(this.getQuizTitle());
	r.setDate(new SimpleDateFormat("yyyy/MM/dd HH:mm").format(new Date()));
	r.setFirstName(this.getFirstName());
	r.setLastName(this.getLastName());
	r.setEmail(this.getEmail());
	r.setNumOfCorrect(this.getNumOfCorrect());
	r.setNumOfQuestions(this.getQuestions().size());
	return r;
}

}
class JsonQuestion {

@SerializedName("text")
@Expose
private String text;
@SerializedName("correct")
@Expose
private Boolean correct;
@SerializedName("locked")
@Expose
private Boolean locked;
@SerializedName("timer")
@Expose
private Integer timer;
@SerializedName("answers")
@Expose
private List<String> answers = null;

public String getText() {
return text;
}

public void setText(String text) {
this.text = text;
}

public Boolean getCorrect() {
return correct;
}

public void setCorrect(Boolean correct) {
this.correct = correct;
}

public Boolean getLocked() {
return locked;
}

public void setLocked(Boolean locked) {
this.locked = locked;
}

public Integer getTimer() {
return timer;
}

public void setTimer(Integer timer) {
this.timer = timer;
}

public List<String> getAnswers() {
return answers;
}

public void setAnswers(List<String> answers) {
this.answers = answers;
}

}
