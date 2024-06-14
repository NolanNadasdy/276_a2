package com.cmpt276.as2.as2.models;

import java.awt.Color;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="rectangles")
public class Rectangle {
    // Attributes
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int rid;
    private String name;
    private float width;
    private float height;

    private Color color;

    // Constructors
    public Rectangle() {
    }

    public Rectangle(String name, float width, float height, Color color) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    // Get Set
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public Color getColor() {
        return color;
    }

    public String getColorString()
    {
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }


}

