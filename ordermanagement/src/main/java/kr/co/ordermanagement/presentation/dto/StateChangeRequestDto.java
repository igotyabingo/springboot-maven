package kr.co.ordermanagement.presentation.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

public class StateChangeRequestDto {
    private String state;

    @JsonCreator
    public StateChangeRequestDto(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
