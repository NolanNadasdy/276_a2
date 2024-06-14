package com.cmpt276.as2.as2.controllers;

import java.awt.Color;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cmpt276.as2.as2.models.Rectangle;
import com.cmpt276.as2.as2.models.RectangleRepository;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class RectanglesController {
    @Autowired
    private RectangleRepository rectangleRepo;
    @GetMapping("/rectangles/view")
    public String getAllRectangles(Model model)
    {
        System.out.println("Getting all rectangles");

        // get all rectangles from database
        List<Rectangle> rectangles = rectangleRepo.findAll();

        // End database call

        model.addAttribute("rectangles", rectangles);
        return "rectangles/showAll";
    }

    @PostMapping("/rectangles/add")
    public String addRectangle(@RequestParam Map<String, String> newRectangle, HttpServletResponse response) 
    {
        System.out.println("ADD rectangle");
        String newName = newRectangle.get("name");
        float newHeight = Float.parseFloat(newRectangle.get("height"));
        float newWidth = Float.parseFloat(newRectangle.get("width"));
        Color newColor = Color.decode(newRectangle.get("color"));

        rectangleRepo.save(new Rectangle(newName, newWidth, newHeight, newColor));
        response.setStatus(201);

        return "rectangles/addedRectangle";
    }

    @GetMapping("/rectangles/delete/{id}")
    public String deleteRectangle(@PathVariable("id") int id)
    {
        System.out.println("Deleting rectangle " + id);
        rectangleRepo.deleteById(id);
        return "redirect:/rectangles/view";
    }

    @GetMapping("/rectangles/inspect/{id}")
    public String inspectRectangle(@PathVariable("id") int id, Model model)
    {
        System.out.println("Inspecting rectangle " + id);

        // Get current rectangle from database
        Optional<Rectangle> optionalRectangle = rectangleRepo.findById(id);
        if(optionalRectangle.isPresent())
        {
            Rectangle curRectangle = optionalRectangle.get();
            model.addAttribute("curRectangle", curRectangle);
            return "rectangles/rectangleDetail";
        }
        else
        {
            return "redirect:/rectangles/view";
        }
    }

    @GetMapping("/rectangles/modify/{id}")
    public String modifyRectangle(@PathVariable("id") int id, Model model)
    {
        System.out.println("modifying rectangle " + id);

        // Get current rectangle from database
        Optional<Rectangle> optionalRectangle = rectangleRepo.findById(id);
        if(optionalRectangle.isPresent())
        {
            Rectangle curRectangle = optionalRectangle.get();
            rectangleRepo.deleteById(id);
            model.addAttribute("curRectangle", curRectangle);
            return "rectangles/rectangleModify";
        }
        else
        {
            return "redirect:/rectangles/view";
        }
    }
    
}
