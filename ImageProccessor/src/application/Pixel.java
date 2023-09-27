package application;

/**
 * A class for a basic editable Pixels
 * @author Johnathan Dewey
 * @version September 13th 2023
 */

import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class Pixel {

	/**
	 * Attributes
	 */
	private PixelWriter _writer;
	private PixelReader _reader;
	private Color _color;
	int _xPosition;
	int _yPosition;

	/**
	 * Constructor
	 */
	public Pixel(WritableImage img, int x, int y) {
		_writer = img.getPixelWriter();
		_reader = img.getPixelReader();
		try {
			_color = _reader.getColor(x, y);
		} catch (Exception e) {
			_color = Constants.COLOR_BLACK;
		}
		_xPosition = x;
		_yPosition = y;
	}

	/**
	 * Getter Method: Returns Random RGB value for a pixel in integer format (0-255)
	 */
	public static int getRandomRBG() {
		return (int) (Math.random() * Constants.MATH_MAX_RGB);
	}

	/**
	 * Getter Method: Returns Red RGB value for this pixel in integer format (0-255)
	 */
	public int getRed() {
		return (int)(_color.getRed() * Constants.MATH_MAX_RGB);
	}

	/**
	 * Getter Method: Returns Green RGB value for this pixel in integer format (0-255)
	 */
	public int getGreen() {
		return (int)(_color.getGreen() * Constants.MATH_MAX_RGB);
	}

	/**
	 * Getter Method: Returns Blue RGB value for this pixel in integer format (0-255)
	 */
	public int getBlue() {
		return (int)(_color.getBlue() * Constants.MATH_MAX_RGB);
	}

	/**
	 * Support Getter Method for quantization(): Gets channel value of a pixel
	 */
	private int getChannelValue(double[] thresholdLevels, double[] outputLevels, double quantization) {

		int end = thresholdLevels.length - 1;

		for (int i = end; i >= 0; i--) {
			if (quantization >= thresholdLevels[i]) {
				return (int) (outputLevels[i] * Constants.MATH_MAX_RGB);
			}
		}

		return (int) (outputLevels[end] * Constants.MATH_MAX_RGB);
	}

	public Color getColor() {
		return _color;
	}

	/**
	 * Setter Method: Sets the color of this pixel to the determined Color object
	 */
	public void setColor(Color color) {
		_writer.setColor(_xPosition, _yPosition, color);
	}

	/**
	 * Manipulation Method: returns the color of this pixel after applying a composite effect based on another pixel and a mask
	 */
	public void composite(Pixel foreground, Pixel mask) {
		double alpha = mask.alphaEquation();

		int red = compositeEquation(this.getRed(), foreground.getRed(), alpha);
		int green = compositeEquation(this.getGreen(), foreground.getGreen(), alpha);
		int blue = compositeEquation(this.getBlue(), foreground.getBlue(), alpha);

		this.setColor(Color.rgb(red,green,blue));
	}

	/**
	 * Manipulation Method: returns the channel values of the color of this pixel after applying a convolution effect based on a kernel
	 */
	public void convolution(double[] accumulator, Kernel kernel, Picture picture, int x, int y) {
		int RED = 0;
		int GREEN = 1;
		int BLUE = 2;

		for (int kernelX = 0; kernelX < kernel._radius; kernelX++) {
			for (int kernelY = 0; kernelY < kernel._radius; kernelY++) {

				int imageX = x - (1 + kernelX);
				int imageY = y - (1 + kernelY);

				Pixel otherPixel = picture.getPixel(snapToImageBounds(imageX), snapToImageBounds(imageY));

				double kernelValue = kernel._kernel[kernelX][kernelY];

				accumulator[RED] += (otherPixel.getRed() * kernelValue);
				accumulator[GREEN] += (otherPixel.getGreen() * kernelValue);
				accumulator[BLUE] += (otherPixel.getBlue() * kernelValue);
			}
		}
		this.setColor(Color.rgb(snapToRGBBounds(accumulator[RED]), snapToRGBBounds(accumulator[GREEN]), snapToRGBBounds(accumulator[BLUE])));
	}

	/**
	 * Manipulation Method: returns the color of this pixel after applying a interpolation/extrapolation effect based on another pixel and an alpha value
	 */
	public void polation(Pixel overlay, double alpha) {

		int red = polationEquation(this.getRed(), overlay.getRed(), alpha);
		int green = polationEquation(this.getGreen(), overlay.getGreen(), alpha);
		int blue = polationEquation(this.getBlue(), overlay.getBlue(), alpha);

		this.setColor(Color.rgb(red, green, blue));
	}	

	/**
	 * Manipulation Method: returns the color of this pixel after applying a greyscale effect
	 */
	public void greyscale() {
		this.setColor(Color.rgb(luminanceEquation(),  luminanceEquation(),  luminanceEquation()));
	}

	/**
	 * Manipulation Method: returns the color of this pixel after applying a threshold effect
	 */
	public void threshold(double average, double threshholdLimit) {
		if (average >= threshholdLimit) {
			this.setColor(Constants.COLOR_WHITE);
		}
		else {
			this.setColor(Constants.COLOR_BLACK);
		}
	}

	/**
	 * Manipulation Method: returns the color of this pixel after applying a channel reduction effect
	 */
	public void quantization(int level, double[] thresholdLevels, double[] outputLevels) {

		double red = quantizationEquation(this.getRed(), level);
		double blue = quantizationEquation(this.getBlue(), level);
		double green = quantizationEquation(this.getGreen(), level);

		Color color = Color.rgb(getChannelValue(thresholdLevels, outputLevels, red), getChannelValue(thresholdLevels, outputLevels, green), getChannelValue(thresholdLevels, outputLevels, blue));

		this.setColor(color);
	}

	/**
	 * Support Method: Calculates the luminance value of a pixel
	 */
	private int luminanceEquation() {
		double product = ((Constants.MATH_RED_COEFFICIENT * (double)this.getRed()) + (Constants.MATH_GREEN_COEFFICIENT * (double)this.getGreen()) +  (Constants.MATH_BLUE_COEFFICIENT * (double)this.getBlue()));
		return (int) product;
	}

	/**
	 * Support Method: Calculates the alpha value of a pixel
	 */
	private double alphaEquation() {
		double average = this.getRed() / Constants.MATH_MAX_RGB;
		this.threshold(average, Constants.MATH_THRESHOLD_DEFAULT);

		return (_color.getBlue());
	}

	/**
	 * Support Method: Calculates the value of a pixel channel using the interpolation/extrapolation equation
	 */
	private int polationEquation(int initial, int overlay, double alpha) {
		double product = ((1 - alpha) * (double)initial   + ( alpha * (double)overlay) );
		return (int) product;
	}

	/**
	 * Support Method: Calculates the value of a pixel channel using the composite equation
	 */
	private int compositeEquation(int background, int foreground, double alpha) {
		double product = ((alpha * (double)foreground) + ((1 - alpha) * (double)background));
		return (int) product;
	}

	/**
	 * Support Method: Calculates the value of a pixel channel using the quantization equation
	 */
	private double quantizationEquation(int rgb, int level) {
		double pixel = rgb / Constants.MATH_MAX_RGB;
		return Math.floor((pixel * (level - 1)) + 0.5) / (level - 1);
	}

	/**
	 * Support Method: Catches RGB values that exceed the bounds, snapping them to the max or min RGB value
	 */
	private int snapToRGBBounds(double accumulator) {
		if (accumulator < Constants.MATH_MIN_RGB) {
			return (int) Constants.MATH_MIN_RGB;
		}
		else if (accumulator > Constants.MATH_MAX_RGB){
			return (int) Constants.MATH_MAX_RGB;
		}
		else {
			return (int) accumulator;
		}
	}

	/**
	 * Support Method: Ignores values less than zero
	 */
	private int snapToImageBounds(double coordinate) {
		if (coordinate < 0) {
			return (int) 0;
		}
		else {
			return (int) coordinate;
		}
	}

}
