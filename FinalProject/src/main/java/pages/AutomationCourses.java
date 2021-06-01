package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AutomationCourses {
	private WebDriver driver;
	private JavascriptExecutor jse;
	private Actions actions;
	private WebDriverWait wait;

	@FindBy(xpath="//span[contains(text(),'קורס אוטומציה אונליין')]")WebElement toAutomationOnlineCourseBTN;
	@FindBy(xpath="//a[contains(text(),'להורדת הסילבוס לחצו כאן')]")WebElement downloadHybridSyllabusBTN;
	@FindBy(xpath="//span[contains(text(),'הרשם למחזור הקרוב!')]")WebElement applyToUpcomingCourseBTN;
	@FindBy(xpath="//a[contains(text(),'Java development environment')]")WebElement syllabusP1;
	@FindBy(xpath="//a[contains(text(),'Selenium basics')]")WebElement syllabusP2;
	@FindBy(xpath="//a[contains(text(),'JAVA Basic & OOP')]")WebElement syllabusP3;
	@FindBy(xpath="//a[contains(text(),'Selenium - advanced')]")WebElement syllabusP4;
	@FindBy(xpath="//a[contains(text(),'JUnit')]")WebElement syllabusP5;
	@FindBy(xpath="//span[contains(text(),'להורדת הסילבוס')]")WebElement downloadSyllabusBTN;
	@FindBy(xpath="(//input[@id = 'form-field-name'])[6]" )WebElement quickContactNameInput;
	@FindBy(xpath="(//form[@name='הרשמה פופאפ כללי']//input)[5]" )WebElement quickContactMailInput;
	@FindBy(xpath="(//form[@name='הרשמה פופאפ כללי']//input)[6]")WebElement quickContactTelInput;
	@FindBy(xpath="//div[@id='omw-205']//button/span/span[@class = ' elementor-button-icon']")WebElement quickContactSendBTN;
	@FindBy(xpath="//span[contains(text(),'לקורס הבא')]")WebElement viewNextCourseBTN;
	@FindBy(xpath="//span[contains(text(),'לקורס הקודם')]")WebElement viewPreviousCourseBTN;
	@FindBy(xpath="(//div/button//span[contains(@class , 'elementor-button-text')])[1]") WebElement submitBTN;
	@FindBy(id="form-field-name") WebElement fNameInput;
	@FindBy(id="form-field-16af83e") WebElement telInput;
	@FindBy(id="form-field-email") WebElement mailInput;

	public AutomationCourses(WebDriver driver) {
		this.driver=driver;
		jse= (JavascriptExecutor)driver;
		actions=new Actions(driver);
		wait = new WebDriverWait(driver,5);
		PageFactory.initElements(driver, this);

	}
	public boolean applyToHybridAutomationCourse() {
		try {
			toAutomationOnlineCourseBTN.click();
			applyToUpcomingCourseBTN.click();
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	public boolean applyToUpcomingOnlineCourse(String name,String mail,String tel)  { 
		try {
			quickContactSendBTN.click();
			actions
			.click(quickContactNameInput)
			.sendKeys(name).click(quickContactMailInput)
			.sendKeys(mail)
			.click(quickContactTelInput)
			.sendKeys(tel).build().perform();
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean syllabusButtons() {
		WebElement[]btnArr= {syllabusP1,syllabusP2,syllabusP3,syllabusP4,syllabusP5};
		WebElement j;
		try {	
			for(int i=0;i<btnArr.length;i++) {
				j=btnArr[i];
				jse.executeScript("arguments[0].click();", j);
			}
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	public boolean viewSyllabus() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(downloadSyllabusBTN)).click();
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	public boolean viewHybridCourseContent() {
		try {
			jse.executeScript("arguments[0].click();",toAutomationOnlineCourseBTN );
			downloadHybridSyllabusBTN.click();
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public boolean applyThroughViewNextCourse(String fName ,String tel ,String mail) {
		try {
			toAutomationOnlineCourseBTN.click();
			viewNextCourseBTN.click();
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

	public boolean viewPreviousCourseBTN() {
		try {
			viewPreviousCourseBTN.click();
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void switchTabs()    {

		String parentWindow=driver.getWindowHandle();
		for(String childTab:driver.getWindowHandles()) {
			driver.switchTo().window(childTab);
		}
		driver.close();
		driver.switchTo().window(parentWindow);

	}

	public void navigateBack() {
		driver.navigate().back();
	}

}
