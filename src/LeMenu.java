import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

public class LeMenu extends MenuButton{
    public LeMenu() {
        super("Menu");
        this.getItems().addAll(
                new MenuItem("Option 1"),
                new MenuItem("Option 2"),
                new MenuItem("Option 3")
        );
    }
}
