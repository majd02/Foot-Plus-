/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;
import com.codename1.components.InteractionDialog;
import com.codename1.googlemaps.MapContainer;
import com.codename1.googlemaps.MapLayout;
import com.codename1.maps.Coord;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.SideMenuBar;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Stade;
import java.io.IOException;
import java.util.StringTokenizer;

public class Map extends Form {

     private static final String HTML_API_KEY = "AIzaSyDdZShLxaKzh5awxa3teDOGLIAk9r1L_SM";
    private Form current;

    public void init(Object context) {
        try {
            Resources theme = Resources.openLayered("/theme");
            UIManager.getInstance().setThemeProps(theme.getTheme(theme.getThemeResourceNames()[0]));
            Display.getInstance().setCommandBehavior(Display.COMMAND_BEHAVIOR_SIDE_NAVIGATION);
            UIManager.getInstance().getLookAndFeel().setMenuBarClass(SideMenuBar.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start(Form previous,Stade stade) {
        if (current != null) {
            current.show();
            return;
        }
        
        Form hi = new Form("Stade Location");
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack()); 
        hi.setLayout(new BorderLayout());
        final MapContainer cnt = new MapContainer(HTML_API_KEY);
        Style s = new Style();
         String fruits =  stade.getAdresse();
    String delimiter = ",";
    StringTokenizer fruitsTokenizer = new StringTokenizer(fruits, delimiter);
    
     while (fruitsTokenizer.hasMoreTokens()) {
            double d =  Double.parseDouble(fruitsTokenizer.nextToken());
             double d1 =  Double.parseDouble(fruitsTokenizer.nextToken());

         cnt.setCameraPosition(new Coord(36.7480,10.2727));
        Container root = LayeredLayout.encloseIn(
                BorderLayout.center(cnt)
                
        );
        Container markers = new Container();
      markers.setLayout(new MapLayout(cnt, markers));
      root.add(markers);

      Coord moscone = new Coord(36.7480,10.2727);
      Button mosconeButton = new Button("");
      FontImage.setMaterialIcon(mosconeButton, FontImage.MATERIAL_PLACE);
      markers.add(moscone, mosconeButton);

      cnt.zoom(moscone, 13);

       
       
        hi.add(BorderLayout.CENTER, root);
        hi.show();

    }
    }

    public void stop() {
        current = Display.getInstance().getCurrent();
    }

    public void destroy() {
    }
   

}