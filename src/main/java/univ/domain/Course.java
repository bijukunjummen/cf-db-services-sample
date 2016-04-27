package univ.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "courses")
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "courseId")
	@JsonProperty("course_id")
	private Long courseId;

	@Size(min = 1, max = 10)
	@Column(name = "course_code")
	@NotNull
	@JsonProperty("course_code")
	private String courseCode;

	@Size(min = 1, max = 50)
	@Column(name = "course_name")
	@JsonProperty("course_name")
	private String courseName;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "teacher_id")
	private Teacher teacher;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "courses")
	private List<Student> students = new ArrayList<>();

	public Teacher getTeacher() {
		return this.teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	@Version
	@Column(name = "version")
	private Integer version;


	public Long getCourseId() {
		return this.courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public String getCourseCode() {
		return this.courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getCourseName() {
		return this.courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public List<Student> getStudents() {
		return this.students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	@Override
	public String toString() {
		return "Course{" +
				"courseId=" + courseId +
				", courseCode='" + courseCode + '\'' +
				", courseName='" + courseName + '\'' +
				", teacher=" + teacher +
				", version=" + version +
				'}';
	}
}
