package com.example.muratturan.quizappex;

import java.io.Serializable;

/**
 * Created by muratturan on 01/08/2017.
 */

public class Colors implements Serializable {

    private int index;
    private String colorCode;

    public Colors(int index, String colorCode) {
        this.index = index;
        this.colorCode = colorCode;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }
}
