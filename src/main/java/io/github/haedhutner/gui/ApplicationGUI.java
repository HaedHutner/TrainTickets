package io.github.haedhutner.gui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import io.github.haedhutner.entity.Line;
import io.github.haedhutner.entity.Ticket;
import io.github.haedhutner.entity.Train;
import io.github.haedhutner.gui.lines.LineDialog;
import io.github.haedhutner.gui.lines.TrainlinesTableModel;
import io.github.haedhutner.gui.ticket.TicketDialog;
import io.github.haedhutner.gui.trains.TrainDialog;
import io.github.haedhutner.gui.trains.TrainsTableModel;
import io.github.haedhutner.managers.LineManager;
import io.github.haedhutner.managers.TicketManager;
import io.github.haedhutner.managers.TrainManager;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class ApplicationGUI extends JFrame {

    private static ApplicationGUI instance = new ApplicationGUI();

    private JTabbedPane mainTabPanel;

    private JTable trainsTable;
    private JTable trainLinesTable;

    private JButton newTrainButton;
    private JButton updateTrainButton;
    private JButton deleteTrainButton;

    private JButton newTrainLine;
    private JButton deleteTrainLineButton;
    private JButton updateTrainLineButton;

    private JPanel mainContentPanel;
    private JButton filterTrainLines;
    private JButton filterTrainButton;
    private JTable ticketsTable;
    private JButton buyTicketButton;
    private JButton deleteTicketButton;
    private JButton updateTicketButton;
    private JButton filterTicketsButton;

    private ApplicationGUI() {
        super.setTitle("Train Tickets");
        super.add(mainContentPanel);
        super.setPreferredSize(new Dimension(800, 600));
        super.setMinimumSize(new Dimension(800, 600));
        super.setVisible(true);
        super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        LineManager.getInstance().init();
        TrainManager.getInstance().init();
        TicketManager.getInstance().init();

        newTrainLine.addActionListener(event -> LineDialog.create());
        deleteTrainLineButton.addActionListener(event -> DeleteEntityDialog.open(getSelectedLine(), LineManager.getInstance()));
        updateTrainLineButton.addActionListener(event -> LineDialog.update(getSelectedLine()));
        filterTrainLines.addActionListener(event -> LineDialog.filter());

        newTrainButton.addActionListener(event -> TrainDialog.create());
        deleteTrainButton.addActionListener(event -> DeleteEntityDialog.open(getSelectedTrain(), TrainManager.getInstance()));
        updateTrainButton.addActionListener(event -> TrainDialog.update(getSelectedTrain()));
        filterTrainButton.addActionListener(event -> TrainDialog.filter());

        buyTicketButton.addActionListener(event -> TicketDialog.create());
        deleteTicketButton.addActionListener(event -> DeleteEntityDialog.open(getSelectedTicket(), TicketManager.getInstance()));
        updateTicketButton.addActionListener(event -> TicketDialog.update(getSelectedTicket()));
        filterTicketsButton.addActionListener(event -> TicketDialog.filter());

        updateTables();
    }

    public static ApplicationGUI getInstance() {
        return instance;
    }

    public void updateTables() {
        updateTrainsTable();
        updateLinesTable();
        updateTicketsTable();
    }

    public void updateLinesTable() {
        LineManager.getInstance().mapTo(trainLinesTable);
    }

    public void updateTrainsTable() {
        TrainManager.getInstance().mapTo(trainsTable);
    }

    public void updateTicketsTable() {
        TicketManager.getInstance().mapTo(ticketsTable);
    }

    public Line getSelectedLine() {
        if (trainLinesTable.getSelectedRow() == -1) return null;
        TrainlinesTableModel model = (TrainlinesTableModel) trainLinesTable.getModel();
        return model.getAt(trainLinesTable.getSelectedRow());
    }

    public Train getSelectedTrain() {
        if (trainsTable.getSelectedRow() == -1) return null;
        TrainsTableModel model = (TrainsTableModel) trainsTable.getModel();
        return model.getAt(trainsTable.getSelectedRow());
    }

    public Ticket getSelectedTicket() {
        if (ticketsTable.getSelectedRow() == -1) return null;
        ResultSetTableModel model = (ResultSetTableModel) ticketsTable.getModel();

        Map<String, Object> ticketData = model.getAt(ticketsTable.getSelectedRow());

        Ticket ticket = new Ticket();

        System.out.println(ticketData.toString());

        ticket.setId((Integer) ticketData.get("TICKET_ID"));
        ticket.setPrice((Double) ticketData.get("TICKET_PRICE"));
        TrainManager.getInstance().select((Integer) ticketData.get("TICKET_TRAIN")).ifPresent(ticket::setTrain);

        return ticket;
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
        mainContentPanel = new JPanel();
        mainContentPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        mainTabPanel = new JTabbedPane();
        mainContentPanel.add(mainTabPanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(800, 600), null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        mainTabPanel.addTab("Trains", panel1);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(5, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        newTrainButton = new JButton();
        newTrainButton.setText("New Train");
        panel2.add(newTrainButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel2.add(spacer1, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        updateTrainButton = new JButton();
        updateTrainButton.setText("Update Train");
        panel2.add(updateTrainButton, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        deleteTrainButton = new JButton();
        deleteTrainButton.setText("Delete Train");
        panel2.add(deleteTrainButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        filterTrainButton = new JButton();
        filterTrainButton.setText("Filter Trains");
        panel2.add(filterTrainButton, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        panel1.add(scrollPane1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        trainsTable = new JTable();
        scrollPane1.setViewportView(trainsTable);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        mainTabPanel.addTab("Train Lines", panel3);
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(5, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel3.add(panel4, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        newTrainLine = new JButton();
        newTrainLine.setText("New Train Line");
        panel4.add(newTrainLine, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel4.add(spacer2, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        deleteTrainLineButton = new JButton();
        deleteTrainLineButton.setText("Delete Train Line");
        panel4.add(deleteTrainLineButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        updateTrainLineButton = new JButton();
        updateTrainLineButton.setText("Update Train Line");
        panel4.add(updateTrainLineButton, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        filterTrainLines = new JButton();
        filterTrainLines.setText("Filter Train Line");
        panel4.add(filterTrainLines, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane2 = new JScrollPane();
        panel3.add(scrollPane2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        trainLinesTable = new JTable();
        scrollPane2.setViewportView(trainLinesTable);
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new GridLayoutManager(7, 2, new Insets(0, 0, 0, 0), -1, -1));
        mainTabPanel.addTab("Tickets", panel5);
        final JScrollPane scrollPane3 = new JScrollPane();
        panel5.add(scrollPane3, new GridConstraints(0, 0, 7, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        ticketsTable = new JTable();
        scrollPane3.setViewportView(ticketsTable);
        buyTicketButton = new JButton();
        buyTicketButton.setText("Buy Ticket");
        panel5.add(buyTicketButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        panel5.add(spacer3, new GridConstraints(4, 1, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        deleteTicketButton = new JButton();
        deleteTicketButton.setText("Delete Ticket");
        panel5.add(deleteTicketButton, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        updateTicketButton = new JButton();
        updateTicketButton.setText("Update Ticket");
        panel5.add(updateTicketButton, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        filterTicketsButton = new JButton();
        filterTicketsButton.setText("Filter Tickets");
        panel5.add(filterTicketsButton, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainContentPanel;
    }
}
