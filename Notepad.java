
package demo;//this is the package of the class

//they are all the classes are import to this programe

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.UIManager;


//this is the Notepad class
public class Notepad implements ActionListener
{

    private final JFrame jf;
    private final JMenuBar menubar;
    private final JMenu file, edit, format, help;
    private final JMenuItem neww, open, save, saveas, exit, page_setup, print;//first menu
    private final JMenuItem cut, copy, paste, replace, date_time;//second menu
    private final JMenuItem open_font_menu, font_color, text_area_color;//third menu
    private final JMenuItem view_help,developer_about;//forth menu
    
    //word wrapped part coding
    private final JCheckBoxMenuItem word_wrap;
    private final JTextArea textarea;
    private final JScrollPane scollpane;
    private String title = "Untitled - Notepad";
    private File filee;
    private JFileChooser filechooser;

    //new frame replace
    private JFrame replaceframe;
    private JLabel jl1, jl2;
    private JTextField jt1, jt2;
    private JButton jb1,jb;

    //new frame font
    private JFrame font_frame;
    private JComboBox cb_font_sytle, cb_font_size, cb_font_family;
    private JButton ok;
    
    
    
    public Notepad()
    {
        
        try 
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        jf = new JFrame(title);//Untitled - Notepad
        jf.setSize(1200, 700);//size of the Notepad
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//this is the method of the JFrame class they automatically exit the frame or close the operation
 
        Image icon = Toolkit.getDefaultToolkit().getImage("D:\\JAVA Netbine\\Second Practice\\09NotePadEditor\\src\\main\\java\\Demo\\v-icon-18.jpg");
        jf.setIconImage(icon);
     
        menubar = new JMenuBar();

        file = new JMenu("File");
        

        neww = new JMenuItem("New");
        neww.setAccelerator(KeyStroke.getKeyStroke('N', InputEvent.CTRL_DOWN_MASK));//this is the shortcut created using this class
        neww.addActionListener(this);//they perform the action "this" is the current class referance variable
        file.add(neww);//and file are added the "new" 

        open = new JMenuItem("Open...");
        open.setAccelerator(KeyStroke.getKeyStroke('O', InputEvent.CTRL_DOWN_MASK));
        open.addActionListener(this);
        file.add(open);

        save = new JMenuItem("Save");
        save.setAccelerator(KeyStroke.getKeyStroke('S', InputEvent.CTRL_DOWN_MASK));
        save.addActionListener(this);
        file.add(save);

        saveas = new JMenuItem("Save As...");
//      saveas.setAccelerator(KeyStroke.getKeyStroke('S', InputEvent.SHIFT_DOWN_MASK));
        saveas.addActionListener(this);
        file.add(saveas);

        file.addSeparator();//this is the saparator they are saparet the two sub menu is the menu like a file is menu

        exit = new JMenuItem("Exit");
        exit.setAccelerator(KeyStroke.getKeyStroke('E', InputEvent.CTRL_DOWN_MASK));
        exit.addActionListener(this);
        file.add(exit);

        page_setup = new JMenuItem("Page Setup");
        page_setup.addActionListener(this);
        file.add(page_setup);

        file.addSeparator();//this is the saparator they are saparet the two sub menu is the menu like a file is menu

        print = new JMenuItem("Print...");
        print.setAccelerator(KeyStroke.getKeyStroke('P', InputEvent.SHIFT_DOWN_MASK));
        print.addActionListener(this);
        file.add(print);

        menubar.add(file);

        edit = new JMenu("Edit");
        
        cut = new JMenuItem("Cut");
        cut.addActionListener(this);
        edit.add(cut);
        
        edit.addSeparator();//saparate the two submenus

        copy = new JMenuItem("Copy");
        copy.setAccelerator(KeyStroke.getKeyStroke('C', InputEvent.CTRL_DOWN_MASK));
        copy.addActionListener(this);
        edit.add(copy);

        edit.addSeparator();

        paste = new JMenuItem("Paste");
        paste.setAccelerator(KeyStroke.getKeyStroke('V', InputEvent.CTRL_DOWN_MASK));
        paste.addActionListener(this);
        edit.add(paste);

        edit.addSeparator();

        replace = new JMenuItem("Replace");
        replace.addActionListener(this);
        edit.add(replace);

        edit.addSeparator();

        date_time = new JMenuItem("Date/Time");
        date_time.addActionListener(this);
        edit.add(date_time);
        
        
        menubar.add(edit);

        format = new JMenu("Format");
       
        word_wrap = new JCheckBoxMenuItem("Word Wrap");

        word_wrap.addActionListener(this);
        format.add(word_wrap);

        format.addSeparator();

        open_font_menu = new JMenuItem("Font...");
        open_font_menu.addActionListener(this);
        format.add(open_font_menu);

        format.addSeparator();

        font_color = new JMenuItem("Font Color");
        font_color.addActionListener(this);
        format.add(font_color);

        format.addSeparator();

        text_area_color = new JMenuItem("Background Color");
        text_area_color.addActionListener(this);
        format.add(text_area_color);
        
        menubar.add(format);
        
        help = new JMenu("Help");
        
        view_help = new JMenuItem("View Help");
        view_help.addActionListener(this);
        help.add(view_help);
       
        
        developer_about = new JMenuItem("NotePad About");
        developer_about.addActionListener(this);
        help.add(developer_about);
        
        menubar.add(help);
        
        jf.setJMenuBar(menubar);//add the meubar on the jframe

        textarea = new JTextArea();
        
        Font f = new Font("Courier New",0,20);
        textarea.setFont(f);
        textarea.setForeground(Color.WHITE);
        textarea.setBackground(Color.BLACK);
        textarea.setCaretColor(Color.WHITE);
        
        //textarea.
        
        //this is the scroll panel class
        scollpane = new JScrollPane(textarea);
        
        //this is the vertical scroll bar
        scollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //this is the horizontal scroll bar
        scollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        jf.add(scollpane);//add the sroll pan on the text area

        jf.setVisible(true);//frame is visible this class
    }

    //this is the interface method are override to the NotePad class and the interface are contain the abstract method and that resons was this method are 
    //implemented to the Notepad class or override
    @Override
    public void actionPerformed(ActionEvent e)
    {
        
        filechooser = new JFileChooser();//this class is the JFileChooser they are choose the file in the dialogbox own pc
        
        
        //------------------------------------------------------------------------------------------------------------------------------------------
        if (e.getSource() == neww)//can perform the action on the "new" menu
        {
            newNotepad();//this class are created the new notepadd 
        }
        if (e.getSource() == exit)//can perform the action on the "exit" menu
        {
            System.exit(0);
        }
        if (e.getSource() == save)//can perform the action on the "save" menu
        {
            save();//more concept
        }
        if (e.getSource() == saveas)//can perform the action on the "saveas" menu
        {
            saveAs();//more concept
        }
        if (e.getSource() == open)//can perform the action on the "open" menu
        {
            open();//more concept
        }
        if (e.getSource() == page_setup)//can perform the action on the "Page Setup" menu
        {
            pageSetup();
        }
        if (e.getSource() == print)//can perform the action on the "Print" menu
        {
            printPage();
        }
        //------------------------------------------------------------------------------------------------------------------------------------------
        
        
        //-----------------------------------------------------------------------------------------------------------------------------------------
        //predifened method for the cut copy and paste menubar
        if (e.getSource() == cut)//can perform the action on the "Cut" menu
        {
            textarea.cut();//this method are already present in the textarea class
        }
        if (e.getSource() == copy)//can perform the action on the "Copy" menu
        {
            textarea.copy();//this method are already present in the textarea class
        }
        if (e.getSource() == paste)//can perform the action on the "Paste" menu
        {
            textarea.paste();//this method are already present in the textarea class
        }
        if (e.getSource() == replace)//can perform the action on the "Replace" menu
        {
            replaceFrame();//create the replace frame
        }
        if (e.getSource() == jb1)////can perform the action on the "Replace Button" 
        {
            replace();//more concept
        }
        if (e.getSource() == date_time)//can perform the action on the "Date/Time" menu
        {
            setDateTime();
        }
        //-------------------------------------------------------------------------------------------------------------------------------------------------
      
        
        //------------------------------------------------------------------------------------------------------------------------------------------------
        
        if (e.getSource() == word_wrap)//can perform the action on the "Word Wrap" menu
        {
            boolean b = word_wrap.getState();
            textarea.setLineWrap(b);
        }
        if (e.getSource() == font_color)//can perform the action on the "Font" menu
        {
            setFontColor();
        }
        if (e.getSource() == text_area_color)//can perform the action on the "Font Color" menu
        {
            setTextareaColor();
        }
        if (e.getSource() == open_font_menu)//can perform the action on the "Font" menu
        {
            openFontFrame();
        }
        if (e.getSource() == ok)//can perform the action on the "OK" Button in the font
        {
            setFontToNotePad();
        }
        
        //------------------------------------------------------------------------------------------------------------------------------------------------       
        
        if(e.getSource()==view_help)
        {
            helpDemo();
        }
        
        if(e.getSource()==developer_about)
        {
            notepadAbout();
        }
        
    }

//-------------------------------------------------------------------------------------------------------------------------------------------------
    public void newNotepad()
    {
//      textarea.setText("");
        String text = textarea.getText();
        if (!text.equals(""))
        {


         int i = JOptionPane.showConfirmDialog(jf, "Do you want to save this file?");
            

//logic part in this part
//            System.out.println(" i -> "+i);
//              i -> 0  => that meanse save the file 
//              i -> 1  => that means not save the file
//              i -> 2  => that means cancel 
//              i -> -1 => that means exit on the dialogbox
            if (i == 0)
            {
                saveAs();//this method is called as you are save this file other wise they are wanished the your text in the Notpad
                //jf.getTitle they are get the title name the title name was Untitle notepad and they are equal to the title then excuted this if condition
                if (!jf.getTitle().equals(title)) 
                {
                    setTitle(title);
                    textarea.setText("");
                }
            } 
            else if (i == 1)
            {
                textarea.setText("");
                setTitle(title);
            }
            
        }
    }
//-------------------------------------------------------------------------------------------------------------------------------------------------
    
 
//-------------------------------------------------------------------------------------------------------------------------------------------------
    
    public void save()
    {
        if (jf.getTitle().equals(title))//untitle that time save the file other-vice 
        {
            saveAs();
        } 
        else 
        {
            try
            {
                String text = textarea.getText();
                byte[] b = text.getBytes();
                FileOutputStream fos = new FileOutputStream(filee);
                fos.write(b);
            } catch (Exception e) 
            {
                System.out.println(e);
            }
        }
    }
    
//-------------------------------------------------------------------------------------------------------------------------------------------------

  
//-------------------------------------------------------------------------------------------------------------------------------------------------

    public void saveAs()//first these method is save() method then change saveas() method
    {
 
        try 
        {
            int i = filechooser.showSaveDialog(jf);//0 return they are save the file
            if (i == 0)
            {
                filee = filechooser.getSelectedFile();//

                String text = textarea.getText();//get the text into the notepad textarea
                byte[] b = text.getBytes();//the text are converted to the byte[] array
                FileOutputStream fos = new FileOutputStream(filee);//they are create the file 
                fos.write(b);//pass the all text in the byte formate
                setTitle(filee.getName());//set title // the title will be set
                fos.close();//fileoutputstream will be close the resource
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
  
    void setTitle(String title)
    {
        jf.setTitle(title);        
    }

    void open() 
    {

        try
        {
            int i = filechooser.showOpenDialog(jf);//open the file dialog box own our system
            //open value -> 0
            //cancel value -> 1
            if (i == 0)
            {
                //new file open  that time selected file replace this new file open

                textarea.setText("");

                FileInputStream fis = new FileInputStream(filechooser.getSelectedFile());
                
                int ii;//read() method they are read the text one by one and return the integer form
                
                while ((ii = fis.read()) != -1)
                {
                    textarea.append(String.valueOf((char) ii));//type casted to this integer and transfer the notepade
                }
                fis.close();

                setTitle(filechooser.getSelectedFile().getName());
            }
        } 
        catch (Exception e) 
        {
            System.out.println(e);
        }
    }

    public void pageSetup()
    {
        PrinterJob pj = PrinterJob.getPrinterJob();//pre-difined class and i am access the using the internet
        PageFormat pf = pj.pageDialog(pj.defaultPage());//it is also the pre-defined class and also access the using the internet
        //page formate code
    }
//-------------------------------------------------------------------------------------------------------------------------------------------------

//-------------------------------------------------------------------------------------------------------------------------------------------------   
    //pre-defined class printPage
    public void printPage()
    {
        PrinterJob pj = PrinterJob.getPrinterJob();

        if (pj.printDialog())
        {
            try
            {
                pj.print();
            }
            catch (Exception e) 
            {
                System.out.println(e);
            }
        }
    }
//-------------------------------------------------------------------------------------------------------------------------------------------------

//-------------------------------------------------------------------------------------------------------------------------------------------------
    public void replaceFrame()
    {

        try
        {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } 
        catch (Exception e) 
        {
            System.out.println(e);
        }
        replaceframe = new JFrame("Replace");
        
        Image icon = Toolkit.getDefaultToolkit().getImage("D:\\JAVA Netbine\\Second Practice\\09NotePadEditor\\src\\main\\java\\Demo\\v-icon-18.jpg");
        replaceframe.setIconImage(icon);
        
        replaceframe.setSize(500, 300);
        replaceframe.setLayout(null);

        jl1 = new JLabel("Find What : ");
        jl1.setBounds(50, 50, 80, 40);
        replaceframe.add(jl1);

        jt1 = new JTextField();
        jt1.setBounds(170, 50, 200, 40);
        replaceframe.add(jt1);

        jl2 = new JLabel("Replace With : ");
        jl2.setBounds(50, 100, 100, 40);
        replaceframe.add(jl2);

        jt2 = new JTextField();
        jt2.setBounds(170, 100, 200, 40);
        replaceframe.add(jt2);

        jb1 = new JButton("Replace");
        jb1.setBounds(200, 150, 100, 40);
        jb1.addActionListener(this);
        replaceframe.add(jb1);

        replaceframe.setVisible(true);

    }
    public void replace()
    {
        String find_what = jt1.getText();//textfield one get the text
        String replace_with = jt2.getText();//textfield two get the text
        String text = textarea.getText();//get the textarea text 
        String new_text = text.replace(find_what, replace_with);//
        textarea.setText(new_text);//set the new text on textare 
        replaceframe.setVisible(false);//then frame will be closed
    }
    
//-------------------------------------------------------------------------------------------------------------------------------------------------

//-------------------------------------------------------------------------------------------------------------------------------------------------

    public void setDateTime()
    {
        LocalDateTime ldt = LocalDateTime.now();//pre-difined class they are set the date and time
        String datetime = ldt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);//local date in your system
        textarea.append(datetime);//the date are added to the textarea
    }

//-------------------------------------------------------------------------------------------------------------------------------------------------

//-------------------------------------------------------------------------------------------------------------------------------------------------

    public void setFontColor() 
    {
        Color c = JColorChooser.showDialog(jf, "Select Font Color", Color.black);//it is also the pre-difened class 
        //provided the color dailog box 
        textarea.setForeground(c);
    }
    
//-------------------------------------------------------------------------------------------------------------------------------------------------

//-------------------------------------------------------------------------------------------------------------------------------------------------

    public void setTextareaColor()
    {
        Color c = JColorChooser.showDialog(jf, "Select Textarea Color", Color.white);
        textarea.setBackground(c);
    }
//-------------------------------------------------------------------------------------------------------------------------------------------------

//-------------------------------------------------------------------------------------------------------------------------------------------------

    public void openFontFrame()
    {
        font_frame = new JFrame("Fonts...");
        
        Image icon = Toolkit.getDefaultToolkit().getImage("D:\\JAVA Netbine\\Second Practice\\09NotePadEditor\\src\\main\\java\\Demo\\v-icon-18.jpg");
        font_frame.setIconImage(icon);
        
        font_frame.setSize(600, 250);
        font_frame.setLayout(null);

        //ont user defaultcloseoperation
        //your system present font are provided or get this class or method they are c -> windows -> font-> all the font are get in the system
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] font_families = ge.getAvailableFontFamilyNames();
        cb_font_family = new JComboBox(font_families);
        cb_font_family.setBounds(50, 50, 200, 40);
        font_frame.add(cb_font_family);

        String[] font_sytle = {"Plain", "Bold", "Italic"};
        cb_font_sytle = new JComboBox(font_sytle);
        cb_font_sytle.setBounds(300, 50, 100, 40);
        font_frame.add(cb_font_sytle);

        Integer[] font_size = {20, 30, 40, 50, 60, 70, 80};
        cb_font_size = new JComboBox(font_size);
        cb_font_size.setBounds(450, 50, 80, 40);
        font_frame.add(cb_font_size);

        ok = new JButton("OK");
        ok.setBounds(250, 150, 80, 50);
        font_frame.add(ok);
        ok.addActionListener(this);

        font_frame.setVisible(true);
    }
//-------------------------------------------------------------------------------------------------------------------------------------------------

//-------------------------------------------------------------------------------------------------------------------------------------------------

    public void setFontToNotePad() 
    {
        String font_family = (String) cb_font_family.getSelectedItem();
        String font_style = (String) cb_font_sytle.getSelectedItem();
        Integer font_size = (Integer) cb_font_size.getSelectedItem();

        int font_stylee = 0;
        if (font_style.equals("Plain"))
        {
            font_stylee = Font.PLAIN;
        }
        else if (font_style.equals("Bold"))
        {
            font_stylee = Font.BOLD;
        } else 
        {
            font_stylee = Font.ITALIC;
        }

        Font f = new Font(font_family, font_stylee, font_size);
        textarea.setFont(f);
        font_frame.setVisible(false);
    }

    public void helpDemo()
    {
      JOptionPane.showConfirmDialog(jf,"Developer Name : Vishal Nimbarte\nCompany Name : Smart Programing","View Help",JOptionPane.INFORMATION_MESSAGE);
    }

    
    public void notepadAbout()
    {
      JOptionPane.showConfirmDialog(jf,"Version 1.4.0 \nJ2SE(CORE JAVA)\nJDK 8th Version","About",JOptionPane.INFORMATION_MESSAGE);
    }
    

}
