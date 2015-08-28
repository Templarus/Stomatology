
package stomatology;

import javafx.application.Application;
import javafx.stage.Stage;

public class Stomatology extends Application
{
    @Override
    public void start(Stage primaryStage)
    {
        Start st = new Start();
        st.setLocationRelativeTo(null);
        st.setVisible(true);

    }
    public static void main(String[] args)
    {
        launch(args);  
    }
    
}
