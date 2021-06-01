package pages;

import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class HitechEndorsement {
	
	private WebDriver driver;
	private JavascriptExecutor jse;
	private String openTabs;
	private String parentWindow;

	@FindBy(xpath="//div[@class='elementor-element elementor-element-bda2060 elementor-widget elementor-widget-theme-post-content']")WebElement textLinksContainer;
	@FindBy(xpath="//div[@class='elementor-social-icons-wrapper elementor-grid']")WebElement shareButtonsContainer;
	@FindBy(xpath="//i[@class='fab fa-youtube']")WebElement shareOnYoutube;
	@FindBy(xpath="//i[@class='fab fa-facebook-f']")WebElement shareOnFacebook;
	@FindBy(xpath="//i[@class='fab fa-instagram']")WebElement shareOnInstagram;
	@FindBy(xpath="//i[@class='fab fa-linkedin']")WebElement shareOnLinkedin;


	public HitechEndorsement(WebDriver driver) {
		this.driver = driver;
		parentWindow = driver.getWindowHandle();
		jse = (JavascriptExecutor)driver;
		openTabs = Keys.chord(Keys.CONTROL,Keys.ENTER);
		PageFactory.initElements(driver, this);
	}

	public boolean textLinksVerify()  {
		try {
			System.out.println("There are : "+ textLinksContainer.findElements(By.tagName("a")).size()+" "+"text links to click");

			for(int i = 0;i<textLinksContainer.findElements(By.tagName("a")).size();i++) {

				textLinksContainer.findElements(By.tagName("a")).get(i).sendKeys(openTabs);
			}
			Set<String> allWin = driver.getWindowHandles(); 

			for (String child : allWin) {
				driver.switchTo().window(child);
				if (!(child.equals(parentWindow))) {
					System.out.println(driver.getTitle());
					driver.close(); 
				}
			}
			driver.switchTo().window(parentWindow);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean integration() throws Exception {

		shareOnYoutube.click();
		if(!(driver.getPageSource().contains("youtube"))) {
			throw new Exception ("integration,wrong page");

		}
		shareOnFacebook.click();
		if(!(driver.getPageSource().contains("facebook"))) {
			throw new Exception ("integration,wrong page");

		}
		shareOnInstagram.click();
		if(!(driver.getPageSource().contains("instagram"))) {
			throw new Exception ("integration,wrong page");

		}
		jse.executeScript("arguments[0].click();", shareOnLinkedin);
		if(!(driver.getPageSource().contains("linkedin"))) {
			throw new Exception ("integration,wrong page");

		}
		return true;
	}
}			










