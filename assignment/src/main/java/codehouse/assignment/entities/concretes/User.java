package codehouse.assignment.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import codehouse.assignment.entities.concretes.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="users")
public class User 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;	

	@Column(name="first_name")
	@NotBlank(message = "This Field Cannot Be Empty.")
	@Size(min = 2, message = "First Name Must Be A Minimum Of 2 Characters")
	private String firstName;
	
	@Column(name="last_name")
	@NotBlank(message = "This Field Cannot Be Empty.")
	@Size(min = 2, message = "Position Name Must Be A Minimum Of 2 Characters")
	private String lastName;
	
	@Column(name = "phone")
	@Size(min = 7, message = "Write The Phone Number With The Area Code.")
	@Pattern(regexp = "^[0-9]{1}[0-9]{10,11}$", message = "Başında + Olmadan Alan Koduyla Giriniz.")
	private String phone;
}