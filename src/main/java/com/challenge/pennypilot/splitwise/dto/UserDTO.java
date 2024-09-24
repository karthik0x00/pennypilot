package com.challenge.pennypilot.splitwise.dto;

import com.challenge.pennypilot.splitwise.exception.InvalidDataProvidedException;
import com.challenge.pennypilot.splitwise.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.util.StringUtils;

public class UserDTO extends AbstractDTO {
    private String name;
    private String emailId;

    private long userId;

    public UserDTO() {}

    public UserDTO(User user) {
        this.emailId = user.getEmailId();
        this.userId = user.getUserId();
        this.name = user.getName();
    }

    public void validate() throws InvalidDataProvidedException {}

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
