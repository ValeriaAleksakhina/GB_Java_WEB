package ru.gb.pom;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import ru.gb.pom.elements.GroupTableRow;
import ru.gb.pom.elements.StudentTableRow;

import static com.codeborne.selenide.Selenide.*;

public class MainPage {
    private SelenideElement usernameLinkInNavBar = $("li.mdc-menu-surface--anchor");
    //private SelenideElement createGroupButton = $("create-btn");
    private SelenideElement createGroupButton = $("button#create-btn");
    private SelenideElement groupNameField = $x("//form//span[contains(text(), 'Login')]/following-sibling::input");
    private SelenideElement submitButtonOnModalWindow = $("form div.submit button");
    private SelenideElement closeCreateGroupIcon = $x("//span[text()='Creating Study Group']" + "//ancestor::div[contains(@class, 'form-modal-header')]//button");

    // Create Students Modal Window
    private SelenideElement createStudentsFormInput = $("div#generateStudentsForm-content input");
    private SelenideElement saveCreateStudentsForm = $("div#generateStudentsForm-content div.submit button");
    private SelenideElement closeCreateStudentsFormIcon = $x("//h2[@id='generateStudentsForm-title']/../button");

    private ElementsCollection rowsInGroupTable = $$x("//table[@aria-label='Tutors list']/tbody/tr");
    private ElementsCollection rowsInStudentTable = $$x("//table[@aria-label='User list']/tbody/tr");


    public SelenideElement waitAndGetGroupTitleByText(String title) {
        String xpath = String.format("//table[@aria-label='Tutors list']/tbody//td[text()='%s']", title);
        //Selenide.sleep(3000);
        return Selenide.$x(xpath).should(Condition.visible);
    }

    public void createGroup(String groupName) {
        createGroupButton.should(Condition.visible).click();
        groupNameField.should(Condition.visible).setValue(groupName);
        submitButtonOnModalWindow.click();
        waitAndGetGroupTitleByText(groupName);
    }

    public void closeCreateGroupModalWindow() {
        closeCreateGroupIcon.click();
        closeCreateGroupIcon.should(Condition.disappear);
    }

    public void typeAmountOfStudentsInCreateStudentsForm(int amount) {
        createStudentsFormInput.should(Condition.visible).setValue(String.valueOf(amount));
    }

    public void clickSaveButtonOnCreateStudentsForm() throws InterruptedException {
        saveCreateStudentsForm.should(Condition.visible).click();
        Thread.sleep(5000);
    }

    public void closeCreateStudentsModalWindow() {
        closeCreateStudentsFormIcon.click();
        closeCreateStudentsFormIcon.should(Condition.disappear);
    }

    public String getUsernameLabelText() {
        return usernameLinkInNavBar.should(Condition.visible).getText().replace("\n", " ");
    }

    // Group Table Section
    public void clickTrashIconOnGroupWithTitle(String title) {

        getGroupRowByTitle(title).clickTrashIcon();
    }

    public void clickRestoreFromTrashIconOnGroupWithTitle(String title) {
        getGroupRowByTitle(title).clickRestoreFromTrashIcon();
    }

    public void clickAddStudentsIconOnGroupWithTitle(String title) {

        getGroupRowByTitle(title).clickAddStudentsIcon();
    }

    public void clickZoomInIconOnGroupWithTitle(String title) {

        getGroupRowByTitle(title).clickZoomInIcon();
    }

    public String getStatusOfGroupWithTitle(String title) {

        return getGroupRowByTitle(title).getStatus();
    }

    private GroupTableRow getGroupRowByTitle(String title) {
        return rowsInGroupTable
                .asDynamicIterable()
                .stream()
                .map(GroupTableRow::new)
                .filter(row -> row.getTitle().equals(title))
                .findFirst().orElseThrow();
    }

    // Students Table Section

    public void clickTrashIconOnStudentWithName(String name) {

        getStudentRowByName(name).clickTrashIcon();
    }

    public void clickRestoreFromTrashIconOnStudentWithName(String name) {
        getStudentRowByName(name).clickRestoreFromTrashIcon();
    }

    public String getStatusOfStudentWithName(String name) {

        return getStudentRowByName(name).getStatus();
    }

    public String getFirstGeneratedStudentName() {
        //wait.until(ExpectedConditions.visibilityOfAllElements(rowsInStudentTable));
        return rowsInStudentTable
                .asDynamicIterable()
                .stream()
                .map(StudentTableRow::new)
                .findFirst().orElseThrow().getName();
    }

    private StudentTableRow getStudentRowByName(String name) {
        //wait.until(ExpectedConditions.visibilityOfAllElements(rowsInStudentTable));
        return rowsInStudentTable
                .asDynamicIterable()
                .stream()
                .map(StudentTableRow::new)
                .filter(row -> row.getName().equals(name))
                .findFirst().orElseThrow();
    }

}
