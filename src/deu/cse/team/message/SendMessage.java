/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deu.cse.team.message;

import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import java.util.HashMap;
import org.json.simple.JSONObject;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

/**
 *
 * @author PC
 */
public class SendMessage {

    public void SendMessage() {
    }
    
    public void send(String phonenumber){
        String api_key = "NCSAADXHKAHNDRYW";
        String api_secret = "3PT9WLTH4XWTXA0TN8PITITSEFFD11H2";
        Message coolsms = new Message(api_key, api_secret);

        // 4 params(to, from, type, text) are mandatory. must be filled
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", phonenumber);
        params.put("from", "01066885399"); //무조건 자기번호 (인증)
        params.put("type", "SMS");
        params.put("text", "당신은 관리책임자 입니다. 강의실을 나가실 때, 청소 및 소등 부탁드립니다.");
        params.put("app_version", "test app 1.2"); // application name and version

        try {
            //send() 는 메시지를 보내는 함수
            JSONObject obj = (JSONObject) coolsms.send(params);
            System.out.println(obj.toString());
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }
    }

}
