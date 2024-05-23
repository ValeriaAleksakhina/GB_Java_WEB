package ru.gb.pom.elements;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byXpath;

public class StudentTableRow {

    private final SelenideElement root;

    public StudentTableRow(SelenideElement root) {
        this.root = root;
    }

    public String getName() {
        return root.findElement(byXpath("./td[2]")).getText();
    }

    public String getStatus() {
        return root.findElement(byXpath("./td[4]")).getText();
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

}
