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
import com.mycomany.entities.Service;
import com.mycompany.services.ServiceService;

import java.util.ArrayList;

public class BackForm extends BaseForm {
    public final TextField searchField = new TextField("", "Search");

    public BackForm(Resources res) {
        super("Services Back office", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        searchField.addDataChangedListener( (i1, i2) -> {
            String searchQuery = searchField.getText().toLowerCase();
            ArrayList<Service> filteredServices = new ArrayList<>();
            for (Service service : ServiceService.getInstance().getAllServices()) {
                if (service.getName().toLowerCase().contains(searchQuery) ||
                        service.getCat().toLowerCase().contains(searchQuery)) {
                    filteredServices.add(service);
                }
            }
            showServices(filteredServices);
        });
          showServices(ServiceService.getInstance().getAllServices());

        tb.setBackCommand("", e -> previous.showBack());
        setUIID("Activate");
      
    }
     private void showServices(ArrayList<Service> services) {
        removeAll();
        createLineSeparator();
        Label label = new Label("\n ");
        Label label1 = new Label("\n ");
        addAll(label, label1,searchField);
        for (Service service : services) {
            TableLayout t = new TableLayout(2, 2);

            t.setGrowHorizontally(true);
            Font f = Font.createSystemFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD, Font.SIZE_MEDIUM);


  Container rowContainer = new Container(t);
            add(rowContainer);
            Label serviceName = new Label(service.getName());
            Label serviceDescription = new Label(service.getCat());
            serviceName.getUnselectedStyle().setFont(f);
            serviceDescription.getUnselectedStyle().setFont(f);
            rowContainer.add(serviceName);
            rowContainer.add(serviceDescription);

Button delete = new Button("Delete");
            delete.addActionListener(evt -> {
                boolean userResponse = com.codename1.ui.Dialog.show("Alert", "Are you sure you want to delete this course?", "Yes", "No");
                if (userResponse) {
                    ServiceService.getInstance().deleteService(service);
                    com.codename1.ui.Dialog.show("Success", "The course has been deleted.", new com.codename1.ui.Command("OK"));
                    showServices( ServiceService.getInstance().getAllServices());
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



