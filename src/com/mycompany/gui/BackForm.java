package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import com.mycomany.entities.Course;
import com.mycompany.services.ServiceCourse;

import java.util.ArrayList;

public class BackForm extends BaseForm {
    public final TextField searchField = new TextField("", "Search");

    public BackForm(Resources res) {
        super("Courses Back office", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();


        searchField.addDataChangedListener( (i1, i2) -> {
            String searchQuery = searchField.getText().toLowerCase();
            ArrayList<Course> filteredCourses = new ArrayList<>();
            for (Course course : ServiceCourse.getInstance().getAllCourses()) {
                if (course.getTitle().toLowerCase().contains(searchQuery) ||
                        course.getCategory().toLowerCase().contains(searchQuery)) {
                    filteredCourses.add(course);
                }
            }
            showCourses(filteredCourses);
        });

        
        showCourses(ServiceCourse.getInstance().getAllCourses());

        tb.setBackCommand("", e -> previous.showBack());
        setUIID("Activate");
        super.addSideMenu(res);
    }

    private void showCourses(ArrayList<Course> courses) {
        removeAll();
        createLineSeparator();
        Label label = new Label("\n ");
        Label label1 = new Label("\n ");
        addAll(label, label1,searchField);
        for (Course course : courses) {
            TableLayout t = new TableLayout(2, 2);

            t.setGrowHorizontally(true);
            Font f = Font.createSystemFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD, Font.SIZE_MEDIUM);

            Container rowContainer = new Container(t);
            add(rowContainer);
            Label courseName = new Label(course.getTitle());
            Label courseDescription = new Label(course.getCategory());
            courseName.getUnselectedStyle().setFont(f);
            courseDescription.getUnselectedStyle().setFont(f);
            rowContainer.add(courseName);
            rowContainer.add(courseDescription);

            Button delete = new Button("Delete");
            delete.addActionListener(evt -> {
                boolean userResponse = com.codename1.ui.Dialog.show("Alert", "Are you sure you want to delete this course?", "Yes", "No");
                if (userResponse) {
                    ServiceCourse.getInstance().deleteCourse(course);
                    com.codename1.ui.Dialog.show("Success", "The course has been deleted.", new com.codename1.ui.Command("OK"));
                    showCourses(ServiceCourse.getInstance().getAllCourses());
                }
            });
            add(delete);
        }
    }

    private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();
    }

    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if (b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
    }
}
