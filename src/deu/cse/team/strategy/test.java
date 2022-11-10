package deu.cse.team.strategy;

import javax.swing.JOptionPane;

public class test {

    public static void main(String[] args) {

        LectureRoom class915 = new Class915();
        String str;
        AllowedAssistant yes;
        class915.setAllowedBehavior(new AllowedAssistant());
        str = class915.performAllowed();
        JOptionPane.showMessageDialog(null, str);

    }
}
