/*
 * 
 */
package uk.co.ariley.armenu;

import java.text.ChoiceFormat;
import java.text.Format;
import java.text.MessageFormat;
import java.text.NumberFormat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ariley
 */
public class FormatTest {
    
    public FormatTest() {
    }
    

    @Before
    public void setUp() {
    }
    

    @After
    public void tearDown() {
    }
    
    
    @Test
    public void test1() {
        double[] limits = {1, 2};
        String[] part = {"this item", "these {0} items"};
        ChoiceFormat format = new ChoiceFormat(limits, part);
        Format[] formats = {format};
        MessageFormat pattern = new MessageFormat("Are you sure you want to delete {0}");
        pattern.setFormats(formats);
        Object[] args = {Integer.valueOf(1)};
        String s = pattern.format(args);
        assertEquals("Are you sure you want to delete this item", s);
    }
    
    
    @Test
    public void test2() {
        double[] limits = {1, 2};
        String[] part = {"this item", "these {0} items"};
        ChoiceFormat format = new ChoiceFormat(limits, part);
        Format[] formats = {format};
        MessageFormat pattern = new MessageFormat("Are you sure you want to delete {0}");
        pattern.setFormats(formats);
        Object[] args = {Integer.valueOf(2)};
        String s = pattern.format(args);
        assertEquals("Are you sure you want to delete these 2 items", s);
    }
}
