/*
KRISTA RYK
    99146369
    OCTOBER 6 2021
 */

package ca.sheridancollege.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
//order bean

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Coffee {

    private Long id;

    private int numCup;
    private String size;
    private String[] sizes = {"Small", "Medium", "Large", "Extra Large"};
    private int numSugar;
    private int numCream;
    private String coffeeType;
    private String[] types = {"Regular", "Double Double", "Triple Triple",
            "Black", "Black one sugar", "Black two sugars", "Black three sugars"};
    private BigDecimal price;
}