package com.cmpt276.as2.as2.models;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface RectangleRepository extends JpaRepository<Rectangle, Integer>
{
    List<Rectangle> findByName(String name);
    List<Rectangle> findByHeightAndWidth(float height, float width);
}