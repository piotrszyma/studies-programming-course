package sample;

import javafx.event.EventHandler;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;
import org.w3c.dom.events.Event;

/**
 * Klasa EventHandlers zawiera definicje EventHandlerow
 * wykorzystywanych w programie.
 */

public class EventHandlers {

    private static double orgSceneX;
    private static double orgSceneY;
    private static double orgTranslateX;
    private static double orgTranslateY;

    private static DropShadow dropShadow = new DropShadow(12, Color.RED);



    static EventHandler<MouseEvent> figureOnMouseEntered =
            new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    //((Shape)(event.getSource())).setStrokeWidth(2);
                    //((Shape)(event.getSource())).setStrokeWidth(2/((Shape)(event.getSource())).getScaleX());

                    // ---- 2/((Shape)(event.getSource())).getScaleX()
                    ((Shape)(event.getSource())).setStrokeType(StrokeType.OUTSIDE);
                    //((Shape)(event.getSource())).setStrokeWidth(3);
                    // ---- ((Shape)(event.getSource())).setStrokeWidth(2/((Shape)(event.getSource())).getScaleX());
                    //((Shape)(event.getSource())).getStrokeDashArray().addAll(3.0,7.0,3.0,7.0);
                    // --- ((Shape)(event.getSource())).setStroke(Color.RED);


                    //ds.setOffsetX(0f);
                    //ds.setOffsetY(0f);
                    dropShadow.setRadius(12);
                    ((Shape)(event.getSource())).setEffect(dropShadow);
                    //((Shape)(event.getSource())).setEffect(null);
                    //((Shape)(event.getSource())).setEffect(new Glow(0.3));
                }
            };

    static EventHandler<MouseEvent> figureOnMouseExited =
            new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    ((Shape)(event.getSource())).setEffect(null);
                }
            };

    static EventHandler<ScrollEvent> figureOnScrolling = new EventHandler<ScrollEvent>() {
        @Override
        public void handle(ScrollEvent event) {

            if(Double.compare(event.getDeltaY(), 0) > 0 && Double.compare(((Shape) event.getSource()).getScaleY(), 3) < 0) {
                double scaleX = ((Shape) event.getSource()).getScaleX() + 0.1;
                double scaleY = ((Shape) event.getSource()).getScaleY() + 0.1;
                ((Shape) event.getSource()).setScaleX(scaleX);
                ((Shape) event.getSource()).setScaleY(scaleY);
                //((Shape)(event.getSource())).setStrokeWidth(2/((Shape)(event.getSource())).getScaleX());
            } else if(Double.compare(event.getDeltaY(), 0) < 0 && Double.compare(((Shape) event.getSource()).getScaleY(), 0.4) > 0 )
            {
                double scaleX = ((Shape) event.getSource()).getScaleX() - 0.1;
                double scaleY = ((Shape) event.getSource()).getScaleY() - 0.1;
                ((Shape) event.getSource()).setScaleX(scaleX);
                ((Shape) event.getSource()).setScaleY(scaleY);
                //((Shape)(event.getSource())).setStrokeWidth(2/((Shape)(event.getSource())).getScaleX());
            }

        }
    };



    static EventHandler<MouseEvent> figureOnMouseDragged =
            new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                dropShadow.setRadius(30);
                ((Shape)(event.getSource())).setEffect(dropShadow);

                if(event.getButton() == MouseButton.PRIMARY)
                {
                    double offsetX = event.getSceneX() - orgSceneX;
                    double offsetY = event.getSceneY() - orgSceneY;
                    double newTranslateX = orgTranslateX + offsetX;
                    double newTranslateY = orgTranslateY + offsetY;

                    ((Shape) (event.getSource())).setTranslateX(newTranslateX);
                    ((Shape) (event.getSource())).setTranslateY(newTranslateY);

                }

            }
            };

    static EventHandler<MouseEvent> figureOnMousePressed =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {

                    dropShadow.setRadius(30);
                    ((Shape)(event.getSource())).setEffect(dropShadow);

                    if(event.getButton() == MouseButton.PRIMARY)
                    {
                        orgSceneX = event.getSceneX();
                        orgSceneY = event.getSceneY();
                        orgTranslateX = ((Shape)(event.getSource())).getTranslateX();
                        orgTranslateY = ((Shape)(event.getSource())).getTranslateY();
                    }
                }
            };

    static EventHandler<MouseEvent> figureOnMouseDropped =
            new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    dropShadow.setRadius(12);
                    ((Shape)(event.getSource())).setEffect(dropShadow);
                }
            };



}
