package com.softserve.validation;

import javax.validation.constraints.*;

import com.softserve.validation.FieldMatch;
import com.softserve.validation.ValidEmail;
import com.softserve.validation.ValidPassword;
import org.springframework.format.annotation.NumberFormat;

//ADDING VALIDATION RULES

@FieldMatch.List({
    @FieldMatch(first = "password", second = "matchingPassword", message = "The password fields must match")
})
public class UserValidator {

	@ValidEmail
	@NotNull(message = "is required")
	@Size(min = 3, message = "is required")
	private String email;

	@ValidPassword
	@NotEmpty(message = "Password must not be blank.")
	@Size(min = 3, max = 10, message = "Password must between 1 to 10 Characters.")
	private String password;

	@NotNull(message = "is required")
	@Size(min = 3, message = "is required")
	private String matchingPassword;

	@NotNull(message = "is required")
	@Size(min = 3, message = "is required")
	private String firstName;

	@NotNull(message = "is required")
	@Size(min = 3, message = "is required")
	private String lastName;

	@NotNull
	@NumberFormat(style = NumberFormat.Style.NUMBER)
	@Min(1)
	@Max(100)
	private String age;

	@NotNull(message = "is required")
	@Size(min = 6, message = "is required")
	private String phone;

	public UserValidator() {

	}

	public String getEmail() { return email; }

	public void setEmail(String email) { this.email = email; }

	public String getPassword() { return password; }

	public void setPassword(String password) { this.password = password; }

	public String getMatchingPassword() { return matchingPassword; }

	public void setMatchingPassword(String matchingPassword) { this.matchingPassword = matchingPassword; }

	public String getFirstName() { return firstName; }

	public void setFirstName(String firstName) { this.firstName = firstName; }

	public String getLastName() { return lastName; }

	public void setLastName(String lastName) { this.lastName = lastName; }

	public String getAge() { return age; }

	public void setAge(String age) { this.age = age; }

	public String getPhone() { return phone; }

	public void setPhone(String phone) { this.phone = phone; }

}
