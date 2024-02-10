import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Main extends JFrame {
    private JTextArea textArea; //Создание текствой области в которой будут отображаться все компоненты
    private List<String> texts; //Создание базы на которой будут сохраняться текста

    public Main() {
        texts = new ArrayList<>(); //Обьявление
        //Задание базовых вещей для интерфейса
        setTitle("Приложение");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout()); //Создание конструктора который перемещает обьекты внутри основной панели

        textArea = new JTextArea(); // Обьявление
        //Задание всех атрибутов текстовой области
        textArea.setEditable(false); //Разрешение пользователю изменять
        textArea.setLineWrap(true); //Перенос слов
        textArea.setWrapStyleWord(true); //Перенос слов целиком
        Font font = new Font("Arial", Font.PLAIN, 20); // задание атрибутов Шрифта
        textArea.setFont(font); //Задание шрифта текстовой области

        //Надо проработать
        JScrollPane scrollPane = new JScrollPane(textArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(); // создание панели для кнопок
        buttonPanel.setLayout(new FlowLayout()); //определение их в 1 строчку

        JButton addButton = new JButton("Напиши что ты хочешь"); //Создание кнопки
        addButton.addActionListener(new ActionListener() { // реализация кнопки и вывод дополнительнового окна
            @Override //переопределение метода
            public void actionPerformed(ActionEvent e) { // реализация нового окна, куда вводится текст
                String text = JOptionPane.showInputDialog("Вот сюда текст надо"); //переменная что вберает в себя данные с нового окна
                if (text != null && !text.isEmpty()) {
                    addText(text);
                    updateTextArea();
                }
            }
        });
        buttonPanel.add(addButton);//добавление кнопки на панель кнопок

        JButton displayButton =  new JButton("Все текста"); // новая кнопка
        displayButton.addActionListener(new ActionListener() { // реализация
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTextArea();
            }
        });
        buttonPanel.add(displayButton); // добавление кнопки на панель кнопок

        mainPanel.add(buttonPanel, BorderLayout.SOUTH); // добавление панели кнопок к основной панели вниз
        add(mainPanel); //добавление самой основой панели
    }

    private void addText(String text) { // реализация метода для добавление в текстовую область
        texts.add(text); // добавление
        JOptionPane.showMessageDialog(this, "Ваш текст добавлен"); // вывод сообщения
    }

    private void updateTextArea() {
        if (texts.isEmpty()) {
            textArea.setText("У вас нет ни 1 текста");
        } else {
            StringBuilder builder = new StringBuilder();
            for (int i = 0;i < texts.size();i++) {
                builder.append((i + 1)).append(". ").append(texts.get(i)).append("\n");
            }
            textArea.setText(builder.toString());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Main main = new Main();
                main.setVisible(true);
            }
        });
    }
}