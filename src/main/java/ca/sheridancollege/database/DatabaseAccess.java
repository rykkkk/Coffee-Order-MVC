package ca.sheridancollege.database;

import ca.sheridancollege.beans.Coffee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DatabaseAccess {

    @Autowired
    protected NamedParameterJdbcTemplate jdbc;

    public void addCup(Coffee cup) {
        MapSqlParameterSource np = new MapSqlParameterSource();
        String query = "INSERT INTO theOrder(numCup,size,coffeeType,numSugar,numCream) VALUES(':numCup', ':size', ':coffeeType', ':numSugar', ':numCream');";
        np.addValue("numCup", cup.getNumCup());
        np.addValue("size", cup.getSize());
        np.addValue("coffeeType", cup.getCoffeeType());
        np.addValue("numSugar", cup.getNumSugar());
        np.addValue("numCream", cup.getNumCream());
        int rowsAffected = jdbc.update(query, np);
        if (rowsAffected > 0) {
            System.out.print("\nCoffee added to order!");
        }
    }

    public List<Coffee> getCup(Long id) {
        MapSqlParameterSource np = new MapSqlParameterSource();
        String query = "SELECT * FROM theOrder WHERE id = :id1";
        np.addValue("id1", id);
        return jdbc.query(query, np, new BeanPropertyRowMapper<>(Coffee.class));
    }

    public List<Coffee> getCups() {
        String query = "SELECT * FROM theOrder";
        MapSqlParameterSource np = new MapSqlParameterSource();
        return jdbc.query(query, np, new BeanPropertyRowMapper<>(Coffee.class));
    }

    public void removeCup(Long id) {
        MapSqlParameterSource np = new MapSqlParameterSource();
        String query = "DELETE FROM theOrder WHERE id = :id1";
        np.addValue("id1", id);
        jdbc.update(query, np);
    }

    public void editNumCup(Coffee cup) {
        MapSqlParameterSource np = new MapSqlParameterSource();
        String query = "UPDATE theOrder SET numCup = :numCup WHERE id = :id"; //important . whatever appears after the :
        // is the param name
        np.addValue("id", cup.getId());
        np.addValue("numCup", cup.getNumCup());
        int rowsAffected = jdbc.update(query, np);
        if (rowsAffected > 0) {
            System.out.println("Coffee order updated!");
        }
    }


}
