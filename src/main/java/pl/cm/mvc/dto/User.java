package pl.cm.mvc.dto;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name="userlist")
public class User {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private Long id;

  @Column(unique = true)
  private String username;
  private String firstname;
  private String lastname;
  private Integer age;

  public User() {
  }

  public User(final Long id) {
    this.id = id;
  }

  public User(final Long id, final String username) {
    this.id = id;
    this.username = username;
  }
}
