package ru.gb.pom.elements;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byXpath;

public class GroupTableRow {

    private final SelenideElement root;

    public GroupTableRow(SelenideElement root) {
        this.root = root;
    }

    public String getTitle() {
        return root.findElement(byXpath("./td[2]")).getText();
    }

    public String getStatus() {
        return root.findElement(byXpath("./td[3]")).getText();
    }

    public void clickTrashIcon() {
        root.findElement(byXpath("./td/button[text()='delete']")).click();
        Selenide.sleep(3000);
        root.findElement(byXpath("./td/button[text()='restore_from_trash']"));
    }

    public void clickRestoreFromTrashIcon() {
        root.findElement(byXpath("./td/button[text()='restore_from_trash']")).click();
        Selenide.sleep(3000);
        root.findElement(byXpath("./td/button[text()='delete']"));
    }

    public void clickAddStudentsIcon() {
        root.findElement(byCssSelector("td button i.material-icons")).click();
    }

    public void clickZoomInIcon() {
        root.findElement(byXpath(".//td/button[contains(., 'zoom_in')]")).click();
    }

}
