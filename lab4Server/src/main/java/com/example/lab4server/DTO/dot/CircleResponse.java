package com.example.lab4server.DTO.dot;

import com.example.lab4server.DTO.dot.DotResponse;
import lombok.Data;

import java.io.Serializable;

@Data
public class CircleResponse implements Serializable {
    private double x;
    private double y;
    private double r;
    private String result;
    private String colorStroke;
    private String colorFill;
    private double radius;

    public CircleResponse(DotResponse dotResponse, String colorR, String colorNotR, double r) {
        this.x = dotResponse.getX();
        this.y = dotResponse.getY();
        this.r = dotResponse.getR();
        this.result = dotResponse.getResult();
        defineCircle(dotResponse, colorR, colorNotR, r);
    }

    private void defineCircle(DotResponse dotResponse, String colorR, String colorNotR, double r) {
        if (dotResponse.getR() == r) {
            this.radius = 5;
            if ("Попадание".equals(dotResponse.getResult())) {
                this.colorStroke = "none";
                this.colorFill = colorR;
            } else {
                this.colorStroke = colorR;
                this.colorFill = "none";
            }
        } else {
            this.radius = 2;
            this.colorStroke = colorNotR;
            this.colorFill = colorNotR;
        }
    }
}
