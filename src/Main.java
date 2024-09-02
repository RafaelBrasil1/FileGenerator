import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;


public class Main {
    static String titulo;
    static File selectedDirectory;
    static String valor = "";
    static int quantosArq;


    public static void main(String[] args) {





        // Cria o frame
        JFrame frame = new JFrame("Files Generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new java.awt.FlowLayout());

        // Cria o seletor de pasta
        JFrame frameSeletor = new JFrame("Selecione");
        frameSeletor.setSize(300, 200);

        // Cria o JFileChooser e configura para escolher diretórios
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        // Cria o frame 3
        JFrame frame3 = new JFrame("Digite um numero");
        frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame3.setSize(500, 200);
        frame3.setLayout(new java.awt.FlowLayout());
        JTextField textField2 = new JTextField(30);
        frame3.add(new JScrollPane(textField2));
        textField2.addKeyListener(new KeyAdapter() {
                                      @Override
                                      public void keyPressed(KeyEvent e) {
                                          if (e.getKeyCode() == 10) {
                                              frame3.dispose();
                                              quantosArq = Integer.parseInt(textField2.getText());
                                              System.out.println(quantosArq);


                                              int i = 1;
                                              frame.setTitle("Criando...");
                                              while (i < quantosArq + 1) {
                                                  for(int f = 0; f <= 10000; f++){
                                                      Random random = new Random();
                                                      int randomInt = random.nextInt(0,127);
                                                      char character = (char) randomInt;
                                                      valor += character;

                                                  }


                                                  File file = new File(selectedDirectory.getAbsolutePath() + File.separator + titulo + String.valueOf(i) + ".txt");
                                                  try (FileWriter fileWriter = new FileWriter(file)) {
                                                      // Escreve conteúdo no arquivo
                                                      fileWriter.write(valor);
                                                      System.out.println("Arquivo criado e texto escrito com sucesso!");
                                                  } catch (IOException a) {
                                                      a.printStackTrace();
                                                      System.out.println("Erro ao criar ou escrever no arquivo.");
                                                  }
                                                  i += 1;
                                                  valor = "";

                                              }
                                          }
                                      }
                                  });


        // Cria o frame 2
        JFrame frame2 = new JFrame("Digite um nome");
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setSize(500, 200);
        frame2.setLayout(new java.awt.FlowLayout());
        JTextField textField = new JTextField(30);
        frame2.add(new JScrollPane(textField));

        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    frame2.dispose();
                    titulo = textField.getText();
                    System.out.println(titulo);
                    JOptionPane.showMessageDialog(frame3, "Digite a quantidade de arquivos que serão criados");
                    frame3.setVisible(true);
            }
            }
        });


        // Cria os botões
        JButton button1 = new JButton("Criar");
        JButton button3 = new JButton("Deletar");

        // Adiciona ActionListener para o primeiro botão
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Selecione a pasta");

                // Exibe o diálogo de seleção de diretório
                int result = fileChooser.showOpenDialog(frame);

                // Verifica se o usuário selecionou um diretório
                if (result == JFileChooser.APPROVE_OPTION) {
                    selectedDirectory = fileChooser.getSelectedFile();
                    System.out.println("Diretório selecionado: " + selectedDirectory.getAbsolutePath());
                    frame2.setVisible(true);
                    JOptionPane.showMessageDialog(frame2, "Digite um nome para os arquivos criados");
                } else {
                    JOptionPane.showMessageDialog(frame, "Pasta não encontrada");
                }


            }
        });



        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = 1;
                while (i < quantosArq + 1) {

                    // Cria um objeto File para o arquivo
                    File file = new File(selectedDirectory.getAbsolutePath() + File.separator + titulo + String.valueOf(i) + ".txt");

                    // Tenta deletar o arquivo
                    if (file.exists()) {
                        if (file.delete()) {
                            System.out.println("Arquivo deletado com sucesso.");
                        } else {
                            System.out.println("Falha ao deletar o arquivo.");
                        }
                    } else {
                        System.out.println("Arquivo não encontrado.");
                    }
                    i += 1;
                }
                JOptionPane.showMessageDialog(frame, "Arquivos Deletados");
            }
        });

        // Adiciona os botões ao frame
        frame.add(button1);
        frame.add(button3);

        // Torna o frame visível
        frame.setVisible(true);
    }
}