package xyz.itwill.io;

import java.awt.BorderLayout;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

//텍스트 파일 편집기 프로그램 - 메모장
public class NotepadApp extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	//JMenuItem 컴퍼넌트를 선택한 경우 ActionEvent 발생
	private JMenuItem init, open, save, exit;
	private JTextArea area;
	
	//FileDialog : 파일을 선택하기 위한 다이얼로그를 생성하기 위한 컴퍼넌트
	private FileDialog openDialog, saveDialog;
		
	//현재 작업중인 문서파일의 경로를 저장하기 위한 필드
	private String filepath;
	
	//JTextArea 컴퍼넌트의 문자열 변경 상태를 저장하기 위한 필드 - 문서파일 변경 유무 확인
	// => false : 미변경, true : 변경
	private boolean status;
	
	public NotepadApp(String title) {
		super(title);
		
		JMenuBar bar=new JMenuBar();
		JMenu menu=new JMenu("파일(F)");
		menu.setMnemonic('F');
		init=new JMenuItem("새로 만들기(N)", 'N');
		open=new JMenuItem("열기(O)", 'O');
		save=new JMenuItem("저장(S)", 'S');
		exit=new JMenuItem("끝내기(X)", 'X');
		init.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
		open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
		menu.add(init);
		menu.add(open);
		menu.add(save);
		menu.addSeparator();
		menu.add(exit);
		bar.add(menu);
		setJMenuBar(bar);
		
		area=new JTextArea();
		area.setFont(new Font("굴림체", Font.PLAIN, 20));
		JScrollPane pane=new JScrollPane(area);
		getContentPane().add(pane, BorderLayout.CENTER);
		
		init.addActionListener(this);
		open.addActionListener(this);
		save.addActionListener(this);
		exit.addActionListener(this);
		
		//JTextArea 컴퍼넌트에서 키보드 관련 이벤트가 발생된 경우 실행될 이벤트 처리 객체 등록
		// => KeyAdapter 클래스를 상속받은 익명의 자식클래스의 객체를 이용하여 이벤트 처리
		area.addKeyListener(new KeyAdapter() {
			//키보드를 눌렀다 띄었을 때 자동 호출되는 이벤트 처리 메소드
			@Override
			public void keyTyped(KeyEvent e) {
				//KeyEvent.getKeyChar() : 이벤트가 발생된 키보드의 문자값을 반환하는 메소드
				// => 이벤트가 발생된 키보드의 문자값이 NULL 문자가 아닌 경우 
				if(!status && e.getKeyChar()!='\0') {
					status=true;//상태 변경
					setTitle("*"+getTitle());//프레임의 제목 변경
				}
			}			
		});
		
		//읽기 파일 선택하기 위한 파일 다이얼로그 생성
		openDialog=new FileDialog(this, "열기", FileDialog.LOAD);
		//쓰기 파일 선택하기 위한 파일 다이얼로그 생성
		saveDialog=new FileDialog(this, "저장", FileDialog.SAVE);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(450, 150, 1000, 600);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new NotepadApp("제목 없음 - Java 메모장");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object eventSource=e.getSource();
		
		if(eventSource==init) {
			status=false;//상태 초기화
			filepath=null;//문서파일의 경로 초기화
			area.setText("");//JTextArea 컴퍼넌트 초기화
			setTitle("제목 없음 - Java 메모장");//프레임 제목 변경
		} else if(eventSource==open) {
			//열기 관련 파일 다이얼로그를 화면에 출력
			// => 파일을 선택하거나 선택을 취소한 경우 파일 다이얼로그는 자동으로 숨김 처리
			openDialog.setVisible(true);
			
			//FileDialog.getFile() : 선택된 파일을 반환하는 메소드
			//파일 다이얼로그에서 파일 선택을 취소한 경우 이벤트 처리 메소드 종료
			if(openDialog.getFile()==null) return;
			
			//파일 다이얼로그에서 선택된 파일에 대한 시스템 경로를 반환받아 저장
			//FileDialog.getDirectory() : 선택된 파일이 저장된 디렉토리 경로를 반환하는 메소드
			filepath=openDialog.getDirectory()+openDialog.getFile();
			
			try {
				//파일 입력스트림을 생성하여 저장
				BufferedReader in=new BufferedReader(new FileReader(filepath));
				
				//프레임의 제목 변경
				setTitle(openDialog.getFile()+" - Java 메모장");
				
				area.setText("");//JTextArea 컴퍼넌트 초기화
				
				//파일에 저장된 값을 한줄씩 읽어 JTextArea 컴퍼넌트에 출력 처리
				while(true) {
					//파일 입력스트림에서 엔터(Enter)전까지의 모든 문자데이타(문자열)를 읽어 저장
					String text=in.readLine();
					if(text==null) break;//읽은 문자열이 없는 경우 반복문 종료
					//JTextArea 컴퍼넌트에 문자열을 추가하여 출력
					area.append(text+"\n");
				}
	
				in.close();
			} catch (FileNotFoundException exception) {
				JOptionPane.showMessageDialog(this, "선택한 파일을 찾을 수 없습니다.");
			} catch (IOException exception) {
				JOptionPane.showMessageDialog(this, "프로그램에 문제가 발생 하였습니다.");
			}
		} else if(eventSource==save) {
			//현재 작업중인 문서 파일이 없는 경우
			if(filepath==null) {
				//저장 관련 파일 다이얼로그를 화면에 출력
				saveDialog.setVisible(true);
				
				//파일 다이얼로그에서 파일 선택을 취소한 경우 이벤트 처리 메소드 종료
				if(saveDialog.getFile()==null) return;
				
				//파일 다이얼로그에서 선택된 파일에 대한 시스템 경로를 반환받아 저장
				filepath=saveDialog.getDirectory()+saveDialog.getFile();
	
				//프레임의 제목 변경
				setTitle(saveDialog.getFile()+" - Java 메모장");
			}
			
			try {
				//파일 출력스트림을 생성하여 저장
				BufferedWriter out=new BufferedWriter(new FileWriter(filepath));
				
				//JTextArea 컴퍼넌트의 모든 문자열을 반환받아 저장
				String text=area.getText();
				
				//BufferedWriter.write(String s) : 출력스트림에 문자열을 전달하는 메소드
				out.write(text);
				
				out.close();
			} catch (IOException exception) {
				JOptionPane.showMessageDialog(this, "프로그램에 문제가 발생 하였습니다.");
			}
		} else if(eventSource==exit) {
			System.exit(0);
		}
	}
}







