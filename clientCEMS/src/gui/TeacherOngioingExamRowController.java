package gui;

import java.io.IOException;
import java.util.ArrayList;

import client.ClientCEMS;
import entity.Exam;
import entity.Question;
import entity.updatedRequestExam;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import message.ClientMessage;
import message.ClientMessageType;
import message.ServerMessageTypes;

/**
 * A class that takes care of rows in table of on going exams.
 * @author Guy and sharon
 *
 */
public class TeacherOngioingExamRowController {
	@FXML
	private Label num;

	@FXML
	private Label title;

	

	@FXML
	private TextField newDurationText;

	@FXML
	private TextArea reasonText;

	

	@FXML
	private Button btnChangeTime;

	@FXML
	private Button btnLockExam;

	private updatedRequestExam exam;

	/**Sets the relevant exam to row table.
	 * @param updatedRequestExam exam
	 */
	public void setExam(updatedRequestExam exam) {
		this.exam = exam;
		title.setText(exam.getExam().getName());
	
	}

	/**
	 * Sending request of teacher for a new time request.
	 * @param ActionEvent event
	 */
	@FXML
	void btnChangeTimePressed(ActionEvent event) {
		ArrayList<String> newRequest = new ArrayList<String>();
		if (CheckInput()) {
			newRequest.add(exam.getExam().getEid());
			newRequest.add(exam.getExam().getName());
			newRequest.add(exam.getExam().getTotalTime());
			newRequest.add(exam.getExam().getID());
			newRequest.add(newDurationText.getText());

			GUIControl guiControl = GUIControl.getInstance();
			ClientMessage msg = new ClientMessage(ClientMessageType.TEACHER_SEND_REQUEST, newRequest);
			guiControl.sendToServer(msg);
			if (guiControl.getServerMsg().getType() == ServerMessageTypes.TEACHER_REQUEST_NOT_RECIVED) {
				GUIControl.popUpError("Error-sending new request.");
			}
		}
	}

	/**
	 * Takes care of locking the exam
	 * @param ActionEvent event
	 * @throws IOException
	 */
	@FXML
	void btnLockExamPressed(ActionEvent event) throws IOException {

		ClientMessage m1 = new ClientMessage(ClientMessageType.TEACHER_LOCK_EXAM, exam.getExam());
		GUIControl.getInstance().sendToServer(m1);

	}

	/**
	 * checks the input of new duration
	 * @return boolean
	 */
	private boolean CheckInput() {
		String input = newDurationText.getText();

		if (input.isEmpty()) {
			GUIControl.popUpError("Error - Field is empty, please insert a number.");
			return false;
		}

		else if (!input.matches("[0-9]+")) {
			GUIControl.popUpError("Error - Please insert numbers only.");
			return false;
		}
		return true;
	}

}
