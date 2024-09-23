package com.challenge.pennypilot.splitwise.dto;

import com.challenge.pennypilot.splitwise.exception.InvalidDataProvidedException;

public abstract class AbstractDTO {
    public abstract void validate() throws InvalidDataProvidedException;
}
