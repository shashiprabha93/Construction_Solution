/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package constructionsolution;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 *
 * @author Shashiprabha
 */
public class ComboSearch<T> {
    
    
    ArrayList<T> ar;
    JTextField txt;
    private ArrayList<String> x = new ArrayList<String>();

    public void setSearchableCombo(final JComboBox cmb, boolean mustSort, final String noReultsText) {

        ar = new ArrayList<T>();
        final int s = cmb.getItemCount();
        for (int i = 0; i < s; i++) {
            System.out.println(s);
            boolean exists = false;
            for (int j = 0; j < ar.size(); j++) {
                if (ar.get(j).toString().equalsIgnoreCase(cmb.getItemAt(i).toString())) {
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                System.out.println(cmb.getClass());
                ar.add((T) cmb.getItemAt(i));
            }
        }
        if (mustSort) {
            //ar.sort(s);
            //Collections.<T>sort(ar);
        }

        txt = (JTextField) cmb.getEditor().getEditorComponent();
        txt.addKeyListener(new KeyAdapter() {

            public void keyReleased(KeyEvent evt) {
                int key = evt.getKeyCode();
                if (!(key == KeyEvent.VK_ESCAPE || key == KeyEvent.VK_ENTER || key == KeyEvent.VK_DOWN || key == KeyEvent.VK_UP)) {
                    String s = txt.getText();
                    int caret = txt.getCaretPosition();
                    cmb.hidePopup();
                    cmb.removeAllItems();
                    for (int i = 0; i < ar.size(); i++) {
                        if (ar.get(i).toString().toUpperCase().startsWith(s.substring(0, caret).toUpperCase())) {
                            cmb.addItem(ar.get(i));
                        }
                    }
                    cmb.showPopup();
                    if (cmb.getItemCount() == 0) {
                        cmb.addItem(noReultsText);
                    }
                    txt.setText(s);
                    txt.setCaretPosition(caret);
                } else if (key == KeyEvent.VK_ESCAPE) {
                    cmb.setSelectedItem(txt.getText());
                    cmb.hidePopup();
//                } else if (key == KeyEvent.VK_ENTER && cmb.getSelectedIndex() == -1) {
//                    if (cmb.getItemCount() == 1 && !cmb.getItemAt(0).equals(noReultsText)) {
//                        cmb.setSelectedIndex(0);
//                    } else if (cmb.getItemCount() > 1) {
//                        cmb.setSelectedIndex(0);
//                    }
                }

                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
//
//                    jtxt.requestFocus();
//                    jtxt.selectAll();
                }

                if (evt.getKeyCode() == KeyEvent.VK_END) {
//                    jtxt_cash.requestFocus();
                }
            }
        });
    }

    public static <T> ArrayList<T> getModels(Class<T> type) {
        ArrayList<T> arrayList = new ArrayList<T>();
        return arrayList;
    }

    //shashi
    public JTextField getComboTextField() {
        return txt;
    }

    public void setComboTextField(JTextField comboTextField) {
        this.txt = comboTextField;
    }
    
}
