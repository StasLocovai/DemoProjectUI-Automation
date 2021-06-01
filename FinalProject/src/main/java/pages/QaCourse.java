package pages;

import java.util.ArrayList;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class QaCourse {
	private WebDriver driver;
	private JavascriptExecutor jse;
	private String openTabs;
	private ArrayList<String> openedTabs;

	@FindBy(xpath="//div[@class='elementor-element elementor-element-2d94c44 elementor-icon-list--layout-traditional elementor-list-item-link-full_width elementor-widget elementor-widget-icon-list']")WebElement linksAboutSV;
	@FindBy(xpath="//div[@class='elementor-column elementor-col-25 elementor-inner-column elementor-element elementor-element-8491c58']")WebElement linksAboutCourses;
	@FindBy(xpath="//div//a[@href='#about-qa']")WebElement aboutQourseQaBTN;
	@FindBy(xpath="//span[@class='elementor-button-text'][contains(text(), 'תוכנית הקורס ')]")WebElement coursePlanQABTN;
	@FindBy(xpath="//span[@class='elementor-button-text'][contains(text(), 'היתרונות שלנו')]")WebElement ourAdvantagesBTN;
	@FindBy(xpath="//span[@class='elementor-button-text'][contains(text(), 'בסיום הקורס ')]")WebElement inTheEndOfCourseBTN;
	@FindBy(xpath="//*[contains(text(),'עברו לסילבוס המלא')]")WebElement syllaBus;
	@FindBy(id="elementor-tab-title-2361")WebElement Q1;
	@FindBy(id="elementor-tab-title-2362")WebElement Q2;
	@FindBy(id="elementor-tab-title-2363")WebElement Q3;
	@FindBy(id="elementor-tab-title-2364")WebElement Q4;
	@FindBy(id="elementor-tab-title-2365")WebElement Q5;



	public QaCourse(WebDriver driver) {
		this.driver=driver;
		openTabs = Keys.chord(Keys.CONTROL,Keys.ENTER);
		openedTabs = new ArrayList<String>();
		jse= (JavascriptExecutor)driver;
		PageFactory.initElements(driver, this);
	}

	public boolean textButtonsFunctionality() {
		WebElement[]btnArr= {aboutQourseQaBTN,coursePlanQABTN,ourAdvantagesBTN,inTheEndOfCourseBTN,syllaBus};
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

	public boolean faQFunc() {
		WebElement[]faq= {Q1,Q2,Q3,Q4,Q5};
		WebElement j;
		try {
			for(int i=0;i<faq.length;i++) {
				j=faq[i];
				jse.executeScript("arguments[0].click();", j);
				
			}
			return true;
		}

		catch(Exception e){
			e.printStackTrace();
			return false;
		}

	}
	public boolean linksAtTheFooterClicksSVColumn() {

		System.out.println("There are : " + linksAboutSV.findElements(By.tagName("a")).size()+" "+"links in the College column at the footer");
		int clicksCounter=0;

		try {
			for(int i=0;i<linksAboutSV.findElements(By.tagName("a")).size();i++) {

				linksAboutSV.findElements(By.tagName("a")).get(i).sendKeys(openTabs);
				clicksCounter++;
			}
			System.out.println("Clicked : "+" "+clicksCounter+" "+"links at the footer in the College column");

			return true;
		}

		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	public boolean linksAtTheFooterClicksCoursesColumn() {

		System.out.println("There are : " + linksAboutCourses.findElements(By.tagName("a")).size()+" "+"links in the Courses column at the footer");
		int clicksCounter=0;
		try {
			for(int i=0;i<linksAboutCourses.findElements(By.tagName("a")).size();i++) {

				linksAboutCourses.findElements(By.tagName("a")).get(i).sendKeys(openTabs);
				clicksCounter++;
			}
			Set<String>allWindows = driver.getWindowHandles();
			openedTabs = new ArrayList<String>(allWindows);

			System.out.println("Clicked : "+" "+clicksCounter+" "+"links at the footer in the Courses column");
			System.out.println("Total opened tabs : "+" "+openedTabs.size()+" "+"including Parent window");
			if(openedTabs.size()==13)
				return true;
		}

		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return false;
	}

}









