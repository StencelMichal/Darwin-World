package agh.cs.lab;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import java.io.IOException;

public class Visualizer {

    private Label[][] labels;

    private Screen screen;

    Visualizer(int width, int height) throws IOException {
        labels = new Label[width][height];
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        Screen screen = terminalFactory.createScreen();
        screen.startScreen();
        this.screen = screen;
        final WindowBasedTextGUI textGUI = new MultiWindowTextGUI(screen);
        final Window window = new BasicWindow("Darwin World");
        Panel contentPanel = new Panel(new GridLayout(width));
        GridLayout gridLayout = (GridLayout)contentPanel.getLayoutManager();
        gridLayout.setHorizontalSpacing(0); // szerokość okna
        gridLayout.setVerticalSpacing(0);

        for(int i=0; i<width; i++){
            for(int j=0; j<height; j++){
                Label label = new Label(" X ");
                label.setPreferredSize(new TerminalSize(2,1));
                label.setBackgroundColor(TextColor.ANSI.RED);
                label.setForegroundColor(TextColor.ANSI.WHITE);
                labels[i][j] = label;
                contentPanel.addComponent(label.withBorder(Borders.singleLine()));
            }
        }

        window.setComponent(contentPanel);
        textGUI.addWindowAndWait(window);

    }

    public void change() throws IOException {
        for (Label[] label : labels) {
            for (Label label1 : label) {
                label1.setForegroundColor(TextColor.ANSI.YELLOW_BRIGHT);
            }
        }
        screen.startScreen();
    }

}
