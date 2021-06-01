package pages;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CoursesPage {

	private WebDriver driver;
	private String[]expectedTitles;
	private ArrayList<WebElement>popularCourses;



	@FindBy(xpath="//div[@class='elementor-element elementor-element-d9df7d5 elementor-align-center elementor-widget elementor-widget-button']//a")WebElement automationCoursesPageBTN;
	@FindBy(xpath="//a[contains(text(),'קורס בניית אתרים ואפליקציות WEB')]")WebElement webAppDevLink;
	@FindBy(xpath="(//a[contains(text(),'קורס QA')])[5]")WebElement QALink;
	@FindBy(xpath="//a[contains(text(),'קורס ניהול רשתות ואבטחת מידע')]")WebElement ITLink;
	@FindBy(xpath="//a[contains(text(),'קורס גיימינג - פיתוח משחקים')]")WebElement gamingLink;
	@FindBy(xpath="(//div[@class='elementor-button-wrapper']/a/span)[1]")WebElement automationLink;
	@FindBy(xpath="(//div[@class='elementor-button-wrapper']/a/span)[2]")WebElement fullStackLink;
	@FindBy(xpath="(//div[@class='elementor-button-wrapper']/a/span)[3]")WebElement nodeJSlink;


	public CoursesPage(WebDriver driver) {
		this.driver = driver;
		expectedTitles = new String[3];
		popularCourses = new  ArrayList<WebElement>();
		PageFactory.initElements(driver, this);

	}

	public boolean toAutomationCoursesPage() {
		try {
			automationCoursesPageBTN.click();
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}

	}
	public boolean verifyPopularCoursesLinks() throws MalformedURLException, IOException {

		popularCourses.add(webAppDevLink);
		popularCourses.add(QALink);
		popularCourses.add(ITLink);
		popularCourses.add(gamingLink);
		try {
			for(int i=0;i<popularCourses.size();i++){
				WebElement j= popularCourses.get(i);
				String url=j.getAttribute("href");
				verifyLinkActive(url);
			}  
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public static void verifyLinkActive(String linkUrl)
	{
		try 
		{
			URL url = new URL(linkUrl);

			HttpURLConnection httpURLConnect=(HttpURLConnection)url.openConnection();

			httpURLConnect.setConnectTimeout(3000);

			httpURLConnect.connect();

			if(httpURLConnect.getResponseCode()==200)
			{
				System.out.println(linkUrl+" - " +httpURLConnect.getResponseMessage());
			}
			if(httpURLConnect.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND)  
			{
				System.out.println(linkUrl+" - " +httpURLConnect.getResponseMessage() + " - "+ HttpURLConnection.HTTP_NOT_FOUND);
			}
		} catch (Exception e) {

		}
	} 
	public boolean moreCoursesButtonsAndContentVerify() {
		WebElement[]moreCourses= {automationLink,fullStackLink,nodeJSlink};
		WebElement j;
		boolean result=false;
		for(int i = 0;i<moreCourses.length;i++) {
			j=moreCourses[i];
			j.click();
			expectedTitles[i]=driver.getTitle();
			if(expectedTitles[i]!=null) {  
				result= true;
				driver.navigate().back();
			}
		}
		System.out.println(Arrays.toString(expectedTitles));
		return result;
	}
}



