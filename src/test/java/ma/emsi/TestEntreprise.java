package ma.emsi;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TestEntreprise {

    private WebDriver mockDriver;
    private WebDriverWait mockWait;

    @Before
    public void setUp() {
        mockDriver = Mockito.mock(WebDriver.class);
        mockWait = Mockito.mock(WebDriverWait.class);
    }

    @Test
    public void testSearchResultsFound() {

        WebElement mockSearchSector = mock(WebElement.class);
        WebElement mockSearchRegion = mock(WebElement.class);
        WebElement mockSearchButton = mock(WebElement.class);
        WebElement mockList = mock(WebElement.class);
        WebElement mockEnterprise = mock(WebElement.class);
        List<WebElement> mockEnterprises = Arrays.asList(mockEnterprise);

        when(mockDriver.findElement(By.name("S_Activité"))).thenReturn(mockSearchSector);
        when(mockDriver.findElement(By.name("Région"))).thenReturn(mockSearchRegion);
        when(mockDriver.findElement(By.cssSelector("#findId"))).thenReturn(mockSearchButton);
        when(mockDriver.findElements(By.cssSelector(".bi-list"))).thenReturn(Arrays.asList(mockList));
        when(mockDriver.findElements(By.cssSelector(".bi-generic"))).thenReturn(mockEnterprises);

        WebElement mockDenomination = mock(WebElement.class);
        when(mockEnterprise.findElement(By.cssSelector(".bi-denomination"))).thenReturn(mockDenomination);
        when(mockDenomination.getText()).thenReturn("Test Enterprise");

        mockSearchButton.click();
        List<WebElement> enterprises = mockDriver.findElements(By.cssSelector(".bi-generic"));

        assertNotNull(enterprises);
        assertEquals(1, enterprises.size());
        assertEquals("Test Enterprise", enterprises.get(0).findElement(By.cssSelector(".bi-denomination")).getText());
    }

    @Test
    public void testNoResultsFound() {

        when(mockDriver.findElements(By.cssSelector(".bi-list"))).thenReturn(Arrays.asList());

        WebElement mockSearchButton = mock(WebElement.class);
        when(mockDriver.findElement(By.cssSelector("#findId"))).thenReturn(mockSearchButton);
        mockSearchButton.click();

        List<WebElement> enterprises = mockDriver.findElements(By.cssSelector(".bi-list"));
        assertTrue(enterprises.isEmpty());
    }

    @Test
    public void testInvalidElementHandling() {

        WebElement mockSearchSector = mock(WebElement.class);
        WebElement mockSearchRegion = mock(WebElement.class);
        WebElement mockSearchButton = mock(WebElement.class);
        when(mockDriver.findElement(By.name("S_Activité"))).thenReturn(mockSearchSector);
        when(mockDriver.findElement(By.name("Région"))).thenReturn(mockSearchRegion);
        when(mockDriver.findElement(By.cssSelector("#findId"))).thenReturn(mockSearchButton);

        when(mockDriver.findElements(By.cssSelector(".bi-list"))).thenReturn(Arrays.asList());

        mockSearchButton.click();
        List<WebElement> enterprises = mockDriver.findElements(By.cssSelector(".bi-list"));

        assertTrue(enterprises.isEmpty());
    }

    @After
    public void tearDown() {
        mockDriver = null;
        mockWait = null;
    }
}
