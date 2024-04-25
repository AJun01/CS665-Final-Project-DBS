import javax.swing.*;
/*
 * This is MainController class
 * this is controller role in MVC pattern
 *
 * in charge of listening to events triggering
 *  */

public class MainController {
    private MainView view;

    public MainController(MainView view) {
        this.view = view;
        initView();
    }

    private void initView() {
        view.boardingButton.addActionListener(e -> SwingUtilities.invokeLater(() -> new DogBoardingCampDatabase().setVisible(true)));
        view.pickingButton.addActionListener(e -> SwingUtilities.invokeLater(() -> new DogPicking().setVisible(true)));
        view.viewingButton.addActionListener(e -> SwingUtilities.invokeLater(() -> new DogDataTable().setVisible(true)));
    }
}

