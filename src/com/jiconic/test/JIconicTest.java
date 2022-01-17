package com.jiconic.test;

import com.jiconic.IconProperties;
import com.jiconic.JIconic;
import com.jiconic.icons.fontawesome.*;
import com.jiconic.icons.materialdesign.*;
import com.jiconic.providers.IconProvider;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class JIconicTest {

    public static void main(String[] args) {

        BufferedImage iconImage = null;

        int totalIcons = 0;

        JIconic.registerProvider(new FontAwesomeSolidProvider());
        JIconic.registerProvider(new FontAwesomeLightProvider());
        JIconic.registerProvider(new FontAwesomeRegularProvider());
        JIconic.registerProvider(new FontAwesomeBrandProvider());
        JIconic.registerProvider(new FontAwesomeDuotoneProvider());
        JIconic.registerProvider(new MaterialDesignRegularProvider());
        JIconic.registerProvider(new MaterialDesignOutlinedProvider());
        JIconic.registerProvider(new MaterialDesignSharpProvider());
        JIconic.registerProvider(new MaterialDesignRoundProvider());
        JIconic.registerProvider(new MaterialDesignTwoToneProvider());


        createTestWindow();

        /*
        for(FontAwesomeV5Free icon : FontAwesomeV5Free.values()) {
            System.out.println("Testing Icon: Font-Awesome [" + icon.getName() + "]");
            iconImage = JIconic.buildImage(icon, 40f, 0, Color.BLACK);
            totalIcons++;
        }



        IconProperties iconProperties = new IconProperties().setSize(40f).setForeground(Color.BLACK);

        for(OpenIconicIcons icon : OpenIconicIcons.values()) {
            System.out.println("Testing Icon: Open-Iconic [" + icon.getName() + "]");
            iconImage = JIconic.build(icon, iconProperties);
            totalIcons++;
        }


        for(MaterialDesignIcons icon : MaterialDesignIcons.values()) {
            System.out.println("Testing Icon: Material Designs [" + icon.getName() + "]");
            iconImage = JIconic.buildImage(icon, 40f, 0, Color.BLACK);
            totalIcons++;
        }



        System.out.println("Tested " + totalIcons + " Icons.");

        if(iconImage == null) {
            System.err.println("Failed creating icon");
        } else {

            try {
                ImageIO.write( iconImage, "png", new File("./iconImage.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

         */

    }

    public static void createTestWindow() {

        ArrayList<String> icons = new ArrayList<>();

        IconProvider[] iconProviders = JIconic.getProviders();

        for(IconProvider iconProvider : iconProviders) {
            for(String icon : iconProvider.getIcons()) {
                icons.add(iconProvider.getPrefix() + "-" + icon);
            }
        }

        String[] comboModel = icons.toArray(new String[0]);


        final IconProperties iconProperties = new IconProperties().setSize(100f).setPrimaryColor(Color.BLACK).setSecondaryColor(Color.GRAY);

        JFrame frame = new JFrame("JIconic Test");
        JPanel contentPanel = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel(new BorderLayout());
        JComboBox<String> iconField = new JComboBox<>(comboModel);
        JButton primaryButton = new JButton("Primary Color");
        JButton secondaryButton = new JButton("Secondary Color");

        AutoCompletion.enable(iconField);

        JLabel iconLabel = new JLabel("");

        inputPanel.add(iconField, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));

        buttonPanel.add(primaryButton);
        buttonPanel.add(secondaryButton);

        inputPanel.add(buttonPanel, BorderLayout.SOUTH);

        inputPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        contentPanel.add(inputPanel, BorderLayout.NORTH);

        iconLabel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 10));

        contentPanel.add(iconLabel, BorderLayout.CENTER);

        primaryButton.addActionListener(e -> {
            Color newColor = JColorChooser.showDialog(frame, "Choose Primary Color", iconProperties.getPrimary());

            iconProperties.setPrimaryColor(newColor);

            ImageIcon imageIcon = (ImageIcon) JIconic.buildIcon(((String)iconField.getSelectedItem()), iconProperties);

            iconLabel.setIcon(imageIcon);
        });

        secondaryButton.addActionListener(e -> {
            Color newColor = JColorChooser.showDialog(frame, "Choose Secondary Color", iconProperties.getSecondary());

            iconProperties.setSecondaryColor(newColor);

            ImageIcon imageIcon = (ImageIcon) JIconic.buildIcon(((String)iconField.getSelectedItem()), iconProperties);

            iconLabel.setIcon(imageIcon);
        });



        iconField.addActionListener(e -> {
            if(JIconic.getProviderForIcon(((String)iconField.getSelectedItem())) != null) {
                ImageIcon imageIcon = (ImageIcon) JIconic.buildIcon(((String)iconField.getSelectedItem()), iconProperties);

                if(imageIcon == null) {
                    return;
                }

                iconLabel.setIcon(imageIcon);
            } else {
                JOptionPane.showMessageDialog(null, "The icon you entered `" + iconField.getSelectedItem() + "` is invalid.", "INVALID FONT-AWESOME ICON NAME", JOptionPane.ERROR_MESSAGE);
            }
        });



        frame.setContentPane(contentPanel);
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

}
