package com.github.develop.function.window;

import com.github.develop.assistant.Application;
import com.github.develop.assistant.BaseWindow;
import com.github.develop.function.search.BaiduSearchWay;
import com.github.develop.function.search.GoogleSearchWay;
import com.github.develop.function.search.MvnRepositorySearchWay;
import com.github.develop.function.search.SearchWay;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.*;

/**
 * 搜索/浏览窗口
 */
public class SearchWindow extends BaseWindow {

    public SearchWindow(Application application) {
        super(application, "Search / Browse", 30, 11, true);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/image/icon.png")));
        this.setResizable(false);

        this.setLayout(new GridLayout(1, 1));

        JTabbedPane tabbedPane = new JTabbedPane();

        SearchPanel searchPanel = new SearchPanel(this);
        BrowsePanel browsePanel = new BrowsePanel(this);

        Image internetIcon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/image/internet.png"));
        Image folderIcon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/image/folder.png"));

        tabbedPane.addTab("Search", new ImageIcon(internetIcon), searchPanel, "Search");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_S);
        tabbedPane.addTab("Browse", new ImageIcon(folderIcon), browsePanel, "Browse");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_B);
        tabbedPane.setDisplayedMnemonicIndexAt(0, 0);
        tabbedPane.setDisplayedMnemonicIndexAt(1, 0);
        this.add(tabbedPane);
    }


    public void toggle() {
        setVisible(!isVisible());
    }

    public static void main(String[] args) {
        new SearchWindow(null).toggle();
    }

}
