package com.github.develop.function.window;

import com.github.develop.function.bookmark.Bookmark;
import com.github.develop.function.utils.BrowseUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 单一书签面板
 */
public class SinpleBookmarkPanel extends JPanel {

    public SinpleBookmarkPanel(JFrame frame, Bookmark[] bookmarks) {
        this.setLayout(new GridLayout(1, 1));
        JList<Bookmark> jList = new JList<>(bookmarks);
        JScrollPane scrollPane = new JScrollPane(jList);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(scrollPane);
        jList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 2) {
                JList source = (JList) e.getSource();
                Bookmark bookmark = (Bookmark) source.getSelectedValue();
                BrowseUtils.browse(bookmark.getUrl());
                frame.dispose();
            }
            }
        });
    }
}
