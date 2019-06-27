/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controller;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Properties;
import javax.mail.Session;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
/**
 *
 * @author Malinda
 */
public class MailController extends javax.swing.JDialog{
    
    Container cp;
    GridLayout gl;
    JPanel pl;
 
    JLabel mail_label;
    JTextField mail_text;
    
    JLabel sub_label;
    JTextField sub_text;
    
    JLabel msg_label;
    JTextArea msg_text;
    
    JButton send_button;
    public MailController(java.awt.Dialog parent, boolean modal){
     super(parent, modal);
     setLocationRelativeTo(this);
        
        ButtonHandler handler_button=new ButtonHandler();
        
        cp=getContentPane();
        gl=new GridLayout(0,2);
        cp.setLayout(gl);
        pl=new JPanel();
        
        mail_label= new JLabel("Send to : ");
        mail_label.setPreferredSize(new Dimension(100,30));
        
        mail_text= new JTextField("...");
        mail_text.setPreferredSize(new Dimension(150,30));
        
        sub_label= new JLabel("Subject : ");
        sub_label.setPreferredSize(new Dimension(100,30));
        
        sub_text= new JTextField("...");
        sub_text.setPreferredSize(new Dimension(150,30));
        
        msg_label= new JLabel("Message : ");
        msg_label.setPreferredSize(new Dimension(100,30));
        
        JScrollPane scroll = new JScrollPane (msg_text);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
          
        msg_text= new JTextArea("...",10,15);
 
        scroll.setViewportView(msg_text);

        send_button= new JButton("Send");
        send_button.setPreferredSize(new Dimension(150,30));
        send_button.addActionListener(handler_button);
        
        pl.add(mail_label);
        pl.add(mail_text);
        pl.add(sub_label);
        pl.add(sub_text);
        pl.add(msg_label);
        pl.add(scroll);
        pl.add(send_button);
        
        cp.add(pl);
    }

     
    class ButtonHandler implements ActionListener  
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource()==send_button)
            {
                String to = mail_text.getText();
                String subject = sub_text.getText();
                String message =  msg_text.getText();
                
                String user = "supuntenna@gmail.com";
                String pass = "supunrock123456";

               send(to,subject, message, user, pass);
            }           
        }
    }  
     
        public static void send(String to, String sub,String msg, final String user, final String pass) 
    {
        Properties props = new Properties();

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");	
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        
        Session session = Session.getDefaultInstance(props,new Authenticator() 
        {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() 
            {
                return new PasswordAuthentication(user, pass);
            }
        });

        try 
        {
            Message message = new MimeMessage(session);
            
            message.setFrom(new InternetAddress(user));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(sub);
            message.setText(msg);

            Transport.send(message);
            
            JOptionPane.showMessageDialog(null,"Email Sent!");
            
        } catch (MessagingException e) 
        {
            JOptionPane.showMessageDialog(null,"Please Try Again!");
            
            throw new RuntimeException(e);
        }
        
    }
             public static void main(String[] args) {
        // TODO code application logic here
        MailController app = new MailController(new javax.swing.JDialog(), true);
        app.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
        app.setSize(800,400);
        app.setVisible(true); 
    }
}
     



