package org.example;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


public class CalcualtorTest {
    @Test
    public void testAddition() {
        Calculator mockedCalculator = mock(Calculator.class);
        when(mockedCalculator.add(2, 3)).thenReturn(5);

        assertEquals(5, mockedCalculator.add(2, 3));

    }

    @Test
    public void testSubtraction() {
        Calculator mockedCalculator = mock(Calculator.class);
        when(mockedCalculator.subtract(5, 3)).thenReturn(2);

        assertEquals(2, mockedCalculator.subtract(5, 3));

    }

}
