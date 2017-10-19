package sample;

import Server.Inf;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by ASUS on 12/7/2015.
 */
public class DonorListController {
    @FXML
    TableView donarlist;
    @FXML
    Button refresh;
    List<TableInf> list;
    ObservableList<TableInf> infs= FXCollections.observableArrayList();
    Socket S;
    boolean isAdmin;
    String userName;
    String searchBooldGroup="all";


    public void setSearchBooldGroup(String searchBooldGroup) {
        this.searchBooldGroup = searchBooldGroup;
    }
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date = new Date();
    String str = dateFormat.format(date);
    String[] tokens = str.split(" ");
    String TDate = tokens[0];
    @FXML
    public void initialize(){
        TableColumn<TableInf, String> firstNameCol = new TableColumn<>("Name");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("sspName"));
        TableColumn<TableInf, String> lastNameCol = new TableColumn<>("Email");
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("sspEmail"));

        TableColumn<TableInf, String> emailCol = new TableColumn<>("Phone");
        emailCol.setMinWidth(100);
        emailCol.setCellValueFactory(new PropertyValueFactory<>("sspPnone"));

        TableColumn<TableInf, String> bdGroup = new TableColumn<>("Bloog Group");
        bdGroup.setMinWidth(100);
        bdGroup.setCellValueFactory(new PropertyValueFactory<>("sspBloodGroup"));


        TableColumn<TableInf, String> lastDonate = new TableColumn<>("Last Donate");
        lastDonate.setMinWidth(100);
        lastDonate.setCellValueFactory(new PropertyValueFactory<>("sspLastDonate"));




        TableColumn<TableInf, String> actionCol = new TableColumn<>("Action");
        actionCol.setCellValueFactory(new PropertyValueFactory<>("action"));

        Callback<TableColumn<TableInf, String>, TableCell<TableInf, String>> cellFactory =
                new Callback<TableColumn<TableInf, String>, TableCell<TableInf, String>>() {
                    @Override
                    public TableCell call( final TableColumn<TableInf, String> param ) {
                        final TableCell<TableInf, String> cell = new TableCell<TableInf, String>() {
                            final Button btn = new Button("Edit");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                // action of 'Select' button click
                                if (getIndex()>=0 && getIndex()<getTableView().getItems().size()){
                                    TableInf inf = getTableView().getItems().get( getIndex() );
                                    boolean isEditable;
                                    isEditable=isAdmin || userName.equals(inf.getName());
                                    btn.setDisable(!isEditable);

                                }
                                btn.setOnAction( ( ActionEvent event ) -> {
                                    TableInf   inf = getTableView().getItems().get( getIndex() ); //= getTableView().getItems().get( getIndex() );
                                    System.out.println("On Edit Button Click");
                                    try{
                                        Stage stage = (Stage) donarlist.getScene().getWindow();
                                        FXMLLoader loader=new FXMLLoader(getClass().getResource("edit.fxml"));
                                        Parent root=loader.load();
                                        EditController controller=loader.getController();
                                        controller.setListInfs(list);
                                        controller.setAdmin(isAdmin);
                                        controller.setEditable(inf);
                                        System.out.println(S.toString());
                                        controller.setS(S);
                                        Scene scene = new Scene(root);
                                        stage.setScene(scene);
                                        stage.show();
                                    }catch (Exception e){

                                    }

                                    }
                                );
                                if ( getIndex()<getTableView().getItems().size() && searchBooldGroup.equals("all"))setGraphic(btn);
                                setText(null);
                            }
                        };
                        return cell;
                    }
                };
        actionCol.setCellFactory(cellFactory);



        /*TableColumn<Inf, String> bloodGrp = new TableColumn<>("Blood Group");
        emailCol.setMinWidth(50);
        TableColumn<Inf, String> lastDonate = new TableColumn<>("Last Donate");
        emailCol.setMinWidth(300);*/
        donarlist.getColumns().addAll(firstNameCol, lastNameCol, emailCol,bdGroup,lastDonate,actionCol);// bloodGrp, lastDonate);

    }

    public void setList(List<TableInf> tableInfs) {
        this.list = tableInfs;
        for(TableInf t:tableInfs){
            if (searchBooldGroup.equals("all")){
                infs.add(t);
            }
            else if(searchBooldGroup.equals(t.getBloodGr())){
                DateandTime A = new DateandTime(TDate, t.getLastDonate());
                boolean x = A.diff();
                if(x) {
                    infs.add(t);
                }
            }
        }
        donarlist.setItems(infs);

    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
        System.out.println("Is Admin Set");
    }

    public void setUserName(String userName) {
        this.userName = userName;
        System.out.println("User Name Set");
    }

    public void setS(Socket s) {
        S = s;
        System.out.println(S.toString());
    }
}
