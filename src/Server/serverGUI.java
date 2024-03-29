package Server;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.fill;


public class serverGUI extends JFrame {

    private JComboBox selectCrossSection;
    private JButton Phase1Button;
    private JButton Phase2Button;
    private JButton Phase3Button;
    private JButton Phase1_ToAllButton;
    private JButton Phase2_ToAllButton;;
    private JButton Phase3_ToAllButton;
    private JButton getControlButton;
    private JButton AutoMode;
    private JLabel CrossSectionLabel;
    private List <Dialog78> dialogsClients;
    private Boolean [] isTokenControl ;
    JFrame frame ;
    Boolean isAlive;


    public serverGUI(){
        this.dialogsClients = new ArrayList<Dialog78>();
        this.isTokenControl = new Boolean[3];
        fill(isTokenControl, Boolean.FALSE);
        selectCrossSection = new JComboBox();
        Phase1Button = new JButton("Move To Phase 1");
        Phase2Button = new JButton("Move To Phase 2");
        Phase3Button = new JButton("Move To Phase 3");
        Phase1_ToAllButton = new JButton("All To Phase 1");
        Phase2_ToAllButton = new JButton("All To Phase 2");
        Phase3_ToAllButton = new JButton("All To Phase 3");
        getControlButton = new JButton("Take Control");
        AutoMode = new JButton("Leave Control");
        AutoMode.setEnabled(false);
        CrossSectionLabel = new JLabel("Cross Section:");
        isAlive = true;
        frame =  new JFrame("Traffic-Light ControllerTrafficLight ");
        frame.setVisible(true);
        init();

        frame.setResizable(false);
    }


    public void init(){
        frame.setLayout(null);
        frame.setSize(400,400);


        selectCrossSection.setBounds(140,30,200,30);
        CrossSectionLabel.setBounds(30,30,140,30);
        Phase1_ToAllButton.setBounds(200,70,140,30);
        Phase2_ToAllButton.setBounds(200,120,140,30);
        Phase3_ToAllButton.setBounds(200,170,140,30);
        Phase1Button.setBounds(30,70,140,30);
        Phase2Button.setBounds(30,120,140,30);
        Phase3Button.setBounds(30,170,140,30);
        getControlButton.setBounds(110,230, 120, 30);
        AutoMode.setBounds(110,270, 120, 30);


        frame.add(CrossSectionLabel);
        frame.add(selectCrossSection);
        frame.add(Phase1Button);
        frame.add(Phase2Button);
        frame.add(Phase3Button);
        frame.add(Phase1_ToAllButton);
        frame.add(Phase2_ToAllButton);
        frame.add(Phase3_ToAllButton);
        frame.add(getControlButton);
        frame.add(AutoMode);



        selectCrossSection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("comboBox open");
                buttonsEnable();
            }
        });

        Phase1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("move  to phase 1 button pressed");
                moveToPhase1ButtonPresed();
            }
        });

        Phase2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("move to phase 2 button pressed");
                moveToPhase2ButtonPresed();
            }
        });

        Phase3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("move to phase 3 button pressed");
                moveToPhase3ButtonPresed();
            }
        });

        Phase1_ToAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("move all to phase 1 button pressed");
                allToPhase1Buttonpresed();
            }
        });

        Phase2_ToAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("move all to phase 2 button pressed");
                allToPhase2Buttonpresed();
            }
        });

        Phase3_ToAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("move all to phase 3 button pressed");
                allToPhase3Buttonpresed();
            }
        });

        getControlButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("get control button pressed");
                tokenControlButtonPresed();
            }
        });

        AutoMode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Leave control button pressed");
                leaveControlButtonPresed();
            }
        });



    }



    private void moveToPhase3ButtonPresed() {
        System.out.println(this.selectCrossSection.getSelectedIndex());
        if(this.selectCrossSection.getSelectedIndex() != -1 && isTokenControl[this.selectCrossSection.getSelectedIndex()]) {
            dialogsClients.get(this.selectCrossSection.getSelectedIndex()).bufferSocketOut.println("evPhase3");
        }
    }

    private void moveToPhase2ButtonPresed() {
        if(this.selectCrossSection.getSelectedIndex() != -1 && isTokenControl[this.selectCrossSection.getSelectedIndex()]) {
            dialogsClients.get(this.selectCrossSection.getSelectedIndex()).bufferSocketOut.println("evPhase2");
        }
    }

    private void moveToPhase1ButtonPresed() {
        if(this.selectCrossSection.getSelectedIndex() != -1 && isTokenControl[this.selectCrossSection.getSelectedIndex()]) {
            dialogsClients.get(this.selectCrossSection.getSelectedIndex()).bufferSocketOut.println("evPhase1");
        }
    }

    private void allToPhase1Buttonpresed() {
        for(int i=0; i<dialogsClients.size(); i++){
            if(i<=2 && isTokenControl[i]){
                dialogsClients.get(i).bufferSocketOut.println("evPhase1");
            }
        }
    }

    private void allToPhase2Buttonpresed() {
        for(int i=0; i<dialogsClients.size(); i++){
            if(i<=2 && isTokenControl[i]){
                dialogsClients.get(i).bufferSocketOut.println("evPhase2");
            }
        }

    }

    private void allToPhase3Buttonpresed() {
        for(int i=0; i<dialogsClients.size(); i++){
            if(i<=2 && isTokenControl[i]){
                dialogsClients.get(i).bufferSocketOut.println("evPhase3");
            }
        }
    }

    private void tokenControlButtonPresed() {
        if(this.selectCrossSection.getSelectedIndex() != -1) {
            isTokenControl[this.selectCrossSection.getSelectedIndex()] = true;
            getControlButton.setEnabled(false);
            AutoMode.setEnabled(true);
            dialogsClients.get(this.selectCrossSection.getSelectedIndex()).bufferSocketOut.println("evGetControl");
        }

    }

    private void leaveControlButtonPresed() {
        if(this.selectCrossSection.getSelectedIndex() != -1) {
            isTokenControl[this.selectCrossSection.getSelectedIndex()] = false;
            getControlButton.setEnabled(true);
            AutoMode.setEnabled(false);
            dialogsClients.get(this.selectCrossSection.getSelectedIndex()).bufferSocketOut.println("evLeaveControl");
        }
    }



    public void addDialog(Dialog78 newDialog ,Integer i){

        dialogsClients.add(newDialog);
        selectCrossSection.addItem("Crossroad "+i);
        ((JLabel)selectCrossSection.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

    }

    private void buttonsEnable() {
        if(isTokenControl[this.selectCrossSection.getSelectedIndex()]){
            getControlButton.setEnabled(false);
            AutoMode.setEnabled(true);
        }
        else{
            getControlButton.setEnabled(true);
            AutoMode.setEnabled(false);
        }

    }


}

