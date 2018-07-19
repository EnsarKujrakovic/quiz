package test1;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Table(name = "results")
public class Result {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name="user_id")
	private long userId;
	@Column(name="quiz_title")
	private String quiz_title;
	@Column(name="date")
	private String date;
	@Column(name="first_name")
	private String firstName;
	@Column(name="last_name")
	private String lastName;
	@Column(name="email")
	private String email;
	@Column(name="num_correct")
	private int numOfCorrect;
	@Column(name="num_questions")
	private int numOfQuestions;
	//@OneToMany(mappedBy="quiz" ,cascade = {CascadeType.ALL}, orphanRemoval=true)
	//public List<Question> questions;
	
	public Result() {}
	public long getId() {
		return id;
	}
	public long getUserId() {
		return this.userId;
	}
	public void setUserId(long id) {
		this.userId=id;
	}
	public String getTitle() {
		return quiz_title;
	}
	public void setTitle(String t) {
		this.quiz_title = t;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String d) {
		this.date = d;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String n) {
		this.firstName = n;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String n) {
		this.lastName = n;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String e) {
		this.email = e;
	}
	public int getNumOfCorrect() {
		return numOfCorrect;
	}
	public void setNumOfCorrect(int n) {
		this.numOfCorrect = n;
	}
	public int getNumOfQuestions() {
		return numOfQuestions;
	}
	public void setNumOfQuestions(int n) {
		this.numOfQuestions= n;
	}
}
