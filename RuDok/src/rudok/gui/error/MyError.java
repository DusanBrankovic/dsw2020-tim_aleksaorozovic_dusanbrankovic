package rudok.gui.error;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MyError {

    private String message;
    private String title;

    public MyError(String message, String title) {
        this.message = message;
        this.title = title;
    }
}
