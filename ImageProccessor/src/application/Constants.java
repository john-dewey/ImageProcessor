package application;

import java.io.File;

/**
 * Constant attributes of this application
 * @author Johnathan Dewey
 * @version September 13th 2023
 */

import javafx.scene.paint.Color;

public class Constants{
	
	/**
	 * Colors
	 */
	protected static final Color COLOR_BLACK = Color.BLACK;
	protected static final Color COLOR_WHITE = Color.WHITE;
	protected static final Color COLOR_BACKGROUND = Color.BLACK;
	
	/**
	 * Booleans
	 */
	protected static final boolean ENABLE_PRESERVE_RATIO = true;
	protected static final boolean ENABLE_SMOOTHING = true;
	
	/**
	 * CSS Constants
	 */
	protected static final String CSS_FILENAME = "application.css";
	protected static final String CSS_APP_CLASS = "app";
	
	protected static final String CSS_OUTPUT_CLASS = "output";
	protected static final String CSS_OUTPUT_B = "outputButton";
	protected static final String CSS_OUTPUT_H = "outputHbox";
	protected static final String CSS_OUTPUT_L = "outputLabel";
	
	protected static final String CSS_MENU_CLASS = "menu";
	protected static final String CSS_MENU_B = "menuButton";
	protected static final String CSS_MENU_L = "menuLabel";
	protected static final String CSS_MENU_V = "menuVbox";
	
	
	protected static final String CSS_POPUP_CLASS = "popup";
	protected static final String CSS_POPUP_BUTTONROW_C = "popupButtonRow";
	protected static final String CSS_POPUP_V = "popupVbox";
	protected static final String CSS_POPUP_B = "popupButton";
	protected static final String CSS_POPUP_L = "popupLabel";
	protected static final String CSS_POPUP_T = "popupTextbox";
	
	protected static final String CSS_ERROR_L = "errorLabel";
	protected static final String CSS_IMAGE_CLASS = "image";

	
	
	/**
	 * File Paths
	 */
	static String normalizeFilePath(File file) {
		String text = file.getAbsolutePath();
		String returnString = "";
		for (int i = 0; i < text.length(); i++) {
			if (text.charAt(i) == '\\') {
				returnString += "/";
			}
			else {
				returnString += text.charAt(i);
			}
		}
		return returnString;
	}

	 protected static final File DOG_FILE = new File("src/images/doggos.jpg");
	 protected static final File RANDOM_FILE = new File("src/images/random.jpg");
	 protected static final File MASK_FILE = new File("src/images/mask.jpg");
	 protected static final File TEMP_FILE = new File("src/out/temp.png");
	 
	 protected static final String TEXT_DOG_FILE = normalizeFilePath(DOG_FILE);
	 protected static final String TEXT_RANDOM_FILE = normalizeFilePath(RANDOM_FILE);
	 protected static final String TEXT_MASK_FILE = normalizeFilePath(MASK_FILE);
	 protected static final String TEXT_TEMP_FILE = normalizeFilePath(TEMP_FILE);
	 protected static final String TEXT_FILENAME = "--No Image Selected--";
	
	/**
	 * Titles
	 */
	protected static final String TITLE_APPLICATION = "Image Processor";
	protected static final String TITLE_IMPORT_WINDOW = "Import an Image";
	protected static final String TITLE_EXPORT_WINDOW = "Export an Image";
	protected static final String TITLE_POLATION_WINDOW = "Interpolate/Extrapolate Menu";
	protected static final String TITLE_COMPOSITE_WINDOW = "Composite Menu";
	protected static final String TITLE_GAUSSIAN_WINDOW = "Gaussian Menu";
	protected static final String TITLE_CHANNEL_WINDOW = "Channel Adjustment Menu";
	protected static final String TITLE_NOISE_WINDOW = "Noise Menu";
	protected static final String TITLE_BRIGHTEN_WINDOW ="Brighten/Darken Menu";
	protected static final String TITLE_THRESHOLD_WINDOW ="Threshold Menu";
	protected static final String TITLE_SCALE_WINDOW = "Scale Menu";
	protected static final String TITLE_ROTATE_WINDOW = "Rotate Menu";
	/**
	 * Display Text
	 */
	protected static final String TEXT_COMPOSITE = "Composite";
	protected static final String TEXT_POLATION = "Interpolation/Extrapolation";
	protected static final String TEXT_MULTIMDEIA = "Multimedia Effects";
	
	protected static final String TEXT_BLUR = "Box Blur";
	protected static final String TEXT_CHANNEL = "Adjust Channels";
	protected static final String TEXT_EDGE = "Edge Detect";
	protected static final String TEXT_GAUSSIAN = "Gaussian Blur";
	protected static final String TEXT_BRIGHTNESS = "Adjust Brightness";
	protected static final String TEXT_NOISE = "Noisify";
	protected static final String TEXT_THRESHOLD = "Threshold";
	protected static final String TEXT_GREYSCALE = "Greyscale";
	protected static final String TEXT_EFFECT = "Pixel Effects";
	
	protected static final String TEXT_INPUT = "Import";
	protected static final String TEXT_EXPORT = "Export";
	
	protected static final String TEXT_SCALE = "Scale";
	protected static final String TEXT_ROTATE = "Rotate";
	protected static final String TEXT_UNDO = "Undo";
	protected static final String TEXT_REDO = "Redo";
	
	protected static final String TEXT_DONE = "Done";
	protected static final String TEXT_EXIT = "Exit";
	
	/**
	 * Field Prompts
	 */
	protected static final String PROMPT_FILEPATH = "Specify the path to the image: ";
	protected static final String PROPMPT_ALPHA = "Specify an alpha value: ";
	protected static final String PROMPT_FUNCTION = "Select a function: ";
	protected static final String PROMPT_POLATION = "Specify the path to the image to interpolate with: ";
	protected static final String PROMPT_COMPOSITE = "Specify the path to the image to composite with: ";
	protected static final String PROMPT_MASK = "Specify the path to the image to mask with: ";
	protected static final String PROMPT_CHANNEL = "Specify the channel value (levels/bit): ";
	protected static final String PROMPT_SIGMA = "Specify a sigma value: ";
	protected static final String PROMPT_PERCENT = "Specify a percent: ";
	protected static final String PROMPT_THRESHOLD = "Specify a threshold value: ";
	protected static final String PROMPT_X = "Specify an X value: ";
	protected static final String PROMPT_Y = "Specify a Y value: ";
	protected static final String PROMPT_ANGLE = "Specify a angle value (degrees): ";
	
	/**
	 * Scene Sizes
	 *  
	 */
	static int multiply(int size, double percent) {
		return (int)((double) size * percent);
	}
	
	protected static final int[] SIZE_APP = {960, 540};
	protected static final int W_APP = SIZE_APP[0];
	protected static final int H_APP = SIZE_APP[1];
	
	//left
	protected static final int W_MENU = multiply(W_APP, 0.33);
	protected static final int H_MENU = multiply(H_APP, 1);
	
	protected static final int W_MENU_B = multiply(W_MENU, 0.75);
	protected static final int H_MENU_B = multiply(H_MENU, 0.04);
	
	protected static final int W_MENU_L = multiply(W_MENU, 0.75);
	protected static final int H_MENU_L = multiply(H_MENU, 0.04);
	
	//right
	protected static final int W_OUTPUT = multiply(W_APP, 0.66);
	protected static final int H_OUTPUT = multiply(H_APP, 1);
	
	protected static final int W_OUTPUT_B = multiply(W_OUTPUT, 0.20);
	protected static final int H_OUTPUT_B = multiply(H_OUTPUT, 0.04);
	
	protected static final int W_OUTPUT_L = multiply(W_OUTPUT, 0.5);
	protected static final int H_OUTPUT_L = multiply(H_OUTPUT, 0.04);
	
	//image
	protected static final int[] SIZE_IMAGE = {multiply(W_OUTPUT, 0.6), multiply(H_OUTPUT, 0.5)};
	protected static final int MAX_IMG_WIDTH = multiply(SIZE_IMAGE[0], 1);
	protected static final int MAX_IMG_HEIGHT = multiply(SIZE_IMAGE[1], 1);
	
	protected static final int MAX_PANE_WIDTH = multiply(SIZE_IMAGE[0], 2);
	protected static final int MAX_PANE_HEIGHT = multiply(SIZE_IMAGE[1], 2);
	
	
	//pop ups
	protected static final int[] SIZE_POPUP = {W_APP/2, H_APP/2};
	protected static final int W_POPUP = SIZE_POPUP[0];
	protected static final int H_POPUP = SIZE_POPUP[1];
	
	protected static final int W_POPUP_B = multiply(W_POPUP, 0.25);
	protected static final int H_POPUP_B = multiply(H_POPUP, 0.04);
	
	protected static final int W_POPUP_L = multiply(W_POPUP, 0.85);
	protected static final int H_POPUP_L = multiply(H_POPUP, 0.04);
	
	protected static final int S_20 = 20;
	protected static final int S_10 = 10;
	protected static final int S_5 = 5;
	protected static final int S_1 = 1;

	/**
	 * Math Constants
	 */
	protected static final double MATH_MAX_RGB = 255.0;
	protected static final double MATH_MIN_RGB = 0.0;
	
	protected static final double MATH_RED_COEFFICIENT = 0.299;
	protected static final double MATH_GREEN_COEFFICIENT = 0.587;
	protected static final double MATH_BLUE_COEFFICIENT = 0.114;
	
	protected static final double MATH_ALPHA_DEFAULT = 0.5;
	protected static final double MATH_THRESHOLD_DEFAULT = 0.5;
	protected static final int MATH_CHANNEL_DEFAULT = 4;
	protected static final double MATH_SIGMA_DEFAULT = 0.5;
	protected static final double MATH_PERCENT_DEFAULT = 0.5;
	
	protected static final double MATH_X_DEFAULT = MAX_IMG_WIDTH/2;
	protected static final double MATH_Y_DEFAULT = MAX_IMG_HEIGHT/2;
	protected static final double MATH_ANGLE_DEFAULT = 180;

}