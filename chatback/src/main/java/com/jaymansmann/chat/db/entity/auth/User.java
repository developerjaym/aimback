/**
 * 
 */
package com.jaymansmann.chat.db.entity.auth;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author Jay
 *
 */
import com.jaymansmann.chat.db.entity.Conversation;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import java.util.List;

@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" })
		 })
@Getter
@Setter
public class User {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7612807721807024759L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 32)
	@NaturalId
	private String username;

	@NotBlank
	@Size(max = 100)
	private String password;

	@OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
	private List<Conversation> conversations;

	public User() {

	}

	public User(@NotBlank @Size(max = 15) String username,
				@NotBlank @Size(max = 100) String password) {
		super();
		this.username = username;
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

}