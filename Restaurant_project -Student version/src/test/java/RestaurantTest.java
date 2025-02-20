import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class RestaurantTest {

    //REFACTOR ALL THE REPEATED LINES OF CODE
    static LocalTime openingTime = LocalTime.parse("10:30:00");
    static LocalTime closingTime = LocalTime.parse("22:00:00");
    static Restaurant restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);

    @BeforeAll
    public static void beforeTestAlltestCases() {
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }
    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
   
    	
    	//Mock getCurrentTime to 11:30:00 where the Restuarant is open 
    	
	
Restaurant aSpy = Mockito.spy(restaurant);
Mockito.when(aSpy.getCurrentTime()).thenReturn(LocalTime.parse("11:30:00"));
System.out.println(aSpy.getCurrentTime());
assertTrue(aSpy.isRestaurantOpen());
    	
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
    	//Mock getCurrentTime to 23:30:00 where the Restuarant is closed 
    	Restaurant bSpy = Mockito.spy(restaurant);
    	Mockito.when(bSpy.getCurrentTime()).thenReturn(LocalTime.parse("23:30:00"));
    	System.out.println(bSpy.getCurrentTime());
    	assertFalse(bSpy.isRestaurantOpen());
    
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }

    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }

    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {


        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}