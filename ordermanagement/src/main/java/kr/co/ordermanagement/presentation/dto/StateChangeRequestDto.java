package kr.co.ordermanagement.presentation.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import kr.co.ordermanagement.domain.order.State;

public class StateChangeRequestDto {
    private State state;

    @JsonCreator
    public StateChangeRequestDto(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }
}
