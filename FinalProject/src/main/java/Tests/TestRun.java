package Tests;
import org.openqa.selenium.WebDriver;
import static org.testng.Assert.assertTrue;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AutomationCourses;
import pages.CoursesPage;
import pages.FullStackPage;
import pages.HitechEndorsement;
import pages.HomePage;
import pages.QaCourse;


public class TestRun {  

	private WebDriver driver;
	private SoftAssert softAssertion;
	private HomePage hp;
	private QaCourse qa;
	private CoursesPage cp;
	private AutomationCourses ac;
	private FullStackPage fs;
	private HitechEndorsement he;

	@BeforeMethod
	public void beforeEachTest() {
		driver = new ChromeDriver();
		softAssertion=new SoftAssert();
		driver.manage().window().maximize();
		driver.get("https://svcollege.co.il/");
		hp = new HomePage(driver);
		qa = new QaCourse(driver);
		cp = new CoursesPage(driver);
		ac = new AutomationCourses(driver);
		he = new HitechEndorsement(driver);
		fs = new FullStackPage(driver);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@AfterMethod
	public void afterEachTest() {
		driver.quit();
		System.out.println("(==============Ended test=============)" );
	}


	@Test
	public void quickContactSanity() {
		assertTrue(hp.SanityTest("Name","bob585173@gmail.com","0555555555"));

	}

	@Test  
	public void HomePageApplyForConsultationInputsAndSendBTN() {
		hp.applyForConsultation("Stas", "0527412468", "staslocovai@gmail.com");
		softAssertion.assertEquals(driver.findElement(By.id("form-field-name")).getAttribute("value"), "Stas","a");
		softAssertion.assertEquals(driver.findElement(By.id("form-field-16af83e")).getAttribute("value"),"0527412468","b");
		softAssertion.assertEquals(driver.findElement(By.id("form-field-email")).getAttribute("value"),"staslocovai@gmail.com","c");
		softAssertion.assertAll();
	}

	@Test 
	public void getContentThroughNavBar()     {
		assertTrue(hp.navBarContent());
	}

	@Test
	public void HomePageVerifyElementsClickability()  {
		softAssertion.assertTrue(hp.clickOnCheckBox());
		softAssertion.assertTrue(hp.clickElements());
		softAssertion.assertAll();

	}

	@Test
	public void chatBotSendMsg() {
		hp.chatBotFunc();
		assertTrue(driver.getPageSource().contains("Test"));
	}

	@Test
	public void QaCoursePageButtonsFunctionality()  {
		hp.navBarClickOnQaCourse();
		qa.textButtonsFunctionality();
		assertTrue(driver.getPageSource().contains("סילבוס QA BASIC"));
	}

	@Test
	public void QAcoursePageFaQFunctional()  {
		hp.navBarClickOnQaCourse();
		qa.faQFunc();
		softAssertion.assertEquals(driver.getPageSource().contains("בוגרי המכללה בכלל וקורס בודק תוכנה בפרט מוצאים עבודה כ-60 ימים בממוצע מרגע סיום הקורס"),true,"a");
		softAssertion.assertEquals(driver.getPageSource().contains("על מנת להתקבל לקורס אין צורך בתואר"),true,"b");
		softAssertion.assertEquals(driver.getPageSource().contains("קורס בדיקת תוכנה אורך 4 חודשים ונערך אחת לשבוע."),true,"c");
		softAssertion.assertEquals(driver.getPageSource().contains("בוגרי קורס QA ממלאים לרוב תפקיד של בדיקות תוכנה ידניות."),true,"d");
		softAssertion.assertEquals(driver.getPageSource().contains("בהחלט. הדגש המרכזי בקורס הוא על רכישת כלים מעשיים לעבודה כאיש QA ובדיקת תוכנה"),true,"e");
		softAssertion.assertAll();
	}

	@Test
	public void QAcourseFooterLinksClickabilityFunctional() {  
		hp.navBarClickOnQaCourse();
		softAssertion.assertTrue(qa.linksAtTheFooterClicksSVColumn());
		softAssertion.assertTrue(qa.linksAtTheFooterClicksCoursesColumn());
		softAssertion.assertAll();

	}

	@Test 
	public void applyToAutomationHybridCourse() {
		hp.clickAutomationOnlineBTN();
		cp.toAutomationCoursesPage();
		ac.applyToHybridAutomationCourse();
		assertTrue(ac.applyToUpcomingOnlineCourse("1", "2", "3"));

	}
	@Test
	public void exploreAutomationSyllabuses()  {
		hp.clickAutomationOnlineBTN();
		cp.toAutomationCoursesPage();
		ac.syllabusButtons();
		ac.viewSyllabus();
		ac.switchTabs();
		softAssertion.assertEquals(driver.getPageSource().contains("Setting java programming workspace on "),true,"a");
		softAssertion.assertEquals(driver.getPageSource().contains("Using selenium Java API"),true,"b");
		softAssertion.assertEquals(driver.getPageSource().contains("Object oriented programing"),true,"c");
		softAssertion.assertEquals(driver.getPageSource().contains("Browsers configurations and hacks"),true,"d");
		softAssertion.assertEquals(driver.getPageSource().contains("JUnit setup and teardown"),true,"e");
		ac.viewHybridCourseContent();
		softAssertion.assertTrue(driver.getCurrentUrl().contains("https://svcollege.co.il/wp-content"));
		softAssertion.assertAll();
	}

	@Test 
	public void applyFromNextAndPreviousCourses() {
		hp.clickAutomationOnlineBTN();
		cp.toAutomationCoursesPage();
		softAssertion.assertTrue(ac.applyThroughViewNextCourse("Test","Test","Test"));
		ac.navigateBack();
		ac.viewPreviousCourseBTN();
		softAssertion.assertTrue(fs.applyToFullStackCourse("Test", "Test", "Test"));
		softAssertion.assertAll();
	}


	@Test
	public void checkPopularCoursesURlWithHttpConnectionAPI () throws MalformedURLException, IOException {
		hp.toAllCoursesClick();
		assertTrue(cp.verifyPopularCoursesLinks());

	}

	@Test
	public void CoursesPageMoreCoursesContentVerify() {
		hp.toAllCoursesClick();
		assertTrue(cp.moreCoursesButtonsAndContentVerify());

	}

	@Test 
	public void HitechPageTextLinksVerify()  {
		hp.navBarClickHiTechEndorsement();
		assertTrue(he.textLinksVerify());
	}

	@Test
	public void integrations() throws Exception {
		hp.navBarClickHiTechEndorsement();
		assertTrue(he.integration());
	}
}













































