package application;

/**
 * Driver for elements of the application
 * @author Johnathan Dewey
 * @version September 13th 2023
 */

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.geometry.Pos;

public class InterfaceManager {
	
	/**
	 * Attributes
	 */
	private Picture _pic;
	private Momento _momento;

	/**
	 * Constructor
	 */
	public InterfaceManager(Picture pic, Momento momento) {
		_pic = pic;
		_momento = momento;
		_momento.addMomento(_pic.copy());
	}

	/**
	 * Builds the scene for MultiMedia Buttons
	 */
	private VBox buildMultimedia() {
		//Create control and layout nodes
		Button composite = new Button(Constants.TEXT_COMPOSITE);
		Button interpolation = new Button(Constants.TEXT_POLATION);
		Label label = new Label(Constants.TEXT_MULTIMDEIA);
		VBox buttons = new VBox(composite, interpolation);
		VBox multi = new VBox(label, buttons);

		//Size elements
		composite.setPrefSize(Constants.W_MENU_B, Constants.H_MENU_B);
		interpolation.setPrefSize(Constants.W_MENU_B, Constants.H_MENU_B);
		label.setPrefSize(Constants.W_MENU_L, Constants.H_MENU_L);
		
		//Space and align elements
		multi.setSpacing(Constants.S_5);
		multi.setAlignment(Pos.CENTER);
		label.setAlignment(Pos.CENTER);
		buttons.setAlignment(Pos.CENTER);
		buttons.setSpacing(1);
		

		//Import events
		composite.setOnAction(new CompositeHandler(_pic, _momento));
		interpolation.setOnAction(new InterpolationHandler(_pic, _momento));
		
		//Apply CSS styles
		composite.getStyleClass().add(Constants.CSS_MENU_B);
		interpolation.getStyleClass().add(Constants.CSS_MENU_B);
		label.getStyleClass().add(Constants.CSS_MENU_L);


		return multi;
	}

	/**
	 * Builds the scene for Effect Buttons
	 */
	private VBox buildEffects() {
		//Create control and layout nodes
		Button blur = new Button(Constants.TEXT_BLUR);
		Button guassian = new Button(Constants.TEXT_GAUSSIAN);
		Button edge = new Button(Constants.TEXT_EDGE);
		Button channel = new Button(Constants.TEXT_CHANNEL);
		Button noise = new Button(Constants.TEXT_NOISE);
		Button brighten = new Button(Constants.TEXT_BRIGHTNESS);
		Button greyscale = new Button(Constants.TEXT_GREYSCALE);
		Button threshold = new Button(Constants.TEXT_THRESHOLD);
		Label label = new Label(Constants.TEXT_EFFECT);
		VBox buttons = new VBox(blur, guassian, edge, channel, noise, brighten, greyscale, threshold);
		VBox effect = new VBox(label, buttons);

		//Size elements
		label.setPrefSize(Constants.W_MENU_L, Constants.H_MENU_L);
		blur.setPrefSize(Constants.W_MENU_B, Constants.H_MENU_B);
		guassian.setPrefSize(Constants.W_MENU_B, Constants.H_MENU_B);
		edge.setPrefSize(Constants.W_MENU_B, Constants.H_MENU_B);
		channel.setPrefSize(Constants.W_MENU_B, Constants.H_MENU_B);
		noise.setPrefSize(Constants.W_MENU_B, Constants.H_MENU_B);
		brighten.setPrefSize(Constants.W_MENU_B, Constants.H_MENU_B);
		greyscale.setPrefSize(Constants.W_MENU_B, Constants.H_MENU_B);
		threshold.setPrefSize(Constants.W_MENU_B, Constants.H_MENU_B);

		//Space and align elements
		effect.setSpacing(Constants.S_5);
		effect.setAlignment(Pos.CENTER);
		label.setAlignment(Pos.CENTER);
		buttons.setAlignment(Pos.CENTER);
		buttons.setSpacing(Constants.S_1);

		//Import events
		blur.setOnAction(new BlurHandler(_pic, _momento));
		guassian.setOnAction(new GaussianHandler(_pic, _momento));
		edge.setOnAction(new EdgeHandler(_pic, _momento));
		channel.setOnAction(new ChannelHandler(_pic, _momento));
		noise.setOnAction(new NoiseHandler(_pic, _momento));
		brighten.setOnAction(new BrightenHandler(_pic, _momento));
		greyscale.setOnAction(new GreyscaleHandler(_pic, _momento));
		threshold.setOnAction(new ThresholdHandler(_pic, _momento));

		//Apply CSS styles
		label.getStyleClass().add(Constants.CSS_MENU_L);
		blur.getStyleClass().add(Constants.CSS_MENU_B);
		guassian.getStyleClass().add(Constants.CSS_MENU_B);
		edge.getStyleClass().add(Constants.CSS_MENU_B);
		channel.getStyleClass().add(Constants.CSS_MENU_B);
		noise.getStyleClass().add(Constants.CSS_MENU_B);
		brighten.getStyleClass().add(Constants.CSS_MENU_B);
		greyscale.getStyleClass().add(Constants.CSS_MENU_B);
		threshold.getStyleClass().add(Constants.CSS_MENU_B);

		return effect;
	}

	/**
	 * Builds the scene for the Info Bar
	 */
	private HBox buildIO() {
		//Create control and layout nodes
		Button importFile = new Button(Constants.TEXT_INPUT);
		Label fileName = new Label(Constants.TEXT_FILENAME);
		Button exportFile = new Button(Constants.TEXT_EXPORT);
		HBox IOFunctions = new HBox(importFile, fileName, exportFile);

		//Size elements
		importFile.setPrefSize(Constants.W_OUTPUT_B, Constants.H_OUTPUT_B);
		fileName.setPrefSize(Constants.W_OUTPUT_L, Constants.H_OUTPUT_L);
		exportFile.setPrefSize(Constants.W_OUTPUT_B, Constants.H_OUTPUT_B);
		
		//Space and align elements
		IOFunctions.setSpacing(Constants.S_10);
		IOFunctions.setAlignment(Pos.CENTER);
		fileName.setAlignment(Pos.BASELINE_CENTER);

		//Import events
		importFile.setOnAction(new InputFileHandler(_pic, _momento, fileName));
		exportFile.setOnAction(new OutputFileHandler(_pic, _momento));

		//Apply CSS styles
		importFile.getStyleClass().add(Constants.CSS_OUTPUT_B);
		fileName.getStyleClass().add(Constants.CSS_OUTPUT_L);
		exportFile.getStyleClass().add(Constants.CSS_OUTPUT_B);

		return IOFunctions;
	}

	/**
	 * Builds the scene for the image 
	 */
	private StackPane buildImage() {
		//draw image
		_pic.draw();
		_pic.getStyleClass().add(Constants.CSS_IMAGE_CLASS);

		//Create control and layout nodes
		StackPane imagePane = new StackPane(_pic);
		
		imagePane.setMaxSize(Constants.MAX_IMG_WIDTH, Constants.MAX_IMG_HEIGHT);

		//Apply CSS styles
		//imagePane.getStyleClass().add(Constants.CSS_IMAGE_CLASS);

		//return node
		return imagePane;
	}

	/**
	 * Builds the bottom buttons 
	 */
	private HBox buildTransformations() {
		//Create control and layout nodes
		Button undo = new Button(Constants.TEXT_UNDO);
		Button redo = new Button(Constants.TEXT_REDO);
		Button scale = new Button(Constants.TEXT_SCALE);
		Button rotate = new Button(Constants.TEXT_ROTATE);
		HBox transformationFunctions = new HBox(undo, redo, scale, rotate);

		//Size elements
		undo.setPrefSize(Constants.W_OUTPUT_B, Constants.H_OUTPUT_B);
		redo.setPrefSize(Constants.W_OUTPUT_B, Constants.H_OUTPUT_B);
		scale.setPrefSize(Constants.W_OUTPUT_B, Constants.H_OUTPUT_B);
		rotate.setPrefSize(Constants.W_OUTPUT_B, Constants.H_OUTPUT_B);
		
		//Space and align elements
		transformationFunctions.setSpacing(Constants.S_10);
		transformationFunctions.setAlignment(Pos.CENTER);

		//Import events
		undo.setOnAction(new UndoHandler(_pic, _momento));
		redo.setOnAction(new RedoHandler(_pic, _momento));
		scale.setOnAction(new ScaleHandler(_pic, _momento));
		rotate.setOnAction(new RotateHandler(_pic, _momento));

		//Apply CSS styles
		undo.getStyleClass().add(Constants.CSS_OUTPUT_B);
		redo.getStyleClass().add(Constants.CSS_OUTPUT_B);
		scale.getStyleClass().add(Constants.CSS_OUTPUT_B);
		rotate.getStyleClass().add(Constants.CSS_OUTPUT_B);

		return transformationFunctions;
	}
	
	
	  /**
	   * 
	   */
	private VBox buildMenu() {
		// make buttons
		VBox multimediaFunctions = this.buildMultimedia();
		VBox effectFunctions = this.buildEffects();
		
		multimediaFunctions.getStyleClass().add(Constants.CSS_MENU_V);
		effectFunctions.getStyleClass().add(Constants.CSS_MENU_V);
		
		//make VBox
		VBox menu = new VBox(multimediaFunctions, effectFunctions);
		menu.setPrefSize(Constants.W_MENU, Constants.H_MENU);
		menu.setSpacing(Constants.S_10);
		menu.setAlignment(Pos.CENTER);
		menu.getStyleClass().add(Constants.CSS_MENU_CLASS);
		
		return menu;

	}
	
	  /**
	   * 
	   */
	private VBox buildOutput() {
		HBox IOBar = this.buildIO();
		StackPane imagePane = this.buildImage();
		HBox transformationBar = this.buildTransformations();
		
		
		IOBar.getStyleClass().add(Constants.CSS_OUTPUT_H);
		imagePane.getStyleClass().add(Constants.CSS_IMAGE_CLASS);
		transformationBar.getStyleClass().add(Constants.CSS_OUTPUT_H);

		VBox output = new VBox(IOBar, transformationBar, imagePane);
		output.setPrefSize(Constants.W_OUTPUT, Constants.H_OUTPUT);
		output.setSpacing(Constants.S_10);
		output.setAlignment(Pos.CENTER);
		output.getStyleClass().add(Constants.CSS_OUTPUT_CLASS);
		
		return output;
	}

	  /**
	   * 
	   */
	protected Scene buildApp() {
		// build environment
		VBox menu = this.buildMenu();
		VBox output = this.buildOutput();
		HBox app = new HBox(menu, output);
		app.getStyleClass().add(Constants.CSS_APP_CLASS);
		

		Scene scene = new Scene(app, Constants.W_APP,Constants.H_APP);
		scene.getStylesheets().add(getClass().getResource(Constants.CSS_FILENAME).toExternalForm());
	
		return scene;
	}
}