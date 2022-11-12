package deu.cse.team.strategy;

import javax.swing.JOptionPane;

public class test {

    public static void main(String[] args) {

        LectureRoom class915 = new Class915();
        String str;
        String str2;
        class915.setAllowedBehavior(new AllowedAssistant());
        class915.setAllowedBehavior(new AllowedStudent());
        str = class915.performAllowed();
        str2 = class915.display();
        JOptionPane.showMessageDialog(null, str);
        JOptionPane.showMessageDialog(null, str2);

    }
}
