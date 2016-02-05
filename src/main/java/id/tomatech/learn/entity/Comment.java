package id.tomatech.learn.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(value="post", allowSetters=true)
@Table(name="comment")
public class Comment {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@NotBlank
	@Column(name="content")
	private String content;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="comment_date")
	private Date commentDate = new Date();
	
	@ManyToOne()
	@JoinColumn(name="post_id", referencedColumnName="id")
	private Post post;
	
	@ManyToOne
	@JoinColumn( name="commentator_id", referencedColumnName="id")
	private Member commentator;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Member getCommentator() {
		return commentator;
	}

	public void setCommentator(Member commentator) {
		this.commentator = commentator;
	}
}
