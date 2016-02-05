package id.tomatech.learn.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="member")
public class Member {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	/**
	 * - Constraint @{@link NotBlank}, berarti property name tidak boleh null
	 * dan tidak boleh empty string
	 */
	@NotBlank
	@Column(name="name")
	private String name;
	
	/**
	 * - Constraint @{@link NotBlank}, berarti property email tidak boleh null
	 * dan tidak boleh empty string
	 * - COnstraint @{@link Email}, berarti property email harus dalam format
	 * email yang valid.
	 */
	@NotBlank
	@Email
	@Column(name="email")
	private String email;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
