package com.challenge.pennypilot.splitwise.dto;

import com.challenge.pennypilot.splitwise.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.util.StringUtils;

public class UserDTO extends AbstractDTO {
    private String firstName;
    private String lastName;
    private String emailId;

    private long userId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    public UserDTO() {}

    public UserDTO(User user) {
        this.firstName = user.getFirstName();
        this.emailId = user.getEmailId();
        this.lastName = user.getLastName();
        this.userId = user.getUserId();
    }

    public void validate() throws Exception {
        if (!StringUtils.hasLength(firstName)) {
            throw new Exception("First Name must not be empty");
        }
        if (!StringUtils.hasLength(lastName)) {
            throw new Exception("Last Name must not be empty");
        }
        if (!StringUtils.hasLength(password)) {
            throw new Exception("Password must not be empty");
        }
        if (!StringUtils.hasLength(emailId)) {
            throw new Exception("Email must not be empty");
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
