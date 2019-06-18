package view.util;

import javafx.animation.FadeTransition;
import javafx.scene.Parent;
import javafx.util.Duration;

public class FadeEffect
{
    public FadeEffect(Parent parent)
    {
        FadeTransition transition = new FadeTransition(Duration.millis(500), parent);
        transition.setFromValue(0);
        transition.setToValue(1);
        transition.play();
    }
}
