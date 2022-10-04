/*
KRISTA RYK
    99146369
    OCTOBER 6 2021
 */
package ca.sheridancollege.controllers;

import ca.sheridancollege.beans.Coffee;
import ca.sheridancollege.database.DatabaseAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class processOrder {

    @Autowired
    DatabaseAccess da;
    // public List<Coffee> coffeeList = new CopyOnWriteArrayList<Coffee>();

    @GetMapping("/")
    public String toIndex(Model model) {

        model.addAttribute("cup", new Coffee());
        model.addAttribute("coffees", da.getCups());
        return "index";
    }

    @PostMapping("/addCup")
    public String newCoffee(Model model, @ModelAttribute Coffee cup) {

        switch (cup.getCoffeeType()) {
            case "Regular":
                cup.setNumCream(1);
                cup.setNumSugar(1);
                cup.setCoffeeType("Regular");
                break;
            case "Double Double":
                cup.setNumCream(2);
                cup.setNumSugar(2);
                cup.setCoffeeType("Double Double");
                break;
            case "Triple Triple":
                cup.setNumCream(3);
                cup.setNumSugar(3);
                cup.setCoffeeType("Triple Triple");
                break;
            case "Black":
                cup.setNumCream(0);
                cup.setNumSugar(0);
                cup.setCoffeeType("Black");
                break;
            case "Black one sugar":
                cup.setNumCream(0);
                cup.setNumSugar(1);
                cup.setCoffeeType("Black one sugar");
                break;
            case "Black two sugars":
                cup.setNumCream(0);
                cup.setNumSugar(2);
                cup.setCoffeeType("Black two sugars");
                break;
            case "Black three sugars":
                cup.setNumCream(0);
                cup.setNumSugar(3);
                cup.setCoffeeType("Black three sugars");
                break;
        }
        model.addAttribute("coffees", da.getCups());
        model.addAttribute("cup", new Coffee());
        da.addCup(cup);

        return "index";
    }

    /*remove a cup*/
    @GetMapping("/removeCup/{id}")
    public String removeCup(Model model, @PathVariable Long id) {
        model.addAttribute("cup", new Coffee());
        model.addAttribute("coffees", da.getCups());
        return "index";
    }

    /*MAYBE AN EDIT ORDER
     * then update*/
    @GetMapping("/editNumCup/{id}")
    public String editNumCup(Model model, @PathVariable Long id) {
        Coffee cup;
        cup = da.getCup(id).get(0);
        model.addAttribute("cup", cup);
        model.addAttribute("coffees", da.getCups());
        return "index";
    }

    @PostMapping("/updateNumCup")
    public String updateNumCup(Model model, @ModelAttribute Coffee cup) {
        da.editNumCup(cup);
        model.addAttribute("coffees", da.getCups());
        model.addAttribute("cup", new Coffee());
        return "index";
    }

    @GetMapping("/orderPage")
    public String toOrderPage(Model model) {
        model.addAttribute("coffees", da.getCups());
        return "orderPage";
    }

}
