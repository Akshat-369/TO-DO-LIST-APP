import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ToDoListGui extends JFrame implements ActionListener {
    //taskPannel will act as the container for the taskComponentPannel
    //taskComponentPannel will store all the taskComponents
    private JPanel taskPannel, taskComponentPannel;

    public ToDoListGui(){
        super("To Do List Application");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(CommonConstants.GUI_SIZE);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        addGuiComponents();
    }

   private void addGuiComponents(){
        //banner text
       JLabel bannerlabel = new JLabel("To Do List");
     //  bannerlabel.setFont(createFont("src/LEMONMILK-Light.otf", 36f));

       bannerlabel.setBounds(
               (CommonConstants.GUI_SIZE.width - bannerlabel.getPreferredSize().width)/2,
               15,
               CommonConstants.BANNER_SIZE.width,
               CommonConstants.BANNER_SIZE.height

       );

       //taskpannel
       taskPannel = new JPanel();

       //taskcomponentpannel
       taskComponentPannel = new JPanel();
       taskComponentPannel.setLayout(new BoxLayout(taskComponentPannel, BoxLayout.Y_AXIS));
       taskPannel.add(taskComponentPannel);

       //add scrolling to the task panel
       JScrollPane scrollPane = new JScrollPane(taskPannel);
       scrollPane.setBounds(8,70, CommonConstants.TASKPANEL_SIZE.width, CommonConstants.TASKPANEL_SIZE.height);
       scrollPane.setMaximumSize(CommonConstants.TASKPANEL_SIZE);
       scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
       scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

       //





       //add task button
       JButton addTaskButton = new JButton("Add Task");
       addTaskButton.setBounds(-5 , CommonConstants.GUI_SIZE.height - 88,
               CommonConstants.ADDTASK_BUTTON_SIZE.width, CommonConstants.ADDTASK_BUTTON_SIZE.height);
       addTaskButton.addActionListener(this);

       //add to frame
       this.getContentPane().add(bannerlabel);
       this.getContentPane().add(scrollPane);
       this.getContentPane().add(addTaskButton);
    }

    private Font createFont(String resource, float size){
        //get the font file path
        String filepath = getClass().getClassLoader().getResource(resource).getPath();

        //check to see if the path contains a folder with spaces in them
        if (filepath.contains("%20")){
            filepath = getClass().getClassLoader().getResource(resource).getPath().replaceAll("%20", " ");

        }

        //create font
        try {
            File customFontFile = new File(filepath);
            Font customFont  = Font.createFont(Font.TRUETYPE_FONT, customFontFile).deriveFont(size);
            return customFont;
        }catch (Exception e){
            System.out.println("Error : "+ e);
        }
        return null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(command.equalsIgnoreCase("Add Task")){
            //create a task componenet
            TaskComponent taskComponent = new TaskComponent(taskComponentPannel);
            taskComponentPannel.add(taskComponent);

            //make the task field request focus after creation
            taskComponent.getTaskField().requestFocus();
            repaint();
            revalidate();
        }
    }
}
