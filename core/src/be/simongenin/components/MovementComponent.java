package be.simongenin.components;

import be.simongenin.enums.MovementState;
import com.badlogic.ashley.core.Component;

import java.util.List;

public class MovementComponent implements Component {

    public float x = 0.0f;
    public float y = 0.0f;

    public List<MovementState> states;

}
