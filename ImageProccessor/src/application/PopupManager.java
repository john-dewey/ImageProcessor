package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PopupManager {

	/**
	 * Attributes
	 */
	private Picture _pic;
	private Momento _momento;

	public PopupManager(Picture pic, Momento momento) {
		_pic = pic;
		_momento = momento;
	}

	protected void buildComposite() {
		//Create control and layout nodes

		Label compositeImageLabel = new Label(Constants.PROMPT_COMPOSITE);
		TextField compositeImageInput = new TextField(Constants.TEXT_DOG_FILE);
		VBox compositeImageField = new VBox(compositeImageLabel,compositeImageInput);

		Label maskImageLabel = new Label(Constants.PROMPT_MASK);
		TextField maskImageInput = new TextField(Constants.TEXT_MASK_FILE);
		VBox maskImageField = new VBox(maskImageLabel,maskImageInput);

		Button exitButton = new Button(Constants.TEXT_EXIT);
		Button doneButton = new Button(Constants.TEXT_DONE);

		VBox display = new VBox(compositeImageField, maskImageField);
		HBox buttonRow = new HBox(exitButton, doneButton);
		VBox popup = new VBox(display,buttonRow);
		
		//move
		compositeImageField.setAlignment(Pos.CENTER);
		compositeImageField.getStyleClass().add(Constants.CSS_POPUP_V);
		maskImageField.setAlignment(Pos.CENTER);
		maskImageField.getStyleClass().add(Constants.CSS_POPUP_V);

		//Size elements
		compositeImageLabel.setPrefSize(Constants.W_POPUP_L, Constants.H_POPUP_L);
		compositeImageInput.setMaxSize(Constants.W_POPUP_L, Constants.H_POPUP_L);
		maskImageLabel.setPrefSize(Constants.W_POPUP_L, Constants.H_POPUP_L);
		maskImageInput.setMaxSize(Constants.W_POPUP_L, Constants.H_POPUP_L);
		exitButton.setPrefSize(Constants.W_POPUP_B, Constants.H_POPUP_B);
		doneButton.setPrefSize(Constants.W_POPUP_B, Constants.H_POPUP_B);
		

		//Space and align elements
		display.setAlignment(Pos.TOP_CENTER);
		buttonRow.setAlignment(Pos.BOTTOM_RIGHT);
		popup.setAlignment(Pos.CENTER);
		display.setSpacing(Constants.S_10);
		buttonRow.setSpacing(Constants.S_5);
		popup.setSpacing(Constants.S_20);

		// CSS
		compositeImageLabel.getStyleClass().add(Constants.CSS_POPUP_L);
		compositeImageInput.getStyleClass().add(Constants.CSS_POPUP_T);
		maskImageLabel.getStyleClass().add(Constants.CSS_POPUP_L);
		maskImageInput.getStyleClass().add(Constants.CSS_POPUP_T);
		exitButton.getStyleClass().add(Constants.CSS_POPUP_B);
		doneButton.getStyleClass().add(Constants.CSS_POPUP_B);
		display.getStyleClass().add(Constants.CSS_POPUP_V);
		buttonRow.getStyleClass().add(Constants.CSS_POPUP_BUTTONROW_C);
		popup.getStyleClass().add(Constants.CSS_POPUP_CLASS);

		//Import events
		exitButton.setOnAction(new ExitHandler());
		doneButton.setOnAction(new FinishCompositeHandler(_pic, _momento, compositeImageInput, maskImageInput));

		//Show window
		Scene scene = new Scene(popup, Constants.W_POPUP, Constants.H_POPUP);
		scene.getStylesheets().add(getClass().getResource(Constants.CSS_FILENAME).toExternalForm());
		Stage newWindow = new Stage();
		newWindow.setTitle(Constants.TITLE_COMPOSITE_WINDOW);
		newWindow.setScene(scene);
		newWindow.show();
	}

	protected void buildPolation() {
		//Create control and layout nodes

		Label imageLabel = new Label(Constants.PROMPT_POLATION);
		TextField imageInput = new TextField(Constants.TEXT_DOG_FILE);
		VBox imageField = new VBox(imageLabel,imageInput);
		


		Label alphaLabel = new Label(Constants.PROPMPT_ALPHA);
		TextField alphaInput = new TextField(Constants.MATH_ALPHA_DEFAULT+"");
		VBox alphaField = new VBox(alphaLabel,alphaInput);

		Button doneButton = new Button(Constants.TEXT_DONE);
		Button exitButton = new Button(Constants.TEXT_EXIT);

		VBox display = new VBox(imageField, alphaField);
		HBox buttonRow = new HBox(exitButton, doneButton);
		VBox popup = new VBox(display,buttonRow);

		//move
		imageField.setAlignment(Pos.CENTER);
		imageField.getStyleClass().add(Constants.CSS_POPUP_V);
		alphaField.setAlignment(Pos.CENTER);
		alphaField.getStyleClass().add(Constants.CSS_POPUP_V);
		
		
		//Size elements
		imageLabel.setPrefSize(Constants.W_POPUP_L, Constants.H_POPUP_L);
		imageInput.setMaxSize(Constants.W_POPUP_L, Constants.H_POPUP_L);
		alphaLabel.setPrefSize(Constants.W_POPUP_L, Constants.H_POPUP_L);
		alphaInput.setMaxSize(Constants.W_POPUP_L, Constants.H_POPUP_L);
		exitButton.setPrefSize(Constants.W_POPUP_B, Constants.H_POPUP_B);
		doneButton.setPrefSize(Constants.W_POPUP_B, Constants.H_POPUP_B);
		

		//Space and align elements
		display.setAlignment(Pos.TOP_CENTER);
		buttonRow.setAlignment(Pos.BOTTOM_RIGHT);
		popup.setAlignment(Pos.CENTER);
		display.setSpacing(Constants.S_10);
		buttonRow.setSpacing(Constants.S_5);
		popup.setSpacing(Constants.S_20);


		//Apply CSS styles
		alphaLabel.getStyleClass().add(Constants.CSS_POPUP_L);
		imageInput.getStyleClass().add(Constants.CSS_POPUP_T);
		imageLabel.getStyleClass().add(Constants.CSS_POPUP_L);
		alphaInput.getStyleClass().add(Constants.CSS_POPUP_T);
		exitButton.getStyleClass().add(Constants.CSS_POPUP_B);
		doneButton.getStyleClass().add(Constants.CSS_POPUP_B);
		display.getStyleClass().add(Constants.CSS_POPUP_V);
		buttonRow.getStyleClass().add(Constants.CSS_POPUP_BUTTONROW_C);
		popup.getStyleClass().add(Constants.CSS_POPUP_CLASS);

		//Import events
		exitButton.setOnAction(new ExitHandler());
		doneButton.setOnAction(new FinishPolationHandler(_pic, _momento, imageInput, alphaInput  ));

		//Show window
		Scene scene = new Scene(popup, Constants.W_POPUP, Constants.H_POPUP);
		scene.getStylesheets().add(getClass().getResource(Constants.CSS_FILENAME).toExternalForm());
		Stage newWindow = new Stage();
		newWindow.setTitle(Constants.TITLE_POLATION_WINDOW);
		newWindow.setScene(scene);
		newWindow.show();
	}

	protected void buildGaussian() {
		//Create control and layout nodes
		Label sigmaLabel = new Label(Constants.PROMPT_SIGMA);
		TextField sigmaInput = new TextField(Constants.MATH_SIGMA_DEFAULT+"");
		VBox sigmaField = new VBox(sigmaLabel,sigmaInput);
		
		//move
		sigmaField.setAlignment(Pos.CENTER);
		sigmaField.getStyleClass().add(Constants.CSS_POPUP_V);

		Button doneButton = new Button(Constants.TEXT_DONE);
		Button exitButton = new Button(Constants.TEXT_EXIT);

		VBox display = new VBox(sigmaField);
		HBox buttonRow = new HBox(exitButton, doneButton);
		VBox popup = new VBox(display,buttonRow);

		//Size elements
		sigmaInput.setMaxSize(Constants.W_POPUP_L, Constants.H_POPUP_L);
		sigmaLabel.setPrefSize(Constants.W_POPUP_L, Constants.H_POPUP_L);
		exitButton.setPrefSize(Constants.W_POPUP_B, Constants.H_POPUP_B);
		doneButton.setPrefSize(Constants.W_POPUP_B, Constants.H_POPUP_B);
		

		//Space and align elements
		display.setAlignment(Pos.TOP_CENTER);
		buttonRow.setAlignment(Pos.BOTTOM_RIGHT);
		popup.setAlignment(Pos.CENTER);
		display.setSpacing(Constants.S_10);
		buttonRow.setSpacing(Constants.S_5);
		popup.setSpacing(Constants.S_20);


		//Apply CSS styles
		sigmaLabel.getStyleClass().add(Constants.CSS_POPUP_L);
		sigmaInput.getStyleClass().add(Constants.CSS_POPUP_T);
		exitButton.getStyleClass().add(Constants.CSS_POPUP_B);
		doneButton.getStyleClass().add(Constants.CSS_POPUP_B);
		display.getStyleClass().add(Constants.CSS_POPUP_V);
		buttonRow.getStyleClass().add(Constants.CSS_POPUP_BUTTONROW_C);
		popup.getStyleClass().add(Constants.CSS_POPUP_CLASS);

		//Import events
		exitButton.setOnAction(new ExitHandler());
		doneButton.setOnAction(new FinishGaussianHandler(_pic, _momento, sigmaInput));

		//Show window
		Scene scene = new Scene(popup, Constants.W_POPUP, Constants.H_POPUP);
		scene.getStylesheets().add(getClass().getResource(Constants.CSS_FILENAME).toExternalForm());
		Stage newWindow = new Stage();
		newWindow.setTitle(Constants.TITLE_GAUSSIAN_WINDOW);
		newWindow.setScene(scene);
		newWindow.show();
	}

	protected void buildAdjustChannel() {
		//Create control and layout nodes
		Label channelLabel = new Label(Constants.PROMPT_CHANNEL);
		TextField channelValue = new TextField(Constants.MATH_CHANNEL_DEFAULT+"");
		VBox channelField = new VBox(channelLabel,channelValue);
		
		//move
		channelField.setAlignment(Pos.CENTER);
		channelField.getStyleClass().add(Constants.CSS_POPUP_V);
		
		Button doneButton = new Button(Constants.TEXT_DONE);
		Button exitButton = new Button(Constants.TEXT_EXIT);
		HBox buttonRow = new HBox(exitButton, doneButton);
		
		VBox display = new VBox(channelField);
		VBox popup = new VBox(display,buttonRow);
		

		//Size elements
		channelValue.setMaxSize(Constants.W_POPUP_L, Constants.H_POPUP_L);
		channelLabel.setPrefSize(Constants.W_POPUP_L, Constants.H_POPUP_L);
		exitButton.setPrefSize(Constants.W_POPUP_B, Constants.H_POPUP_B);
		doneButton.setPrefSize(Constants.W_POPUP_B, Constants.H_POPUP_B);
		

		//Space and align elements
		display.setAlignment(Pos.TOP_CENTER);
		buttonRow.setAlignment(Pos.BOTTOM_RIGHT);
		popup.setAlignment(Pos.CENTER);
		display.setSpacing(Constants.S_10);
		buttonRow.setSpacing(Constants.S_5);
		popup.setSpacing(Constants.S_20);

		//Apply CSS styles
		channelLabel.getStyleClass().add(Constants.CSS_POPUP_L);
		channelValue.getStyleClass().add(Constants.CSS_POPUP_T);
		exitButton.getStyleClass().add(Constants.CSS_POPUP_B);
		doneButton.getStyleClass().add(Constants.CSS_POPUP_B);
		display.getStyleClass().add(Constants.CSS_POPUP_V);
		buttonRow.getStyleClass().add(Constants.CSS_POPUP_BUTTONROW_C);
		popup.getStyleClass().add(Constants.CSS_POPUP_CLASS);

		//Import events
		exitButton.setOnAction(new ExitHandler());
		doneButton.setOnAction(new FinishChannelHandler(_pic, _momento, channelValue));

		//Show window
		Scene scene = new Scene(popup, Constants.W_POPUP, Constants.H_POPUP);
		scene.getStylesheets().add(getClass().getResource(Constants.CSS_FILENAME).toExternalForm());
		Stage newWindow = new Stage();
		newWindow.setTitle(Constants.TITLE_CHANNEL_WINDOW);
		newWindow.setScene(scene);
		newWindow.show();
	}

	protected void buildAdjustBrightness() {

		//Create control and layout nodes
		Label comboBoxLabel = new Label(Constants.PROMPT_FUNCTION);

		String brighten = "Brighten";
		String darken = "Darken";

		ObservableList<String> options = 
				FXCollections.observableArrayList(
						brighten, darken
						);
		final ComboBox<String> comboBox = new ComboBox<String>(options);

		Button doneButton = new Button(Constants.TEXT_DONE);
		Button exitButton = new Button(Constants.TEXT_EXIT);
		
		VBox comboBoxField = new VBox(comboBoxLabel,comboBox);
		
		//move
		comboBoxField.setAlignment(Pos.CENTER);
		comboBoxField.getStyleClass().add(Constants.CSS_POPUP_V);

		VBox display = new VBox(comboBoxField);
		HBox buttonRow = new HBox(exitButton, doneButton);
		VBox popup = new VBox(display, buttonRow);

		//Size Elements
		comboBox.setPrefSize(Constants.W_POPUP_L, Constants.H_POPUP_L);
		doneButton.setPrefSize(Constants.W_POPUP_B, Constants.H_POPUP_B);
		exitButton.setPrefSize(Constants.W_POPUP_B, Constants.H_POPUP_B);
		comboBoxLabel.setPrefSize(Constants.W_POPUP_L, Constants.H_POPUP_L);
		
		
		//Space and align elements
		display.setAlignment(Pos.TOP_CENTER);
		buttonRow.setAlignment(Pos.BOTTOM_RIGHT);
		popup.setAlignment(Pos.CENTER);
		display.setSpacing(Constants.S_10);
		buttonRow.setSpacing(Constants.S_5);
		popup.setSpacing(Constants.S_20);


		// CSS
		comboBoxLabel.getStyleClass().add(Constants.CSS_POPUP_L);
		comboBox.getStyleClass().add(Constants.CSS_POPUP_T);
		doneButton.getStyleClass().add(Constants.CSS_POPUP_B);
		exitButton.getStyleClass().add(Constants.CSS_POPUP_B);
		display.getStyleClass().add(Constants.CSS_POPUP_V);
		buttonRow.getStyleClass().add(Constants.CSS_POPUP_BUTTONROW_C);
		popup.getStyleClass().add(Constants.CSS_POPUP_CLASS);
		
		//Import events
		exitButton.setOnAction(new ExitHandler());
		doneButton.setOnAction(new FinishBrightnessHandler(_pic, _momento, comboBox));


		//Show window
		Scene scene = new Scene(popup, Constants.W_POPUP, Constants.H_POPUP);
		scene.getStylesheets().add(getClass().getResource(Constants.CSS_FILENAME).toExternalForm());
		Stage newWindow = new Stage();
		newWindow.setTitle(Constants.TITLE_BRIGHTEN_WINDOW);
		newWindow.setScene(scene);
		newWindow.show();

	}

	protected void buildNoise() {
		//Create control and layout nodes

		Label alphaLabel = new Label(Constants.PROPMPT_ALPHA);
		TextField alphaInput = new TextField(Constants.MATH_ALPHA_DEFAULT+"");
		VBox alphaField = new VBox(alphaLabel,alphaInput);
		
		//move
		alphaField.setAlignment(Pos.CENTER);
		alphaField.getStyleClass().add(Constants.CSS_POPUP_V);

		Button doneButton = new Button(Constants.TEXT_DONE);
		Button exitButton = new Button(Constants.TEXT_EXIT);

		VBox display = new VBox(alphaField);
		HBox buttonRow = new HBox(exitButton, doneButton);
		VBox popup = new VBox(display, buttonRow);


		//Size elements
		alphaInput.setMaxSize(Constants.W_POPUP_L, Constants.H_POPUP_L);
		alphaLabel.setPrefSize(Constants.W_POPUP_L, Constants.H_POPUP_L);
		exitButton.setPrefSize(Constants.W_POPUP_B, Constants.H_POPUP_B);
		doneButton.setPrefSize(Constants.W_POPUP_B, Constants.H_POPUP_B);
		

		//Space and align elements
		display.setAlignment(Pos.TOP_CENTER);
		buttonRow.setAlignment(Pos.BOTTOM_RIGHT);
		popup.setAlignment(Pos.CENTER);
		display.setSpacing(Constants.S_10);
		buttonRow.setSpacing(Constants.S_5);
		popup.setSpacing(Constants.S_20);

		//Apply CSS styles
		alphaLabel.getStyleClass().add(Constants.CSS_POPUP_L);
		alphaInput.getStyleClass().add(Constants.CSS_POPUP_T);
		exitButton.getStyleClass().add(Constants.CSS_POPUP_B);
		doneButton.getStyleClass().add(Constants.CSS_POPUP_B);
		display.getStyleClass().add(Constants.CSS_POPUP_V);
		buttonRow.getStyleClass().add(Constants.CSS_POPUP_BUTTONROW_C);
		popup.getStyleClass().add(Constants.CSS_POPUP_CLASS);

		//Import events
		exitButton.setOnAction(new ExitHandler());
		doneButton.setOnAction(new FinishNoiseHandler(_pic, _momento, alphaInput  ));

		//Show window
		Scene scene = new Scene(popup, Constants.W_POPUP, Constants.H_POPUP);
		scene.getStylesheets().add(getClass().getResource(Constants.CSS_FILENAME).toExternalForm());
		Stage newWindow = new Stage();
		newWindow.setTitle(Constants.TITLE_NOISE_WINDOW);
		newWindow.setScene(scene);
		newWindow.show();
	}

	protected void buildThreshold() {
		//Create control and layout nodes

		Label thresholdLabel = new Label(Constants.PROMPT_THRESHOLD);
		TextField thresholdInput = new TextField(Constants.MATH_THRESHOLD_DEFAULT+"");
		VBox thresholdField = new VBox(thresholdLabel,thresholdInput);
		
		Button doneButton = new Button(Constants.TEXT_DONE);
		Button exitButton = new Button(Constants.TEXT_EXIT);

		VBox display = new VBox(thresholdField);
		HBox buttonRow = new HBox(exitButton, doneButton);
		VBox popup = new VBox(display, buttonRow);
		
		//move
		thresholdField.setAlignment(Pos.CENTER);
		thresholdField.getStyleClass().add(Constants.CSS_POPUP_V);


		//Size elements
		thresholdInput.setMaxSize(Constants.W_POPUP_L, Constants.H_POPUP_L);
		thresholdLabel.setPrefSize(Constants.W_POPUP_L, Constants.H_POPUP_L);
		exitButton.setPrefSize(Constants.W_POPUP_B, Constants.H_POPUP_B);
		doneButton.setPrefSize(Constants.W_POPUP_B, Constants.H_POPUP_B);
		

		//Space and align elements
		display.setAlignment(Pos.TOP_CENTER);
		buttonRow.setAlignment(Pos.BOTTOM_RIGHT);
		popup.setAlignment(Pos.CENTER);
		display.setSpacing(Constants.S_10);
		buttonRow.setSpacing(Constants.S_5);
		popup.setSpacing(Constants.S_20);

		//Apply CSS styles
		thresholdLabel.getStyleClass().add(Constants.CSS_POPUP_L);
		thresholdInput.getStyleClass().add(Constants.CSS_POPUP_T);
		exitButton.getStyleClass().add(Constants.CSS_POPUP_B);
		doneButton.getStyleClass().add(Constants.CSS_POPUP_B);
		display.getStyleClass().add(Constants.CSS_POPUP_V);
		buttonRow.getStyleClass().add(Constants.CSS_POPUP_BUTTONROW_C);
		popup.getStyleClass().add(Constants.CSS_POPUP_CLASS);

		//Import events
		exitButton.setOnAction(new ExitHandler());
		doneButton.setOnAction(new FinishThresholdHandler(_pic, _momento, thresholdInput ));

		//Show window
		Scene scene = new Scene(popup, Constants.W_POPUP, Constants.H_POPUP);
		scene.getStylesheets().add(getClass().getResource(Constants.CSS_FILENAME).toExternalForm());
		Stage newWindow = new Stage();
		newWindow.setTitle(Constants.TITLE_THRESHOLD_WINDOW);
		newWindow.setScene(scene);
		newWindow.show();
	}

	protected void buildImport(Label fileName) {
		//Create control and layout nodes

		Label fileLabel = new Label(Constants.PROMPT_FILEPATH);
		TextField fileInput = new TextField(Constants.TEXT_RANDOM_FILE);
		VBox fileField = new VBox(fileLabel,fileInput);

		Button doneButton = new Button(Constants.TEXT_DONE);
		Button exitButton = new Button(Constants.TEXT_EXIT);
		
		VBox display = new VBox(fileField);
		HBox buttonRow = new HBox(exitButton, doneButton);
		VBox popup = new VBox(display, buttonRow);
		
		//move
		fileField.setAlignment(Pos.CENTER);
		fileField.getStyleClass().add(Constants.CSS_POPUP_V);

		//Size elements
		fileInput.setMaxSize(Constants.W_POPUP_L, Constants.H_POPUP_L);
		fileLabel.setPrefSize(Constants.W_POPUP_L, Constants.H_POPUP_L);
		exitButton.setPrefSize(Constants.W_POPUP_B, Constants.H_POPUP_B);
		doneButton.setPrefSize(Constants.W_POPUP_B, Constants.H_POPUP_B);
		

		//Space and align elements
		display.setAlignment(Pos.TOP_CENTER);
		buttonRow.setAlignment(Pos.BOTTOM_RIGHT);
		popup.setAlignment(Pos.CENTER);
		display.setSpacing(Constants.S_10);
		buttonRow.setSpacing(Constants.S_5);
		popup.setSpacing(Constants.S_20);
		
		//Apply CSS styles
		fileLabel.getStyleClass().add(Constants.CSS_POPUP_L);
		fileInput.getStyleClass().add(Constants.CSS_POPUP_T);
		exitButton.getStyleClass().add(Constants.CSS_POPUP_B);
		doneButton.getStyleClass().add(Constants.CSS_POPUP_B);
		display.getStyleClass().add(Constants.CSS_POPUP_V);
		buttonRow.getStyleClass().add(Constants.CSS_POPUP_BUTTONROW_C);
		popup.getStyleClass().add(Constants.CSS_POPUP_CLASS);

		//Import events
		exitButton.setOnAction(new ExitHandler());
		doneButton.setOnAction(new FinishImportHandler(_pic, _momento, fileInput, fileName));

		//Show window
		Scene scene = new Scene(popup, Constants.W_POPUP, Constants.H_POPUP);
		scene.getStylesheets().add(getClass().getResource(Constants.CSS_FILENAME).toExternalForm());
		Stage newWindow = new Stage();
		newWindow.setTitle(Constants.TITLE_IMPORT_WINDOW);
		newWindow.setScene(scene);
		newWindow.show();
	}

	protected void buildExport() {
		//Create control and layout nodes

		Label fileLabel = new Label(Constants.PROMPT_FILEPATH);
		TextField fileInput = new TextField(Constants.TEXT_RANDOM_FILE);
		VBox fileField = new VBox(fileLabel,fileInput);

		Button doneButton = new Button(Constants.TEXT_DONE);
		Button exitButton = new Button(Constants.TEXT_EXIT);
		HBox buttonRow = new HBox(exitButton, doneButton);
		
		VBox display = new VBox(fileField);
		VBox popup = new VBox(display , buttonRow);
		
		//move
		fileField.setAlignment(Pos.CENTER);
		fileField.getStyleClass().add(Constants.CSS_POPUP_V);

		//Size elements
		fileInput.setMaxSize(Constants.W_POPUP_L, Constants.H_POPUP_L);
		fileLabel.setPrefSize(Constants.W_POPUP_L, Constants.H_POPUP_L);
		exitButton.setPrefSize(Constants.W_POPUP_B, Constants.H_POPUP_B);
		doneButton.setPrefSize(Constants.W_POPUP_B, Constants.H_POPUP_B);
		

		//Space and align elements
		display.setAlignment(Pos.TOP_CENTER);
		buttonRow.setAlignment(Pos.BOTTOM_RIGHT);
		popup.setAlignment(Pos.CENTER);
		display.setSpacing(Constants.S_10);
		buttonRow.setSpacing(Constants.S_5);
		popup.setSpacing(Constants.S_20);

		//Apply CSS styles
		fileLabel.getStyleClass().add(Constants.CSS_POPUP_L);
		fileInput.getStyleClass().add(Constants.CSS_POPUP_T);
		exitButton.getStyleClass().add(Constants.CSS_POPUP_B);
		doneButton.getStyleClass().add(Constants.CSS_POPUP_B);
		display.getStyleClass().add(Constants.CSS_POPUP_V);
		buttonRow.getStyleClass().add(Constants.CSS_POPUP_BUTTONROW_C);
		popup.getStyleClass().add(Constants.CSS_POPUP_CLASS);
		
		//Import events
		exitButton.setOnAction(new ExitHandler());
		doneButton.setOnAction(new FinishExportHandler(_pic, _momento, fileInput));

		//Show window
		Scene scene = new Scene(popup, Constants.W_POPUP, Constants.H_POPUP);
		scene.getStylesheets().add(getClass().getResource(Constants.CSS_FILENAME).toExternalForm());
		Stage newWindow = new Stage();
		newWindow.setTitle(Constants.TITLE_EXPORT_WINDOW);
		newWindow.setScene(scene);
		newWindow.show();
	}

	protected void buildScale() {
		//Create control and layout nodes

		Label percentLabel = new Label(Constants.PROMPT_PERCENT);
		TextField percentInput = new TextField(Constants.MATH_PERCENT_DEFAULT+"");
		VBox percentField = new VBox(percentLabel, percentInput);
		
		Button doneButton = new Button(Constants.TEXT_DONE);
		Button exitButton = new Button(Constants.TEXT_EXIT);
		HBox buttonRow = new HBox(exitButton, doneButton);
		
		VBox display = new VBox(percentField);
		VBox popup = new VBox(display ,buttonRow);
		
		percentField.setAlignment(Pos.CENTER);
		percentField.getStyleClass().add(Constants.CSS_POPUP_V);
		

		//Size elements
		percentInput.setMaxSize(Constants.W_POPUP_L, Constants.H_POPUP_L);
		percentLabel.setPrefSize(Constants.W_POPUP_L, Constants.H_POPUP_L);
		exitButton.setPrefSize(Constants.W_POPUP_B, Constants.H_POPUP_B);
		doneButton.setPrefSize(Constants.W_POPUP_B, Constants.H_POPUP_B);
		

		//Space and align elements
		display.setAlignment(Pos.TOP_CENTER);
		buttonRow.setAlignment(Pos.BOTTOM_RIGHT);
		popup.setAlignment(Pos.CENTER);
		display.setSpacing(Constants.S_10);
		buttonRow.setSpacing(Constants.S_5);
		popup.setSpacing(Constants.S_20);

		//Apply CSS styles
		percentLabel.getStyleClass().add(Constants.CSS_POPUP_L);
		percentInput.getStyleClass().add(Constants.CSS_POPUP_T);
		exitButton.getStyleClass().add(Constants.CSS_POPUP_B);
		doneButton.getStyleClass().add(Constants.CSS_POPUP_B);
		display.getStyleClass().add(Constants.CSS_POPUP_V);
		buttonRow.getStyleClass().add(Constants.CSS_POPUP_BUTTONROW_C);
		popup.getStyleClass().add(Constants.CSS_POPUP_CLASS);

		//Import events
		exitButton.setOnAction(new ExitHandler());
		doneButton.setOnAction(new FinishScaleHandler(_pic, _momento, percentInput));
		
		//Show window
		Scene scene = new Scene(popup, Constants.W_POPUP, Constants.H_POPUP);
		scene.getStylesheets().add(getClass().getResource(Constants.CSS_FILENAME).toExternalForm());
		Stage newWindow = new Stage();
		newWindow.setTitle(Constants.TITLE_SCALE_WINDOW);
		newWindow.setScene(scene);
		newWindow.show();

	}


	protected void buildRotate() {
		//Create control and layout nodes
		
		double img_x = _pic.getWidth()/2;
		double img_y = _pic.getHeight()/2;
		

		Label xLabel = new Label(Constants.PROMPT_X);
		TextField xInput = new TextField(img_x+"");
		VBox xField = new VBox(xLabel, xInput);


		Label yLabel = new Label(Constants.PROMPT_Y);
		TextField yInput = new TextField(img_y+"");
		VBox yField = new VBox(yLabel, yInput);
		
		Label angleLabel = new Label(Constants.PROMPT_ANGLE);
		TextField angleInput = new TextField(Constants.MATH_ANGLE_DEFAULT+"");
		VBox angleField = new VBox(angleLabel, angleInput);

		Button doneButton = new Button(Constants.TEXT_DONE);
		Button exitButton = new Button(Constants.TEXT_EXIT);
		HBox buttonRow = new HBox(exitButton, doneButton);
		
		VBox display = new VBox(xField, yField, angleField);
		VBox popup = new VBox(display, buttonRow);
		

		//Size elements
		angleInput.setMaxSize(Constants.W_POPUP_L, Constants.H_POPUP_L);
		angleLabel.setPrefSize(Constants.W_POPUP_L, Constants.H_POPUP_L);
		yInput.setMaxSize(Constants.W_POPUP_L, Constants.H_POPUP_L);
		yLabel.setPrefSize(Constants.W_POPUP_L, Constants.H_POPUP_L);
		xInput.setMaxSize(Constants.W_POPUP_L, Constants.H_POPUP_L);
		xLabel.setPrefSize(Constants.W_POPUP_L, Constants.H_POPUP_L);
		exitButton.setPrefSize(Constants.W_POPUP_B, Constants.H_POPUP_B);
		doneButton.setPrefSize(Constants.W_POPUP_B, Constants.H_POPUP_B);
	
		//Space and align elements
		display.setAlignment(Pos.TOP_CENTER);
		buttonRow.setAlignment(Pos.BOTTOM_RIGHT);
		popup.setAlignment(Pos.CENTER);
		display.setSpacing(Constants.S_10);
		buttonRow.setSpacing(Constants.S_5);
		popup.setSpacing(Constants.S_20);

		//Apply CSS styles
		xLabel.getStyleClass().add(Constants.CSS_POPUP_L);
		yLabel.getStyleClass().add(Constants.CSS_POPUP_L);
		angleLabel.getStyleClass().add(Constants.CSS_POPUP_L);
		angleInput.getStyleClass().add(Constants.CSS_POPUP_T);
		yInput.getStyleClass().add(Constants.CSS_POPUP_T);
		xInput.getStyleClass().add(Constants.CSS_POPUP_T);
		exitButton.getStyleClass().add(Constants.CSS_POPUP_B);
		doneButton.getStyleClass().add(Constants.CSS_POPUP_B);
		
		display.getStyleClass().add(Constants.CSS_POPUP_V);
		buttonRow.getStyleClass().add(Constants.CSS_POPUP_BUTTONROW_C);
		popup.getStyleClass().add(Constants.CSS_POPUP_CLASS);
		
		
		//move
		xField.setAlignment(Pos.CENTER);
		xField.getStyleClass().add(Constants.CSS_POPUP_V);
		yField.setAlignment(Pos.CENTER);
		yField.getStyleClass().add(Constants.CSS_POPUP_V);
		angleField.setAlignment(Pos.CENTER);
		angleField.getStyleClass().add(Constants.CSS_POPUP_V);
		

		//Import events
		exitButton.setOnAction(new ExitHandler());
		doneButton.setOnAction(new FinishRotateHandler(_pic, _momento, angleInput, xInput, yInput));
		
		//Show window
		Scene scene = new Scene(popup, Constants.W_POPUP, Constants.H_POPUP);
		scene.getStylesheets().add(getClass().getResource(Constants.CSS_FILENAME).toExternalForm());
		Stage newWindow = new Stage();
		newWindow.setTitle(Constants.TITLE_ROTATE_WINDOW);
		newWindow.setScene(scene);
		newWindow.show();
	}
}
