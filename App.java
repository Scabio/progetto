package com.vashlabs.project;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
//import javafx.scene.layout.StackPane;
//import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application implements EventHandler<ActionEvent> {

	private String titleText = "VashLabs";
	private String url = "http://localhost/insert.php";
	// private String buttonText1 = "Click!";

	// private VBox layout1 = new VBox(20);
	// private StackPane layout2 = new StackPane();
	private GridPane grid = new GridPane();

	// private Button button1 = new Button();
	// private Button button2 = new Button();
	private Button buttonAddRow = new Button("Aggiungi riga");
	private Button buttonSubmit = new Button("Submit");

	private Label labelDataCall = new Label("Data call");
	private Label labelTimeCall = new Label("Ora call");
	private Label labelNominativo = new Label("Nominativo cliente");
	private Label labelMotivazione = new Label("Titolo motivazione call");
	private Label labelDescrizione = new Label("Descrizione motivazione call");

	private Scene scene1 = new Scene(grid, 500, 500);
	// private Scene scene2 = new Scene(layout2, 500, 500);

	private Stage window;

	private int row = 0;
	private int headerRows = 2;

	private List<DatePicker> dataCallList = new ArrayList<>();
	private List<TextField> timeCallList = new ArrayList<>();
	private List<TextField> nominativoList = new ArrayList<>();
	private List<TextField> motivazioneList = new ArrayList<>();
	private List<TextField> descrizioneList = new ArrayList<>();

	@Override
	public void start(Stage stage) {
		window = stage;
		/*
		 * button1.setText(buttonText1); button1.setOnAction(this);
		 * 
		 * button2.setText(buttonText1); button2.setOnAction(this);
		 */
		// layout1.getChildren().addAll(label1, button1);

		// layout2.getChildren().addAll(label2, button2);
		buttonAddRow.setOnAction(this);
		buttonSubmit.setOnAction(this);
		grid.addRow(row++, buttonAddRow, buttonSubmit);
		grid.addRow(row++, labelDataCall, labelTimeCall, labelNominativo, labelMotivazione, labelDescrizione);

		window.setTitle(titleText);
		window.setScene(scene1);
		window.show();
	}

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void handle(ActionEvent event) {
		int index = row - headerRows;
		if (event.getSource() == buttonAddRow) {
			System.out.println("Stai aggiungendo una riga alla tabella");

			dataCallList.add(index, new DatePicker());
			timeCallList.add(index, new TextField());
			nominativoList.add(index, new TextField());
			motivazioneList.add(index, new TextField());
			descrizioneList.add(index, new TextField());
			grid.addRow(row++, dataCallList.get(index), timeCallList.get(index), nominativoList.get(index),
					motivazioneList.get(index), descrizioneList.get(index));

		} else if (event.getSource() == buttonSubmit) {
			System.out.println("Stai per inviare i dati");
			try {
				sendData();
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private synchronized void sendData() throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		String body = "";
		BodyPublisher bodyPublisher;
		HttpRequest request;
		String dataCall;
		String timeCall;
		String nominativo;
		String motivazione;
		String descrizione;
		int maxIndex = row - headerRows;
		for (int i = 0; i < maxIndex; i++) {
			dataCall = URLEncoder.encode( dataCallList.get(i).getValue().toString(), "UTF-8");
			timeCall = URLEncoder.encode( timeCallList.get(i).getText(), "UTF-8");
			nominativo = URLEncoder.encode( nominativoList.get(i).getText(), "UTF-8");
			motivazione = URLEncoder.encode( motivazioneList.get(i).getText(), "UTF-8");
			descrizione = URLEncoder.encode( descrizioneList.get(i).getText(), "UTF-8");
			if (!body.equals("")) {
				body = body + "&";
			}
			body = body + "dataCall" + i + "=" + dataCall.toString() + "&timeCall" + i + "=" + timeCall.toString()
					+ "&nominativo" + i + "=" + nominativo + "&motivazione" + i + "=" + motivazione + "&descrizione" + i
					+ "=" + descrizione;

		}
		System.out.println("--------------\nbody =\n" + body + "\n--------------");
		bodyPublisher = BodyPublishers.ofString(body);
		request = HttpRequest.newBuilder().uri(URI.create(url)).header("Content-Type", "text/plain; charset=UTF-8")
				.POST(bodyPublisher).build();
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
	}
	/*
	 * private void getData(String data) { if(data.equals("OK")) {
	 * 
	 * } }
	 */
}