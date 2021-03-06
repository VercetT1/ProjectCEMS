package gui;

import java.io.IOException;

import entity.Course;
import entity.Exam;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * @author Guy and Sharon
 *TeacherCreateExamPage4Controller - controller of the fourth page of creating exam
 *(Page with success message of creation and the generated exam number ).
 */
public class TeacherCreateExamPage4Controller {

	 
	 
	private Course course;
    @FXML
    private Text examID;

    @FXML
    private Button btnBack;

    
    public  void setexamID(String s) {
    	examID.setText(s);
    }
   
    
    public void setCourse(Course course) {
		this.course = course;
	}
    
    @FXML
    void btnBackPressed(ActionEvent event) throws IOException {
    	((TeacherExamBankExamsController)GUIControl.instance.loadStage("/gui/TeacherExamBankExams.fxml")).setTeacherCourse(course);
    }

}
