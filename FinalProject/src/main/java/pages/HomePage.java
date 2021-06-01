package pages;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class HomePage   {

	private WebDriver driver;
	private Actions actions;
	private JavascriptExecutor jse;

	@FindBy(id="menu-1-ff0f6b2")WebElement navBarContentContainer;
	@FindBy(xpath="//div[@class='elementor-element elementor-element-7a2f19c elementor-widget elementor-widget-theme-site-logo elementor-widget-image']")WebElement SVClogoBTN;
	@FindBy(xpath="//div//ul[@id='menu-1-ff0f6b2']//a[contains(text(),'בדיקות תוכנה')]")WebElement navMenuSoftTestBTN;
	@FindBy(xpath="//div//ul[@id='menu-1-ff0f6b2']//a[contains(text(),'הסבה להייטק')]")WebElement HiTechEndorsement;
	@FindBy(xpath="//div//ul[@class='sub-menu elementor-nav-menu--dropdown sm-nowrap']//a[contains(text(),'קורס QA')]")WebElement navBarQaCourse;
	@FindBy(xpath="(//div[@class='elementor-widget-container']//ul[@class='elementor-icon-list-items elementor-inline-items']/li)[4]/a")WebElement toAllCoursesBTN;
	@FindBy(xpath="//span[contains(text(),'לסרטונים נוספים של בוגרים >')]")WebElement graduatesVideosBTN;
	@FindBy(xpath="//a[@class='elementor-button-link elementor-button elementor-size-xs']")WebElement moreTestimonialsByGraduatesBTN;
	@FindBy(xpath="//div[@data-id='6babf99']//a[@class='elementor-button-link elementor-button elementor-size-sm']")WebElement practicalTraining;
	@FindBy(xpath="//div[@data-id = 'd0f6035']//i[@class = 'fa fa-envelope-o']") WebElement quickContactBTN;
	@FindBy(xpath="(//input[@id = 'form-field-name'])[6]" )WebElement quickContactNameInput;
	@FindBy(xpath="(//input[@id='form-field-email'])[6]" )WebElement quickContactMailInput;
	@FindBy(xpath="(//input[@id='form-field-16af83e'])[5]")WebElement quickContactTelInput;
	@FindBy(xpath="(//div[@class='elementor-field-group elementor-column elementor-field-type-submit elementor-col-100 e-form__buttons']/button/span)[4]")WebElement quickContactSendBTN;
	@FindBy(xpath="(//div/button//span[contains(@class , 'elementor-button-text')])[1]") WebElement submitBTN;
	@FindBy(id="form-field-name") WebElement fNameInput;
	@FindBy(id="form-field-16af83e") WebElement telInput;
	@FindBy(id="form-field-email") WebElement mailInput;
	@FindBy(id="openVSButton") WebElement chatBotOpen;
	@FindBy(xpath="//div[@id='inputbox']")WebElement chatBotTextInput;
	@FindBy(xpath="//div[@id='inputbox']//a")WebElement chatBotSendBTN;
	@FindBy(id="form-field-field_49dd334") WebElement checkBox;
	@FindBy(linkText="קורס QA")WebElement linkTextCourseQA;
	@FindBy(linkText="קורס בניית אתרים ואפליקציות FULL Stack")WebElement linkTextCourseFullStack;
	@FindBy(linkText="קורס ניהול רשתות ואבטחת מידע")WebElement linkTextCourseIt;
	@FindBy(linkText="קורס גיימינג - פיתוח משחקים")WebElement linkTextCourseGaming;
	@FindBy(xpath="//div[@class = 'elementor-row']//div/article//div[@class = 'elementor-post__text']/h3/a[contains(text() , 'התנהלות נכונה להיי טקיסט המתחיל בחיפוש עבודה')]")WebElement articleTips;
	@FindBy(xpath="//div[@class = 'elementor-row']//div/article//div[@class = 'elementor-post__text']/h3/a[contains(text() , 'קורס או תואר במדעי המחשב – יתרונות וחסרונות')]")WebElement articleCommon;
	@FindBy(xpath="//div[@class = 'elementor-row']//div/article//div[@class = 'elementor-post__text']/h3/a[contains(text() , 'האם הסבה להייטק זה צעד משתלם? וכיצד עושים את זה?')]")WebElement articleToHiTech;
	@FindBy(xpath="//div[@class = 'elementor-row']//div/article//div[@class = 'elementor-post__text']/h3/a[contains(text() , 'תכונות שליליות בראיונות עבודה')]")WebElement articleJobInterviews;
	@FindBy(xpath="//span[contains(text(),'קורס אוטומציה אונליין >')]")WebElement automationOnlineBTN;


	public HomePage(WebDriver driver) {
		this.driver=driver;
		jse= (JavascriptExecutor)driver;
		actions = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		PageFactory.initElements(driver, this);
	}

	public boolean SanityTest(String name,String mail,String tel)  { 
		try {
			jse.executeScript("scrollBy(0,700)");
			actions.click(quickContactBTN).click(quickContactSendBTN)
			.click(quickContactNameInput)
			.sendKeys(name)
			.click(quickContactMailInput)
			.sendKeys(mail)
			.click(quickContactTelInput)
			.sendKeys(tel).build().perform();
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean chatBotFunc()  { 
		try {
			chatBotOpen.click();
			driver.switchTo().frame("iframeChat");
			actions.click(chatBotTextInput).sendKeys("Test").build().perform();
			chatBotSendBTN.click();
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean navBarContent()  { 
		int counter =0;
		try {
			for(int i = 0;i<navBarContentContainer.findElements(By.tagName("a")).size();i++) {
				jse.executeScript("arguments[0].click();", navBarContentContainer.findElements(By.tagName("a")).get(i));
				counter++;

			}
			System.out.println("Clicked all the "+counter+" "+"buttons");
			return true;

		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean applyForConsultation(String fName ,String tel ,String mail) {
		try {
			submitBTN.click();
			fNameInput.sendKeys(fName);
			telInput.sendKeys(tel);
			mailInput.sendKeys(mail);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean navBarClickOnQaCourse()  { 
		try {
			navMenuSoftTestBTN.click();
			navBarQaCourse.click();
			return true;

		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean navBarClickHiTechEndorsement (){
		try{
			HiTechEndorsement.click();
			return true;
		}catch(Exception e) {
			return false;
		}

	}

	public boolean clickOnCheckBox() {
		try {
			actions.moveToElement(checkBox).doubleClick().perform();
			if(checkBox.isSelected())
				return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return false;
	}
	public boolean clickElements() {
		try {
			linkTextCourseQA.click();
			SVClogoBTN.click();
			linkTextCourseFullStack.click();
			SVClogoBTN.click();
			linkTextCourseIt.click();
			SVClogoBTN.click();
			linkTextCourseGaming.click();
			SVClogoBTN.click();
			toAllCoursesBTN.click();
			SVClogoBTN.click();
			graduatesVideosBTN.click();
			switchTabs();
			jse.executeScript("scrollBy(0,1000)");
			moreTestimonialsByGraduatesBTN.click();
			SVClogoBTN.click();
			practicalTraining.click();
			SVClogoBTN.click();
			articleTips.click();
			SVClogoBTN.click();
			articleCommon.click();
			SVClogoBTN.click();
			articleToHiTech.click();
			SVClogoBTN.click();
			articleJobInterviews.click();
			SVClogoBTN.click();
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean toAllCoursesClick() {
		try {
			toAllCoursesBTN.click();
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean clickAutomationOnlineBTN() {
		try {
			automationOnlineBTN.click();
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public void switchTabs()  {

		String parentWindow=driver.getWindowHandle();
		for(String childTab:driver.getWindowHandles()) {
			driver.switchTo().window(childTab);
		}
		driver.close();
		driver.switchTo().window(parentWindow);
	}

}


















































