import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class ChangePassword extends MIDlet implements CommandListener {
	private Display display;
	private Command change, cancel;
	private Form form;
	private TextField old, newpass, confirm;

	public ChangePassword(){
		display = Display.getDisplay(this);
		old = new TextField("Old Password","", 10, TextField.PASSWORD);
		newpass = new TextField("New Password","", 10, TextField.PASSWORD);
		confirm = new TextField("Confirm Password","", 10, TextField.PASSWORD);
		form = new Form("Change Password");
		change = new Command("Change", Command.OK, 2);
		cancel = new Command("Cancel", Command.EXIT,2);
		form.append(old);
		form.append(newpass);
		form.append(confirm);
		form.addCommand(change);
		form.addCommand(cancel);
		form.setCommandListener(this);
	}

	public void startApp(){
		display.setCurrent(form);
	}

	public void pauseApp(){}

	public void destroyApp(boolean unconditional){
		notifyDestroyed();
	}

	public void commandAction(Command c, Displayable d){
		String label = c.getLabel();
		if(label.equals("Cancel")) {
			destroyApp(true);
		}

		else if(label.equals("Change")) {
			validate(old.getString(), newpass.getString(), confirm.getString());
		}
	}

	public void validate(String old, String newpass, String confirm) {
		if(old.equals("old") && newpass.equals(confirm)) {
			success();
		}
		else{
			error();
		}
	}

	public void success() {
		Alert success = new Alert("Success", "Password Changed", null, AlertType.CONFIRMATION);
		success.setTimeout(Alert.FOREVER);
		old.setString("");
		newpass.setString("");
		confirm.setString("");
		display.setCurrent(success, form);
	}

	public void error() {
		Alert error = new Alert("Error", "Try again", null, AlertType.ERROR);
		error.setTimeout(Alert.FOREVER);
		old.setString("");
		newpass.setString("");
		confirm.setString("");
		display.setCurrent(error, form);
	}
}