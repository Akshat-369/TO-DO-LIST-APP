//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class TaskComponent extends JPanel implements ActionListener {
//    private JCheckBox checkbox;
//    private JTextPane taskField;
//    private JButton deleteButton;
//
//    public JTextPane getTaskField() {
//        return taskField;
//    }
//
//   //this panel is used that we can make updates to the task component panel when deleting task
//   private JPanel parentPanel;
//
//   public TaskComponent(JPanel parentPanel){
//       this.parentPanel = parentPanel;
//
//       //task field
//       taskField = new JTextPane();
//       taskField.setPreferredSize(CommonConstants.TASKFIELD_SIZE);
//       taskField.setContentType("text/html");
//
//       //checkbox
//       checkbox = new JCheckBox();
//       checkbox.setPreferredSize(CommonConstants.CHECKBOX_SIZE);
//       checkbox.addActionListener(this);
//
//
//       //delete button
//       deleteButton = new JButton("X");
//       deleteButton.setPreferredSize(CommonConstants.DELETE_BUTTON_SIZE);
//       deleteButton.addActionListener(this);
//
//       //add to this taskcomponent
//       add(checkbox);
//       add(taskField);
//       add(deleteButton);
//
//
//
//
//
//   }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//       if (checkbox.isSelected()){
//           //replace all html tags to empty string to grab the main text
//           String taskText  = taskField.getText().replaceAll("<[^>]*>", "");
//
//           //add strikethrough text
//           taskField.setText("<html><s>"+ taskText +"</s></html>");
//       } else if (!checkbox.isSelected()) {
//           String testText = taskField.getText().replaceAll("<[^>]*>", "");
//
//           taskField.setText(testText);
//       }
//
//       if (e.getActionCommand().equalsIgnoreCase("X")){
//           //delete this component from the parent panel
//           parentPanel.remove(this);
//           parentPanel.repaint();
//           parentPanel.revalidate();
//       }
//
//    }
//}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TaskComponent extends JPanel implements ActionListener {
    private JCheckBox checkbox;
    private JTextPane taskField;
    private JButton deleteButton;

    // Reference to the parent panel
    private JPanel parentPanel;

    public JTextPane getTaskField() {
        return taskField;
    }

    public TaskComponent(JPanel parentPanel) {
        this.parentPanel = parentPanel;

        // Set layout for TaskComponent
        setLayout(new BorderLayout(10, 0)); // 10px gap between components

        // Set preferred size for TaskComponent
        setPreferredSize(new Dimension(CommonConstants.TASKPANEL_SIZE.width - 20, 60)); // Slightly reduce width to account for scrollbar

        // Add border for better visual separation
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Padding around components

        // Checkbox
        checkbox = new JCheckBox();
        checkbox.setPreferredSize(new Dimension(20, 20)); // Fixed size for better alignment
        checkbox.addActionListener(this);

        // Task Field (editable area for entering text)
        taskField = new JTextPane();
        taskField.setContentType("text/html"); // Enable HTML content (for strikethrough effect)
        taskField.setText(""); // Initialize with empty text
        taskField.setMargin(new Insets(5, 5, 5, 5)); // Add padding inside the text field

        // Delete Button
        deleteButton = new JButton("X");
        deleteButton.setPreferredSize(new Dimension(50, 30)); // Fixed size for better alignment
        deleteButton.setFocusPainted(false); // Remove focus border for better appearance
        deleteButton.addActionListener(this);

        // Add components to TaskComponent
        add(checkbox, BorderLayout.WEST); // Checkbox on the left
        add(taskField, BorderLayout.CENTER); // Task field in the center
        add(deleteButton, BorderLayout.EAST); // Delete button on the right
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == checkbox) {
            // Toggle strikethrough for completed tasks
            String taskText = taskField.getText().replaceAll("<[^>]*>", ""); // Strip HTML tags
            if (checkbox.isSelected()) {
                // Add strikethrough HTML tags
                taskField.setText("<html><s>" + taskText + "</s></html>");
            } else {
                // Restore plain text
                taskField.setText(taskText);
            }
        } else if (e.getSource() == deleteButton) {
            // Remove this component from the parent panel
            parentPanel.remove(this);
            parentPanel.revalidate(); // Refresh the layout
            parentPanel.repaint(); // Repaint the UI
        }
    }
}
