package agh.cs.lab;


import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;

public class Main {

    private final int sizeX=40;

    private final int sizeY=20;

    private final int amountOfAnimals=3;

    public static void main(String[] args) throws IOException {
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        Screen screen = null;
        screen = terminalFactory.createScreen();
        screen.startScreen();
        DefaultWindowManager defaultWindowManager = new DefaultWindowManager();
        final WindowBasedTextGUI textGUI = new MultiWindowTextGUI(screen);
        final Window window = new BasicWindow("My Root Window");
        Panel contentPanel = new Panel(new GridLayout(5));
        GridLayout gridLayout = (GridLayout)contentPanel.getLayoutManager();
        gridLayout.setHorizontalSpacing(0); // szerokość okna
        gridLayout.setVerticalSpacing(0);
//        Label title = new Label("This is a label that spans two columns");
//        title.setLayoutData(GridLayout.createLayoutData(
//                GridLayout.Alignment.BEGINNING, // Horizontal alignment in the grid cell if the cell is larger than the component's preferred size
//                GridLayout.Alignment.BEGINNING, // Vertical alignment in the grid cell if the cell is larger than the component's preferred size
//                true,       // Give the component extra horizontal space if available
//                false,        // Give the component extra vertical space if available
//                2,                  // Horizontal span
//                1));                  // Vertical span\
//        Label label = new Label("###");
////        label.setPosition(new TerminalPosition(100,100));
////        label.setSize(new TerminalSize(100,100));
////        label.setLabelWidth(30);
////        label.setLayoutData(GridLayout.createLayoutData(GridLayout.Alignment.BEGINNING, GridLayout.Alignment.BEGINNING,false,false,1,1));
//        Label l2 = new Label("asdvf");
//        l2.withBorder(Borders.singleLine());
//        l2.setPosition(new TerminalPosition(50,10));
//        l2.setSize(new TerminalSize(10,10));
//        l2.setLayoutData(GridLayout.createLayoutData(GridLayout.Alignment.END, GridLayout.Alignment.END,false,false,3,2));
        for(int i = 0; i<5; i++){
            for( int j =0;j <5; j++){
                Label label = new Label(" X ");
                label.setPreferredSize(new TerminalSize(2,1));
                label.setBackgroundColor(TextColor.ANSI.RED);
                label.setForegroundColor(TextColor.ANSI.WHITE);
                contentPanel.addComponent(label.withBorder(Borders.singleLine()));
            }
        }
//        label.setForegroundColor(TextColor.ANSI.CYAN);
//        label.withBorder(Borders.singleLine());
//        label.setBackgroundColor(TextColor.ANSI.RED);
//        //contentPanel.addComponent(title);
//        contentPanel.addComponent(label.withBorder(Borders.doubleLine()));
//        contentPanel.addComponent(l2.withBorder(Borders.singleLine()));



//        Panel contentPanel2 = new Panel(new GridLayout(10));
//        GridLayout gridLayout2 = (GridLayout)contentPanel.getLayoutManager();
//        gridLayout.setHorizontalSpacing(0); // szerokość okna
//        gridLayout.setVerticalSpacing(0);
//        Label lab3 = new Label("333");
//        contentPanel2.addComponent(lab3);

//        contentPanel.addComponent(new Label("Text Box (aligned)"));
//        contentPanel.addComponent(
//                new TextBox()
//                        .setLayoutData(GridLayout.createLayoutData(GridLayout.Alignment.BEGINNING, GridLayout.Alignment.CENTER)));

            /*
            Here is an example of customizing the regular text box component so it masks the content and can work for
            password input.
             */
//        contentPanel.addComponent(new Label("Password Box (right aligned)"));
//        contentPanel.addComponent(
//                new TextBox()
//                        .setMask('*')
//                        .setLayoutData(GridLayout.createLayoutData(GridLayout.Alignment.END, GridLayout.Alignment.CENTER)));

            /*
            While we are not going to demonstrate all components here, here is an example of combo-boxes, one that is
            read-only and one that is editable.
             */
//        contentPanel.addComponent(new Label("Read-only Combo Box (forced size)"));
//        List<String> timezonesAsStrings = new ArrayList<>(Arrays.asList(TimeZone.getAvailableIDs()));
//        ComboBox<String> readOnlyComboBox = new ComboBox<>(timezonesAsStrings);
//        readOnlyComboBox.setReadOnly(true);
//        readOnlyComboBox.setPreferredSize(new TerminalSize(20, 1));
//        contentPanel.addComponent(readOnlyComboBox);
//
//        contentPanel.addComponent(new Label("Editable Combo Box (filled)"));
//        contentPanel.addComponent(
//                new ComboBox<>("Item #1", "Item #2", "Item #3", "Item #4")
//                        .setReadOnly(false)
//                        .setLayoutData(GridLayout.createHorizontallyFilledLayoutData(1)));

            /*
            Some user interactions, like buttons, work by registering callback methods. In this example here, we're
            using one of the pre-defined dialogs when the button is triggered.
             */
//        contentPanel.addComponent(new Label("Button (centered)"));
//        contentPanel.addComponent(new Button("Button", () -> MessageDialog.showMessageDialog(textGUI, "MessageBox", "This is a message box", MessageDialogButton.OK)).setLayoutData(GridLayout.createLayoutData(GridLayout.Alignment.CENTER, GridLayout.Alignment.CENTER)));

            /*
            Close off with an empty row and a separator, then a button to close the window
//             */
//        contentPanel.addComponent(
//                new EmptySpace()
//                        .setLayoutData(
//                                GridLayout.createHorizontallyFilledLayoutData(2)));
//        contentPanel.addComponent(
//                new Separator(Direction.HORIZONTAL)
//                        .setLayoutData(
//                                GridLayout.createHorizontallyFilledLayoutData(2)));
//        contentPanel.addComponent(
//                new Button("Close", window::close).setLayoutData(
//                        GridLayout.createHorizontallyEndAlignedLayoutData(2)));

            /*
            We now have the content panel fully populated with components. A common mistake is to forget to attach it to
            the window, so let's make sure to do that.
             */
//        window.setComponent(contentPanel2);

            /*
            Now the window is created and fully populated. As discussed above regarding the threading model, we have the
            option to fire off the GUI here and then later on decide when we want to stop it. In order for this to work,
            you need a dedicated UI thread to run all the GUI operations, usually done by passing in a
            SeparateTextGUIThread object when you create the TextGUI. In this tutorial, we are using the conceptually
            simpler SameTextGUIThread, which essentially hijacks the caller thread and uses it as the GUI thread until
            some stop condition is met. The absolutely simplest way to do this is to simply ask lanterna to display the
            window and wait for it to be closed. This will initiate the event loop and make the GUI functional. In the
            "Close" button above, we tied a call to the close() method on the Window object when the button is
            triggered, this will then break the even loop and our call finally returns.
             */




        final Window window2 = new BasicWindow("Stats");
        window2.setPosition(new TerminalPosition(10,10));
        textGUI.addWindowAndWait(window2);

        window.setComponent(contentPanel);
        textGUI.addWindowAndWait(window);
    }

}
