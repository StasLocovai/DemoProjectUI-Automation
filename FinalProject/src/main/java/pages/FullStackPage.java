package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FullStackPage {
	
	private WebDriver driver;

	@FindBy(xpath="(//div/button//span[contains(@class , 'elementor-button-text')])[1]") WebElement submitBTN;
	@FindBy(id="form-field-name") WebElement fNameInput;
	@FindBy(id="form-field-tel")WebElement telInputOnFullStackPage;
	@FindBy(id="form-field-email") WebElement mailInput;

	public FullStackPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	public boolean applyToFullStackCourse(String fname,String tel,String mail) {
		try {
			submitBTN.click();
			fNameInput.sendKeys(fname);
			telInputOnFullStackPage.sendKeys(tel);
			mailInput.sendKeys(mail);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public void odeToViewer() {
		System.out.println("!!!!!!Thank you for the patience , see you around  !!!!!!");
	}

}
