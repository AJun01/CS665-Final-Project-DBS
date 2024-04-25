import javax.swing.*;
/*
 * This is Main
 * this is responsible the initiating the main page and drop the music
 *  */
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main extends JFrame{
public static void main(String[] args) {
    BackgroundMusicPlayer player = BackgroundMusicPlayer.getInstance();
    player.play("/Users/aj/IdeaProjects/BBproject/src/bgmusic.wav");
    SwingUtilities.invokeLater(() -> {
        MainView view = new MainView();
        MainController controller = new MainController(view);
        view.setVisible(true);
    });
}
}