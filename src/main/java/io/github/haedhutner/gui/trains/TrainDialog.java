package io.github.haedhutner.gui.trains;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import io.github.haedhutner.db.DBManager;
import io.github.haedhutner.entity.Line;
import io.github.haedhutner.entity.Train;
import io.github.haedhutner.gui.CreateFilterUpdateDialog;
import io.github.haedhutner.managers.LineManager;
import io.github.haedhutner.managers.TrainManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Vector;

public class TrainDialog extends JDialog implements CreateFilterUpdateDialog<Train, Integer> {

    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JSpinner idSpinner;
    private JTextField departureTextField;
    private JComboBox<Line> routeCombo;

    private Train train;

    private TrainDialog() {
        idSpinner.setModel(new SpinnerNumberModel());
        idSpinner.setEnabled(false);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setResizable(false);
        setMinimumSize(new Dimension(400, 200));
        setPreferredSize(contentPane.getMinimumSize());
        routeCombo.setModel(new DefaultComboBoxModel<>(new Vector<>(LineManager.getInstance().getAll())));

        buttonCancel.addActionListener(e -> onCancel());

        departureTextField.setText(LocalDateTime.now().toString());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    public static TrainDialog create() {
        TrainDialog dialog = new TrainDialog();

        dialog.buttonOK.setText("Create");
        dialog.setTitle("Create New Train");

        dialog.buttonOK.addActionListener(e -> dialog.createOk());
        dialog.buttonCancel.addActionListener(e -> dialog.onCancel());

        dialog.setVisible(true);
        return dialog;
    }

    public static TrainDialog update(Train train) {
        TrainDialog dialog = new TrainDialog();

        dialog.buttonOK.setText("Update");
        dialog.setTitle("Update Train");

        dialog.setEntity(train);

        dialog.buttonOK.addActionListener(e -> dialog.updateOk());
        dialog.buttonCancel.addActionListener(e -> dialog.onCancel());

        dialog.setVisible(true);
        return dialog;
    }

    public static TrainDialog filter() {
        TrainDialog dialog = new TrainDialog();

        dialog.buttonOK.setText("Filter");
        dialog.setTitle("Filter Trains");

        dialog.buttonOK.addActionListener(e -> dialog.filterOk());
        dialog.buttonCancel.addActionListener(e -> dialog.filterCancel());

        dialog.setVisible(true);
        return dialog;
    }

    private void onCancel() {
        dispose();
    }

    @Override
    public DBManager<Train, Integer> getManager() {
        return TrainManager.getInstance();
    }

    @Override
    public Train getEntity() {
        if (train != null) return train;
        else {
            train = new Train();
            updateEntity();
            return train;
        }
    }

    @Override
    public void updateEntity() {
        if (train == null) return;
        train.setRoute((Line) routeCombo.getSelectedItem());

        LocalDateTime parsed;

        try {
            parsed = LocalDateTime.parse(departureTextField.getText());
        } catch (DateTimeParseException e) {
            parsed = LocalDateTime.now();
        }

        train.setDepartureTime(parsed);
    }

    @Override
    public void setEntity(Train entity) {
        this.train = entity;
        this.idSpinner.setValue(entity.getId());
        this.departureTextField.setText(entity.getDepartureTime().toString());
        this.routeCombo.setSelectedItem(entity.getRoute());
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        contentPane = new JPanel();
        contentPane.setLayout(new GridLayoutManager(2, 1, new Insets(10, 10, 10, 10), -1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1, true, false));
        panel1.add(panel2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        buttonOK = new JButton();
        buttonOK.setText("OK");
        panel2.add(buttonOK, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonCancel = new JButton();
        buttonCancel.setText("Cancel");
        panel2.add(buttonCancel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(4, 2, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel3.add(spacer2, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Id");
        panel3.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        idSpinner = new JSpinner();
        panel3.add(idSpinner, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Route");
        panel3.add(label2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Departing At");
        panel3.add(label3, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        departureTextField = new JTextField();
        panel3.add(departureTextField, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        routeCombo = new JComboBox();
        panel3.add(routeCombo, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }
}
