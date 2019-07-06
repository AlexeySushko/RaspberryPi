package ui;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.EllipseBuilder;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.util.Arrays;

public class RadarWindowFX extends Application {
    private static final int heigth = 400;
    private static final int width = 800;

    //for correct calculation of the angle depending on the position
    private static final double coefPosition = 6.6666;
    private static final Group root = new Group();

    public static String[] resArrStr = new String[]{};


    public void showFigure() {
        launch(new String[]{});
    }

    @Override
    public void start(Stage primaryStage) {

        resArrStr = new String[]{
                "15", "135", "88", "142", "143", "100", "97", "97", "100",
                "102", "142", "152", "153", "198", "198", "200", "15", "15",
                "16", "17", "154", "2621", "2620", "2617", "2618", "2617", "2616"};

        primaryStage.setTitle("Ultrasonic Radar");
        Scene scene = new Scene(root, width, heigth, Color.WHITE);

        createAreaScan();

        System.out.println(Arrays.toString(resArrStr));
        for (int i = 0; i < resArrStr.length; i++) {
            root.getChildren().add(drawSensorLine(i + 1, Integer.parseInt(resArrStr[i])));
        }

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void createAreaScan() {
//        Line lineLeft = new Line(heigth, heigth, 0, 0);
//        Line lineRight = new Line(heigth, heigth, width, 0);

        root.getChildren().add(createRangeArea(400));
        root.getChildren().add(createRangeArea(300));
        root.getChildren().add(createRangeArea(200));
        root.getChildren().add(createRangeArea(100));

//        root.getChildren().add(lineLeft);
//        root.getChildren().add(lineRight);
    }

    /**
     * Draw correct line on the sensor panel.
     *
     * @param position 1-27 position servo.
     * @param length   length for come object.
     * @return correct line.
     */
    private Node drawSensorLine(int position, double length) {
        int endX = 0;
        int endY = 0;

        length = length * 10 > 400 ? 400 : length * 10;
        int y = (int) (length * Math.sin(Math.toRadians(position * coefPosition)));

        endY = heigth - (y);//141
        endX = (int) (heigth - (length * Math.cos(Math.toRadians(position * coefPosition))));

        Line line = new Line(heigth, heigth, endX, endY);
        line.setStrokeWidth(3);

        System.out.print("pos = "+ position + "   ");
        System.out.print("len = "+ length + "   ");
        System.out.print("x :"+ endX + "   ");
        System.out.println("y :"+ endY);

        return line;
    }

    /**
     * Draws the initial markup of the scanner
     *
     * @param radius before 4000 mm. (Max sensor range).
     */
    private Node createRangeArea(int radius) {
        Ellipse circle = EllipseBuilder.create()
                .centerX(400)
                .centerY(400)
                .radiusX(radius)
                .radiusY(radius)
                .strokeWidth(1)
                .stroke(Color.BLACK)
                .fill(Color.WHITE)
                .build();

        return circle;
    }
}
