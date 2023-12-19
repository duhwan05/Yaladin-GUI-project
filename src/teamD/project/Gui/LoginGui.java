package teamD.project.Gui;
import javax.swing.*;

import teamD.project.Dao.Test.CustomerDaoTest;
import teamD.project.Dto.CustomerDto;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;

public class LoginGui {
	
	CustomerDto login = null;
	private int cust_no;
    private JFrame frame;
    private JTextField idField;
    //private JPasswordField passwordField;
  //  private JFrame cust = new JFrame();
  //  public JFrame getlogin() {
  //  	return cust;
  //  }
    private int index;
    private static final String[] IMAGES= { 
    	"D:\\gookbi-workspace\\teamD.project\\image\\얄라딘.jpg",
    	"D:\\gookbi-workspace\\teamD.project\\image\\lamp.png"
    };
   
    public static void main(String[] args) {
    	
    	
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginGui window = new LoginGui();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public LoginGui() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(400, 100, 1100, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        		
        
        
        
        
//id 텍스트 필드
        idField = new JTextField();
        idField.setFont(new Font("굴림", Font.PLAIN, 20));
        idField.setForeground(new Color(255, 255, 255));
        idField.setBackground(new Color(128, 128, 255));
        idField.setHorizontalAlignment(SwingConstants.CENTER);
        idField.setBounds(338, 623, 435, 45);
        frame.getContentPane().add(idField);
        
        //login
        JButton loginButton = new JButton("LOGIN");
        loginButton.setBackground(new Color(128, 128, 255));
        loginButton.setForeground(new Color(255, 255, 255));
        loginButton.setBounds(0, 616, 183, 63);
        loginButton.addActionListener(new ActionListener() {
        	
        	public void actionPerformed(ActionEvent e) {
        		CustomerDaoTest customerdao = new CustomerDaoTest();
        		
        		String id = idField.getText();
        		try {
        			login = customerdao.select(id);
        			if(login !=null) {
        				JOptionPane.showMessageDialog(frame, "로그인 성공");
        					new CustomerBookList(login);
        				frame.dispose();
        				
        			}
        			//관리자 권환으로 실행
        			else if(id.equals("admin")) {
        				JOptionPane.showMessageDialog(frame, "관리자 모드");
        					new AdminBookListGui();
        			}
        			else {
        				JOptionPane.showMessageDialog(frame, "아이디가 없습니다.", "로그인 실패", JOptionPane.ERROR_MESSAGE);
        			}
        		} catch (SQLException e1) {
        			e1.printStackTrace();
        		}
        		
        		
        		/*String password = new String(passwordField.getPassword());

                        if (id.equals("admin") && password.equals("password")) {
                            
                            // 로그인 후 화면
                        } else {
                            JOptionPane.showMessageDialog(frame, "아이디가 없습니다.", "로그인 실패", JOptionPane.ERROR_MESSAGE);
                        }*/
        	}

	
        });
        //화면 이미지
        JLabel lblNewLabel_1 = new JLabel("  Aladin Book Store");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setIcon(new ImageIcon("D:\\iclass0419\\image\\제목 없음.png"));               
        lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
        lblNewLabel_1.setForeground(new Color(200, 200, 0));
        lblNewLabel_1.setFont(new Font("Brush Script MT", Font.PLAIN, 70));
        lblNewLabel_1.setBounds(108, 26, 729, 90);
        frame.getContentPane().add(lblNewLabel_1);
        JButton signUpButton = new JButton("회원가입");
        signUpButton.setBackground(new Color(128, 128, 255));
        signUpButton.setForeground(new Color(255, 255, 255));
        signUpButton.setBounds(0, 689, 183, 62);
        frame.getContentPane().add(signUpButton);
        
        //화면 이미지
        JLabel lblImage = new JLabel();
        lblImage.setHorizontalAlignment(SwingConstants.CENTER);
        lblImage.setBounds(-16, 0, 1100, 761
        		);
        lblImage.setIcon(new ImageIcon(IMAGES[index]));
        frame.getContentPane().add(lblImage);
        frame.getContentPane().add(loginButton);
        
        //회원가입 버튼
        signUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	CustomerDaoTest customerdao = new CustomerDaoTest();
            	CustomerDto customer = new CustomerDto();

            	
                JDialog signUpDialog = new JDialog(frame, "회원가입");
                signUpDialog.setBounds(700,300,700,500);
                signUpDialog.getContentPane().setLayout(new BorderLayout());
                signUpDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

                JPanel inputPanel = new JPanel();
                inputPanel.setLayout(new GridLayout(5,5));
                inputPanel.setBackground(new Color(232,232,232));

                JLabel idLabel = new JLabel("아이디를 입력하세요 :");
                idLabel.setFont(new Font("함초롱바탕", Font.PLAIN, 20));
                JTextField idInput = new JTextField();
 
                //JLabel passwordLabel = new JLabel("비밀번호를 입력하세요 :");
               // JPasswordField passwordInput = new JPasswordField();
                JLabel nameLabel = new JLabel("이름을 입력하세요 :");
                nameLabel.setFont(new Font("함초롱바탕", Font.PLAIN, 20));
                JTextField nameInput = new JTextField();
                
                JLabel ageLabel = new JLabel("나이를 입력하세요 :");
                ageLabel.setFont(new Font("함초롱바탕", Font.PLAIN, 20));
                JTextField ageInput = new JTextField();
                
                JLabel addressLabel = new JLabel("주소를 입력하세요 :");
                addressLabel.setFont(new Font("함초롱바탕", Font.PLAIN, 20));
                JTextField addressInput3 = new JTextField();
                
                JLabel telLabel = new JLabel("전화번호를 입력하세요 :");
                telLabel.setFont(new Font("함초롱바탕", Font.PLAIN, 20));
                JTextField telInput = new JTextField();

                

                inputPanel.add(idLabel);
                inputPanel.add(idInput);
               // inputPanel.add(passwordLabel);
                //inputPanel.add(passwordInput);
                inputPanel.add(nameLabel);
                inputPanel.add(nameInput);
                
                inputPanel.add(ageLabel);
                inputPanel.add(ageInput);
                
                inputPanel.add(addressLabel);
                inputPanel.add(addressInput3);
                
                inputPanel.add(telLabel);
                inputPanel.add(telInput);

              
                
                JButton registerButton = new JButton("가입하기");
                registerButton.setBounds(700, 300, 400, 100);
                registerButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String id = idInput.getText();
                        customer.setCust_id(id);
                        String name = nameInput.getText();
                        customer.setCust_name(name);
                        int age = Integer.parseInt(ageInput.getText());
                        customer.setAge(age);
                        
                        try {
                    		customerdao.insert(customer);
                    		
                    } catch (SQLException e1) {
                    	e1.printStackTrace();
                    }
                       // String password = new String(passwordInput.getPassword());
                        System.out.println("회원가입 정보 : ");
                        System.out.println("아이디 : " + name);
                        System.out.println("이름 : " + id);
                      //  System.out.println("비밀번호 : " + password);
                        signUpDialog.dispose();
                        
                    }
                });

                signUpDialog.getContentPane().add(inputPanel, BorderLayout.CENTER);
             signUpDialog.getContentPane().add(registerButton, BorderLayout.SOUTH);
                signUpDialog.setVisible(true);
            }
        });
      frame.setVisible(true);
    }


  
    
 /*   @SuppressWarnings("serial")
	class BackgroundPanel extends JPanel {
        private BufferedImage backgroundImage;

        public BackgroundPanel() {
            try {
                backgroundImage = ImageIO.read(new File("./image/1.jpg"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
        }
    }*/
}