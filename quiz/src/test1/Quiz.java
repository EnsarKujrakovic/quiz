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
@Table(name = "quizes")
public class Quiz {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name="user_id")
	private long userId;
	@Column(name="title")
	private String title;
	@Column(name="description")
	private String description;
	@OneToMany(mappedBy="quiz" ,cascade = {CascadeType.ALL}, orphanRemoval=true)
	public List<Question> questions;
	
	public Quiz() {}
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
		return title;
	}
	public void setTitle(String t) {
		this.title = t;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String d) {
		this.description = d;
	}
}
