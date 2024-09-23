package com.challenge.pennypilot.splitwise.dto;

import com.challenge.pennypilot.splitwise.exception.InvalidDataProvidedException;
import com.challenge.pennypilot.splitwise.model.Group;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.util.StringUtils;

public class GroupDTO extends AbstractDTO {
    private String name;
    private String description;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long groupId;

    public GroupDTO() {}

    public GroupDTO(Group group) {
        this.name = group.getName();
        this.description = group.getDescription();
        this.groupId = group.getGroupId();
    }

    @Override
    public void validate() throws InvalidDataProvidedException {
        if (!StringUtils.hasLength(name)) {
            throw new InvalidDataProvidedException("Group name must not be empty");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }
}
