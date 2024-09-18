/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package dao;

import dao.RecipeDAO;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Ralph Lee <your.name at your.org>
 */
public class RecipeDAOTest {
    
    public RecipeDAOTest() {
    }

    @Test
    public void testCreate() {
    }

    @Test
    public void testUpdate() {
    }

    @Test
    public void testListLast() {
    }

    @Test
    public void testListBest() {
        
        
    }
    
    
    
    @Test
    public void testCheckDate(){
        java.sql.Timestamp inThePast = java.sql.Timestamp.valueOf("2007-09-23 10:10:10.0");
        java.sql.Timestamp inTheFuture = java.sql.Timestamp.valueOf("2030-06-20 10:10:10.0");
        java.sql.Timestamp RightNow = java.sql.Timestamp.valueOf(LocalDateTime.now());
        java.sql.Timestamp lessThanMonthAgo = java.sql.Timestamp.valueOf("2024-03-10 10:10:10.0");
        java.sql.Timestamp overMonthAgo = java.sql.Timestamp.valueOf("2024-02-17 10:10:10.0");
        
        /*RecipeDAO rdao=new RecipeDAO();
        assertFalse(rdao.checkDate(inThePast));
        assertTrue(rdao.checkDate(inTheFuture));
        assertTrue(rdao.checkDate(RightNow));
        assertTrue(rdao.checkDate(lessThanMonthAgo));
        assertFalse(rdao.checkDate(overMonthAgo));*/
        
                
    }

}
